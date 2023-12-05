package com.atilaBiosystems.InventoryManagementSystem.DAO;

public class RequestDAO {
    private String itemDescription;
    private String catalogNumber;
    private String itemURL;
    private int requestCategory;
    private String project;
    private int purpose;
    private double requestAmount;
    private double pricePerUnit;
    private String requestBy;
    private String requestDate;
    private Integer materialId;
    private Integer componentRecordId;
    private Integer productRecordId;

    public String getItemDescription() {
        return itemDescription;
    }
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }
    public String getCatalogNumber() {
        return catalogNumber;
    }
    public void setCatalogNumber(String catalogNumber) {
        this.catalogNumber = catalogNumber;
    }
    public String getItemURL() {
        return itemURL;
    }
    public void setItemURL(String itemURL) {
        this.itemURL = itemURL;
    }
    public int getRequestCategory() {
        return requestCategory;
    }
    public void setRequestCategory(int requestCategory) {
        this.requestCategory = requestCategory;
    }
    public String getProject() {
        return project;
    }
    public void setProject(String project) {
        this.project = project;
    }
    public int getPurpose() {
        return purpose;
    }
    public void setPurpose(int purpose) {
        this.purpose = purpose;
    }
    public double getRequestAmount() {
        return requestAmount;
    }
    public void setRequestAmount(double requestAmount) {
        this.requestAmount = requestAmount;
    }
    public double getPricePerUnit() {
        return pricePerUnit;
    }
    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
    public String getRequestBy() {
        return requestBy;
    }
    public void setRequestBy(String requestBy) {
        this.requestBy = requestBy;
    }
    public String getRequestDate() {
        return requestDate;
    }
    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }
    public Integer getMaterialId() {
        return materialId;
    }
    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }
    public Integer getComponentRecordId() {
        return componentRecordId;
    }
    public void setComponentRecordId(Integer componentRecordId) {
        this.componentRecordId = componentRecordId;
    }
    public Integer getProductRecordId() {
        return productRecordId;
    }
    public void setProductRecordId(Integer productRecordId) {
        this.productRecordId = productRecordId;
    }
}
