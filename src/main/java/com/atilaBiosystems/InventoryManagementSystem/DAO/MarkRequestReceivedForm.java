package com.atilaBiosystems.InventoryManagementSystem.DAO;

public class MarkRequestReceivedForm {
    private int requestId;
    private double fulfilledAmount;
    private double receivedAmount;
    private String receivedBy;
    private String receivedDate;
    private int isFullyReceived;
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
    public double getReceivedAmount() {
        return receivedAmount;
    }
    public void setReceivedAmount(double receivedAmount) {
        this.receivedAmount = receivedAmount;
    }
    public String getReceivedBy() {
        return receivedBy;
    }
    public void setReceivedBy(String receivedBy) {
        this.receivedBy = receivedBy;
    }
    public String getReceivedDate() {
        return receivedDate;
    }
    public void setReceivedDate(String receivedDate) {
        this.receivedDate = receivedDate;
    }
    public int getIsFullyReceived() {
        return isFullyReceived;
    }
    public void setIsFullyReceived(int isFullyReceived) {
        this.isFullyReceived = isFullyReceived;
    }
}
