
/*
 * TCSS 372 – MIPS SIMULATOR 
 */
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Class used to parse assembly language into hexadecimal.
 * 
 * @author David Saelee, Kyle Bittner, Patrick Moy
 * @version 11/15/2019
 *
 */
public class AsmParser {

	/**
	 * Stores a string list of the .data values.
	 * 
	 */
	List<String> data;
	/**
	 * Stores a string list of the .text values.
	 * 
	 */
	List<String> text;
	/**
	 * Stores a string of the current label.
	 * 
	 */
	private String currentLabel;
	/**
	 * Stores a boolean of the next line label.
	 * 
	 */
	private boolean nextLineLabel;
	/**
	 * Stores a string and integer hash map of all labels.
	 */
	private LinkedHashMap<String, Integer> myLabels;
	/**
	 * Stores a memory address index.
	 */
	private MemFile myMem;

	/**
	 * Constructor used to initialize fields and simulator components.
	 */
	public AsmParser(MemFile myMem) {
		data = new ArrayList<String>();
		text = new ArrayList<String>();
		currentLabel = null;
		nextLineLabel = false;
		myLabels = new LinkedHashMap<String, Integer>();
		this.myMem = myMem;
	}

	/**
	 * Takes a string of assembly language, converts into hexadecimal and stores
	 * representation in memory location.
	 * 
	 * @param theInput A string of assembly instructions.
	 * @param myMem    A memory location index.
	 */
	public void parseAssembly(String theInput, MemFile myMem) {

		BufferedReader inputReader;
		boolean readingData = false;
		boolean readingText = false;

		int currentLine = 1;

		try {
			inputReader = new BufferedReader(new StringReader(theInput));

			String nextLine = inputReader.readLine();
			while (nextLine != null) {
				// System.out.println(nextLine); // For testing purposes
				String finalLine = stripComments(nextLine).trim();

				if (!finalLine.isEmpty()) {
					if (finalLine.equalsIgnoreCase(".data")) {
						readingData = true;
						readingText = false;

					} else if (finalLine.equalsIgnoreCase(".text")) {
						readingText = true;
						readingData = false;

					} else if (readingData && !readingText) {
						if (finalLine.indexOf(':') != -1) {
							finalLine = parseLabel(finalLine);
							if (!nextLineLabel) {

								String dataEntry = new DataImport(finalLine).getDataValue();
								data.add(dataEntry);
								myLabels.put(currentLabel, data.size() + 1599);
								currentLabel = null;
							}
						} else {
							if (nextLineLabel) {
								String dataEntry = new DataImport(finalLine).getDataValue();
								data.add(dataEntry);
								myLabels.put(currentLabel, data.size() + 1599);
								currentLabel = null;
							} else { // This branch is effectively never used for .data
								System.err.println("Error - Data not labeled!");
							}
						}

					} else if (readingText && !readingData) {
						if (finalLine.indexOf(':') != -1) {
							finalLine = parseLabel(finalLine);
							if (!nextLineLabel) {
								text.add(textToBinary(finalLine));
								myLabels.put(currentLabel, text.size());
								currentLabel = null;
							}

						} else {
							text.add(textToBinary(finalLine));
							if (nextLineLabel) {
								myLabels.put(currentLabel, text.size());
								currentLabel = null;
							}
						}
					} else if ((!(readingData ^ readingText))) {
						System.err.println("Parser encountered issue at line " + currentLine);
						System.exit(-1);
					}
				}
				currentLine++;

				nextLine = inputReader.readLine();

			}

			inputReader.close();
		}

		catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	/**
	 * Takes a string from assembly instructions and strips all comments.
	 * 
	 * @param theLine A string of assembly language.
	 * @return An empty string.
	 */
	private String stripComments(String theLine) {
		String strippedLine = theLine;
		int charIndex = theLine.indexOf('#');
		if (charIndex > -1) {
			if (theLine.charAt(0) != '#') {
				strippedLine = theLine.substring(0, charIndex);
			} else {
				strippedLine = "";
			}
		}
		return strippedLine;
	}

	/**
	 * Converts instruction into hexadecimal that carrys a label.
	 * 
	 * @param theLine A string of assembly instructions.
	 * @return A string of hexadecimal instructions.
	 */
	private String textToBinary(String theLine) {
		ParsedCode decode = new ParsedCode(theLine, myLabels);
		// System.out.println(decode.getInstruct());
		return decode.getInstruct();
	}

	/**
	 * This method loops through the instructions set to match their label pairs
	 * further downstream in the code.
	 * 
	 * @param labelsToFix Hash map of stored labels that have not found their pair.
	 * @param labelList   Hash map of all stored labels.
	 * @param memory      Memory address index.
	 */
	public void reLabelPass(LinkedHashMap<ParsedCode, String> labelsToFix, LinkedHashMap<String, Integer> labelList,
			MemFile memory) {
		for (ParsedCode key : labelsToFix.keySet()) {
			String incompleteInstruct = key.getInstruct();
			String labelName = labelsToFix.get(key);
			int correctLabelAddress = labelList.get(labelName);
			long newAddress = Long.decode(incompleteInstruct);
			newAddress = newAddress - 0xFFFF + correctLabelAddress;
			for (int i = 0; i < memory.getSize(); i++) {
				if (memory.getData(i) == Long.decode(incompleteInstruct)) {
					memory.setData(i, newAddress);
				}
			}
		}
	}

	/**
	 * Strips the label from the assembly instructions.
	 * 
	 * @param theLine A string of assembly instructions.
	 * @return A string of the label.
	 */
	private String parseLabel(String theLine) {
		String parsedLine = null;
		if (theLine.endsWith(":")) {
			nextLineLabel = true;
			currentLabel = theLine.substring(0, theLine.length() - 1);
			parsedLine = "";
		} else {
			int labelIndex = theLine.indexOf(":");
			currentLabel = theLine.substring(0, labelIndex);
			parsedLine = theLine.substring(currentLabel.length() + 1).trim();
		}
		return parsedLine;
	}

	/**
	 * Outputs the contents of the parsed assembly instructions to GUI.
	 * 
	 * @param theInput A string of assembly instructions.
	 * @return A hexadecimal representation of the assembly instructions.
	 */
	public String run(String theInput) {
		parseAssembly(theInput, this.myMem);

		for (int i = 0; i < text.size(); i++) {
			this.myMem.setData(i, Long.decode(text.get(i)));
		}

		for (int i = 0; i < data.size(); i++) {
			this.myMem.setData(i + 1599, Long.decode(data.get(i)));
		}

		reLabelPass(ParsedCode.noLabelAddress, this.myLabels, this.myMem);

		StringBuffer result = new StringBuffer();

		result.append("data:\n");
		result.append("\tLabel\tData\n");

		ArrayList<String> dataLabels = new ArrayList<String>();
		for (String key : myLabels.keySet()) {
			dataLabels.add(key);
		}
		for (int i = 0; i < data.size(); i++) {
			result.append("\t" + dataLabels.get(i));
			result.append("\t" + data.get(i) + "\n");
		}

		result.append("\n\ninstructions:\n");
		result.append("\tAddress\tHexadecimal\n");
		for (int i = 0; i < text.size(); i++) {
			result.append("\t" + i);

			result.append("\t" + text.get(i) + "\n");
		}

		return result.toString();
	}



}
