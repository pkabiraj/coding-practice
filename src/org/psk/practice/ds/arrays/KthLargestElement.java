package org.psk.practice.ds.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class KthLargestElement {

    private static class Compare {

        private static class GreaterThan implements Comparator<Integer> {

            public int compare(Integer a, Integer b) {
                return (a > b) ? -1 : (a.equals(b) ? 0 : 1);
            }
        }

        public static final GreaterThan GREATER_THAN = new GreaterThan();
    }

    // The numbering starts from one, i.e., if A = [3,1,-1,2] then
    // findKthLargest(A , 1) returns 3, findKthLargest(A , 2) returns 2,
    // findKthLargest(A , 3) returns 1, and findKthLargest(A, 4) returns -1.
    public static int findKthLargest(List<Integer> A, int k) {
        return findKth(A, k, Compare.GREATER_THAN);
    }

    public static int findKth(List<Integer> A, int k, Comparator<Integer> cmp) {
        int left = 0, right = A.size() - 1;
        Random r = new Random(0);
        while (left <= right) {
            // Generates a random integer in [left, right].
            int pivotIdx = r.nextInt(right - left + 1) + left;
            int newPivotIdx = partitionAroundPivot(left, right, pivotIdx, A, cmp);
            if (newPivotIdx == k - 1) {
                return A.get(newPivotIdx);
            } else if (newPivotIdx > k - 1) {
                right = newPivotIdx - 1;
            } else { // newPivotIdx < k - 1.
                left = newPivotIdx + 1;
            }
        }
        return A.get(right);
    }

    // Partitions A.subList(left , right+1) around pivotIdx , returns the new index
    // of the pivot, newPivotldx , after partition. After partitioning ,
    // A.subList(left, newPivotldx) contains elements that are less than the
    // pivot, and A.subList(newPivotldx + 1 , right + 1) contains elements that
    // are greater than the pivot.
    //
    // Note: "less than" is defined by the Comparator object.
    //
    // Returns the new index of the pivot element after partition.
    private static int partitionAroundPivot(int left, int right, int pivotIdx,
                                            List<Integer> A,
                                            Comparator<Integer> cmp) {
        int pivotValue = A.get(pivotIdx);
        int newPivotIdx = left;
        Collections.swap(A, pivotIdx, right);
        for (int i = left; i < right; ++i) {
            if (cmp.compare(A.get(i), pivotValue) < 0) {
                Collections.swap(A, i, newPivotIdx++);
            }
        }
        Collections.swap(A, right, newPivotIdx);
        return newPivotIdx;
    }

    public static void main(String[] args) {
        final List<Integer> A = new ArrayList<>(Arrays.asList(3, 2, 1, 5, 4));
        System.out.println(findKthLargest(A, 3));
    }
}
