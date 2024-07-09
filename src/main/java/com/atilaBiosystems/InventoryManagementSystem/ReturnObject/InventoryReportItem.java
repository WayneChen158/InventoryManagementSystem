package com.atilaBiosystems.InventoryManagementSystem.ReturnObject;

public class InventoryReportItem {
    private String catalogNum;
    private String description;
    private Integer value;

    // Getters and Setters

    public String getCatalogNum() {
        return catalogNum;
    }

    public void setCatalogNum(String catalogNum) {
        this.catalogNum = catalogNum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
