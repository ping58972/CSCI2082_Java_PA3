package edu.century.pa3;

/**public class LinkedStack from the package edu.century.pa3
 * 	Linked Stack class for store Characters like "{[<()>]}" 
 * 	to implement check error of file.
 * 
 *  Century College, CSCI 2082 Fall 2018.
 *  LinkedStack.java, Programming Assignment 03.
 *  
 *  @author (Ping) Nalongsone Danddank
 *  @version 1.0
 *  @since 10/25/2018
 * */

import java.util.EmptyStackException;

public class LinkedStack <T> {
	private Node<T> head;
	private int count;
	
	/*
	 * Constructor for the LinkedStack
		public LinkedStack()
		Initialize an empty LinkedStack.
		Postcondition:
			This LinkedStack is empty.
	 * */
	public LinkedStack() {
		this.head =null;

		count =0;
	}
	
	/*
	 * push
		public void push(T element)
		Add a new element to this LinkedStack.
		Parameter:
			element – the new element that is being pushed
		Postcondition:
			element cannot be empty or null.
		Throws: NullPointerException
			Indicates subject and element are null.
	 * */
	public void push(T element) {
		head = new Node<T>(element, head);
	
		count++;
	}
	
	/* 
	 * pop
	 * public T pop()
	 * to pop data from LinkedStack in generic type.
	 * Parameter:
	 * Postcondition:
	 * 		the data of LinkedStack.
	 * Throws: 	EmptyStackException	
	 * */
	public T pop() {
		if(head == null) {
			throw new EmptyStackException();
		}
		T top = head.data;
		head = head.link;
		return top;
	}
	
	/* 
	 * peek
	 * public T pop()
	 * to pop data from LinkedStack in generic type.
	 * Parameter:
	 * Postcondition:
	 * 		the data of LinkedStack in generic type.
	 * Throws: NullPointerException
			Indicates empty and head is null.
	 * */
	public T peek() {
		if(head == null) {
			return null;
			//throw new EmptyStackException();
		}
		return head.data;
	}
	
	
	public boolean isEmpty() {
		return head == null;
	}
	/* toString
	 *  public String toString()
	 * 		To display info from stack
	 * Parameter 
	 * Precondition
	 * Return
	 * 		the Strings values of instance object convert to String.
	 * Throws: NullPointerException
			Indicates empty and head is null. 		
	 * */
	public String toString() {
		String str ="";
		Node<T> cur =head;
		while(cur != null) {
			str += cur.toString()+" ";
			cur = cur.link; 
		}
		return str;
	}

	//Inner class for Node of LinkedStack in generic type class.
	public class Node<T>{
		public T data;
		public Node<T> link;

		/*
		 * Constructor for the Node<T>
			public Node(T data, Node<T> link)
			Initialize for Node<T> to LinkedStack.
			Postcondition:
				data is not empty;
				link is require Node generic type. 
		 * */
		public Node(T data, Node<T> link){
			this.data = data;
			this.link = link;
		}

		@Override
		public String toString() {
			return data.toString();
		}		
	}	
}

