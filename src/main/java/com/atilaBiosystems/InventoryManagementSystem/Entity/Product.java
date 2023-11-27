package com.atilaBiosystems.InventoryManagementSystem.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;
@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @Column(name = "product_catalog")
    private String productCatalog;

    @Column(name = "product_name")
    private String productName;

    @Column(name="version_description")
    private String versionDescription;

    @JsonManagedReference
    @OneToMany(mappedBy = "product")
    private List<ProductRecord> productRecords;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.REFRESH,
                    CascadeType.PERSIST, CascadeType.PERSIST})
    @JoinTable(
            name = "assembly_by",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "component_id")
    )
    private List<Component> components;

    public Product(){}

    public Product(String productCatalog, String productName, String versionDescription) {
        this.productCatalog = productCatalog;
        this.productName = productName;
        this.versionDescription = versionDescription;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductCatalog() {
        return productCatalog;
    }

    public void setProductCatalog(String productCatalog) {
        this.productCatalog = productCatalog;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getVersionDescription() {
        return versionDescription;
    }

    public void setVersionDescription(String versionDescription) {
        this.versionDescription = versionDescription;
    }

    public List<ProductRecord> getProductRecords() {
        return productRecords;
    }

    public void setProductRecords(List<ProductRecord> productRecords) {
        this.productRecords = productRecords;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", versionDescription='" + versionDescription + '\'' +
                '}';
    }
}
