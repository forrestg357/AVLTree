import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class AVLTreeTest {

	@Test
	void testOrder() {
//		
//		//We will put nodes in order of level (keeping the tree balanced as we go)
//		//We will test put/delete (with rotations) in a separate test
//		
		AVLTree tree = new AVLTree();
		
		tree.put(12);
		tree.put(7);
		tree.put(14);
		tree.put(6);
		tree.put(8);
		tree.put(13);
		tree.put(18);
		
		//Testing preOrder
		
		assertEquals("12 7 6 8 14 13 18 ", tree.preOrder());
		
		//Testing inOrder
		
		assertEquals("6 7 8 12 13 14 18 ", tree.inOrder());
		
		//Testing postOrder
		
		assertEquals("6 8 7 13 18 14 12 ", tree.postOrder());
		
		//Testing levelOrder
		
		System.out.println(tree.levelOrder());
		assertEquals("12 7 14 6 8 13 18 ", tree.levelOrder());

	}
	
	//For the rest of the tests, we will use preOrder to check that elements have been "put"/"deleted" correctly
	
	@Test
	void testPut() {
		
		//put test 0: add to a tree that contains no numbers, and test regular binary tree addition
		
		AVLTree tree0 = new AVLTree();
		tree0.put(1);
		assertEquals("1 ", tree0.preOrder());
		
		tree0.put(2);
		assertEquals("1 2 ", tree0.preOrder());
		
		//put test 1: Right rotate
		
		AVLTree tree1 = new AVLTree();
		tree1.put(3);
		tree1.put(2);
		tree1.put(1);
		assertEquals("2 1 3 ", tree1.preOrder());
		
		//put test 2: Left rotate
		
		AVLTree tree2 = new AVLTree();
		tree2.put(1);
		tree2.put(2);
		tree2.put(3);
		assertEquals("2 1 3 ", tree2.preOrder());
		
		//put test 3: Right-left rotate
		
		AVLTree tree3 = new AVLTree();
		tree3.put(1);
		tree3.put(3);
		tree3.put(2);
		assertEquals("2 1 3 ", tree3.preOrder());
		
		//put test 4: Left-right rotate
		
		AVLTree tree4 = new AVLTree();
		tree4.put(3);
		tree4.put(1);
		tree4.put(2);
		assertEquals("2 1 3 ", tree4.preOrder());
		
		//put test 5: Left rotate with a "special child" reassignment
		
		AVLTree tree5 = new AVLTree();
		tree5.put(5);
		tree5.put(3);
		tree5.put(7);
		tree5.put(6);
		tree5.put(8);
		tree5.put(9);
		assertEquals("7 5 3 6 8 9 ", tree5.preOrder());
		
		//put test 6: Right rotate with a "special child" reassignment
		
		AVLTree tree6 = new AVLTree();
		tree6.put(9);
		tree6.put(7);
		tree6.put(12);
		tree6.put(5);
		tree6.put(8);
		tree6.put(3);
		assertEquals("7 5 3 9 8 12 ", tree6.preOrder());
		
		//put test 7: left-right rotate with a "special child" reassignment
		
		AVLTree tree7 = new AVLTree();
		tree7.put(10);
		tree7.put(6);
		tree7.put(11);
		tree7.put(5);
		tree7.put(8);
		tree7.put(7);
		assertEquals("8 6 5 7 10 11 ", tree7.preOrder());
		
		//put test 8: right-left rotate with a "special child" reassignment
		
		AVLTree tree8 = new AVLTree();
		tree8.put(10);
		tree8.put(9);
		tree8.put(16);
		tree8.put(13);
		tree8.put(18);
		tree8.put(14);
		assertEquals("13 10 9 16 14 18 ", tree8.preOrder());
		
		//put test 9: rotate left, not on the root
		
		AVLTree tree9 = new AVLTree();
		tree9.put(10);
		tree9.put(8);
		tree9.put(12);
		tree9.put(14);
		tree9.put(16);
		assertEquals("10 8 14 12 16 ", tree9.preOrder());
		
		//put test 10: rotate right, not on the root
		
		AVLTree tree10 = new AVLTree();
		tree10.put(10);
		tree10.put(8);
		tree10.put(12);
		tree10.put(6);
		tree10.put(4);
		assertEquals("10 6 4 8 12 ", tree10.preOrder());
		
		
		//put test 11: rotate left-right, not on the root
		
		AVLTree tree11 = new AVLTree();
		tree11.put(5);
		tree11.put(3);
		tree11.put(8);
		tree11.put(1);
		tree11.put(2);
		assertEquals("5 2 1 3 8 ", tree11.preOrder());
		
		
		//put test 12: rotate right-left, not on the root
		
		AVLTree tree12 = new AVLTree();
		tree12.put(5);
		tree12.put(3);
		tree12.put(8);
		tree12.put(10);
		tree12.put(9);
		assertEquals("5 3 9 8 10 ", tree12.preOrder());
		
		//put test 13: rotate left, not on the root, reassign "special child"
		
		AVLTree tree13 = new AVLTree();
		tree13.put(6);
		tree13.put(4);
		tree13.put(10);
		tree13.put(2);
		tree13.put(8);
		tree13.put(12);
		tree13.put(14);
		tree13.put(16);
		assertEquals("6 4 2 10 8 14 12 16 ", tree13.preOrder());
		
		
		//put test 14: rotate right, not on the root, reassign "special child"
		
		AVLTree tree14 = new AVLTree();
		tree14.put(14);
		tree14.put(9);
		tree14.put(16);
		tree14.put(7);
		tree14.put(12);
		tree14.put(18);
		tree14.put(5);
		tree14.put(8);
		tree14.put(3);
		assertEquals("14 7 5 3 9 8 12 16 18 ", tree14.preOrder());
		
		
		//put test 15: rotate left-right, not on the root, reassign "special child"
		
		AVLTree tree15 = new AVLTree();
		tree15.put(12);
		tree15.put(10);
		tree15.put(14);
		tree15.put(6);
		tree15.put(11);
		tree15.put(16);
		tree15.put(5);
		tree15.put(8);
		tree15.put(7);
		assertEquals("12 8 6 5 7 10 11 14 16 ", tree15.preOrder());
		
		//put test 16: rotate right-left, not on the root, reassign "special child"
		
		AVLTree tree16 = new AVLTree();
		tree16.put(10);
		tree16.put(8);
		tree16.put(12);
		tree16.put(6);
		tree16.put(11);
		tree16.put(15);
		tree16.put(13);
		tree16.put(16);
		tree16.put(14);
		assertEquals("10 8 6 13 12 11 15 14 16 ", tree16.preOrder());
		
		//put test 17: add a number to a tree that already contains the number
		AVLTree tree17 = new AVLTree();
		tree17.put(6);
		tree17.put(4);
		tree17.put(10);
		tree17.put(2);
		tree17.put(8);
		tree17.put(14);
		tree17.put(14);
		assertEquals("6 4 2 10 8 14 ", tree17.preOrder());
		
	
	}
	
	@Test
	void testdelete() {
		
		//delete test 1: delete in a tree that contains nothing
		
		AVLTree tree1 = new AVLTree();
		tree1.delete(1);
	    assertEquals("", tree1.preOrder());
		
		//delete test 2: delete in a tree that contains 1 element
	    
	    AVLTree tree2 = new AVLTree();
	    int randomNum = (int) (Math.random() * 1000);
	    tree2.put(randomNum);
	    tree2.delete(randomNum);
	    assertEquals("", tree2.preOrder());
		
		//delete test 3: deletes a leaf from a tree with root balance 0
		
		AVLTree tree3 = new AVLTree();
		
		tree3.put(6);
		tree3.put(4);
		tree3.put(10);
		tree3.put(2);
		tree3.put(8);
		tree3.put(14);
		
		tree3.delete(8);
		
		assertEquals("6 4 2 10 14 ", tree3.preOrder());
		
		//delete test 4: deletes a leaf from a tree with root balance != 0, is still balanced
		
		AVLTree tree4 = new AVLTree();
		tree4.put(6);
		tree4.put(4);
		tree4.put(10);
		tree4.put(2);
		tree4.put(8);
		tree4.put(14);
		tree4.put(12);
		tree4.put(16);
		tree4.delete(16);
		assertEquals("6 4 2 10 8 14 12 ", tree4.preOrder());
		
		//delete test 5: deletes a leaf from a tree with root balance != 0, makes it unbalanced
		
		AVLTree tree5 = new AVLTree();
		tree5.put(6);
		tree5.put(4);
		tree5.put(10);
		tree5.put(2);
		tree5.put(8);
		tree5.put(14);
		tree5.put(12);
		tree5.put(16);
		tree5.delete(2);
		assertEquals("10 6 4 8 14 12 16 ", tree5.preOrder());
		
		//delete test 6: deletes from the left subtree
		
		AVLTree tree6 = new AVLTree();
		tree6.put(6);
		tree6.put(4);
		tree6.put(10);
		tree6.put(2);
		tree6.put(8);
		tree6.put(14);
		tree6.delete(4);
		assertEquals("6 2 10 8 14 ", tree6.preOrder());
		
		//delete test 7: deletes from the right subtree
		
		AVLTree tree7 = new AVLTree();
		tree7.put(10);
		tree7.put(8);
		tree7.put(12);
		tree7.put(6);
		tree7.put(9);
		tree7.put(14);
		
		tree7.delete(12);
		assertEquals("10 8 6 9 14 ", tree7.preOrder());
		
		
		//delete test 8: replace root with number from left
		
		AVLTree tree8 = new AVLTree();
		tree8.put(10);
		tree8.put(8);
		tree8.put(12);
		tree8.put(6);
		tree8.put(9);
		tree8.put(14);
		tree8.put(4);
		tree8.put(7);
		tree8.delete(10);
		assertEquals("9 6 4 8 7 12 14 ", tree8.preOrder());
		
		//delete test 9: replace root with number from right;
		
		AVLTree tree9 = new AVLTree();
		tree9.put(6);
		tree9.put(4);
		tree9.put(10);
		tree9.put(2);
		tree9.put(8);
		tree9.put(14);
		tree9.put(12);
		tree9.put(16);
		tree9.delete(6);
		assertEquals("8 4 2 14 10 12 16 ", tree9.preOrder());
		
		
		//delete test 10: Balance is 0, deletes from the right
		
		AVLTree tree10 = new AVLTree();
		tree10.put(10);
		tree10.put(8);
		tree10.put(12);
		tree10.put(6);
		tree10.put(9);
		tree10.put(14);
		tree10.delete(10);
		assertEquals("9 8 6 12 14 ", tree10.preOrder());
		
	}
	
	@Test
	void testContains() {
		AVLTree tree =  new AVLTree();
		
		//contains when the tree contains nothing
		for (int i = 0; i < 1000; i ++) {
			assertEquals(false, tree.contains(i));
			
		}
		
		//adding 1 element 
		tree.put(3);
		assertTrue(tree.contains(3));
		
		//removing the 1 element
		tree.delete(3);
		assertEquals(false, tree.contains(3));
		
		//Larger tree
		tree.put(10);
		tree.put(8);
		tree.put(12);
		tree.put(6);
		tree.put(11);
		tree.put(15);
		tree.put(13);
		tree.put(16);
		tree.put(14);
		
		assertEquals(true, tree.contains(10));
		assertEquals(false, tree.contains(3));
		
	}
	
	
	@Test
	void testDepth() {
		AVLTree tree = new AVLTree();
		
		//Depth of a tree with nothing in it
		assertEquals(-1, tree.getDepth());
		
		//Depth of a tree with one item
		
		tree.put(10);
		assertEquals(0, tree.getDepth());
		
		//Check depth after removing an item
		
		tree.put(10);
		tree.delete(10);
		assertEquals(-1, tree.getDepth());
		
		//Check depth after adding several items (class example)
		
		tree.put(0);
		tree.put(3);
		tree.put(1);
		tree.put(16);
		tree.put(14);
		tree.put(12);
		tree.put(13);
		tree.put(2);
		tree.put(5);
		tree.put(9);
		tree.put(6);
		tree.put(15);
		tree.put(8);
		tree.put(7);
		tree.put(11);
		tree.put(4);
		tree.put(10);
		tree.put(18);
		tree.put(17);
		tree.put(19);
		assertEquals(4, tree.getDepth());
		
		
	}
	
	@Test
	void testIsEmpty() {
		AVLTree tree = new AVLTree();
		assertEquals(true, tree.isEmpty());
		
		tree.put(1);
		assertEquals(false, tree.isEmpty());
	}
	
	@Test
	void testMax() {
		AVLTree tree = new AVLTree();
		tree.put(7);
		tree.put(5);
		tree.put(9);
		tree.put(3);
		tree.put(8);
		tree.put(12);
		assertEquals(12, tree.max());
	}
	
	@Test
	void testMin() {
		AVLTree tree = new AVLTree();
		tree.put(7);
		tree.put(5);
		tree.put(9);
		tree.put(3);
		tree.put(8);
		tree.put(12);
		assertEquals(3, tree.min());
		
		
	}
	
	@Test
	void testSize() {
		
		//Test 1: No numbers overlap
		int numPut = 10000;
		AVLTree tree1 = new AVLTree();
		
		for (int i = 0; i < numPut; i++) {
			tree1.put(i);
		}
		
		assertEquals(numPut, tree1.size());
		
		//Test 2: Stress test (numbers may overlap)
		AVLTree tree2 = new AVLTree();
		int size = 0;
		for (int i = 0; i < numPut; i++) {
			if (tree2.put((int) (Math.random() * numPut))) {
				size++;
			}
			
		}
		
		assertEquals(size, tree2.size());
	}
	@Test
	void testRemove2() {
		AVLTree tree = new AVLTree();
		tree.put(10);
		tree.put(7);
		tree.put(12);
		tree.put(5);
		tree.put(9);
		tree.put(14);
		tree.put(4);
		tree.put(6);
		tree.put(8);
		tree.delete(10);
		
	}
	
	@Test
	void testRemove3() {
		AVLTree testTree = new AVLTree();
		testTree.put(20);
		testTree.put(10);
		testTree.put(30);
		testTree.put(5);
		testTree.put(15);
		
		assertEquals("20 10 5 15 30 ", testTree.preOrder());
		testTree.delete(20);
		assertEquals("15 10 5 30 ", testTree.preOrder());
		
	}
	
	@Test
	void deleteAllLeaves() {
		AVLTree testTree = new AVLTree();
		testTree.put(20);
		testTree.put(10);
		testTree.put(30);
		testTree.put(5);
		testTree.put(15);
		testTree.put(25);
		testTree.put(35);
		
		assertEquals("20 10 5 15 30 25 35 ", testTree.preOrder());
		testTree.delete(5);
		assertEquals("20 10 15 30 25 35 ", testTree.preOrder());
		testTree.delete(15);
		assertEquals("20 10 30 25 35 ", testTree.preOrder());
		testTree.delete(25);
		assertEquals("20 10 30 35 ", testTree.preOrder());
		testTree.delete(35);
		assertEquals("20 10 30 ", testTree.preOrder());
		testTree.delete(10);
		assertEquals("20 30 ", testTree.preOrder());
		testTree.delete(30);
		assertEquals("20 ", testTree.preOrder());
		testTree.delete(20);
		assertEquals("", testTree.preOrder());
	}
	
	@Test
	void deleteZeroBalNoSRoot() {
		AVLTree testTree = new AVLTree();
		testTree.put(20);
		testTree.put(10);
		testTree.put(30);
		testTree.put(5);
		testTree.put(15);
		testTree.put(25);
		testTree.put(35);
		
		assertEquals("20 10 5 15 30 25 35 ", testTree.preOrder());
		testTree.delete(20);
		assertEquals("15 10 5 30 25 35 ", testTree.preOrder());
	}
	
	@Test
	void deleteOneBalNoSRoot() {
		AVLTree testTree = new AVLTree();
		testTree.put(20);
		testTree.put(10);
		testTree.put(30);
		testTree.put(25);
		testTree.put(35);
		
		assertEquals("20 10 30 25 35 ", testTree.preOrder());
		testTree.delete(20);
		assertEquals("25 10 30 35 ", testTree.preOrder());
	}
	
	@Test
	void deleteNOneBalNoSRoot() {
		AVLTree testTree = new AVLTree();
		testTree.put(20);
		testTree.put(10);
		testTree.put(30);
		testTree.put(5);
		testTree.put(15);
		
		assertEquals("20 10 5 15 30 ", testTree.preOrder());
		testTree.delete(20);
		assertEquals("15 10 5 30 ", testTree.preOrder());
	}
	
	@Test
	void deleteZeroBalWithSRoot() {
		AVLTree testTree = new AVLTree();
		testTree.put(20);
		testTree.put(10);
		testTree.put(30);
		testTree.put(5);
		testTree.put(25);
		testTree.put(35);
		
		assertEquals("20 10 5 30 25 35 ", testTree.preOrder());
		testTree.delete(20);
		assertEquals("10 5 30 25 35 ", testTree.preOrder());
	}
	
	@Test
	void deleteOneBalWithSRoot() {
		AVLTree testTree = new AVLTree();
		testTree.put(20);
		testTree.put(10);
		testTree.put(30);
		testTree.put(5);
		testTree.put(15);
		testTree.put(25);
		testTree.put(35);
		testTree.put(12);
		testTree.put(27);
		
		assertEquals("20 10 5 15 12 30 25 27 35 ", testTree.preOrder());
		testTree.delete(20);
		assertEquals("15 10 5 12 30 25 27 35 ", testTree.preOrder());
	}
	
	@Test
	void deleteNOneBalWithSRoot() {
		AVLTree testTree = new AVLTree();
		testTree.put(20);
		testTree.put(10);
		testTree.put(30);
		testTree.put(5);
		testTree.put(15);
		testTree.put(25);
		testTree.put(35);
		testTree.put(12);
		
		assertEquals("20 10 5 15 12 30 25 35 ", testTree.preOrder());
		testTree.delete(20);
		assertEquals("15 10 5 12 30 25 35 ", testTree.preOrder());
	}
	
	@Test
	void deleteZeroBalNoS() {
		AVLTree testTree = new AVLTree();
		testTree.put(0);
		testTree.put(20);
		testTree.put(-20);
		testTree.put(10);
		testTree.put(-10);
		testTree.put(30);
		testTree.put(-30);
		testTree.put(5);
		testTree.put(-5);
		testTree.put(15);
		testTree.put(-15);
		testTree.put(25);
		testTree.put(-25);
		testTree.put(35);
		testTree.put(-35);
		
		assertEquals("0 -20 -30 -35 -25 -10 -15 -5 20 10 5 15 30 25 35 ", testTree.preOrder());
		testTree.delete(20);
		assertEquals("0 -20 -30 -35 -25 -10 -15 -5 15 10 5 30 25 35 ", testTree.preOrder());
	}
	
	@Test
	void deleteOneBalNoS() {
		AVLTree testTree = new AVLTree();
		testTree.put(0);
		testTree.put(20);
		testTree.put(-20);
		testTree.put(10);
		testTree.put(-10);
		testTree.put(30);
		testTree.put(-30);
		testTree.put(25);
		testTree.put(-25);
		testTree.put(35);
		testTree.put(-35);
		
		assertEquals("0 -20 -30 -35 -25 -10 20 10 30 25 35 ", testTree.preOrder());
		testTree.delete(20);
		assertEquals("0 -20 -30 -35 -25 -10 25 10 30 35 ", testTree.preOrder());
	}
	
	@Test
	void deleteNOneBalNoS() {
		AVLTree testTree = new AVLTree();
		testTree.put(0);
		testTree.put(20);
		testTree.put(-20);
		testTree.put(10);
		testTree.put(-10);
		testTree.put(30);
		testTree.put(-30);
		testTree.put(5);
		testTree.put(-5);
		testTree.put(15);
		testTree.put(-15);
		
		assertEquals("0 -20 -30 -10 -15 -5 20 10 5 15 30 ", testTree.preOrder());
		testTree.delete(20);
		assertEquals("0 -20 -30 -10 -15 -5 15 10 5 30 ", testTree.preOrder());
	}
	
	@Test
	void deleteZeroBalWithS() {
		AVLTree testTree = new AVLTree();
		testTree.put(0);
		testTree.put(20);
		testTree.put(-20);
		testTree.put(10);
		testTree.put(-10);
		testTree.put(30);
		testTree.put(-30);
		testTree.put(25);
		testTree.put(-25);
		testTree.put(5);
		testTree.put(-5);
		testTree.put(35);
		testTree.put(-35);
		
		assertEquals("0 -20 -30 -35 -25 -10 -5 20 10 5 30 25 35 ", testTree.preOrder());
		testTree.delete(20);
		assertEquals("0 -20 -30 -35 -25 -10 -5 10 5 30 25 35 ", testTree.preOrder());
	}
	
	@Test
	void deleteOneBalWithS() {
		AVLTree testTree = new AVLTree();
		testTree.put(0);
		testTree.put(20);
		testTree.put(-20);
		testTree.put(10);
		testTree.put(-10);
		testTree.put(30);
		testTree.put(-30);
		testTree.put(5);
		testTree.put(-5);
		testTree.put(15);
		testTree.put(-15);
		testTree.put(25);
		testTree.put(-25);
		testTree.put(35);
		testTree.put(-35);
		testTree.put(27);
		testTree.put(-27);
		
		assertEquals("0 -20 -30 -35 -25 -27 -10 -15 -5 20 10 5 15 30 25 27 35 ", testTree.preOrder());
		testTree.delete(20);
		assertEquals("0 -20 -30 -35 -25 -27 -10 -15 -5 25 10 5 15 30 27 35 ", testTree.preOrder());
	}
	
	@Test
	void deleteNOneBalWithS() {
		AVLTree testTree = new AVLTree();
		testTree.put(0);
		testTree.put(20);
		testTree.put(-20);
		testTree.put(10);
		testTree.put(-10);
		testTree.put(30);
		testTree.put(-30);
		testTree.put(5);
		testTree.put(-5);
		testTree.put(15);
		testTree.put(-15);
		testTree.put(25);
		testTree.put(-25);
		testTree.put(35);
		testTree.put(-35);
		testTree.put(12);
		testTree.put(-12);
		
		assertEquals("0 -20 -30 -35 -25 -10 -15 -12 -5 20 10 5 15 12 30 25 35 ", testTree.preOrder());
		testTree.delete(20);
		assertEquals("0 -20 -30 -35 -25 -10 -15 -12 -5 15 10 5 12 30 25 35 ", testTree.preOrder());
	}
	
	//these 4 methods check cases where the AVLTree deals with nulls whether it be in the parameter or
	//because a tree starts out null (if no errors are thrown and the preorder doesn't become unordinary
	//these cases are successful)
	@Test
	void deleteRoot() {
		AVLTree testTree = new AVLTree();
		testTree.put(10);
		testTree.delete(10);
		assertEquals("", testTree.preOrder());
	}
	
	@Test
	void deleteFirst() {
		AVLTree testTree = new AVLTree();
		testTree.delete(1);
		assertEquals("", testTree.preOrder());
	}
	
	@Test
	void deleteNull() {
		AVLTree testTree = new AVLTree();
		testTree.delete(null);
		assertEquals("", testTree.preOrder());
		
		testTree.put(10);
		testTree.delete(null);
		assertEquals("10 ", testTree.preOrder());
	}
	
	@Test
	void putNull() {
		AVLTree testTree = new AVLTree();
		testTree.put(null);
		assertEquals("", testTree.preOrder());
		
		testTree.put(10);
		testTree.put(null);
		assertEquals("10 ", testTree.preOrder());
	}
}
