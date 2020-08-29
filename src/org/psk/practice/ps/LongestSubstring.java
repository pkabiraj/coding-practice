package org.psk.practice.ps;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string, find the longest substrings without repeating characters. Iterate through the given string, find the
 * longest maximum substrings.
 */
public class LongestSubstring {

    public static void main(String[] args) {
        final LongestSubstring lonSubStr = new LongestSubstring();
        System.out.println(lonSubStr.getLongestSubstr("java2novice"));
        System.out.println(lonSubStr.getLongestSubstr("java_language_is_sweet"));
        System.out.println(lonSubStr.getLongestSubstr("java_java_java_java"));
        System.out.println(lonSubStr.getLongestSubstr("abcabcbb"));
    }

    private final Set<String> subStrList = new HashSet<>();
    private int finalSubStrSize = 0;

    public Set<String> getLongestSubstr(final String input) {
        // reset instance variables
        subStrList.clear();
        finalSubStrSize = 0;
        // have a boolean flag on each character ascii value
        final boolean[] flag = new boolean[256];
        int j = 0;
        final char[] inputCharArr = input.toCharArray();
        for (int i = 0; i < inputCharArr.length; i++) {
            char c = inputCharArr[i];
            if (flag[c]) {
                extractSubString(inputCharArr, j, i);
                for (int k = j; k < i; k++) {
                    if (inputCharArr[k] == c) {
                        j = k + 1;
                        break;
                    }
                    flag[inputCharArr[k]] = false;
                }
            } else {
                flag[c] = true;
            }
        }
        extractSubString(inputCharArr, j, inputCharArr.length);
        return subStrList;
    }

    private void extractSubString(final char[] inputArr, final int start, final int end) {
        final StringBuilder sb = new StringBuilder();
        for (int i = start; i < end; i++) {
            sb.append(inputArr[i]);
        }
        String subStr = sb.toString();
        if (subStr.length() > finalSubStrSize) {
            finalSubStrSize = subStr.length();
            subStrList.clear();
            subStrList.add(subStr);
        } else if (subStr.length() == finalSubStrSize) {
            subStrList.add(subStr);
        }
    }
}