package com.atilaBiosystems.InventoryManagementSystem.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="product_records")
public class ProductRecord {

    /*
    product_record_id INT NOT NULL AUTO_INCREMENT,
    product_name VARCHAR(100),
    product_id INT,
    lot_number VARCHAR(50),
    manufacture_date DATE,
    amount_in_stock INT,
    PRIMARY KEY (product_record_id),
    FOREIGN KEY (product_id) REFERENCES products (product_id)
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_record_id")
    private int productRecordId;

    @Column(name="product_name")
    private String productName;

    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="product_id")
    private Product product;

    @Column(name="lot_number")
    private String lotNumber;

    @Column(name="manufacture_date")
    private Date manufactureDate;

    @Column(name="amount_in_stock")
    private int amountInStock;

    public ProductRecord() {}

    public ProductRecord(String productName, String lotNumber, Date manufactureDate, int amountInStock) {
        this.productName = productName;
        this.lotNumber = lotNumber;
        this.manufactureDate = manufactureDate;
        this.amountInStock = amountInStock;
    }

    public int getProductRecordId() {
        return productRecordId;
    }

    public void setProductRecordId(int productRecordId) {
        this.productRecordId = productRecordId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public Date getManufactureDate() {
        return manufactureDate;
    }

    public void setManufactureDate(Date manufactureDate) {
        this.manufactureDate = manufactureDate;
    }

    public int getAmountInStock() {
        return amountInStock;
    }

    public void setAmountInStock(int amountInStock) {
        this.amountInStock = amountInStock;
    }

    @Override
    public String toString() {
        return "ProductRecord{" +
                "productName='" + productName + '\'' +
                ", lotNumber='" + lotNumber + '\'' +
                ", manufactureDate=" + manufactureDate +
                ", amountInStock=" + amountInStock +
                '}';
    }
}
