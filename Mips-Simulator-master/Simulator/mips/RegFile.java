/*
 * TCSS 372 – MIPS SIMULATOR 
 */

/**
 * Register file to update all registers.
 * 
 * @author David Saelee, Kyle Bittner, Patrick Moy
 * @version 11/15/2019
 *
 */
public class RegFile extends AbstractData {
	/**
	 * Stores register input.
	 */
	private int registerInput;
	/**
	 * Stores register input.
	 */
	private int registerInput2;
	/**
	 * Stores register input.
	 */
	private int regWrite;

	/**
	 * Register file constructor.  Creates an array of 32 registers.
	 * 
	 */
	public RegFile() {
		super(32);
	}
	
	/**
	 * Gets register at specific index. 
	 * 
	 * @param index of register
	 * @return register at specific index.
	 */
	public static String registerList(int index) {
		
		  String[] registers = { "$zero", "$at", "$v0", "$v1", "$a0", "$a1", "$a2",
		  "$a3", "$t0", "$t1", "$t2", "$t3", "$t4", "$t5", "$t6", "$t7", "$s0", "$s1",
		  "$s2", "$s3", "$s4", "$s5", "$s6", "$s7", "$t8", "$t9", "$k0", "$k1", "$gp",
		  "$sp", "$fp", "$ra" }; 
		 
		return registers[index];
	}
	
	/**
	 * Sets register value.
	 * 
	 * @param in1 register input.
	 * @param in2 register input.
	 * @param wout register input.
	 */
	public void setRegisters(int in1, int in2, int wout) {
		registerInput = in1;
		registerInput2 = in2;
		regWrite = wout;
	}
	
	/**
	 * Get data from register1.
	 * 
	 * @return data from register1.
	 */
	public long readReg() {
		return getData(registerInput);
	}
	
	/**
	 * Get data from register2.
	 * 
	 * @return data from register2.
	 */
	public long readReg2() {
		return getData(registerInput2);
	}
	
	/** Write to register.
	 * 
	 * @param RegWrite flag true = write.
	 * @param data register data input. 
	 */
	public void writeToReg(boolean RegWrite, long data) {
		if(RegWrite) {
			setData(regWrite, data);
		}
	}

	/**
	 * Retrieves data from array index.
	 * 
	 * @return data from index.
	 */
	@Override
	protected long getData(int index) {
		
		if(index == 0)
			
			return 0; 
		
		else
			
		return super.getData(index);
		
	}

	/**
	 * Sets input data at specified index.
	 * 
	 */
	@Override
	protected void setData(int index, long data) {
		
		if(index == 0)
			
			return;
		
		else
			
		super.setData(index, data);
	}

	
}
