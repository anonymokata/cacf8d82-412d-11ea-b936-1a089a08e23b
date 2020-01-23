package org.example;

public class SpecialBuyNGetMAtXOff implements Special {
    private int prerequisiteCount;
    private int specialCount;
    private double discount;

    public SpecialBuyNGetMAtXOff(int prerequisiteCount, int specialCount, double specialOffRate) {
        this.setPrerequisiteCount(prerequisiteCount);
        this.setSpecialCount(specialCount);
        this.setDiscount(specialOffRate);
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

    @Override
    public double computeSpecialPrice(ScannedItem scannedItem, int scannedItemSequenceNumber) {
        if ((scannedItemSequenceNumber - 1) % (this.prerequisiteCount + this.specialCount) >= this.prerequisiteCount) {
            double specialPrice = scannedItem.getInventoryItem().getPricePerUnit() * (1.0 - this.discount);
            // Round down to the nearest cent
            return Math.floor(specialPrice * 100.0) / 100.00;
        } else {
            return scannedItem.getInventoryItem().getPricePerUnit();
        }
    }
}
