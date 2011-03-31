package edu.stockton;

/**
 * Represents a component of an ATC command sentence.
 * Used primarily by the Language Processor.
 * @author jicobaligod
 *
 */
public class Component {
	private String text;
	private String type;
	
	/**
	 * Constructs a Component with the text and component type.
	 * The type correlates to the LP tag() method types.
	 * @param text Component string
	 * @param type Component type
	 */
	public Component(String text, String type) {
		this.text = text.trim();
		this.type = type;
	}
	
	public String getText() {
		return text;
	}
	
	public String getType() {
		return type;
	}
	
	public void setText(String text) {
		this.text = text.trim();
	}
	
	public void setType(String type) {
		this.type = type;
	}
}
