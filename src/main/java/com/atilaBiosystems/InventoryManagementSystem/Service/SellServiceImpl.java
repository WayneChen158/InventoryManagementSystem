package com.atilaBiosystems.InventoryManagementSystem.Service;

import com.atilaBiosystems.InventoryManagementSystem.DAO.SellItemDAO;
import com.atilaBiosystems.InventoryManagementSystem.Entity.*;
import com.atilaBiosystems.InventoryManagementSystem.Repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellServiceImpl implements SellService{
    private RawMaterialsRepository rawMaterialsRepository;
    private ProductRecordRepository productRecordRepository;
    private ComponentRecordRepository componentRecordRepository;
    private InvoiceRpository invoiceRpository;
    private InvoiceContentRepository invoiceContentRepository;
    private CustomerRepository customerRepository;

    public SellServiceImpl(RawMaterialsRepository rawMaterialsRepository,
                           ProductRecordRepository productRecordRepository,
                           ComponentRecordRepository componentRecordRepository,
                           InvoiceRpository invoiceRpository,
                           InvoiceContentRepository invoiceContentRepository,
                           CustomerRepository customerRepository) {
        this.rawMaterialsRepository = rawMaterialsRepository;
        this.productRecordRepository = productRecordRepository;
        this.componentRecordRepository = componentRecordRepository;
        this.invoiceRpository = invoiceRpository;
        this.invoiceContentRepository = invoiceContentRepository;
        this.customerRepository = customerRepository;
    }


    @Override
    @Transactional
    public void sellCurrentInvoice(List<SellItemDAO> itemList) {
        for (SellItemDAO item: itemList){
            if (item.getCategory().equals("product")) {
                ProductRecord productRecord = productRecordRepository.findById(item.getRecordId()).orElse(null);
                productRecord.setAmountInStock(item.getCurrentStock() - item.getSellQty());
            } else if (item.getCategory().equals("component")) {
                ComponentRecord componentRecord = componentRecordRepository.findById(item.getRecordId()).orElse(null);
                componentRecord.setAmountInStock(item.getCurrentStock() - item.getSellQty());
            } else {
                RawMaterial rawMaterial = rawMaterialsRepository.findById(item.getRecordId()).orElse(null);
                rawMaterial.setAmountInStock(item.getCurrentStock() - item.getSellQty());
            }
        }
    }

    @Override
    public List<Invoice> findAllInvoice() {
        return invoiceRpository.findAll();
    }

    @Override
    public List<Invoice> findAllUnshippedInvoice() {
        return invoiceRpository.findByStatus(1);
    }

    @Override
    public List<Invoice> findAllShippedInvoice() {
        return invoiceRpository.findByStatus(2);
    }

    @Override
    public List<InvoiceContent> checkInvoiceDetails(Invoice invoice) {
        if (invoice == null) return new ArrayList<>();
        return invoice.getInvoiceContents();
    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public void createInvoice(String invoiceNumber, Customer customer, List<InvoiceContent> invoiceContents) {
        Invoice invoice = new Invoice(invoiceNumber);
        if (customer != null){
            invoice.setCustomer(customer);
        }

    }


}
