package ch6;

import java.util.EmptyStackException;

public class StackLinkL<T> {
	private Node<T> head;
	private int count;
	
	public StackLinkL() {
		count = 0;
	}
	
	public void push(T element) {
		head = new Node(element, head);
	}
	
	public T pop() {
		if (head == null)
			throw new EmptyStackException();
		T top = head.getData();
		head = head.getLink();
		
		return top;
	}
	public T peek() {
		if (head == null)
			throw new EmptyStackException();
		
		return head.getData();
	}
	
}
