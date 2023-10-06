package com.atilaBiosystems.InventoryManagementSystem.repository;

import com.atilaBiosystems.InventoryManagementSystem.entity.RawMaterials;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public List<RawMaterials> findAll() {
        TypedQuery<RawMaterials> theQuery = this.entityManager.createQuery("FROM RawMaterials", RawMaterials.class);
        List<RawMaterials> rawMaterialsList = theQuery.getResultList();
        return rawMaterialsList;
    }

    @Override
    public RawMaterials findById(int id) {
        return entityManager.find(RawMaterials.class, id);
    }

    @Override
    public RawMaterials save(RawMaterials theRawMaterial) {
        RawMaterials dbRawMaterial = entityManager.merge(theRawMaterial);
        return dbRawMaterial;
    }

    @Override
    public void deleteById(int id) {
        RawMaterials theRawMaterial = entityManager.find(RawMaterials.class, id);
        entityManager.remove(theRawMaterial);
    }
}
