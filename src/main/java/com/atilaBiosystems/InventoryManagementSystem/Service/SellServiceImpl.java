package com.atilaBiosystems.InventoryManagementSystem.Service;

import com.atilaBiosystems.InventoryManagementSystem.DAO.SellItemDAO;
import com.atilaBiosystems.InventoryManagementSystem.Entity.ComponentRecord;
import com.atilaBiosystems.InventoryManagementSystem.Entity.ManufactureRecord;
import com.atilaBiosystems.InventoryManagementSystem.Entity.ProductRecord;
import com.atilaBiosystems.InventoryManagementSystem.Entity.RawMaterial;
import com.atilaBiosystems.InventoryManagementSystem.Repository.ComponentRecordRepository;
import com.atilaBiosystems.InventoryManagementSystem.Repository.ProductRecordRepository;
import com.atilaBiosystems.InventoryManagementSystem.Repository.RawMaterialsRepository;
import com.atilaBiosystems.InventoryManagementSystem.Repository.RequestRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellServiceImpl implements SellService{
    private RawMaterialsRepository rawMaterialsRepository;
    private ProductRecordRepository productRecordRepository;
    private ComponentRecordRepository componentRecordRepository;

    public SellServiceImpl(RawMaterialsRepository rawMaterialsRepository,
                           ProductRecordRepository productRecordRepository,
                           ComponentRecordRepository componentRecordRepository) {
        this.rawMaterialsRepository = rawMaterialsRepository;
        this.productRecordRepository = productRecordRepository;
        this.componentRecordRepository = componentRecordRepository;
    }


    @Override
    @Transactional
    public void sellCurrentInvoice(List<SellItemDAO> itemList) {
        for (SellItemDAO item: itemList){
            if (item.getCategory().equals("product")) {
                ProductRecord productRecord = productRecordRepository.findById(item.getRecordId()).orElse(null);
                productRecord.setAmountInStock(item.getCurrentStock() - item.getSellQty());
            } else if (item.getCategory().equals("component")) {
                ComponentRecord componentRecord = componentRecordRepository.findById(item.getRecordId()).orElse(null);
                componentRecord.setAmountInStock(item.getCurrentStock() - item.getSellQty());
            } else {
                RawMaterial rawMaterial = rawMaterialsRepository.findById(item.getRecordId()).orElse(null);
                rawMaterial.setAmountInStock(item.getCurrentStock() - item.getSellQty());
            }
        }
    }
}
