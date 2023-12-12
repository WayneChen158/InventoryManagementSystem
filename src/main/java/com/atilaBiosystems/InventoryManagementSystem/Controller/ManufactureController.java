package com.atilaBiosystems.InventoryManagementSystem.Controller;

import com.atilaBiosystems.InventoryManagementSystem.Entity.ManufactureRecord;
import com.atilaBiosystems.InventoryManagementSystem.Entity.RawMaterial;
import com.atilaBiosystems.InventoryManagementSystem.Service.ManufactureRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/manufacture")
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

    @PutMapping("/cancel/{manufactureRecordId}")
    public ResponseEntity<?> cancelManufacture(
            @PathVariable int manufactureRecordId){
        manufactureRecordService.cancelManufacture(manufactureRecordId);
        return ResponseEntity.ok().body("Manufacture canceled successfully");
    }

    @PutMapping("/{manufactureRecordId}")
    public ResponseEntity<?> finishManufacture(
            @PathVariable int manufactureRecordId,
            @RequestParam(value = "updateScale", required = false) Integer updateScale,
            @RequestParam(value = "updateLotNumber", required = false) String updateLotNumber){
        manufactureRecordService.finishManufacture(manufactureRecordId, updateScale, updateLotNumber);
        return ResponseEntity.ok().body("Manufacture finished successfully");
    }
}
