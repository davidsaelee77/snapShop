
/*
 * TCSS 372 – MIPS SIMULATOR 
 */
import java.util.*;

/**
 * {@code} Abstract data class to store index and data of instructions.
 * 
 * @author David Saelee, Kyle Bittner, Patrick Moy
 * @version 11/15/2019
 *
 */

public abstract class AbstractData {

	/**
	 * Stores array of instructions.
	 */
	private long[] dataArray;

	/**
	 * Stores list of updated instructions.
	 */
	private List<Long> update;

	/**
	 * AbstractData type constructor. Creates array and list and initializes array
	 * size.
	 * 
	 * @param dataSize array size.
	 */
	public AbstractData(int dataSize) {

		dataArray = new long[dataSize];

		update = new LinkedList<Long>();

		clear();
	}

	/**
	 * Clone data array.
	 * 
	 * @return Cloned data array.
	 */
	public long[] dataClone() {

		return dataArray.clone();

	}

	/**
	 * Get updated data.
	 * 
	 * @return Updated data.
	 */
	public List<Long> update() {

		return new LinkedList<Long>(update);

	}

	/**
	 * Resets array elements of entire array back to 0.
	 */
	public void clear() {

		for (int i = 0; i < dataArray.length; i++) {

			dataArray[i] = 0;

		}

	}

	/**
	 * Clear update list at index.
	 * 
	 * @param index array index.
	 */
	private void delete(long index) {

		if (update.contains(index))

			update.remove(update.indexOf(index));

		update.add(0, index);

	}

	/**
	 * Gets the value in array index.
	 * 
	 * @param index at this index
	 * @return the data within the index.
	 */
	long getData(int ind) {

		return dataArray[ind];

	}

	/**
	 * Sets data.
	 * 
	 * @param index of data.
	 * @param value of data.
	 */
	void setData(int ind, long val) {

		delete(ind);

		dataArray[ind] = val;

	}
	
	/**
	 * Returns the size of the array.
	 * 
	 * @return length of array.
	 */
	int getSize() {
		return dataArray.length;
	}

}
