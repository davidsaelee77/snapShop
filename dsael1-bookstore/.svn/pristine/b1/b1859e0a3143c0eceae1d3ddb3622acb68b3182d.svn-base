/*
 * TCSS 305 – Autumn 2018
 */

package model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

/**
 * Shopping cart application used to calculate the items entered into the
 * provided bookstore GUI.
 * 
 * @author David Saelee
 * @version 10/13/2018
 *
 */
public final class Item {

    /**
     * Declaration of fields.
     */

    /**
     * The name of the item.
     */
    private String myName;
    /**
     * The single price amount.
     */
    private BigDecimal myPrice;
    /**
     * The bulk quantity amount.
     */
    private int myBulkQuantity;
    /**
     * The bulk price amount.
     */
    private BigDecimal myBulkPrice;
    /**
     * Check to determine if bulk pricing is available.
     */
    private boolean myBulkAvailable;
    /**
     * Formats numbers into currency.
     */
    private NumberFormat myFormat = NumberFormat.getCurrencyInstance(Locale.US);

    /**
     * Constructor that is passed 2 parameters and sets them to their respective
     * variables. Use boolean to check if bulk options are available.
     * 
     * @param theName assigns the item name to the field variable.
     * @param thePrice assigns the price value to the field variable.
     * @throws IllegalArgumentException if name is empty.
     * @throws IllegalArgumentException if price is less than 0.
     * 
     */

    public Item(final String theName, final BigDecimal thePrice) {

        if (theName.isEmpty()) {
            throw new IllegalArgumentException("No name is given");
        }
        final BigDecimal zero = BigDecimal.ZERO;

        if (thePrice.compareTo(zero) < 0) {
            throw new IllegalArgumentException("Invalid number.  The number must be negative");
        }
        myName = theName;
        myPrice = thePrice;
        myBulkAvailable = false;

    }

    /**
     * Constructor that is passed 4 parameters and sets them to their respective
     * variables. Use boolean to check if bulk options are available.
     * 
     * @param theName assigns the item name to the field variable.
     * @param thePrice assigns the price value to the field variable.
     * @param theBulkQuantity assigns the bulk quantity value to the field
     *            variable.
     * @param theBulkPrice assigns the bulk price to the field variable.
     * @throws IllegalArgumentException if item name is not given.
     * @throws IllegalArgumentException if the price is less than 0.
     * @throws IllegalArgumentException if the bulkQuantity is less than 0.
     * @throws IllegalArugementException if the bulkPrice is less than 0.
     */

    public Item(final String theName, final BigDecimal thePrice, final int theBulkQuantity,
                final BigDecimal theBulkPrice) {

        if (theName.isEmpty()) {
            throw new IllegalArgumentException("Empty item name given.");
        }
        final BigDecimal zero = new BigDecimal(0);

        if ((thePrice.compareTo(zero) < 0) || (theBulkQuantity < 0) 
                        || (theBulkPrice.compareTo(zero) < 0)) {
            throw new IllegalArgumentException("Can not have negative value");
        }

        myName = theName;
        myPrice = thePrice;
        myBulkQuantity = theBulkQuantity;
        myBulkPrice = theBulkPrice;
        myBulkAvailable = true;

    }

    /**
     * Returns the price of the item.
     * 
     * @return getPrice() returns the price of the item.
     */
    public BigDecimal getPrice() {

        return myPrice;
    }

    /**
     * Returns the quantity of the bulk items.
     * 
     * @return getBulkQuantity returns the quantity of the bulk items.
     */
    public int getBulkQuantity() {

        return myBulkQuantity;
    }

    /**
     * Returns the bulk price of the items.
     * 
     * @return getBulkPrice() returns the bulk price of the items.
     */
    public BigDecimal getBulkPrice() {

        return myBulkPrice;
    }

    /**
     * Determines if bulk option is available.
     * 
     * @return isBulk returns true or false depending on bulk option
     *         availability.
     */
    public boolean isBulk() {

        return myBulkAvailable;

    }
    /**
     * Displays the item name followed by a , then space, 
     * and the price formatted to currency values.
     * if statement checks bulk availability and if 
     * bulk options are available; will append bulkQuantity and bulkPrice.
     * 
     * 
     */
    @Override
    public String toString() {
        //Used repeat variable to clean up code and to avoid checkStyle warning.
        final String repeat = myName + ", " + myFormat.format(myPrice);
        
        if (isBulk()) {

            final StringBuilder addBulkPrice = new StringBuilder();
            addBulkPrice.append(" (" + myBulkQuantity + " for " + myFormat.format(myBulkPrice)
                                + ")");

            return repeat + addBulkPrice;
        }

        return repeat;
    }
    /**
     * Boolean method to compare out provided parameter
     * values to this Objects values to determine equivalence.
     * If all values are similar than we can conclude with
     * certainty they are the same item.  Test bulk items
     * against regular items.
     *  
     * 
     * @param theOther object to compare.
     * @return true if objects are equal, false otherwise.
     */
    @Override
    public boolean equals(final Object theOther) {
        final Item thisOther = (Item) theOther;
        if (isBulk()) {
            return myBulkQuantity == thisOther.myBulkQuantity
                   && myBulkPrice.equals(thisOther.myBulkPrice)
                   && myName.equals(thisOther.myName);
        } else {
            return myName.equals(thisOther.myName) && myPrice.equals(thisOther.myPrice);
        }
    }
    /**
     * Generate specific hashCode for our given parameters.
     * 
     * @return the calculated hashCode for object.
     */
    @Override
    public int hashCode() {

        final int hashCode;
        if (isBulk()) {
            hashCode = Objects.hash(myName, myPrice, myBulkQuantity, myBulkPrice);
        } else {
            hashCode = Objects.hash(myName, myPrice);
        }
        return hashCode;
    }

}
