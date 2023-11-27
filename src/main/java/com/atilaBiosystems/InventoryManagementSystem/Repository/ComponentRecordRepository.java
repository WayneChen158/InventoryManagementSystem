package com.atilaBiosystems.InventoryManagementSystem.Repository;

import com.atilaBiosystems.InventoryManagementSystem.Entity.ComponentRecord;
import com.atilaBiosystems.InventoryManagementSystem.Entity.ProductRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComponentRecordRepository extends JpaRepository<ComponentRecord, Integer> {

    List<ComponentRecord> findByAmountInStockGreaterThan(Integer amount);
}
