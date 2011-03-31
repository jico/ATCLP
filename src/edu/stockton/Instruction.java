package edu.stockton;

import java.util.ArrayList;

/**
 * Represents a defined and valid ATC instruction, corresponding
 * to an XML node in the instructions.xml data library.
 * Contains the phrase regular expression of the instruction, type,
 * and a list of the different parameters it may have.
 * 
 * Used only with the InstructionEngine.
 *
 */
public class Instruction {
	private String phrase;
	private String type;
	private ArrayList<Param> params;
	
	/**
	 * Constructs an Instruction object
	 * @param phrase The regex phrase of the instruction
	 * @param type The type of the instruction
	 * @param params A list of parameter Param objects
	 */
	public Instruction(String phrase, String type, ArrayList<Param> params) {
		this.phrase = phrase;
		this.type = type;
		this.params = params;
	}
	
	/**
	 * Gets the phrase of the instruction
	 * @return The instruction phrase
	 */
	public String getPhrase() {
		return phrase;
	}
	
	/**
	 * Gets the type of the instruction
	 * @return The instruction type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Gets the list of instruction parameters
	 * @return ArrayList of Param objects representing the instruction parameters
	 */
	public ArrayList<Param> getParams() {
		return params;
	}
	
}
