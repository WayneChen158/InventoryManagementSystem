package com.atilaBiosystems.InventoryManagementSystem.Controller;

import com.atilaBiosystems.InventoryManagementSystem.Service.RawMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/raw-materials")
public class RawMaterialController {

    private final RawMaterialService rawMaterialService;

    @Autowired
    public RawMaterialController(RawMaterialService rawMaterialService) {
        this.rawMaterialService = rawMaterialService;
    }

    // Additional custom endpoints, if needed
    // For example, you can define custom search or business logic endpoints here.
}

