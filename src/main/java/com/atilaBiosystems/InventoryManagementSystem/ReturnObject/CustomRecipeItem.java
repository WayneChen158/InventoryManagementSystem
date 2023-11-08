package com.atilaBiosystems.InventoryManagementSystem.ReturnObject;

public class CustomRecipeItem {

    public int id;
    public String itemName;
    public Double volPerRxn;
    public Double vol;
    public boolean hasEnoughInStock;
    public CustomRecipeItem(int id, String itemName, Double volPerRxn, Double vol, boolean hasEnoughInStock){
        this.id = id;
        this.itemName = itemName;
        this.volPerRxn = volPerRxn;
        this.vol = vol;
        this.hasEnoughInStock = hasEnoughInStock;
    }
}
