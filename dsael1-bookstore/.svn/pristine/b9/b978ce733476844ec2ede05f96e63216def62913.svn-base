/*
 * TCSS 305 – Autumn 2018
 */
package tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import model.Item;
import model.ItemOrder;
import org.junit.Test;


/**
 * {@code} JUnit code to test constructor and methods for bugs.
 * 
 * @author David Saelee
 * @version 10/14/2018
 *
 */
public class ItemOrderTest {

    /**
     * Test checks negative quantity when creating 
     * a new ItemOrder. Throws IllegalArgumentException.
     * 
     * {@link ItemOrder#ItemOrder(Item, int) }
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNegativeQuantityItemOrder() {
        final int thisQuantity = -1;
        new ItemOrder(new Item("myItem", BigDecimal.ZERO), thisQuantity);
    }
    /**
     * Test checks null value when creating new ItemOrder.
     * Throws NullPointerException.
     * 
     * {@link ItemOrder#ItemOrder(Item, int)}
     */
    @Test(expected = NullPointerException.class)
    public void testNullItemOrder() {
        new ItemOrder(null, 0);
    }
    /**
     * Test checks the toString output.
     * Compares local expected variables against
     * the variable when passed to the toString method.
     * 
     * {@link toString#toString()}
     */
    @Test
    public void testToString() {    
        final ItemOrder newOrder = new ItemOrder(new Item("myItem", BigDecimal.ZERO), 10);
        assertEquals("Quantity: 10 Item Order: myItem, $0.00", newOrder.toString());
    }

}
