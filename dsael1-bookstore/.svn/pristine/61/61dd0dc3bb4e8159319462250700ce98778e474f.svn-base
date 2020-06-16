/*
 * TCSS 305 – Autumn 2018
 */

package tests;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import model.Item;
import org.junit.Test;

/**
 * {@code} JUnit code to test constructor and methods for bugs.
 * 
 * @author David Saelee
 * @version 10/14/2018
 *
 */
public class ItemTest {

    /**
     * Test checks if string name of single item is passed correctly. Throws
     * IllegalArgumentException if string is empty.
     * 
     * {@link Item#Item(String, BigDecimal)}
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSingleItemNameConstructorIsNotEmpty() {
        final String thisName = "";
        new Item(thisName, BigDecimal.ZERO);

    }

    /**
     * Test checks if string name of bulk item is passed correctly. Throws
     * IllegalArgumentException if string is empty.
     * 
     * {@link Item#Item(String, BigDecimal, int, BigDecimal)}
     * 
     */
    @Test(expected = IllegalArgumentException.class)
    public void testBulkItemNameConstructorIsNotEmpty() {
        final String thisName = "";
        new Item(thisName, BigDecimal.ZERO, 1, BigDecimal.ZERO);

    }

    /**
     * Test checks if single item price value is not a negative value. Throws
     * IllegalArgumentException if value is less than 0.
     * 
     * {@link Item#Item(String, BigDecimal)}
     */
    @Test(expected = IllegalArgumentException.class)
    public void testItemPriceConstructorNotNegative() {

        final BigDecimal thisPrice = new BigDecimal(-1);
        new Item("myItem", thisPrice);

    }

    /**
     * Test checks if bulk item price value is not a negative value. Throws
     * IllegalArgumentException if value is less than 0.
     * 
     * {@link Item#Item(String, BigDecimal, int, BigDecimal)}
     * 
     */
    @Test(expected = IllegalArgumentException.class)
    public void testBulkItemPriceConstructorNotNegative() {
        final BigDecimal thisPrice = new BigDecimal(-1);
        new Item("myName", thisPrice, 1, BigDecimal.ZERO);

    }

    /**
     * Test checks if bulk quantity value is not a negative value. Throws
     * IllegalArgumentException if value is less than 0.
     * 
     * {@link Item#Item(String, BigDecimal, int, BigDecimal)}
     * 
     * 
     */
    @Test(expected = IllegalArgumentException.class)
    public void testBulkQuantityConstructorNotNegative() {

        final int thisBulkQuantity = -1;
        new Item("myItem", BigDecimal.ZERO, thisBulkQuantity, BigDecimal.ZERO);
    }

    /**
     * Test checks if bulk price value is not a negative value. Throws
     * IllegalArgumentException if value is less than 0.
     * 
     * {@link Item#Item(String, BigDecimal, int, BigDecimal)}
     * 
     * 
     */
    @Test(expected = IllegalArgumentException.class)
    public void testBulkPriceConstructorNotNegative() {

        final BigDecimal thisBulkPrice = new BigDecimal(-1.0);

        new Item("myItem", BigDecimal.ZERO, 1, thisBulkPrice);
    }

    /**
     * Test checks if hashCode are equivalent for two bulk items that have the
     * same name, same price, same quantity, and the same bulk price.
     * assertEquals compares two equal parameter items against their hashCodes.
     * 
     * {@link hashCode#hashCode()}
     */
    @Test
    public void testBulkHashCode() {
        final Item item1 = new Item("ItemName", new BigDecimal(2.25), 
                                    3, new BigDecimal(5));
        final Item item2 = new Item("ItemName", new BigDecimal(2.25), 
                                    3, new BigDecimal(5));
        assertEquals(item1.hashCode(), item2.hashCode());
    }

