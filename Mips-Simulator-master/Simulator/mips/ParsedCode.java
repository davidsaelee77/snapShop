/*
 * TCSS 372 – MIPS SIMULATOR 
 */

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.StringTokenizer;


/**
 * {@code} Parses instructions to be executed by instruction decode.
 * 
 * @author David Saelee, Kyle Bittner, Patrick Moy
 * @version 11/15/2019
 *
 */
public class ParsedCode {

	
	static LinkedHashMap<ParsedCode, String> noLabelAddress = new LinkedHashMap<ParsedCode, String>();

	private String instruct, iFormat = "";

	private byte opcode = 0, funct = 0, rd = 0, rs = 0, rt = 0;

	private int address = 0, imm = 0;

	private boolean r_type = false, j_type = false, i_type = false;

	public ParsedCode(String line, HashMap<String, Integer> labels) {

		StringTokenizer tokens = new StringTokenizer(line, " ()\t,");

		String op = "", inst1 = "", inst2 = "", inst3 = "";

		op = tokens.nextToken();

		if (tokens.countTokens() == 1) {
			inst1 = tokens.nextToken();
		}

		if (tokens.countTokens() == 2) {
			inst1 = tokens.nextToken();

			inst2 = tokens.nextToken();
		}

		if (tokens.countTokens() == 3) {
			inst1 = tokens.nextToken();

			inst2 = tokens.nextToken();

			inst3 = tokens.nextToken();
		}

		switch (op.toLowerCase()) {

		case "add":
			funct = 0x20;
			r_type = true;
			break;

		case "addu":
			funct = 0x21;
			r_type = true;

		case "and":
			funct = 0x24;
			r_type = true;
			break;

		case "or":
			funct = 0x25;
			r_type = true;
			break;

		case "jr":
			funct = 0x8;
			r_type = true;
			break;

		case "j":
			opcode = 0x2;
			j_type = true;
			break;

		case "addi":
			i_type = true;
			opcode = 0x8;
			iFormat = "tsi";
			break;

		case "addiu":
			i_type = true;
			opcode = 0x9;
			iFormat = "tsi";
			break;

		case "andi":
			opcode = 0xC;
			i_type = true;
			iFormat = "tsi";
			break;

		case "ori":
			opcode = 0xD;
			i_type = true;
			iFormat = "tsi";
			break;

		case "lw":
			opcode = 0x23;
			i_type = true;
			iFormat = "tisL";
			break;

		case "sw":
			opcode = 0x2b;
			i_type = true;
			iFormat = "tisL";
			break;

		case "beq":
			opcode = 4;
			i_type = true;
			iFormat = "sti";
			break;

		case "bne":
			opcode = 5;
			i_type = true;
			iFormat = "sti";
			break;

		}

		if (j_type) {
			if (labels.containsKey(inst1)) {
				address = (opcode << 26) + (labels.get(inst1));

				instruct = formatAddress(address);
			} else {
				address = (opcode << 26) + (0xFFFF);
				instruct = formatAddress(address);
				noLabelAddress.put(this, inst1);

			}
		} else if (r_type) {

			if (op.equals("jr")) {
				rs = parseReg(inst1);
				instruct = formatAddress((rs << 21) + funct);

			} else {
				rd = parseReg(inst1);

				rs = parseReg(inst2);

				rt = parseReg(inst3);

				instruct = formatAddress(funct + (rs << 21) + (rt << 16) + (rd << 11));
			}

		} else if (i_type) {

			switch (iFormat) {

			case "tsi":
				rt = parseReg(inst1);
				rs = parseReg(inst2);
				imm = Integer.parseInt(inst3);
				instruct = formatAddress((opcode << 26) + (rs << 21) + (rt << 16) + (imm));
				break;

			case "sti":
				rs = parseReg(inst1);
				rt = parseReg(inst2);
				if (labels.containsKey(inst3)) {
					imm = labels.get(inst3);
					instruct = formatAddress((opcode << 26) + (rs << 21) + (rt << 16) + imm);
				} else {
					labels.put(inst3, -1);
					imm = 0xFFFF;
					instruct = formatAddress((opcode << 26) + (rs << 21) + (rt << 16) + imm);
					noLabelAddress.put(this, inst3);
				}

				break;
			case "tisL":
				rt = parseReg(inst1);
				if (!(inst3.equals(""))) {
					imm = parseReg(inst2);
					rs = parseReg(inst3);
					instruct = formatAddress((opcode << 26) + (rs << 21) + (rt << 16) + imm);

				} else {
					imm = labels.get(inst2);
					instruct = formatAddress((opcode << 26) + (0b11111 << 21) + (rt << 16) + (0xF << 12) + imm);
					// Using bits 6-7 (since our addresses are small, the immediate will never use
					// those bits.
					// Indicates that it's a direct address label.
				}
				break;
			}
		}
	}

	private byte parseReg(String reg) {
		byte regNum = 0;
		if (reg.charAt(0) == '$') {

			if (reg.equalsIgnoreCase("$zero")) {

				regNum = 0;

			} else if (reg.equalsIgnoreCase("$gp")) {

				regNum = 28;

			} else if (reg.equalsIgnoreCase("$sp")) {

				regNum = 29;

			} else if (reg.equalsIgnoreCase("$fp")) {

				regNum = 30;

			} else if (reg.equalsIgnoreCase("$ra")) {

				regNum = 31;

			} else {

				char letter = reg.charAt(1);

				byte regInd = Byte.parseByte(reg.substring(2));

				switch (letter) {

				case 'v':
					regInd += 2;
					break;

				case 'a':
					regInd += 4;
					break;

				case 't':
					regInd += 8;
					if (regInd >= 16) {

						regInd += 8;
					}
					break;

				case 's':
					regInd += 16;
					break;

				default:
					throw new IllegalArgumentException("Invalid register!" + reg);

				}

			//	assert reg.equals(RegFile.registerList(regInd));

				regNum = regInd;

			}

		}

		return regNum;
	}

	public String formatAddress(int theAddress) {
		return "0x" + String.format("%08x", theAddress);
	}

	public String getInstruct() {
		return instruct;
	}

	public short getOpcode() {
		return opcode;
	}

	public short getFunct() {
		return funct;
	}

	public short getRd() {
		return rd;
	}

	public short getRs() {
		return rs;
	}

	public short getRt() {
		return rt;
	}

	public int getImm() {
		return imm;
	}

	public int getAddress() {
		return address;
	}

	public boolean isR_type() {
		return r_type;
	}

	public boolean isJ_type() {
		return j_type;
	}

	public boolean isI_type() {
		return i_type;
	}
}
