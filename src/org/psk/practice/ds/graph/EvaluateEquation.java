package org.psk.practice.ds.graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class EvaluateEquation {

    public static void main(String[] args) {
        final List<List<String>> equations = Arrays.asList(
                Arrays.asList("a", "b"),
                Arrays.asList("b", "c")
        );
        final double[] values = {2.0, 3.0};
        final List<List<String>> queries = Arrays.asList(
                Arrays.asList("a", "c"),
                Arrays.asList("b", "a"),
                Arrays.asList("a", "e"),
                Arrays.asList("a", "a"),
                Arrays.asList("x", "x"),
                Arrays.asList("c", "b")
        );
        System.out.println(Arrays.toString(new EvaluateEquation().calcEquation(equations, values, queries)));
    }

    public double[] calcEquation(List<List<String>> equations,
                                 double[] values,
                                 List<List<String>> queries) {
        final Map<String, Map<String, Double>> graph = buildGraph(equations, values);

        return evaluate(queries, graph);
    }

    private Map<String, Map<String, Double>> buildGraph(final List<List<String>> equations,
                                                        final double[] values) {
        final Map<String, Map<String, Double>> graph = new HashMap<>();

        for (int i = 0; i < equations.size(); i++) {
            final List<String> equation = equations.get(i);
            final String dividend = equation.get(0);
            final String divisor = equation.get(1);
            double quotient = values[i];

            if (!graph.containsKey(dividend)) {
                graph.put(dividend, new HashMap<>());
            }
            if (!graph.containsKey(divisor)) {
                graph.put(divisor, new HashMap<>());
            }

            graph.get(dividend).put(divisor, quotient);
            graph.get(divisor).put(dividend, 1.0 / quotient);
        }

        return graph;
    }

    private double[] evaluate(final List<List<String>> queries,
                              final Map<String, Map<String, Double>> graph) {
        final double[] results = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {
            final List<String> query = queries.get(i);
            final String dividend = query.get(0);
            final String divisor = query.get(1);

            if (!graph.containsKey(dividend) || !graph.containsKey(divisor)) {
                results[i] = -1.0;
            } else if (dividend.equals(divisor)) {
                results[i] = 1.0;
            } else {
                results[i] = dfs(graph, dividend, divisor, 1.0, new HashSet<>());
            }
        }

        return results;
    }

    private double dfs(final Map<String, Map<String, Double>> graph,
                       final String currNode,
                       final String targetNode,
                       final double result,
                       final Set<String> visited) {
        visited.add(currNode);
        double prod = -1.0;
        final Map<String, Double> neighbours = graph.get(currNode);

        if (neighbours.containsKey(targetNode)) {
            prod = result * neighbours.get(targetNode);
        } else {
            for (Map.Entry<String, Double> neighbour : neighbours.entrySet()) {
                final String nextNode = neighbour.getKey();
                if (visited.contains(nextNode)) {
                    continue;
                }

                prod = dfs(graph, nextNode, targetNode, result * neighbour.getValue(), visited);
                if (prod != -1.0) {
                    break;
                }
            }
        }

        return prod;
    }
}
