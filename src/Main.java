import java.util.List;

/**
 * The Main class demonstrates the usage of the SearchTree class. It shows how to:
 * - Build the SearchTree with initial nodes.
 * - Add new nodes with specified keys and heap values.
 * - Find the path to a node.
 * - Display the tree structure.
 * - Remove a node from the tree.
 */
public class Main {
	public static void main(String[] args) {
		// Create an instance of SearchTree
		SearchTree searchTree = new SearchTree();

		// Define the initial keys and heap values to insert into the SearchTree
		String[] keys = {"Hot", "Bed", "Lime", "Jack", "Bake", "Fox", "Wake"};
		int[] heapValues = {86, 72, 77, 60, 12, 50, 41};

		// Build the SearchTree with the provided keys and heap values
		searchTree.build(keys, heapValues);

		System.out.println("\n----- ADDING NODES -----");
		// Define additional keys and heap values to be added to the tree
		String[] newKeys = {"Red", "Good", "Cat", "True"};
		int[] newHeapValues = {33, 8, 30, 32};

		// Loop through the new keys and corresponding heap values, adding each to the tree
		for (int i = 0; i < newKeys.length; i++) {
			searchTree.add(newKeys[i], newHeapValues[i]);  // Add the new node to the tree
			System.out.println("Added: " + newKeys[i] + " with heap value " + newHeapValues[i]);
		}

		// Searching for a specific node and displaying its path
		System.out.println("\n----- FINDING PATH OF THE NODE ELEMENTS -----");
		// Find the path to the "wake" node (case-insensitive search)
		List<String> path = searchTree.findPath("wake");
		// Display the path to the "wake" node
		searchTree.displayPath(path);

		// Find the path to the "True" node (case-insensitive search)
		List<String> path1 = searchTree.findPath("True");
		// Display the path to the "True" node
		searchTree.displayPath(path1);

		// Display the structure of the SearchTree before removal
		System.out.println("\n");
		searchTree.displayTree();

		// Remove a node from the tree
		searchTree.remove("true");  // Remove the node with key "true"
		System.out.println("\n----- AFTER REMOVING THE NODE ELEMENT -----");
		// Display the structure of the SearchTree after removal
		searchTree.displayTree();
	}
}
