package edu.century.pa3;

/**public class SimpleParser from the package edu.century.pa3
 * 	to implement check error of file, and display where the error line been.
 * 
 *  Century College, CSCI 2082 Fall 2018.
 *  SimpleParser.java, Programming Assignment 03.
 *  
 *  @author (Ping) Nalongsone Danddank
 *  @version 1.0
 *  @since 10/25/2018
 * */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SimpleParser {
	//like array 2D to store all character of file.
	private ArrayList<ArrayList<Character>> expArrayList2D;
	//to reading file.
	private BufferedReader reader;	
	//to store temporary characters to handle.
	private LinkedStack<Character> tmpstack;
	//to store error characters.
	private LinkedStack<Character> errStack;
	//to store the expecting characters that missing.
	private LinkedStack<Character> expStack;
	//to count the line of error.
	private Integer line;
	//to store the numbers of error line in expecting characters.
	private LinkedStack<Integer> NumErrlineExp;
	//to store the numbers of error in line of temporary characters.
	private LinkedStack<Integer> NumErrlineTmp;
	//
	public final char LEFT_P = '(';
	public final char RIGHT_P = ')';
	public final char LEFT_S = '[';
	public final char RIGHT_S = ']';
	public final char LEFT_C = '{';
	public final char RIGHT_C = '}';
	public final char LEFT_A = '<';
	public final char RIGHT_A = '>';
	//
	/* @Descriptions 
	 * 		Initialize and Constructor for the SimpleParser class
	 * @Parameter
	 * 		path - a File path that set path to instance of object.
	 * @Precondition
	 * 		path cannot be empty or null.
	 * @Postcondition
	 * @Thorws 
	 * 		NullPointerException - Indicates path.		
	 * */
	public SimpleParser(File path) {	
		init(path);				
	}

	/*
	 * public boolean isBalance()
		to determine if file balance or not.
		Precondition:
		Returns:
			the ture or false if Balance or unbalanced .
		Throws: NullPointerException
			Indicates tmpstack or errStack .
	 * */
	public boolean isBalance() {	
		return tmpstack.isEmpty() && errStack.isEmpty();
	}
	
	/*
	 * insert
		private void checkError(char token)
		to check error for each character.
		Parameter:
			token – char type to check error
		Postcondition:
		Throws: 
	 * */
	private void checkError(char token){
		Integer errLine = line+1;
		switch (token) {
		case LEFT_P: 	// (
			handle(token, errLine);break;			
		case RIGHT_P: 	// )		
			handle(token, errLine, LEFT_P);break;			
		case LEFT_S: 	// [
			handle(token, errLine);break;
		case RIGHT_S: 	// ]
			handle(token, errLine, LEFT_S);break;			
		case LEFT_C: 	// {
			handle(token, errLine);break;
		case RIGHT_C: 	// }
			handle(token, errLine, LEFT_C);break;		
		case LEFT_A: 	// <
			handle(token, errLine);break;
		case RIGHT_A: 	// >
			handle(token, errLine, LEFT_A);break;
		default: break;
		}		
	}
	
	/*
	 * handle
		private void handle(char token, Integer errLine)
		to handle error for each character.
		Parameter:
			token – char type to check error
			errLine - Integer the number of error line.
		Postcondition:
		Throws: 
	 * */
	private void handle(char token, Integer errLine) {
		tmpstack.push(token);
		NumErrlineTmp.push(errLine);
	}
	
	/*
	 * handle
		private void handle(char token, Integer errLine, char left)
		to handle error for each character.
		Parameter:
			token – char type to check error
			errLine - Integer the number of error line.
			left - char for check.
		Postcondition:
		Throws: 
	 * */
	private void handle(char token, Integer errLine, char left) {
		if(!tmpstack.isEmpty() && tmpstack.peek().equals(left)) {
			tmpstack.pop();
			NumErrlineTmp.pop();			
		}else { 
			errStack.push(token);
			NumErrlineExp.push(errLine);
			if(!tmpstack.isEmpty()) {
			expStack.push(expectCh(tmpstack.pop()));
			NumErrlineTmp.pop();				
			}
		}
	}

	/*
	 * expectCh
		private Character expectCh(Character ch)
		to find the expecting character.
		Parameter:
			ch – character type to find.
		Postcondition:
		Throws: 
	 * */
	private Character expectCh(Character ch) {
		if(ch.equals(LEFT_P)) return RIGHT_P;
		if(ch.equals(LEFT_S)) return RIGHT_S;
		if(ch.equals(LEFT_C)) return RIGHT_C;
		if(ch.equals(LEFT_A)) return RIGHT_A;
		return null;
	}
	
	/*
	 * init
		public void init(File path)
		to set up file to instance of object
		Parameter:
			path – file path of character to handle.
		Postcondition: file path not empty.
		Throws: FileNullExceptions
	 * */
	public void init(File path) {
		try {
			reader = new BufferedReader(new FileReader(path));		
			String line = reader.readLine();
			expArrayList2D = new ArrayList<ArrayList<Character>>();
			int i=0;
			while(line != null) {					
				char[] chs = line.toCharArray();
				ArrayList<Character> ch = new 	ArrayList<Character>();
				for (char c : chs )
					ch.add(c);
				expArrayList2D.add(i++, ch);
				line = reader.readLine();
			}
		}catch (IOException e) {
				e.printStackTrace();
		 }
		tmpstack = new LinkedStack<>();
		errStack = new LinkedStack<>();
		expStack = new LinkedStack<>();			
		NumErrlineExp = new LinkedStack<>();
		NumErrlineTmp = new LinkedStack<>();
		
		for (line=0; line<expArrayList2D.size(); line++) {
			for(int j=0; j<expArrayList2D.get(line).size(); j++) {
				checkError(expArrayList2D.get(line).get(j));		
			}	
		}
	}
	
	/*
	 * displayError
		public String displayError()
		to display error of file and show the line fo error character.
		Parameter:
		Postcondition: The String of error of file.
		Throws: NullPointsExceptions
	 * */
	public String displayError() {
		String strs= "";
		if(isBalance()) {
			return "File is balanced!";
		}
		strs += "File is unbalanced!";
		if(!errStack.isEmpty()) {
			while(errStack.peek()!=null) {
				strs += String.format("\nError in line %d: "
				+ "Expecting: %s, but found: %s  ",NumErrlineExp.pop(),
				expStack.pop(),errStack.pop() );
			}
		}
		if(!tmpstack.isEmpty()) {
			while(tmpstack.peek()!=null) {
				strs += String.format("\nError in line %d: "
						+ " found: %s ",NumErrlineTmp.pop(),
						tmpstack.pop());
			}
		}
		return strs;
	}
	
	/*
	 * fileToString
		public String fileToString()
		to display file's contents.
		Parameter:
		Postcondition: The String of file contents.
		Throws: NullPointsExceptions
	 * */
	public String fileToString() {
		String strs= "1|  ";
		int i=1;
		for(ArrayList<Character> str : expArrayList2D) {
			i++;
			for(char stre : str) {
				strs += stre;
			}
			strs += String.format("\n%d|  ", i);
		}
		return strs;
	}
}
