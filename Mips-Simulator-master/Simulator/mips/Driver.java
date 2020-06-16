
/*
 * TCSS 372 – MIPS SIMULATOR 
 */

/**
 * {@code} Driver class used to pass parsed object to GUI.
 * 
 * @author David Saelee, Kyle Bittner, Patrick Moy
 * @version 11/15/2019
 *
 */

public class Driver {

	MemFile myMem;
	PC myPC;
	RegFile myReg;

	/**
	 * Constructor that takes no arguments. Originally intended to initialize
	 * simulator.
	 */
	public Driver() {

		myMem = new MemFile();
		myPC = new PC();
		myReg = new RegFile();

	}

	/**
	 * Run method that passes parsed instruction object to the GUI.
	 * 
	 * @param theInput A string of assembly instructions.
	 * @return A string of hex instructions.
	 */
	public String run(String theInput) {
		if (theInput != null) {
			StringBuffer result = new StringBuffer();
			result.append("Registers: " + "\t\n");
			AsmParser parse = new AsmParser(this.myMem);
			String parsedString = parse.run(theInput);
//			for (int i = 0; i < myMem.getSize(); i++) {
//				System.out.println(myMem.getData(i));
//			}

			for (int i = 0; i < 32; i++) {
				result.append(RegFile.registerList(i) + "\t");
				result.append(this.myReg.getData(i) + "\n");
			}
			result.append("\n");
			
			InstructDecode fetchExecute = new InstructDecode(this.myReg, this.myMem, this.myPC);
			while (myPC.getPC() < 1599) {
				long instruction = this.myMem.getData(myPC.getPC());
				if (instruction != 0) {
					fetchExecute.decode(instruction);
				}
				myPC.incrementPC();
			}			

			result.append(parsedString);
			
			result.append("Registers: " + "\t\n");

			for (int i = 0; i < 32; i++) {
				result.append(RegFile.registerList(i) + "\t");
				result.append(this.myReg.getData(i) + "\n");

			}

			result.append("\n");
			return result.toString();
			
		} else {
			throw new IllegalArgumentException("");
			// TODO: Probably some sort of error message about null file
		}
	}

}
