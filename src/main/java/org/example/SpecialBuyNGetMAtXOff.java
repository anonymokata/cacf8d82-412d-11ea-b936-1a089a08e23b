package org.example;

public class SpecialBuyNGetMAtXOff implements Special {
    private double prerequisiteCount;
    private double specialCount;
    private double discount;
    private double limit;

    public SpecialBuyNGetMAtXOff(double prerequisiteCount, double specialCount, double specialOffRate, double limit) {
        this.setPrerequisiteCount(prerequisiteCount);
        this.setSpecialCount(specialCount);
        this.setDiscount(specialOffRate);
        this.setLimit(limit);
    }

    public double getPrerequisiteCount() {
        return this.prerequisiteCount;
    }

    public void setPrerequisiteCount(double prerequisiteCount) {
        if (prerequisiteCount <= 0.0)
            throw new IllegalArgumentException("Prerequisite count must be greater than zero");
        this.prerequisiteCount = prerequisiteCount;
    }

    public double getSpecialCount() {
        return this.specialCount;
    }

    public void setSpecialCount(double specialCount) {
        if (specialCount <= 0.0) throw new IllegalArgumentException("Special count must be greater than zero");
        this.specialCount = specialCount;
    }

    public double getDiscount() {
        return this.discount;
    }

    public void setDiscount(double discount) {
        if ((discount <= 0.0) || (discount > 1.00))
            throw new IllegalArgumentException("Discount must be greater than 0% and less or equal to 100%");
        this.discount = discount;
    }

    public double getLimit() {
        return this.limit;
    }

    public void setLimit(double limit) {
        if (limit < 0.0) throw new IllegalArgumentException("Limit must be greater or equal to zero");
        this.limit = limit;
    }

    @Override
    public double computeSpecialPrice(InventoryItem inventoryItem, double totalQuantity) {
        double limitedQuantity = (this.limit > 0.0) ? Math.min(totalQuantity, this.limit) : totalQuantity;
        double discountedQuantity;
        if (inventoryItem.isSoldByWeight()) {
            discountedQuantity = (limitedQuantity / (this.prerequisiteCount + this.specialCount)) * this.specialCount;
        } else {
            discountedQuantity = ((int) (limitedQuantity / (this.prerequisiteCount + this.specialCount))) * this.specialCount;
            discountedQuantity += Math.max(0, (limitedQuantity % (this.prerequisiteCount + this.specialCount)) - this.prerequisiteCount);
        }
        double fullPriceQuantity = totalQuantity - discountedQuantity;
        double specialPrice = (fullPriceQuantity * inventoryItem.getPrice()) +
                (discountedQuantity * inventoryItem.getPrice() * (1.0 - this.discount));
        // Round down to the nearest cent
        specialPrice = Math.floor(specialPrice * 100.0) / 100.00;
        return specialPrice;
    }
}
