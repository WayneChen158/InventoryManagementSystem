package com.atilaBiosystems.InventoryManagementSystem.ReturnObject;

public class CustomRecipeItem {
    public String itemName;
    public Double volPerRxn;
    public Double vol;
    public boolean hasEnoughInStock;
    public CustomRecipeItem(String itemName, Double volPerRxn, Double vol, boolean hasEnoughInStock){
        this.itemName = itemName;
        this.volPerRxn = volPerRxn;
        this.vol = vol;
        this.hasEnoughInStock = hasEnoughInStock;
    }
}
