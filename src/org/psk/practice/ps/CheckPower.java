package org.psk.practice.ps;

public class CheckPower {

    public static void main(String[] args) {
        for (int i = 0; i < 30; i++) {
            checkPowerOf2(i);
        }
    }

    public static void checkPowerOf2(int n) {
        if (n > 1 && (n & n - 1) == 0) {
            System.out.println("YES => " + n);
        } else {
            System.out.println("NO => " + n);
        }
    }
}
