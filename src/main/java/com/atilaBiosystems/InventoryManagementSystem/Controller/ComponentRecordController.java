package com.atilaBiosystems.InventoryManagementSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atilaBiosystems.InventoryManagementSystem.Entity.ComponentRecord;
import com.atilaBiosystems.InventoryManagementSystem.Service.ComponentRecordService;

@RestController
@RequestMapping("/api/componentRecords")
public class ComponentRecordController {
    
    private final ComponentRecordService componentRecordService;

    @Autowired
    public ComponentRecordController(ComponentRecordService componentRecordService) {
        this.componentRecordService = componentRecordService;
    }

    @GetMapping()
    public List<ComponentRecord> findAll() {
        return this.componentRecordService.findAll();
    }
}
