package com.atilaBiosystems.InventoryManagementSystem.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private int requestId;

    @Column(name = "item_catalog")
    private String itemCatalog;

    @Column(name = "item_description")
    private String itemDescription;

    @Column(name = "unit")
    private String unit;

    @Column(name = "item_URL")
    private String itemURL;

    @Column(name = "request_category")
    private Integer requestCategory;

    @Column(name = "project")
    private String project;

    @Column(name = "project_description")
    private String projectDescription;

    @Column(name = "purpose")
    private Integer purpose;

    @Column(name = "request_by")
    private String requestBy;

    @Column(name = "done_by")
    private String doneBy;

    @Column(name = "received_by")
    private String receivedBy;

    @Column(name = "request_amount")
    private Double requestAmount;

    @Column(name = "fulfilled_amount")
    private Double fulfilledAmount;

    @Column(name = "received_amount")
    private Double receivedAmount;

    @Column(name = "price_per_unit")
    private Double pricePerUnit;

    @Column(name = "request_date")
    private Date requestDate;

    @Column(name = "fulfilled_date")
    private Date fulfilledDate;

    @Column(name = "received_date")
    private Date receivedDate;

    @Column(name = "status")
    private Integer status;

    @Column(name = "order_number")
    private String orderNumber;

    @Column(name = "vendor")
    private String vendor;

    @Column(name = "comment")
    private String comment;

    @Column(name = "material_id")
    private Integer materialId;

    @Column(name = "component_record_id")
    private Integer componentRecordId;

    @Column(name = "product_record_id")
    private Integer productRecordId;

    // Define constructors
    public Request(){}

    public Request(String itemDescription,
                   Integer requestCategory,
                   String project, Integer purpose,
                   Double requestAmount,
                   Date requestDate) {
        this.itemDescription = itemDescription;
        this.requestCategory = requestCategory;
        this.project = project;
        this.purpose = purpose;
        this.requestAmount = requestAmount;
        this.requestDate = requestDate;
    }

    // Getters and Setters


    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getItemCatalog() {
        return itemCatalog;
    }

    public void setItemCatalog(String itemCatalog) {
        this.itemCatalog = itemCatalog;
    }

    public String getItemURL() {
        return itemURL;
    }

    public void setItemURL(String itemURL) {
        this.itemURL = itemURL;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public Integer getRequestCategory() {
        return requestCategory;
    }

    public void setRequestCategory(Integer requestCategory) {
        this.requestCategory = requestCategory;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public Integer getPurpose() {
        return purpose;
    }

    public void setPurpose(Integer purpose) {
        this.purpose = purpose;
    }

    public String getRequestBy() {
        return requestBy;
    }

    public void setRequestBy(String requestBy) {
        this.requestBy = requestBy;
    }

    public String getDoneBy() {
        return doneBy;
    }

    public void setDoneBy(String doneBy) {
        this.doneBy = doneBy;
    }

    public Double getRequestAmount() {
        return requestAmount;
    }

    public void setRequestAmount(Double requestAmount) {
        this.requestAmount = requestAmount;
    }

    public Double getFulfilledAmount() {
        return fulfilledAmount;
    }

    public void setFulfilledAmount(Double fulfilledAmount) {
        this.fulfilledAmount = fulfilledAmount;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getFulfilledDate() {
        return fulfilledDate;
    }

    public void setFulfilledDate(Date fulfilledDate) {
        this.fulfilledDate = fulfilledDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public Integer getMaterialId() {
        return materialId;
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

    public String getReceivedBy() {
        return receivedBy;
    }

    public void setReceivedBy(String receivedBy) {
        this.receivedBy = receivedBy;
    }

    public Double getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(Double receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }
}
