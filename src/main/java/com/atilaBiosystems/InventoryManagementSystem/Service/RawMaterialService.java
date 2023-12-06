package com.atilaBiosystems.InventoryManagementSystem.Service;

import com.atilaBiosystems.InventoryManagementSystem.Entity.RawMaterial;

import java.util.List;

public interface RawMaterialService {

    // Additional custom service methods, if needed
    // To Define methods that go beyond basic CRUD operations here.
    List<RawMaterial> filterRawMaterials(
        String searchKeyword, String manufacturer, Integer groupName);

    RawMaterial createRawMaterial(RawMaterial rawMaterial);

    //    public RawMaterial updateRawMaterialById(Integer materialId, RawMaterial updatedRawMaterial);

    List<RawMaterial> findAll();

    List<RawMaterial> findByCategory(Integer category);

    // When adding a new request, check whether the requested item
    // is already in raw_materials table by input catalog number
    // When more than 1 items are returned with the same catalog number,
    // try to match with the item description
    RawMaterial findByCatalogNumber(String catalogNumber, String itemDescription);

    RawMaterial findById(int rawMaterialId);

    RawMaterial updateRawMaterial(RawMaterial rawMaterial);
}
