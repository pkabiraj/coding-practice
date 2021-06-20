package org.psk.practice.ps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Solution {

    public static void main(String[] args) {
        final Solution solution = new Solution();

        System.out.println(solution.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        System.out.println(Arrays.toString(solution.plusOne(new int[]{1, 9, 9})));

        System.out.println(solution.findKthLargest(new int[]{1, 8, 6, 5, 5, 4, 8, 3, 7}, 4));

        System.out.println(solution.findMedianSortedArrays(new int[]{1, 3}, new int[]{1}));

        System.out.println(solution.countSmaller(new int[]{5, 2, 6, 1}));
        //         0 1 2
        //         0 1 2
        //         0 0 0

        solution.printSortedStrings(3);

        final int sum = Arrays.stream(new int[]{1, -2, 1}).sum();
        System.out.println(sum);

        System.out.println(solution.compareVersion("1.0", "1.0.1"));

        System.out.println(solution.getMaxRainWaterBetweenTowers(new int[]{1, 5, 2, 3, 1, 6, 2, 4}));

        System.out.println(findFirstMissingPositive(new ArrayList<>(Arrays.asList(3, 5, 4, -1, 5, 1, -1))));
    }

    public static int findFirstMissingPositive(List<Integer> A) {
        // Record which values are present by writing A.get(i) to index A.get(i) - 1
        // if A.get(i) is between 1 and A .size(), inclusive. We save the value at
        // index A.get(i) - 1 by swapping it with the entry at i. If A.get(i) is
        // negative or greater than n, we just advance i.
        int i = 0;
        while (i < A.size()) {
            if (A.get(i) > 0 && A.get(i) <= A.size() && !A.get(A.get(i) - 1).equals(A.get(i))) {
                Collections.swap(A, i, A.get(i) - 1);
            } else {
                ++i;
            }
        }
        // Second pass through A to search for the first index i such that A.get(i)
        // != i + 1, indicating that i + 1 is absent. If all numbers between 1 and
        // A.size() are present, the smallest missing positive is A.size() + 1.
        for (i = 0; i < A.size(); ++i) {
            if (A.get(i) != i + 1) {
                return i + 1;
            }
        }
        return A.size() + 1;
    }

    //    public int getMaxRainWaterBetweenTowers(final int[] towerHeights) {
    //        int rainWater = 0, maxSeenSoFar = 0, maxSeenLeft = 0;
    //        int[] maxSeenRight = new int[towerHeights.length];
    //
    //        for (int i = towerHeights.length - 1; i >= 0; i--) {
    //            if (towerHeights[i] > maxSeenSoFar) {
    //                maxSeenSoFar = towerHeights[i];
    //            }
    //            maxSeenRight[i] = maxSeenSoFar;
    //        }
    //
    //        for (int i = 0; i < towerHeights.length; i++) {
    //            rainWater += Integer.max(Integer.min(maxSeenLeft, maxSeenRight[i]) - towerHeights[i], 0);
    //            if (towerHeights[i] > maxSeenLeft) {
    //                maxSeenLeft = towerHeights[i];
    //            }
    //        }
    //
    //        return rainWater;
    //    }

    public int getMaxRainWaterBetweenTowers(final int[] towerHeights) {
        int rainWater = 0, i = 0, j = towerHeights.length - 1;
        while (i < j) {
            int width = j - i;
            rainWater = Math.max(rainWater, width * Math.min(towerHeights[i], towerHeights[j]));

            if (towerHeights[i] > towerHeights[j]) {
                j--;
            } else if (towerHeights[i] < towerHeights[j]) {
                i++;
            } else {
                i++;
                j--;
            }
        }

        return rainWater;
    }

    public String licenseKeyFormatting(String S, int K) {
        if (S == null || S.isBlank()) {
            return S;
        }
        final char[] input = S.toCharArray();
        final Stack<Character> chars = new Stack<>();
        int j = 0;
        for (int i = S.length() - 1; i >= 0; i--) {
            if (j < K && input[i] != '-') {
                chars.push(Character.toUpperCase(input[i]));
                j++;
            } else {
                j = 0;
                chars.push('-');
            }
        }

        final StringBuilder license = new StringBuilder();
        while (!chars.isEmpty()) {
            license.append(chars.pop());
        }
        return license.toString();
    }

    public int compareVersion(String version1, String version2) {
        String[] version1Versions = version1.split("\\.");
        String[] version2Versions = version2.split("\\.");

        return compareVersion(version1Versions, version2Versions, 0);
    }

    private int compareVersion(String[] v1, String[] v2, int i) {
        int compare = 0;
        if (i < v1.length || i < v2.length) {
            String v1Ver = "0";
            String v2Ver = "0";
            if (i < v1.length) {
                v1Ver = v1[i];
            }
            if (i < v2.length) {
                v2Ver = v2[i];
            }
            if (Integer.valueOf(v1Ver).equals(Integer.valueOf(v2Ver))) {
                compare = compareVersion(v1, v2, i + 1);
            } else {
                compare = Integer.valueOf(v1Ver).compareTo(Integer.valueOf(v2Ver));
            }
        }
        return compare;
    }

    public void printSortedStrings(int remaining) {
        printSortedStrings(remaining, "");
    }

    private void printSortedStrings(final int remaining, final String prefix) {
        if (remaining == 0) {
            if (isInOrder(prefix)) {
                System.out.println(prefix);
            }
        } else {
            for (int i = 0; i < 26; i++) {
                char c = ithLetter(i);
                printSortedStrings(remaining - 1, prefix + c);
            }
        }
    }

    private boolean isInOrder(final String s) {
        for (int i = 1; i < s.length(); i++) {
            int prev = ithLetter(s.charAt(i - 1));
            int curr = ithLetter(s.charAt(i));

            if (prev > curr) {
                return false;
            }
        }
        return true;
    }

    private char ithLetter(final int i) {
        return (char) (((int) 'a') + i);
    }

    public List<Integer> countSmaller(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }
        final int len = nums.length;
        int[] cloned = new int[len];
        System.arraycopy(nums, 0, cloned, 0, len);

        Arrays.sort(cloned);

        final List<Integer> counts = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            final int pos = binarySearch(cloned, nums[i]);
            if (i == pos) {
                counts.add(0);
            } else {
                counts.add(Math.min(pos, len - i - 1));
            }
        }
        return counts;
    }

    private int binarySearch(final int[] nums, int key) {
        int low = 0;
        int high = nums.length - 1;
        int mid = high;

        while (low <= high) {
            mid = low + (high - low) / 2;
            if (nums[mid] == key) {
                break;
            } else if (nums[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return mid;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int x = nums1.length;
        int y = nums2.length;
        int low = 0;
        int high = x;

        while (low <= high) {
            int partitionX = (low + high) / 2;
            int partitionY = (x + y + 1) / 2 - partitionX;

            int maxLeftX = partitionX == 0 ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minRightX = partitionX == x ? Integer.MAX_VALUE : nums1[partitionX];

            int maxLeftY = partitionY == 0 ? Integer.MIN_VALUE : nums1[partitionY - 1];
            int minRightY = partitionY == y ? Integer.MAX_VALUE : nums1[partitionY];

            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                if ((x + y) % 2 == 0) {
                    return ((double) Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2;
                } else {
                    return (double) Math.max(maxLeftX, maxLeftY);
                }
            } else if (maxLeftX > minRightY) { // We are far on right, go to left
                high = partitionX - 1;
            } else {
                low = partitionX + 1;
            }
        }

        throw new IllegalArgumentException("Arrays are no sorted");
    }

    public int[] plusOne(int[] digits) {
        int remainder = 0;
        int no = 0;
        int overflow = 1;
        int i;
        for (i = digits.length - 1; i >= 0; i--) {
            no = digits[i] + overflow;
            remainder = no - 10;
            if (remainder < 0) {
                digits[i] = no;
                break;
            } else {
                digits[i] = no % 10;
                overflow = no / 10;
            }
        }

        if (i <= 0 && remainder >= 0) {
            int[] copied = new int[digits.length + 1];
            copied[0] = 1;
            System.arraycopy(digits, 0, copied, 1, copied.length - 1);
            return copied;
        }

        return digits;
    }

    public int maxArea(int[] height) {
        if (height.length < 2) {
            return 0;
        }
        if (height.length == 2) {
            return Math.min(height[0], height[1]);
        }

        int maxArea = 1;
        int area = 1;
        int start = 0;
        int end = height.length - 1;

        while (start < end) {
            area = Math.min(height[start], height[end]) * (end - start);
            maxArea = Math.max(maxArea, area);
            if (height[start] < height[end]) {
                start++;
            } else {
                end--;
            }
        }

        return maxArea;
    }

    public void swap(final int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    public int partition(final int[] nums, int left, int right, int pivotIndex) {
        int pivot = nums[pivotIndex];
        // 1. move pivot to end
        swap(nums, pivotIndex, right);
        int storeIndex = left;

        // 2. move all smaller elements to the left
        for (int i = left; i <= right; i++) {
            if (nums[i] < pivot) {
                swap(nums, storeIndex, i);
                storeIndex++;
            }
        }

        // 3. move pivot to its final place
        swap(nums, storeIndex, right);

        return storeIndex;
    }

    public int quickSelect(final int[] nums, int left, int right, int kSmallest) {
        // Returns the k-th smallest element of list within left..right
        if (left == right) { // If the list contains only one element,
            return nums[left];  // return that element
        }

        // select a random pivotIndex
        Random random = new Random();
        int pivotIndex = left + random.nextInt(right - left);

        pivotIndex = partition(nums, left, right, pivotIndex);

        // the pivot is on (N - k)th smallest position
        if (kSmallest == pivotIndex) {
            return nums[kSmallest];
        }
        // go left side
        else if (kSmallest < pivotIndex) {
            return quickSelect(nums, left, pivotIndex - 1, kSmallest);
        }
        // go right side
        return quickSelect(nums, pivotIndex + 1, right, kSmallest);
    }

    public int findKthLargest(int[] nums, int k) {
        int size = nums.length;
        // kth largest is (N - k)th smallest
        return quickSelect(nums, 0, size - 1, size - k);
    }
}
