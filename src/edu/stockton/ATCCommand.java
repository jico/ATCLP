package edu.stockton;

// Mark Mucciarone
// Last Date worked on: 3/24/2011
//
// Typical get and set methods to access/modify private 
// instance fields (recipient, type, parameter). i.e. 
// getRecipient() returns recipient instance field
// along with toXML command to output all fields into an XML
// ATCCommand line.  
public class ATCCommand {
	private String recipient;
	private String type;
	private String parameter;
	
	public ATCCommand(String recipient, String type, String parameter) {		
		this.recipient = recipient;		
		this.type = type;		
		this.parameter = parameter;	
		}	
	
	public String getRecipient() {		
		return recipient;	
		}
	
	public String getType() {		
		return type;	
		}
	
	public String getParameter() {		
		return parameter;	
		}
	
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	
	public String toXML(){
		
		String temp = "<ATCCommand><recipient>" + recipient + "</recipient><type>" + type + "</type><parameter>" + parameter + "</parameter></ATCCommand>";
		return temp;
	}
	
	
	
	
	
}





