import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

import edu.stockton.*;

/**
 * Console interacts with the Language Processor, as well as
 * the NumberEngine, CallsignEngine, and InstructionEngine using
 * predefined commands.
 * 
 * @author Jico Baligod
 * @version 1.0b
 *
 */
public class Console {
	private static Scanner console = new Scanner(System.in);
	private static String input;
	private static String[] line;
	private static LinkedList params = new LinkedList();
	private static ListIterator itr;
	private static CallsignEngine callsignEngine;
	private static InstructionEngine instructionEngine;
	private static LanguageProcessor LP;
	

	public static void main(String[] args) throws Exception {
		boolean exit = false;
		
		// Initialize Engines and LP
		callsignEngine = new CallsignEngine();
		instructionEngine = new InstructionEngine();
		LP = new LanguageProcessor();
		
		System.out.println("Console started. \"exit\" to quit.");
		do {
			params.clear();
			System.out.print(">> ");
			
			input = console.nextLine();
			if(input.equals("exit")) exit = true;
			else {
				// Format of commands:
				// i.e. "parse: nineteen forty four"
				line = input.split(":");
				if(line.length != 2) System.out.println("Error");
				else {
					String cmd = line[0].trim();
					String param = line[1].trim();
					
					if(cmd.equalsIgnoreCase("parse")) System.out.println(LP.parse(param).toXML());
					if(cmd.equalsIgnoreCase("tonum")) System.out.println(NumberEngine.toNumeric(param));
					if(cmd.equalsIgnoreCase("identify")) System.out.println(callsignEngine.telephonyToDesignator(param));
					if(cmd.equalsIgnoreCase("params")) {
						System.out.println(instructionEngine.parse(param).toString());
					}
					if(cmd.equalsIgnoreCase("instruction")) System.out.println(instructionEngine.isInstruction(param));

				}	
				
			}
			
		} while(!exit);
		System.out.println("Console terminated. Goodbye!");	
		
	}
	
	

}
