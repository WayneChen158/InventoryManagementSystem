package com.atilaBiosystems.InventoryManagementSystem.Controller;

import com.atilaBiosystems.InventoryManagementSystem.Entity.ManufactureRecord;
import com.atilaBiosystems.InventoryManagementSystem.ReturnObject.CustomComponentRecord;
import com.atilaBiosystems.InventoryManagementSystem.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/manufacture/{productId}")
    public List<CustomComponentRecord> checkComponentInventory(
            @PathVariable int productId){
        return productService.checkComponentInventory(productId);
    }

    @PostMapping("/manufacture/{productId}")
    public ManufactureRecord putInManufactureLine(
            @PathVariable int productId,
            @RequestParam(value = "scale", required = false) Integer scale){

        return productService.putInManufactureLine(productId, scale);
    }

    @PutMapping("/manufacture/{manufactureRecordId}")
    public void finishManufacture(
            @PathVariable int manufactureRecordId,
            @RequestParam(value = "scale", required = false) Integer scale){
        productService.checkComponentinventoryWithScale(manufactureRecordId, scale);
        productService.finishManufacture(manufactureRecordId, scale);

    }


}
