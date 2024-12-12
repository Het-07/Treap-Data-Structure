/**
 * TreapNode class represents a node in a treap data structure.
 * Each node contains a key (string), a heap value (integer), and references
 * to its left and right children.
 */
public class TreapNode {

	// The string to store (converted to lowercase for case-insensitive comparison)
	String key;

	// Priority value used to maintain the heap property of the treap
	int heapValue;

	// Reference to the left child of this node
	TreapNode left;

	// Reference to the right child of this node
	TreapNode right;

	/**
	 * Constructs a new TreapNode with the specified key and heap value.
	 * The key is stored as lowercase to ensure case-insensitive comparison.
	 *
	 * @param key       The key to be stored in this node.
	 * @param heapValue The priority value for maintaining the heap property.
	 */
	public TreapNode(String key, int heapValue) {
		this.key = key.toLowerCase();  // Store the key as lowercase for case-insensitive comparison
		this.heapValue = heapValue;     // Set the heap value
		this.left = null;               // Initialize left child reference to null
		this.right = null;              // Initialize right child reference to null
	}
}
