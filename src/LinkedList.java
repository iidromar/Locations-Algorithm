class Node<T>{
	public T data;
	public Node<T> next;
	public Node(T element) {
		data = element;
		next = null;
	}
}
public class LinkedList<T> implements List<T> {
	private Node<T> head;
	private Node<T> current;
	
	public LinkedList() {
		head = current = null;
	}

	@Override
	public boolean empty() {
		return head == null;
	}

	@Override
	public boolean full() {
		return false;
	}

	@Override
	public void findFirst() {
		current = head;
	}

	@Override
	public void findNext() {
		current = current.next;
	}

	@Override
	public boolean last() {
		return current.next == null;
	}

	@Override
	public T retrieve() {
		return current.data;
	}

	@Override
	public void update(T e) {
		
		current.data = e;
	}

	@Override
	public void insert(T e) {
		Node<T> tester;
		if(empty()) {
			current = head = new Node<T>(e);
		}
		else {
			tester = current.next;
			current.next = new Node<T>(e);
			current = current.next;
			current.next = tester;
		}
	}

	@Override
	public void remove() {
		if(current == head) {
			head = head.next;
		}
		else {
			Node<T> tester = head;
			while(tester.next != current)
				tester = tester.next;
			tester.next = current.next;
		}
		if(current.next == null)
			current = head;
		else
			current = current.next;
	}
	
}
