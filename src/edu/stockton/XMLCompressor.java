package edu.stockton;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class XMLCompressor {
	
	public static void main(String args[]) throws Exception {
		String testFile = "numbersPretty.xml";
		String testOut = "testCompress.xml";
		compress(testFile, testOut);
	}
	
	public static void compress(String filename, String destination) throws Exception {
		FileReader reader = new FileReader(filename);
		FileWriter writer = new FileWriter(destination);
		Scanner in = new Scanner(reader);
		BufferedWriter out = new BufferedWriter(writer);
		
		String str;
		while (in.hasNext()) {
	       str = in.nextLine().replaceAll("\t\r\n", "");
	       out.write(str);
	    }
	    reader.close();
		writer.close();
	}
	
	
}
