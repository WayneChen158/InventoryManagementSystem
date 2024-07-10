package com.atilaBiosystems.InventoryManagementSystem.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="invoice_id")
    private int invoiceId;
    @Column(name = "invoice_number")
    private String invoiceNumber;
    @Column(name="status") // status in 1,2,3 1 is created, 2 is shipped, 3 is deleted
    private int status;
    @ManyToOne
    @JoinColumn(name = "customer")
    @JsonIgnore
    private Customer customer;
    @Column(name="invoice_date")
    private Date invoiceDate;
    @Column(name="ship_date")
    private Date shipDate;
    @Column(name="tracking_number")
    private Double trackingNumber;

    @JsonManagedReference
    @OneToMany(mappedBy = "invoice")
    private List<InvoiceContent> invoiceContents;

    public Invoice(){}

    public Invoice(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;

    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public Double getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(Double trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public List<InvoiceContent> getInvoiceContents() {
        return invoiceContents;
    }

    public void setInvoiceContents(List<InvoiceContent> invoiceContents) {
        this.invoiceContents = invoiceContents;
    }
}
