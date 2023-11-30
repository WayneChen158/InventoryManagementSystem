package com.atilaBiosystems.InventoryManagementSystem.Service;

import com.atilaBiosystems.InventoryManagementSystem.DAO.SellItemDAO;

import java.util.List;

public interface SellService {

    void sellCurrentInvoice(List<SellItemDAO> itemList);
}
