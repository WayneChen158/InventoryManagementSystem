package com.atilaBiosystems.InventoryManagementSystem.DAO;

public class CustomerDAO {

    private String customerName;

    private String company;

    private String phoneNumber;

    private String emailAddress;

    private String shippingAddress;

    public CustomerDAO(){}

    public CustomerDAO(String customerName, String company,
                       String phoneNumber, String emailAddress, String shippingAddress) {
        this.customerName = customerName;
        this.company = company;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.shippingAddress = shippingAddress;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
