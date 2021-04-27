package org.psk.practice.ds.arrays;

import java.util.List;

public class FindDuplicateAndMissing {

    /**
     * Let t be
     * the element appearing twice, and m be the missing number. The sum of the numbers
     * from 0 to n -1, inclusive, is , so the sum of the elements in the array is exactly
     * "~1)" +t-m. This gives us an equation in t and m,but we need one more independent
     * equation to solve for them.
     * We could use an equation for the product of the elements in the array, or for the
     * sum of the squares of the elements in the array. Both of these are unsatisfactory
     * because they are prone to overflow.
     * The introduction to this problem showed how to find a missing number from an
     * array of n - 2 distinct numbers between 0 and n - 1 using XOR. Applying the same
     * idea to the current problem, i.e., computing the XOR of all the numbers from 0 to
     * n -1, inclusive, and the entries in the array, yields m © t. This does not seem very
     * helpful at first glance, since we want m and f. However, since m ± t, there must be
     * some bit in m©t that is set to 1, i.e., m and t differ in that bit. For example, the XOR of
     * (01101)2 and (11100)2 is (10001)2. The Is in the XOR are exactly the bits where (01101)2
     * and (11100)2 differ.
     * This fact allows us to focus on a subset of numbers from 0 to n -1 where we can
     * guarantee exactly one of m and t is present. Suppose we know m and t differ in the
     * kth bit. We compute the XOR of the numbers from 0 to n -1in which the kth bit is 1,
     * and the entries in the array in which the kth bit is 1. Let this XOR be h—by the logic
     * described in the problem statement, h must be one of m or f. We can make another
     * pass through A to determine if h is the duplicate or the missing element.
     * For example, for the array (5,3, 0,3,1,2), the duplicate entry t is 3
     * and the missing entry m is 4. Represented in binary the array is
     * ((101)2,(011)2,(000)2,(011)2,(001)2,(010)2). The XOR of these entries is (110)2. The
     * XOR of the numbers from 0 to 5, inclusive, is (001)2. The XOR of (110)2 and (001)2
     * is (111)2. This tells we can focus our attention on entries where the least significant
     * bit is 1. We compute the XOR of all numbers between 0 and 5 in which this bit is
     * 1, i.e., (001)2, (011)2, and (101)2, and all entries in the array in which this bit is 1, i.e.,
     * (101)2, (011)2/ (011)2/ and (001)2. The XOR of these seven values is (011)2. This implies
     * that (011)2 = 3 is either the missing or the duplicate entry. Another pass through
     * the array shows that it is the duplicate entry. We can then find the missing entry by
     * forming the XOR of (011)2 with all entries in the array, and XORing that result with
     * the XOR of all numbers from 0 to 5, which yields (100)2, i.e., 4.
     */

    private static class DuplicateAndMissing {

        public Integer duplicate;
        public Integer missing;

        public DuplicateAndMissing(Integer duplicate, Integer missing) {
            this.duplicate = duplicate;
            this.missing = missing;
        }
    }

    public static DuplicateAndMissing findDuplicateMissing(List<Integer> A) {
        // Compute the XOR of all numbers from SI to / A / - 1 and all entries in A.
        int missXORDup = 0;
        for (int i = 0; i < A.size(); ++i) {
            missXORDup ^= i ^ A.get(i);
        }
        // We need to find a bit that’s set to 1 in missXORDup. Such a bit
        // must exist if there is a single missing number and a single duplicated
        // number in A .
        //
        // The bit- fiddling assignment below sets all of bits in differBit to 0
        // except for the least significant bit in missXORDup that’s 1.
        int differBit = missXORDup & (~(missXORDup - 1));
        int missOrDup = 0;
        for (int i = 0; i < A.size(); ++i) {
            // Focus on entries and numbers in which the differBit-th bit is 1.
            if ((i & differBit) != 0) {
                missOrDup ^= i;
            }
            if ((A.get(i) & differBit) != 0) {
                missOrDup ^= A.get(i);
            }
            // missOrDup is either the missing value or the duplicated entry.
            for (int a : A) {
                if (a == missOrDup) { // missOrDup is the duplicate.
                    return new DuplicateAndMissing(missOrDup, missOrDup ^ missXORDup);
                }
            }
        }
        // missOrDup is the missing value.
        return new DuplicateAndMissing(missOrDup ^ missXORDup, missOrDup);
    }
}
