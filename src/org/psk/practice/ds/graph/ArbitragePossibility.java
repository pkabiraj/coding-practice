package org.psk.practice.ds.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <pre>You are exploring the remote valleys of Papua New Guinea, one of the last uncharted places in the world. You come
 * across a tribe that does not have money—instead it relies on the barter system. A total of n commodities are traded
 * and the exchange rates are specified by a 2D array. For example, three sheep can be exchanged for seven goats and
 * four goats can be exchanged for 200 pounds of wheat. Transaction costs are zero, exchange rates do not fluctuate,
 * fractional quantities of items can be sold, and the exchange rate between each pair of commodities is finite.
 * Design an efficient algorithm to determine whether there exists an arbitrage—a way to start with a single unit of
 * some commodity C and convert it back to more than one unit of C through a sequence of exchanges.</pre>
 */
public class ArbitragePossibility {

    /**
     * <pre>We define a weighted directed graph G = (V, E = V X V), where V corresponds to the set of commodities. The
     * weight w(e) of edge e = («, v) is the amount of commodity v we can buy with one unit of commodity u. Observe
     * that an arbitrage exists if and only if there exists a cycle in G whose edge weights multiply out to >1.
     * Create a new graph G' = (V, E) with weight function w'(e) = -lg w(e). Since
     * lg(a X b) = lga + lg b, there exists a cycle in G whose edge weights multiply out to
     * more than 1 if and only if there exists a cycle in G' whose edge weights sum up to
     * less than lg1 = 0. (This property is true for logarithms to any base, so if it is more
     * efficient for example to use base-e, we can do so.)
     * The Bellman-Ford algorithm detects negative-weight cycles. Usually, finding a
     * negative-weight cycle is done by adding a dummy vertex s with 0-weight edges to
     * each vertex in the given graph and running the Bellman-Ford single-source shortest
     * path algorithm from s. However, for the arbitrage problem, the graph is complete.
     * Hence, we can run Bellman-Ford algorithm from any single vertex, and get the right
     * result.</pre>
     */
    public static boolean isArbitrageExist(List<List<Double>> G) {
        // Transforms each edge in G.
        for (List<Double> edgeList : G) {
            for (int i = 0; i < edgeList.size(); i++) {
                edgeList.set(i, -Math.log10(edgeList.get(i)));
            }
        }
        // Uses Bellman-Ford to find negative weight cycle.
        return bellmanFord(G, 0);
    }

    private static boolean bellmanFord(List<List<Double>> G, int source) {
        List<Double> disToSource = new ArrayList<>(Collections.nCopies(G.size(), Double.MAX_VALUE));
        disToSource.set(source, 0.0);
        for (int times = 1; times < G.size(); ++times) {
            boolean haveUpdate = false;
            for (int i = 0; i < G.size(); ++i) {
                for (int j = 0; j < G.get(i).size(); ++j) {
                    if (disToSource.get(i) != Double.MAX_VALUE && disToSource.get(j) > disToSource.get(i) + G.get(i)
                            .get(j)) {
                        haveUpdate = true;
                        disToSource.set(j, disToSource.get(i) + G.get(i).get(j));
                    }
                }
            }
            // No update in this iteration means no negative cycle.
            if (!haveUpdate) {
                return false;
            }
        }
        // Detects cycle if there is any further update.
        for (int i = 0; i < G.size(); ++i) {
            for (int j = 0; j < G.get(i).size(); ++j) {
                if (disToSource.get(i) != Double.MAX_VALUE
                    && disToSource.get(i) > disToSource.get(i) + G.get(i).get(j)) {
                    return true;
                }
            }
        }
        return false;
    }
}
