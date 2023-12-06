package com.atilaBiosystems.InventoryManagementSystem.Service;

import com.atilaBiosystems.InventoryManagementSystem.Entity.RawMaterial;
import com.atilaBiosystems.InventoryManagementSystem.Repository.RawMaterialsRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RawMaterialServiceImpl implements RawMaterialService{
    private RawMaterialsRepository rawMaterialsRepository;

    public RawMaterialServiceImpl(RawMaterialsRepository rawMaterialsRepository) {
        this.rawMaterialsRepository = rawMaterialsRepository;
    }

    public List<RawMaterial> filterRawMaterials(
            String searchKeyword, String manufacturer, Integer groupName) {
        // Implement dynamic query building based on filter criteria and search keyword
        // Use Spring Data JPA Specifications or Criteria API here
        return rawMaterialsRepository.findFilteredRawMaterials(searchKeyword, manufacturer, groupName);
    }

    @Override
    public List<RawMaterial> findAll() {
        return rawMaterialsRepository.findAll();
    }

    @Override
    public List<RawMaterial> findByCategory(Integer category) {
        return rawMaterialsRepository.findByCategory(1);
    }

    @Override
    public RawMaterial createRawMaterial(RawMaterial rawMaterial) {
        return this.rawMaterialsRepository.save(rawMaterial);
    }

    @Override
    public RawMaterial findByCatalogNumber(String catalogNumber, String itemDescription) {
        System.out.println(catalogNumber);
        List<RawMaterial> candidates = this.rawMaterialsRepository.findByCatalogNumber(catalogNumber);
        if (candidates != null && candidates.size() == 1) {
            System.out.println("Existing raw material found!");
            System.out.println(candidates.get(0).getDescription());
            System.out.println(candidates.get(0).getMaterialId());
            return candidates.get(0);
        } else if (candidates != null && candidates.size() > 1) {
            System.out.println("More than 1 items found");
            for (RawMaterial candidate : candidates) {
                if (candidate.getDescription().equals(itemDescription)) {
                    System.out.println(candidate.getDescription());
                    System.out.println(candidate.getMaterialId());
                    return candidate;
                }
            }
            return null;
        } else {
            System.out.println("No item found");
            return null;
        }
    }

    @Override
    public RawMaterial findById(int rawMaterialId) {
        RawMaterial rawMaterial = rawMaterialsRepository.findById(rawMaterialId).orElse(null);
        return rawMaterial;
    }

    @Override
    public RawMaterial updateRawMaterial(RawMaterial rawMaterial) {
        this.rawMaterialsRepository.save(rawMaterial);
        return this.rawMaterialsRepository.findById(rawMaterial.getMaterialId()).orElse(null);
    }

    @Override
    public boolean deleteRawMaterialById(int rawMaterialId) {
        if (this.findById(rawMaterialId) != null) {
            this.rawMaterialsRepository.deleteById(rawMaterialId);
            return true;
        }
        return false;
    }
}
