package com.atilaBiosystems.InventoryManagementSystem.DAO;

public class RequestDAO {
    private String itemDescription;
    private String vendor;
    private String catalogNumber;
    private String itemURL;
    private int requestCategory;
    private String project;
    private int purpose;
    private double requestAmount;
    private String unit;
    private double pricePerUnit;
    private String requestBy;
    private String requestDate;
    private String comment;
    private Integer materialId;
    private Integer componentRecordId;
    private Integer productRecordId;

    public String getItemDescription() {
        return itemDescription;
    }
    public String getVendor() {
        return vendor;
    }
    public String getCatalogNumber() {
        return catalogNumber;
    }
    public String getItemURL() {
        return itemURL;
    }
    public int getRequestCategory() {
        return requestCategory;
    }
    public String getProject() {
        return project;
    }
    public int getPurpose() {
        return purpose;
    }
    public double getRequestAmount() {
        return requestAmount;
    }
    public String getUnit() {
        return unit;
    }
    public double getPricePerUnit() {
        return pricePerUnit;
    }
    public String getRequestBy() {
        return requestBy;
    }
    public String getRequestDate() {
        return requestDate;
    }
    public String getComment() {
        return comment;
    }
    public Integer getMaterialId() {
        return materialId;
    }
    public Integer getComponentRecordId() {
        return componentRecordId;
    }
    public Integer getProductRecordId() {
        return productRecordId;
    }
}
