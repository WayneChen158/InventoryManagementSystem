package com.atilaBiosystems.InventoryManagementSystem.Service;

import com.atilaBiosystems.InventoryManagementSystem.Entity.ComponentRecord;
import com.atilaBiosystems.InventoryManagementSystem.Entity.ManufactureRecord;
import com.atilaBiosystems.InventoryManagementSystem.Entity.ManufactureRecordDetail;
import com.atilaBiosystems.InventoryManagementSystem.Entity.RawMaterial;
import com.atilaBiosystems.InventoryManagementSystem.Repository.ManufactureRecordDetailRepository;
import com.atilaBiosystems.InventoryManagementSystem.Repository.ManufactureRecordRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public List<ManufactureRecord> findByStatus(Integer status) {
        return manufactureRecordRepository.findByStatus(status);
    }

    @Override
    @Transactional
    public void finishManufacture(Integer manufactureRecordId, Integer updateScale, String updateLotNumber) {
        ManufactureRecord manufactureRecord = manufactureRecordRepository.findById(manufactureRecordId).orElse(null);

        if (manufactureRecord != null) {
            for (ManufactureRecordDetail detail : manufactureRecord.getRecordDetails()) {
                if (detail.getComponentRecord() != null) {
                    int beforeManufacture = detail.getComponentRecord().getAmountInStock();
                    detail.getComponentRecord().setAmountInStock((int)(beforeManufacture - detail.getTotalVol()));
                    continue;
                }
                if (detail.getRawMaterial() != null) {
                    RawMaterial currRawMateiral = detail.getRawMaterial();
                    int beforeManufacture = currRawMateiral.getAmountInStock();
                    currRawMateiral.setAmountInStock((int) (beforeManufacture - detail.getTotalVol()));
                }
            }
            manufactureRecord.setStatus(2);
            if (manufactureRecord.getComponentRecord() != null){
                manufactureRecord.getComponentRecord().setAmountInStock(updateScale);
                manufactureRecord.getComponentRecord().setLotNumber(updateLotNumber);
            } else {
                manufactureRecord.getProductRecord().setAmountInStock(updateScale);
                manufactureRecord.getProductRecord().setLotNumber(updateLotNumber);
            }
        } else {
            throw new EntityNotFoundException("ManufactureRecord not found");
        }
    }

    @Override
    @Transactional
    public void cancelManufacture(Integer manufactureRecordId) {
        ManufactureRecord manufactureRecord = manufactureRecordRepository.findById(manufactureRecordId).orElse(null);

        if (manufactureRecord != null) {
            manufactureRecord.setStatus(3);
        } else {
            throw new EntityNotFoundException("ManufactureRecord not found");
        }
    }
}
