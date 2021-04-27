package org.psk.practice.ds.recursionAndDP;

import java.util.ArrayList;
import java.util.List;

import org.ctci.sixthedition.solutions.ch02LinkedLists.Q2_02_Return_Kth_To_Last.QuestionC.Index;

/**
 * A string is said to be palindromic if it reads the same backwards and forwards. A decomposition of a string is a set
 * of strings whose concatenation is the string. For example, "611116" is palindromic, and "611", "11", "6" is one
 * decomposition for it. Compute all palindromic decompositions of a given string. For example, if the string is
 * "0204451881", then the decomposition "020", "44", "5", "1881" is palindromic, as is "020", "44", "5", "1", "88", "1".
 * However, "02044, "5", "1881" is not a palindromic decomposition.
 */
public class PalindromicDecomposition {

    public static List<List<String>> palindromicPartitioning(final String input) {
        final List<List<String>> result = new ArrayList<>();
        palindromicPartitioning(input, 0, new ArrayList<>(), result);
        return result;
    }

    private static void palindromicPartitioning(final String input, final int offset,
                                                    final ArrayList<String> partialPartition,
                                                    final List<List<String>> result) {
        if (offset == input.length()) {
            result.add(new ArrayList<>(partialPartition));
            return;
        }

        for (int i = offset + 1; i < input.length(); i++) {
            final String prefix = input.substring(offset, i);
            if (isPalindrome(prefix)) {
                partialPartition.add(prefix);
                palindromicPartitioning(input, i, partialPartition, result);
                partialPartition.remove(partialPartition.size() - 1);
            }
        }
    }

    private static boolean isPalindrome(final String prefix) {
        for (int i = 0, j = prefix.length() - 1; i < j; i++, j--) {
            if (prefix.charAt(i) != prefix.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}
