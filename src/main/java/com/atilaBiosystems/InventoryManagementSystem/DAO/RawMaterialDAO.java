package com.atilaBiosystems.InventoryManagementSystem.DAO;

public class RawMaterialDAO {
    private String itemName;
    private String catlogNumber;
    private String vendor;
    private int rawMaterialType;
    private String owner;
    private String location;
    private int amount;
    private int alertAmount;
    private String website;
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public String getCatlogNumber() {
        return catlogNumber;
    }
    public void setCatlogNumber(String catlogNumber) {
        this.catlogNumber = catlogNumber;
    }
    public String getVendor() {
        return vendor;
    }
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
    public int getRawMaterialType() {
        return rawMaterialType;
    }
    public void setRawMaterialType(int rawMaterialType) {
        this.rawMaterialType = rawMaterialType;
    }
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public int getAlertAmount() {
        return alertAmount;
    }
    public void setAlertAmount(int alertAmount) {
        this.alertAmount = alertAmount;
    }
    public String getWebsite() {
        return website;
    }
    public void setWebsite(String website) {
        this.website = website;
    }
}
