/**
 * The SearchTree class extends the Treap data structure and modifies it by tracking search counts
 * for each node. Nodes with higher search counts are moved upwards in the tree to allow for faster
 * future access, thus combining the properties of a treap with frequency-based reordering.
 */
public class SearchTree extends Treap {

	/**
	 * The SearchTreeNode class extends TreapNode, adding an additional field `searchCount` to track
	 * how many times the node has been searched.
	 */
	private static class SearchTreeNode extends TreapNode {
		int searchCount; // Tracks the number of times the node has been searched

		/**
		 * Constructor for SearchTreeNode, initializing with a key, heap value, and setting searchCount to 1.
		 *
		 * @param key       The key for this node.
		 * @param heapValue The heap value for this node.
		 */
		public SearchTreeNode(String key, int heapValue) {
			super(key, heapValue);
			this.searchCount = 1; // Initial search count is 1 upon node creation
		}
	}

	// ==================== Add() Method ====================

	/**
	 * Overrides the add method to insert a key into the search tree. Generates a random heap value for
	 * each node, and ensures that duplicate keys are not added.
	 *
	 * @param key The key to be added.
	 * @return    True if the key was successfully added, false otherwise (e.g., if key is invalid or already exists).
	 */
	public boolean add(String key) {
		if (!isValidKey(key)) return false; // Only disallow null keys, empty strings are acceptable.

		// If the key already exists, do not add it again.
		if (find(key)) return false;

		int generatedHeapValue = generateRandomHeapValue();  // Generate a random heap value for the node
		root = insertNode(root, key.toLowerCase(), generatedHeapValue);  // Insert the node
		count++; // Increase the count of nodes
		return true;
	}

	/**
	 * Helper method to generate a random heap value for a new node.
	 *
	 * @return A random integer between 0 and 99 to be used as the heap value.
	 */
	private int generateRandomHeapValue() {
		return (int) (Math.random() * 100);  // Generates a heap value in the range [0, 99]
	}

	// ==================== Find() Method ====================

	/**
	 * Overrides the find method to search for a key in the search tree. If the key is found, the
	 * search count of the corresponding node is incremented, and the tree is updated to reflect the
	 * increased frequency.
	 *
	 * @param key The key to search for.
	 * @return    True if the key is found, false otherwise.
	 */
	@Override
	public boolean find(String key) {
		if (!isValidKey(key)) return false;

		boolean found = findRecursive(root, key.toLowerCase());
		if (found) {
			// Find the node with the given key and increment its search count
			SearchTreeNode searchNode = (SearchTreeNode) findNode(root, key.toLowerCase());
			if (searchNode != null) {
				searchNode.searchCount++;  // Increment the search count for the found node
				// Rebalance the tree based on the updated search frequency
				root = updateTreeBasedOnFrequency((SearchTreeNode) root, key.toLowerCase());
			}
		}
		return found;
	}

	// ==================== Remove() Method ====================

	/**
	 * Overrides the remove method to delete a node from the search tree. The method first checks if
	 * the key exists, and if it does, it removes the corresponding node.
	 *
	 * @param key The key of the node to be removed.
	 * @return    True if the node was successfully removed, false otherwise.
	 */
	@Override
	public boolean remove(String key) {
		if (!isValidKey(key) || !find(key)) return false;
		return super.remove(key); // Call the remove method from the superclass (Treap)
	}

	// ==================== Helper Methods ====================

	/**
	 * Helper method to insert a new SearchTreeNode into the tree.
	 *
	 * @param node      The current node in the tree.
	 * @param key       The key to be inserted.
	 * @param heapValue The heap value of the node.
	 * @return          The updated root of the subtree after insertion.
	 */
	@Override
	protected TreapNode insertNode(TreapNode node, String key, int heapValue) {
		if (node == null) return new SearchTreeNode(key, heapValue); // Create a SearchTreeNode instead of TreapNode
		return super.insertNode(node, key, heapValue); // Call the insertNode method from the superclass (Treap)
	}

	/**
	 * Updates the tree by reordering nodes based on their search frequencies. If a child node has a
	 * higher search count than its parent, a rotation is performed to bring the child closer to the root.
	 *
	 * @param node The root of the subtree to be updated.
	 * @param key  The key of the node whose frequency has changed.
	 * @return     The updated root of the subtree after rebalancing.
	 */
	private SearchTreeNode updateTreeBasedOnFrequency(SearchTreeNode node, String key) {
		if (node == null) return null;

		if (key.equals(node.key)) return node;

		// Update the subtree and check whether rotation is needed based on search counts
		if (key.compareTo(node.key) < 0) {
			return updateChildNodeFrequency(node, key, true); // True indicates left child
		} else {
			return updateChildNodeFrequency(node, key, false); // False indicates right child
		}
	}

	/**
	 * Updates the frequency count of a child node and performs rotations if the search count of the child
	 * exceeds that of the parent.
	 *
	 * @param node    The parent node whose child needs frequency update.
	 * @param key     The key of the node whose search count has changed.
	 * @param isLeft  True if the child is a left child, false if it's a right child.
	 * @return        The updated parent node after potential rotations.
	 */
	private SearchTreeNode updateChildNodeFrequency(SearchTreeNode node, String key, boolean isLeft) {
		if (isLeft) {
			// Update left child and check if rotation is needed
			node.left = updateTreeBasedOnFrequency((SearchTreeNode) node.left, key);
			if (node.left != null && ((SearchTreeNode) node.left).searchCount > node.searchCount) {
				node = (SearchTreeNode) rightRotate(node); // Perform right rotation if left child's search count is greater
			}
		} else {
			// Update right child and check if rotation is needed
			node.right = updateTreeBasedOnFrequency((SearchTreeNode) node.right, key);
			if (node.right != null && ((SearchTreeNode) node.right).searchCount > node.searchCount) {
				node = (SearchTreeNode) leftRotate(node); // Perform left rotation if right child's search count is greater
			}
		}
		return node;
	}

	/**
	 * Helper method to find a node in the tree by its key.
	 *
	 * @param node The current node in the search tree.
	 * @param key  The key of the node to be found.
	 * @return     The node with the given key, or null if the key is not found.
	 */
	private TreapNode findNode(TreapNode node, String key) {
		if (node == null) return null;

		if (key.equals(node.key)) return node;

		return key.compareTo(node.key) < 0
				? findNode(node.left, key)   // Search in the left subtree if key is smaller
				: findNode(node.right, key); // Search in the right subtree if key is larger
	}
}
