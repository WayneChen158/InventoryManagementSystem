package com.atilaBiosystems.InventoryManagementSystem.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.atilaBiosystems.InventoryManagementSystem.Entity.ProductRecord;
import com.atilaBiosystems.InventoryManagementSystem.Repository.ProductRecordRepository;

@Service
public class ProductRecordServiceImpl implements ProductRecordService {
    
    private final ProductRecordRepository productRecordRepository;

    public ProductRecordServiceImpl(ProductRecordRepository productRecordRepository) {
        this.productRecordRepository = productRecordRepository;
    }

    @Override
    public List<ProductRecord> findAll() {
        return this.productRecordRepository.findAll();
    }
}
