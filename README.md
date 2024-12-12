# Treap Data Structure
This data structure is implemented using two primary data structures: Binary Search Tree(BST) and Heap.

The Treap maintains the binary search tree property for efficient searching while also adhering to the max-heap property for balancing, based on an associated heap value. The result is a self-balancing tree where the nodes are ordered by their keys in BST order, but also by their heap values, creating a priority queue-like behavior.

The SearchTree extends the Treap by introducing a dynamic re-balancing mechanism that promotes frequently searched keys toward the root of the tree, optimizing the structure based on access patterns over time. The key challenge in this implementation is to combine the properties of both BST and heaps, ensuring efficient searching, insertion, and deletion while also maintaining balance through tree rotations.

## Files and External Data
