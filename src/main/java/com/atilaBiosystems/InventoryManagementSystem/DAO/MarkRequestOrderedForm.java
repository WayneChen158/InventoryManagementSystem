package com.atilaBiosystems.InventoryManagementSystem.DAO;

public class MarkRequestOrderedForm {
    private int requestId;
    private double fulfilledAmount;
    private Double pricePerUnit;
    private String doneBy;
    private String fulfilledDate;
    public int getRequestId() {
        return requestId;
    }
    public double getFulfilledAmount() {
        return fulfilledAmount;
    }
    public Double getPricePerUnit() {
        return pricePerUnit;
    }
    public String getDoneBy() {
        return doneBy;
    }
    public String getFulfilledDate() {
        return fulfilledDate;
    }
}
