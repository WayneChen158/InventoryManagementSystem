package com.atilaBiosystems.InventoryManagementSystem.Controller;

import com.atilaBiosystems.InventoryManagementSystem.DAO.InvoiceItemDAO;
import com.atilaBiosystems.InventoryManagementSystem.DAO.SellItemDAO;
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

    @PostMapping("/invoices-create/{invoiceNumber}/{customerID}")
    public void createInvoice(
            @PathVariable String invoiceNumber,
            @PathVariable String customerID,
            @RequestBody List<InvoiceItemDAO> invoiceItemDAOs)
    {
        Customer customer = sellService.findCustomerById(Integer.valueOf(customerID));
        sellService.createInvoice(invoiceNumber,customer,invoiceItemDAOs);
    }

    @GetMapping("/customers")
    public List<Customer> findAllCustomers(){
        return sellService.findAllCustomers();
    }

    @PostMapping("/customer-create")
    public void createCustomer(
            @RequestBody Customer customer){
        sellService.saveCustomer(customer);
    }
}
