/*
 * TCSS 372 – MIPS SIMULATOR 
 */

/**
 * {@code} Interprets hexadecimal instructions and computes the results of the
 * MIPS code.
 * 
 * @author David Saelee, Kyle Bittner, Patrick Moy
 * @version 11/15/2019
 *
 */
public class InstructDecode {

	/**
	 * Stores the memory index location.
	 */
	MemFile myMemory;
	/**
	 * Stores the register file values.
	 */
	RegFile myRegisters;
	/**
	 * Stores the program counter value.
	 */
	PC myPC;
	/**
	 * Initializes the instruct value to 0.
	 */
	long instruct = 0;
	/**
	 * Stores the value between bits 21 - 25.
	 */
	int rs;
	/**
	 * Stores the value between bits 16 -20.
	 */
	int rt;
	/**
	 * Stores the value between 11-15.
	 */
	int rd;
	/**
	 * Stores the value between 26 - 31.
	 */
	int opcodeCheck;
	/**
	 * Stores the value between 0-5.
	 */
	int functCheck;
	/**
	 * Stores the value of the label.  Composed of the instruct value
	 * and the bits from 0-15.
	 */
	int target;
	/**
	 * Stores the value of i-type determinant.  Composed of the instruct
	 * value and the bits from 12-15. 
	 */
	int iTypeCheck;

	/**
	 * Binary integer that represents bits 0-15.
	 */
	final int mask00to15 = 0b0000_0000_0000_0000_1111_1111_1111_1111;
	/**
	 * Binary integer that represents bits 0-5.
	 */
	final int mask00to05 = 0b0000_0000_0000_0000_0000_0000_0011_1111;
	/**
	 * Binary integer that represents bits 6-10.
	 */
	final int mask06to10 = 0b0000_0000_0000_0000_0000_0111_1100_0000;
	/**
	 * Binary integer that represents bits 11-15.
	 */
	final int mask11to15 = 0b0000_0000_0000_0000_1111_1000_0000_0000;
	/**
	 * Binary integer that represents bits 16-20.
	 */
	final int mask16to20 = 0b0000_0000_0001_1111_0000_0000_0000_0000;
	/**
	 * Binary integer that represents bits 21-25.
	 */
	final int mask21to25 = 0b0000_0011_1110_0000_0000_0000_0000_0000;
	/**
	 * Binary integer that represents bits 26-31.
	 */
	final int mask26to31 = 0b1111_1100_0000_0000_0000_0000_0000_0000;
	/**
	 * Binary integer that represents bits 0-25.
	 */
	final int mask00to25 = 0b0000_0011_1111_1111_1111_1111_1111_1111;
	/**
	 * Binary integer that represents bits 12-15.
	 */
	final int mask12to15 = 0b0000_0000_0000_0000_1111_0000_0000_0000;
	
	final int mask00to11 = 0b0000_0000_0000_0000_0000_1111_1111_1111;

	/**
	 * Constructor to initialize register, memory and counter values.
	 * 
	 * @param theRegisters register array.
	 * @param theMemory	memory file index array.
	 * @param thePC	program counter.
	 */
	public InstructDecode(RegFile theRegisters, MemFile theMemory, PC thePC) {
		myMemory = theMemory;
		myPC = thePC;
		myRegisters = theRegisters;
	}

	/**
	 * Decodes the hexadecimal instructions and performs 
	 * the necessary computation of the instruction. ("SHOULD THIS RETURN A VALUE")?
	 * 
	 * @param theInstruct A string of hexadecimal instructions. 
	 */
	public void decode(long theInstruct) {
		instruct = theInstruct;
		opcodeCheck = (int) ((mask26to31 & instruct) >> 26);
		if (opcodeCheck == 0) {
			functCheck = (int) (mask00to05 & instruct);
			rs = (int) ((mask21to25 & instruct) >> 21);
			rt = (int) ((mask16to20 & instruct) >> 16);
			rd = (int) ((mask11to15 & instruct) >> 11);

			switch (functCheck) {

			case 0x20:
//				System.out.println("add");
				myRegisters.setData(rd, myRegisters.getData(rs) + myRegisters.getData(rt));
				break;

			case 0x21:
//				System.out.println("add");
				myRegisters.setData(rd, myRegisters.getData(rs) + myRegisters.getData(rt));
				break;

			case 0x24:
//				System.out.println("and");
				myRegisters.setData(rd, myRegisters.getData(rs) & myRegisters.getData(rt));
				break;

			case 0x25:
//				System.out.println("or");
				myRegisters.setData(rd, myRegisters.getData(rs) | myRegisters.getData(rt));
				break;
			case 0x8:
//				System.out.println("jr");
				myPC.set((int) myRegisters.getData(rs));
				break;
			default:
				System.err.println("Opcode not found");
			}
		} else {
			rs = (int) ((mask21to25 & instruct) >> 21);
			rt = (int) ((mask16to20 & instruct) >> 16);
			target = (int) ((instruct & mask00to11));
			switch (opcodeCheck) {

			case 0x2: // j
//				System.out.println("j");
				target = (int) ((instruct & mask00to25));
				myPC.set(target);
				break;

			case 0x8: // addi
//				System.out.println("addi");
				myRegisters.setData(rt, (myRegisters.getData(rs) + target));
				break;

			case 0x9: // addiu
//				System.out.println("addiu");
				myRegisters.setData(rt, myRegisters.getData(rs) + target);
				break;

			case 0xc: // andi
//				System.out.println("andi");
				myRegisters.setData(rt, myRegisters.getData(rs) & target);
				break;

			case 0xd: // ori
//				System.out.println("ori");
				myRegisters.setData(rt, myRegisters.getData(rs) | target);
				break;

			case 0x23: // lw // LW ONLY WORKS W/ DIRECT LABEL RN
//				System.out.println("lw");
				iTypeCheck = (int) ((instruct & mask12to15) >> 12);
				if (iTypeCheck == 0xF) {
					myRegisters.setData(rt, myMemory.getData(target));
				} else {
					myRegisters.setData(rt, myMemory.getData(rs));
				}
				break;

			case 0x2b: // sw --- LW AND SW INCOMPLETE FOR POINTER INCREMENTING
//				System.out.println("sw");
				myRegisters.setData(rt, myMemory.getData(rs));
				break;

			case 4: // beq
//				System.out.println("beq");
				if (myRegisters.getData(rs) == myRegisters.getData(rt)) {
					myPC.set(target);
				}

				break;

			case 5: // bne
//				System.out.println("bne");
				if (myRegisters.getData(rs) != myRegisters.getData(rt)) {
					myPC.set(target);
				}
				break;

			}
		}
	}

}
