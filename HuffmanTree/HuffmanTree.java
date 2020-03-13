package hw4;

import java.util.PriorityQueue;

import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;

/*
 * Instructions: 
 * First: Read through the assignment specification, make sure you understand what the assignment is asking for.
 * Second: There are number of "TODO" instructions within this code, make sure to complete all of them fully.
 * Third: Test you code.
 */

// TODO: Name and Pledge

// Pledge: I pledge my honor that I have abided by the Stevens Honor System.
// Name: Siddhanth Patel


/**
 * HW4 CS284 Spring 2019
 * Implements a Huffman encoding tree.
 * The included code has been commented for the student's benefit, feel free to read through it.
 */
public class HuffmanTree {

	// ******************** Start of Stub Code ******************** //
	// ************************************************************ //
    /** Node<E> is an inner class and it is abstract.
     * There will be two kinds
     * of Node, one for leaves and one for internal nodes. */
    abstract static class Node implements Comparable<Node>{
        /** The frequency of all the items below this node */
        protected int frequency;
        
        public Node(int freq) {
        	this.frequency = freq;
        }
        
		/** Needed for the Minimum Heap used later in this stub. */
		public int compareTo(Node other) {
			return this.frequency - other.frequency;
		}
    }
    /** Leaves of a Huffman tree contain the data items */
    protected static class LeafNode extends Node {
        // Data Fields
        /** The data in the node */
        protected char data;
        /** Constructor to create a leaf node (i.e. no children) */
        public LeafNode(char data, int freq) {
            super(freq);
            this.data = data;
        }
        /** toString method */
        public String toString() {
            return "[value= "+this.data + ",freq= "+frequency+"]";
        }
    }
    /** Internal nodes contain no data,
     * just references to left and right subtrees */
    protected static class InternalNode extends Node {
        /** A reference to the left child */
        protected Node left;
        /** A reference to the right child */
        protected Node right;

        /** Constructor to create an internal node */
        public InternalNode(Node leftC, Node rightC) {
            super(leftC.frequency + rightC.frequency);
            left = leftC; right = rightC;
        }
        public String toString() {
            return "(freq= "+frequency+")";
        }
    }
	
	// Enough space to encode all "extended ascii" values
	// This size is probably overkill (since many of the values are not "printable" in the usual sense)
	private static final int codex_size = 256;
	
	/* Data Fields for Huffman Tree */
	private Node root;
	
	public HuffmanTree(String s) {
		root = buildHuffmanTree(s);
	}
	
	/**
	 * Returns the frequencies of all characters in s.
	 * @param s
	 * @return
	 */
	public static int[] frequency(String s) {
		int[] freq = new int[codex_size];
		for (char c: s.toCharArray()) {
			freq[c]++;
		}
		return freq;
	}
	
	/**
	 * Builds the actual Huffman tree for that particular string.
	 * @param s
	 * @return
	 */
	private static Node buildHuffmanTree(String s) {
		int[] freq = frequency(s);
		
		// Create a minimum heap for creating the Huffman Tree
		// Note to students: You probably won't know what this data structure
		// is yet, and that is okay.
		PriorityQueue<Node> min_heap = new PriorityQueue<Node>();
		
		// Go through and create all the nodes we need
		// as in, all the nodes that actually appear in our string (have a frequency greater then 0)
		for(int i = 0; i < codex_size; i++) {
			if (freq[i] > 0) {
				// Add a new node (for that character) to the min_heap, notice we have to cast our int i into a char.
				min_heap.add(new LeafNode((char) i, freq[i]));
			}
		}
		
		// Edge case (string was empty)
		if (min_heap.isEmpty()) {
			throw new NullPointerException("Cannot encode an empty String");
		}
		
		// Now to create the actual Huffman Tree 
		// NOTE: this algorithm is a bit beyond what we cover in cs284, 
		// you'll see this in depth in cs385
		
		// Merge smallest subtrees together
		while (min_heap.size() > 1) {
			Node left = min_heap.poll();
			Node right = min_heap.poll();
			Node merged_tree = new InternalNode(left, right);
			min_heap.add(merged_tree);
		}
		
		// Return our structured Huffman Tree
		return min_heap.poll();
	}
	
	// ******************** End of Stub Code ******************** //
	// ********************************************************** //
	
	Map<String, String> datavalue = new HashMap<String, String>();
	
	public String bitsToString(Boolean[] encoding) {
		// TODO Complete bitsToString method
		String x = "";
		for (boolean value : encoding) {
			if (value == true) {
				x += "1";
			}
			else {
				x += "0";
			}
		}
		return x;
    }
	
	public Boolean[] stringtoBits(String x) {
		Boolean data[] = new Boolean[x.length()];
		for (int i =0; i<x.length(); i++) {
			if (Integer.parseInt(Character.toString(x.charAt(i)))==1) {
				data[i] = true;
			}
			else if (Integer.parseInt(Character.toString(x.charAt(i)))==0){
				data[i] = false;
			}
		}
		return data;
	}
	
