package com.atilaBiosystems.InventoryManagementSystem.DAO;

import java.time.LocalDateTime;

public class InvoiceDAO {

    private Integer invoiceID;

    private String invoiceNumber;

    private String url;

    private LocalDateTime invoiceDate;

    private LocalDateTime shipDate;

    private String trackingNumber;

    public InvoiceDAO(){}

    public InvoiceDAO(Integer invoiceID, String invoiceNumber,
                      String url, LocalDateTime invoiceDate, LocalDateTime shipDate,
                      String trackingNumber) {
        this.invoiceID = invoiceID;
        this.invoiceNumber = invoiceNumber;
        this.url = url;
        this.invoiceDate = invoiceDate;
        this.shipDate = shipDate;
        this.trackingNumber = trackingNumber;
    }

    public Integer getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(Integer invoiceID) {
        this.invoiceID = invoiceID;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDateTime invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public LocalDateTime getShipDate() {
        return shipDate;
    }

    public void setShipDate(LocalDateTime shipDate) {
        this.shipDate = shipDate;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
}
