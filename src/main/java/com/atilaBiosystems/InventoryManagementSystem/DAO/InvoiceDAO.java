package com.atilaBiosystems.InventoryManagementSystem.DAO;
import java.util.Date;

public class InvoiceDAO {

    private Integer invoiceID;

    private String invoiceNumber;

    private Date invoiceDate;

    private Date shipDate;

    public InvoiceDAO(){}

    public InvoiceDAO(Integer invoiceID, String invoiceNumber, Date invoiceDate, Date shipDate) {
        this.invoiceID = invoiceID;
        this.invoiceNumber = invoiceNumber;
        this.invoiceDate = invoiceDate;
        this.shipDate = shipDate;
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

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }
}
