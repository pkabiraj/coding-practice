package org.psk.practice.ds.trie;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MapSum {

    public static void main(String[] args) {
        final MapSum mapSum = new MapSum();
        mapSum.insert("ap", 3);
        System.out.println(mapSum.sum("ape"));
        mapSum.insert("ap", 2);
        System.out.println(mapSum.sum("ap"));
        mapSum.insert("aplp", 4);
        System.out.println(mapSum.sum("ap"));
        mapSum.insert("appel", 5);
        System.out.println(mapSum.sum("ap"));
        mapSum.insert("abcd", 2);
        System.out.println(mapSum.sum("ap"));
        System.out.println(mapSum.sum("a"));
    }

    private final TrieNode root;

    /** Initialize your data structure here. */
    public MapSum() {
        root = new TrieNode();
    }

    public void insert(String key, int val) {
        if (key == null || key.isBlank()) {
            return;
        }

        final char[] chars = key.toCharArray();
        TrieNode node = root;
        for (char ch : chars) {
            TrieNode childNode = node.children.get(ch);
            if (childNode == null) {
                childNode = new TrieNode();
                node.children.put(ch, childNode);
            }
            node = childNode;
        }
        node.end = true;
        node.val = val;
    }

    public int sum(String prefix) {
        if (prefix == null || prefix.isBlank()) {
            return 0;
        }

        int sum = 0;
        final char[] chars = prefix.toCharArray();
        TrieNode node = root;
        for (char ch : chars) {
            node = node.children.get(ch);
            if (node == null) {
                return 0;
            }
        }

        if (node.end) {
            sum += node.val;
        }
        sum += getChildSum(node);

        return sum;
    }

    private int getChildSum(TrieNode node) {
        int sum = 0;
        if (!node.children.isEmpty()) {
            for (TrieNode child : node.children.values()) {
                if (child.end) {
                    sum += child.val;
                }
                sum += getChildSum(child);
            }
        }
        return sum;
    }

    private static class TrieNode {
        public Map<Character, TrieNode> children = new HashMap<>();
        public int val;
        public boolean end;
    }
}
