package org.psk.practice.ds;

import java.util.Optional;

public class MaximumSum {

    public static void main(String[] args) {
        final int n = 3;
        final int[][] stacks = {
                {100, 1, 1}, // 1 is at top
                {30, 2, 10}, // 10 is at top
                {20, 3, 6},  // 6 is at top
                {50, 9, 1}   // 1 is at top
        };
        final MaximumSum maximumSum = new MaximumSum();
        System.out.println(maximumSum.maxSum(stacks, n));
    }

    private int maxSum(final int[][] stacks, final int n) {
        final MaximumSum maximumSum = Optional.of(new MaximumSum()).orElse(new MaximumSum());

        int[][] sums = new int[stacks.length][];
        for (int i = 0; i < stacks.length; i++) {
            sums[i] = new int[stacks[i].length];
            int count = 0;
            for (int j = stacks[i].length - 1; j >= 0; j--) {
                count += stacks[i][j];
                sums[i][j] = count;
            }
        }

        // Follows knapsack kind of logic. Include an element OR Exclude the element
        int[][] dp = new int[stacks.length + 1][n + 1];
        for (int i = 1; i <= stacks.length; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i - 1][j];
                for (int k = stacks[i - 1].length - 1; k >= stacks[i - 1].length - 1 - (j - 1); k--) {
                    int count = stacks[i - 1].length - k;
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - count] + sums[i - 1][k]);
                }
            }
        }

        return dp[stacks.length][n];
    }
}
