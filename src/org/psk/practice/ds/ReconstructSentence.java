package org.psk.practice.ds;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ReconstructSentence {

    public static String reconstructSentence(final String noSpaceSentence,
                                             final Map<String, Boolean> dictionary) {
        int ptr = 0;
        Stack<Integer> spaces = new Stack<>();
        spaces.add(ptr);
        String currWord = "";
        while (!spaces.isEmpty()) {
            currWord = noSpaceSentence.substring(spaces.peek(), ptr);
            System.out.println("Curr Word: " + currWord);
            if (dictionary.containsKey(currWord)) {
                System.out.println(">>>Found word: " + currWord);
                spaces.push(ptr);
            }
            if (ptr >= noSpaceSentence.length()) { // reached the end
                if (dictionary.containsKey(currWord)) {
                    System.out.println("Sentence is done!");
                    spaces.pop(); // Done.
                    break;
                }
                System.out.println("Backtracking ...");
                if (spaces.isEmpty()) {
                    System.out.println("Sentence could not be reconstructed");
                    break;
                } else {
                    ptr = spaces.pop();
                }
            }
            ptr++;
        }

        // Reconstruct Sentence
        int to = noSpaceSentence.length();
        String sentence = "";
        while (!spaces.isEmpty()) {
            int from = spaces.pop();
            String word = noSpaceSentence.substring(from, to);
            to = from;
            sentence = word + " " + sentence;
        }

        return sentence;
    }

    public static void main(String[] args) {
        testValidSentence();
    }

    // Try it with this unit test.
    public static void testValidSentence() {
        // Map<String, Boolean> dictionary = new HashMap<>();
        // dictionary.put("the", true);
        // dictionary.put("dog", true);
        // dictionary.put("went", true);
        // dictionary.put("to", true);
        // dictionary.put("park", true);
        // dictionary.put("in", true);
        // dictionary.put("sunshine", true);
        // dictionary.put("happy", true);
        // dictionary.put("do", true);
        // dictionary.put("sun", true);
        // dictionary.put("shine", true);
        // dictionary.put("sunny", true);
        // dictionary.put("on", true);
        // dictionary.put("a", true);
        // dictionary.put("day", true);
        //
        // String sentence = "thehappydogwenttotheparkonasunnyday";
        //
        // System.out.println(sentence + " Reconstructed: " + reconstructSentence(sentence, dictionary));

        Map<String, Boolean> dictionary = new HashMap<>();
        dictionary.put("sachin", true);
        dictionary.put("is", true);
        dictionary.put("a", true);
        dictionary.put("bat", true);
        dictionary.put("man", true);
        dictionary.put("batsman", true);

        String sentence = "sachinisabatsman";

        System.out.println(sentence + " Reconstructed: " + reconstructSentence(sentence, dictionary));
    }
}