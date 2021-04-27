package org.psk.practice.ds;

import java.util.List;

public class BinarySearch {

    /**
     * Write a method that takes a sorted array and a key and returns the index of the first occurrence of that key in
     * the array.
     */
    public static int searchFirstOfK(List<Integer> A, int k) {
        int left = 0, right = A.size() - 1, result = -1;
        // A.subList (left , right + 1) is the candidate set.
        while (left <= right) {
            int mid = left + ((right - left) / 2);
            if (A.get(mid) > k) {
                right = mid - 1;
            } else if (A.get(mid) == k) {
                result = mid;
                // Nothing to the right of mid can be the first occurrence of k.
                right = mid - 1;
            } else { // A.get (mid) < k
                left = mid + 1;
            }
        }
        return result;
    }

    /**
     * Implement a function which takes as input a floating point value and returns its square root.
     */
    public static double squareRoot(double x) {
        // Decides the search range according to x’s value relative to 1.(9.
        double left, right;
        if (x < 1.0) {
            left = x;
            right = 1.0;
        } else { // x >= 1.<9.
            left = 1.0;
            right = x;
        }
        // Keeps searching as long as left < right , within tolerance.
        while (compare(left, right) == Ordering.SMALLER) {
            double mid = left + 0.5 * (right - left);
            double midSquared = mid * mid;
            if (compare(midSquared, x) == Ordering.EQUAL) {
                return mid;
            } else if (compare(midSquared, x) == Ordering.LARGER) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return left;
    }

    private enum Ordering {SMALLER, EQUAL, LARGER}

    private static Ordering compare(double a, double b) {
        final double EPSILON = 0.00001;
        // Uses normalization for precision problem.
        double diff = (a - b) / b;
        return diff < -EPSILON ? Ordering.SMALLER
               : (diff > EPSILON ? Ordering.LARGER : Ordering.EQUAL);
    }
}
