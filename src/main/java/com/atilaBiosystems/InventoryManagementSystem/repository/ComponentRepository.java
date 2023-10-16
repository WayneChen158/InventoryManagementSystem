package com.atilaBiosystems.InventoryManagementSystem.repository;

import com.atilaBiosystems.InventoryManagementSystem.entity.Component;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComponentRepository extends JpaRepository<Component, Integer> {
}
