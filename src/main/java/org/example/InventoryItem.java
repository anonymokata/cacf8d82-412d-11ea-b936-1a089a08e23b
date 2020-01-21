package org.example;

public class InventoryItem {
    private String name;
    private double price;
    private Special special;

    public InventoryItem(String name, double price) {
        this.setName(name);
        this.setPrice(price);
        this.setSpecial(new SpecialNone());
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

    public Special getSpecial() {
        return this.special;
    }

    public void setSpecial(Special special) {
        this.special = special;
    }
}
