package com.atilaBiosystems.InventoryManagementSystem.Service;

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

    void createInvoice(String invoiceNumber, Customer customer, List<InvoiceContent> invoiceContents);
}
