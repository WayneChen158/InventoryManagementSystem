package com.atilaBiosystems.InventoryManagementSystem.Repository;

import com.atilaBiosystems.InventoryManagementSystem.Entity.ManufactureRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufactureRecordRepository extends JpaRepository<ManufactureRecord, Integer> {
}
