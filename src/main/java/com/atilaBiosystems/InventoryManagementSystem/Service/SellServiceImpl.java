package com.atilaBiosystems.InventoryManagementSystem.Service;

import com.atilaBiosystems.InventoryManagementSystem.DAO.InvoiceItemDAO;
import com.atilaBiosystems.InventoryManagementSystem.DAO.SellItemDAO;
import com.atilaBiosystems.InventoryManagementSystem.Entity.*;
import com.atilaBiosystems.InventoryManagementSystem.Exception.InsufficientStockException;
import com.atilaBiosystems.InventoryManagementSystem.Repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SellServiceImpl implements SellService{
    private final RawMaterialsRepository rawMaterialsRepository;
    private final ProductRecordRepository productRecordRepository;
    private final ComponentRecordRepository componentRecordRepository;
    private final InvoiceRpository invoiceRpository;
    private final InvoiceContentRepository invoiceContentRepository;
    private final CustomerRepository customerRepository;

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
                assert productRecord != null;
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
    public Customer findCustomerById(Integer customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public List<Invoice> findCustomerOrderHistory(Customer customer) {
        return customer.getInvoices();
    }

    @Override
    @Transactional
    public void createInvoice(String invoiceNumber, Customer customer, List<InvoiceItemDAO> invoiceItems) {
        Invoice invoice = new Invoice(invoiceNumber);
        if (customer != null){
            invoice.setCustomer(customer);
        }

        List<InvoiceContent> details = new ArrayList<>();

        for(InvoiceItemDAO item: invoiceItems){
            InvoiceContent curr =new InvoiceContent(invoice);
            if (item.getCategory().equals("r")){
                RawMaterial rawMaterial = rawMaterialsRepository.findById(item.getUniqueID())
                        .orElseThrow(() -> new NoSuchElementException("RawMaterial not found with ID: " + item.getUniqueID()));
                curr.setMaterialRecord(rawMaterial);
            } else if (item.getCategory().equals("c")){
                ComponentRecord componentRecord = componentRecordRepository.findById(item.getUniqueID())
                        .orElseThrow(() -> new NoSuchElementException("ComponentRecord not found with ID: " + item.getUniqueID()));
                curr.setComponentRecord(componentRecord);
            } else {
                ProductRecord productRecord = productRecordRepository.findById(item.getUniqueID())
                        .orElseThrow(() -> new NoSuchElementException("ProductRecord not found with ID: " + item.getUniqueID()));
                curr.setProductRecord(productRecord);
            }
            curr.setQty(item.getAmount());
            invoiceContentRepository.save(curr);
            details.add(curr);
        }
        invoice.setStatus(1);
        invoice.setInvoiceDate(new Date());
        invoice.setInvoiceContents(details);
        invoiceRpository.save(invoice);

    }

    @Override
    @Transactional
    public void shipInvoice(Invoice invoice) {
        //Check if the stock is enough to make this shipment successfully
        List<InvoiceContent> invoiceContents = invoice.getInvoiceContents();
        for (InvoiceContent item: invoiceContents){
            if (item.getMaterialRecord() != null){
                RawMaterial rawMaterial = item.getMaterialRecord();
                if(rawMaterial.getAmountInStock() < item.getQty()){
                    throw new InsufficientStockException("Not enough amount for shipping for material: " + rawMaterial.getCatalogNumber());
                }
            }
            if(item.getComponentRecord() != null){
                ComponentRecord componentRecord = item.getComponentRecord();
                if(componentRecord.getAmountInStock() < item.getQty()){
                    throw new InsufficientStockException("Not enough amount for shipping for material: "
                            + componentRecord.getComponentCatalog() + " " + componentRecord.getLotNumber());
                }
            }
            if(item.getProductRecord() != null){
                ProductRecord productRecord = item.getProductRecord();
                if(productRecord.getAmountInStock() < item.getQty()){
                    throw new InsufficientStockException("Not enough amount for shipping for material: "
                            + productRecord.getProductCatalog() + " " + productRecord.getLotNumber());
                }
            }
        }

        //If pass the inventory check, deduct it from the system
        for (InvoiceContent item: invoiceContents){
            if (item.getMaterialRecord() != null){
                RawMaterial rawMaterial = item.getMaterialRecord();
                rawMaterial.setAmountInStock(rawMaterial.getAmountInStock() - item.getQty());
            }
            if(item.getComponentRecord() != null){
                ComponentRecord componentRecord = item.getComponentRecord();
                componentRecord.setAmountInStock(componentRecord.getAmountInStock() - item.getQty());
            }
            if(item.getProductRecord() != null){
                ProductRecord productRecord = item.getProductRecord();
                productRecord.setAmountInStock(productRecord.getAmountInStock() - item.getQty());
            }
        }

        invoice.setStatus(2);
    }

    @Override
    @Transactional
    public void deleteInvoice(Invoice invoice) {
        invoice.setStatus(3);
    }

    @Override
    @Transactional
    public List<InvoiceContent> addInvoiceItems(Invoice invoice, List<InvoiceItemDAO> invoiceItems) {

        List<InvoiceContent> res = new ArrayList<>();

        for(InvoiceItemDAO item: invoiceItems){
            InvoiceContent curr =new InvoiceContent(invoice);
            if (item.getCategory().equals("r")){
                RawMaterial rawMaterial = rawMaterialsRepository.findById(item.getUniqueID())
                        .orElseThrow(() -> new NoSuchElementException("RawMaterial not found with ID: " + item.getUniqueID()));
                curr.setMaterialRecord(rawMaterial);
            } else if (item.getCategory().equals("c")){
                ComponentRecord componentRecord = componentRecordRepository.findById(item.getUniqueID())
                        .orElseThrow(() -> new NoSuchElementException("ComponentRecord not found with ID: " + item.getUniqueID()));
                curr.setComponentRecord(componentRecord);
            } else {
                ProductRecord productRecord = productRecordRepository.findById(item.getUniqueID())
                        .orElseThrow(() -> new NoSuchElementException("ProductRecord not found with ID: " + item.getUniqueID()));
                curr.setProductRecord(productRecord);
            }
            curr.setQty(item.getAmount());
            res.add(curr);
        }

        return res;
    }

    @Override
    @Transactional
    public void updateInvoiceContent(InvoiceContent invoiceContent, InvoiceItemDAO item) {
        invoiceContent.setMaterialRecord(null);
        invoiceContent.setComponentRecord(null);
        invoiceContent.setProductRecord(null);

        if (item.getCategory().equals("r")){
            RawMaterial rawMaterial = rawMaterialsRepository.findById(item.getUniqueID())
                    .orElseThrow(() -> new NoSuchElementException("RawMaterial not found with ID: " + item.getUniqueID()));
            invoiceContent.setMaterialRecord(rawMaterial);
        } else if (item.getCategory().equals("c")){
            ComponentRecord componentRecord = componentRecordRepository.findById(item.getUniqueID())
                    .orElseThrow(() -> new NoSuchElementException("ComponentRecord not found with ID: " + item.getUniqueID()));
            invoiceContent.setComponentRecord(componentRecord);
        } else {
            ProductRecord productRecord = productRecordRepository.findById(item.getUniqueID())
                    .orElseThrow(() -> new NoSuchElementException("ProductRecord not found with ID: " + item.getUniqueID()));
            invoiceContent.setProductRecord(productRecord);
        }

        invoiceContent.setQty(item.getAmount());
    }


}
