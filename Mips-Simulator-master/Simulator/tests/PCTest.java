/*
 * TCSS 372 – MIPS SIMULATOR 
 */

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit test for PC class.
 * 
 * @author David Saelee, Kyle Bittner, Patrick Moy
 * @version 11/15/2019
 *
 */
public class PCTest {

	/**
	 * Test to determine if restart method sets counter back to 0.
	 * 
	 * @throws Exception invalid input.
	 * {@link PC#RestartPC()}
	 */
	@Test
	public void restartPCTest() throws Exception {

		PC counter = new PC();
		counter.set(4);
		assertEquals(4, counter.getPC());
		counter.restartPC();
		assertEquals(0, counter.getPC());

	}

	/**
	 * Test to determine if method returns correct value.
	 * 
	 * @throws Exception invalid input.
	 * {@link PC#GetPC()}
	 */
	@Test
	public void getPCTest() throws Exception {

		PC counter = new PC();
		counter.set(44);
		assertEquals(44, counter.getPC());

	}
	/**
	 * Test to determine if method sets correct counter value.
	 * 
	 * @throws Exception invalid input.
	 * {@link PC#SetPC()}
	 */
	@Test
	public void setPCTest() throws Exception {

		PC counter = new PC();
		counter.set(44);
		assertEquals(44, counter.getPC());
		//DO test for not divisible by 4 in different test method.
		

	}

}
