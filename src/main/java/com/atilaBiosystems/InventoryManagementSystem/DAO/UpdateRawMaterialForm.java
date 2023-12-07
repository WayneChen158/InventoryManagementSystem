package com.atilaBiosystems.InventoryManagementSystem.DAO;

public class UpdateRawMaterialForm {
    private int materialId;
    private Integer category;
    private int groupName;
    private String catalogNumber;
    private String description;
    private String manufacturer;
    private Double concentration;
    private String receiveDate;
    private String location;
    private String owner;
    private String website;
    private Integer threshold;
    private Integer amountInStock;
    public int getMaterialId() {
        return materialId;
    }
    public void setMaterialId(int materialId) {
        this.materialId = materialId;
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
    public String getReceiveDate() {
        return receiveDate;
    }
    public void setReceiveDate(String receiveDate) {
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
