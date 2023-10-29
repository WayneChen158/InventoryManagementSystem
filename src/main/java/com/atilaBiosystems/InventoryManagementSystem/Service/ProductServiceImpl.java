package com.atilaBiosystems.InventoryManagementSystem.Service;

import com.atilaBiosystems.InventoryManagementSystem.Entity.*;
import com.atilaBiosystems.InventoryManagementSystem.Exception.InvalidProductIdException;
import com.atilaBiosystems.InventoryManagementSystem.Exception.MissingComponentException;
import com.atilaBiosystems.InventoryManagementSystem.Repository.ManufactureRecordDetailRepository;
import com.atilaBiosystems.InventoryManagementSystem.Repository.ManufactureRecordRepository;
import com.atilaBiosystems.InventoryManagementSystem.Repository.ProductRecordRepository;
import com.atilaBiosystems.InventoryManagementSystem.Repository.ProductRepository;
import com.atilaBiosystems.InventoryManagementSystem.ReturnObject.CustomComponentRecord;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService{

    private ProductRepository productRepository;
    private ProductRecordRepository productRecordRepository;
    private ManufactureRecordRepository manufactureRecordRepository;
    private ManufactureRecordDetailRepository manufactureRecordDetailRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              ProductRecordRepository productRecordRepository,
                              ManufactureRecordRepository manufactureRecordRepository,
                              ManufactureRecordDetailRepository manufactureRecordDetailRepository) {
        this.productRepository = productRepository;
        this.productRecordRepository = productRecordRepository;
        this.manufactureRecordRepository = manufactureRecordRepository;
        this.manufactureRecordDetailRepository = manufactureRecordDetailRepository;
    }

    @Override
    public List<CustomComponentRecord> checkComponentInventory(Integer productId) {
        List<CustomComponentRecord> comLst = new ArrayList<>();

        Product product = productRepository.findById(productId).orElse(null);

        for (Component com: product.getComponents()){
            int currSize = comLst.size();
            for (ComponentRecord comRecord: com.getComponentRecords()) {
                if (comRecord.getAmountInStock() > 0) {
                    comLst.add(new CustomComponentRecord(comRecord));
                }
            }
            if (comLst.size() == currSize){
                ComponentRecord needToManufacture = new ComponentRecord(com.getComponentName(),
                        null, null, 0);
                needToManufacture.setComponent(com);
                comLst.add(new CustomComponentRecord(needToManufacture));
            }
        }

        return comLst;
    }

    @Override
    public void checkComponentinventoryWithScale(Integer manufactureRecordId, Integer scale) {

        ManufactureRecord manufactureRecord = manufactureRecordRepository.findById(manufactureRecordId).orElse(null);

        for (ManufactureRecordDetail currManDetail: manufactureRecord.getRecordDetails()){
            Double needAmount = currManDetail.getTotalVol();
            if (needAmount > currManDetail.getComponentRecord().getAmountInStock()){
                throw new NullPointerException("Not Enough " + currManDetail.getComponentRecord().getComponentName() + " In Stock");
            }
        }
    }

    @Override
    @Transactional
    public ManufactureRecord putInManufactureLine(Integer productId, Integer scale) {

        Product product = productRepository.findById(productId).orElse(null);

        if (product == null) {
            throw new InvalidProductIdException("Invalid productId " + productId);
        }

        LocalDate localDate = LocalDate.now();
        Date currentDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String lotNum = sdf.format(currentDate);

        ProductRecord latestProductRecord = new ProductRecord(product.getProductName(),
                lotNum, currentDate, 0);

        latestProductRecord.setProduct(product);
        productRecordRepository.save(latestProductRecord);

        ManufactureRecord currManufactureRecord = new ManufactureRecord(product.getProductName(),
                currentDate, scale, "YC", 1);

        currManufactureRecord.setProductRecord(latestProductRecord);

        List<ManufactureRecordDetail> details = new ArrayList<>();

        for (Component component: product.getComponents()){
            boolean hasRecord = false;
            for (ComponentRecord componentRecord: component.getComponentRecords()){
                if (componentRecord.getAmountInStock() >= scale){
                    ManufactureRecordDetail currDetail = new ManufactureRecordDetail(componentRecord.getComponentName(),
                            1.0, scale.doubleValue(), currManufactureRecord);
                    currDetail.setComponentRecord(componentRecord);
                    details.add(currDetail);
                    manufactureRecordDetailRepository.save(currDetail);
                    hasRecord = true;
                    break;
                }
            }
            if (!hasRecord){
                for (ManufactureRecord mr: manufactureRecordRepository.findByStatus(1)){
                    try{
                        if (mr.getComponentRecord().getComponent().getComponentId() == component.getComponentId()){
                            ManufactureRecordDetail currDetail = new ManufactureRecordDetail(mr.getComponentRecord().getComponentName(),
                                    1.0, scale.doubleValue(), currManufactureRecord);
                            currDetail.setComponentRecord(mr.getComponentRecord());
                            details.add(currDetail);
                            manufactureRecordDetailRepository.save(currDetail);
                            hasRecord = true;
                        }

                    } catch (NullPointerException e1){

                    }
                }
            }
            if (!hasRecord) {
                throw new MissingComponentException("Please make sure you have put all the required component in manufacture line");
            }
        }

        manufactureRecordRepository.save(currManufactureRecord);
        return currManufactureRecord;
    }

    @Override
    @Transactional
    public void finishManufacture(Integer manufactureRecordId, Integer updateScale) {
        ManufactureRecord manufactureRecord = manufactureRecordRepository.findById(manufactureRecordId).orElse(null);

        for (ManufactureRecordDetail currManDetail: manufactureRecord.getRecordDetails()){
            ComponentRecord cr = currManDetail.getComponentRecord();
            Double amount = currManDetail.getTotalVol();
            cr.setAmountInStock((int)(cr.getAmountInStock() - amount));

        }

        manufactureRecord.setStatus(2);
        manufactureRecord.getProductRecord().setAmountInStock(updateScale);
    }
}
