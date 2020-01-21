package org.example;

public class SpecialMarkdown implements Special {
    private double markdown;

    public SpecialMarkdown(double markdown) {
        this.setMarkdown(markdown);
    }

    public double getMarkdown() {
        return this.markdown;
    }

    public void setMarkdown(double markdown) {
        this.markdown = markdown;
    }

    @Override
    public double getSpecialPrice(ScannedItem scannedItem, int scannedItemOrderCount) {
        // Count doesn't apply to this discount. All items are discounted.
        return scannedItem.getInventoryItem().getPrice() - this.markdown;
    }
}
