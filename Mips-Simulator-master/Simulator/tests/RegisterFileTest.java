/*
 * TCSS 372 – MIPS SIMULATOR 
 */

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Unit test for RegisterFile class.
 * 
 * @author David Saelee, Kyle Bittner, Patrick Moy
 * @version 11/15/2019
 *
 */
public class RegisterFileTest {

	/**
	 * Test to determine if correct register is returned given specified index.
	 * 
	 * @throws Exception invalid input. {@link RegisterFile#RegisterList()}
	 */
	@Test
	public void registerListTest() throws Exception {

		assertEquals("$zero", RegFile.registerList(0));
		assertEquals("$at", RegFile.registerList(1));
		assertEquals("$v0", RegFile.registerList(2));
		assertEquals("$v1", RegFile.registerList(3));
		assertEquals("$a0", RegFile.registerList(4));
		assertEquals("$a1", RegFile.registerList(5));
		assertEquals("$a2", RegFile.registerList(6));
		assertEquals("$a3", RegFile.registerList(7));
		assertEquals("$t0", RegFile.registerList(8));
		assertEquals("$t1", RegFile.registerList(9));
		assertEquals("$t2", RegFile.registerList(10));
		assertEquals("$t3", RegFile.registerList(11));
		assertEquals("$t4", RegFile.registerList(12));
		assertEquals("$t5", RegFile.registerList(13));
		assertEquals("$t6", RegFile.registerList(14));
		assertEquals("$t7", RegFile.registerList(15));
		assertEquals("$s0", RegFile.registerList(16));
		assertEquals("$s1", RegFile.registerList(17));
		assertEquals("$s2", RegFile.registerList(18));
		assertEquals("$s3", RegFile.registerList(19));
		assertEquals("$s4", RegFile.registerList(20));
		assertEquals("$s5", RegFile.registerList(21));
		assertEquals("$s6", RegFile.registerList(22));
		assertEquals("$s7", RegFile.registerList(23));
		assertEquals("$t8", RegFile.registerList(24));
		assertEquals("$t9", RegFile.registerList(25));
		assertEquals("$k0", RegFile.registerList(26));
		assertEquals("$k1", RegFile.registerList(27));
		assertEquals("$gp", RegFile.registerList(28));
		assertEquals("$sp", RegFile.registerList(29));
		assertEquals("$fp", RegFile.registerList(30));
		assertEquals("$ra", RegFile.registerList(31));
	}

	/**
	 * Test to determine if method sets registers correctly.
	 * 
	 * @throws Exception invalid input. {@link RegisterFile#SetRegisters()}
	 */
	@Test
	public void setRegistersTest() throws Exception {

		RegFile regfile = new RegFile();

		regfile.setRegisters(1, 2, 3);

		regfile.setData(1, 5);
		regfile.setData(2, 10);
		regfile.writeToReg(true, 20);

		assertEquals(5, regfile.readReg());
		assertEquals(10, regfile.readReg2());
		assertEquals(20, regfile.getData(3));

	}

	/**
	 * Test to determine if register1 is read correctly.
	 * 
	 * @throws Exception invalid input. {@link RegisterFile#ReadReg()}
	 */
	@Test
	public void readRegTest() throws Exception {

		RegFile regfile = new RegFile();

		regfile.setRegisters(1, 2, 3);
		regfile.setData(1, 49);

		assertEquals(49, regfile.readReg());

	}

	/**
	 * Test to determine if register2 is read correctly.
	 * 
	 * @throws Exception invalid input. {@link RegisterFile#ReadReg2()}
	 */
	@Test
	public void readReg2Test() throws Exception {

		RegFile regfile = new RegFile();

		regfile.setRegisters(1, 2, 3);
		regfile.setData(2, 59);

		assertEquals(59, regfile.readReg2());

	}

	/**
	 * Test to determine if register is written to correctly.
	 * 
	 * @throws Exception invalid input. {@link RegisterFile#WriteToRegister()}
	 */
	@Test
	public void writeToRegTest() throws Exception {

		RegFile regfile = new RegFile();

		regfile.setRegisters(1, 2, 3);
		regfile.writeToReg(true, 69);

		assertEquals(69, regfile.getData(3));

	}

	/**
	 * Test to determine if value in register is returned correctly.
	 * 
	 * @throws Exception invalid exception. {@link RegisterFile#GetData()}
	 */
	@Test
	public void getDataTest() throws Exception {

		RegFile regfile = new RegFile();

		regfile.setRegisters(1, 2, 3);
		regfile.setData(3, 99);

		assertEquals(99, regfile.getData(3));

	}

	/**
	 * Test to determine if data is set in register correctly.
	 * 
	 * @throws Exception invalid input. {@link RegisterFile#SetData()}
	 */
	@Test
	public void setDataTest() throws Exception {

		RegFile regfile = new RegFile();

		regfile.setRegisters(1, 2, 3);
		regfile.setData(1, 55);

		assertEquals(55, regfile.readReg());

	}

}
