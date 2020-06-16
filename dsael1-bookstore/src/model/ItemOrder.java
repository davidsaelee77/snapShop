/*
 * TCSS 305 – Autumn 2018
 */

package model;

/**
 * ItemOrder class compiles the item and quantity and stores the information.
 * 
 * @author David Saelee
 * @version 10/13/2018
 *
 */
public final class ItemOrder {
    /**
     * Declaration of fields.
     */
    /**
     * Data stored for item.
     */
    private Item myItem;
    /**
     * The amount of items ordered.
     */
    private int myQuantity;

    /**
     * Constructor that builds the ItemOrder and populates 
     * the variable with an item and quantity.
     * 
     * @param theItem is the data stored for item.
     * @param theQuantity is the amount of items ordered.
     * @throws IllegalArgumentExpcetion if value is less than 0.
     * @throws NullPointerException if the value of the theItem is null. 
     */
    public ItemOrder(final Item theItem, final int theQuantity) {
        
        if (theQuantity < 0) {
            throw new IllegalArgumentException("Can not have a quantity less than 0.");
        }
        if (theItem == null) {
            throw new NullPointerException("Can not have a null value");
        }
        myItem = theItem;
        myQuantity = theQuantity;
        
    }

    /**
     * Returns the item.
     * 
     * @return getItem returns the item information.
     */
    public Item getItem() {
        
        return myItem;
    }
    /**
     * Returns the quantity of the item.
     * 
     * @return getQuantity returns the quantity of the item.
     */
    public int getQuantity() {
        
        return myQuantity;
    }

    /**
     * Returns a text representation of 
     * the quantity and item ordered.
     * 
     * @return quantity and item text representation.
     */
    @Override
    public String toString() {

        return "Quantity: " + myQuantity + " Item Order: " + myItem;
    }

}
