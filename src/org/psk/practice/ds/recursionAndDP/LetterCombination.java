package org.psk.practice.ds.recursionAndDP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombination {

    private static final Map<Character, List<String>> NO_CHARS = new HashMap<>();

    public static void main(String[] args) {
        final List<String> letterCombinations = new LetterCombination().letterCombinations("23");
        System.out.println(letterCombinations);
    }

    public List<String> letterCombinations(String digits) {
        populatePhoneNoCharsMap();

        if (digits == null || digits.isBlank()) {
            return Collections.emptyList();
        }

        for (final char digitChar : digits.toCharArray()) {
            int digit = Integer.parseInt(String.valueOf(digitChar));
            if (digit < 2 || digit > 9) {
                throw new IllegalArgumentException("Provided input is invalid " + digits);
            }
        }

        if (digits.length() == 1) {
            return NO_CHARS.get(digits.charAt(0));
        }

        return combination(digits, new ArrayList<>());
    }

    private List<String> combination(String digits, List<String> prefixes) {
        if (digits.isEmpty()) {
            return prefixes;
        }
        char digitChar = digits.charAt(0);
        List<String> chars = NO_CHARS.get(digitChar);
        List<String> newPrefixes = new ArrayList<>();
        if (prefixes.isEmpty()) {
            newPrefixes = chars;
        } else {
            for (String prefix : prefixes) {
                for (String newChar : chars) {
                    newPrefixes.add(prefix + newChar);
                }
            }
        }

        return combination(digits.substring(1), newPrefixes);
    }

    private void populatePhoneNoCharsMap() {
        NO_CHARS.put('2', Arrays.asList("a", "b", "c"));
        NO_CHARS.put('3', Arrays.asList("d", "e", "f"));
        NO_CHARS.put('4', Arrays.asList("g", "h", "i"));
        NO_CHARS.put('5', Arrays.asList("j", "k", "l"));
        NO_CHARS.put('6', Arrays.asList("m", "n", "o"));
        NO_CHARS.put('7', Arrays.asList("p", "q", "r", "s"));
        NO_CHARS.put('8', Arrays.asList("t", "u", "v"));
        NO_CHARS.put('9', Arrays.asList("w", "x", "y", "z"));
    }
}
