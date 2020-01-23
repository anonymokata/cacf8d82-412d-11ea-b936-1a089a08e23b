package org.example;

public class InventoryItem {
    private String name;
    private double pricePerUnit;
    private Special special;

    public InventoryItem(String name, double pricePerUnit) {
        this.setName(name);
        this.setPricePerUnit(pricePerUnit);
        this.setSpecial(new SpecialNone());
    }

    public static void validateName(String name) {
        if (name == null) throw new IllegalArgumentException("Name cannot be empty");
        if (name.trim().length() == 0) throw new IllegalArgumentException("Name cannot be empty");
    }

    public static void validatePrice(double pricePerUnit) {
        if (pricePerUnit < 0) throw new IllegalArgumentException("Price per-unit must be greater than zero");
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        InventoryItem.validateName(name);
        this.name = name.trim();
    }

    public double getPricePerUnit() {
        return this.pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        if (pricePerUnit < 0) throw new IllegalArgumentException("Price cannot be less than 0");
        this.pricePerUnit = pricePerUnit;
    }

    public Special getSpecial() {
        return this.special;
    }

    public void setSpecial(Special special) {
        this.special = special;
    }
}
