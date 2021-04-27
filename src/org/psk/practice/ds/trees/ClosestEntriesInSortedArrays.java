package org.psk.practice.ds.trees;

import java.util.ArrayList;
import java.util.List;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.TreeSet;

/**
 * Design an algorithm that takes three sorted arrays and returns one entry from each such that the minimum interval
 * containing these three entries is as small as possible. For example, if the three arrays are (5,10,15),
 * (3,6,9,12,15), and (8,16,24), then 15,15,16 he in the smallest possible interval.
 */
public class ClosestEntriesInSortedArrays {

    public static class ArrayData implements Comparable<ArrayData> {

        public int val;
        public int idx;

        public ArrayData(int idx, int val) {
            this.val = val;
            this.idx = idx;
        }

        @Override
        public int compareTo(ArrayData o) {
            int result = Integer.compare(val, o.val);
            if (result == 0) {
                result = Integer.compare(idx, o.idx);
            }
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof ArrayData)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            ArrayData that = (ArrayData) obj;
            return this.val == that.val && this.idx == that.idx;
        }

        @Override
        public int hashCode() {
            return Objects.hash(val, idx);
        }
    }

    public static int findMinDistanceSortedArrays(List<List<Integer>> sortedArrays) {
        // Indices into each of the arrays.
        List<Integer> heads = new ArrayList<>(sortedArrays.size());
        for (List<Integer> arr : sortedArrays) {
            heads.add(0);
        }
        int result = Integer.MAX_VALUE;
        NavigableSet<ArrayData> currentHeads = new TreeSet<>();
        // Adds the minimum element of each array in to currentHeads.
        for (int i = 0; i < sortedArrays.size(); ++i) {
            currentHeads.add(new ArrayData(i, sortedArrays.get(i).get(heads.get(i))));
        }
        while (true) {
            result = Math.min(result, currentHeads.last().val - currentHeads.first().val);
            int idxNextMin = currentHeads.first().idx;
            // Return if some array has no remaining elements.
            heads.set(idxNextMin, heads.get(idxNextMin) + 1);
            if (heads.get(idxNextMin) >= sortedArrays.get(idxNextMin).size()) {
                return result;
            }
            currentHeads.pollFirst();
            currentHeads.add(new ArrayData(idxNextMin, sortedArrays.get(idxNextMin).get(heads.get(idxNextMin))));
        }
    }
}
