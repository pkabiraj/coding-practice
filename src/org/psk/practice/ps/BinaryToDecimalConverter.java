package org.psk.practice.ps;

/**
 * @author pkabiraj
 */
public class BinaryToDecimalConverter {

    public static void main(String[] args) {
        fromStringToNo();

    }

    private static void fromStringToNo() {
        String binaryNo = "11110100110110";
        char[] binaryArr = binaryNo.toCharArray();
        int pow = 0;
        long decimalNo = 0;

        for (int i = binaryArr.length - 1; i >= 0; i--) {
            int no = Integer.parseInt("" + binaryArr[i]);
            decimalNo += no * Math.pow(2, pow++);
        }

        System.out.println("For Binary no => " + binaryNo + "  The decimal no is = " + decimalNo);
    }
}
