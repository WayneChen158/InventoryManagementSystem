package com.atilaBiosystems.InventoryManagementSystem.Controller;

import com.atilaBiosystems.InventoryManagementSystem.Entity.RawMaterial;
import com.atilaBiosystems.InventoryManagementSystem.Service.RawMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RawMaterialController {

    private final RawMaterialService rawMaterialService;

    @Autowired
    public RawMaterialController(RawMaterialService rawMaterialService) {
        this.rawMaterialService = rawMaterialService;
    }

    @GetMapping("/rawMaterials")
    public List<RawMaterial> getRawMaterials(){
        return rawMaterialService.findAll();
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

    // GET /api/rawMaterials : get all the RawMaterials
    // PUT /api/rawMaterials/{materialId} Body should include all the information, frontend should keep this in mind
}

