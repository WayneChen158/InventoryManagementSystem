package com.atilaBiosystems.InventoryManagementSystem.DAO;

import com.atilaBiosystems.InventoryManagementSystem.Entity.ProductRecord;

import java.util.List;

public class ProductStockForm {

    private int productId;

    private String productCatalog;

    private String productName;

    private int amountInStock;

    public ProductStockForm(){}

    public ProductStockForm(int productId, String productCatalog, String productName,
                            int amountInStock) {
        this.productId = productId;
        this.productCatalog = productCatalog;
        this.productName = productName;
        this.amountInStock = amountInStock;
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

    public int getAmountInStock() {
        return amountInStock;
    }

    public void setAmountInStock(int amountInStock) {
        this.amountInStock = amountInStock;
    }

}
