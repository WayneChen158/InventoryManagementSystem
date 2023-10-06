package com.atilaBiosystems.InventoryManagementSystem.service;

import com.atilaBiosystems.InventoryManagementSystem.entity.RawMaterials;
import com.atilaBiosystems.InventoryManagementSystem.repository.RawMaterialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RawMaterialsServiceImpl implements RawMaterialsService{
    private RawMaterialsRepository rawMaterialsRepository;

    @Autowired
    public RawMaterialsServiceImpl(RawMaterialsRepository rawMaterialsRepository) {
        this.rawMaterialsRepository = rawMaterialsRepository;
    }
    @Override
    public List<RawMaterials> findAll() {
        return this.rawMaterialsRepository.findAll();
    }

    @Override
    public RawMaterials findById(int id) {
        Optional<RawMaterials> result = this.rawMaterialsRepository.findById(id);
        RawMaterials theRawMaterial = null;
        if(result.isPresent()){
            theRawMaterial = result.get();
        } else {
            throw new RuntimeException("Cannot find the material id - " + id);
        }
        return theRawMaterial;
    }

    @Override
    public RawMaterials save(RawMaterials theRawMaterial) {
        return this.rawMaterialsRepository.save(theRawMaterial);
    }

    @Override
    public void deleteById(int id) {
        this.rawMaterialsRepository.deleteById(id);
    }
}
