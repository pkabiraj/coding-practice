package org.psk.practice.ds.recursionAndDP;

public class Power {

    public static double pow(double a, int b) {
        // check the validity of a and b
        if (a == 0 && b == 0) {
            return Integer.MIN_VALUE;
        }

        if (a == 0) {
            return 0;
        }

        if (b == 0) {
            return 1;
        }

        if (b == 1) {
            return a;
        }

        boolean aMinus = a < 0;
        boolean bMinus = b < 0;

        int bAbs = Math.abs(b);
        double aAbs = Math.abs(a);

        // check if b is odd
        double tempAnswer;
        if ((b & 1) != 0) {
            tempAnswer = pow(aAbs, bAbs - 1) * aAbs;
        } else {
            tempAnswer = pow(aAbs * aAbs, bAbs / 2);
        }

        if (bMinus) {
            tempAnswer = 1.0 / tempAnswer;
        }
        if (aMinus && (b & 1) != 0) {
            tempAnswer *= -1;
        }

        return tempAnswer;

    }

    public static void main(String[] args) {
        System.out.println(Power.pow(-2, 9));

        // int a[] = { 7, 5, 7, 6, 7, 4, 7, 6, 7, 3, 7, 5, 7 };
        // int n = a.length;
        // int candidate = 0;
        // int count = 0;
        // for (int i = 0; i < n; i++) {
        // if (count == 0) {
        // candidate = i;
        // count = 1;
        // } else {
        // if (a[i] == a[candidate]) {
        // count--;
        // } else {
        // count++;
        // }
        // }
        // }
        // count = 0;
        // for (int i = 0; i < n; i++) {
        // if (a[i] == a[candidate]) {
        // count++;
        // }
        // }
        // System.out.println(count >= (n + 1) / 2 ? candidate : -1);
    }
}
