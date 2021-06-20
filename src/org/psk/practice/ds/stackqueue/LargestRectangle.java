package org.psk.practice.ds.stackqueue;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class LargestRectangle {

    public static void main(String[] args) {
        System.out.println(calculateLargestRectangle(Arrays.asList(1, 4, 2, 5, 6, 3, 2, 6, 6, 5, 2, 1, 3)));
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
