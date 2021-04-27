package org.psk.practice.ds.recursionAndDP;

import java.util.Arrays;
import java.util.List;

public class DynamicProgramming {

    public static void main(String[] args) {
        DynamicProgramming programming = new DynamicProgramming();
        int change = 91;
        int[] cache = new int[change + 1];
        Arrays.fill(cache, -1);
        //        System.out.println(programming.makeChange(change, cache));

        System.out.println(programming.numCombinationsForFinalScore(12));
        System.out.println(numCombinationsForFinalScoreDP(12));
    }

    private static final int[] DENOMINATIONS = new int[]{1, 5, 10, 25};

    public int makeChange(final int change, final int[] cache) {
        if (change == 0) {
            return 0;
        }
        int minCoins = Integer.MAX_VALUE;
        for (int i = DENOMINATIONS.length - 1; i >= 0; i--) {
            int coin = DENOMINATIONS[i];
            if (change - coin >= 0) {
                if (cache[change - coin] >= 0) {
                    return cache[change - coin];
                } else {
                    int currMinCoins = makeChange(change - coin, cache);
                    minCoins = Math.min(currMinCoins, minCoins);
                }
            }
        }
        cache[change] = minCoins + 1;
        return cache[change];
    }


    private static final int[] SCORES = new int[]{2, 3, 7};

    public int numCombinationsForFinalScore(final int finalScore) {
        return numCombinationsForFinalScore(finalScore, 0, 0);
    }

    private int numCombinationsForFinalScore(final int remain, int start, int num) {
        if (remain == 0) {
            return 1;
        } else if (remain < 0) {
            return 0;
        }

        int sum = 0;
        for (int i = start; i < SCORES.length; i++) {
            sum += numCombinationsForFinalScore(remain - SCORES[i], i, sum + num);
        }
        return sum;
    }

    public static int numCombinationsForFinalScoreDP(int finalScore) {
        int[][] numCombinationsForScore = new int[SCORES.length][finalScore + 1];
        for (int i = 0; i < SCORES.length; ++i) {
            numCombinationsForScore[i][0] = 1; // One way to reach 9.
            for (int j = 1; j <= finalScore; ++j) {
                int withoutThisPlay = i - 1 >= 0 ? numCombinationsForScore[i - 1][j] : 0;
                int withThisPlay = j >= SCORES[i] ? numCombinationsForScore[i][j - SCORES[i]] : 0;
                numCombinationsForScore[i][j] = withoutThisPlay + withThisPlay;
            }
        }
        return numCombinationsForScore[SCORES.length - 1][finalScore];
    }

    public static int computeBinomialCoefficient(int n, int k) {
        return computeXChooseY(n, k, new int[n + 1][k + 1]);
    }

    /**
     * n  = n-1  + n-1 k     k     k-1
     */
    private static int computeXChooseY(int x, int y, int[][] xChooseY) {
        if (y == 0 || x == y) {
            return 1;
        }
        if (xChooseY[x][y] == 0) {
            int withoutY = computeXChooseY(x - 1, y, xChooseY);
            int withY = computeXChooseY(x - 1, y - 1, xChooseY);
            xChooseY[x][y] = withoutY + withY;
        }
        return xChooseY[x][y];
    }

    /**
     * <pre>In the pick-up-coins game, an even number of coins are placed in a line. Two players take turns at choosing
     * one coin each—they can only choose from the two coins at the ends of the line. The game ends when all the
     * coins have been picked up. The player whose coins have the higher total value wins. A player can't pass his turn.
     * Design an efficient algorithm for computing the maximum total value for the starting player in the
     * pick-up-coins game.</pre>
     */
    public static int pickUpCoins(List<Integer> coins) {
        return computeMaximumRevenueForRange(coins, 0, coins.size() - 1, new int[coins.size()][coins.size()]);
    }

    private static int computeMaximumRevenueForRange(
            List<Integer> coins, int a, int b, int[][] maximumRevenueForRange) {
        if (a > b) {
            // No coins left.
            return 0;
        }
        if (maximumRevenueForRange[a][b] == 0) {
            int maximumRevenueA = coins.get(a)
                                  + Math.min(computeMaximumRevenueForRange(coins, a + 2, b, maximumRevenueForRange),
                                             computeMaximumRevenueForRange(coins, a + 1, b - 1,
                                                                           maximumRevenueForRange));
            int maximumRevenueB = coins.get(b)
                                  + Math.min(computeMaximumRevenueForRange(coins, a + 1, b - 1, maximumRevenueForRange),
                                             computeMaximumRevenueForRange(coins, a, b - 2, maximumRevenueForRange));
            maximumRevenueForRange[a][b] = Math.max(maximumRevenueA, maximumRevenueB);
        }
        return maximumRevenueForRange[a][b];
    }

    /**
     * You are climbing stairs. You can advance 1 to k steps at a time. Your destination is exactly n steps up.
     */
    public static int numberOfWaysToTop(int top, int maximumStep) {
        return computeNumberOfWaysToH(top, maximumStep, new int[top + 1]);
    }

    private static int computeNumberOfWaysToH(int n, int maximumStep, int[] numberOfWaysToH) {
        if (n <= 1) {
            return 1;
        }
        if (numberOfWaysToH[n] == 0) {
            for (int i = 1; i <= maximumStep && n - i >= 0; ++i) {
                numberOfWaysToH[n] += computeNumberOfWaysToH(n - i, maximumStep, numberOfWaysToH);
            }
        }
        return numberOfWaysToH[n];
    }

    /**
     * Write a program that takes as input an array of numbers and returns the length of a longest non-decreasing
     * subsequence in the array. Note that elements of non-decreasing subsequence are not required to immediately follow
     * each other in the original sequence.
     */
    public static int longestNonDecreasingSubsequenceLength(final List<Integer> A) {
        int[] maxLength = new int[A.size() + 1];
        int overAllMax = Integer.MIN_VALUE;

        Arrays.fill(maxLength, 1);

        for (int i = 1; i < A.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (A.get(i) >= A.get(j)) {
                    maxLength[i] = Math.max(maxLength[i], maxLength[j] + 1);
                    overAllMax = Math.max(overAllMax, maxLength[i]);
                }
            }
        }

        return overAllMax;
    }
}
