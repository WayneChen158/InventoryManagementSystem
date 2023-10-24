package com.atilaBiosystems.InventoryManagementSystem.Repository;

import com.atilaBiosystems.InventoryManagementSystem.Entity.ProductRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRecordRepository extends JpaRepository<ProductRecord, Integer> {
}
