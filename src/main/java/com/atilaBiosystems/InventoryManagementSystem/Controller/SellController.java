package com.atilaBiosystems.InventoryManagementSystem.Controller;

import com.atilaBiosystems.InventoryManagementSystem.DAO.*;
import com.atilaBiosystems.InventoryManagementSystem.Entity.*;
import com.atilaBiosystems.InventoryManagementSystem.Service.SellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SellController {

    private final SellService sellService;

    @Autowired
    public SellController(SellService sellService) {
        this.sellService = sellService;
    }

    @PutMapping("/sell")
    public ResponseEntity<String> sellCurrentInvoice(@RequestBody List<SellItemDAO> itemList) {
        try {
            sellService.sellCurrentInvoice(itemList);
            return ResponseEntity.ok("Invoice processed successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing invoice: " + e.getMessage());
        }
    }

    @GetMapping("/invoices-unshipped")
    public List<Invoice> getUnshippedInvoices(){
        return sellService.findAllUnshippedInvoice();
    }

    @GetMapping("/invoices-shipped")
    public List<Invoice> getShippedInvoices(){
        return sellService.findAllShippedInvoice();
    }

    @PostMapping("/invoices-create/{customerID}")
    public void createInvoice(
            @PathVariable String customerID,
            @RequestBody CreateInvoiceForm invoiceForm)
    {
        Customer customer = sellService.findCustomerById(Integer.valueOf(customerID));
        sellService.createInvoice(invoiceForm.getInvoiceDAO(),customer,invoiceForm.getInvoiceItemDAOs());
    }

    @PutMapping("/invoices-ship/{invoiceID}")
    public void shipInvoice(
            @PathVariable String invoiceID)
    {
        Invoice invoice = sellService.findInvoiceById(Integer.valueOf(invoiceID));
        sellService.shipInvoice(invoice);
    }

    @PutMapping("/invoices-update")
    public void updateInvoice(@RequestBody InvoiceDAO invoiceDAO){
        sellService.updateInvoice(invoiceDAO);
    }

    @DeleteMapping("/invoice-delete/{invoiceID}")
    public void deleteInvoice(@PathVariable String invoiceID){
        Invoice invoice = sellService.findInvoiceById(Integer.valueOf(invoiceID));
        sellService.deleteInvoice(invoice);
    }

    @GetMapping("/invoices-detail/{invoiceID}")
    public List<InvoiceItemDAO> getInvoiceDetails(@PathVariable String invoiceID){
        Invoice invoice = sellService.findInvoiceById(Integer.valueOf(invoiceID));
        return sellService.checkInvoiceDetails(invoice);
    }

    @PostMapping("/Invoices-detail-add/{invoiceID}")
    public void addInvoiceContent(
            @PathVariable String invoiceID,
            @RequestBody InvoiceItemDAO invoiceItemDAO){
        Invoice invoice = sellService.findInvoiceById(Integer.valueOf(invoiceID));
        sellService.addInvoiceItems(invoice, invoiceItemDAO);
    }

    @PutMapping("/Invoices-detail-update/{invoiceContentID}")
    public void updateInvoiceContent(
            @RequestBody InvoiceItemDAO invoiceItemDAO){
        sellService.updateInvoiceContent(invoiceItemDAO);
    }

    @DeleteMapping("/Invoices-detail-delete/{invoiceContentID}")
    public void deleteInvoiceContent(@PathVariable String invoiceContentID){
        InvoiceContent invoiceContent = sellService.findInvoiceContentById(Integer.valueOf(invoiceContentID));
        sellService.deleteInvoiceContent(invoiceContent);
    }

    @GetMapping("/customers")
    public List<Customer> findAllCustomers(){
        return sellService.findAllCustomers();
    }

    @PostMapping("/customer-create")
    public void createCustomer(
            @RequestBody CustomerDAO customerDAO){
        sellService.createNewCustomer(customerDAO);
    }

    @PutMapping("/customer-update/{customerID}")
    public void updateCustomerInfo(
            @PathVariable String customerID,
            @RequestBody CustomerDAO customerDAO){
        Customer customer = sellService.findCustomerById(Integer.valueOf(customerID));
        sellService.updateCustomerInfo(customer, customerDAO);
    }

    @DeleteMapping("/customer-delete/{customerID}")
    public void deleteCustomer(
            @PathVariable String customerID){
        Customer customer = sellService.findCustomerById(Integer.valueOf(customerID));
        sellService.deleteCustomer(customer);
    }

    @GetMapping("/customer-order-history/{customerID}")
    public List<InvoiceDAO> getOrderHistory(
            @PathVariable String customerID){
        Customer customer = sellService.findCustomerById(Integer.valueOf(customerID));
        return sellService.findCustomerOrderHistory(customer);
    }
}