	public String toString() {
		// TODO Complete toString method (see assignment specification)
		// HINT: Might need helper method for preOrderTraversal
		StringBuilder str = new StringBuilder();
		preorderTraversal(root, 0, str);
		return str.toString();
    }
	
	private void preorderTraversal(Node curr, int height, StringBuilder str) {
		//str.append("  x" +height+" ")
		for (int i=0; i< height; i++) {
			str.append("	");
		}
		if (curr instanceof InternalNode) {
			InternalNode innern = (InternalNode) curr;
			str.append(innern.toString() + "\n");
	        preorderTraversal(innern.left, height+1, str);
	        preorderTraversal(innern.right, height+1, str);
		}
		else {
			LeafNode m = (LeafNode)curr;
			str.append(m.toString() + "\n");	
		}
	}
	
	public String decode(Boolean[] coding) {
		if (root==null) {
			throw new IllegalArgumentException("Invalid encoding");
		}
		StringBuilder FinalChar = new StringBuilder();
		String x = bitsToString(coding);
		decodehelper(root, x, FinalChar);
		return FinalChar.toString();
	}
	
	private void decodehelper(Node curr, String x, StringBuilder FinalChar) {
		if (x.length()==0) {
			return;
		}
		
		if (curr instanceof InternalNode) {
			InternalNode n= (InternalNode)curr;
			if (Integer.parseInt(Character.toString(x.charAt(0)))==1 && n.right != null) {
				decodehelper(n.right, x.substring(1), FinalChar);
			}
			else {
				decodehelper(n.left, x.substring(1), FinalChar);
				
			}
		}
		else if (curr instanceof LeafNode) {
			LeafNode m = (LeafNode)curr;
			FinalChar.append(m.data);
			decodehelper(root, x, FinalChar);
		}
		else {
			throw new IllegalArgumentException("Invalid encoding");
		}
	}
	
	public Boolean[] encode(String inputText) {
		if (root==null) {
			throw new IllegalArgumentException("Input cannot be encoded");
		}
		StringBuilder found = new StringBuilder();
		StringBuilder scrap = new StringBuilder();
		StringBuilder output = new StringBuilder();
		for (int i =0; i< inputText.length(); i++) {
			encodehelper(root, inputText.charAt(i), scrap, found);
			output.append(scrap);
			scrap.setLength(0);
		}
		return stringtoBits(output.toString());
	}
	
	private void encodehelper(Node curr, char letter, StringBuilder scrap, StringBuilder found) {
		if (curr ==null) {
			scrap.append("null\n");
		}
		else {
			if (curr instanceof InternalNode) {
				InternalNode n= (InternalNode)curr;
				encodehelper(n.left, letter, scrap.append("0"), found);
				if (found.length()>0) {
					return;
				}
				if (scrap.length()>0) {
				scrap.setLength(scrap.length()-1);}
				encodehelper(n.right, letter, scrap.append("1"), found);
				if (found.length()>0) {
					return;
				}
				if (scrap.length()>1) {
					scrap.setLength(scrap.length()-2);
				}
		}
		else {
			LeafNode m = (LeafNode)curr;
				if (m.data == letter) {
					found.append(1);
				}
				else {
					found.setLength(0);
				}
			}
		}
	}
	
	public Boolean[] efficientEncode(String inputText) {
		if (root==null) {
			throw new IllegalArgumentException("Input cannot be encoded");
		}
		StringBuilder output = new StringBuilder();
		efficientEncodehelper(root, output);
		output.setLength(0);
		for (int i =0; i< inputText.length(); i++) {
				output.append(datavalue.get(Character.toString(inputText.charAt(i))));
			}
		return stringtoBits(output.toString());
		}
	
	public void efficientEncodehelper(Node curr, StringBuilder output) {
		if (curr==null) {
			output.append("null\n");
		}
		else {
			if (curr instanceof InternalNode) {
				InternalNode innern = (InternalNode)curr;
				efficientEncodehelper(innern.left, output.append("0"));
				output.setLength(output.length()-1);
				efficientEncodehelper(innern.right, output.append("1"));
				if (output.length()>2) {
					output.setLength(output.length()-2);
				}
		}
		else {
			LeafNode x = (LeafNode) curr;
			datavalue.put(Character.toString(x.data), output.toString());
		}
	}
	}
	
	public static void main(String[] args) {
		// Code to see if stuff works...
		String s = "Some string you want to encode";
		String l = "xxeeeeaaaaaaaaa";
		String encodethis = "xaeeaxe";
		HuffmanTree t = new HuffmanTree(l); // Creates specific Huffman Tree for "s"
		// Now you can use encode, decode, and toString to interact with your specific Huffman Tree
		//System.out.println(t.toString());
		String x = "101";
		System.out.println(t.toString());
		System.out.println(Arrays.toString(t.efficientEncode(encodethis)));
	}
}

