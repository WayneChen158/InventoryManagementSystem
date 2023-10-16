package com.atilaBiosystems.InventoryManagementSystem.Controller;

import com.atilaBiosystems.InventoryManagementSystem.Service.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/components")
public class ComponentController {

    private final ComponentService componentService;

    @Autowired
    public ComponentController(ComponentService componentService) {
        this.componentService = componentService;
    }

    // Additional custom endpoints, if needed
    // For example, you can define custom search or business logic endpoints here.
}
