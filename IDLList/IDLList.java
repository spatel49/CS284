package classes;

import java.util.ArrayList;

/**
 * Siddhanth Patel -- CS 284A
 * I pledge my honor that I have abided by the Stevens Honor System.
 * 
 * @author spate144
 * 
 */

public class IDLList<E> {
	//Inner class for Nodes
	private static class Node<E> {
		//Data Fields
		/** The data in the Node */
		E data;
		/** Previous node */
		Node<E> prev;
		/** Next node */
		Node<E> next;
		
		//Constructors
		/**
		 * Constructs a Node with data being elem
		 * @param elem The data value for Node
		 */
		Node(E elem) {
			//Sets node values to default
			data = elem;
			prev = null;
			next = null;
		}

		/**
		 * Constructs a Node with data being elem,
		 * previous referencing the previous Node,
		 * and next referencing the next Node.
		 * 
		 * @param elem The data value for Node
		 * @param prev Previous Node
		 * @param next Next Node
		 */
		Node(E elem, Node<E> prev, Node<E> next) {
			this.data = elem;
			this.prev = prev;
			this.next = next;
		}
	}
	//Data Fields
		/** First Node in IDLL */
		private Node<E> head;
		/** Last Node in IDLL */
		private Node<E> tail;
		/** Size of IDLL */
		private int size;
		/** Array that references the Nodes with index */
		private ArrayList<Node<E>> indices;
		
	//Constructors
	/**
	 * Constructs an IDLL with a null head and null tail
	 */
	public IDLList() {
		//Constructor
		this.head = null;
		this.tail = null;
		this.size=0;
		indices = new ArrayList<Node<E>>();
	}
	
	//Operations
	/**
	 * Adds a element at given index
	 * @param index The index where the new Node will be added
	 * @param elem Element that is being added
	 * @return True if it adds successfully
	 */
	public boolean add(int index, E elem) {
		if (index < 0 || index >= size|| elem == null) {
			throw new IllegalArgumentException();
		}
		else if (index == size) {
			append(elem);
			return true;
		}
		else if (index == 0) {
			add(elem);
			return true;
		}
		else {
			Node<E> previous = indices.get(index-1);
			Node<E> newNode1 = new Node<E>(elem, previous, previous.next);
			previous.next.prev = newNode1;
			previous.next = newNode1;
			indices.add(index, newNode1);
			size++;
		}
		return true;
	}
	
	/**
	 * This method adds new Node of element elem at beginning of list
	 * @param elem Element being added to list
	 * @return True if it adds successfully.
	 */
	public boolean add (E elem) {
        if (this.head == null && this.tail == null) {
            Node<E> n1 = new Node<E>(elem);
            this.head = n1;
            this.tail = n1;
            indices.add(0, n1);
        } else {
            Node<E> current = indices.get(0);
            Node<E> n1 = new Node<E>(elem, current.prev, current);
            current.prev = n1;
            this.head = n1;
            indices.add(0, n1);
        }
        size++;
        return true;
    }
	
	/**
	 * This method adds a new Node at the end of list
	 * @param elem Element being added to list
	 * @return True if it adds successfully.
	 */
	public boolean append(E elem) {
		if (elem == null) {
			throw new IllegalArgumentException();
		}
		else if (tail == null) {
			this.add(elem);
			return true;
		}
		else {
			Node<E> node2 = tail;
			tail = new Node<E>(elem, tail, null);
			node2.next = tail;
			indices.add(tail);
			size++;
		}
		return true;
	}
	
	/**
	 * This method gets data from Node at given index
	 * @param index Index in list where data is requested
	 * @return the element data at given index
	 */
	public E get(int index) {
		//Returns the element at a specific index
		if(index < 0 || index > size) {
			throw new ArrayIndexOutOfBoundsException();
		} else {
			return indices.get(index).data;
		}
		}
		
	
	/**
	 * This method gets data from first Node in list
	 * @return Returns data of head in list
	 */
	public E getHead() {
		//Returns the value of the head
		if (this.head == null && this.tail == null) {
            throw new IllegalArgumentException();
        }

        return this.head.data;
	}
	
	/**
	 * This method gets data from last Node in list
	 * @return Returns data of tail in list
	 */
	public E getLast() {
		if (this.head == null && this.tail == null) {
            throw new IllegalArgumentException();
        }

        return this.tail.data;
	}
	
	/**
	 * This method returns the size of list
	 * @return size of the List
	 */
	public int size() {
		return size;
	}
	
	/**
	 * This method removes the first Node of list
	 * @return Returns the first element that was removed
	 */
	public E remove() {
		if (head == null) {
			throw new IllegalArgumentException();
		}
		
		else if(head == tail) {
			E temp = head.data;
			head = null;
			tail = null;
			indices.remove(0);
			size--;
			return temp;
		} else {
			E temp = head.data;
			head = head.next;
			indices.remove(0);
			size--;
			return temp;
		}
		}
		
	
	/**
	 * This method removes the last element in list
	 * @return Returns the last element in list
	 */
	public E removeLast() {
		if (tail == null) {
			throw new NullPointerException();
		}
		
		else if (head == tail) {
			E temp = tail.data;
			head = null;
			tail = null;
			indices.remove(size - 1);
			size--;
			return temp;
		}
		
		else {
			E temp = tail.data;
			tail = tail.prev;
			tail.next = null;
			indices.remove(size - 1);
			size--;
			return temp;
		}
	}
	/**
	 * This method removes element at a given index
	 * @param index The index of node being removed
	 * @return Returns the data of node being removed
	 */	
	public E removeAt(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		if (head == null || tail == null) {
			throw new NullPointerException();
		}
		
		else if (index == size - 1) {
			E elem = removeLast();
			return elem;
		}
		
		else if (index == 0) {
			E elem = remove();
			return elem;
		}
		else {
			Node<E> temp = indices.get(index);
			Node<E> prevtemp = temp.prev;
			Node<E> nexttemp = temp.next;
			prevtemp.next = nexttemp;
			nexttemp.prev = prevtemp;			
			indices.remove(index);
			size--;
			return temp.data;
		}
	}
	
	/**
	 * This method removes the first instance of Node of given element by searching list
	 * @param elem The Element that will be removed
	 * @return True if the element is in the list and removed
	 */
	public boolean remove(E elem) {
		if (head == null) {
			throw new NullPointerException();
		}
		int i = 0;
		boolean removed = false;
		
		while(i < size && !removed) {
			if (indices.get(i).data == elem) {
				removeAt(i);
				removed = true;
			}
			else {
				i++;
			}
		}
		return removed;
	}
	
	/**
	 * Returns a string representation of the IDLL
	 * @return Returns string representation of IDLL
	 */
	public String toString() {
        StringBuilder s = new StringBuilder();
        Node<E> current = this.head;
        s.append("null");
        while (current != this.tail.next) {
            s.append("<-");
            s.append(current.data);
            s.append("->");
            current = current.next;
        }
        s.append("null");

        return s.toString();
    }
}