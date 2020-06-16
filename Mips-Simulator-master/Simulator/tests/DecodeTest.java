/*
 * TCSS 372 – MIPS SIMULATOR 
 */

import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * {@code} Unit test for decode class.
 * 
 * @author David Saelee, Kyle Bittner, Patrick Moy
 * @version 11/15/2019
 *
 */
public class DecodeTest {

	/**
	 * Test to determine if add parses registers correctly.
	 * 
	 * @throws Exception invalid input.
	 * 
	 * {@link Decode#Add()}
	 */
	@Test
	public void add() throws Exception {
		ParsedCode inst = new ParsedCode("add $v0, $v0, $v0", null);
		assertEquals("rd", 2, inst.getRd());
		assertEquals("rs", 2, inst.getRs());
		assertEquals("rt", 2, inst.getRt());
	}

	/**
	 * Test to determine if addu parses registers correctly.
	 * 
	 * @throws Exception invalid input.
	 * 
	 * {@link Decode#Addu()}
	 */
	@Test
	public void addu() throws Exception {
		ParsedCode inst = new ParsedCode("addu $v0, $v0, $v0", null);
		assertEquals("rd", 2, inst.getRd());
		assertEquals("rs", 2, inst.getRs());
		assertEquals("rt", 2, inst.getRt());
	}

	/**
	 * Test to determine if and parses registers correctly.
	 * 
	 * @throws Exception invalid input.
	 * 
	 * {@link Decode#And()}
	 */
	@Test
	public void and() throws Exception {
		ParsedCode inst = new ParsedCode("and $v0, $v0, $v0", null);
		assertEquals("rd", 2, inst.getRd());
		assertEquals("rs", 2, inst.getRs());
		assertEquals("rt", 2, inst.getRt());
	}

	/**
	 * Test to determine if or parses registers correctly.
	 * 
	 * @throws Exception invalid input.
	 * 
	 * {@link Decode#Or()}
	 */
	@Test
	public void or() throws Exception {
		ParsedCode inst = new ParsedCode("or $v0, $v0, $v0", null);
		assertEquals("rd", 2, inst.getRd());
		assertEquals("rs", 2, inst.getRs());
		assertEquals("rt", 2, inst.getRt());
	}


}
