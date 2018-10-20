package edu.century.pa3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SimpleParser {
	private ArrayList<ArrayList<Character>> expArrayList2D;
	private BufferedReader reader;
	
	private LinkedStack<Character> tmpstack;
	private LinkedStack<Character> errStack;
	private LinkedStack<Character> expStack;	
	private Integer line;
	private LinkedStack<Integer> NumErrlineExp;
	private LinkedStack<Integer> NumErrlineTmp;
		
	public final char LEFT_P = '(';
	public final char RIGHT_P = ')';
	public final char LEFT_S = '[';
	public final char RIGHT_S = ']';
	public final char LEFT_C = '{';
	public final char RIGHT_C = '}';
	public final char LEFT_A = '<';
	public final char RIGHT_A = '>';
	
	public SimpleParser(File path) {	
		init(path);				
	}
	public void evaluate() {
		
		for (line=0; line<expArrayList2D.size(); line++) {
			for(int j=0; j<expArrayList2D.get(line).size(); j++) {
				insert(expArrayList2D.get(line).get(j));		
			}	
		}

	}
	public boolean isBalance() {	
		return tmpstack.isEmpty() && errStack.isEmpty();
	}
	
	private void insert(char token){
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
	
	private void handle(char token, Integer errLine) {
		tmpstack.push(token);
		NumErrlineTmp.push(errLine);
	}
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

	private Character expectCh(Character ch) {
		if(ch.equals(LEFT_P)) return RIGHT_P;
		if(ch.equals(LEFT_S)) return RIGHT_S;
		if(ch.equals(LEFT_C)) return RIGHT_C;
		if(ch.equals(LEFT_A)) return RIGHT_A;
		return null;
	}
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
		evaluate();
	}
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
