package org.example;

import org.junit.Assert;
import org.junit.Test;

public class CheckoutOrderTotalTest {
    private static final double PRICE_MAX_DELTA = 0.001;
    private final CheckoutOrderTotal checkoutOrderTotal = new CheckoutOrderTotal();

    @Test
    public void canAddSingleItemToInventory() {
        this.checkoutOrderTotal.addItemToInventory("Soup", 1.0);
        Assert.assertEquals(1.0, this.checkoutOrderTotal.getInventoryItemPrice("Soup"), CheckoutOrderTotalTest.PRICE_MAX_DELTA);
    }

    @Test
    public void canAddTwoItemsToInventory() {
        this.checkoutOrderTotal.addItemToInventory("Soup", 1.0);
        this.checkoutOrderTotal.addItemToInventory("Ketchup", 3.0);
        Assert.assertEquals(3.0, this.checkoutOrderTotal.getInventoryItemPrice("Ketchup"), CheckoutOrderTotalTest.PRICE_MAX_DELTA);
    }

    @Test
    public void canScanSingleItem() {
        this.checkoutOrderTotal.addItemToInventory("Soup", 1.0);
        this.checkoutOrderTotal.addItem("Soup");
        Assert.assertEquals(1.0, this.checkoutOrderTotal.computeTotal(), CheckoutOrderTotalTest.PRICE_MAX_DELTA);
    }
}
