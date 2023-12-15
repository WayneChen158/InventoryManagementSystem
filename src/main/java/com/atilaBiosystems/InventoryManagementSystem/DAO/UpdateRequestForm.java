package com.atilaBiosystems.InventoryManagementSystem.DAO;

public class UpdateRequestForm {
    private int requestId;
    private String itemURL;
    private Integer purpose;
    private String project;
    private Double requestAmount;
    private Double fulfilledAmount;
    private Double pricePerUnit;
    public int getRequestId() {
        return requestId;
    }
    public String getItemURL() {
        return itemURL;
    }
    public Integer getPurpose() {
        return purpose;
    }
    public String getProject() {
        return project;
    }
    public Double getRequestAmount() {
        return requestAmount;
    }
    public Double getFulfilledAmount() {
        return fulfilledAmount;
    }
    public Double getPricePerUnit() {
        return pricePerUnit;
    }
}
