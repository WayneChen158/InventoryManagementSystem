package com.atilaBiosystems.InventoryManagementSystem.service;

import com.atilaBiosystems.InventoryManagementSystem.entity.RawMaterials;

import java.util.List;

public interface RawMaterialsService {

    List<RawMaterials> findAll();

    RawMaterials findById(int id);

    RawMaterials save(RawMaterials theRawMaterial);

    void deleteById(int id);
}
