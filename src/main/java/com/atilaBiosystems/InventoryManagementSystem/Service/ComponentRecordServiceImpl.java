package com.atilaBiosystems.InventoryManagementSystem.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.atilaBiosystems.InventoryManagementSystem.Entity.ComponentRecord;
import com.atilaBiosystems.InventoryManagementSystem.Repository.ComponentRecordRepository;

@Service
public class ComponentRecordServiceImpl implements ComponentRecordService {
    
    private final ComponentRecordRepository componentRecordRepository;

    public ComponentRecordServiceImpl(ComponentRecordRepository componentRecordRepository) {
        this.componentRecordRepository = componentRecordRepository;
    }

    @Override
    public List<ComponentRecord> findAll() {
        return this.componentRecordRepository.findAll();
    }
}
