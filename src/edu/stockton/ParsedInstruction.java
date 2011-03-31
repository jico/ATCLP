package edu.stockton;

/**
 * Represents an interpreted instruction.
 * Contains the formatted output parameter,
 * instruction type, and original instruction phrase.
 *
 */
public class ParsedInstruction {
	private String phrase;
	private String param;
	private String type;
	
	/**
	 * Constructs a ParsedInstruction
	 * @param phrase The original instruction phrase
	 * @param param The formatted output parameter
	 * @param type The instruction type
	 */
	public ParsedInstruction(String phrase, String param, String type) {
		this.phrase = phrase;
		this.param = param;
		this.type = type;
	}
	
	/**
	 * Gets the original instruction phrase
	 * @return The phrase
	 */
	public String getPhrase() {
		return phrase;
	}
	
	/**
	 * Gets the formatted output parameter
	 * @return The parameter
	 */
	public String getParam() {
		return param;
	}
	
	/**
	 * Gets the instruction type
	 * @return The type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Returns the parameters in a formatted string.
	 */
	public String toString() {
		return "phrase[" + phrase + "]\n" + "type[" + type + "]\n" + "param[" + param + "]";
	}
	
}
