# Treap-Data-Structure
This data structure is implemented using two primary data structures: Binary Search Tree(BST) and Heap.

The Treap maintains the binary search tree property for efficient searching while also adhering to the max-heap property for balancing, based on an associated heap value. The result is a self-balancing tree where the nodes are ordered by their keys in BST order, but also by their heap values, creating a priority queue-like behavior.

The SearchTree extends the Treap by introducing a dynamic re-balancing mechanism that promotes frequently searched keys toward the tree's root, optimizing the structure based on access patterns over time. The key challenge in this implementation is combining the properties of both BST and heaps, ensuring efficient searching, insertion, and deletion while maintaining balance through tree rotations.

## Files and External Data -
- Searchable.java: Defines the interface used by the Treap class. This interface outlines key operations such as adding elements, finding elements, removing elements, and managing the heap structure. The Searchable interface ensures that any class implementing it adheres to the basic structure of a searchable data structure.

- TreapNode.java: This file contains the TreapNode class, which represents the fundamental building block of the Treap. Each TreapNode contains a string key (converted to lowercase for case-insensitive comparison), an integer heap value, and references to its left and right child nodes. The heap value is used to maintain the heap property during tree balancing.

- Treap.java: The Treap class is the core of this project. It implements the Searchable interface and contains methods for adding, finding, removing, and managing nodes in the tree. The Treap maintains the BST property for string keys, while also ensuring the heap property (i.e., the parent node’s heap value is larger than those of its children). Key methods include add(), build(), find(), findPath(), changeOrder(), remove(), and size().

- SearchTree.java: The SearchTree class extends the Treap by adding search frequency tracking to each node. In this class, each node is a SearchTreeNode, which extends TreapNode and adds a searchCount to track how often the node is searched. This class overrides the find() method to increment the search count each time a node is found, and repositions nodes with higher search counts closer to the root.

## Output -
<img width="501" alt="Screenshot 2024-12-12 at 3 42 03 PM" src="https://github.com/user-attachments/assets/651c741c-f5b1-4b78-8ec4-ad844352fcad" />

Fig.1 - Sample Treap

<img width="912" alt="Screenshot 2024-10-08 at 8 56 58 PM" src="https://github.com/user-attachments/assets/cef0c5dc-60ae-4b87-b430-ba5a6cb4f362" />

Fig.2 - Adding Nodes and Finding the Path of the Node.

<img width="1009" alt="Screenshot 2024-10-08 at 8 57 03 PM" src="https://github.com/user-attachments/assets/add083a7-78b0-4007-a97e-db2986da71f3" />

Fig.3 - After Adding Node and After Removing the Node

## Key Algorithms -
1. Insertion and Rebalancing: When a new node is inserted into the Treap, it is first added according to BST rules (based on string comparison). After insertion, the heap property is checked, and rotations are applied if the heap property is violated.

○ Pseudocode:
- Insert key with heapV alue at the appropriate position based on BST order.
- If the heapV alue violates the heap property(child's heapV alue > parent's), apply rotations.

2. Find Operation: The find() method recursively searches for the key in the tree by traversing either left or right depending on the result of the string comparison. It returns true if the key is found, and false otherwise. In the SearchTree, the find() method also increments the node’s search count and rebalances the tree if the search count exceeds that of its parent.

○ Pseudocode:
- Search for key in tree using BST rules.
- If key is found, increment search count.
- If search count is greater than the parent's, apply rotations to move node closer to root.

3. Rotation Mechanism: To maintain the heap property, both left and right rotations are used:

- Right Rotation:
y = node
x = y.left
y.left = x.right
x.right = y
return x

- Left Rotation:
x = node
y = x.right
x.right = y.left
y.left = x
return y

4. Path Finding: The findPath() method traces the path from a specific node to the root. It uses a recursive helper function to collect the path and returns it as a list of strings.

○ Pseudocode:
- Recursively traverse the tree and record each node on the path to the root.
- Reverse the path and return it as a list.

5. Remove Operation: The remove() method deletes a node from the tree while maintaining both BST and heap properties. It does this by recursively searching for the node, and then either promoting a child node or rotating the tree to maintain the heap property.

○ Pseudocode:
- Find the node to remove.
- If node has children, promote one of the children and rebalance with rotation if necessary.
- Return the updated tree.
