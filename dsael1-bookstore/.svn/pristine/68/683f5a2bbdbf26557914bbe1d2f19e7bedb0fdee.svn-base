/*
 * TCSS 305 – Autumn 2018
 */
package tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import model.Cart;
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
public class CartTest {
    /**
     * Test instantiates an empty cart and checks if collection is not null.
     * 
     * Test method for {@link Cart#Cart()}.
     */
    @Test
    public void testConstructorCreatesNewCart() {
        final Cart c = new Cart();
        assertNotNull(c);
    }
    /**
     * Test throws NullPointerException if null value
     * is passed for ItemOrder.
     * 
     * {@link Cart#Cart(ItemOrder)}
     */
    @Test(expected = NullPointerException.class)
    public void testItemOrderAddNullValue() {
        final ItemOrder thisOrder = null;
        final Cart newCart = new Cart();
        newCart.add(thisOrder);
    }
    /**
     * Test determines if calculateTotal method is
     * functioning properly by passing in 
     * an empty list.  No calculations takes place
     * because of empty list so it returns a 0. 
     * assertEquals compares an expected test case 
     * to the calculatedTotal method.
     * 
     * {@link caluculateTotal#calculateTotal()}
     */
    @Test
    public void testCalculateTotalOnEmptyList() {
        final BigDecimal zero = BigDecimal.ZERO.setScale(2);
        final Cart newCart = new Cart();
        assertEquals(zero, newCart.calculateTotal());
    }
    /**
     * Test calculates a nonBulkItem order.  Instantiate
     * a new ItemOrder and compares expected value
     * to the calculate total value output.
     * 
     * {@link #aluculateTotal#calculateTotal()}
     */
    @Test
    public void testCaluculateTotalNonBulkItem() {

        final Cart newCart = new Cart();
        newCart.add(new ItemOrder(new Item("myName", new BigDecimal(10)), 1));
        assertEquals(new BigDecimal(10).setScale(2), newCart.calculateTotal());

    }
    /**
     * Test calculates a BulkItem order without and with membership.  
     * Instantiate a new ItemOrder and compares expected value
     * to the calculate total value output.  Set membership to true
     * and compare the output after setting membership.
     * 
     * {@link #aluculateTotal#calculateTotal()}
     */

    @Test
    public void testCalculateTotalBulkItemWithMembership() {

        final Item newBulkItem = new Item("myName", new BigDecimal(10), 2, new BigDecimal(15));
        final Cart newCart = new Cart();
        newCart.add(new ItemOrder(newBulkItem, 2));
        assertEquals(new BigDecimal(20).setScale(2), newCart.calculateTotal());
        newCart.setMembership(true);
        assertEquals(new BigDecimal(15).setScale(2), newCart.calculateTotal());
    }
    /**
     * Test calculates the total quantity of items in shopping cart
     * after a change has been made.  Instantiates an empty
     * cart then add an ItemOrder.  assertEquals statement to compare
     * the values.  Add another quantity (I chose 0) to cart.
     * Use assertEquals to compare toString statements to determine changes. 
     * 
     * {@link caluculateTotal#calculateTotal()}
     */
    @Test
    public void testCalculateTotalWhenQuantityHasBeenChanged() {

        final Item newBulkItem = new Item("myName", new BigDecimal(10), 2, new BigDecimal(15));
        final Cart newCart = new Cart();
        newCart.add(new ItemOrder(newBulkItem, 2));
        assertEquals(new BigDecimal(20).setScale(2), newCart.calculateTotal());
        newCart.add(new ItemOrder(newBulkItem, 0));
        assertEquals(BigDecimal.ZERO.setScale(2), newCart.calculateTotal());
        assertEquals("Total Cost: 0.00 My Shopping Cart: {myName, $10.00 (2 for $15.00)="
                        + "Quantity: 0 Item Order: myName, $10.00 (2 for $15.00)}",
                     newCart.toString());

    }
    /**
     * Test checks the total quantity of items in shopping cart
     * Instantiates an empty cart then add an ItemOrder.  
     * assertEquals statement to compare the toString values.  
     * Use calculateTotal to determine if values are the same.
     * Call the .clear method() to empty the collection.
     * Use assertEquals to compare toString statements to determine changes. 
     * 
     * {@link clear#clear()}
     */
    @Test
    public void testClear() {

        final Item newBulkItem = new Item("myName", new BigDecimal(10), 2, new BigDecimal(15));
        final Cart newCart = new Cart();
        newCart.add(new ItemOrder(newBulkItem, 2));
        assertEquals("Total Cost: 20.00 My Shopping Cart: {myName, $10.00 (2 for $15.00)="
                        + "Quantity: 2 Item Order: myName, $10.00 (2 for $15.00)}",
                     newCart.toString());
        assertEquals(new BigDecimal(20).setScale(2), newCart.calculateTotal());
        newCart.clear();
        assertEquals("Total Cost: 0.00 My Shopping Cart: {}", newCart.toString());

    }

}
