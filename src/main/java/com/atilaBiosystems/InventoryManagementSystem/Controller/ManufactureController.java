package com.atilaBiosystems.InventoryManagementSystem.Controller;

import com.atilaBiosystems.InventoryManagementSystem.Entity.ManufactureRecord;
import com.atilaBiosystems.InventoryManagementSystem.Service.ManufactureRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ManufactureController {
    private final ManufactureRecordService manufactureRecordService;
    @Autowired
    public ManufactureController(ManufactureRecordService manufactureRecordService) {
        this.manufactureRecordService = manufactureRecordService;
    }
    @GetMapping("/manufactureRecords")
    public List<ManufactureRecord> getManufactureRecord(
            @RequestParam(name = "status", required = true, defaultValue = "1") int status){
        return manufactureRecordService.findByStatus(status);
    }
}
