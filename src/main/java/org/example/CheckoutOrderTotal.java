package org.example;

import java.util.HashMap;

public class CheckoutOrderTotal {
    private HashMap<String, Double> itemNamePriceMap = new HashMap<>();

    public void addItemToInventory(String itemName, double itemPrice) {
        this.itemNamePriceMap.put(itemName, itemPrice);
    }

    public double getInventoryItemPrice(String itemName) {
        return this.itemNamePriceMap.get(itemName);
    }
}
