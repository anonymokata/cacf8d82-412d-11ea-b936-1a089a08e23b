package org.example;

public class SpecialNone implements Special {
    @Override
    public double computeSpecialPrice(InventoryItem inventoryItem, double totalQuantity) {
        // No special. Return the per-unit price.
        return totalQuantity * inventoryItem.getPrice();
    }
}
