import java.util.*;

public class BinarySearchTreeWithDups<T extends Comparable<? super T>> extends BinarySearchTree<T> {

	public BinarySearchTreeWithDups() {
		super();
	}

	public BinarySearchTreeWithDups(T rootEntry) {
		super(rootEntry);
	}

	@Override
	public boolean add(T newEntry) {
		if (isEmpty()) {
			return super.add(newEntry);
		} else {
			return addEntryHelperNonRecursive(newEntry);
		}
	}

	// IMPLEMENT THIS METHOD; THIS METHOD CANNOT BE RECURSIVE
	private boolean addEntryHelperNonRecursive(T newEntry) {
		BinaryNode<T> currentNode = root;
		BinaryNode<T> newNode = new BinaryNode<>(newEntry);
		
		while (true) {
			// 1. Compare new element to current element
			int comparison = newEntry.compareTo(currentNode.getData());
			
			// 2, 4. If new element is smaller or equal (aka duplicate), go to left subtree
			if (comparison <= 0) {
				if (currentNode.hasLeftChild()) {
					currentNode = currentNode.getLeftChild();
				} 
				else {
					currentNode.setLeftChild(newNode);
					return true;
				}
			} 
			// 3. If new element is larger, go to right subtree
			else {
				if (currentNode.hasRightChild()) {
					currentNode = currentNode.getRightChild();
				}
				else {
					currentNode.setRightChild(newNode);
					return true;
				}
			}
		}
	}

	// THIS METHOD CANNOT BE RECURSIVE.
	// Make sure to take advantage of the sorted nature of the BST!
	public int countIterative(T target) {
		int count = 0;
		BinaryNode<T> currentNode = root;
		
	    while (currentNode != null) {
	    	// Begin comparing nodes from the root. Increment count if 
	    	// the currentNode matches the target
	        int comparison = target.compareTo(currentNode.getData());
	        if (comparison == 0) {
	            count++;
	            currentNode = currentNode.getLeftChild(); // Check for duplicates in left subtree
	        } 
	        // Go into left subtree if target < currentNode's data
	        else if (comparison < 0) {
	            currentNode = currentNode.getLeftChild(); 
	        } 
	        // Go into right subtree if target > currentNode's data
	        else { 
	            currentNode = currentNode.getRightChild();
	        }
	    }
		return count;
	}

	// THIS METHOD MUST BE RECURSIVE! 
	// You are allowed to create a private helper.
	// Make sure to take advantage of the sorted nature of the BST!
	
	public int countGreaterRecursive(T target) {
		return countGreaterRecursiveHelper(root, target);
	}
	
	private int countGreaterRecursiveHelper(BinaryNode<T> currentNode, T target) {
		if (currentNode == null) {
			return 0;
		}
		int count = 0;
		
		// If the current node is greater, include it and explore both subtrees
		if (currentNode.getData().compareTo(target) > 0) {
			count = 1 
					+ countGreaterRecursiveHelper(currentNode.getLeftChild(), target)
					+ countGreaterRecursiveHelper(currentNode.getRightChild(), target);
		}
		// Otherwise, skip left subtree and explore only the right subtree
		else {
			count = countGreaterRecursiveHelper(currentNode.getRightChild(), target);
		}
		
		return count;
	}

	// THIS METHOD CANNOT BE RECURSIVE.
	// Hint: use a stack!
	// Make sure to take advantage of the sorted nature of the BST!
	public int countGreaterIterative(T target) {
		int count = 0;
		Stack<BinaryNode<T>> stack = new Stack<BinaryNode<T>>();
		BinaryNode<T> currentNode = root;
		
		// Traverse the right subtree first as we are looking for larger values
		// Push each node onto the stack
		while (currentNode != null || !stack.isEmpty()) {
			while (currentNode != null) {
				stack.push(currentNode);
				currentNode = currentNode.getRightChild();
			}
			// Increment count if the node popped from the stack is greater than the target
			currentNode = stack.pop();
			if (currentNode.getData().compareTo(target) > 0) {
				count++;
				currentNode = currentNode.getLeftChild();
			}
			// Otherwise, skip left subtree if currentNode is <= to target
			else {
				currentNode = null;
			}
		}
		return count;
	}
			
	
	// For full credit, the method should be O(n).
	// You are allowed to use a helper method.
	// The method can be iterative or recursive.
	// If you make the method recursive, you might need to comment out the call to the method in Part B.
	public int countUniqueValues() {
		// YOUR EXTRA CREDIT CODE HERE! 
		return 0; // placeholder: replace with your own code
	}

}