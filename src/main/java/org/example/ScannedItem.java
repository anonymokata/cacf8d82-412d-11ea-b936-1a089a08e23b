package org.example;

public class ScannedItem {
    private InventoryItem inventoryItem;
    private double quantity;

    public ScannedItem(InventoryItem inventoryItem, double quantity) {
        this.setInventoryItem(inventoryItem);
        this.setQuantity(quantity);
    }

    public InventoryItem getInventoryItem() {
        return this.inventoryItem;
    }

    public void setInventoryItem(InventoryItem inventoryItem) {
        if (inventoryItem == null) throw new IllegalArgumentException("Inventory item cannot be empty");
        this.inventoryItem = inventoryItem;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
