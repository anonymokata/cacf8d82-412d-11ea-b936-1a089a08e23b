package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckoutOrderTotal {
    private HashMap<String, InventoryItem> inventoryItems = new HashMap<>();
    private List<ScannedItem> scannedItems = new ArrayList<>();
    private HashMap<InventoryItem, Double> scannedItemTotalQuantityMap = new HashMap<>();

    public void addItemToInventory(String name, double price, boolean soldByWeight) {
        InventoryItem.validateName(name);
        InventoryItem.validatePrice(price);
        if (this.inventoryItems.containsKey(name))
            throw new IllegalArgumentException(name + " already exists in the inventory");
        this.inventoryItems.put(name, new InventoryItem(name, price, soldByWeight));
    }

    public void addItemToInventory(String name, double price) {
        this.addItemToInventory(name, price, false);
    }

    private InventoryItem getInventoryItem(String name) {
        InventoryItem.validateName(name);
        if (!this.inventoryItems.containsKey(name))
            throw new IllegalArgumentException(name + " doesn't exist in the inventory");
        return this.inventoryItems.get(name);
    }

    public double getInventoryItemPrice(String name) {
        return this.getInventoryItem(name).getPrice();
    }

    public void addMarkdownToInventoryItem(String name, double markdown) {
        InventoryItem inventoryItem = this.getInventoryItem(name);
        if (markdown > inventoryItem.getPrice())
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

    public void addNForXSpecial(String name, int count, double price, int limit) {
        InventoryItem inventoryItem = this.getInventoryItem(name);
        inventoryItem.setSpecial(new SpecialBuyNForX(count, price, limit));
    }

    public void addNForXSpecial(String name, int count, double price) {
        this.addNForXSpecial(name, count, price, 0);
    }

    public void addItemToOrder(String name, double quantity) {
        if (quantity <= 0.0) throw new IllegalArgumentException("Quantity must be greater than zero");
        InventoryItem inventoryItem = this.getInventoryItem(name);
        if ((!inventoryItem.isSoldByWeight()) && ((quantity % 1.0) != 0))
            throw new IllegalArgumentException("Pre-unit items must have discrete quantities");
        ScannedItem scannedItem = new ScannedItem(inventoryItem, quantity);
        this.scannedItems.add(scannedItem);
        double totalQuantity = this.scannedItemTotalQuantityMap.getOrDefault(scannedItem.getInventoryItem(), 0.0) +
                scannedItem.getQuantity();
        this.scannedItemTotalQuantityMap.put(inventoryItem, totalQuantity);
    }

    public void addItemToOrder(String name) {
        this.addItemToOrder(name, 1.0);
    }

    public void removeItemFromOrder(int itemNumber) {
        // Item number starts at 1, convert that to index
        int itemIndex = itemNumber - 1;
        if (itemIndex < 0) throw new IllegalArgumentException("Item number must be greater than 1");
        if (itemIndex >= this.scannedItems.size())
            throw new IllegalArgumentException("Item number is not in the checkout order");
        ScannedItem scannedItem = this.scannedItems.get(itemIndex);
        double totalQuantity = this.scannedItemTotalQuantityMap.get(scannedItem.getInventoryItem()) -
                scannedItem.getQuantity();
        this.scannedItems.remove(itemIndex);
        if (totalQuantity > 0.0) {
            this.scannedItemTotalQuantityMap.put(scannedItem.getInventoryItem(), totalQuantity);
        } else {
            this.scannedItemTotalQuantityMap.remove(scannedItem.getInventoryItem());
        }
    }

    public double computeTotal() {
        double total = 0.0;

        // Go through all scanned items, assign a sequence number for all scanned items that belong to the same
        // inventory item, compute the special, multiply by the quantity, and accumulate the total
        for (Map.Entry<InventoryItem, Double> entry : this.scannedItemTotalQuantityMap.entrySet()) {
            InventoryItem inventoryItem = entry.getKey();
            Double totalQuantity = entry.getValue();
            total += inventoryItem.getSpecial().computeSpecialPrice(inventoryItem, totalQuantity);
        }

        return total;
    }
}
