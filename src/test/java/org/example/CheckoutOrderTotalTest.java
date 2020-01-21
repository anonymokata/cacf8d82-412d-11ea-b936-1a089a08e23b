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
        this.checkoutOrderTotal.addItemToInventory("Ketchup", 3.00);
        this.checkoutOrderTotal.addItemToInventory("Mayo", 5.00);
        this.checkoutOrderTotal.addMarkdownToInventoryItem("Soup", 0.20);
        this.checkoutOrderTotal.addMarkdownToInventoryItem("Ketchup", 0.90);
        this.checkoutOrderTotal.addItemToOrder("Soup");
        this.checkoutOrderTotal.addItemToOrder("Ketchup");
        this.checkoutOrderTotal.addItemToOrder("Mayo");
        this.checkoutOrderTotal.addItemToOrder("Soup");
        Assert.assertEquals(8.70, this.checkoutOrderTotal.computeTotal(), CheckoutOrderTotalTest.PRICE_MAX_DELTA);
    }

    @Test
    public void canAddBuyThreeGetTwoAt30PercentOff() {
        this.checkoutOrderTotal.addItemToInventory("Soup", 1.00);
        this.checkoutOrderTotal.addBuyNGetMAtXOff("Soup", 3, 2, 0.30);
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 1.00, total 1.00
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 1.00, total 2.00
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 1.00, total 3.00
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 0.70, total 3.70
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 0.70, total 4.40
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 1.00, total 5.40
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 1.00, total 6.40
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 1.00, total 7.40
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 0.70, total 8.10
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 0.70, total 8.80
        this.checkoutOrderTotal.addItemToOrder("Soup"); // Item price 1.00, total 9.80
        Assert.assertEquals(9.80, this.checkoutOrderTotal.computeTotal(), CheckoutOrderTotalTest.PRICE_MAX_DELTA);
    }
}
