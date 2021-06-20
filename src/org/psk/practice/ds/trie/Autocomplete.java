package org.psk.practice.ds.trie;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Autocomplete {

    // Trie node class
    private static class Node {

        String prefix;
        Map<Character, Node> children;

        // Does this node represent the last character in a word?
        boolean isWord;

        private Node(String prefix) {
            this.prefix = prefix;
            this.children = new HashMap<>();
        }
    }

    // The trie
    private final Node trie;

    // Construct the trie from the dictionary
    public Autocomplete(String[] dict) {
        trie = new Node("");
        for (String s : dict) {
            insertWord(s);
        }
    }

    // Insert a word into the trie
    private void insertWord(String s) {
        // Iterate through each character in the string. If the character is not
        // already in the trie then add it
        Node curr = trie;
        for (int i = 0; i < s.length(); i++) {
            if (!curr.children.containsKey(s.charAt(i))) {
                curr.children.put(s.charAt(i), new Node(s.substring(0, i + 1)));
            }
            curr = curr.children.get(s.charAt(i));
        }
        curr.isWord = true;
    }

    // Find all words in trie that start with prefix
    public List<String> getWordsForPrefix(String pre) {
        List<String> results = new LinkedList<>();

        // Iterate to the end of the prefix
        Node curr = trie;
        for (char c : pre.toCharArray()) {
            if (curr.children.containsKey(c)) {
                curr = curr.children.get(c);
            } else {
                return results;
            }
        }

        // At the end of the prefix, find all child words
        findAllChildWords(curr, results);
        return results;
    }

    // Recursively find every child word
    private void findAllChildWords(Node n, List<String> results) {
        if (n.isWord) {
            results.add(n.prefix);
        }
        for (Character c : n.children.keySet()) {
            findAllChildWords(n.children.get(c), results);
        }
    }
}