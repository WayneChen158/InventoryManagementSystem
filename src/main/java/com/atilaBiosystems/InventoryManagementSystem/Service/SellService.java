package com.atilaBiosystems.InventoryManagementSystem.Service;

import com.atilaBiosystems.InventoryManagementSystem.DAO.InvoiceItemDAO;
import com.atilaBiosystems.InventoryManagementSystem.DAO.SellItemDAO;
import com.atilaBiosystems.InventoryManagementSystem.Entity.*;

import java.util.List;

public interface SellService {

    void sellCurrentInvoice(List<SellItemDAO> itemList);

    List<Invoice> findAllInvoice();

    List<Invoice> findAllUnshippedInvoice();

    List<Invoice> findAllShippedInvoice();

    List<InvoiceContent> checkInvoiceDetails(Invoice invoice);

    List<Customer> findAllCustomers();

    Customer findCustomerById(Integer customerId);

    void saveCustomer(Customer customer);

    List<Invoice> findCustomerOrderHistory(Customer customer);

    void createInvoice(String invoiceNumber, Customer customer, List<InvoiceItemDAO> invoiceItems);

    void shipInvoice(Invoice invoice);

    void deleteInvoice(Invoice invoice);

    List<InvoiceContent> addInvoiceItems(Invoice invoice, List<InvoiceItemDAO> invoiceItems);

    void updateInvoiceContent(InvoiceContent invoiceContent, InvoiceItemDAO item);
}
