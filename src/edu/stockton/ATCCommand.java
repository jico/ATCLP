package edu.stockton;

/**
 * ATCCommand data object holds the processed command
 * values and contains methods to return them.
 *   
 * @author Mark Mucciarone
 *
 */
public class ATCCommand {
	private String recipient;
	private String type;
	private String parameter;
	
	public ATCCommand(String recipient, String type, String parameter) {		
		this.recipient = recipient;		
		this.type = type;		
		this.parameter = parameter;	
	}	
	
	/**
	 * Gets the recipient value
	 * @return The recipient string
	 */
	public String getRecipient() {		
		return recipient;	
	}
	
	/**
	 * Gets the type
	 * @return The type string
	 */
	public String getType() {		
		return type;	
	}
	
	/**
	 * Gets the parameter
	 * @return The parameter string
	 */
	public String getParameter() {		
		return parameter;	
	}
	
	/**
	 * Sets the recipient value
	 * @param recipient The recipient
	 */
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	
	/**
	 * Sets the type value
	 * @param type The type
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * Sets the parameter value
	 * @param parameter The parameter
	 */
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	
	/**
	 * Outputs the data in an XML string
	 * @return The recipient, type, and parameter values in XML format
	 */
	public String toXML(){
		String temp = "<ATCCommand><recipient>" + recipient + "</recipient><type>" + type + "</type><parameter>" + parameter + "</parameter></ATCCommand>";
		return temp;
	}
	
}





