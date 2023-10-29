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

}
