package org.example;

public class ScannedItem {
    private InventoryItem inventoryItem;
    private double quantity;

    public ScannedItem(InventoryItem inventoryItem, double quantity) {
        this.inventoryItem = inventoryItem;
        this.quantity = quantity;
    }

    public InventoryItem getInventoryItem() {
        return inventoryItem;
    }

    public void setInventoryItem(InventoryItem inventoryItem) {
        this.inventoryItem = inventoryItem;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
