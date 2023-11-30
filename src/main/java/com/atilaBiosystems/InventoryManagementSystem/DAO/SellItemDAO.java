package com.atilaBiosystems.InventoryManagementSystem.DAO;

public class SellItemDAO {
    private String catalog;
    private String category;
    private Integer currentStock;
    private boolean isEditable;
    private String itemName;
    private Integer recordId;
    private Integer sellQty;
    private String uniqueId;

    public SellItemDAO() {
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(Integer currentStock) {
        this.currentStock = currentStock;
    }

    public boolean isEditable() {
        return isEditable;
    }

    public void setEditable(boolean editable) {
        isEditable = editable;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getSellQty() {
        return sellQty;
    }

    public void setSellQty(Integer sellQty) {
        this.sellQty = sellQty;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }
}
