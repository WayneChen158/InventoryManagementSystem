package com.atilaBiosystems.InventoryManagementSystem.DAO;

public class InvoiceItemDAO {
    private Integer invoiceContentID;

    private String category;

    private Integer uniqueID;

    private String SKU;

    private String description;

    private Integer amount;

    public InvoiceItemDAO(){}

    public InvoiceItemDAO(Integer invoiceContentID, String category,
                          String SKU, Integer uniqueID, String description, Integer amount) {
        this.invoiceContentID = invoiceContentID;
        this.category = category;
        this.SKU = SKU;
        this.uniqueID = uniqueID;
        this.description = description;
        this.amount = amount;
    }

    public Integer getInvoiceContentID() {
        return invoiceContentID;
    }

    public void setInvoiceContentID(Integer invoiceContentID) {
        this.invoiceContentID = invoiceContentID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(Integer uniqueID) {
        this.uniqueID = uniqueID;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
