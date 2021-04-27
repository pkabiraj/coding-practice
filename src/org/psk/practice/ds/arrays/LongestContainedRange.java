package org.psk.practice.ds.arrays;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Write a program which takes as input a set of integers represented by an array, and returns the size of a largest
 * subset of integers in the array having the property that if two integers are in the subset, then so are all integers
 * between them. For example, if the input is (3,-2,7, 9,8,1,2,0, -1,5,8), the largest such subset is{-2,-1,0,1,2,3), so
 * you should return 6.
 */
public class LongestContainedRange {

    public static int longestContainedRange(List<Integer> A) {
        // unprocessedEntries records the existence of each entry in A.
        Set<Integer> unprocessedEntries = new HashSet<>(A);
        int maxIntervalSize = 0;
        while (!unprocessedEntries.isEmpty()) {
            int a = unprocessedEntries.iterator().next();
            unprocessedEntries.remove(a);
            // Finds the lower bound of the largest range containing a.
            int lowerBound = a - 1;
            while (unprocessedEntries.contains(lowerBound)) {
                unprocessedEntries.remove(lowerBound);
                --lowerBound;
            }
            // Finds the upper bound of the largest range containing a.
            int upperBound = a + 1;
            while (unprocessedEntries.contains(upperBound)) {
                unprocessedEntries.remove(upperBound);
                ++upperBound;
            }
            maxIntervalSize = Math.max(upperBound - lowerBound - 1, maxIntervalSize);
        }
        return maxIntervalSize;
    }
}
