package com.atilaBiosystems.InventoryManagementSystem.DAO;

public class MarkRequestReceivedForm {
    private int requestId;
    private double fulfilledAmount;
    private double receivedAmount;
    private String unit;
    private double inboundFactor;
    private String receivedBy;
    private String receivedDate;
    private int isFullyReceived;
    public int getRequestId() {
        return requestId;
    }
    public double getFulfilledAmount() {
        return fulfilledAmount;
    }
    public double getReceivedAmount() {
        return receivedAmount;
    }
    public String getUnit() {
        return unit;
    }
    public double getInboundFactor() {
        return inboundFactor;
    }
    public String getReceivedBy() {
        return receivedBy;
    }
    public String getReceivedDate() {
        return receivedDate;
    }
    public int getIsFullyReceived() {
        return isFullyReceived;
    }
}
