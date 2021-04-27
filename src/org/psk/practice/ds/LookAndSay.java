package org.psk.practice.ds;

/**
 * The look-and-say sequence starts with 1. Subsequent numbers are derived by de¬ scribing the previous number in terms
 * of consecutive digits. Specifically, to generate an entry of the sequence from the previous entry, read off the
 * digits of the previ¬ ous entry, counting the number of digits in groups of the same digit. For exam¬ ple, 1; one 1;
 * two Is; one 2 then one 1; one 1, then one 2, then two Is; three Is, then two 2s, then one 1. The first eight numbers
 * in the look-and-say sequence are <1,11, 21,1211,111221,312211,13112221,1113213211>.
 * Write a program that takes as input an integer n and returns the nth integer in the look-and-say sequence. Return
 * the result as a string.
 */
public class LookAndSay {

    public static void main(String[] args) {
        lookAndSay(9);
    }

    public static void lookAndSay(int n) {
        String s = "1";
        for (int i = 1; i < n; ++i) {
            s = nextNumber(s);
            System.out.println(s);
        }
    }
    private static String nextNumber(String s) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            int count = 1 ;
            while (i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1)) {
                ++i ;
                ++count ;
            }
            result.append(count);
            result.append(s.charAt(i));
        }
        return result.toString();
    }
}
