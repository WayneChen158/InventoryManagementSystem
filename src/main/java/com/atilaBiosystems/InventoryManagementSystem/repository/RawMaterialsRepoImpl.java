package com.atilaBiosystems.InventoryManagementSystem.repository;

import com.atilaBiosystems.InventoryManagementSystem.entity.RawMaterials;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RawMaterialsRepoImpl implements RawMaterialsRepository{

    // Define field for EntityManager
    private EntityManager entityManager;

    // Inject EntityManager using constructor injection
    @Autowired
    public RawMaterialsRepoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(RawMaterials theRawMaterial) {
        entityManager.persist(theRawMaterial);
    }

    @Override
    public RawMaterials findById(Integer id) {
        return entityManager.find(RawMaterials.class, id);
    }
}
