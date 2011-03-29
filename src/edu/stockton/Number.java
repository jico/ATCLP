package edu.stockton;

/**
 * Represents a number and contains the textual representation,
 * as well as value and weight information for determining numeric
 * value and numeric representation.
 * Used primarily for the NumberEngine.
 */
public class Number {
	private int value;
	private int weight;
	private String text;
	
	public Number(String textRepresentation, int val, int weight) {
		text = textRepresentation;
		value = val;
		this.weight = weight;
	}
	
	/**
	 * Gets the number value
	 * @return The value
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Gets the number weight
	 * @return The weight
	 */
	public int getWeight() {
		return weight;
	}
	
	/**
	 * Gets the textual representation of the word
	 * @return The number in text format. i.e. "three"
	 */
	public String getTextRepresentation() {
		return text;
	}
	
	/**
	 * Sets the number value
	 * @param val The value
	 */
	public void setValue(int val) {
		value = val;
	}
	
	/**
	 * Sets the weight value
	 * @param weight The weight
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	/**
	 * Sets the textual representation
	 * @param text Text format of number
	 */
	public void setTextRepresentation(String text) {
		this.text = text;
	}
}
