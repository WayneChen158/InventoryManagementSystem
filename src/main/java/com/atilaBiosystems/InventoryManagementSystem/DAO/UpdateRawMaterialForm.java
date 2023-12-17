package com.atilaBiosystems.InventoryManagementSystem.DAO;

public class UpdateRawMaterialForm {
    private int materialId;
    private Integer category;
    private int groupName;
    private String catalogNumber;
    private String description;
    private String manufacturer;
    private String concentration;
    private String receiveDate;
    private String location;
    private String owner;
    private String website;
    private Integer threshold;
    private Integer amountInStock;
    private String unit;
    public int getMaterialId() {
        return materialId;
    }
    public Integer getCategory() {
        return category;
    }
    public int getGroupName() {
        return groupName;
    }
    public String getCatalogNumber() {
        return catalogNumber;
    }
    public String getDescription() {
        return description;
    }
    public String getManufacturer() {
        return manufacturer;
    }
    public String getConcentration() {
        return concentration;
    }
    public String getReceiveDate() {
        return receiveDate;
    }
    public String getLocation() {
        return location;
    }
    public String getOwner() {
        return owner;
    }
    public String getWebsite() {
        return website;
    }
    public Integer getThreshold() {
        return threshold;
    }
    public Integer getAmountInStock() {
        return amountInStock;
    }
    public String getUnit() {
        return unit;
    }
}