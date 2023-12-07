package com.atilaBiosystems.InventoryManagementSystem.Controller;

import com.atilaBiosystems.InventoryManagementSystem.DAO.RawMaterialDAO;
import com.atilaBiosystems.InventoryManagementSystem.DAO.UpdateRawMaterialForm;
import com.atilaBiosystems.InventoryManagementSystem.Entity.RawMaterial;
import com.atilaBiosystems.InventoryManagementSystem.Service.RawMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RawMaterialController {

    private final RawMaterialService rawMaterialService;

    @Autowired
    public RawMaterialController(RawMaterialService rawMaterialService) {
        this.rawMaterialService = rawMaterialService;
    }

    private Date parseDateString(String dateString) {
        String datePattern = "MM/dd/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
        Date date = null;
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Date parsing failed");
            System.out.println(dateString);
            e.printStackTrace();;
        }
        return date;
    }

    @GetMapping("/rawMaterials")
    public List<RawMaterial> getRawMaterials(){
        return rawMaterialService.findAll();
    }

    @GetMapping("/rawMaterials/{rawMaterialId}")
    public RawMaterial getRawMaterialById(@PathVariable int rawMaterialId) {
        return this.rawMaterialService.findById(rawMaterialId);
    }

    @GetMapping("/consumables")
    public List<RawMaterial> getConsumables(){
        return rawMaterialService.findByCategory(1);
    }

    // Additional custom endpoints, if needed
    // For example, you can define custom search or business logic endpoints here.
    @GetMapping("/filter")
    public List<RawMaterial> filterRawMaterials(
            @RequestParam(value = "search", required = false) String searchKeyword,
            @RequestParam(value = "manufacturer", required = false) String manufacturer,
            @RequestParam(value = "groupName", required = false) Integer groupName) {
        // Implement filtering and searching logic in the service
        return rawMaterialService.filterRawMaterials(searchKeyword, manufacturer, groupName);
    }

    // Add a new RawMaterial entry to the database
    @PostMapping("/rawMaterials/add")
    public RawMaterial addNewMaterial(@RequestBody RawMaterialDAO rawMaterialDAO){
        RawMaterial rawMaterial = new RawMaterial();
        rawMaterial.setGroupName(rawMaterialDAO.getRawMaterialType());
        rawMaterial.setCatalogNumber(rawMaterialDAO.getCatlogNumber());
        rawMaterial.setDescription(rawMaterialDAO.getItemName());
        rawMaterial.setManufacturer(rawMaterialDAO.getVendor());
        rawMaterial.setConcentration(null);
        rawMaterial.setReceiveDate(null);
        rawMaterial.setThreshold(rawMaterialDAO.getAlertAmount());
        rawMaterial.setAmountInStock(rawMaterialDAO.getAmount());
        rawMaterial.setOwner(rawMaterialDAO.getOwner());
        rawMaterial.setLocation(rawMaterialDAO.getLocation());
        rawMaterial.setWebsite(rawMaterialDAO.getWebsite());
        return this.rawMaterialService.createRawMaterial(rawMaterial);
    }

    @DeleteMapping("/rawMaterials/delete/{rawMaterialId}")
    public ResponseEntity<String> deleteRawMaterialById(@PathVariable int rawMaterialId) {
        boolean success = this.rawMaterialService.deleteRawMaterialById(rawMaterialId);
        if (success) {
            return ResponseEntity.ok().body(String.format("Raw material ID %d has been successfully deleted", rawMaterialId));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Failed to delete raw material ID %d...", rawMaterialId));
        }
    }

    @PatchMapping("/rawMaterials/update")
    public ResponseEntity<String> updateRawMaterialById(@RequestBody UpdateRawMaterialForm form) {
        int materialId = form.getMaterialId();

        RawMaterial material = this.rawMaterialService.findById(materialId);
        if (material == null) {
            String responseString = String.format("Failed to update raw material ID %d...", materialId);
            responseString += "Provided ID is not valid.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseString);
        }

        if (form.getCategory().equals(1) || form.getCategory().equals(2)) {
            material.setCategory(form.getCategory());
        } else {
            material.setCategory(null);
        }

        material.setGroupName(form.getGroupName());
        material.setCatalogNumber(form.getCatalogNumber());
        material.setDescription(form.getDescription());
        material.setManufacturer(form.getManufacturer());

        try {
            material.setConcentration(Double.valueOf(form.getConcentration()));
        } catch (NumberFormatException e) {
            material.setConcentration(null);
        }

        if (material.getLocation() == null && form.getLocation().equals("")) {
            material.setLocation(null);
        } else {
            material.setLocation(form.getLocation());
        }

        if (material.getOwner() == null && form.getOwner().equals("")) {
            material.setOwner(null);
        } else {
            material.setOwner(form.getOwner());
        }

        if (material.getWebsite() == null && form.getWebsite().equals("")) {
            material.setWebsite(null);
        } else {
            material.setWebsite(form.getWebsite());
        }
        
        material.setThreshold(form.getThreshold());
        material.setAmountInStock(form.getAmountInStock());

        Date receiveDate = this.parseDateString(form.getReceiveDate());
        if (receiveDate != null) {
            material.setReceiveDate(receiveDate);
        }

        RawMaterial updatedMaterial = this.rawMaterialService.updateRawMaterial(material);
        if (updatedMaterial != null) {
            return ResponseEntity.ok().body(String.format("Successfully updated raw material %d", materialId));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Failed to update raw material %d...", materialId));
        }
    }

    // GET /api/rawMaterials : get all the RawMaterials
    // PUT /api/rawMaterials/{materialId} Body should include all the information, frontend should keep this in mind
}

