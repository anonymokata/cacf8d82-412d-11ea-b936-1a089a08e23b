package org.example;

public class SpecialNone implements Special {

    @Override
    public double getSpecialPrice(ScannedItem scannedItem, int scannedItemOrderCount) {
        // No special.
        return scannedItem.getInventoryItem().getPrice();
    }
}
