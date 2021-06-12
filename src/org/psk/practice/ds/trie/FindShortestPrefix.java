package org.psk.practice.ds.trie;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FindShortestPrefix {

    public static String findShortestPrefix(String s, Set<String> dictionary) {
        // Builds a trie according to given dictionary dictionary.
        Trie trie = new Trie();
        for (String word : dictionary) {
            trie.insert(word);
        }
        return trie.getShortestUniquePrefix(s);
    }

    private static class Trie {

        TrieNode root = new TrieNode();

        public void insert(final String word) {
            TrieNode p = root;
            for (char c : word.toCharArray()) {
                if (!p.children.containsKey(c)) {
                    p.children.put(c, new TrieNode());
                }
                p = p.children.get(c);
            }

            if (!p.end) {
                p.end = true;
            }
        }

        public String getShortestUniquePrefix(final String query) {
            StringBuilder prefix = new StringBuilder();
            TrieNode p = root;
            for (char c : query.toCharArray()) {
                prefix.append(c);
                if (!p.children.containsKey(c)) {
                    return prefix.toString();
                }
                p = p.children.get(c);
            }

            return "";
        }
    }

    private static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean end = false;
    }
}
