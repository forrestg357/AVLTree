import java.util.LinkedList;
import java.util.Queue;

public class AVLTree {
	
	private class Node{
		
		//Node instance variables
		
		private Integer data;
		private int depth;
		private Node right;
		private Node left;
		
		//Node constructor 1
		private Node() {
			this.data = null;
			this.right = null;
			this.left = null;
			this.depth = 0;
		}
		
		//Node constructor 2
		private Node(Integer data) {
			this.data = data;
			this.right = null;
			this.left = null;
			this.depth = 0;
			
		}
		
		//Node constructor 3
		private Node(Integer data, Node right, Node left) {
			this.data = data;
			this.right = right;
			this.left = left;
			this.depth = 0;
			
		}
	}
	
	private Node root;

	
	//AVLTree Constructor 1
	public AVLTree(Integer data) {
		root = new Node(data);
		
	}
	
	//AVLTree Constructor 2
	public AVLTree() {
		root = null;
		
	}
	
	//Get depth of the tree
	public int getDepth() {
		return root == null ? -1 : root.depth;
		
	}
	
	//put data 
	
	//Although this uses the contain method, thus making the method 2logn time, it makes for cleaner
	//code, does not sacrifice too much time efficiency, and gets rid of an else in the helper
	
	public boolean put(Integer num) {
		if (contains(num, root) || num == null) {
			return false;
			
		}
		root = put(num, root);
		return true;
		
	}

	//Helper method for put data
	private Node put(Integer num, Node currentNode) {
		if (currentNode == null) {
			currentNode = new Node(num);
			return currentNode;
			
		}

		if (num > currentNode.data) {
			currentNode.right = put(num, currentNode.right);
			
		} else if (num < currentNode.data) {
			currentNode.left = put(num, currentNode.left);
			
		}
		
		currentNode = updateNode(currentNode);
		return currentNode;
	}
	
	//Gets the balance of a node
	private int getBalance(Node node) {
		return ((node.right == null ? -1 : node.right.depth) - (node.left == null ? -1 : node.left.depth));
		
	}
	
	
	//Updates the depth of a node
	private void updateDepth(Node node) {
		node.depth = Math.max((node.left == null ? -1 : node.left.depth),  (node.right == null ? -1 : node.right.depth)) + 1;
	}
	
	//Balances the tree
	//4 conditions: Left rotate, right rotate, left-right rotate, right-left rotate
	private Node balanceTree(Node currentNode) {
		if (getBalance(currentNode) < -1 && getBalance(currentNode.left) <= 0) {
			currentNode = rightRotate(currentNode);
			
		}else if (getBalance(currentNode) > 1 && getBalance(currentNode.right) >= 0) {
			currentNode = leftRotate(currentNode);
			
		}else if (getBalance(currentNode) > 1 && getBalance(currentNode.right) < 0) {
			currentNode.right = rightRotate(currentNode.right);
			currentNode = leftRotate(currentNode);
			
		}else if (getBalance(currentNode) < -1 && getBalance(currentNode.left) > 0) {
			currentNode.left = leftRotate(currentNode.left);
			currentNode = rightRotate(currentNode);
			
		}
		return currentNode;
		
	}
	
	//Right rotate
	private Node rightRotate(Node currentNode) {
        Node left = currentNode.left;
        Node leftRight = left.right;
 
        left.right = currentNode;
        currentNode.left = leftRight;
 
        //Update depths
        updateDepth(currentNode);
        updateDepth(left);
        return left;
        
	}
	
	//Left rotate
	private Node leftRotate(Node currentNode) {
		Node right = currentNode.right;
        Node rightLeft = right.left;
 
        right.left = currentNode;
        currentNode.right = rightLeft;
 
        // Update depths
        updateDepth(currentNode);
        updateDepth(right);
        return right;
        
	}
	
	//Updates a node by fixing the depths and checking if balancing is necessary
	private Node updateNode(Node currentNode) {
		updateDepth(currentNode);
		int balance = getBalance(currentNode);
		if (Math.abs(balance) > 1) {
			currentNode = balanceTree(currentNode);

		}
		return currentNode;
	}
	
	//Note for orders: as per the convention decided in class, every number in a given order will have a space
	//after it, including the last number listed
	
	//preOrder 
	public String preOrder() {
		return preOrder("", root);
		
	}
	
	//preOrder helper
	private String preOrder(String output, Node currentNode) {
		if (currentNode == null) {
			return output;
			
		}
		output += currentNode.data + " ";
		output = preOrder(output, currentNode.left);
		output = preOrder(output, currentNode.right);
		return output;
		
	}
	
	//inOrder
	public String inOrder() {
		return inOrder("", root);
	}
	
	//inOrder helper
	private String inOrder(String output, Node currentNode) {
		if (currentNode == null) {
			return output;
			
		}
		output = inOrder(output, currentNode.left);
		output += currentNode.data + " ";
		output = inOrder(output, currentNode.right);
		return output;
		
	}
	
