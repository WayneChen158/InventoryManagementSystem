package com.atilaBiosystems.InventoryManagementSystem.Repository;

import com.atilaBiosystems.InventoryManagementSystem.Entity.ProductRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRecordRepository extends JpaRepository<ProductRecord, Integer> {

    List<ProductRecord> findByAmountInStockGreaterThan(Integer amount);
}
