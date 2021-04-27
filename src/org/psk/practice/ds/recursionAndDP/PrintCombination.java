package org.psk.practice.ds.recursionAndDP;

import java.util.LinkedList;
import java.util.List;

public class PrintCombination {

    public static void main(String[] args) {
        String input = "1234";

        doCombine(input.toCharArray(), new StringBuilder(), input.length(), 0);
        System.out.println("-------------------------------------------------------");

        final List<List<CharSequence>> combinations = combinations(input.toCharArray());
        combinations.forEach(combination -> System.out.println(String.join("", combination)));
    }

    private static void doCombine(char[] instr, StringBuilder outstr, int length, int start) {
        for (int i = start; i < length - 1; i++) {
            outstr.append(instr[i]);
            System.out.println(outstr);

            doCombine(instr, outstr, length, i + 1);

            outstr.setLength(outstr.length() - 1);
        }
        System.out.println(outstr.toString() + instr[length - 1]);
    }

    private static List<List<CharSequence>> combinations(char[] n) {
        List<List<CharSequence>> results = new LinkedList<>();
        combinations(n, 0, results, new LinkedList<>());
        return results;
    }

    private static void combinations(char[] n, int i, List<List<CharSequence>> results, List<CharSequence> path) {
        if (i == n.length) {
            results.add(path);
            return;
        }

        List<CharSequence> pathWithCurrent = new LinkedList<>(path);
        pathWithCurrent.add("" + n[i]);

        // Find all the combinations that include the current item
        combinations(n, i + 1, results, pathWithCurrent);

        // Find all the combinations that exclude the current item
        combinations(n, i + 1, results, path);
    }
}