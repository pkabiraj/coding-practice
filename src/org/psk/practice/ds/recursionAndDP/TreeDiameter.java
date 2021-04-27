package org.psk.practice.ds.recursionAndDP;

import java.util.ArrayList;
import java.util.List;

public class TreeDiameter {

    public static class TreeNode {

        List<Edge> edges = new ArrayList<>();
    }

    private static class Edge {

        public TreeNode root;
        public Double length;

        public Edge(TreeNode root, Double length) {
            this.root = root;
            this.length = length;
        }
    }

    private static class HeightAndDiameter {

        public Double height;
        public Double diameter;

        public HeightAndDiameter(Double height, Double diameter) {
            this.height = height;
            this.diameter = diameter;
        }
    }

    public static double computeDiameter(TreeNode T) {
        return T != null ? computeHeightAndDiameter(T).diameter : 0.0;
    }

    private static HeightAndDiameter computeHeightAndDiameter(TreeNode r) {
        double diameter = Double.MIN_VALUE;
        double[] heights = {0.0, 0.0}; // Stores the max two heights.
        for (Edge e : r.edges) {
            HeightAndDiameter heightDiameter = computeHeightAndDiameter(e.root);
            if (heightDiameter.height + e.length > heights[0]) {
                heights[1] = heights[0];
                heights[0] = heightDiameter.height + e.length;
            } else if (heightDiameter.height + e.length > heights[1]) {
                heights[1] = heightDiameter.height + e.length;
            }
            diameter = Math.max(diameter, heightDiameter.diameter);
        }
        return new HeightAndDiameter(heights[0], Math.max(diameter, heights[0] + heights[1]));
    }
}
