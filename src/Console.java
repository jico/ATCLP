import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

import edu.stockton.*;

/**
 * Console interacts with the Language Processor.
 * 
 * @author Jico Baligod
 * @version 0.1a
 *
 */
public class Console {
	private static Scanner console = new Scanner(System.in);
	private static String input;
	private static String[] line;
	private static LinkedList params = new LinkedList();
	private static ListIterator itr;
	private static NumberEngine numberEngine;
	private static CallsignEngine callsignEngine;
	
	private static ArrayList<String> numbersDict = new ArrayList<String>();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		boolean exit = false;
		numberEngine = new NumberEngine();
		callsignEngine = new CallsignEngine();
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
					
					if(cmd.equalsIgnoreCase("tonum")) System.out.println(numberEngine.toNumeric(param));
					if(cmd.equalsIgnoreCase("isCallsign")) System.out.println(callsignEngine.isDesignator(param));
					if(cmd.equalsIgnoreCase("getCompany")) System.out.println(callsignEngine.getCompany(param));
					if(cmd.equalsIgnoreCase("getDesignator")) System.out.println(callsignEngine.getDesignator(param));
				}
				
				
				/*
				itr = params.listIterator();
				
				while(itr.hasNext()) {
					String current = (String) itr.next();
					System.out.println(current);
				}
				*/	
				
			}
			
		} while(!exit);
		System.out.println("Console terminated. Goodbye!");	
		
	}
	
	

}
