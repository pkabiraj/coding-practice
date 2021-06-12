package org.psk.practice.ps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrintTriplets {

    public static void main(String[] args) {
        final int[] arr = {-1, 0, 1, 2, -1, -4};
        threeSum(arr).forEach(System.out::println);
    }

    private static List<List<Integer>> threeSum(int[] arr) {
        List<List<Integer>> results = new ArrayList<>();

        Arrays.sort(arr);

        for (int i = 0; i < arr.length - 3; i++) {
            if (i == 0 || arr[i] > arr[i - 1]) {
                int start = i + 1;
                int end = arr.length - 1;

                while (start < end) {
                    final int val = arr[i] + arr[start] + arr[end];
                    if (val == 0) {
                        results.add(Arrays.asList(arr[i], arr[start], arr[end]));
                    }

                    if (val < 0) {
                        int currentStart = start;
                        while (arr[start] == arr[currentStart] && start < end) {
                            start++;
                        }
                    } else {
                        int currentEnd = end;
                        while (arr[end] == arr[currentEnd] && start < end) {
                            end--;
                        }
                    }
                }
            }
        }

        return results;
    }
}