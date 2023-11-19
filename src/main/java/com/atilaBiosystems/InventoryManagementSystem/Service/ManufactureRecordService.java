package com.atilaBiosystems.InventoryManagementSystem.Service;

import com.atilaBiosystems.InventoryManagementSystem.Entity.ManufactureRecord;

import java.util.List;

public interface ManufactureRecordService {

    List<ManufactureRecord> findAll();

    List<ManufactureRecord> findByStatus(Integer status);

    void finishManufacture(Integer manufactureRecordId, Integer updateScale);

    void cancelManufacture(Integer manufactureRecordId);
}
