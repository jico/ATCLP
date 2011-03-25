package edu.stockton;

public class Number {
	private int value;
	private int weight;
	private String text;
	
	public Number() {
		
	}
	
	public Number(String textRepresentation, int val, int weight) {
		text = textRepresentation;
		value = val;
		this.weight = weight;
	}
	
	public int getValue() {
		return value;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public String getTextRepresentation() {
		return text;
	}
	
	public void setValue(int val) {
		value = val;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public void setTextRepresentation(String text) {
		this.text = text;
	}
}
