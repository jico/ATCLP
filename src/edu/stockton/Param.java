package edu.stockton;

/**
 * Represents an instruction parameter.
 * Used primarily by the InstructionEngine.
 */
public class Param {
	private String type;
	private String inputRegex;
	private String outputRegex;
	
	/**
	 * Constructs a Param object.
	 * @param inputRegex The regex of the parameter to search for
	 * @param outputRegex The regex formatting to output the parameter value
	 * @param type The type of the parameter
	 */
	public Param(String inputRegex, String outputRegex, String type) {
		this.inputRegex = inputRegex;
		this.outputRegex = outputRegex;
		this.type = type;
	}
	
	/**
	 * Gets the parameter type
	 * @return The parameter type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Gets the input regular expression string
	 * @return The input regular expression string
	 */
	public String getInputRegex() {
		return inputRegex;
	}
	
	/**
	 * Gets the output regular expression formatting
	 * @return The output regular expression
	 */
	public String getOutputRegex() {
		return outputRegex;
	}
	
	/**
	 * Sets the parameter type
	 * @param type The parameter type
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * Sets the input regular expression
	 * @param inputRegex input regular expression string
	 */
	public void setInputRegex(String inputRegex) {
		this.inputRegex = inputRegex;
	}
	
	/**
	 * Sets the output regular expression format
	 * @param outputRegex The output regular expression format
	 */
	public void setOutputRegex(String outputRegex) {
		this.outputRegex = outputRegex;
	}
	
}