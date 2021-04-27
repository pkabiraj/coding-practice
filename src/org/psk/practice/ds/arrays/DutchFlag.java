package org.psk.practice.ds.arrays;

import java.util.Collections;
import java.util.List;

/**
 * Write a program that takes an array A and an index i into A, and rearranges the elements such that all elements less
 * than A[i] (the "pivot") appear first, followed by elements equal to the pivot, followed by elements greater than the
 * pivot.
 */
public class DutchFlag {

    public enum Color {RED, WHITE, BLUE}

    public static void dutchFlagPartitionBruteForce(int pivotIndex, List<Color> A) {
        Color pivot = A.get(pivotIndex);
        // First pass: group elements smaller than pivot.
        for (int i = 0; i < A.size(); ++i) {
            // Look for a smaller element.
            for (int j = i + 1; j < A.size(); ++j) {
                if (A.get(j).ordinal() < pivot.ordinal()) {
                    Collections.swap(A, i, j);
                    break;
                }
            }
        }
        // Second pass: group elements larger than pivot.
        for (int i = A.size() - 1; i >= 0 && A.get(i).ordinal() >= pivot.ordinal(); --i) {
            // Look for a larger element. Stop when we reach an element less
            // than pivot, since first pass has moved them to the start of A.
            for (int j = i - 1; j >= 0 && A.get(j).ordinal() >= pivot.ordinal(); --j) {
                if (A.get(j).ordinal() > pivot.ordinal()) {
                    Collections.swap(A, i, j);
                    break;
                }
            }
        }
    }

    public static void dutchFlagPartitionSinglePass(int pivotIndex, List<Color> A) {
        Color pivot = A.get(pivotIndex);
        // First pass: group elements smaller than pivot.
        int smaller = 0;
        for (int i = 0; i < A.size(); ++i) {
            if (A.get(i).ordinal() < pivot.ordinal()) {
                Collections.swap(A, i, smaller++);
            }
        }
        // Second pass: group elements larger than pivot.
        int larger = A.size() - 1;
        for (int i = A.size() - 1; i >= 0 && A.get(i).ordinal() >= pivot.ordinal(); --i) {
            if (A.get(i).ordinal() > pivot.ordinal()) {
                Collections.swap(A, i, larger--);
            }
        }
    }
}
