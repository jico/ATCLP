import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

import org.json.*;

import edu.stockton.NumberParser;

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
	private static String[] cmds;
	private static LinkedList cmdsList = new LinkedList();
	private static ListIterator itr;
	private static NumberParser numBot;
	
	private static ArrayList<String> numbersDict = new ArrayList<String>();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		boolean exit = false;
		numBot = new NumberParser();
		
		do {
			cmdsList.clear();
			System.out.print(">> ");
			
			input = console.nextLine();
			if(input.equals("exit")) exit = true;
			else {
				System.out.println(numBot.toInt(input));
				/*
				cmds = input.split(" ");
				for(String cmd : cmds) {
					cmdsList.add(cmd);
				}
				
				itr = cmdsList.listIterator();
				
				while(itr.hasNext()) {
					String current = (String) itr.next();
					System.out.print(current);
					System.out.println(" /" + tag(current));
				}	
				*/
			}
			
		} while(!exit);
		System.out.println("Console terminated. Goodbye!");	
		
	}
	
	public static String tag(String text) {
		if(numBot.isNumber(text)) return "number";
		else return "unknown";
	}

}
