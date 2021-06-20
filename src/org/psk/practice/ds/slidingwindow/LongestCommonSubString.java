package org.psk.practice.ds.slidingwindow;

import java.util.ArrayList;
import java.util.List;

public class LongestCommonSubString {

    public static void main(String[] args) {
        final LongestCommonSubString longestCommonSubString = new LongestCommonSubString();
        final List<String> commonSubStrings = longestCommonSubString.commonSubString("lclc", "clcl");
        System.out.println(commonSubStrings);
    }

    public List<String> commonSubString(final String s1, final String s2) {
        List<String> result = null;
        int[][] match = new int[s1.length()][s2.length()];
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    if (i == 0 || j == 0) {
                        match[i][j] = 1;
                    } else {
                        match[i][j] = match[i - 1][j - 1] + 1;
                    }

                    if (match[i][j] > max) {
                        max = match[i][j];
                        result = new ArrayList<>();
                        result.add(s1.substring(i - max + 1, i + 1));
                    } else if (match[i][j] == max) {
                        result.add(s1.substring(i - max + 1, i + 1));
                    }
                }
            }
        }

        return result;
    }
}
