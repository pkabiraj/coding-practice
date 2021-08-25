package org.psk.practice.ds.trees;

import java.util.Stack;

public class LowestCommonAncestor {

    public static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                   "val=" + val +
                   ", left=" + left +
                   ", right=" + right +
                   '}';
        }
    }

    /**
     * <pre>
     *                               3
     *                9                            8
     *        4             5                1         7
     *    0       2                       6
     * </pre>
     */

    public static void main(String[] args) {
        final LowestCommonAncestor lca = new LowestCommonAncestor();

        final TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.left.left = new TreeNode(4);
        root.left.left.left = new TreeNode(0);
        root.left.left.left.right = new TreeNode(2);
        root.left.right = new TreeNode(5);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(1);
        root.right.left.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        System.out.println(lca.lca(root, root.left.left.left, root.left.right));

        System.out.println(lca.lca(root, root.left.left, root.left.left));

        System.out.println(lca.lca(root, root.left.left, root.right.left));
    }

    public Integer lca(TreeNode root, TreeNode node1, TreeNode node2) {
        if (node1 == null || node2 == null) {
            return null;
        } else if (node1.val == node2.val) {
            return node1.val;
        }

        final Stack<Integer> node1Path = getPath(root, node1);
        final Stack<Integer> node2Path = getPath(root, node2);

        Integer lca = null;
        while ((node1Path != null && !node1Path.isEmpty()) && (node2Path != null && !node2Path.isEmpty())) {
            Integer node1Val = node1Path.pop();
            Integer node2Val = node2Path.pop();

            if (node1Val.equals(node2Val)) {
                lca = node1Val;
            } else {
                break;
            }
        }

        return lca;
    }

    private Stack<Integer> getPath(final TreeNode root, final TreeNode node) {
        if (root == null) {
            return null;
        }
        if (root.val == node.val) {
            final Stack<Integer> stack = new Stack<>();
            stack.push(root.val);
            return stack;
        }
        Stack<Integer> leftPath = getPath(root.left, node);
        if (leftPath != null) {
            leftPath.push(root.val);
            return leftPath;
        }
        Stack<Integer> rightPath = getPath(root.right, node);
        if (rightPath != null) {
            rightPath.push(root.val);
            return rightPath;
        }
        return null;
    }
}
