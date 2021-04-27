package org.psk.practice.ds.recursionAndDP;

public class StringMatcher {

    public static void main(String[] args) {
//        System.out.println(matchCore("aaab", "a.a*b"));
//        System.out.println(matchCore("ababbbbbb", "a.a*b*"));
//        System.out.println(matchCore("acb", "a.a*b"));

        System.out.println(matchCore("a", "ab*"));
    }

    /*
     * Match with pattern
     */
    public static boolean matchCore(String text, String pattern) {
        if (isBlank(pattern)) {
            return isBlank(text);
        } else if (pattern.equals(".*") && !isBlank(text)) {
            return true;
        } else if (isBlank(text) && (pattern.length() == 2 && pattern.charAt(1) == '*')) {
            return true;
        }

        if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
            if (!isBlank(text) && pattern.charAt(0) == text.charAt(0)) {
                return matchCore(text.substring(1), pattern.substring(2)) // move on to the next state
                       || matchCore(text.substring(1), pattern) // stay on the current state
                       || matchCore(text, pattern.substring(2)); // ignore a '*'
            } else {
                return matchCore(text, pattern.substring(2));
            }
        }

        if (!isBlank(text) && (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.')) {
            return matchCore(text.substring(1), pattern.substring(1));
        }

        return false;
    }

    private static boolean isBlank(String input) {
        return input == null || input.isBlank();
    }
}