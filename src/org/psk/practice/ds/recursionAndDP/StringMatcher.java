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

    // Another solution
    public static boolean isMatch(String regex, String s) {
        // Case (2.): regex starts with ’^’.
        if (regex.charAt(0) == '^') {
            return isMatchHere(regex.substring(1), s);
        }
        for (int i = 0; i <= s.length(); ++i) {
            if (isMatchHere(regex, s.substring(i))) {
                return true;
            }
        }
        return false;
    }

    private static boolean isMatchHere(String regex, String s) {
        if (regex.isEmpty()) {
            // Case (1.): Empty regex matches all strings.
            return true;
        }
        if ("$".equals(regex)) {
            // Case (2): Reach the end of regex, and last char is ’S’.
            return s.isEmpty();
        }
        if (regex.length() >= 2 && regex.charAt(1) == '*') {
            // Case (3.): A '*' match.
            // Iterate through s, checking '*' condition, if '*' condition holds, performs the remaining checks.
            for (int i = 0; i < s.length() && (regex.charAt(0) == '.' || regex.charAt(0) == s.charAt(i)); ++i) {
                if (isMatchHere(regex.substring(2), s.substring(i + 1))) {
                    return true;
                }
            }
            // See '*' matches zero character in s.
            return isMatchHere(regex.substring(2), s);
        }
        // Case (4.): regex begins with single character match.
        return !s.isEmpty() && (regex.charAt(0) == '.' || regex.charAt(0) == s.charAt(0))
               && isMatchHere(regex.substring(1), s.substring(1));
    }
}