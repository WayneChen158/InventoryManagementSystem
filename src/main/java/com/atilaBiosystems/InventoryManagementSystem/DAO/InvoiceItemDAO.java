package com.atilaBiosystems.InventoryManagementSystem.DAO;

public class InvoiceItemDAO {
    private String category;

    private Integer uniqueID;

    private Integer amount;

    public InvoiceItemDAO(){}

    public InvoiceItemDAO(String category, Integer uniqueID, Integer amount) {
        this.category = category;
        this.uniqueID = uniqueID;
        this.amount = amount;
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
}
