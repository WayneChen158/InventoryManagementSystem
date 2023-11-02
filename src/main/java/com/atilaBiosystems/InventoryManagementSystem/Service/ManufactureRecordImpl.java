package com.atilaBiosystems.InventoryManagementSystem.Service;

import com.atilaBiosystems.InventoryManagementSystem.Entity.ManufactureRecord;
import com.atilaBiosystems.InventoryManagementSystem.Repository.ManufactureRecordDetailRepository;
import com.atilaBiosystems.InventoryManagementSystem.Repository.ManufactureRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManufactureRecordImpl implements ManufactureRecordService{

    private final ManufactureRecordRepository manufactureRecordRepository;
    private final ManufactureRecordDetailRepository manufactureRecordDetailRepository;

    public ManufactureRecordImpl(ManufactureRecordRepository manufactureRecordRepository, ManufactureRecordDetailRepository manufactureRecordDetailRepository) {
        this.manufactureRecordRepository = manufactureRecordRepository;
        this.manufactureRecordDetailRepository = manufactureRecordDetailRepository;
    }


    @Override
    public List<ManufactureRecord> findAll() {
        return manufactureRecordRepository.findAll();
    }
}
