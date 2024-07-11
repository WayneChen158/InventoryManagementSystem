package com.atilaBiosystems.InventoryManagementSystem.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "invoice_contents")
public class InvoiceContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="content_id")
    private int contentId;

    @ManyToOne
    @JoinColumn(name = "invoice_id", referencedColumnName = "invoice_id")
    @JsonIgnore
    private Invoice invoice;

    @OneToOne
    @JoinColumn(name = "product_record_id")
    @JsonIgnore
    private ProductRecord productRecord;

    @OneToOne
    @JoinColumn(name = "component_record_id")
    @JsonIgnore
    private ComponentRecord componentRecord;

    @OneToOne
    @JoinColumn(name = "material_id")
    @JsonIgnore
    private RawMaterial materialRecord;

    @Column(name = "qty")
    private Integer qty;

    public InvoiceContent(){}

    public InvoiceContent(Invoice invoice) {
        this.invoice = invoice;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public ProductRecord getProductRecord() {
        return productRecord;
    }

    public void setProductRecord(ProductRecord productRecord) {
        this.productRecord = productRecord;
    }

    public ComponentRecord getComponentRecord() {
        return componentRecord;
    }

    public void setComponentRecord(ComponentRecord componentRecord) {
        this.componentRecord = componentRecord;
    }

    public RawMaterial getMaterialRecord() {
        return materialRecord;
    }

    public void setMaterialRecord(RawMaterial materialRecord) {
        this.materialRecord = materialRecord;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
}
