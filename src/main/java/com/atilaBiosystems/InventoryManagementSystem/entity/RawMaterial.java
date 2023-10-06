package com.atilaBiosystems.InventoryManagementSystem.entity;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name = "raw_materials")
public class RawMaterial {
    // Define fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="material_id")
    private int materialId;
    @Column(name="group_name")
    private int groupName;
    @Column(name="catalog_num")
    private String catalogNumber;
    @Column(name="description")
    private String description;
    @Column(name="manufacturer")
    private String manufacturer;
    @Column(name="concentration")
    private double concentration;
    @Column(name="receive_date")
    private Date receiveDate;
    @Column(name="threshold")
    private int threshold;
    @Column(name="amount_in_stock")
    private int amountInStock;

    // Define constructors
    public RawMaterial() {

    }

    public RawMaterial(int groupName, String catalogNumber, String description,
                       String manufacturer, double concentration, Date receiveDate,
                       int threshold, int amountInStock) {
        this.groupName = groupName;
        this.catalogNumber = catalogNumber;
        this.description = description;
        this.manufacturer = manufacturer;
        this.concentration = concentration;
        this.receiveDate = receiveDate;
        this.threshold = threshold;
        this.amountInStock = amountInStock;
    }

    // Define getters/setters
    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public int getGroupName() {
        return groupName;
    }

    public void setGroupName(int groupName) {
        this.groupName = groupName;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getConcentration() {
        return concentration;
    }

    public void setConcentration(double concentration) {
        this.concentration = concentration;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public int getAmountInStock() {
        return amountInStock;
    }

    public void setAmountInStock(int amountInStock) {
        this.amountInStock = amountInStock;
    }

    // Define toString() method

    @Override
    public String toString() {
        String s = groupName == 1 ? "Chemicals" : "Oligos";
        return "RawMaterials{" +
                "materialId=" + materialId +
                ", groupName=" + s +
                ", description='" + description + '\'' +
                ", amountInStock=" + amountInStock +
                '}';
    }
}
