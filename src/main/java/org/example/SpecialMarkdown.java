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
        if (markdown <= 0) throw new IllegalArgumentException("Markdown must be greater than zero");
        this.markdown = markdown;
    }

    @Override
    public double computeSpecialPrice(InventoryItem inventoryItem, double totalQuantity) {
        // All items are discounted by markdown.
        return totalQuantity * (inventoryItem.getPrice() - this.markdown);
    }
}
