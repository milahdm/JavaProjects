import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

public class BinaryTreeTest {

    private BinaryTree<Integer> tree;

    @BeforeEach
    public void setUp() {
        // Setting up a sample tree:
        //       10
        //      /  \
        //     5   15
        //    / \    \
        //   3   7   20
        BinaryTree<Integer>.Node root = new BinaryTree<Integer>().new Node(10, null, null, null);
        BinaryTree<Integer>.Node left = new BinaryTree<Integer>().new Node(5, null, null, root);
        BinaryTree<Integer>.Node right = new BinaryTree<Integer>().new Node(15, null, null, root);
        BinaryTree<Integer>.Node leftLeft = new BinaryTree<Integer>().new Node(3, null, null, left);
        BinaryTree<Integer>.Node leftRight = new BinaryTree<Integer>().new Node(7, null, null, left);
        BinaryTree<Integer>.Node rightRight = new BinaryTree<Integer>().new Node(20, null, null, right);

        root.left = left;
        root.right = right;
        left.left = leftLeft;
        left.right = leftRight;
        right.right = rightRight;

        tree = new BinaryTree<>(root);
    }

    @Test
    public void testTreeStructure() {
        assertEquals(10, tree.getRoot().data);
        assertEquals(5, tree.getRoot().left.data);
        assertEquals(15, tree.getRoot().right.data);
        assertEquals(3, tree.getRoot().left.left.data);
        assertEquals(7, tree.getRoot().left.right.data);
        assertEquals(20, tree.getRoot().right.right.data);
    }

    @Test
    public void testSettersAndGetters() {
        BinaryTree<Integer>.Node newNode = new BinaryTree<Integer>().new Node(100, null, null, null);
        tree.getRoot().left = newNode;
        assertEquals(100, tree.getRoot().left.data);
    }

    @Test
    public void testIterators() {
        BinaryTree<Integer>.Iter iterator = tree.begin();
        assertEquals(3, iterator.get());
        iterator.advance();
        assertEquals(5, iterator.get());
        iterator.advance();
        assertEquals(7, iterator.get());
        iterator.advance();
        assertEquals(10, iterator.get());
        iterator.advance();
        assertEquals(15, iterator.get());
        iterator.advance();
        assertEquals(20, iterator.get());
    }

    @Test
    public void testReverseIterator() {
        BinaryTree<Integer>.Iter reverseIterator = tree.rbegin();
        assertEquals(20, reverseIterator.get());
        reverseIterator.retreat();
        assertEquals(15, reverseIterator.get());
        reverseIterator.retreat();
        assertEquals(10, reverseIterator.get());
        reverseIterator.retreat();
        assertEquals(7, reverseIterator.get());
        reverseIterator.retreat();
        assertEquals(5, reverseIterator.get());
        reverseIterator.retreat();
        assertEquals(3, reverseIterator.get());
    }
}