	//postOrder
	public String postOrder() {
		return postOrder("", root);
	}
	
	//postOrder helper
	private String postOrder(String output, Node currentNode) {
		if (currentNode == null) {
			return output;
			
		}
		output = postOrder(output, currentNode.left);
		output = postOrder(output, currentNode.right);
		output += currentNode.data + " ";
		return output;
		
	}
	

	//levelOrder requires a queue with the root node
	public String levelOrder() {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		return levelOrder("", queue);
	}
	
	//levelOrder helper method
	private String levelOrder(String string, Queue<Node> queue) {
		//Recurses depth-wise through the tree, until all nodes/levels are printed
		if (!queue.isEmpty()) {
			
			Node currentNode = queue.poll();

			string += currentNode.data + " ";
			
			if (currentNode.left != null) {
				queue.add(currentNode.left);
			}
			if (currentNode.right != null) {
				queue.add(currentNode.right);
			}
			
			string = levelOrder(string, queue);
		}
		
		return string;
		

	}
	
	//size
	public int size() {
		return size(root);
	}

	//size helper
	private int size(Node currentNode) {
		
        if (currentNode == null) {
            return 0;
            
        }
        else {
           return (size(currentNode.left) + size(currentNode.right)) + 1;
           
        }
	}
	
	//Checks to see if the root is null -> the tree is empty
	public boolean isEmpty() {
		return root == null;
		
	}
	
	//max
	public int max() {
		return max(root);
	}
	
	//max helper
	private int max(Node currentNode) {
		if (currentNode.right == null) {
			return currentNode.data;
		}
		return max(currentNode.right);
		
	}

	//min
	public int min() {
		return min(root);
	}
	
	//min helper
	private int min(Node currentNode) {
		if (currentNode.left == null) {
			return currentNode.data;
		}
		return min(currentNode.left);
	}

	//delete
	public void delete(Integer num) {
		if (num == null) return;
		root = delete(num, root);
		
	}
	
	//delete helper method
	private Node delete(Integer num, Node currentNode) {
		if (currentNode == null) {
			return currentNode;
		}
		
		if (currentNode.data.equals(num)) {
			currentNode = replaceDeleted(currentNode);
			
			
		}else {
			if (num > currentNode.data) { //Searching for the delete-num (on right)
				currentNode.right = delete(num, currentNode.right);
				
				
			} else if (num < currentNode.data) { //Searching for the delete-num (on left)
				currentNode.left = delete(num, currentNode.left);
				
			}
		}
		
		if (currentNode != null) {
			currentNode = updateNode(currentNode);
		}
		return currentNode;
		
		
	}
	
	//Replaces the deleted node and fixes the tree
	private Node replaceDeleted(Node currentNode) {
		
		//Finding the value we will use to replace the deleted node value
		if (getBalance(currentNode) > 0) {
			if (currentNode.right == null) {
				currentNode = currentNode.left;
				
			}else {
				currentNode.data = replaceData(currentNode.right, false);
				currentNode.right = updateDeletePath(currentNode.right, false);
				
			}
			
		}else if (getBalance(currentNode) <= 0) { //Convention: we take from the left if the balance is 0
			if (currentNode.left == null) {
				currentNode = currentNode.right;
				
			}else {
				currentNode.data = replaceData(currentNode.left, true);
				currentNode.left = updateDeletePath(currentNode.left, true);
				
			}
		}
		
		return currentNode;
		
	}

	//Replaces the data of the deleted node
	private Integer replaceData(Node currentNode, boolean right) {
		if (right) {
			if (currentNode.right == null) {
				Integer data = currentNode.data;
				return data;
			}
			return replaceData(currentNode.right, right);
			
		}else {
			if (currentNode.left == null) {
				Integer data = currentNode.data;
				return data;
			}
			return replaceData(currentNode.left, right);
			
		}
	}
	
	//deletes either the rightmost on the left or the leftmost on the right and updates the tree
	private Node updateDeletePath(Node currentNode, boolean right) {
		if (right) {
			if (currentNode.right == null) {
				currentNode = currentNode.left;
				return currentNode;

			}else {
				currentNode.right = updateDeletePath(currentNode.right, right);
			}
			
		}else {
			if (currentNode.left == null) {
				currentNode = currentNode.right;
				return currentNode;
				
			}else {
				currentNode.left = updateDeletePath(currentNode.left, right);
			}
			
		}
		
		if (currentNode != null) { //Updates the tree after deleting node we use to replace the "root"
			currentNode = updateNode(currentNode);
			
		}
		return currentNode;
	}

	
	//contains
	public boolean contains(Integer i) {
		return contains(i, root);
		
	}
	
	//contains helper
	private boolean contains(Integer i, Node currentNode) {
		if (currentNode == null) {
			return false;
			
		}else {
			if (currentNode.data.equals(i)) {
				return true;
				
			}
			return contains(i, currentNode.right) || contains(i, currentNode.left);
			
		}
	}	
}
