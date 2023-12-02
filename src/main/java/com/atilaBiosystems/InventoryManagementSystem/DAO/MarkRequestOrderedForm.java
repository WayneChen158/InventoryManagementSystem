package com.atilaBiosystems.InventoryManagementSystem.DAO;

public class MarkRequestOrderedForm {
    private int requestId;
    private double fulfilledAmount;
    private String doneBy;
    private String fulfilledDate;
    public int getRequestId() {
        return requestId;
    }
    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }
    public double getFulfilledAmount() {
        return fulfilledAmount;
    }
    public void setFulfilledAmount(double fulfilledAmount) {
        this.fulfilledAmount = fulfilledAmount;
    }
    public String getDoneBy() {
        return doneBy;
    }
    public void setDoneBy(String doneBy) {
        this.doneBy = doneBy;
    }
    public String getFulfilledDate() {
        return fulfilledDate;
    }
    public void setFulfilledDate(String fulfilledDate) {
        this.fulfilledDate = fulfilledDate;
    }
}
