package com.atilaBiosystems.InventoryManagementSystem.rest;

import com.atilaBiosystems.InventoryManagementSystem.entity.RawMaterials;
import com.atilaBiosystems.InventoryManagementSystem.service.RawMaterialsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RawMaterialsRestController {

    private RawMaterialsService rawMaterialsService;

    public RawMaterialsRestController(RawMaterialsService rawMaterialsService){
        this.rawMaterialsService = rawMaterialsService;
    }

    @GetMapping("/raw_materials")
    public List<RawMaterials> findAll() {
        return this.rawMaterialsService.findAll();
    }

    @GetMapping("/raw_materials/{rawMaterialId}")
    public RawMaterials getRawMaterial(@PathVariable int rawMaterialId) {
        RawMaterials theRawMaterial = this.rawMaterialsService.findById(rawMaterialId);

        if (theRawMaterial == null) {
            throw new RuntimeException("Raw Material id not found - " + rawMaterialId);
        }

        return theRawMaterial;
    }

    @PostMapping("/raw_materials")
    public RawMaterials addRawMaterial(@RequestBody RawMaterials theRawMaterial) {

        // Also just in case they pass an id in JSON ... set id to 0
        // This is to force save new items
        theRawMaterial.setMaterialId(0);

        RawMaterials dbRawMaterial = rawMaterialsService.save(theRawMaterial);

        return dbRawMaterial;
    }

    @PutMapping("/raw_materials")
    public RawMaterials updateRawMaterial(@RequestBody RawMaterials theRawMaterial) {
        RawMaterials dbRawMaterial = rawMaterialsService.save(theRawMaterial);
        return dbRawMaterial;
    }

    @DeleteMapping("/raw_materials/{rawMaterialId}")
    public String deleteRawMaterial(@PathVariable int rawMaterialId) {
        RawMaterials theRawMaterial = this.rawMaterialsService.findById(rawMaterialId);
        if (theRawMaterial == null) {
            throw new RuntimeException("Raw Material id not found - " + rawMaterialId);
        }
        this.rawMaterialsService.deleteById(rawMaterialId);
        return "Deleted raw material" + rawMaterialId;
    }
}
