package com.atilaBiosystems.InventoryManagementSystem.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="component_records")
public class ComponentRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="component_record_id")
    private Integer ComponentRecordId;

    @Column(name="component_catalog")
    private String componentCatalog;

    @Column(name="component_name")
    private String componentName;

    @JsonBackReference // Indicates that Component is not serialized as part of ComponentRecord
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="component_id")
    private Component component;

    @Column(name="lot_number")
    private String lotNumber;

    @Column(name="manufacture_date")
    private Date manufactureDate;

    @Column(name="amount_in_stock")
    private int amountInStock;
    @Column(name="unit")
    private String unit;

    public ComponentRecord() {}

    public ComponentRecord(String componentCatalog,
                           String componentName,
                           String lotNumber,
                           Date manufactureDate,
                           int amountInStock,
                           String unit) {
        this.componentCatalog = componentCatalog;
        this.componentName = componentName;
        this.lotNumber = lotNumber;
        this.manufactureDate = manufactureDate;
        this.amountInStock = amountInStock;
        this.unit = unit;
    }

    public Integer getComponentRecordId() {
        return ComponentRecordId;
    }

    public void setComponentRecordId(Integer componentRecordId) {
        ComponentRecordId = componentRecordId;
    }

    public String getComponentCatalog() {
        return componentCatalog;
    }

    public void setComponentCatalog(String componentCatalog) {
        this.componentCatalog = componentCatalog;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public Date getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(Date manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public int getAmountInStock() {
        return amountInStock;
    }

    public void setAmountInStock(int amountInStock) {
        this.amountInStock = amountInStock;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "ComponentRecord{" +
                "componentName='" + componentName + '\'' +
                ", lotNumber='" + lotNumber + '\'' +
                ", manufactureDate=" + manufactureDate +
                ", amountInStock=" + amountInStock +
                '}';
    }
}
