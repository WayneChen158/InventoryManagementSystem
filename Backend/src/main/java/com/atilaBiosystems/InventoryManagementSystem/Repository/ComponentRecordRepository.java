package com.atilaBiosystems.InventoryManagementSystem.Repository;

import com.atilaBiosystems.InventoryManagementSystem.Entity.ComponentRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComponentRecordRepository extends JpaRepository<ComponentRecord, Integer> {
}
