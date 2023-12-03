package com.atilaBiosystems.InventoryManagementSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atilaBiosystems.InventoryManagementSystem.Entity.ProductRecord;
import com.atilaBiosystems.InventoryManagementSystem.Service.ProductRecordService;

@RestController
@RequestMapping("api/productRecords")
public class ProductRecordController {
    
    private final ProductRecordService productRecordService;

    @Autowired
    public ProductRecordController(ProductRecordService productRecordService) {
        this.productRecordService = productRecordService;
    }

    @GetMapping()
    public List<ProductRecord> findAll() {
        return this.productRecordService.findAll();
    }
}
