package org.example;

import org.junit.Assert;
import org.junit.Test;

public class CheckoutOrderTotalTest {

    @Test
    public void canAddSingleItemToInventory() {
        CheckoutOrderTotal checkoutOrderTotal = new CheckoutOrderTotal();
        checkoutOrderTotal.addItemToInventory("Soup", 1.0);
        Assert.assertEquals(1.0, checkoutOrderTotal.getInventoryItemPrice("Soup"), 0.001);
    }
}
