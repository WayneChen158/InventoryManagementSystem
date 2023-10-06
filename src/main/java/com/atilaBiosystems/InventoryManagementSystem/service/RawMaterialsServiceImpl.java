package com.atilaBiosystems.InventoryManagementSystem.service;

import com.atilaBiosystems.InventoryManagementSystem.entity.RawMaterials;
import com.atilaBiosystems.InventoryManagementSystem.repository.RawMaterialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
        return this.rawMaterialsRepository.findById(id);
    }

    @Override
    @Transactional
    public RawMaterials save(RawMaterials theRawMaterial) {
        return this.rawMaterialsRepository.save(theRawMaterial);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        this.rawMaterialsRepository.deleteById(id);
    }
}
