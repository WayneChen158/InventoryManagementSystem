package com.atilaBiosystems.InventoryManagementSystem.Controller;

import com.atilaBiosystems.InventoryManagementSystem.Entity.Component;
import com.atilaBiosystems.InventoryManagementSystem.Entity.ManufactureRecord;
import com.atilaBiosystems.InventoryManagementSystem.Entity.Product;
import com.atilaBiosystems.InventoryManagementSystem.Entity.ProductRecord;
import com.atilaBiosystems.InventoryManagementSystem.Exception.MissingComponentException;
import com.atilaBiosystems.InventoryManagementSystem.ReturnObject.CustomComponentRecord;
import com.atilaBiosystems.InventoryManagementSystem.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/inStock")
    public List<ProductRecord> getAllInStockProducts() {
        return productService.findByAmountInStockGreaterThan();
    }

    @GetMapping("/components/{productId}")
    public List<Component> getAllComponents(
            @PathVariable int productId) {
        Product product = productService.findById(productId);
        return product.getComponents();
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

    }

    @PutMapping("/updateStock/{productRecordId}")
    public void updateStock(
            @PathVariable int productRecordId,
            @RequestParam(value = "updateStock", required = false) Integer updateStock) {
        productService.updateStock(productRecordId, updateStock);
    }

    @ExceptionHandler(MissingComponentException.class)
    public ResponseEntity<String> handleNullComponentException(MissingComponentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
