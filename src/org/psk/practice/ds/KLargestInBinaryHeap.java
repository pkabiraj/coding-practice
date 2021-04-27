package org.psk.practice.ds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * <p>A heap contains limited information about the ordering of elements,so unlike a sorted array or a balanced BST,
 * naive algorithms for computing the k largest elements have a time complexity that depends linearly on the number of
 * elements in the collection.</p>
 * <p>Given a max-heap, represented as an array A,design an algorithm that computes the k largest elements stored in
 * the max-heap. You cannot modify the heap. For example, if the heap is (561, 314, 401, 28, 156, 359, 271,11,3), the
 * four largest elements are 561, 314, 401, and 359.</p>
 */
public class KLargestInBinaryHeap {

    /**
     * The ideal data structure for tracking the index to process next is a data structure which support fast
     * insertions, and fast extract-max, i.e., in a max-heap. So our algorithm is to create a max-heap of candidates,
     * initialized to hold the index 0, which serves as a reference to A[0]. The indices in the max-heap are ordered
     * according to corresponding value in A. We then iteratively perform k extract-max operations from the max-heap.
     * Each extraction of an index i is followed by inserting the indices of i's left child, 2i + 1, and right child, 2i
     * + 2, to the max-heap, assuming these children exist.
     */
    private static class HeapEntry {

        public Integer index;
        public Integer value;

        public HeapEntry(Integer index, Integer value) {
            this.index = index;
            this.value = value;
        }
    }

    private static class Compare implements Comparator<HeapEntry> {

        @Override
        public int compare(HeapEntry ol, HeapEntry o2) {
            return Integer.compare(o2.value, ol.value);
        }

        public static final Compare COMPARE_HEAP_ENTRIES = new Compare();
    }

    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public static List<Integer> kLargestInBinaryHeap(List<Integer> a, int k) {
        if (k <= 0) {
            return Collections.emptyList();
        }
        // Stores the (index, value)-pair in candidateMaxHeap. This heap is ordered by the value field.
        PriorityQueue<HeapEntry> candidateMaxHeap = new PriorityQueue<>(
                DEFAULT_INITIAL_CAPACITY, Compare.COMPARE_HEAP_ENTRIES);
        candidateMaxHeap.add(new HeapEntry(0, a.get(0)));
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; ++i) {
            Integer candidateIdx = candidateMaxHeap.peek().index;
            result.add(candidateMaxHeap.remove().value);
            int leftChildIdx = 2 * candidateIdx + 1;
            if (leftChildIdx < a.size()) {
                candidateMaxHeap.add(new HeapEntry(leftChildIdx, a.get(leftChildIdx)));
            }
            int rightChildIdx = 2 * candidateIdx + 2;
            if (rightChildIdx < a.size()) {
                candidateMaxHeap.add(new HeapEntry(rightChildIdx, a.get(rightChildIdx)));
            }
        }
        return result;
    }
}
