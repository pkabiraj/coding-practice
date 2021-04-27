package org.psk.practice.ds.trees;

import java.util.Stack;

public class ZigzagTreeTraverser {

    public static void main(String[] args) {
        Node root = new Node();
        root.setData(1);
        Node l1l = new Node();
        l1l.setData(3);
        Node l1r = new Node();
        l1r.setData(2);
        root.setLeft(l1l);
        root.setRight(l1r);

        Node l2l1 = new Node();
        l2l1.setData(4);
        Node l2r1 = new Node();
        l2r1.setData(5);
        l1l.setLeft(l2l1);
        l1l.setRight(l2r1);

        Node l2l2 = new Node();
        l2l2.setData(6);
        Node l2r2 = new Node();
        l2r2.setData(7);
        l1r.setLeft(l2l2);
        l1r.setRight(l2r2);

        Stack<Node> parent = new Stack<>();
        parent.add(root);
        traverseZigzag(parent, false);
    }

    private static int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.getLeft()), maxDepth(root.getRight()));
    }

    private static void traverseZigzag(Stack<Node> parent,
                                       boolean l2r) {
        if (parent == null || parent.isEmpty()) {
            return;
        }
        Stack<Node> child = new Stack<>();

        while (!parent.isEmpty()) {
            Node node = parent.pop();

            System.out.println(node.getData());

            if (l2r) {
                if (node.getRight() != null) {
                    child.push(node.getRight());
                }
                if (node.getLeft() != null) {
                    child.push(node.getLeft());
                }
            } else {
                if (node.getLeft() != null) {
                    child.push(node.getLeft());
                }
                if (node.getRight() != null) {
                    child.push(node.getRight());
                }
            }
        }

        traverseZigzag(child, !l2r);
    }

    public static class Node {

        private int data;
        private Node left, right;

        public int getData() {
            return data;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public void setData(int data) {
            this.data = data;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}