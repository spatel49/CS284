package classes;

import java.util.Random;
import java.util.Stack;

/**
 * 
 * @author Siddhanth Patel
 * 4/28/2019
 * @param <E>
 */

public class Treap<E extends Comparable<E>> {
	/**
	 * Private inner class Node
	 *
	 * @param <E> Node type
	 */
	private static class Node<E>{
		//Data Fields
		public E data; // key for the search
		public int priority; // random heap priority
		public Node <E> left;
		public Node <E> right;
		
		//Constructors
		/**
		 * Creates new Node with data and priority set to parameters
		 * and left/right children set to null.
		 * @param data - key value
		 * @param priority - priority value
		 */
		public Node(E data, int priority) {
			if (data==null) {
				throw new NullPointerException("Data is null");
			}
			this.data = data;
			this.priority = priority;
			this.left = null;
			this.right = null;
		}
		
		//Methods
		/*
		 * Performs a right rotation, returning a reference to the root of the result
		 * @return reference to the root of the result
		 */
		public Node <E> rotateRight() {
			Node<E> temp = this;
			temp = temp.left;
			this.left = temp.right;
			temp.right= this;
			return temp;
		}
		//Methods
				/*
				 * Performs a left rotation, returning a reference to the root of the result
				 * @return reference to the root of the result
				 */
		public Node<E> rotateLeft(){
		Node <E> temp = this;
		temp = temp.right;
		this.right = temp.left;
		temp.left = this;
		return temp;
		}
		
		@Override
        public String toString() {
            return "(key=" +data+", priority=" +priority+ ")";
        }
	}
	
	// Data Fields
	private Random priorityGenerator;
    private Node<E> root;
    protected boolean deleted;
    
    //Constructors
    /**
     * Creates an empty Treap
     */
    public Treap() {
    	root = null;
    	priorityGenerator = new Random();
    	
    /**
     * Creates an empty Treap with random priority
     */
    }
    public Treap(long seed) {
    	root= null;
    	priorityGenerator = new Random(seed);
    }
    
    //Methods
    /**
     * Add method that creates a random priority and calls on another add method
     * @param key
     * @return
     */
    public boolean add(E key) {
    	 int randomNumber = priorityGenerator.nextInt();
         return add(key, randomNumber);
    }
    
    /**
     * Adds Node to the treap by cycling through the treap until the appropriate parent leaf.
     * @param key data value of item being added
     * @param priority priority value of item being added
     * @return true if added, false if it is not added
     */
    public boolean add(E key, int priority) {
    	Stack<Node<E>> nodeStack = new Stack<Node<E>>();
    	boolean added= false;
    	Node<E> holder = root;
    	while (added == false) {
    		if (holder==null) {
    	    	holder = new Node<E>(key, priority);
    	    	added = true;
    	    	break;
    		}
    		if (key.compareTo(holder.data)==0) {
    			break;
    		}
    		if (key.compareTo(holder.data)<0) {
    			nodeStack.push(holder);
    			if (holder.left==null) {
    				holder.left = new Node<E>(key, priority);
    				holder=holder.left;
    				added = true;
    			}
    			else {
    				holder = holder.left;
    			}
    		}
    		else {
    			nodeStack.push(holder);
    			if (holder.right == null) {
    				holder.right = new Node<E>(key, priority);
    				holder=holder.right;
    				added = true;
    			} else {
    				holder = holder.right;
    				}
    			}
    		}
    	reheap(holder, nodeStack);
    	return added;
    }
   
