package org.psk.practice.ps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/**
 * <pre>Given a set of line segments, where each segment consists of a closed interval of the X-axis, a color, and a
 * height. When viewed from above, the color at point x on the X-axis is the color of the highest segment that includes x.
 * Write a program that takes lines segments as input, and outputs the view from above for these segments. You can
 * assume no two segments whose intervals overlap have the same height.</pre>
 */
public class JoinLineSegments {

    /**
     * <pre>Key observation is that, viewed from above, the color can change at
     * most at an endpoint. This discretizes the problem—we only need to consider the
     * endpoints, of which there are at most twice as many as line segments.
     * A brute-force algorithm then is to take these endpoints, and for each endpoint,
     * find the highest line segment containing it. To express the result as line segments, we
     * need to sort endpoints
     * Finding the highest line segment containing an endpoint has time complexity 0(n),
     * where n is the number of line segments, leading to an 0(n2) overall time bound.
     * We can improve the time complexity by recognizing that it is grossly inefficient
     * to test each endpoint against all line segments. Instead, we should track just the line
     * segments that contain the endpoint being processed, and to get the color efficiently
     * we should keep these line segments sorted by height
     * Specifically, we scan endpoints, maintaining the set of line segments that intersect
     * the current endpoint. This set is stored in a BST with the height being the key. The
     * color is determined by the highest line segment. When we encounter a left endpoint,
     * we add the corresponding line segment in a BST.When we encounter a right endpoint,
     * we remove the corresponding line segment from the BST.
     * As a concrete example, consider Figure 25.6 on the previous page. When we are
     * processing J's left endpoint, the BST consists of 77 and 7, with 77 appearing first (since
     * it is lower than 7). We add / to the BST, in between H and 7. The next endpoint
     * processed is 7's right endpoint, at which stage we remove 7 from the BST. Now / is
     * the highest line segment. From J's left endpoint to 7's right endpoint, the view from
     * above will be 7.</pre>
     */

    public static class LineSegment {

        public int left, right; // Specifies the interval.
        public int color;
        public int height;

        public LineSegment(int left, int right, int color, int height) {
            this.left = left;
            this.right = right;
            this.color = color;
            this.height = height;
        }
    }

    public static class Endpoint implements Comparable<Endpoint> {

        private final boolean isLeft;
        private final LineSegment line;

        public Endpoint(boolean isLeft, LineSegment line) {
            this.isLeft = isLeft;
            this.line = line;
        }

        public int value() {
            return isLeft ? line.left : line.right;
        }

        @Override
        public int compareTo(Endpoint o) {
            return Integer.compare(value(), o.value());
        }
    }

    public static List<LineSegment> calculateViewFromAbove(List<LineSegment> A) {
        List<Endpoint> sortedEndpoints = new ArrayList<>();
        for (LineSegment a : A) {
            sortedEndpoints.add(new Endpoint(true, a));
            sortedEndpoints.add(new Endpoint(false, a));
        }
        Collections.sort(sortedEndpoints);
        List<LineSegment> result = new ArrayList<>();
        int prevXAxis = sortedEndpoints.get(0).value(); // Leftmost end point.
        LineSegment prev = null;
        TreeMap<Integer, LineSegment> activeLineSegments = new TreeMap<>();
        for (Endpoint endpoint : sortedEndpoints) {
            if (!activeLineSegments.isEmpty() && prevXAxis != endpoint.value()) {
                if (prev == null) { // Found first segment.
                    prev = new LineSegment(prevXAxis, endpoint.value(), activeLineSegments.lastEntry().getValue().color,
                                           activeLineSegments.lastEntry().getValue().height);
                } else {
                    if (prev.height == activeLineSegments.lastEntry().getValue().height
                        && prev.color == activeLineSegments.lastEntry().getValue().color
                        && prevXAxis == prev.right) {
                        prev.right = endpoint.value();
                    } else {
                        result.add(prev);
                        prev = new LineSegment(prevXAxis, endpoint.value(),
                                               activeLineSegments.lastEntry().getValue().color,
                                               activeLineSegments.lastEntry().getValue().height);
                    }
                }
            }
            prevXAxis = endpoint.value();
            if (endpoint.isLeft) { // Left end point.
                activeLineSegments.put(endpoint.line.height, endpoint.line);
            } else { // Right end point.
                activeLineSegments.remove(endpoint.line.height);
            }
        }
        // Output the remaining segment (if any).
        if (prev != null) {
            result.add(prev);
        }
        return result;
    }
}
