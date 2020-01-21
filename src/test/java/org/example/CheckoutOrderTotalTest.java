package org.example;

import org.junit.Assert;
import org.junit.Test;

public class CheckoutOrderTotalTest {
    private static final double PRICE_MAX_DELTA = 0.001;
    private final CheckoutOrderTotal checkoutOrderTotal = new CheckoutOrderTotal();

    @Test
    public void canAddMultipleItemsToInventory() {
        this.checkoutOrderTotal.addItemToInventory("Soup", 1.00);
        this.checkoutOrderTotal.addItemToInventory("Ketchup", 3.00);
        Assert.assertEquals(3.00, this.checkoutOrderTotal.getInventoryItemPrice("Ketchup"), CheckoutOrderTotalTest.PRICE_MAX_DELTA);
    }

    @Test
    public void canScanMultipleItems() {
        this.checkoutOrderTotal.addItemToInventory("Soup", 1.00);
        this.checkoutOrderTotal.addItemToInventory("Ketchup", 3.00);
        this.checkoutOrderTotal.addItemToOrder("Soup");
        this.checkoutOrderTotal.addItemToOrder("Ketchup");
        this.checkoutOrderTotal.addItemToOrder("Soup");
        Assert.assertEquals(5.00, this.checkoutOrderTotal.computeTotal(), CheckoutOrderTotalTest.PRICE_MAX_DELTA);
    }

    @Test
    public void canScanMultipleByWeightItem() {
        this.checkoutOrderTotal.addItemToInventory("Ground Beef", 4.50);
        this.checkoutOrderTotal.addItemToInventory("Bananas", 0.60);
        this.checkoutOrderTotal.addItemToOrder("Ground Beef", 1.5);
        this.checkoutOrderTotal.addItemToOrder("Bananas", 1.2);
        Assert.assertEquals(7.47, this.checkoutOrderTotal.computeTotal(), CheckoutOrderTotalTest.PRICE_MAX_DELTA);
    }

    @Test
    public void canAddMarkdown() {
        this.checkoutOrderTotal.addItemToInventory("Soup", 1.00);
        this.checkoutOrderTotal.addMarkdownToInventoryItem("Soup", 0.20);
        this.checkoutOrderTotal.addItemToOrder("Soup");
        Assert.assertEquals(0.80, this.checkoutOrderTotal.computeTotal(), CheckoutOrderTotalTest.PRICE_MAX_DELTA);
    }
}
