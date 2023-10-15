package com.atilaBiosystems.InventoryManagementSystem.entity;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name="intermediate_component_records")
public class IntermediateComponentRecord {

    /**
     * CREATE TABLE intermediate_component_records (
     * 	i_component_record_id INT NOT NULL AUTO_INCREMENT,
     *     i_component_name VARCHAR(100),
     *     i_component_id INT,
     *     lot_number VARCHAR(50),
     *     manufacture_date DATE,
     *     amount_in_stock INT,
     *     PRIMARY KEY (i_component_record_id),
     *     FOREIGN KEY (i_component_id) REFERENCES intermediate_components (i_component_id)
     * );
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="i_component_record_id")
    private int interComponentRecordId;

    @Column(name="i_component_name")
    private String interComponentName;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="i_component_id")
    private IntermediateComponent intermediateComponent;

    @Column(name="lot_number")
    private String lotNumber;

    @Column(name="manufacture_date")
    private Date manufactureDate;

    @Column(name="amount_in_stock")
    private int amountInStock;

    public IntermediateComponentRecord() {}

    public IntermediateComponentRecord(String interComponentName, String lotNumber, Date manufactureDate, int amountInStock) {
        this.interComponentName = interComponentName;
        this.lotNumber = lotNumber;
        this.manufactureDate = manufactureDate;
        this.amountInStock = amountInStock;
    }

    public int getInterComponentRecordId() {
        return interComponentRecordId;
    }

    public void setInterComponentRecordId(int interComponentRecordId) {
        this.interComponentRecordId = interComponentRecordId;
    }

    public String getInterComponentName() {
        return interComponentName;
    }

    public void setInterComponentName(String interComponentName) {
        this.interComponentName = interComponentName;
    }

    public IntermediateComponent getIntermediateComponent() {
        return intermediateComponent;
    }

    public void setIntermediateComponent(IntermediateComponent intermediateComponent) {
        this.intermediateComponent = intermediateComponent;
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

    @Override
    public String toString() {
        return "IntermediateComponentRecord{" +
                "interComponentName='" + interComponentName + '\'' +
                ", lotNumber='" + lotNumber + '\'' +
                ", manufactureDate=" + manufactureDate +
                ", amountInStock=" + amountInStock +
                '}';
    }
}
