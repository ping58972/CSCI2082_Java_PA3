package edu.century.pa3;

import java.util.EmptyStackException;

public class LinkedStack <T> {
	private Node<T> head;
	private int count;
	
	public LinkedStack() {
		this.head =null;

		count =0;
	}
	
	public void push(T element) {
		head = new Node<T>(element, head);
	
		count++;
	}
	public boolean isEmpty() {
		return head == null;
	}
	public T pop() {
		if(head == null) {
			throw new EmptyStackException();
		}
		T top = head.data;
		head = head.link;
		return top;
	}
	public T peek() {
		if(head == null) {
			return null;
			//throw new EmptyStackException();
		}
		return head.data;
	}
	public String toString() {
		String str ="";
		Node<T> cur =head;
	while(cur != null) {
		str += cur.toString()+" ";
		cur = cur.link; 
	}
	return str;
	}
	
	//Inner class
	public class Node<T>{
		public T data;
		public Node<T> link;

		public Node(T data, Node<T> link){
			this.data = data;
			this.link = link;
		}
		
		public void removeNodeAfeter(){
			link = link.link;
		}
		
		public void addNodeAfter(T element) {
			link = new Node(element, null);
		}
		public  int size(Node head) {
			Node cursor;
			int count =0;
			for(cursor=head; cursor!=null; cursor=cursor.link) {
				count++;
			}
			return count;
		}

		@Override
		public String toString() {
			return data.toString();
		}
		
	}
	
}

