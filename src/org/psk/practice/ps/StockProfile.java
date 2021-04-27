package org.psk.practice.ps;

import java.util.List;

public class StockProfile {

    public static void main(String[] args) {
        double[] STOCK = {100, 113, 110, 85, 105, 102, 86, 63, 81, 101, 94, 106, 101, 79, 94, 90, 97};

        getBestTime(STOCK);
    }

    public static double computeMaxProfit(List<Double> prices) {
        double minPrice = Double.MAX_VALUE, maxProfit = 0.0D;
        for (Double price : prices) {
            maxProfit = Math.max(maxProfit, price - minPrice);
            minPrice = Math.min(minPrice, price);
        }
        return maxProfit;
    }

    public static void getBestTime(double[] stocks) {
        int min = 0;
        double maxDiff = 0;
        int buy = 0, sell = 0;
        for (int i = 0; i < stocks.length; i++) {
            if (stocks[i] < stocks[min]) {
                min = i;
            }
            double diff = stocks[i] - stocks[min];
            if (diff > maxDiff) {
                buy = min;
                sell = i;
                maxDiff = diff;
            }
        }

        System.out.println("buyTime=" + buy + " (" + stocks[buy] + "), profit=" + maxDiff + ", sellTime=" + sell
                           + " (" + stocks[sell] + ")");
    }
}