package com.atilaBiosystems.InventoryManagementSystem.repository;

import com.atilaBiosystems.InventoryManagementSystem.entity.RawMaterials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RawMaterialsRepository extends JpaRepository<RawMaterials, Integer> {

}
