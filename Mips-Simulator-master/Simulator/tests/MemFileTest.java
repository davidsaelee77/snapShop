/*
 * TCSS 372 – MIPS SIMULATOR 
 */

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit test for MemFile class.
 * 
 * @author David Saelee, Kyle Bittner, Patrick Moy
 * @version 11/15/2019
 *
 */
public class MemFileTest {

	/**
	 * Test to determine if method sets data value in index correctly.
	 * 
	 * @throws Exception invalid input. {@link MemFile#MemorySetWrite()}
	 */
	@Test
	public void memoryWriteSetMemoryTest() throws Exception {

		MemFile memory = new MemFile();
		long returnVal = memory.memoryWrite(44, 55, false, true);
		assertEquals(55, memory.getData(44));
		assertEquals(0, returnVal);

	}

	/**
	 * Test to determine if method gets data value in index correctly.
	 * 
	 * @throws Exception {@link MemFile#MemoryGetWrite()}
	 */
	@Test
	public void memoryWriteGetMemoryTest() throws Exception {

		MemFile memory = new MemFile();
		memory.setData(40, 105);
		long returnVal = memory.memoryWrite(40, 105, true, false);
		assertEquals(105, memory.getData(40));
		assertEquals(105, returnVal);

	}

}
