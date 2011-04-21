package edu.stockton;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * Console interacts with the Language Processor, as well as
 * the NumberEngine, CallsignEngine, and InstructionEngine using
 * predefined commands.
 *
 */
public class Console {
	private static Scanner console = new Scanner(System.in);
	private static String input;
	private static String[] line;
	private static LinkedList params = new LinkedList();
	private static ListIterator itr;	

	public static void main(String[] args) {
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
				if(line.length != 2) System.out.println("Command syntax error. Syntax: [command] [options] '[param]'");
				else {
					// Pull command, optionsList, and argument
					String cmdLine = line[0].trim();
					String[] optionsList = cmdLine.split("-");
					String method = optionsList[0].trim();
					String param = line[1].trim();
					
					String options = "";
					boolean verbose = false;
					boolean fileInput = false;
					boolean silent = false;
					
					// Check options and set option variable
					if(optionsList.length > 1) {
						// Get all option flags
						for(int i = 1; i < optionsList.length; i++) options += optionsList[i].trim();	
						
						// Set flags
						if(options.indexOf("v") >= 0) {
							verbose = true;
							
						}
						if(options.indexOf("f") >= 0) fileInput = true;
						if(options.indexOf("s") >= 0) silent = true;
						
					}
					
					if(verbose) {
						System.out.println("cmd: " + method);
						System.out.println("param: " + param);
						System.out.println("options: " + options);
					}
					
					// Execute method
					try {
						// main parse method
						if(method.equalsIgnoreCase("parse")) {
							if(fileInput) {
								try {
									parseFile(param, silent);
								} catch(FileNotFoundException e) {
									System.out.println("No such file: " + param);
								}
								
							} else System.out.println(LanguageProcessor.parse(param, verbose).toXML());
						}
						
						// other useful methods
						else if(method.equalsIgnoreCase("tonum")) {
							System.out.println(NumberEngine.toNumeric(param));

						}
						else if(method.equalsIgnoreCase("identify")) {
							System.out.println(CallsignEngine.telephonyToDesignator(param));
						}
						else if(method.equalsIgnoreCase("params")) {
							System.out.println(InstructionEngine.parse(param).toString());
						}
						else if(method.equalsIgnoreCase("isinstr")) {
							int index = InstructionEngine.isInstruction(param);
							if(index >= 0) System.out.println("Valid instruction: index[" + index + "]");
							else System.out.println("Unrecognized instruction");
						}
						else System.out.println("Unrecognized command '" + method + "' type help for command list");
					} catch(ParseException e) {
						System.out.println(e.getMessage());
					} 
					
				}	
				
			}
			
		} while(!exit);
		System.out.println("Console terminated. Goodbye!");	
		
	}
	
	/**
	 * Prints the help menu.
	 */
	public static void printHelp() {
		FileReader reader;
		try {
			reader = new FileReader("help.txt");
			
			Scanner in = new Scanner(reader);
			
			while(in.hasNext()) {
				System.out.println(in.nextLine());
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Help text file not found!");
			e.printStackTrace();
		} 
		
		
	}
	
	/**
	 * Parses a file containing ATC command transcriptions line by line.
	 * Runs the LanguageProcessor parse method on each line and prints
	 * the result.
	 * @param filename name of the file
	 * @param silent if set to true, will suppress any unparsed lines
	 * @throws FileNotFoundException
	 */
	public static void parseFile(String filename, boolean silent) throws FileNotFoundException {
		FileReader reader = new FileReader(filename);
		Scanner in = new Scanner(reader);
		
		int total = 0;
		int successful = 0;
		
		while (in.hasNextLine()) {
			total++;
			
			String cmd = in.nextLine();
			if(!silent) System.out.println(cmd);
			
			try {
				String output = LanguageProcessor.parse(cmd, false).toXML();
				if(silent) System.out.println(cmd);
				System.out.println(output + "\n");
				successful++;
			} catch(ParseException e) {
				if(!silent) System.out.println(e.getMessage() + "\n");
			} catch(Exception e) {
				if(!silent) System.out.println("parse error\n");
			}
			
		}
		
		System.out.println("Total commands: " + total);
		System.out.println("Successfully parsed: " + successful);
		in.close();
	}
	
	

}
