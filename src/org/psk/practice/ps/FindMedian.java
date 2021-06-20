package org.psk.practice.ps;

import java.util.Collections;
import java.util.Iterator;
import java.util.PriorityQueue;

public class FindMedian {

    public static void main(String[] args) {
        int[] a = {1, 2, 5, 11, 15};
        int[] b = {3, 4, 13, 17, 18};
        // 1 2 3 4 5 11 13 15 17 18 (Median = (5 + 11)/2)
        final double median = new FindMedian().findMedianSortedArrays(a, b, 0, a.length - 1, 0, b.length - 1);
        System.out.println("Median=" + median);
    }

    public double findMedianSortedArrays(int[] a, int[] b, int startA, int endA, int startB, int endB) {
        if ((endA - startA == 1) && (endB - startB == 1)) {
            return (1.0 * (Math.max(a[startA], b[startB]) + Math.min(a[endA], b[endB]))) / 2;
        }

        int aMidIdx = (startA + endA) / 2;
        int bMidIdx = (startB + endB) / 2;

        int aMid = a[aMidIdx];
        int bMid = b[bMidIdx];

        if (aMid == bMid) {
            return aMid;
        }

        if (aMid < bMid) {
            // Since aMid <= median <= bMid, narrow down search by eliminating elements < aMid and > bMid
            startA = aMidIdx;
            endB = bMidIdx;
        } else {
            // Since bMid <= median <= aMid, narrow down search by eliminating elements < bMid and > aMid
            startB = bMidIdx;
            endA = aMidIdx;
        }

        return findMedianSortedArrays(a, b, startA, endA, startB, endB);
    }

    // For streamed continuous data
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public static void onlineMedian(Iterator<Integer> sequence) {
        // minHeap stores the larger half seen so far.
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        // maxHeap stores the smaller half seen so far.
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(DEFAULT_INITIAL_CAPACITY, Collections.reverseOrder());
        while (sequence.hasNext()) {
            int x = sequence.next();
            if (minHeap.isEmpty()) {
                // This is the very first element.
                minHeap.offer(x);
            } else {
                if (x >= minHeap.peek()) {
                    minHeap.offer(x);
                } else {
                    maxHeap.offer(x);
                }
            }
            // Ensure minHeap and maxHeap have equal number of elements if an even number of elements is read;
            // otherwise , minHeap must have one more element than maxHeap.
            if (minHeap.size() > maxHeap.size() + 1) {
                maxHeap.offer(minHeap.poll());
            } else if (maxHeap.size() > minHeap.size()) {
                minHeap.offer(maxHeap.poll());
            }
            System.out.println(minHeap.size() == maxHeap.size() ? 0.5 * (minHeap.peek() + maxHeap.peek())
                               : minHeap.peek());
        }
    }
}
