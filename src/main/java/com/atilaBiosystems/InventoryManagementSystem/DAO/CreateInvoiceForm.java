package com.atilaBiosystems.InventoryManagementSystem.DAO;

import java.util.List;

public class CreateInvoiceForm {

    private InvoiceDAO invoiceDAO;
    private List<InvoiceItemDAO> invoiceItemDAOs;

    public CreateInvoiceForm(){}

    public CreateInvoiceForm(InvoiceDAO invoiceDAO, List<InvoiceItemDAO> invoiceItemDAOs) {
        this.invoiceDAO = invoiceDAO;
        this.invoiceItemDAOs = invoiceItemDAOs;
    }

    public InvoiceDAO getInvoiceDAO() {
        return invoiceDAO;
    }

    public void setInvoiceDAO(InvoiceDAO invoiceDAO) {
        this.invoiceDAO = invoiceDAO;
    }

    public List<InvoiceItemDAO> getInvoiceItemDAOs() {
        return invoiceItemDAOs;
    }

    public void setInvoiceItemDAOs(List<InvoiceItemDAO> invoiceItemDAOs) {
        this.invoiceItemDAOs = invoiceItemDAOs;
    }
}