    /**
     * Alters the tree to match max heap properties
     * @param holder current node being used
     * @param nodeStack stack with all prior nodes from adding the new node
     */
   private void reheap(Node<E> holder, Stack<Node<E>> nodeStack) {
	   if (nodeStack.empty()) {
           root = holder;
           return;
       } else if (nodeStack.peek().priority > holder.priority) {
           return;
    	} else {
            Node<E> nodefromStack = nodeStack.pop();
            if (!nodeStack.empty()) {
                if (nodeStack.peek().left != null && nodeStack.peek().left.equals(nodefromStack)) {
                    nodeStack.peek().left = holder;
                } else {
                    nodeStack.peek().right = holder;
                }
            }
            if (nodefromStack.left != null && nodefromStack.left.equals(holder)) {
            	nodefromStack.rotateRight();
            } else {
            	nodefromStack.rotateLeft();
            }
            reheap(holder, nodeStack);}
    }
   
   /**
    * Finds the node and then rotates it until it becomes a leaf. It then deletes that node.
    * @param key item that is deleted
    * @param root node that is being compared with
    * @return true if deleted, false if not deleted
    */
    public Node<E> delete(E key, Node<E> root){
    	if(root==null) {
    		deleted = false;
    		return root;
    	}
    	else {
    		int compare = root.data.compareTo(key);
    		if(compare < 0) {
    			root.right = delete(key,root.right);
    		}
    		else {
    			if(compare >0) {
    				root.left = delete(key,root.left);
    			}
    			else {
    				deleted = true;
    				if(root.right==null) {
    					root=root.left;
    				}
    				else if(root.left==null) {
    					root=root.right;
    				}
    				else {
    					if(root.right.priority < root.left.priority) {
    						root=root.rotateRight();
    						root.right=delete(key,root.right);
    					}
    					else {
    						root=root.rotateLeft();
    						root.left = delete(key,root.left);
    					}
    				}
    			}
    		}
    	}
    	return root;
    }
    /**
     * Deletes the node by calling another delete function
     * @param key the item being used
     * @return true if deleted, false if not deleted
     */
    public boolean delete(E key) {
        	root = delete(key, root);
        	return deleted;
        	}
    
    /**
     * Finds the item in the treap
     * @param root the Node being compared with
     * @param key item being looked for
     * @return true if it is found, false otherwise
     */
    private boolean find(Node<E> root, E key) {
    	if (root == null) {
    		return false;
    	}
    	int compare = root.data.compareTo(key);
    	return compare == 0;
    }
    
    /**
     *  Finds the item in the treap
     * @param key item being looked for
     * @return true if it is found, false otherwise
     */
    
    public boolean find(E key) {
    	if (root == null) {
    		return false;
    	}
        while (!find(root, key) && root != null) {
        	int compare = root.data.compareTo(key);
        	if (compare < 0) {
        		root = root.right;
	        }
	        else {
	        	root = root.left;
	        }
        }
        return find(root, key);
    }
    
    /**
     * Helper function that returns string version of the treap
     * @param holder - root of the treap
     * @param k - place to start in the treap
     * @return a readable string version of the treap
     */
    private String toString(Node<E> holder, int k) {
        StringBuilder treapstring = new StringBuilder();
        for (int i = 0; i < k; i++) {
        	treapstring.append("  ");
        }
        if (holder == null) {
        	treapstring.append("null");
            return treapstring.toString();
        }
        treapstring.append(holder.toString());
        treapstring.append("\n");
        treapstring.append(toString(holder.left, k+1));
        treapstring.append("\n");
        treapstring.append(toString(holder.right, k+1));
        return treapstring.toString();
    }
    /**
     * Returns a readable string version of the treap
     */
    @Override
    public String toString(){
        return toString(root, 0);
    }
    
    public static void main(String [] args) {
    	Treap<Integer>testTree = new Treap<Integer>();
    	testTree.add (4 ,14);
    	testTree.add (2 ,31);
    	testTree.add (6 ,70);
    	testTree.add (1 ,84);
    	testTree.add (3 ,12);
    	testTree.add (5 ,83);
    	testTree.add (7 ,26);
    	System.out.println(testTree.toString());
    	System.out.println(testTree.add(17,26));
    	System.out.println(testTree.delete(4));
    	System.out.println(testTree.find(7));
    	
    }
    
}
