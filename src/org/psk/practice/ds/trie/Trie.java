package org.psk.practice.ds.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Trie {

    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String s) {
        TrieNode current = root;
        if (isEmpty(s)) {
            current.setWord(true); // for blank operator.
        }
        s = normalizeString(s);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            TrieNode childNode = current.getChildNode(c);
            if (childNode == null) {
                childNode = new TrieNode(c);
                current.getChildNodes().put(c, childNode);
            }
            // update current node.
            current = childNode;
        }
        // update current node isWord to true.
        current.setWord(true);
        System.out.println("Inserted String:" + s + " into Trie");
    }

    public void search(String s) {
        TrieNode current = root;
        if (!isEmpty(s)) {
            s = normalizeString(s);
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                TrieNode childNode = current.getChildNode(c);
                if (childNode != null) {
                    current = childNode; // increment child node.
                } else {
                    System.out.println("String:" + s + " not found.");
                    return;
                }
            }
            if (current.isWord()) {
                System.out.println("String:" + s + " found.");
            }
        }
    }

    public void delete(String s, TrieNode node) {
        if (!isEmpty(s)) {
            s = normalizeString(s);
            if (node != null) {
                char c = s.charAt(0);
                TrieNode childNode = node.getChildNode(c);
                delete(s.substring(1), childNode);
                System.out.println("Deleted" + c);
            }
        }
    }

    public void printWordsMatchingPrefix(String prefix, TrieNode current, String entirePrefix) {
        if (!isEmpty(prefix)) {
            prefix = normalizeString(prefix);
            char c = prefix.charAt(0);
            childPrefix(current, entirePrefix, c, prefix.substring(prefix.indexOf(c) + 1));
        } else {
            // denotes reaching end of prefix, begin traversing to get matching words.
            Map<Character, TrieNode> map = current.getChildNodes();
            if (map != null) {
                for (char c : map.keySet()) {
                    childPrefix(current, entirePrefix, c, "");
                }
            }
        }
    }

    private void childPrefix(final TrieNode current, final String entirePrefix, final char c, String prefix) {
        TrieNode child = current.getChildNode(c);
        if (child != null) {
            String word = entirePrefix + child.getContent();
            if (child.isWord()) {
                System.out.println("Found word:" + word);
            }
            printWordsMatchingPrefix(prefix, child, word);
        }
    }

    public void prefixMatch(String input) {
        List<String> matches = new ArrayList<>();
        prefixMatch(normalizeString(input).trim().toCharArray(), 0, root, new StringBuilder(), matches);

        if (matches.isEmpty()) {
            System.out.println("No matches found for prefix = " + input);
        } else {
            System.out.println("Following are the matches for = " + input);
            for (String match : matches) {
                System.out.println(match);
            }
        }
    }

    private void prefixMatch(char[] input,
                             int currentIndex,
                             TrieNode current,
                             StringBuilder prefix,
                             List<String> matches) {
        if (currentIndex < input.length) { // Going through the prefix
            char c = input[currentIndex];
            TrieNode childNode = current.getChildNode(c);
            if (childNode == null) {
                return;
            }
            prefix.append(c);
            if (childNode.isWord()) {
                matches.add(prefix.toString());
            }
            prefixMatch(input, currentIndex + 1, childNode, prefix, matches);
        } else { // Prefix finished. Find all matching words
            Map<Character, TrieNode> childNodes = current.getChildNodes();
            if (childNodes != null && !childNodes.isEmpty()) {
                Set<Entry<Character, TrieNode>> childEntries = childNodes.entrySet();
                for (Entry<Character, TrieNode> childEntry : childEntries) {
                    TrieNode node = childEntry.getValue();
                    prefix.append(node.getContent());
                    if (node.isWord()) {
                        matches.add(prefix.toString());
                    }
                    prefixMatch(input, currentIndex, node, prefix, matches);
                }
                prefix.setLength(prefix.length() - 1);
            } else {
                prefix.setLength(prefix.length() - 1);
            }
        }
    }

    private String normalizeString(String s) {
        return s.toLowerCase();
    }

    public TrieNode getRoot() {
        return root;
    }

    private boolean isEmpty(String s) {
        if (s == null) {
            return true;
        }

        return s.trim().length() < 1;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("Pinaki");
        trie.insert("Sankar");
        trie.insert("Kabiraj");
        trie.insert("Shankar");
        trie.insert("Shankara");
        trie.insert("Shamkar");
        trie.insert("Shonkor");
        trie.insert("Sankor");

        trie.printWordsMatchingPrefix("sh", trie.getRoot(), "");

        trie.prefixMatch("sh");

        trie.search("Pinaki");
    }
}
