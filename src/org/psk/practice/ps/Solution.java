package org.psk.practice.ps;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

public class Solution {

    public static void main(String[] args) {
        final Solution solution = new Solution();

        //        System.out.println(solution.maxArea(new int[] {1,8,6,2,5,4,8,3,7}));
        //        System.out.println(Arrays.toString(solution.plusOne(new int[]{1, 9, 9})));

        //        System.out.println(solution.findKthLargest(new int[] {1,8,6,5,5,4,8,3,7}, 4));

        //        final List<List<String>> equations = Arrays.asList(
        //                Arrays.asList("a", "b"),
        //                Arrays.asList("b", "c")
        //        );
        //        final double[] values = {2.0, 3.0};
        //        final List<List<String>> queries = Arrays.asList(
        //                Arrays.asList("a", "c"),
        //                Arrays.asList("b", "a"),
        //                Arrays.asList("a", "e"),
        //                Arrays.asList("a", "a"),
        //                Arrays.asList("x", "x"),
        //                Arrays.asList("b", "a")
        //        );
        //        System.out.println(Arrays.toString(solution.calcEquation(equations, values, queries)));

        //        System.out.println(solution.findMedianSortedArrays(new int[]{1, 3}, new int[]{1}));

        //        System.out.println(solution.countSmaller(new int[]{5, 2, 6, 1}));
        // 0 1 2
        // 0 1 2
        // 0 0 0

        //        solution.printSortedStrings(3);

        //        final int sum = Arrays.stream(new int[]{1, -2, 1}).sum();
        //        System.out.println(sum);

        //        System.out.println(solution.compareVersion("1.0", "1.0.1"));

        //        System.out.println(solution.getMaxRainWaterBetweenTowers(new int[]{1, 5, 2, 3, 1, 6, 2, 4}));

        System.out.println(calculateLargestRectangle(Arrays.asList(1, 4, 2, 5, 6, 3, 2, 6, 6, 5, 2, 1, 3)));
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

    public double[] calcEquation(List<List<String>> equations,
                                 double[] values,
                                 List<List<String>> queries) {
        final Map<String, Map<String, Double>> graph = buildGraph(equations, values);

        return evaluate(queries, graph);
    }

    private Map<String, Map<String, Double>> buildGraph(final List<List<String>> equations,
                                                        final double[] values) {
        final Map<String, Map<String, Double>> graph = new HashMap<>();

        for (int i = 0; i < equations.size(); i++) {
            final List<String> equation = equations.get(i);
            final String dividend = equation.get(0);
            final String divisor = equation.get(1);
            double quotient = values[i];

            if (!graph.containsKey(dividend)) {
                graph.put(dividend, new HashMap<>());
            }
            if (!graph.containsKey(divisor)) {
                graph.put(divisor, new HashMap<>());
            }

            graph.get(dividend).put(divisor, quotient);
            graph.get(divisor).put(dividend, 1.0 / quotient);
        }

        return graph;
    }

    private double[] evaluate(final List<List<String>> queries,
                              final Map<String, Map<String, Double>> graph) {
        final double[] results = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {
            final List<String> query = queries.get(i);
            final String dividend = query.get(0);
            final String divisor = query.get(1);

            if (!graph.containsKey(dividend) || !graph.containsKey(divisor)) {
                results[i] = -1.0;
            } else if (dividend.equals(divisor)) {
                results[i] = 1.0;
            } else {
                results[i] = dfs(graph, dividend, divisor, 1.0, new HashSet<>());
            }
        }

        return results;
    }

    private double dfs(final Map<String, Map<String, Double>> graph,
                       final String currNode,
                       final String targetNode,
                       final double result,
                       final Set<String> visited) {
        visited.add(currNode);
        double prod = -1.0;
        final Map<String, Double> neighbours = graph.get(currNode);

        if (neighbours.containsKey(targetNode)) {
            prod = result * neighbours.get(targetNode);
        } else {
            for (Map.Entry<String, Double> neighbour : neighbours.entrySet()) {
                final String nextNode = neighbour.getKey();
                if (visited.contains(nextNode)) {
                    continue;
                }

                prod = dfs(graph, nextNode, targetNode, result * neighbour.getValue(), visited);
                if (prod != -1.0) {
                    break;
                }
            }
        }

        return prod;
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
            if (area > maxArea) {
                maxArea = area;
            }
            if (height[start] < height[end]) {
                start++;
            } else {
                end--;
            }
        }

        return maxArea;
    }

    int[] nums;

    public void swap(int a, int b) {
        int tmp = this.nums[a];
        this.nums[a] = this.nums[b];
        this.nums[b] = tmp;
    }


    public int partition(int left, int right, int pivot_index) {
        int pivot = this.nums[pivot_index];
        // 1. move pivot to end
        swap(pivot_index, right);
        int store_index = left;

        // 2. move all smaller elements to the left
        for (int i = left; i <= right; i++) {
            if (this.nums[i] < pivot) {
                swap(store_index, i);
                store_index++;
            }
        }

        // 3. move pivot to its final place
        swap(store_index, right);

        return store_index;
    }

    public int quickselect(int left, int right, int k_smallest) {
    /*
    Returns the k-th smallest element of list within left..right.
    */

        if (left == right) // If the list contains only one element,
        {
            return this.nums[left];  // return that element
        }

        // select a random pivot_index
        Random random_num = new Random();
        int pivot_index = left + random_num.nextInt(right - left);

        pivot_index = partition(left, right, pivot_index);

        // the pivot is on (N - k)th smallest position
        if (k_smallest == pivot_index) {
            return this.nums[k_smallest];
        }
        // go left side
        else if (k_smallest < pivot_index) {
            return quickselect(left, pivot_index - 1, k_smallest);
        }
        // go right side
        return quickselect(pivot_index + 1, right, k_smallest);
    }

    public int findKthLargest(int[] nums, int k) {
        this.nums = nums;
        int size = nums.length;
        // kth largest is (N - k)th smallest
        return quickselect(0, size - 1, size - k);
    }

    public static int calculateLargestRectangle(List<Integer> heights) {
        Stack<Integer> pillarIndices = new Stack<>();
        int maxRectangleArea = 0;
        for (int i = 0; i <= heights.size(); ++i) {
            if (!pillarIndices.isEmpty() && i < heights.size()
                && heights.get(i).equals(heights.get(pillarIndices.peek()))) {
                // Replace earlier building with same height by current building. This ensures the later buildings
                // have the correct left endpoint.
                pillarIndices.pop();
                pillarIndices.push(i);
            }
            // By iterating to heights.size() instead of heights.size() - 1, we can uniformly handle the computation
            // for rectangle area here.
            while (!pillarIndices.isEmpty() && isNewPillarOrReachEnd(heights, i, pillarIndices.peek())) {
                int height = heights.get(pillarIndices.pop());
                int width = pillarIndices.isEmpty() ? i : i - pillarIndices.peek() - 1;
                maxRectangleArea = Math.max(maxRectangleArea, height * width);
            }
            pillarIndices.push(i);
        }
        return maxRectangleArea;
    }

    private static boolean isNewPillarOrReachEnd(List<Integer> heights, int currIdx, int lastPillarIdx) {
        return currIdx >= heights.size() || heights.get(currIdx) < heights.get(lastPillarIdx);
    }
}
