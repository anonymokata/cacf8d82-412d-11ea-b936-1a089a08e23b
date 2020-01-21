package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CheckoutOrderTotal {
    private HashMap<String, InventoryItem> inventoryItems = new HashMap<>();
    private List<ScannedItem> scannedItems = new ArrayList<>();

    public void addItemToInventory(String name, double price) {
        this.inventoryItems.put(name, new InventoryItem(name, price));
    }

    public double getInventoryItemPrice(String name) {
        return this.inventoryItems.get(name).getPrice();
    }

    public void addItemToOrder(String name) {
        this.addItemToOrder(name, 1.0);
    }

    public void addItemToOrder(String name, double quantity) {
        InventoryItem inventoryItem = this.inventoryItems.get(name);
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
