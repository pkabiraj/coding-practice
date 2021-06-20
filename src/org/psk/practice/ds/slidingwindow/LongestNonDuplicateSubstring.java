package org.psk.practice.ds.slidingwindow;

public class LongestNonDuplicateSubstring {

    public static void main(String[] args) {
        System.out.println(longestNonDuplicatedSubstring("abcd"));
        System.out.println(longestNonDuplicatedSubstring("tmmzuxt"));
        System.out.println(longestNonDuplicatedSubstring("pwwkew"));
    }

    private static int longestNonDuplicatedSubstring(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }

        int result = 0;
        boolean[] charsPresent = new boolean[256];
        char[] strArr = str.toCharArray();
        int j = 0;
        for (int i = 0; i < strArr.length; i++) {
            if (charsPresent[strArr[i]]) {
                while (j < i) {
                    charsPresent[strArr[j]] = false;
                    if (strArr[i] == strArr[j]) {
                        j++;
                        break;
                    }
                    j++;
                }
            }
            charsPresent[strArr[i]] = true;
            int currentMax = i - j + 1;
//            if (i == strArr.length - 1 && j != 0) {
//                currentMax++;
//            }
            result = Math.max(result, currentMax);
        }
        return result;
    }
}
