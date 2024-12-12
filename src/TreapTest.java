import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TreapTest {

	private Treap treap;
	private SearchTree searchTree;

	@BeforeEach
	public void setUp() {
		treap = new Treap();
		searchTree = new SearchTree();
	}

	// ========================= Add() Tests =========================

	// Input validation
	@Test
	public void testAddNullKey() {
		assertFalse(treap.add(null, 10));  // Adding null key
	}

	// Boundary test cases
	@Test
	public void testAddEmptyString() {
		assertTrue(treap.add("", 10));  // Add empty string
	}

	@Test
	public void testAddSmallestString() {
		assertTrue(treap.add("a", 10));  // Add a string smaller than any in the tree
	}

	@Test
	public void testAddLargestString() {
		assertTrue(treap.add("zzz", 10));  // Add a string larger than any in the tree
	}

	@Test
	public void testAddToEmptyTree() {
		assertTrue(treap.add("first", 10));  // Add to an empty tree
	}

	@Test
	public void testAddZeroHeapValue() {
		assertFalse(treap.add("zero", 0));  // Add with heap value of 0
	}

	@Test
	public void testAddNegativeHeapValue() {
		assertFalse(treap.add("negative", -1));  // Add with negative heap value
	}

	// Control flow tests
	@Test
	public void testAddToTreeWithOnlyRoot() {
		treap.add("root", 10);
		assertTrue(treap.add("left", 5));  // Add to a tree that just has a root
	}

	@Test
	public void testAddToLargerTree() {
		treap.add("root", 10);
		treap.add("left", 5);
		assertTrue(treap.add("right", 15));  // Add to a tree taller than a root
	}

	@Test
	public void testAddStringMiddleRange() {
		treap.add("apple", 10);
		treap.add("banana", 20);
		assertTrue(treap.add("berry", 15));  // Add a string to the middle range
	}

	@Test
	public void testAddDuplicateString() {
		treap.add("apple", 10);
		assertFalse(treap.add("apple", 10));  // Add duplicate string
	}

	@Test
	public void testAddLeftChildToRoot() {
		treap.add("root", 10);
		assertTrue(treap.add("leftChild", 5));  // Add left child to root
	}

	@Test
	public void testAddRightChildToRoot() {
		treap.add("root", 10);
		assertTrue(treap.add("rightChild", 15));  // Add right child to root
	}

	@Test
	public void testAddLeftGrandchild() {
		treap.add("root", 10);
		treap.add("leftChild", 5);
		assertTrue(treap.add("leftGrandchild", 2));  // Add left grandchild
	}

	@Test
	public void testAddRightGrandchild() {
		treap.add("root", 10);
		treap.add("leftChild", 5);
		assertTrue(treap.add("rightGrandchild", 7));  // Add right grandchild
	}

	@Test
	public void testAddRightLeftGrandchild() {
		treap.add("root", 10);
		treap.add("rightChild", 15);
		assertTrue(treap.add("rightLeftGrandchild", 12));  // Add left grandchild of right child
	}

	@Test
	public void testAddRightRightGrandchild() {
		treap.add("root", 10);
		treap.add("rightChild", 15);
		assertTrue(treap.add("rightRightGrandchild", 20));  // Add right grandchild of right child
	}

	@Test
	public void testAddLowestHeapValue() {
		treap.add("low", 10);
		assertTrue(treap.add("lowest", 1));  // Add lowest heap value
	}

	@Test
	public void testAddHighestHeapValue() {
		treap.add("high", 10);
		assertTrue(treap.add("highest", 20));  // Add highest heap value
	}

	@Test
	public void testAddMiddleHeapValue() {
		treap.add("low", 5);
		treap.add("high", 15);
		assertTrue(treap.add("middle", 10));  // Add middle heap value
	}

	@Test
	public void testAddDuplicateHeapValue() {
		treap.add("apple", 10);
		assertFalse(treap.add("banana", 10));  // Add duplicate heap value
	}

	// Data flow tests
	@Test
	public void testAddAfterBuild() {
		treap.build(new String[] {"apple", "banana"}, new int[] {10, 20});
		assertTrue(treap.add("cherry", 15));  // Add after build()
	}

	@Test
	public void testAddAfterDelete() {
		treap.add("apple", 10);
		treap.remove("apple");
		assertTrue(treap.add("apple", 10));  // Add after deletion
	}



	// ========================= Build() Tests =========================

	// Input validation
	@Test
	public void testBuildNullKeyArray() {
		assertFalse(treap.build(null, new int[] {10}));  // Null key array
	}

	@Test
	public void testBuildNullHeapArray() {
		assertFalse(treap.build(new String[] {"apple"}, null));  // Null heap value array
	}

	@Test
	public void testBuildMismatchedArrayLengths() {
		assertFalse(treap.build(new String[] {"apple"}, new int[] {10, 20}));  // Mismatched key/heap array lengths
	}

	// Boundary tests
	@Test
	public void testBuildEmptyArrays() {
		assertTrue(treap.build(new String[] {}, new int[] {}));  // Build with empty arrays
	}

	@Test
	public void testBuildSingleElementArray() {
		assertTrue(treap.build(new String[] {"apple"}, new int[] {10}));  // Build with single element
	}

	// Control flow tests
	@Test
	public void testBuildMultipleKeys() {
		assertTrue(treap.build(new String[] {"apple", "banana", "cherry"}, new int[] {10, 20, 15}));  // Multiple keys
	}

	@Test
	public void testBuildAscendingKeys() {
		assertTrue(treap.build(new String[] {"apple", "banana", "cherry"}, new int[] {10, 20, 30}));  // Ascending keys
	}

	@Test
	public void testBuildDescendingKeys() {
		assertTrue(treap.build(new String[] {"cherry", "banana", "apple"}, new int[] {30, 20, 10}));  // Descending keys
	}

	@Test
	public void testBuildRandomKeys() {
		assertTrue(treap.build(new String[] {"banana", "apple", "cherry"}, new int[] {20, 10, 15}));  // Random keys
	}

	@Test
	public void testBuildAscendingHeapValues() {
		assertTrue(treap.build(new String[] {"apple", "banana", "cherry"}, new int[] {10, 20, 30}));  // Ascending heap values
	}

	@Test
	public void testBuildDescendingHeapValues() {
		assertTrue(treap.build(new String[] {"apple", "banana", "cherry"}, new int[] {30, 20, 10}));  // Descending heap values
	}

	@Test
	public void testBuildRandomHeapValues() {
		assertTrue(treap.build(new String[] {"apple", "banana", "cherry"}, new int[] {20, 10, 15}));  // Random heap values
	}

	@Test
	public void testBuildLeftSkewedTree() {
		assertTrue(treap.build(new String[] {"c", "b", "a"}, new int[] {30, 20, 10}));  // Linked list down the left side
	}

	@Test
	public void testBuildRightSkewedTree() {
		assertTrue(treap.build(new String[] {"a", "b", "c"}, new int[] {10, 20, 30}));  // Linked list down the right side
	}

	// Data flow tests
	@Test
	public void testBuildTwiceInARow() {
		assertTrue(treap.build(new String[] {"apple"}, new int[] {10}));
		assertTrue(treap.build(new String[] {"banana"}, new int[] {20}));  // Build twice in a row
	}

	@Test
	public void testBuildAfterAdd() {
		treap.add("apple", 10);
		assertTrue(treap.build(new String[] {"banana", "cherry"}, new int[] {20, 30}));  // Build after add()
	}



	// ========================= Find() Tests =========================

	// Input validation
	@Test
	public void testFindNullKey() {
		assertFalse(treap.find(null));  // Null key
	}

	// Boundary tests
	@Test
	public void testFindEmptyStringInTree() {
		treap.add("", 10);
		assertTrue(treap.find(""));  // Find empty string in tree
	}

	@Test
	public void testFindEmptyStringNotInTree() {
		assertFalse(treap.find(""));  // Empty string not in tree
	}

	@Test
	public void testFindSmallestElement() {
		treap.add("a", 10);
		assertTrue(treap.find("a"));  // Find smallest element
	}

	@Test
	public void testFindLargestElement() {
		treap.add("zzz", 10);
		assertTrue(treap.find("zzz"));  // Find largest element
	}

	@Test
	public void testFindInEmptyTree() {
		assertFalse(treap.find("apple"));  // Search in empty tree
	}

	// Control flow tests
	@Test
	public void testFindInTreeWithRootOnly() {
		treap.add("root", 10);
		assertTrue(treap.find("root"));  // Find root in tree with only root
	}

	@Test
	public void testFindNonRootInTreeWithRootOnly() {
		treap.add("root", 10);
		assertFalse(treap.find("nonRoot"));  // Non-root in tree with only root
	}

	@Test
	public void testFindLeafNode() {
		treap.add("root", 10);
		treap.add("left", 5);
		assertTrue(treap.find("left"));  // Find leaf node
	}

	@Test
	public void testFindMiddleNode() {
		treap.add("root", 10);
		treap.add("middle", 8);
		assertTrue(treap.find("middle"));  // Find middle node
	}

	// Data flow tests
	@Test
	public void testFindAfterDelete() {
		treap.add("apple", 10);
		treap.remove("apple");
		assertFalse(treap.find("apple"));  // Find after deletion
	}

	@Test
	public void testFindInTreeBuiltWithAdd() {
		treap.add("apple", 10);
		assertTrue(treap.find("apple"));  // Find in tree built with add()
	}

	@Test
	public void testFindInTreeBuiltWithBuild() {
		treap.build(new String[] {"apple"}, new int[] {10});
		assertTrue(treap.find("apple"));  // Find in tree built with build()
	}

	@Test
	public void testFindAfterJustFinding() {
		treap.add("apple", 10);
		assertTrue(treap.find("apple"));
		assertTrue(treap.find("apple"));  // Find after just finding the value
	}



	// ========================= FindPath() Tests =========================

	// Input validation
	@Test
	public void testFindPathNullKey() {
		assertNull(treap.findPath(null));  // Null key
	}

	// Boundary tests
	@Test
	public void testFindPathEmptyStringInTree() {
		treap.add("", 10);
		assertNotNull(treap.findPath(""));  // Find path for empty string in tree
	}

	@Test
	public void testFindPathEmptyStringNotInTree() {
		assertNull(treap.findPath(""));  // Empty string not in tree
	}

	@Test
	public void testFindPathSmallestElement() {
		treap.add("a", 10);
		assertNotNull(treap.findPath("a"));  // Find path for smallest element
	}

	@Test
	public void testFindPathLargestElement() {
		treap.add("zzz", 10);
		assertNotNull(treap.findPath("zzz"));  // Find path for largest element
	}

	@Test
	public void testFindPathInEmptyTree() {
		assertNull(treap.findPath("apple"));  // Search in empty tree
	}

	// Control flow tests
	@Test
	public void testFindPathInTreeWithRootOnly() {
		treap.add("root", 10);
		assertNotNull(treap.findPath("root"));  // Find path to root in tree with only root
	}

	@Test
	public void testFindPathNonRootInTreeWithRootOnly() {
		treap.add("root", 10);
		assertNull(treap.findPath("nonRoot"));  // Non-root in tree with only root
	}

	@Test
	public void testFindPathLeafNode() {
		treap.add("root", 10);
		treap.add("left", 5);
		assertNotNull(treap.findPath("left"));  // Find path to leaf node
	}

	@Test
	public void testFindPathMiddleNode() {
		treap.add("root", 10);
		treap.add("middle", 8);
		assertNotNull(treap.findPath("middle"));  // Find path to middle node
	}

	@Test
	public void testFindPathFurthestFromRoot() {
		treap.add("root", 10);
		treap.add("left", 5);
		treap.add("leftLeft", 2);
		assertNotNull(treap.findPath("leftLeft"));  // Find path to element furthest from root
	}

	@Test
	public void testFindPathNonExistentElement() {
		assertNull(treap.findPath("nonExistent"));  // Element not in tree
	}

	// Data flow tests
	@Test
	public void testFindPathAfterDelete() {
		treap.add("apple", 10);
		treap.remove("apple");
		assertNull(treap.findPath("apple"));  // Find path after deletion
	}

	@Test
	public void testFindPathInTreeBuiltWithAdd() {
		treap.add("apple", 10);
		assertNotNull(treap.findPath("apple"));  // Find path in tree built with add()
	}

	@Test
	public void testFindPathInTreeBuiltWithBuild() {
		treap.build(new String[] {"apple"}, new int[] {10});
		assertNotNull(treap.findPath("apple"));  // Find path in tree built with build()
	}

	@Test
	public void testFindPathAfterJustFinding() {
		treap.add("apple", 10);
		assertNotNull(treap.findPath("apple"));
		assertNotNull(treap.findPath("apple"));  // Find path after just finding the value
	}



	// ========================= ChangeOrder() Tests =========================

	// Input validation
	@Test
	public void testChangeOrderNullKey() {
		assertFalse(treap.changeOrder(null, 10));  // Null key
	}

	@Test
	public void testChangeOrderZeroHeapValue() {
		treap.add("apple", 10);
		assertFalse(treap.changeOrder("apple", 0));  // Change heap value to 0
	}

	@Test
	public void testChangeOrderNegativeHeapValue() {
		treap.add("apple", 10);
		assertFalse(treap.changeOrder("apple", -1));  // Change heap value to negative
	}

	// Boundary tests
	@Test
	public void testChangeOrderEmptyString() {
		treap.add("", 10);
		assertTrue(treap.changeOrder("", 20));  // Change order of empty string
	}

	@Test
	public void testChangeOrderSmallestString() {
		treap.add("a", 10);
		assertTrue(treap.changeOrder("a", 20));  // Change order of smallest string
	}

	@Test
	public void testChangeOrderLargestString() {
		treap.add("zzz", 10);
		assertTrue(treap.changeOrder("zzz", 20));  // Change order of largest string
	}

	@Test
	public void testChangeOrderNonExistentString() {
		assertFalse(treap.changeOrder("nonExistent", 10));  // Change order of string not in the tree
	}

	// Control flow tests
	@Test
	public void testChangeOrderSameHeapValue() {
		treap.add("apple", 10);
		assertTrue(treap.changeOrder("apple", 10));  // Change heap value to the same value
	}

	@Test
	public void testChangeOrderLowerHeapValue() {
		treap.add("apple", 10);
		assertTrue(treap.changeOrder("apple", 5));  // Change heap value to a lower value
	}

	@Test
	public void testChangeOrderHigherHeapValue() {
		treap.add("apple", 10);
		assertTrue(treap.changeOrder("apple", 20));  // Change heap value to a higher value
	}

	@Test
	public void testChangeOrderDuplicateHeapValue() {
		treap.add("apple", 10);
		treap.add("banana", 20);
		assertTrue(treap.changeOrder("apple", 20));  // Change to duplicate heap value
	}

	@Test
	public void testChangeOrderRootNode() {
		treap.add("root", 10);
		assertTrue(treap.changeOrder("root", 20));  // Change heap value of root
	}

	@Test
	public void testChangeOrderLeafNode() {
		treap.add("root", 10);
		treap.add("left", 5);
		assertTrue(treap.changeOrder("left", 2));  // Change heap value of a leaf node
	}

	@Test
	public void testChangeOrderIntermediateNode() {
		treap.add("root", 10);
		treap.add("middle", 8);
		assertTrue(treap.changeOrder("middle", 15));  // Change heap value of an intermediate node
	}

	@Test
	public void testChangeOrderToSmallestInTree() {
		treap.add("apple", 10);
		treap.add("banana", 20);
		assertTrue(treap.changeOrder("banana", 1));  // Change to become smallest in tree
	}

	@Test
	public void testChangeOrderToLargestInTree() {
		treap.add("apple", 10);
		treap.add("banana", 20);
		assertTrue(treap.changeOrder("apple", 30));  // Change to become largest in tree
	}

	// Data flow tests
	@Test
	public void testChangeOrderAfterDelete() {
		treap.add("apple", 10);
		treap.remove("apple");
		assertFalse(treap.changeOrder("apple", 20));  // Change order after deletion
	}

	@Test
	public void testChangeOrderInTreeBuiltWithAdd() {
		treap.add("apple", 10);
		assertTrue(treap.changeOrder("apple", 20));  // Change order in tree built with add()
	}

	@Test
	public void testChangeOrderInTreeBuiltWithBuild() {
		treap.build(new String[] {"apple"}, new int[] {10});
		assertTrue(treap.changeOrder("apple", 20));  // Change order in tree built with build()
	}

	@Test
	public void testChangeOrderSameKeyTwice() {
		treap.add("apple", 10);
		assertTrue(treap.changeOrder("apple", 15));
		assertTrue(treap.changeOrder("apple", 20));  // Change order of same key twice
	}



	// ========================= Remove() Tests =========================

	// Input validation
	@Test
	public void testRemoveNullKey() {
		assertFalse(treap.remove(null));  // Null key
	}

	// Boundary tests
	@Test
	public void testRemoveEmptyStringInTree() {
		treap.add("", 10);
		assertTrue(treap.remove(""));  // Remove empty string in tree
	}

	@Test
	public void testRemoveEmptyStringNotInTree() {
		assertFalse(treap.remove(""));  // Empty string not in tree
	}

	@Test
	public void testRemoveFromEmptyTree() {
		assertFalse(treap.remove("apple"));  // Remove from empty tree
	}

	@Test
	public void testRemoveSmallestValue() {
		treap.add("a", 10);
		assertTrue(treap.remove("a"));  // Remove smallest value
	}

	@Test
	public void testRemoveLargestValue() {
		treap.add("zzz", 10);
		assertTrue(treap.remove("zzz"));  // Remove largest value
	}

	// Control flow tests
	@Test
	public void testRemoveRootNode() {
		treap.add("root", 10);
		assertTrue(treap.remove("root"));  // Remove root
	}

	@Test
	public void testRemoveLeafNode() {
		treap.add("root", 10);
		treap.add("leaf", 5);
		assertTrue(treap.remove("leaf"));  // Remove leaf node
	}

	@Test
	public void testRemoveIntermediateNode() {
		treap.add("root", 10);
		treap.add("middle", 8);
		assertTrue(treap.remove("middle"));  // Remove intermediate node
	}

	@Test
	public void testRemoveNonExistentString() {
		assertFalse(treap.remove("nonExistent"));  // Remove non-existent string
	}

	@Test
	public void testRemoveOneLevelAwayFromLeaf() {
		treap.add("root", 10);
		treap.add("leaf", 5);
		treap.add("leafChild", 2);
		assertTrue(treap.remove("leaf"));  // Remove node one level away from leaf
	}

	// Data flow tests
	@Test
	public void testRemoveSameValueTwice() {
		treap.add("apple", 10);
		assertTrue(treap.remove("apple"));
		assertFalse(treap.remove("apple"));  // Remove same value twice
	}

	@Test
	public void testRemoveAfterAdd() {
		treap.add("apple", 10);
		assertTrue(treap.remove("apple"));  // Remove after adding
	}



	// ========================= Size() Tests =========================

	// Input validation
	// (no input required for size())

	// Boundary tests
	@Test
	public void testSizeOfEmptyTree() {
		assertEquals(0, treap.size());  // Size of empty tree
	}

	// Control flow tests
	@Test
	public void testSizeOfTreeWithRoot() {
		treap.add("root", 10);
		assertEquals(1, treap.size());  // Size with just a root node
	}

	@Test
	public void testSizeOfMultiLevelTree() {
		treap.add("root", 10);
		treap.add("left", 5);
		treap.add("right", 15);
		assertEquals(3, treap.size());  // Size with multiple levels of nodes
	}

	// Data flow tests
	@Test
	public void testSizeAfterAdd() {
		treap.add("apple", 10);
		assertEquals(1, treap.size());  // Size after adding
	}

	@Test
	public void testSizeAfterBuild() {
		treap.build(new String[] {"apple", "banana"}, new int[] {10, 20});
		assertEquals(2, treap.size());  // Size after build()
	}

	@Test
	public void testSizeAfterDelete() {
		treap.add("apple", 10);
		treap.remove("apple");
		assertEquals(0, treap.size());  // Size after deleting
	}

	@Test
	public void testSizeAfterFind() {
		treap.add("apple", 10);
		treap.find("apple");
		assertEquals(1, treap.size());  // Size after finding
	}

	@Test
	public void testSizeAfterChangeOrder() {
		treap.add("apple", 10);
		treap.changeOrder("apple", 20);
		assertEquals(1, treap.size());  // Size after changing heap value
	}
}