package com.atilaBiosystems.InventoryManagementSystem.Service;

import com.atilaBiosystems.InventoryManagementSystem.Entity.RawMaterial;

import java.util.List;

public interface RawMaterialService {

    // Additional custom service methods, if needed
    // To Define methods that go beyond basic CRUD operations here.
    public List<RawMaterial> filterRawMaterials(
            String searchKeyword, String manufacturer, Integer groupName);

    public RawMaterial createRawMaterial(RawMaterial rawMaterial);

    //    public RawMaterial updateRawMaterialById(Integer materialId, RawMaterial updatedRawMaterial);

    List<RawMaterial> findAll();

    List<RawMaterial> findByCategory(Integer category);
}
