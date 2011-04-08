package edu.stockton;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 * Console interacts with the Language Processor, as well as
 * the NumberEngine, CallsignEngine, and InstructionEngine using
 * predefined commands.
 * 
 * @author Jico Baligod
 *
 */
public class Console {
	private static Scanner console = new Scanner(System.in);
	private static String input;
	private static String[] line;
	private static LinkedList params = new LinkedList();
	private static ListIterator itr;	

	public static void main(String[] args) throws Exception {
		boolean exit = false;
		
		System.out.println("ATCLP Console started");
		System.out.println("\"help\" for more information");
				
		do {
			params.clear();
			System.out.print(">> ");
			
			input = console.nextLine();
			if(input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit")) exit = true;
			else if(input.equalsIgnoreCase("help")) printHelp(); 
			else {
				// Format of commands:
				// i.e. "parse: nineteen forty four"
				line = input.split("'");
				if(line.length != 2) System.out.println("Command syntax error. Syntax: [command] '[param]'");
				else {
					// Pull command, options, and argument
					String cmdLine = line[0].trim();
					String[] cmdList = cmdLine.split("-");
					String method = cmdList[0].trim();
					String param = line[1].trim();
					
					// Options variables
					boolean debug = false;
					
					if(cmdList.length > 1) {
						for(int i = 1; i < cmdList.length; i++) {
							if(cmdList[i].equals("d")) debug = true;
						}
					}
					
					if(debug) {
						System.out.println("cmd: " + method);
						System.out.println("param: " + param);
						System.out.print("result: ");
					}
					
					// Execute method
					if(method.equalsIgnoreCase("parse")) System.out.println(LanguageProcessor.parse(param).toXML());
					else if(method.equalsIgnoreCase("tonum")) System.out.println(NumberEngine.toNumeric(param));
					else if(method.equalsIgnoreCase("identify")) System.out.println(CallsignEngine.telephonyToDesignator(param));
					else if(method.equalsIgnoreCase("params")) {
						System.out.println(InstructionEngine.parse(param).toString());
					}
					else if(method.equalsIgnoreCase("isinst")) System.out.println(InstructionEngine.isInstruction(param));
					else System.out.println("Unrecognized command '" + method + "' type help for command list");
				}	
				
			}
			
		} while(!exit);
		System.out.println("Console terminated. Goodbye!");	
		
	}
	
	public static void printHelp() throws Exception {
		FileReader reader = new FileReader("help.txt");
		Scanner in = new Scanner(reader);
		
		while(in.hasNext()) {
			System.out.println(in.nextLine());
		}
		
	}
	
	

}
