package com.atilaBiosystems.InventoryManagementSystem.Service;

import com.atilaBiosystems.InventoryManagementSystem.DAO.CustomerDAO;
import com.atilaBiosystems.InventoryManagementSystem.DAO.InvoiceDAO;
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
                assert componentRecord != null;
                componentRecord.setAmountInStock(item.getCurrentStock() - item.getSellQty());
            } else {
                RawMaterial rawMaterial = rawMaterialsRepository.findById(item.getRecordId()).orElse(null);
                assert rawMaterial != null;
                rawMaterial.setAmountInStock(item.getCurrentStock() - item.getSellQty());
            }
        }
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
    public Invoice findInvoiceById(Integer invoiceID) {
        return invoiceRpository.findById(invoiceID).orElse(null);
    }

    @Override
    public List<InvoiceItemDAO> checkInvoiceDetails(Invoice invoice) {
        if (invoice == null) return new ArrayList<>();
        List<InvoiceItemDAO> res = new ArrayList<>();

        for(InvoiceContent item: invoice.getInvoiceContents()){
            InvoiceItemDAO curr = new InvoiceItemDAO();
            if(item.getMaterialRecord() != null){
                curr.setCategory("r");
                RawMaterial material = item.getMaterialRecord();
                curr.setUniqueID(material.getMaterialId());
                curr.setSKU(material.getCatalogNumber());
                curr.setDescription(material.getDescription());
            } else if (item.getComponentRecord() != null){
                curr.setCategory("c");
                ComponentRecord componentRecord = item.getComponentRecord();
                curr.setUniqueID(componentRecord.getComponentRecordId());
                curr.setSKU(componentRecord.getComponentCatalog());
                curr.setDescription(componentRecord.getLotNumber());
            } else {
                curr.setCategory("p");
                ProductRecord productRecord = item.getProductRecord();
                curr.setUniqueID(productRecord.getProductRecordId());
                curr.setSKU(productRecord.getProductCatalog());
                curr.setDescription(productRecord.getLotNumber());
            }
            curr.setAmount(item.getQty());
            res.add(curr);
        }

        return res;
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
    @Transactional
    public void createNewCustomer(CustomerDAO customerDAO) {
        Customer newCustomer = new Customer();
        newCustomer.setCustomerName(customerDAO.getCustomerName());
        newCustomer.setCompany(customerDAO.getCompany());
        newCustomer.setPhoneNumber(customerDAO.getPhoneNumber());
        newCustomer.setEmailAddress(customerDAO.getEmailAddress());
        newCustomer.setShipAddress(customerDAO.getShippingAddress());

        customerRepository.save(newCustomer);
    }

    @Override
    @Transactional
    public void updateCustomerInfo(Customer customer, CustomerDAO customerDAO) {
        customer.setCustomerName(customerDAO.getCustomerName());
        customer.setCompany(customerDAO.getCompany());
        customer.setPhoneNumber(customerDAO.getPhoneNumber());
        customer.setEmailAddress(customerDAO.getEmailAddress());
        customer.setShipAddress(customerDAO.getShippingAddress());

        customerRepository.save(customer);
    }

    @Override
    @Transactional
    public void deleteCustomer(Customer customer) {
        customerRepository.delete(customer);
    }

    @Override
    public List<InvoiceDAO> findCustomerOrderHistory(Customer customer) {
        List<InvoiceDAO> res = new ArrayList<>();

        for (Invoice invoice: customer.getInvoices()){
            InvoiceDAO curr = new InvoiceDAO();
            curr.setInvoiceID(invoice.getInvoiceId());
            curr.setInvoiceNumber(invoice.getInvoiceNumber());
            curr.setInvoiceDate(invoice.getInvoiceDate());
            curr.setShipDate(invoice.getShipDate());

            res.add(curr);
        }

        return res;
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
    public void updateInvoice(InvoiceDAO invoiceDAO) {
        Invoice invoice = invoiceRpository.findById(invoiceDAO.getInvoiceID()).orElse(new Invoice());

        invoice.setInvoiceNumber(invoiceDAO.getInvoiceNumber());
        invoice.setInvoiceDate(invoiceDAO.getInvoiceDate());
        invoice.setShipDate(invoiceDAO.getShipDate());

        invoiceRpository.save(invoice);
    }

    @Override
    @Transactional
    public void deleteInvoice(Invoice invoice) {
        invoice.setStatus(3);
    }

    @Override
    public InvoiceContent findInvoiceContentById(Integer invoiceContentID) {
        return invoiceContentRepository.findById(invoiceContentID).orElse(new InvoiceContent());
    }

    @Override
    @Transactional
    public void addInvoiceItems(Invoice invoice, InvoiceItemDAO item) {

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

    @Override
    @Transactional
    public void deleteInvoiceContent(InvoiceContent invoiceContent) {
        invoiceContentRepository.delete(invoiceContent);
    }


}
