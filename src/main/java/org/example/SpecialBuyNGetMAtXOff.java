package org.example;

public class SpecialBuyNGetMAtXOff implements Special {
    private int prerequisiteCount;
    private int specialCount;
    private double discount;
    private int limit;

    public SpecialBuyNGetMAtXOff(int prerequisiteCount, int specialCount, double specialOffRate, int limit) {
        this.setPrerequisiteCount(prerequisiteCount);
        this.setSpecialCount(specialCount);
        this.setDiscount(specialOffRate);
        this.setLimit(limit);
    }

    public int getPrerequisiteCount() {
        return this.prerequisiteCount;
    }

    public void setPrerequisiteCount(int prerequisiteCount) {
        if (prerequisiteCount <= 0) throw new IllegalArgumentException("Prerequisite count must be greater than zero");
        this.prerequisiteCount = prerequisiteCount;
    }

    public int getSpecialCount() {
        return this.specialCount;
    }

    public void setSpecialCount(int specialCount) {
        if (specialCount <= 0) throw new IllegalArgumentException("Special count must be greater than zero");
        this.specialCount = specialCount;
    }

    public double getDiscount() {
        return this.discount;
    }

    public void setDiscount(double discount) {
        if ((discount <= 0) || (discount > 1.00))
            throw new IllegalArgumentException("Discount must be greater than 0% and less or equal to 100%");
        this.discount = discount;
    }

    public int getLimit() {
        return this.limit;
    }

    public void setLimit(int limit) {
        if (limit < 0) throw new IllegalArgumentException("Limit must be greater or equal to zero");
        this.limit = limit;
    }

    @Override
    public double computeSpecialPrice(ScannedItem scannedItem, int scannedItemSequenceNumber) {
        if ((scannedItemSequenceNumber > this.limit) && (this.limit > 0)) {
            // Once limit is exceed, full per-unit price is used.
            // Limit equal is zero means there is no limit
            return scannedItem.getInventoryItem().getPricePerUnit();
        } else if ((scannedItemSequenceNumber - 1) % (this.prerequisiteCount + this.specialCount) >= this.prerequisiteCount) {
            double specialPrice = scannedItem.getInventoryItem().getPricePerUnit() * (1.0 - this.discount);
            // Round down to the nearest cent
            return Math.floor(specialPrice * 100.0) / 100.00;
        } else {
            return scannedItem.getInventoryItem().getPricePerUnit();
        }
    }
}
