package org.psk.practice.ds.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddToNumArr {

    public static void main(String[] args) {
        System.out.println(plus(new ArrayList<>(Arrays.asList(1, 2, 9)), 1));
        System.out.println(plus(new ArrayList<>(Arrays.asList(9, 2, 1)), 9));
        System.out.println(plus(new ArrayList<>(Arrays.asList(9, 9, 1)), 9));
        System.out.println(plus(new ArrayList<>(Arrays.asList(1, 9, 1)), 9));
        System.out.println(plus(new ArrayList<>(Arrays.asList(9, 9, 9)), 1));
    }

    public static List<Integer> plus(List<Integer> digits, int no) {
        int carry = no;
        for (int i = digits.size() - 1; i >= 0; i--) {
            int sum = digits.get(i) + carry;
            carry = 0;
            if (sum >= 10) {
                sum = sum % 10;
                carry = 1;
            }
            digits.set(i, sum);
        }
        if (carry == 1) {
            digits.add(0, 1);
        }
        return digits;
    }
}
