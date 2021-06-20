package ab3.impl.KuparSiarheyeuIsmailov;

import ab3.Ab3;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Ab3ImplTest {

    Ab3Impl ab3Impl = new Ab3Impl();

    @Test
    void reconstructTree() {
        int[] inorder ={4, 2, 5, 1, 6, 3, 7};
        int[] preorder ={1, 2, 4, 5, 3, 6, 7};

        Ab3.Tree tree = ab3Impl.reconstructTree(inorder, preorder);

        assertEquals(1, tree.root.key);
        assertEquals(2, tree.root.left.key);
        assertEquals(3, tree.root.right.key);
        assertEquals(4, tree.root.left.left.key);
        assertEquals(5, tree.root.left.right.key);
        assertEquals(6, tree.root.right.left.key);
        assertEquals(7, tree.root.right.right.key);

        assertNull(tree.root.left.left.left);
        assertNull(tree.root.left.left.right);
        assertNull(tree.root.left.right.left);
        assertNull(tree.root.left.right.right);
        assertNull(tree.root.right.left.left);
        assertNull(tree.root.right.left.right);
        assertNull(tree.root.right.right.left);
        assertNull(tree.root.right.right.right);

    }


    /*
           (1)
             (2)
               (3)
	    LWR (inorder): 1 2 3
	    WLR (preorder): 1 2 3
     */
    @Test
    void reconstructWorstCaseRightTree() {
        int[] inorder ={1, 2, 3};
        int[] preorder ={1, 2, 3};

        Ab3.Tree tree = ab3Impl.reconstructTree(inorder, preorder);

        assertEquals(1, tree.root.key);
        assertEquals(2, tree.root.right.key);
        assertEquals(3, tree.root.right.right.key);

        assertNull(tree.root.left);
        assertNull(tree.root.right.left);
        assertNull(tree.root.right.right.left);
        assertNull(tree.root.right.right.right);
    }

    /*
           (1)
         (2)
        (3)
    LWR (inorder): 3 2 1
    WLR (preorder): 1 2 3
 */
    @Test
    void reconstructWorstCaseLeftTree() {
        int[] inorder ={3, 2, 1};
        int[] preorder ={1, 2, 3};

        Ab3.Tree tree = ab3Impl.reconstructTree(inorder, preorder);

        assertEquals(1, tree.root.key);
        assertEquals(2, tree.root.left.key);
        assertEquals(3, tree.root.left.left.key);

        assertNull(tree.root.right);
        assertNull(tree.root.left.right);
        assertNull(tree.root.left.left.left);
        assertNull(tree.root.left.left.right);
    }

    @Test
    void reconstructNode() {
    }

    @Test
    void indexOf() {
    }

    @Test
    void farthestVertex() {
    }

    @Test
    void lzwEncode() {
    }

    @Test
    void lzwDecode() {
    }
}