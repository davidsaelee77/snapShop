/*
 * TCSS 372 – MIPS SIMULATOR 
 */

import java.util.*;

/**
 * {@code} This class formats the .data label of the instruction type before
 * adding to the hash map.
 * 
 * @author David Saelee, Kyle Bittner, Patrick Moy
 * @version 11/15/2019
 *
 */
public class DataImport {

	/**
	 * Stores and initializes instruction address index to 0.
	 */
	int inst1 = 0;
	/**
	 * Stores a string of the instruction type.
	 */
	String type = "";
	/**
	 * Stores a string of the data type.
	 */
	String data = "";

	/**
	 * Method takes a assembly line in the .data field and 
	 * parses the instruction into a hexadecimal format.
	 * 
	 * @param theLine A string of assembly code.
	 */
	public DataImport(String theLine) {
		StringTokenizer tokens = new StringTokenizer(theLine, " \t',");
		type = tokens.nextToken();
		switch (type) {

		case ".word":
			inst1 = Integer.parseInt(tokens.nextToken());
			data = formatAddress(inst1);
			break;
		}
	}

	/**
	 * Formats the .word value into a hexadecimal representation.
	 * 
	 * @param theAddress converted integer representing .word.
	 * @return A hexadecimal representation of the parsed label in .data.
	 */
	public static String formatAddress(int theAddress) {
		return "0x" + String.format("%08X", theAddress);
	}
	/**
	 * Returns the value of data.
	 * 
	 * @return string data value.
	 */
	public String getDataValue() {
		return data;
	}

	/**
	 * Returns the value of the instruction type.
	 * 
	 * @return int instruction value.
	 */
	public int getInst1() {
		return inst1;
	}

}
