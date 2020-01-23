package org.example;

public class SpecialNone implements Special {
    @Override
    public double computeSpecialPrice(ScannedItem scannedItem, int scannedItemSequenceNumber) {
        // No special. Return the per-unit price
        return scannedItem.getInventoryItem().getPricePerUnit();
    }
}
