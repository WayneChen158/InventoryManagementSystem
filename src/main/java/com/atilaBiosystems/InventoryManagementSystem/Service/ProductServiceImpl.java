package com.atilaBiosystems.InventoryManagementSystem.Service;

import com.atilaBiosystems.InventoryManagementSystem.DAO.ProductStockForm;
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
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(int productId) {
        Product product = productRepository.findById(productId).orElse(null);
        return product;
    }

    @Override
    public List<CustomComponentRecord> checkComponentInventory(Integer productId) {
        List<CustomComponentRecord> comLst = new ArrayList<>();

        Product product = productRepository.findById(productId).orElse(null);

        assert product != null;
        for (AssemblyBy assemblyItem: product.getAssemblyItems()){
//            int currSize = comLst.size();
            int min = Integer.MAX_VALUE;
            Component com = assemblyItem.getComponent();

            ComponentRecord curr = null;
            for (ComponentRecord comRecord: com.getComponentRecords()) {
                if (comRecord.getAmountInStock() > 0 && comRecord.getAmountInStock() < min) {
                    min = comRecord.getAmountInStock();
                    curr = comRecord;
                }
            }

            if (curr != null) {
                comLst.add(new CustomComponentRecord(curr));
            } else{
                ComponentRecord needToManufacture = new ComponentRecord(com.getComponentCatalog(),com.getComponentName(),
                        null, null, 0, com.getUnit());
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

        ProductRecord latestProductRecord = new ProductRecord(product.getProductCatalog(),product.getProductName(),
                lotNum, currentDate, 0);

        latestProductRecord.setProduct(product);
        productRecordRepository.save(latestProductRecord);

        ManufactureRecord currManufactureRecord = new ManufactureRecord(product.getProductName(),
                currentDate, scale, "YC", "kit(s)", 1);

        currManufactureRecord.setProductRecord(latestProductRecord);

        List<ManufactureRecordDetail> details = new ArrayList<>();

        for (AssemblyBy assemblyItem: product.getAssemblyItems()){
            boolean hasRecord = false;
            Component component = assemblyItem.getComponent();
            Double amountPerAssay = assemblyItem.getAmountPerAssay();
            double currScale = scale * amountPerAssay;
            for (ComponentRecord componentRecord: component.getComponentRecords()){
                if (componentRecord.getAmountInStock() >= currScale){
                    ManufactureRecordDetail currDetail = new ManufactureRecordDetail(componentRecord.getComponentName(),
                            amountPerAssay, currScale, currManufactureRecord);
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
                        if (Objects.equals(mr.getComponentRecord().getComponent().getComponentId(), component.getComponentId())){
                            ManufactureRecordDetail currDetail = new ManufactureRecordDetail(mr.getComponentRecord().getComponentName(),
                                    amountPerAssay, currScale, currManufactureRecord);
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
    public List<ProductRecord> findByAmountInStockGreaterThan() {
        return productRecordRepository.findByAmountInStockGreaterThan(0);
    }

    @Override
    @Transactional
    public void updateStock(Integer productRecordId, Integer updateScale) {
        ProductRecord productRecord = productRecordRepository.findById(productRecordId).orElse(null);
        productRecord.setAmountInStock(updateScale);
    }

    @Override
    public List<ProductStockForm> getProductList() {
        List<ProductStockForm> res = new ArrayList<>();

        List<Product> productList = productRepository.findAll();

        for(Product product: productList){
            int amountInStock = 0;
            for (ProductRecord pr: product.getProductRecords()){
                amountInStock += pr.getAmountInStock();
            }
            ProductStockForm curr = new ProductStockForm(product.getProductId(),
                    product.getProductCatalog(), product.getProductName(), amountInStock);

            res.add(curr);
        }

        return res;
    }
}