    /**
     * Test checks if hashCode are not equivalent for two bulk items that have
     * the different parameter values. assertEquals compares two items with
     * different values against their hashCodes.
     * 
     * {@link hashCode#hashCode()}
     */
    @Test
    public void testNotEqualsBulkHashCode() {

        final Item item1 = new Item("ItemName", new BigDecimal(2.25), 
                                    3, new BigDecimal(5));
        final Item item2 = new Item("ItemName", new BigDecimal(2.25), 
                                    4, new BigDecimal(5));

        assertNotEquals(item1.hashCode(), item2.hashCode());
    }
    /**
     * Test checks if hashCode are equivalent for two single items that have the same
     * name, and same price. assertEquals compares two equal parameter 
     * items against their hashCodes.
     * 
     * {@link hashCode#hashCode()}
     */
    @Test
    public void testNonBulkHashCode() {

        final Item item1 = new Item("ItemName", new BigDecimal(2.25));
        final Item item2 = new Item("ItemName", new BigDecimal(2.25));

        assertEquals(item1.hashCode(), item2.hashCode());
    }
    /**
     * Test checks if hashCode are not equivalent for two items that have
     * the different parameter values. assertEquals compares two items with
     * different values against their hashCodes.
     * 
     * {@link hashCode#hashCode()}
     */    
    @Test
    public void testNotEqualsNonBulkHashCode() {

        final Item item1 = new Item("ItemName", new BigDecimal(2.25));
        final Item item2 = new Item("ItemName", new BigDecimal(2.30));

        assertNotEquals(item1.hashCode(), item2.hashCode());
    }
    /**
     * Test determines if boolean true.  assertTrue
     * will compares the call to Item.isBulk() and return true.
     * 
     * {@link isBulk#isBulk()}
     */
    @Test
    public void testTrueIsBulk() {

        assertTrue(new Item("myItem", BigDecimal.ZERO, 1, BigDecimal.ZERO).isBulk());
    }
    /**
     * Test determines if boolean false.  assertFalse
     * will compares the call to Item.isBulk() and return false
     * because the parameters don't fulfill the bulk item parameter
     * requirements.
     * 
     * {@link isBulk#isBulk()}
     */
    @Test
    public void testFalseIsBulk() {

        assertFalse(new Item("myItem", BigDecimal.ZERO).isBulk());
    }
    /**
     * Test checks the nonBulkItem toString output.
     * Compares local expected variables against
     * the variable when passed to the toString method.
     * 
     * {@link toString#toString()}
     * 
     */
    @Test
    public void testMyNonBulkItemsToString() {

        final Item thisNonBulkItem = new Item("myItem", new BigDecimal(10.00));

        assertEquals("myItem, $10.00", thisNonBulkItem.toString());
    }
    /**
     * Test checks the BulkItem toString output.
     * Compares local expected variables against
     * the variable when passed to the toString method.
     * 
     * {@link toString#toString()}
     * 
     */
    @Test
    public void testMyBulkItemsToString() {

        final Item thisBulkItem =
                        new Item("myItem", new BigDecimal(10.00), 10, new BigDecimal(10.00));

        assertEquals("myItem, $10.00 (10 for $10.00)", thisBulkItem.toString());
    }
    /**
     * Test uses assertTrue to compares two single
     * items to confirm their equivalence.
     * 
     * {@link equals#equals(Object)}
     */
    @Test
    public void testNonBulkEqualsObject() {

        final Item item1 = new Item("ItemName", new BigDecimal(2.25));
        final Item item2 = new Item("ItemName", new BigDecimal(2.25));

        assertTrue(item1.equals(item2));
    }
    /**
     * Test uses assertFalse to compares two single 
     * items to confirm their difference.
     * 
     * {@link equals#equals(Object)}
     */
    @Test
    public void testNonBulkNotEqualsObject() {

        final Item item1 = new Item("ItemName", new BigDecimal(2.25));
        final Item item2 = new Item("ItemName", new BigDecimal(2.30));

        assertFalse(item1.equals(item2));
    }
    /**
     * Test uses assertTrue to compares two bulk
     * items to confirm their equivalence.
     * 
     * {@link equals#equals(Object)}
     */
    @Test
    public void testBulkEqualsObject() {

        final Item item1 = new Item("ItemName", new BigDecimal(2.25), 3, new BigDecimal(5));
        final Item item2 = new Item("ItemName", new BigDecimal(2.25), 3, new BigDecimal(5));

        assertTrue(item1.equals(item2));
    }
    /**
     * Test uses assertFalse to compares two bulk
     * items to confirm their difference.
     * 
     * {@link equals#equals(Object)}
     */
    @Test
    public void testBulkNotEqualsObject() {

        final Item item1 = new Item("ItemName", new BigDecimal(2.25), 3, new BigDecimal(5));
        final Item item2 = new Item("ItemName", new BigDecimal(2.25), 4, new BigDecimal(5));

        assertFalse(item1.equals(item2));
    }

}
