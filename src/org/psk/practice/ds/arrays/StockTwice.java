package org.psk.practice.ds.arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Write a program that computes the maximum profit that can be made by buying and selling a share at most twice. The
 * second buy must be made on another date after the first sale.
 */
public class StockTwice {

    /**
     * The brute-force algorithm which examines all possible combinations of buy-sell-buy-sell days has complexity <9
     * (n4). The complexity can be improved to 0(n2) by applying the0(n) algorithm to each pair of subarrays formed by
     * splitting A. The inefficiency in the above approaches comes from not taking advantage of previous computations.
     * Suppose we record the best solution for A[0 : j], j between 1 and n —1, inclusive. Now we can do a reverse
     * iteration, computing the best solution for a single buy-and-sell for A[j : n — 1], j between 1 and n — 1,
     * inclusive. For each day, we combine this result with the result from the forward iteration for the previous
     * day—this yields the maximum profit if we buy and sell once before the current day and once at or after the
     * current day. For example, suppose the input array is (12,11,13,9,12,8,14,13,15). Then the most profit that can be
     * made with a single buy and sell by Day i (inclusive) is F = (0, 0, 2, 2,3, 3,6,6, 7). Working backwards, the most
     * profit that can be made with a single buy and sell on or after Day i is B = (7, 7, 7, 7, 7, 7, 2, 2, 0). To
     * combine these two, we compute M[i] = F[i-1] + B[i], where F[-l] is taken to be 0 (since the second buy must
     * happen strictly after the first sell). This yields M = (7, 7, 7, 9, 9,10,5,8,6), i.e., the maximum profit is 10.
     */
    public static double buyAndSellStockTwice(List<Double> prices) {
        double maxTotalProfit = 0.0d;
        List<Double> firstBuySellProfits = new ArrayList<>();
        double minPriceSoFar = Double.MAX_VALUE;
        // Forward phase. For each day, we record maximum profit if we
        // sell on that day.
        for (Double price : prices) {
            minPriceSoFar = Math.min(minPriceSoFar, price);
            maxTotalProfit = Math.max(maxTotalProfit, price - minPriceSoFar);
            firstBuySellProfits.add(maxTotalProfit);
        }
        // Backward phase. For each day, find the maximum profit if we make
        // the second buy on that day.
        double maxPriceSoFar = Double.MIN_VALUE;
        for (int i = prices.size() - 1; i > 0; --i) {
            maxPriceSoFar = Math.max(maxPriceSoFar, prices.get(i));
            maxTotalProfit = Math.max(maxTotalProfit, maxPriceSoFar - prices.get(i) + firstBuySellProfits.get(i - 1));
        }
        return maxTotalProfit;
    }
}
