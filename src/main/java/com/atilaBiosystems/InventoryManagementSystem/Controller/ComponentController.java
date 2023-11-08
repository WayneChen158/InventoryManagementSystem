package com.atilaBiosystems.InventoryManagementSystem.Controller;

import com.atilaBiosystems.InventoryManagementSystem.Entity.Component;
import com.atilaBiosystems.InventoryManagementSystem.Entity.ManufactureRecord;
import com.atilaBiosystems.InventoryManagementSystem.Entity.RawMaterial;
import com.atilaBiosystems.InventoryManagementSystem.ReturnObject.CustomRecipeItem;
import com.atilaBiosystems.InventoryManagementSystem.Service.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @GetMapping()
    public List<Component> findAll(){
        return componentService.findAll();
    }

    @GetMapping("/manufacture/{componentId}")
    public List<CustomRecipeItem> getRecipeByComponentId(
            @PathVariable int componentId,
            @RequestParam(value = "scale", required = false) Integer scale){
        return componentService.getRecipeByComponentId(componentId, scale);
    }

    // Client click the button "manufacture this component" after confirming the recipe and pre-inventory check
    @PostMapping("/manufacture/{componentId}")
    public ManufactureRecord putInManufactureLine(
            @PathVariable int componentId,
            @RequestParam(value = "scale", required = false) Integer scale){
        return componentService.putInManufactureLine(componentId, scale);
    }

    // Update the recipe which is in the manufacture line
    @PutMapping("/manufacture/update_recipe/{manufactureRecordId}")
    public void updateManufactureRecipe(
            @PathVariable int manufactureRecordId,
            @RequestBody List<Map<String, Double>> updates){
        componentService.updateManufactureRecipe(manufactureRecordId, updates);;
    }

    // Done manufacture
    // (Check recipe, Update Manufacture record status, Update rawmaterial and product record inventory)
    @PutMapping("/manufacture/{manufactureRecordId}")
    public List<InventoryItem> finishManufacture(
            @PathVariable int manufactureRecordId,
            @RequestParam(value = "updateScale", required = false) Integer updateScale){
        List<InventoryItem> lst = new ArrayList<>();
        List<RawMaterial> rawMaterials = componentService.finishManufacture(manufactureRecordId, updateScale);
        for (RawMaterial rm: rawMaterials){
            InventoryItem currItem = new InventoryItem(rm.getMaterialId(), rm.getCatalogNumber(),
                    rm.getDescription(),rm.getThreshold(), rm.getAmountInStock(),
                    rm.getAmountInStock() > 1.1*rm.getThreshold());
            lst.add(currItem);
        }
        return lst;
    }

    class InventoryItem {
        public int itemId;
        public String catalogNum;
        public String description;
        public int threshold;
        public int amountInStock;
        public boolean hasEnoughInStock;

        public InventoryItem(int itemId, String catalogNum,
                             String description, int threshold,
                             int amountInStock, boolean hasEnoughInStock) {
            this.itemId = itemId;
            this.catalogNum = catalogNum;
            this.description = description;
            this.threshold = threshold;
            this.amountInStock = amountInStock;
            this.hasEnoughInStock = hasEnoughInStock;
        }
    }
}
