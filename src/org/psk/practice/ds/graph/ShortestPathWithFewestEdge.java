package org.psk.practice.ds.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * <pre>In the usual formulation of the shortest path problem, the number of edges in the
 * path is not a consideration. For example, considering the shortest path problem from
 * a to h in Figure 19.1 on Page 350, the sum of the edge costs on the path {a,c,e,d,h) is
 * 22, which is the same as for path (a,b,k,i,j,f,g,h). Both are shortest paths, but the
 * latter has three more edges.
 * Heuristically, if we did want to avoid paths with a large number of edges, we can
 * add a small amount to the cost of each edge. However, depending on the structure
 * of the graph and the edge costs, this may not result in the shortest path.
 * Design an algorithm which takes as input a graph G = (V) E), directed or undirected,
 * a non-negative cost function on E, and vertices s and f; your algorithm should output
 * a path with the fewest edges amongst all shortest paths from s to f.</pre>
 */
public class ShortestPathWithFewestEdge {

    private static class VertexWithDistance {

        public GraphVertex vertex;
        public Integer distance;

        public VertexWithDistance(GraphVertex vertex, Integer distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }

    private static class DistanceWithFewestEdges {

        public Integer distance;
        public Integer minNumEdges;

        public DistanceWithFewestEdges(Integer distance, Integer minNumEdges) {
            this.distance = distance;
            this.minNumEdges = minNumEdges;
        }
    }

    public static class GraphVertex implements Comparable<GraphVertex> {

        public DistanceWithFewestEdges distanceWithFewestEdges = new DistanceWithFewestEdges(Integer.MAX_VALUE, 0);
        public List<VertexWithDistance> edges = new ArrayList<>();
        public int id; // The id of this vertex.
        public GraphVertex pred = null; // The predecessor in the shortest path.

        @Override
        public int compareTo(GraphVertex o) {
            if (!distanceWithFewestEdges.distance.equals(o.distanceWithFewestEdges.distance)) {
                return Integer.compare(distanceWithFewestEdges.distance, o.distanceWithFewestEdges.distance);
            }
            if (!distanceWithFewestEdges.minNumEdges.equals(o.distanceWithFewestEdges.minNumEdges)) {
                return Integer.compare(distanceWithFewestEdges.minNumEdges, o.distanceWithFewestEdges.minNumEdges);
            }
            return Integer.compare(id, o.id);
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof GraphVertex)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            GraphVertex that = (GraphVertex) obj;
            return id == that.id && distanceWithFewestEdges.distance.equals(that.distanceWithFewestEdges.distance)
                   && distanceWithFewestEdges.minNumEdges.equals(that.distanceWithFewestEdges.minNumEdges);
        }

        @Override
        public int hashCode() {
            return Objects.hash(distanceWithFewestEdges.distance, distanceWithFewestEdges.minNumEdges);
        }
    }

    public static void dijkstraShortestPath(GraphVertex s, GraphVertex t) {
        // Initialization of the distance of starting point.
        s.distanceWithFewestEdges = new DistanceWithFewestEdges(0, 0);
        SortedSet<GraphVertex> nodeSet = new TreeSet<>();
        nodeSet.add(s);
        while (!nodeSet.isEmpty()) {
            // Extracts the minimum distance vertex from heap.
            GraphVertex u = nodeSet.first();
            if (u.equals(t)) {
                break;
            }
            nodeSet.remove(nodeSet.first());
            // Relax neighboring vertices of u.
            for (VertexWithDistance v : u.edges) {
                int vDistance = u.distanceWithFewestEdges.distance + v.distance;
                int vNumEdges = u.distanceWithFewestEdges.minNumEdges + 1;
                if (v.vertex.distanceWithFewestEdges.distance > vDistance
                    || (v.vertex.distanceWithFewestEdges.distance == vDistance
                        && v.vertex.distanceWithFewestEdges.minNumEdges > vNumEdges)) {
                    nodeSet.remove(v.vertex);
                    v.vertex.pred = u;
                    v.vertex.distanceWithFewestEdges = new DistanceWithFewestEdges(vDistance, vNumEdges);
                    nodeSet.add(v.vertex);
                }
            }
            // Outputs the shortest path with fewest edges.
            outputShortestPath(t);
        }
    }

    private static void outputShortestPath(GraphVertex v) {
        if (v != null) {
            outputShortestPath(v.pred);
            System.out.print(v.id + " ");
        }
    }
}