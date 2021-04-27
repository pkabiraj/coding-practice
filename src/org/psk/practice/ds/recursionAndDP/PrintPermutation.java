package org.psk.practice.ds.recursionAndDP;

/**
 * The Class PrintPermutation to print all the permutations of the input string. May not be the best solution but it
 * works fine.
 */
public class PrintPermutation {

    public static void main(String[] args) {
        String input = "abcd";

        permute(input.toCharArray(), 0);
        System.out.println("----------------------------------------------------------------");
        permute(input);
        System.out.println("----------------------------------------------------------------");
        permutation(input);
    }

    private static void permute(char[] str, int index) {
        if (index == str.length) { // Got new permutation, print it
            System.out.println(str);
            return;
        }

        for (int i = index; i < str.length; i++) {
            str = swap(str, index, i); // Swap characters to get new permutation

            permute(str, index + 1); // Recursive call

            str = swap(str, index, i); // Go back to previous version of string
        }
    }

    private static char[] swap(char[] str, int index, int i) {
        char temp = str[index];
        str[index] = str[i];
        str[i] = temp;

        return str;
    }

    private static void permute(String str) {
        int length = str.length();
        boolean[] used = new boolean[length];
        StringBuilder out = new StringBuilder();
        char[] in = str.toCharArray();

        doPermute(in, out, used, length, 0);
    }

    private static void doPermute(char[] in, StringBuilder out, boolean[] used, int length, int level) {
        if (level == length) {
            System.out.println(out.toString());
            return;
        }

        for (int i = 0; i < length; ++i) {
            if (used[i]) {
                continue;
            }

            out.append(in[i]);
            used[i] = true;
            doPermute(in, out, used, length, level + 1);
            used[i] = false;
            out.setLength(out.length() - 1);
        }
    }

    private static void permutation(final String input) {
        permutation(input, new StringBuilder());
    }

    private static void permutation(final String input, final StringBuilder prefix) {
        if (input.length() == 0) {
            System.out.println(prefix.toString());
        } else {
            for (int i = 0; i < input.length(); i++) {
                final String remainder = input.substring(0, i) + input.substring(i + 1);
                permutation(remainder, prefix.append(input.charAt(i)));
                prefix.setLength(prefix.length() - 1);
            }
        }
    }
}