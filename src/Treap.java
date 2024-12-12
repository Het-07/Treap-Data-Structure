import java.util.ArrayList;
import java.util.List;

/**
 * The Treap class implements a treap data structure, which combines the properties of a binary search tree
 * (with respect to keys) and a max-heap (with respect to heap values). It supports operations such as adding nodes,
 * removing nodes, changing heap order, finding keys, and retrieving the path from a key to the root.
 *
 * Implements the Searchable interface.
 */
public class Treap implements Searchable {

	// Root node of the treap
	protected TreapNode root;

	// Number of nodes currently in the treap
	protected int count;

	/**
	 * Constructor to initialize an empty treap.
	 */
	public Treap() {
		this.root = null;
		this.count = 0;
	}

	// ==================== Rotations ====================

	/**
	 * Performs a right rotation on the given node y, which becomes y's left child.
	 *
	 * @param y The node to rotate.
	 * @return  The new root of the subtree after the rotation.
	 */
	protected TreapNode rightRotate(TreapNode y) {
		TreapNode x = y.left;
		y.left = x.right;
		x.right = y;
		return x;
	}

	/**
	 * Performs a left rotation on the given node x, which becomes x's right child.
	 *
	 * @param x The node to rotate.
	 * @return  The new root of the subtree after the rotation.
	 */
	protected TreapNode leftRotate(TreapNode x) {
		TreapNode y = x.right;
		x.right = y.left;
		y.left = x;
		return y;
	}

	// ==================== Helper Methods ====================

	/**
	 * Helper method to check if a key is valid (not null).
	 *
	 * @param key The key to check.
	 * @return    True if the key is valid, false otherwise.
	 */
	protected boolean isValidKey(String key) {
		return key != null;
	}

	/**
	 * Helper method to find if a heap value already exists in the treap to prevent duplicates.
	 *
	 * @param node      The current node.
	 * @param heapValue The heap value to check for duplicates.
	 * @return          True if the heap value is found, false otherwise.
	 */
	private boolean findDuplicateHeapValue(TreapNode node, int heapValue) {
		if (node == null) return false;
		return node.heapValue == heapValue ||
				findDuplicateHeapValue(node.left, heapValue) ||
				findDuplicateHeapValue(node.right, heapValue);
	}

	// ==================== Add Method ====================

	/**
	 * Adds a new node with the given key and heap value to the treap.
	 *
	 * Ensures the treap maintains both the binary search tree and heap properties.
	 *
	 * @param key       The key to be added.
	 * @param heapValue The associated heap value.
	 * @return          True if the node was added, false otherwise.
	 */
	public boolean add(String key, int heapValue) {
		if (isValidKey(key) && heapValue > 0 && !findDuplicateHeapValue(root, heapValue)) {
			root = insertNode(root, key.toLowerCase(), heapValue);
			count++;
			return true;
		}
		return false;
	}

	/**
	 * Helper method to insert a new node into the treap.
	 *
	 * @param node      The current node in the treap.
	 * @param key       The key to be added.
	 * @param heapValue The heap value of the new node.
	 * @return          The root of the subtree after insertion.
	 */
	protected TreapNode insertNode(TreapNode node, String key, int heapValue) {
		if (node == null) return new TreapNode(key, heapValue);

		if (key.compareTo(node.key) < 0) node.left = insertNode(node.left, key, heapValue);
		else node.right = insertNode(node.right, key, heapValue);

		// Perform rotations to maintain heap property
		if (node.left != null && node.left.heapValue > node.heapValue) node = rightRotate(node);
		if (node.right != null && node.right.heapValue > node.heapValue) node = leftRotate(node);

		return node;
	}

	// ==================== Build Method ====================

	/**
	 * Builds the treap efficiently from arrays of keys and heap values.
	 *
	 * @param keys       Array of keys to be inserted.
	 * @param heapValues Array of heap values corresponding to the keys.
	 * @return           True if the treap was built successfully, false otherwise.
	 */
	public boolean build(String[] keys, int[] heapValues) {
		if (keys == null || heapValues == null || keys.length != heapValues.length) return false;
		root = null;
		count = 0;
		for (int i = 0; i < keys.length; i++) add(keys[i], heapValues[i]);
		return true;
	}

	// ==================== Find Method ====================

	/**
	 * Finds if a node with the given key exists in the treap.
	 *
	 * @param key The key to search for.
	 * @return    True if the key is found, false otherwise.
	 */
	public boolean find(String key) {
		return isValidKey(key) && findRecursive(root, key.toLowerCase());
	}

	/**
	 * Helper method to recursively find a node by key.
	 *
	 * @param node The current node in the treap.
	 * @param key  The key to search for.
	 * @return     True if the key is found, false otherwise.
	 */
	protected boolean findRecursive(TreapNode node, String key) {
		if (node == null) return false;
		if (key.equals(node.key)) return true;
		return key.compareTo(node.key) < 0 ? findRecursive(node.left, key) : findRecursive(node.right, key);
	}

	// ==================== FindPath Method ====================

