package org.psk.practice.ps;

import java.util.Arrays;

public class ArrayToAllPermutations {

    // For simplicity I am taking it as string array. Char Array will save space
    private static final String[] alphabet = {"", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
                                              "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "v", "z"};

    // A Binary Tree node
    private static class Node {

        String dataString;
        Node left;
        Node right;

        Node(String dataString) {
            this.dataString = dataString;
            // Be default left and right child are null.
        }

        public String getDataString() {
            return dataString;
        }
    }

    /**
     * <code>
     * Input: {1, 1} Output: ("aa", 'k") [2 interpretations: aa(1, 1), k(11)]
     * <p>
     * Input: {1, 2, 1} Output: ("aba", "au", "la") [3 interpretations: aba(1,2,1), au(1,21), la(12,1)]
     * <p>
     * Input: {9, 1, 8} Output: {"iah", "ir"} [2 interpretations: iah(9,1,8), ir(9,18)]
     * </code>
     * <pre>
     *                    �� {1,2,1}            Codes used in tree
     *                  /             \               "a" --> 1
     *                 /               \              "b" --> 2
     *             "a"{2,1}            "l"{1}         "l" --> 12
     *            /        \          /     \
     *           /          \        /       \
     *       "ab"{1}        "au"    "la"      null
     *        /    \
     *       /      \
     *    "aba"      null
     * </pre>
     */
    public static void main(String[] args) {
        int[] input = {1, 1, 2, 1};
        printAllInterpretations(input);
    }

    // The main function that prints all interpretations of array
    private static void printAllInterpretations(int[] input) {
        // Step 1: Create Tree
        Node root = createTree(0, "", input);

        // Step 2: Print Leaf nodes
        printLeaf(root);
    }

    // Method to create a binary tree which stores all interpretations of arr[] in lead nodes
    private static Node createTree(int data,
                                   String pString,
                                   int[] input) {
        // Invalid input as alphabets maps from 1 to 26
        if (data > 26) {
            return null;
        }

        // Parent String + String for this node
        String dataToStr = pString + alphabet[data];

        Node root = new Node(dataToStr);

        // if arr.length is 0 means we are done
        if (input.length != 0) {
            data = input[0];

            // new array will be from index 1 to end as we are consuming
            // first index with this node
            int[] newArr = Arrays.copyOfRange(input, 1, input.length);

            // left child
            root.left = createTree(data, dataToStr, newArr);

            // right child will be null if size of array is 0 or 1
            if (input.length > 1) {
                data = input[0] * 10 + input[1];

                // new array will be from index 2 to end as we
                // are consuming first two index with this node
                newArr = Arrays.copyOfRange(input, 2, input.length);

                root.right = createTree(data, dataToStr, newArr);
            }
        }
        return root;
    }

    // To print out leaf nodes
    private static void printLeaf(Node root) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            System.out.print(root.getDataString() + "   ");
        }

        printLeaf(root.left);
        printLeaf(root.right);
    }
}