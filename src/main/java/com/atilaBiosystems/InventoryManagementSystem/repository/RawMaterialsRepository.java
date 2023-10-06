package com.atilaBiosystems.InventoryManagementSystem.repository;

import com.atilaBiosystems.InventoryManagementSystem.entity.RawMaterials;

import java.util.List;

public interface RawMaterialsRepository {

    List<RawMaterials> findAll();
    RawMaterials findById(int id);
    RawMaterials save(RawMaterials theRawMaterial);
    void deleteById(int id);
}
