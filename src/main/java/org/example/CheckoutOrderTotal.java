package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CheckoutOrderTotal {
    private HashMap<String, InventoryItem> inventoryItems = new HashMap<>();
    private List<ScannedItem> scannedItems = new ArrayList<>();

    public void addItemToInventory(String name, double pricePerUnit) {
        InventoryItem.validateName(name);
        InventoryItem.validatePrice(pricePerUnit);
        if (this.inventoryItems.containsKey(name))
            throw new IllegalArgumentException(name + " already exists in the inventory");
        this.inventoryItems.put(name, new InventoryItem(name, pricePerUnit));
    }

    private InventoryItem getInventoryItem(String name) {
        InventoryItem.validateName(name);
        if (!this.inventoryItems.containsKey(name))
            throw new IllegalArgumentException(name + " doesn't exist in the inventory");
        return this.inventoryItems.get(name);
    }

    public double getInventoryItemPrice(String name) {
        return this.getInventoryItem(name).getPricePerUnit();
    }

    public void addMarkdownToInventoryItem(String name, double markdown) {
        InventoryItem inventoryItem = this.getInventoryItem(name);
        if (markdown > inventoryItem.getPricePerUnit())
            throw new IllegalArgumentException("Markdown cannot exceed price");
        inventoryItem.setSpecial(new SpecialMarkdown(markdown));
    }

    public void addBuyNGetMAtXOffSpecial(String name, int prerequisiteCount, int specialCount, double discount, int limit) {
        InventoryItem inventoryItem = this.getInventoryItem(name);
        inventoryItem.setSpecial(new SpecialBuyNGetMAtXOff(prerequisiteCount, specialCount, discount, limit));
    }

    public void addBuyNGetMAtXOffSpecial(String name, int prerequisiteCount, int specialCount, double discount) {
        this.addBuyNGetMAtXOffSpecial(name, prerequisiteCount, specialCount, discount, 0);
    }

    public void addNForXSpecial(String name, int count, double price) {
        InventoryItem inventoryItem = this.getInventoryItem(name);
        inventoryItem.setSpecial(new SpecialBuyNForX(count, price));
    }

    public void addItemToOrder(String name) {
        this.addItemToOrder(name, 1.0);
    }

    public void addItemToOrder(String name, double quantity) {
        if (quantity <= 0.0) throw new IllegalArgumentException("Quantity must be greater than zero");
        InventoryItem inventoryItem = this.getInventoryItem(name);
        this.scannedItems.add(new ScannedItem(inventoryItem, quantity));
    }

    public double computeTotal() {
        double total = 0.0;
        HashMap<InventoryItem, Integer> scannedItemSequenceNumber = new HashMap<>();

        // Go through all scanned items, assign a sequence number for all scanned items that belong to the same
        // inventory item, compute the special, multiply by the quantity, and accumulate the total
        for (ScannedItem scannedItem : this.scannedItems) {
            InventoryItem inventoryItem = scannedItem.getInventoryItem();
            int sequenceNumber = scannedItemSequenceNumber.getOrDefault(inventoryItem, 0) + 1;
            scannedItemSequenceNumber.put(inventoryItem, sequenceNumber);
            double quantity = scannedItem.getQuantity();
            double finalPrice = inventoryItem.getSpecial().computeSpecialPrice(scannedItem, sequenceNumber);
            total += quantity * finalPrice;
        }

        return total;
    }
}
