package org.psk.practice.ds.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Let s and t be strings and D a dictionary, i.e., a set of strings. Define s to produce t if there exists a sequence
 * of strings from the dictionary P = (so,s1,...,sn_1 ) such that the first string is s, the last string is f, and
 * adjacent strings have the same length and differ in exactly one character. The sequence P is called a production
 * sequence. For example, if the dictionary is {bat, cot,dog,dag,dot, cat), then (cat, cot, dot,dog) is production
 * sequence. Given a dictionary D and two strings s and f, write a program to determine if s produces t. Assume that all
 * characters are lowercase alphabets. If s does produce f, output the length of a shortest production sequence;
 * otherwise, output -1.
 */
public class StringTransformation {

    private static class StringWithDistance {

        public String candidate;
        public Integer distance;

        public StringWithDistance(String candidate, Integer distance) {
            this.candidate = candidate;
            this.distance = distance;
        }
    }

    // Uses BFS to find the least steps of transformation.
    public static int transformString(Set<String> dictionary, String source, String target) {
        Queue<StringWithDistance> q = new LinkedList<>();
        dictionary.remove(source); // Marks source as visited by erasing it in dictionary.
        q.add(new StringWithDistance(source, 0));
        StringWithDistance f;
        while ((f = q.poll()) != null) {
            // Returns if we find a match.
            if (f.candidate.equals(target)) {
                return f.distance; // Number of steps reaches target.
            }
            // Tries all possible transformations of f.first.
            String str = f.candidate;
            for (int i = 0; i < str.length(); ++i) {
                String strStart = i == 0 ? "" : str.substring(0, i);
                String strEnd = i + 1 < str.length() ? str.substring(i + 1) : "";
                for (int j = 0; j < 26; ++j) { // Iterates through ’a’ ~ ’z’.
                    String modStr = strStart + (char) ('a' + j) + strEnd;
                    if (dictionary.remove(modStr)) {
                        q.add(new StringWithDistance(modStr, f.distance + 1));
                    }
                }
            }
        }
        return -1; // Cannot find a possible transformations.
    }
}
