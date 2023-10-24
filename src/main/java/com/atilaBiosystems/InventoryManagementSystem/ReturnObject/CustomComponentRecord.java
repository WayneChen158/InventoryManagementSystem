package com.atilaBiosystems.InventoryManagementSystem.ReturnObject;

import com.atilaBiosystems.InventoryManagementSystem.Entity.ComponentRecord;

import java.util.Date;

public class CustomComponentRecord {

    public Integer componentRecordId;
    public Integer componentId;
    public String componentName;
    public String lotNumber;
    public Date manufactureDate;
    public int amountInStock;

    public CustomComponentRecord(ComponentRecord componentRecord) {
        this.componentRecordId = componentRecord.getComponentRecordId();
        this.componentId = componentRecord.getComponent().getComponentId();
        this.componentName = componentRecord.getComponentName();
        this.lotNumber = componentRecord.getLotNumber();
        this.manufactureDate = componentRecord.getManufactureDate();
        this.amountInStock = componentRecord.getAmountInStock();
    }
}
