package com.atilaBiosystems.InventoryManagementSystem.Service;

import com.atilaBiosystems.InventoryManagementSystem.DAO.CustomerDAO;
import com.atilaBiosystems.InventoryManagementSystem.DAO.InvoiceDAO;
import com.atilaBiosystems.InventoryManagementSystem.DAO.InvoiceItemDAO;
import com.atilaBiosystems.InventoryManagementSystem.DAO.SellItemDAO;
import com.atilaBiosystems.InventoryManagementSystem.Entity.*;

import java.util.List;

public interface SellService {

    void sellCurrentInvoice(List<SellItemDAO> itemList);

    List<Invoice> findAllUnshippedInvoice();

    List<Invoice> findAllShippedInvoice();

    Invoice findInvoiceById(Integer invoiceID);

    List<InvoiceItemDAO> checkInvoiceDetails(Invoice invoice);

    List<Customer> findAllCustomers();

    Customer findCustomerById(Integer customerId);

    void createNewCustomer(CustomerDAO customerDAO);

    void updateCustomerInfo(Customer customer, CustomerDAO customerDAO);

    void deleteCustomer(Customer customer);

    List<InvoiceDAO> findCustomerOrderHistory(Customer customer);

    void createInvoice(String invoiceNumber, Customer customer, List<InvoiceItemDAO> invoiceItems);

    void shipInvoice(Invoice invoice);

    void updateInvoice(InvoiceDAO invoiceDAO);

    void deleteInvoice(Invoice invoice);

    InvoiceContent findInvoiceContentById(Integer invoiceContentID);

    void addInvoiceItems(Invoice invoice, InvoiceItemDAO item);

    void updateInvoiceContent(InvoiceContent invoiceContent, InvoiceItemDAO item);

    void deleteInvoiceContent(InvoiceContent invoiceContent);
}
