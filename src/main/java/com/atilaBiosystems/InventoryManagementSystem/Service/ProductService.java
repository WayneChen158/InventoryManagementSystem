package com.atilaBiosystems.InventoryManagementSystem.Service;

import com.atilaBiosystems.InventoryManagementSystem.Entity.ComponentRecord;
import com.atilaBiosystems.InventoryManagementSystem.Entity.ManufactureRecord;
import com.atilaBiosystems.InventoryManagementSystem.Entity.Product;
import com.atilaBiosystems.InventoryManagementSystem.Entity.ProductRecord;
import com.atilaBiosystems.InventoryManagementSystem.ReturnObject.CustomComponentRecord;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Map;

public interface ProductService {

    List<Product> findAll();

    Product findById(int productId);

    List<CustomComponentRecord> checkComponentInventory(Integer productId);

    void checkComponentinventoryWithScale(Integer manufactureRecordID, Integer scale);

    //Only put manufacture record in the DB, manufacture record should be updated right before 'Done' manufacture
    ManufactureRecord putInManufactureLine(Integer productId, Integer scale);

    List<ProductRecord> findByAmountInStockGreaterThan();

    void updateStock(Integer productRecordId, Integer updateScale);

}
