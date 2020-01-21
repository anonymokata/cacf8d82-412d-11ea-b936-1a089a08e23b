package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CheckoutOrderTotal {
    private HashMap<String, InventoryItem> inventoryItems = new HashMap<>();
    private List<ScannedItem> scannedItems = new ArrayList<>();

    public void addItemToInventory(String name, double price) {
        if (name == null) throw new IllegalArgumentException("Name cannot be empty");
        if (name.trim().length() == 0) throw new IllegalArgumentException("Name cannot be empty");
        if (this.inventoryItems.containsKey(name))
            throw new IllegalArgumentException(name + " already exists in the inventory");
        this.inventoryItems.put(name, new InventoryItem(name, price));
    }

    private InventoryItem getInventoryItem(String name) {
        if (name == null) throw new IllegalArgumentException("Name cannot be empty");
        if (name.trim().length() == 0) throw new IllegalArgumentException("Name cannot be empty");
        if (!this.inventoryItems.containsKey(name))
            throw new IllegalArgumentException(name + " doesn't exist in the inventory");
        return this.inventoryItems.get(name);
    }

    public double getInventoryItemPrice(String name) {
        return this.getInventoryItem(name).getPrice();
    }

    public void addMarkdownToInventoryItem(String name, double markdown) {
        if (name == null) throw new IllegalArgumentException("Name cannot be empty");
        if (name.trim().length() == 0) throw new IllegalArgumentException("Name cannot be empty");
        InventoryItem inventoryItem = this.inventoryItems.get(name);
        if (markdown > inventoryItem.getPrice()) throw new IllegalArgumentException("Markdown cannot exceed price");
        inventoryItem.setSpecial(new SpecialMarkdown(markdown));
    }

    public void addBuyNGetMAtXOff(String name, int prerequisiteCount, int specialCount, double discount) {
        if (name == null) throw new IllegalArgumentException("Name cannot be empty");
        if (name.trim().length() == 0) throw new IllegalArgumentException("Name cannot be empty");
        InventoryItem inventoryItem = this.inventoryItems.get(name);
        if ((discount > 1.0) || (discount < 0.0))
            throw new IllegalArgumentException("Discount must be between 0% to 100%");
        inventoryItem.setSpecial(new SpecialBuyNGetMAtXOff(prerequisiteCount, specialCount, discount));
    }

    public void addItemToOrder(String name) {
        this.addItemToOrder(name, 1.0);
    }

    public void addItemToOrder(String name, double quantity) {
        if (name == null) throw new IllegalArgumentException("Name cannot be empty");
        if (name.trim().length() == 0) throw new IllegalArgumentException("Name cannot be empty");
        if (quantity <= 0) throw new IllegalArgumentException("Quantity cannot be zero or negative");
        InventoryItem inventoryItem = this.getInventoryItem(name);
        this.scannedItems.add(new ScannedItem(inventoryItem, quantity));
    }

    public double computeTotal() {
        double total = 0.0;
        HashMap<InventoryItem, Integer> itemCount = new HashMap<>();

        for (ScannedItem scannedItem : this.scannedItems) {
            InventoryItem inventoryItem = scannedItem.getInventoryItem();
            itemCount.put(inventoryItem, itemCount.getOrDefault(inventoryItem, 0) + 1);
            double quantity = scannedItem.getQuantity();
            double finalPrice = inventoryItem.getSpecial().getSpecialPrice(scannedItem, itemCount.get(inventoryItem));
            total += quantity * finalPrice;
        }

        return total;
    }
}
