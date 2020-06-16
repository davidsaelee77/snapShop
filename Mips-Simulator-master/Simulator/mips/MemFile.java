/*
 * TCSS 372 – MIPS SIMULATOR 
 */

/**
 * {@code} Reads or writes data to memory.
 * 
 * @author David Saelee, Kyle Bittner, Patrick Moy
 * @version 11/15/2019
 *
 */
public class MemFile extends AbstractData {
	
	public MemFile() {
		// initial memory size
		super(2000);
	}
	
	/**
	 * Read and write to memory
	 * 
	 * @param address of memory location
	 * @param data for memory
	 * @param readMem read to memory location true = write
	 * @param writeMem write to memory location true = write
	 * @return 0
	 */
	public long memoryWrite(int address, int data, boolean readMem,
			boolean writeMem) {
		
		if(writeMem) {
			setData(address, data);
		}

		if(readMem) {
			return getData(address);
		}

		return 0;
	}
	
	public void writeMemory(int address, int data) {
		setData(address, data);
	}
	
	public long readMemory(int address) {
		return getData(address);
	}
}
