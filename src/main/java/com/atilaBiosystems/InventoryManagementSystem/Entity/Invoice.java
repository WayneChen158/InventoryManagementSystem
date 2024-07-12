package com.atilaBiosystems.InventoryManagementSystem.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
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

    @Column(name = "url")
    private String url;

    @Column(name="status") // status in 1,2,3 1 is created, 2 is shipped, 3 is deleted
    private int status;

    @ManyToOne
    @JoinColumn(name = "customer")
    @JsonBackReference
    private Customer customer;

    @Column(name="invoice_date")
    private LocalDateTime invoiceDate;

    @Column(name="ship_date")
    private LocalDateTime shipDate;

    @Column(name="tracking_number")
    private String trackingNumber;

    @JsonManagedReference
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public List<InvoiceContent> getInvoiceContents() {
        return invoiceContents;
    }

    public void setInvoiceContents(List<InvoiceContent> invoiceContents) {
        this.invoiceContents = invoiceContents;
    }
}
