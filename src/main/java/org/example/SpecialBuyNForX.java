package org.example;

public class SpecialBuyNForX implements Special {
    private int count;
    private double total;
    private double price;

    public SpecialBuyNForX(int count, double total) {
        this.setCount(count);
        this.setTotal(total);
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
        this.computePrice();
    }

    public double getTotal() {
        return this.total;
    }

    public void setTotal(double total) {
        this.total = total;
        this.computePrice();
    }

    private void computePrice() {
        // Round to the nearest cent
        if (this.total > 0) this.price = Math.round((this.count / this.total) * 100.0) / 100.00;
    }

    @Override
    public double getSpecialPrice(ScannedItem scannedItem, int scannedItemOrderCount) {
        return this.price;
    }
}
