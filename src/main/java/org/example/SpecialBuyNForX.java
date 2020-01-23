package org.example;

public class SpecialBuyNForX implements Special {
    private int count;
    private double total;
    private double effectivePrice;

    public SpecialBuyNForX(int count, double total) {
        this.setCount(count);
        this.setTotal(total);
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        if (count <= 0) throw new IllegalArgumentException("Count must be greater than zero");
        this.count = count;
        this.computePrice();
    }

    public double getTotal() {
        return this.total;
    }

    public void setTotal(double total) {
        if (total <= 0) throw new IllegalArgumentException("Total must be greater than zero");
        this.total = total;
        this.computePrice();
    }

    private void computePrice() {
        // Round down to the nearest cent. Protect against division by zero that can occur as the object is constructed.
        if (this.total > 0) this.effectivePrice = Math.floor((this.count / this.total) * 100.0) / 100.00;
    }

    @Override
    public double computeSpecialPrice(ScannedItem scannedItem, int scannedItemSequenceNumber) {
        // Simply return the effective price (count / total). Sequence number does't affect this special.
        return this.effectivePrice;
    }
}
