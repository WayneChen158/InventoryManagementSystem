package com.atilaBiosystems.InventoryManagementSystem.Entity;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name = "RD_materials")
public class RDMaterial {
    // Define fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="material_id")
    private int rdMaterialId;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="p_material_id")
    private RawMaterial material;
    @Column(name = "category")
    private Integer category;
    @Column(name="group_name")
    private int groupName;
    @Column(name="catalog_num")
    private String catalogNumber;
    @Column(name="description")
    private String description;
    @Column(name="manufacturer")
    private String manufacturer;
    @Column(name="concentration")
    private Double concentration;
    @Column(name="receive_date")
    private Date receiveDate;
    @Column(name="location")
    private String location;
    @Column(name="owner")
    private String owner;
    @Column(name="website")
    private String website;
    @Column(name="threshold")
    private Integer threshold;
    @Column(name="amount_in_stock")
    private Integer amountInStock;

    // Define constructors

    public RDMaterial() {

    }

    public RDMaterial(RawMaterial material, Integer amountInStock) {
        this.material = material;
        this.amountInStock = amountInStock;
    }

    public int getRdMaterialId() {
        return rdMaterialId;
    }

    public void setRdMaterialId(int rdMaterialId) {
        this.rdMaterialId = rdMaterialId;
    }

    public RawMaterial getMaterial() {
        return material;
    }

    public void setMaterial(RawMaterial material) {
        this.material = material;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
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

    public Double getConcentration() {
        return concentration;
    }

    public void setConcentration(Double concentration) {
        this.concentration = concentration;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public Integer getAmountInStock() {
        return amountInStock;
    }

    public void setAmountInStock(Integer amountInStock) {
        this.amountInStock = amountInStock;
    }
}
