package edu.century.pa3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SimpleParser {
	private ArrayList<ArrayList<Character>> expArrayList2D;
	private BufferedReader reader;
	private LinkedStack<Character> parentheses;
	
	public final char LEFT_P = '(';
	public final char RIGHT_P = ')';
	public final char LEFT_S = '[';
	public final char RIGHT_S = ']';
	public final char LEFT_C = '{';
	public final char RIGHT_C = '}';
	public final char LEFT_A = '<';
	public final char RIGHT_A = '>';

	
	public SimpleParser(String path) {	
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
					// TODO Auto-generated catch block
					e.printStackTrace();
			 }		
			parentheses = new LinkedStack<>();
			evaluate();
	}
	public void evaluate() {
		
		for (int i=0; i<expArrayList2D.size(); i++) {
			for(int j=0; j<expArrayList2D.get(i).size(); j++) {
				insert(expArrayList2D.get(i).get(j));
			}	
		}
	}
	public void getChackBalance() {
/*		if(parentheses.peek().equals(null)) {
			return true;
		}
		else return false;*/
		System.out.println("haha"+parentheses.toString()+"haho");
		//return true;
	}
	
	private void insert(char token){
		switch (token) {
		case LEFT_P: 	// (
			parentheses.push(token);
			break;
		case RIGHT_P: 	// )		
			if(parentheses.peek().equals(LEFT_P)) {
				parentheses.pop();
			}break;
		case LEFT_S: 	// [
			parentheses.push(token);
			break;
		case RIGHT_S: 	// ]
			if(parentheses.peek().equals(LEFT_S)) {
				parentheses.pop();
			}break;
		case LEFT_C: 	// {
			parentheses.push(token);
			break;
		case RIGHT_C: 	// }
			if(parentheses.peek().equals(LEFT_C)) {
				parentheses.pop();
			}break;
		case LEFT_A: 	// <
			parentheses.push(token);
			break;
		case RIGHT_A: 	// >
			if(parentheses.peek().equals(LEFT_A)) {
				parentheses.pop();
			}break;
			default: break;
		}
	}
	
	public String fileToString() {
		String strs= "";
		for(ArrayList<Character> str : expArrayList2D) {
			for(char stre : str) {
				strs += stre;
			}
			strs += "\n";
		}
		return strs;
	}
}
