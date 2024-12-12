import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SearchTreeTest {

	private SearchTree tree;

	@BeforeEach
	public void setUp() {
		tree = new SearchTree();
	}

	// ========== Test for add() method ==========
	@Test
	public void testAddBasic() {
		assertTrue(tree.add("apple"), "Should return true for a new key");
		assertEquals(1, tree.size(), "Size should be 1 after adding one key");
	}

	@Test
	public void testAddDuplicate() {
		assertTrue(tree.add("banana"), "Should return true for a new key");
		assertFalse(tree.add("banana"), "Should return false for duplicate key");
		assertEquals(1, tree.size(), "Size should remain 1 after adding a duplicate");
	}

	@Test
	public void testAddEmptyString() {
		assertTrue(tree.add(""), "Should return true for empty string");
		assertEquals(1, tree.size(), "Size should be 1 after adding empty string");
	}

	@Test
	public void testAddCaseInsensitivity() {
		assertTrue(tree.add("Orange"), "Should return true for new key");
		assertFalse(tree.add("orange"), "Should return false for duplicate key with different case");
		assertEquals(1, tree.size(), "Size should be 1 after trying to add case-insensitive duplicate");
	}

	@Test
	public void testAddNull() {
		assertFalse(tree.add(null), "Should return false when trying to add null");
		assertEquals(0, tree.size(), "Size should remain 0 after trying to add null");
	}

	@Test
	public void testAddMultipleUniqueKeys() {
		assertTrue(tree.add("apple"), "Should return true for apple");
		assertTrue(tree.add("banana"), "Should return true for banana");
		assertTrue(tree.add("cherry"), "Should return true for cherry");
		assertEquals(3, tree.size(), "Size should be 3 after adding three unique keys");
	}

	@Test
	public void testAddAllLowerCase() {
		assertTrue(tree.add("kiwi"), "Should return true for kiwi");
		assertTrue(tree.add("mango"), "Should return true for mango");
		assertFalse(tree.add("kiwi"), "Should return false for duplicate kiwi");
		assertEquals(2, tree.size(), "Size should remain 2 after adding duplicate kiwi");
	}

	@Test
	public void testAddLongString() {
		String longString = "thisIsAVeryLongStringThatExceedsNormalLength";
		assertTrue(tree.add(longString), "Should return true for a long string");
		assertEquals(1, tree.size(), "Size should be 1 after adding a long string");
	}

	@Test
	public void testAddSpecialCharacters() {
		assertTrue(tree.add("!@#$%^&*()"), "Should return true for special characters");
		assertEquals(1, tree.size(), "Size should be 1 after adding special characters");
	}

	// ========== Test for find() method ==========
	@Test
	public void testFindBasic() {
		tree.add("apple");
		assertTrue(tree.find("apple"), "Should find the key 'apple'");
	}

	@Test
	public void testFindNonExistentKey() {
		assertFalse(tree.find("pear"), "Should return false for non-existent key");
	}

	@Test
	public void testFindCaseInsensitive() {
		tree.add("Grape");
		assertTrue(tree.find("grape"), "Should find 'grape' regardless of case");
	}

	@Test
	public void testFindEmptyString() {
		tree.add("");
		assertTrue(tree.find(""), "Should find empty string");
	}

	@Test
	public void testFindNull() {
		assertFalse(tree.find(null), "Should return false when searching for null");
	}

	@Test
	public void testFindAfterMultipleAdds() {
		tree.add("apple");
		tree.add("banana");
		tree.add("cherry");
		assertTrue(tree.find("banana"), "Should find 'banana' after multiple adds");
	}

	@Test
	public void testFindCaseInsensitiveWithMultipleAdds() {
		tree.add("Blueberry");
		assertTrue(tree.find("blueberry"), "Should find 'blueberry' with different case after adding");
	}

	@Test
	public void testFindSpecialCharacters() {
		tree.add("!@#$%^&*()");
		assertTrue(tree.find("!@#$%^&*()"), "Should find special characters");
	}

	@Test
	public void testFindLongString() {
		String longString = "thisIsAVeryLongStringThatExceedsNormalLength";
		tree.add(longString);
		assertTrue(tree.find(longString), "Should find long string");
	}

	@Test
	public void testFindNonExistentKeyAfterMultipleAdds() {
		tree.add("apple");
		tree.add("banana");
		assertFalse(tree.find("orange"), "Should return false for non-existent key after adds");
	}

	// ========== Test for remove() method ==========
	@Test
	public void testRemoveBasic() {
		tree.add("apple");
		assertTrue(tree.remove("apple"), "Should successfully remove 'apple'");
		assertFalse(tree.find("apple"), "Should not find 'apple' after removal");
		assertEquals(0, tree.size(), "Size should be 0 after removing the only element");
	}

	@Test
	public void testRemoveNonExistentKey() {
		tree.add("banana");
		assertFalse(tree.remove("orange"), "Should return false for non-existent key");
		assertEquals(1, tree.size(), "Size should remain 1 after attempting to remove non-existent key");
	}

	@Test
	public void testRemoveEmptyString() {
		tree.add("");
		assertTrue(tree.remove(""), "Should remove empty string");
		assertFalse(tree.find(""), "Should not find empty string after removal");
		assertEquals(0, tree.size(), "Size should be 0 after removing the empty string");
	}

	@Test
	public void testRemoveNull() {
		tree.add("apple");
		assertFalse(tree.remove(null), "Should return false when trying to remove null");
		assertEquals(1, tree.size(), "Size should remain unchanged after trying to remove null");
	}

	@Test
	public void testRemoveMultipleKeys() {
		tree.add("apple");
		tree.add("banana");
		tree.add("cherry");
		assertTrue(tree.remove("banana"), "Should successfully remove 'banana'");
		assertFalse(tree.find("banana"), "Should not find 'banana' after removal");
		assertEquals(2, tree.size(), "Size should be 2 after removing one key");
	}

	@Test
	public void testRemoveLastKey() {
		tree.add("kiwi");
		assertTrue(tree.remove("kiwi"), "Should successfully remove 'kiwi'");
		assertFalse(tree.find("kiwi"), "Should not find 'kiwi' after removal");
		assertEquals(0, tree.size(), "Size should be 0 after removing the last key");
	}

	@Test
	public void testRemoveCaseInsensitive() {
		tree.add("Mango");
		assertTrue(tree.remove("mango"), "Should successfully remove 'mango' with different case");
		assertFalse(tree.find("Mango"), "Should not find 'Mango' after removal");
	}

	@Test
	public void testRemoveSpecialCharacters() {
		tree.add("!@#$%^&*()");
		assertTrue(tree.remove("!@#$%^&*()"), "Should successfully remove special characters");
		assertFalse(tree.find("!@#$%^&*()"), "Should not find special characters after removal");
	}

	// ========== Additional boundary tests ==========
	@Test
	public void testAddAndRemoveLargeNumberOfKeys() {
		for (int i = 0; i < 100; i++) {
			assertTrue(tree.add("key" + i), "Should add key" + i);
		}
		assertEquals(100, tree.size(), "Size should be 100 after adding 100 keys");

		for (int i = 0; i < 100; i++) {
			assertTrue(tree.remove("key" + i), "Should remove key" + i);
		}
		assertEquals(0, tree.size(), "Size should be 0 after removing all keys");
	}

	@Test
	public void testFindWithLargeNumberOfKeys() {
		for (int i = 0; i < 100; i++) {
			tree.add("key" + i);
		}
		for (int i = 0; i < 100; i++) {
			assertTrue(tree.find("key" + i), "Should find key" + i);
		}
	}

	@Test
	public void testRemoveWithLargeNumberOfKeys() {
		for (int i = 0; i < 100; i++) {
			tree.add("key" + i);
		}
		for (int i = 0; i < 100; i++) {
			assertTrue(tree.remove("key" + i), "Should remove key" + i);
		}
		assertEquals(0, tree.size(), "Size should be 0 after removing all keys");
	}

	// ========== Tests with large string lengths ==========
	@Test
	public void testAddLongStrings() {
		String longKey1 = "a".repeat(1000); // 1000 characters long
		String longKey2 = "b".repeat(1000); // 1000 characters long

		assertTrue(tree.add(longKey1), "Should add long key 1");
		assertTrue(tree.add(longKey2), "Should add long key 2");
		assertEquals(2, tree.size(), "Size should be 2 after adding two long keys");

		assertTrue(tree.find(longKey1), "Should find long key 1");
		assertTrue(tree.find(longKey2), "Should find long key 2");
	}

	@Test
	public void testRemoveLongStrings() {
		String longKey = "c".repeat(1000); // 1000 characters long
		tree.add(longKey);
		assertTrue(tree.remove(longKey), "Should successfully remove long key");
		assertFalse(tree.find(longKey), "Should not find long key after removal");
	}
}
