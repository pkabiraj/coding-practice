package org.psk.practice.ds.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Given a list of packages that need to be built and the dependencies for each package, determine a valid order in
 * which to build the packages.
 *
 * <pre>eg.
 *
 * 0:
 * 1: 0
 * 2: 0
 * 3: 1, 2
 * 4: 3
 *
 * output: 0, 1, 2, 3, 4
 * </pre>
 */
public class BuildOrderTopoSort {

    public static void main(String[] args) {
        final BuildOrderTopoSort solution = new BuildOrderTopoSort();

        final int[][] processes = {
                {},
                {0},
                {0},
                {1, 2},
                {3}
        };
        System.out.println(solution.buildOrder(processes));
        System.out.println("------------------------------");
        System.out.println(solution.buildOrderKahnBFS(processes));
    }

    private List<Integer> buildOrderKahnBFS(final int[][] processes) {
        int numProcesses = processes.length;
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        int[] inDegree = new int[numProcesses];
        List<Integer> topologicalOrder = new ArrayList<>();

        // Build adjacency list
        for (int src = 0; src < processes.length; src++) {
            int[] dests = processes[src];
            inDegree[src]+=dests.length;

            for (int dest : dests) {
                final List<Integer> lst = adjList.getOrDefault(dest, new ArrayList<>());
                lst.add(src);
                adjList.put(dest, lst);
            }
        }

        // Add all nodes with in-degree 0 to queue
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numProcesses; i++) {
            if (inDegree[i] == 0) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            Integer node = q.poll();
            topologicalOrder.add(node);

            final List<Integer> neighbors = adjList.getOrDefault(node, Collections.emptyList());
            for (Integer neighbor : neighbors) {
                // Reduce in-degree of neighbor and if it becomes 0, add to queue
                inDegree[neighbor]--;

                if (inDegree[neighbor] == 0) {
                    q.add(neighbor);
                }
            }
        }

        if (topologicalOrder.size() == numProcesses) {
            return topologicalOrder;
        }

        return Collections.emptyList();
    }

    // Perform topological sort
    // Input is a list of dependencies where the index is the process number
    // and the value is the numbers the processes it depends on
    public List<Integer> buildOrder(int[][] processes) {
        Set<Integer> temporaryMarks = new HashSet<>();
        Set<Integer> permanentMarks = new HashSet<>();
        List<Integer> result = new LinkedList<>();

        // Recursively search from any unmarked node
        for (int i = 0; i < processes.length; i++) {
            if (!permanentMarks.contains(i)) {
                visit(i, processes, temporaryMarks, permanentMarks, result);
            }
        }

        return result;
    }

    // Search through all unmarked nodes accessible from process
    public void visit(int process,
                             int[][] processes,
                             Set<Integer> temporaryMarks,
                             Set<Integer> permanentMarks,
                             List<Integer> result) {
        // Throw an error if we find a cycle
        if (temporaryMarks.contains(process)) {
            throw new RuntimeException("Graph is not acyclic");
        }

        // If we haven't visited the node, recursively search from there
        if (!permanentMarks.contains(process)) {
            temporaryMarks.add(process);

            // Perform recursive search from children
            for (int i : processes[process]) {
                visit(i, processes, temporaryMarks, permanentMarks, result);
            }

            // Add permanent mark, remove temporary mark, and add to results list
            permanentMarks.add(process);
            temporaryMarks.remove(process);
            result.add(process);
        }
    }
}
