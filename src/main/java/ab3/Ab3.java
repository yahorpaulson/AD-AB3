package ab3;

import java.util.HashMap;

public interface Ab3 {

	/**
	 * Eine Klasse um einen Baumknoten zu repräsentieren.
	 * A class to represent a tree node.
	 */
	public class Node {
		public Node left, right; /** Pointer to children */
		public int key; /** The value of the key inside the node */
	}

	/**
	 * Eine Klasse um einen Wurzelbaum zu repräsentieren.
	 * A class to represent a rooted tree.
	 */
	public class Tree {
		public Node root; /** Pointer to the root node */
		public int size; /** The number of nodes in the tree */
	}

	/**
	 * Implementieren Sie eine Methode, um einen Baum aus seiner LWR-
	 * (inorder) und WLR- (preorder) Ordnung zu rekonstruieren.
	 *
	 * Implement a method that reconstructs a tree from its inorder (LNR)
	 * and preorder (NLR) traversals.
	 *
	 * @param inorder Ein Array mit Schlüsseln des Baums in LWR-Ordnung
	 * 		  Array containing the keys of the tree in LNR order
	 *
	 * @param preorder Ein Array mit Schlüsseln des Baums in WLR-Ordnung
	 * 		   Array containing the keys of the tree in NLR order
	 *
	 * @return der rekonstruierte Baum
	 *         the reconstructed tree
	 */
	public Tree reconstructTree(int[] inorder, int[] preorder);

	/**
	 * Implementieren Sie Dijkstra's Algorithmus um den "am weitesten
	 * entfernten Knoten" von einem Ausgangsknoten in einem Graph zu
	 * finden. Der am weitesten entfernte Knoten ist jener, zu dem der
	 * kürzeste Pfad vom Ausgangsknoten, verglichen mit allen anderen
	 * Knoten, am längsten ist. Gehen Sie von zusammenhängenden,
	 * ungerichteten Graphen aus.
	 *
	 * Implement Dijkstra's Algorithm to find the "most distant vertex"
	 * from a given starting vertex in a graph. The most distant vertex is
	 * the one whose shortest path from the starting node is the longest
	 * among all nodes in the graph. You can assume that graphs are always
	 * connected and undirected.
	 *
	 * @param startingVertex der Ausgangsknoten
	 * 		         the starting vertex
	 *
	 * @param adjacencyCostMatrix eine Adjazenzmatrix, die gleichzeitig
	 *                            auch die Kosten zwischen zwei Knoten
	 *                            speichert. 0 -> keine Verbindung,
	 *                            i != 0 -> Kosten von i
	 * 		   
	 * 		              an ajdacency matrix that also stores cost
	 * 		              0 -> no connection
	 * 		              i != 0 -> cost of i
	 *
	 * @return die Entfernung zum am weitesten entfernten Knoten
	 *         the distance to the most distant vertex
	 */
	public int farthestVertex(int startingVertex, int[][] adjacencyCostMatrix);

	/**
	 * Implementieren Sie die LZW Codierung mit der angegebenen
	 * Codewort-Länge. Bei Codewort-Längen, die nicht durch 8 teilbar sind
	 * (also keine vollen Bytes) muss darauf geachtet werden, dass die
	 * einzelnen Bits korrekt aneinander gehängt werden. Das letzte Byte
	 * muss eventuell mit Nullen aufgefüllt werden. Codewort 1100110011
	 * kombiniert mit Codewort 1010101010 (insgesamt 20bit) ergäbe also
	 * [11001100][11101010][10100000], genau 3 Bytes.
	 *
	 * Implement LZW encoding using the given codeword length. For codeword
	 * sizes that are not divisible by 8 (i.e. not using full bytes), you
	 * need to make sure that the individual bits are concatinated
	 * appropriately. The last byte may need to be padded with additional
	 * zeros. Codeword 1100110011 prepended to codeword 1010101010 (20bit)
	 * would thus result in [11001100][11101010][10100000], 3 bytes in
	 * total.
	 *
	 * @param data die zu codierenden Daten
	 * 	       the data to be encoded
	 *
	 * @param bits die Codewort-Länge
	 * 	       the codeword length
	 *
	 * @return die codierten Daten
	 *         the encoded data
	 */
	public byte[] lzwEncode(byte[] data, int bits);

	/**
	 * Implementieren Sie die LZW Decodierung zur obigen Methode.
	 *
	 * Implement LZW decoding for the above method.
	 *
	 * @param data die zu decodierenden Daten
	 * 	       the data to be decoded
	 *
	 * @param bits die Codewort-Länge
	 * 	       the codeword length
	 *
	 * @return die decodierten Daten
	 *         the decoded data
	 */
	public byte[] lzwDecode(byte[] data, int bits);
}
