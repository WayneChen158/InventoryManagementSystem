package com.atilaBiosystems.InventoryManagementSystem.Service;

import java.util.List;

import com.atilaBiosystems.InventoryManagementSystem.Entity.ProductRecord;

public interface ProductRecordService {

    List<ProductRecord> findAll();
}