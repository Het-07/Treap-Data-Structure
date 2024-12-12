import java.util.List;

/**
 * Searchable interface represents the operations for a treap-like data structure,
 * where each node has a key (string) and a heap value (integer).
 * The interface defines methods to add, find, remove, and manipulate the treap.
 */
public interface Searchable {

	/**
	 * Adds a new node to the treap with the given key and heap value.
	 * The treap must maintain both binary search tree (BST) properties
	 * (with respect to the key) and max-heap properties (with respect to the heap value).
	 *
	 * @param key       The key to be added.
	 * @param heapValue The associated heap value.
	 * @return          True if the key was added successfully, false if the key already exists.
	 */
	boolean add(String key, int heapValue);

	/**
	 * Efficiently builds the treap using an array of keys and an array of corresponding heap values.
	 * This method aims to construct the treap from a bulk of data rather than adding keys individually.
	 *
	 * @param keys      Array of keys to be inserted into the treap.
	 * @param heapValues Array of heap values corresponding to each key.
	 * @return           True if the treap was built successfully, false otherwise (e.g., arrays of unequal length).
	 */
	boolean build(String[] keys, int[] heapValues);

	/**
	 * Finds whether a node with the given key exists in the treap.
	 *
	 * @param key The key to be searched for.
	 * @return    True if the key exists, false otherwise.
	 */
	boolean find(String key);

	/**
	 * Returns the path from the node with the given key to the root of the treap.
	 * If the key does not exist, an empty list or null is returned.
	 * The path is represented as a list of keys, starting from the specified key and ending at the root.
	 *
	 * @param key The key whose path to the root is to be returned.
	 * @return    A list of keys representing the path from the node to the root.
	 */
	List<String> findPath(String key);

	/**
	 * Changes the heap value of an existing node in the treap while maintaining
	 * the heap properties of the structure. This might require rotations to restore the heap order.
	 *
	 * @param key       The key whose heap value is to be changed.
	 * @param heapValue The new heap value for the key.
	 * @return          True if the heap value was changed successfully, false if the key does not exist.
	 */
	boolean changeOrder(String key, int heapValue);

	/**
	 * Removes a node with the specified key from the treap. The tree's structure must
	 * be adjusted to maintain both the BST and heap properties after removal.
	 *
	 * @param key The key to be removed.
	 * @return    True if the key was removed successfully, false if the key does not exist.
	 */
	boolean remove(String key);

	/**
	 * Returns the number of nodes currently present in the treap.
	 *
	 * @return The size of the treap (number of nodes).
	 */
	int size();
}
