package org.psk.practice.ds.trees;

/**
 * A binary tree is symmetric if you can draw a vertical line through the root and then the left subtree is the mirror
 * image of the right subtree.
 */
public class SymmetricTree {

    public static class BinaryTreeNode<T> {

        public T data;
        public BinaryTreeNode<T> left, right;
    }

    public static boolean isSymmetric(BinaryTreeNode<Integer> tree) {
        return tree == null || checkSymmetric(tree.left, tree.right);
    }

    private static boolean checkSymmetric(BinaryTreeNode<Integer> subtree0,
                                          BinaryTreeNode<Integer> subtree1) {
        if (subtree0 == null && subtree1 == null) {
            return true;
        } else if (subtree0 != null && subtree1 != null) {
            return subtree0.data == subtree1.data
                   && checkSymmetric(subtree0.left, subtree1.right)
                   && checkSymmetric(subtree0.right, subtree1.left);
        }
        // One subtree is empty, and the other is not.
        return false;
    }
}
