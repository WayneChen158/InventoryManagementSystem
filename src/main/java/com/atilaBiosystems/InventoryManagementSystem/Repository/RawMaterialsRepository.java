package com.atilaBiosystems.InventoryManagementSystem.Repository;

import com.atilaBiosystems.InventoryManagementSystem.Entity.RawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="raw-materials")
public interface RawMaterialsRepository extends JpaRepository<RawMaterial, Integer> {

}
