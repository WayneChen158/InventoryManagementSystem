package com.atilaBiosystems.InventoryManagementSystem.Repository;

import com.atilaBiosystems.InventoryManagementSystem.Entity.Component;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComponentRepository extends JpaRepository<Component, Integer> {
}
