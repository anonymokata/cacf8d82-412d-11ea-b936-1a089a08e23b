package org.example;

import org.junit.Assert;
import org.junit.Test;

public class CheckoutOrderTotalTest {
    private static final double PRICE_MAX_DELTA = 0.001;
    private final CheckoutOrderTotal checkoutOrderTotal = new CheckoutOrderTotal();

    @Test
    public void canAddMultipleItemsToInventory() {
        this.checkoutOrderTotal.addItemToInventory("Soup", 1.0);
        this.checkoutOrderTotal.addItemToInventory("Ketchup", 3.0);
        Assert.assertEquals(3.0, this.checkoutOrderTotal.getInventoryItemPrice("Ketchup"), CheckoutOrderTotalTest.PRICE_MAX_DELTA);
    }

    @Test
    public void canScanMultipleItems() {
        this.checkoutOrderTotal.addItemToInventory("Soup", 1.0);
        this.checkoutOrderTotal.addItemToInventory("Ketchup", 3.0);
        this.checkoutOrderTotal.addItem("Soup");
        this.checkoutOrderTotal.addItem("Ketchup");
        this.checkoutOrderTotal.addItem("Soup");
        Assert.assertEquals(5.0, this.checkoutOrderTotal.computeTotal(), CheckoutOrderTotalTest.PRICE_MAX_DELTA);
    }

    @Test
    public void canScanSingleByWeightItem() {
        this.checkoutOrderTotal.addItemToInventory("Ground Beef", 4.5);
        this.checkoutOrderTotal.addItem("Ground Beef", 1.5);
        Assert.assertEquals(6.75, this.checkoutOrderTotal.computeTotal(), CheckoutOrderTotalTest.PRICE_MAX_DELTA);
    }
}
