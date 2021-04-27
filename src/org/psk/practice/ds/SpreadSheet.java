package org.psk.practice.ds;

public class SpreadSheet {

    public static void main(String[] args) {
        System.out.println(ssDecodeColID("ABC"));
        System.out.println(ssDecodeColID("AA"));
        System.out.println(ssDecodeColID("BA"));
        System.out.println(ssDecodeColID("X"));
        System.out.println(ssDecodeColID("ZZ"));
        System.out.println(ssDecodeColID("A"));
        System.out.println(ssDecodeColID("Z"));

        System.out.println("---------------------------");
        System.out.println(ssGenerateColId(731));
        System.out.println(ssGenerateColId(27));
        System.out.println(ssGenerateColId(53));
        System.out.println(ssGenerateColId(24));
        System.out.println(ssGenerateColId(702));
        System.out.println(ssGenerateColId(1));
        System.out.println(ssGenerateColId(26));
    }

    public static int ssDecodeColID(final String col) {
        int result = 0;
        for (int i = 0; i < col.length(); i++) {
            char c = col.charAt(i);
            result = result * 26 + c - 'A' + 1;
        }
        return result;
    }

    public static String ssGenerateColId(int col) {
        final StringBuilder builder = new StringBuilder();
        while (col > 0) {
            int remainder = col % 26;
            char c = remainder == 0 ? 'Z' : (char) ('A' + remainder - 1);
            builder.append(c);
            col = remainder == 0 ? col - 1 : col;
            col = col / 26;
        }

        return builder.reverse().toString();
    }
}
