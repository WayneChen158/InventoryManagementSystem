package com.atilaBiosystems.InventoryManagementSystem.Repository;


import com.atilaBiosystems.InventoryManagementSystem.Entity.InvoiceContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceContentRepository extends JpaRepository<InvoiceContent, Integer> {
}
