package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CheckoutOrderTotal {
    private HashMap<String, Double> itemNamePriceMap = new HashMap<>();
    private List<String> scannedItems = new ArrayList<>();

    public void addItemToInventory(String itemName, double itemPrice) {
        this.itemNamePriceMap.put(itemName, itemPrice);
    }

    public double getInventoryItemPrice(String itemName) {
        return this.itemNamePriceMap.get(itemName);
    }

    public void addItem(String itemName) {
        this.scannedItems.add(itemName);
    }

    public double computeTotal() {
        double total = 0.0;

        for (String scannedItem : this.scannedItems) {
            total += this.itemNamePriceMap.get(scannedItem);
        }

        return total;
    }
}
