package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CheckoutOrderTotal {
    private HashMap<String, InventoryItem> inventoryItems = new HashMap<>();
    private List<ScannedItem> scannedItems = new ArrayList<>();

    public void addItemToInventory(String itemName, double itemPrice) {
        this.inventoryItems.put(itemName, new InventoryItem(itemName, itemPrice));
    }

    public double getInventoryItemPrice(String itemName) {
        return this.inventoryItems.get(itemName).getPrice();
    }

    public void addItem(String itemName) {
        this.addItem(itemName, 1.0);
    }

    public void addItem(String itemName, double quantity) {
        InventoryItem inventoryItem = this.inventoryItems.get(itemName);
        this.scannedItems.add(new ScannedItem(inventoryItem, quantity));
    }

    public double computeTotal() {
        double total = 0.0;

        for (ScannedItem scannedItem : this.scannedItems) {
            total += scannedItem.getQuantity() * scannedItem.getInventoryItem().getPrice();
        }

        return total;
    }
}
