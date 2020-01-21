package org.example;

public class InventoryItem {
    private String name;
    private double price;
    private double markdown;

    public InventoryItem(String name, double price) {
        this.setName(name);
        this.setPrice(price);
        this.setMarkdown(0.00);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name == null) throw new IllegalArgumentException("Name cannot be empty");
        if (name.trim().length() == 0) throw new IllegalArgumentException("Name cannot be empty");
        this.name = name.trim();
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("Price cannot be less than 0");
        this.price = price;
    }

    public double getMarkdown() {
        return this.markdown;
    }

    public void setMarkdown(double markdown) {
        if (markdown > price) throw new IllegalArgumentException("Markdown cannot exceed price");
        this.markdown = markdown;
    }
}
