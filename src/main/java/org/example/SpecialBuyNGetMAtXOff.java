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
        this.prerequisiteCount = prerequisiteCount;
    }

    public int getSpecialCount() {
        return this.specialCount;
    }

    public void setSpecialCount(int specialCount) {
        this.specialCount = specialCount;
    }

    public double getDiscount() {
        return this.discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public double getSpecialPrice(ScannedItem scannedItem, int scannedItemOrderCount) {
        if ((scannedItemOrderCount - 1) % (this.prerequisiteCount + this.specialCount) >= this.prerequisiteCount) {
            double specialPrice = scannedItem.getInventoryItem().getPrice() * (1.0 - this.discount);
            // Round to the nearest cent
            return Math.round(specialPrice * 100.0) / 100.00;
        } else {
            return scannedItem.getInventoryItem().getPrice();
        }
    }
}
