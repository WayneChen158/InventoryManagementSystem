package com.atilaBiosystems.InventoryManagementSystem.Entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "manufacture_records")
public class ManufactureRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manufacture_record_id")
    private Integer manufactureRecordId;

    @Column(name = "component_name")
    private String componentName;

    @Column(name = "manufacture_date")
    private Date manufactureDate;

    @Column(name = "manufacture_scale")
    private Integer scale;

    @Column(name = "owner")
    private String owner;

    @Column(name = "status")
    private Integer status; // {1: manufacturing, 2: done, 3: cancelled}

    @OneToMany(mappedBy = "manufactureRecord")
    private List<ManufactureRecordDetail> recordDetails;

    public ManufactureRecord() {}

    public ManufactureRecord(String componentName, Date manufactureDate, Integer scale, String owner, Integer status) {
        this.componentName = componentName;
        this.manufactureDate = manufactureDate;
        this.scale = scale;
        this.owner = owner;
        this.status = status;
    }

    public Integer getManufactureRecordId() {
        return manufactureRecordId;
    }

    public void setManufactureRecordId(Integer manufactureRecordId) {
        this.manufactureRecordId = manufactureRecordId;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public Date getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(Date manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<ManufactureRecordDetail> getRecordDetails() {
        return recordDetails;
    }

    public void setRecordDetails(List<ManufactureRecordDetail> recordDetails) {
        this.recordDetails = recordDetails;
    }

    @Override
    public String toString() {
        return "ManufactureRecord{" +
                "componentName='" + componentName + '\'' +
                ", manufactureDate=" + manufactureDate +
                ", scale=" + scale +
                ", owner='" + owner + '\'' +
                ", status=" + status +
                '}';
    }
}
