package org.psk.practice.ds.trees;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an inorder traversal sequence and a preorder traversal sequence of a binary tree write a program to reconstruct
 * the tree. Assume each node has a unique key.
 */
public class ReConstructTree {

    public static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        final ReConstructTree tree = new ReConstructTree();
        final int[] preorder = new int[] {3, 9, 20,15,7};
        final int[] inorder = new int[] {9,3,15,20,7};
        final TreeNode node = tree.buildTree(preorder, inorder);

        System.out.println(node);
    }

    int preIndex = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null) {
            return null;
        }

        Map<Integer, Integer> valueIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            valueIndexMap.put(inorder[i], i);
        }

        return buildTree(preorder, inorder, 0, inorder.length - 1, valueIndexMap);
    }

    private TreeNode buildTree(int[] preorder, int[] inorder, int start, int end, Map<Integer, Integer> valueIndexMap) {
        if (start > end) {
            return null;
        }

        final TreeNode node = new TreeNode(preorder[preIndex++]);

        // If this node has no children then return
        if (start == end) {
            return node;
        }

        // Else find the index of this node in Inorder traversal
        int inIndex = valueIndexMap.get(node.val);

        node.left = buildTree(preorder, inorder, start, inIndex - 1, valueIndexMap);
        node.right = buildTree(preorder, inorder, inIndex + 1, end, valueIndexMap);

        return node;
    }
}
