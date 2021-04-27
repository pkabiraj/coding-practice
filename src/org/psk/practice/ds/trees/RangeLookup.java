package org.psk.practice.ds.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>Consider the problem of developing a web-service that takes a geographical location, and returns the nearest
 * restaurant. The service starts with a set of restaurant
 * locations—each location includes X and Y-coordinates. A query consists of a location,
 * and should return the nearest restaurant (ties can be broken arbitrarily).
 * One approach is to build two BSTs on the restaurant locations: Tx sorted on
 * the X coordinates, and Ty sorted on the Y coordinates. A query on location (p,q)
 * can be performed by finding all the restaurants whose X coordinate is in the interval
 * [p— D,p+D],and all the restaurants whose Y coordinate is in the interval [q-D,q+D],
 * taking the intersection of these two sets, and finding the restaurant in the intersection
 * which is closest to (p,q). Heuristically, if D is chosen correctly, the subsets are small
 * and a brute-force search for the closest point is fast. One approach is to start with a
 * small value for D and keep doubling it until the final intersection is nonempty.
 * There are other data structures which are more robust, e.g., Quadtrees and k-d
 * trees, but the approach outlined above works well in practice.
 * Write a program that takes as input a BST and an interval and returns the BST keys
 * that lie in the interval.</pre>
 */
public class RangeLookup {

    public static class BSTNode<T> {

        public T data;
        public BSTNode<T> left, right;
    }

    private static class Interval {

        public int left, right;

        public Interval(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    public static List<Integer> rangeLookupInBST(BSTNode<Integer> tree, Interval interval) {
        List<Integer> result = new ArrayList<>();
        rangeLookupInBSTHelper(tree, interval, result);
        return result;
    }

    public static void rangeLookupInBSTHelper(BSTNode<Integer> tree, Interval interval, List<Integer> result) {
        if (tree == null) {
            return;
        }
        if (interval.left <= tree.data && tree.data <= interval.right) {
            // tree.data lies in the interval.
            rangeLookupInBSTHelper(tree.left, interval, result);
            result.add(tree.data);
            rangeLookupInBSTHelper(tree.right, interval, result);
        } else if (interval.left > tree.data) {
            rangeLookupInBSTHelper(tree.right, interval, result);
        } else { // interval.right >= tree.data
            rangeLookupInBSTHelper(tree.left, interval, result);
        }
    }
}
