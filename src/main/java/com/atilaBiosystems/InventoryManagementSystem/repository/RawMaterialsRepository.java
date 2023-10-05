package com.atilaBiosystems.InventoryManagementSystem.repository;

import com.atilaBiosystems.InventoryManagementSystem.entity.RawMaterials;

public interface RawMaterialsRepository {

    void save(RawMaterials theRawMaterial);

    RawMaterials findById(Integer id);
}