	/**
	 * Finds the path from the node with the given key to the root of the treap.
	 *
	 * @param key The key whose path to the root is to be found.
	 * @return    A list of keys representing the path from the node to the root, or null if the key is not found.
	 */
	public List<String> findPath(String key) {
		if (!isValidKey(key)) return null;

		String[] path = new String[100]; // Fixed-size array to hold the path
		int[] index = {0}; // To keep track of the path length

		if (findPathRecursive(root, key.toLowerCase(), path, index)) {
			return reverseToList(path, index[0]);
		} else {
			return null;
		}
	}

	/**
	 * Helper method to recursively find the path to a node by key.
	 *
	 * @param node  The current node in the treap.
	 * @param key   The key to find the path for.
	 * @param path  Array to store the path.
	 * @param index Current index in the path array.
	 * @return      True if the path is found, false otherwise.
	 */
	protected boolean findPathRecursive(TreapNode node, String key, String[] path, int[] index) {
		if (node == null) return false;

		path[index[0]] = node.key;
		index[0]++;

		if (key.equals(node.key)) return true;

		if (key.compareTo(node.key) < 0) {
			if (findPathRecursive(node.left, key, path, index)) return true;
		} else {
			if (findPathRecursive(node.right, key, path, index)) return true;
		}

		index[0]--;
		return false;
	}

	/**
	 * Helper method to reverse the path array and convert it to a list.
	 *
	 * @param path   Array of keys representing the path.
	 * @param length The length of the valid path.
	 * @return       A list of keys representing the path in reverse order (from node to root).
	 */
	private List<String> reverseToList(String[] path, int length) {
		List<String> reversedPath = new ArrayList<>();
		for (int i = length - 1; i >= 0; i--) {
			reversedPath.add(path[i]);
		}
		return reversedPath;
	}

	// ==================== ChangeOrder Method ====================

	/**
	 * Changes the heap value of a node while maintaining the treap's properties.
	 *
	 * Removes the node, changes its heap value, and re-inserts it into the treap.
	 *
	 * @param key         The key whose heap value is to be changed.
	 * @param newHeapValue The new heap value.
	 * @return            True if the order was successfully changed, false otherwise.
	 */
	public boolean changeOrder(String key, int newHeapValue) {
		if (!isValidKey(key) || newHeapValue <= 0 || !find(key)) return false;
		remove(key);
		root = insertNode(root, key.toLowerCase(), newHeapValue);
		count++;
		return true;
	}

	// ==================== Remove Method ====================

	/**
	 * Removes a node with the given key from the treap.
	 *
	 * @param key The key of the node to be removed.
	 * @return    True if the node was removed, false otherwise.
	 */
	public boolean remove(String key) {
		if (!isValidKey(key) || !find(key)) return false;
		root = deleteNode(root, key.toLowerCase());
		count--;
		return true;
	}

	/**
	 * Helper method to recursively delete a node from the treap.
	 *
	 * @param node The current node in the treap.
	 * @param key  The key of the node to be deleted.
	 * @return     The root of the subtree after deletion.
	 */
	protected TreapNode deleteNode(TreapNode node, String key) {
		if (node == null) return null;

		if (key.compareTo(node.key) < 0) node.left = deleteNode(node.left, key);
		else if (key.compareTo(node.key) > 0) node.right = deleteNode(node.right, key);
		else {
			if (node.left == null && node.right == null) return null;
			if (node.left == null) return node.right;
			if (node.right == null) return node.left;

			if (node.left.heapValue > node.right.heapValue) {
				node = rightRotate(node);
				node.right = deleteNode(node.right, key);
			} else {
				node = leftRotate(node);
				node.left = deleteNode(node.left, key);
			}
		}
		return node;
	}

	// ==================== Size Method ====================

	/**
	 * Returns the number of nodes in the treap.
	 *
	 * @return The number of nodes in the treap.
	 */
	public int size() {
		return count;
	}

	// ==================== Display Tree ====================

	/**
	 * Displays the treap structure in a human-readable format.
	 */
	public void displayTree() {
		if (root == null) {
			System.out.println("Tree is empty.");
		} else {
			displayTreeRecursive(root, "", true);
		}
	}

	/**
	 * Helper method to recursively display the treap structure.
	 *
	 * @param node  The current node in the treap.
	 * @param prefix The prefix for formatting the output.
	 * @param isTail True if this is the last child, false otherwise.
	 */
	protected void displayTreeRecursive(TreapNode node, String prefix, boolean isTail) {
		System.out.println(prefix + (isTail ? "└── " : "├── ") + node.key + " (" + node.heapValue + ")");

		List<TreapNode> children = new ArrayList<>();
		if (node.left != null) children.add(node.left);
		if (node.right != null) children.add(node.right);

		for (int i = 0; i < children.size(); i++) {
			boolean isLastChild = (i == children.size() - 1);
			displayTreeRecursive(children.get(i), prefix + (isTail ? "    " : "│   "), isLastChild);
		}
	}

	// ==================== Display Path ====================

	/**
	 * Displays the path from a node to the root.
	 *
	 * @param path The path from the node to the root.
	 */
	public void displayPath(List<String> path) {
		if (path == null || path.isEmpty()) {
			System.out.println("No path exists.");
		} else {
			System.out.println("Path: " + String.join(" -> ", path));
		}
	}
}