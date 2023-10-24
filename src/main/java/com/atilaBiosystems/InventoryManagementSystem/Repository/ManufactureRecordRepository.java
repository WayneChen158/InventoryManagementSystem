package com.atilaBiosystems.InventoryManagementSystem.Repository;

import com.atilaBiosystems.InventoryManagementSystem.Entity.ManufactureRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManufactureRecordRepository extends JpaRepository<ManufactureRecord, Integer> {
    List<ManufactureRecord> findByStatus(Integer status);
}
