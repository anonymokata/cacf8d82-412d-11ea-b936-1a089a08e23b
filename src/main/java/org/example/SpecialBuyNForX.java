package org.example;

public class SpecialBuyNForX implements Special {
    private int count;
    private double total;
    private int limit;
    private double effectivePrice;

    public SpecialBuyNForX(int count, double total, int limit) {
        this.setCount(count);
        this.setTotal(total);
        this.setLimit(limit);
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        if (count <= 0) throw new IllegalArgumentException("Count must be greater than zero");
        this.count = count;
        this.computeEffectivePrice();
    }

    public double getTotal() {
        return this.total;
    }

    public void setTotal(double total) {
        if (total <= 0) throw new IllegalArgumentException("Total must be greater than zero");
        this.total = total;
        this.computeEffectivePrice();
    }

    public int getLimit() {
        return this.limit;
    }

    public void setLimit(int limit) {
        if (limit < 0) throw new IllegalArgumentException("Limit must be greater or equal to zero");
        this.limit = limit;
    }

    private void computeEffectivePrice() {
        // Round down to the nearest cent. Protect against division by zero that can occur as the object is constructed.
        if (this.total > 0) this.effectivePrice = Math.floor((this.count / this.total) * 100.0) / 100.00;
    }

    @Override
    public double computeSpecialPrice(InventoryItem inventoryItem, double totalQuantity) {
        double discountedQuantity = (this.limit > 0) ? Math.min(totalQuantity, this.limit) : totalQuantity;
        double fullPriceQuantity = totalQuantity - discountedQuantity;
        double specialPrice = (fullPriceQuantity * inventoryItem.getPrice()) +
                (discountedQuantity * this.effectivePrice);
        // Round down to the nearest cent
        specialPrice = Math.floor(specialPrice * 100.0) / 100.00;
        return specialPrice;
    }
}
