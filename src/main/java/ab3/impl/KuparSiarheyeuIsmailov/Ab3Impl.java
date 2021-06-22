package ab3.impl.KuparSiarheyeuIsmailov;

import ab3.Ab3;

import java.util.LinkedList;
import java.util.List;

public class Ab3Impl implements Ab3 {

	/*

	     (1)
	  (2)   (3)
	(4)(5) (6)(7)

	LWR: 4 2 5 1 6 3 7
	WLR: 1 2 4 5 3 6 7

	     (1)
	  (2)   (3)
	(4)       (7)

	LWR: 4 2 1 3 7
	WLR: 1 2 4 3 7

	 */
	@Override
	public Tree reconstructTree(int[] inorder, int[] preorder) {
		// LWR-(inorder) und WLR-(preorder)
		// YOUR CODE HERE
		Tree tree = new Tree();
		tree.size = inorder.length;

		tree.root = reconstructNode(inorder, 0, inorder.length - 1, preorder, 0, preorder.length - 1);

		return tree;
	}

	Node reconstructNode(int[] inorder, int iFrom, int iTo, int[] preorder, int pFrom, int pTo) {
		if (pFrom < 0 || pTo < 0 || pTo >= preorder.length || iFrom > iTo || pFrom > pTo)
			return null;
		int key = preorder[pFrom];
		Node node = new Node();
		node.key = key;

		int posInorderRoot = indexOf(key, inorder, iFrom, iTo);

		int leftSize = posInorderRoot - iFrom;
		node.left = reconstructNode(inorder, iFrom, posInorderRoot - 1,
				preorder, pFrom + 1, pFrom + leftSize);

		//int rightSize = iTo - posInorderRoot;
		node.right = reconstructNode(inorder, posInorderRoot + 1, iTo,
				preorder, pFrom + leftSize + 1, pTo);

		return node;
	}

	int indexOf(int key, int[] array, int from, int to) {
		for (int i = from; i <= to; i++) {
			if (array[i] == key) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int farthestVertex(int startingVertex, int[][] adjacencyCostMatrix) {

		// YOUR CODE HERE
		return -1;
	}




	@Override
	public byte[] lzwEncode(byte[] data, int bits) {
		List<Long> output = new LinkedList<>();

		List<Byte> Wz = new LinkedList<>();

		CodeTabelle codeTabelle = new CodeTabelle(bits);

		for(byte b : data){
			Wz.add(b);

			if(!codeTabelle.containsPattern(Wz)){
				List<Byte> W = new LinkedList<>(Wz);
				W.remove(W.size()-1);

				byte z = Wz.get(Wz.size()-1);

				output.add(codeTabelle.codeWordForSymbol(W));

				codeTabelle.newSymbol(Wz);

				Wz.clear();
				Wz.add(z);

			}
		}
		long[] outputArray = null; //TODO:convert

		BitConverter converter = new BitConverter(bits);

		return converter.convertFromBitSize(outputArray);
	}

	@Override
	public byte[] lzwDecode(byte[] data, int bits) {

		BitConverter converter = new BitConverter(bits);

		long[] codewords = converter.convertToBitSize(data);

		return null;
	}
}
