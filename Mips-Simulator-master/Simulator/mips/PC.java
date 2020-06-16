/*
 * TCSS 372 – MIPS SIMULATOR 
 */

/**
 * Keeps track of program counter.
 * 
 * @author David Saelee, Kyle Bittner, Patrick Moy
 * @version 11/15/2019
 *
 */
public class PC {
	
	/**
	 * Stores current program count.
	 */
	private int currentPC;
	
	/**
	 * Creates program counter and initializes to 0.
	 */
	public PC() {
		// start at 0
		currentPC = 0;
	}

	/**
	 * Restarts program counter.
	 * 
	 */
	public void restartPC() {
		currentPC = 0;
	}
	

	/**
	 * Gets current counter value.
	 * 
	 * @return current counter value.
	 */
	public int getPC() {
		return currentPC;
	}
	
	
	public void incrementPC() {
		currentPC++;
	}
	
	/**
	 * Checks value to determine if divisible by 4, sets program counter.
	 * 
	 * @param newPC program counter input.
	 */
	public void set(int newPC) {
		
		// throws assertion exception if newPC mod 4 != 0
		// Added modulus here... unsure what assert really does but given the explanation above it works for test cases now.
		assert newPC % 4  == 0;
		
		this.currentPC = newPC;
	}
}
