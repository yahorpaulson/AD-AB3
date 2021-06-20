package ab3;

import ab3.impl.KuparSiarheyeuIsmailov.Ab3Impl;
import ab3.Ab3.Node;
import ab3.Ab3.Tree;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

public class Ab3Test {

    private Random rand = new Random(System.currentTimeMillis());

    private static Ab3 ab3Impl = new Ab3Impl();

    private static int NUM_TESTS = 1000;
    private static int NUM_TESTS_LARGE = 500000;
    private static int TREE_SIZE_SMALL = 200;
    private static int TREE_SIZE_LARGE = 2000;

    //assertEquals
    //assertTrue
    //assertArrayEquals
    
    @Test
    public void testTreeReconstruction()
    {
        for(int i = 0; i < NUM_TESTS; ++i)
        {
            Tree t = getRandomTree(TREE_SIZE_SMALL);
            int[] inorder = inorder(t);
            int[] preorder = preorder(t);

            Tree reconstructed = ab3Impl.reconstructTree(inorder, preorder);
//            System.out.println("inorder: " + Arrays.toString(inorder)
//                    + "\npreorder: " + Arrays.toString(preorder));
            assertTreeEquals(t.root, reconstructed.root);
        }
    }

    @Test
    public void testFarthestVertexSimple()
    {
        // Graph aus der VO, Knoten 1 hat Index 0
        int[][] graph = new int[9][9];
        graph[0][1] = graph[1][0] = 2;
        graph[0][6] = graph[6][0] = 15;
        graph[0][5] = graph[5][0] = 9;
        graph[1][6] = graph[6][1] = 6;
        graph[1][2] = graph[2][1] = 4;
        graph[6][7] = graph[7][6] = 15;
        graph[6][8] = graph[8][6] = 2;
        graph[5][7] = graph[7][5] = 11;
        graph[7][4] = graph[4][7] = 3;
        graph[7][8] = graph[8][7] = 4;
        graph[5][4] = graph[4][5] = 6;
        graph[4][3] = graph[3][4] = 1;
        graph[8][3] = graph[3][8] = 1;
        graph[8][2] = graph[2][8] = 15;
        graph[2][3] = graph[3][2] = 2;

        // Knoten 8, wie in der VO berechnet, ist am weitesten von Knoten 1 weg
        assertEquals(12, ab3Impl.farthestVertex(0, graph));
    }

    @Test
    public void testLzwEmpty()
    {
        byte[] empty = new byte[] {};

        assertArrayEquals(empty, ab3Impl.lzwEncode(empty, 16));
    }

    @Test
    public void testLzwSimple16bit()
    {
        byte[] input = "servus".getBytes();
        byte[] encoded = ab3Impl.lzwEncode(input, 16);
        byte[] decoded = ab3Impl.lzwDecode(encoded, 16);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testLzwSimple10bit()
    {
        byte[] input = "servus".getBytes();
        byte[] encoded = ab3Impl.lzwEncode(input, 10);
        byte[] decoded = ab3Impl.lzwDecode(encoded, 10);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testLzwCompressed16bit()
    {
        byte[] input = "ababcbababaaaaad".getBytes();
        byte[] encoded = ab3Impl.lzwEncode(input, 16);
        byte[] decoded = ab3Impl.lzwDecode(encoded, 16);
        assertArrayEquals(input, decoded);
    }

    @Test
    public void testLzwCompressed10bit()
    {
        byte[] input = "ababcbababaaaaad".getBytes();
        byte[] encoded = ab3Impl.lzwEncode(input, 10);
        byte[] decoded = ab3Impl.lzwDecode(encoded, 10);
        assertArrayEquals(input, decoded);
    }


    @Test
    public void testLzwSizeDecrease()
    {
        byte[] input = "servus alle miteinand".getBytes();
        byte[] encoded16 = ab3Impl.lzwEncode(input, 16);
        byte[] encoded10 = ab3Impl.lzwEncode(input, 10);
        assertTrue(encoded10.length < encoded16.length);
    }

    @Test
    public void testLzwEncoder16()
    {
        // yields 10 codewords as output (97 98 257 99 258 261 97 263 263 100).
        byte[] input = "ababcbababaaaaad".getBytes();
        byte[] encoded = ab3Impl.lzwEncode(input, 16);
        assertEquals(20, encoded.length); // 10 codewords, 16 bit = 2 byte each
        assertArrayEquals(
            new byte[] { 97, 0, 98, 0, 1, 1, 99, 0, 2, 1, 5, 1, 97, 0, 7, 1, 7, 1, 100, 0 },
            encoded
        );
    }

    @Test
    public void testLzwEncoderLength10()
    {
        // yields 10 codewords as output (97 98 257 99 258 261 97 263 263 100).
        byte[] input = "ababcbababaaaaad".getBytes();
        byte[] encoded = ab3Impl.lzwEncode(input, 10);
        assertEquals(13, encoded.length); // 10 codewords * 10 bit = 100 bits
    }


    ////////// HELPER FUNCTIONS //////////////////////////////////////////
    private int[] getRandomArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++)
            arr[i] = Math.abs(rand.nextInt(2 * size));
        return arr;
    }

    private int[] getRandomSortedArray(int size) {
        int[] arr = new int[size];
        int prev = 1;
        for (int i = 0; i < size; i++)
            prev = arr[i] = prev + 1 + Math.abs(rand.nextInt(10));
        return arr;
    }

    private Tree getRandomTree(int size)
    {
        Tree tree = new Tree();
        tree.size = size;
        tree.root = getRandomSubTree(size);
        return tree;
    }

    private Node getRandomSubTree(int size)
    {
        if(size == 0) return null;

        Node node = new Node();
        node.key = Math.abs(rand.nextInt());

        int subtreeType = rand.nextInt(3);
        if(subtreeType == 0) // both children
        {
            node.left = getRandomSubTree((int)((size - 1)/2.0 + 0.5));
            node.right = getRandomSubTree((int)((size - 1)/2.0));
        }
        else if(subtreeType == 1) // only left child
        {
            node.left = getRandomSubTree(size - 1);
        }
        else // only right child
        {
            node.right = getRandomSubTree(size - 1);
        }
        
        return node;
    }

    private void assertTreeEquals(Node expected, Node actual)
    {
        if(expected == null)
        {
            assertNull(actual);
        }
        else
        {
            assertNotNull(actual);
            assertEquals(expected.key, actual.key);
            assertTreeEquals(expected.left, actual.left);
            assertTreeEquals(expected.right, actual.right);
        }
    }

    private int[] inorder(Tree tree)
    {
        int[] result = new int[tree.size];
        int pos = 0;

        Stack<Node> s = new Stack<Node>();
        Node curr = tree.root;
        while (curr != null || s.size() > 0)
        {
            while (curr != null)
            {
                s.push(curr);
                curr = curr.left;
            }
     
            curr = s.pop();
            result[pos++] = curr.key;
            curr = curr.right;
        }

        return result;
    }

    private int[] preorder(Tree tree)
    {
        int[] result = new int[tree.size];
        int pos = 0;
        
        if (tree.root == null) return result;
 
        Stack<Node> s = new Stack<Node>();
        s.push(tree.root);
 
        while (!s.empty())
        {
            Node curr = s.pop();
            result[pos++] = curr.key;
            if (curr.right != null) s.push(curr.right);
            if (curr.left != null) s.push(curr.left);
        }

        return result;
    }
}
