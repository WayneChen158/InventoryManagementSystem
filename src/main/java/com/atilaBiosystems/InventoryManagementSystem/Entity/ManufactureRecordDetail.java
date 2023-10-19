package com.atilaBiosystems.InventoryManagementSystem.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "manufacture_record_details")
public class ManufactureRecordDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_detail_id")
    private Integer recordDetailId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "amount_per_rxn")
    private Double amountPerRxn;

    @Column(name = "total_vol")
    private Double totalVol;

    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="manufacture_record_id")
    private ManufactureRecord manufactureRecord;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="component_record_id")
    private ComponentRecord componentRecord;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="raw_material_id")
    private RawMaterial rawMaterial;

    public ManufactureRecordDetail() {}

    public ManufactureRecordDetail(String itemName, Double amountPerRxn, Double totalVol, ManufactureRecord manufactureRecord) {
        this.itemName = itemName;
        this.amountPerRxn = amountPerRxn;
        this.totalVol = totalVol;
        this.manufactureRecord = manufactureRecord;
    }

    public Integer getRecordDetailId() {
        return recordDetailId;
    }

    public void setRecordDetailId(Integer recordDetailId) {
        this.recordDetailId = recordDetailId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getAmountPerRxn() {
        return amountPerRxn;
    }

    public void setAmountPerRxn(Double amountPerRxn) {
        this.amountPerRxn = amountPerRxn;
    }

    public Double getTotalVol() {
        return totalVol;
    }

    public void setTotalVol(Double totalVol) {
        this.totalVol = totalVol;
    }

    public ManufactureRecord getManufactureRecord() {
        return manufactureRecord;
    }

    public void setManufactureRecord(ManufactureRecord manufactureRecord) {
        this.manufactureRecord = manufactureRecord;
    }

    public ComponentRecord getComponentRecord() {
        return componentRecord;
    }

    public void setComponentRecord(ComponentRecord componentRecord) {
        this.componentRecord = componentRecord;
    }

    public RawMaterial getRawMaterial() {
        return rawMaterial;
    }

    public void setRawMaterial(RawMaterial rawMaterial) {
        this.rawMaterial = rawMaterial;
    }

    @Override
    public String toString() {
        return "ManufactureRecordDetail{" +
                "itemName='" + itemName + '\'' +
                ", amountPerRxn=" + amountPerRxn +
                ", totalVol=" + totalVol +
                '}';
    }
}
