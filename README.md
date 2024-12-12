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

Final Output -

<img width="912" alt="Screenshot 2024-10-08 at 8 56 58 PM" src="https://github.com/user-attachments/assets/cef0c5dc-60ae-4b87-b430-ba5a6cb4f362" />

Fig.2 - Adding Nodes and Finding the Path of the Node.

<img width="1009" alt="Screenshot 2024-10-08 at 8 57 03 PM" src="https://github.com/user-attachments/assets/add083a7-78b0-4007-a97e-db2986da71f3" />

Fig.3 - After Adding Node and After Removing the Node
