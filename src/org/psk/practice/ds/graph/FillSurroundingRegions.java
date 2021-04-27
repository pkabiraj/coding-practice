package org.psk.practice.ds.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Let A be a 2D array whose entries are either WorB. Write a program that takes A, and replaces all Ws that cannot
 * reach the boundary with a B.
 */
public class FillSurroundingRegions {

    public static void fillSurroundedRegions(List<List<Character>> board) {
        if (board.isEmpty()) {
            return;
        }
        List<List<Boolean>> visited = new ArrayList<>(board.size());
        for (List<Character> characters : board) {
            visited.add(new ArrayList(Collections.nCopies(characters.size(), false)));
        }
        // Identifies the regions that are reachable via white path starting from
        // the first or last columns.
        for (int i = 0; i < board.size(); ++i) {
            if (board.get(i).get(0) == 'W' && !visited.get(i).get(0)) {
                markBoundaryRegion(i, 0, board, visited);
            }
            if (board.get(i).get(board.get(i).size() - 1) == 'W' && !visited.get(i).get(board.get(i).size() - 1)) {
                markBoundaryRegion(i, board.get(i).size() - 1, board, visited);
            }
        }
        // Identifies the regions that are reachable via white path starting from the first or last rows.
        for (int j = 0; j < board.get(0).size(); ++j) {
            if (board.get(0).get(j) == 'W' && !visited.get(0).get(j)) {
                markBoundaryRegion(0, j, board, visited);
            }
            if (board.get(board.size() - 1).get(j) == 'W' && !visited.get(board.size() - 1).get(j)) {
                markBoundaryRegion(board.size() - 1, j, board, visited);
            }
        }
        // Marks the surrounded white regions as black.
        for (int i = 1; i < board.size() - 1; ++i) {
            for (int j = 1; j < board.get(i).size() - 1; ++j) {
                if (board.get(i).get(j) == 'W' && !visited.get(i).get(j)) {
                    board.get(i).set(j, 'B');
                }
            }
        }
    }

    private static class Coordinate {

        public Integer x;
        public Integer y;

        public Coordinate(Integer x, Integer y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void markBoundaryRegion(int i, int j,
                                           List<List<Character>> board,
                                           List<List<Boolean>> visited) {
        Queue<Coordinate> q = new LinkedList<>();
        q.add(new Coordinate(i, j));
        visited.get(i).set(j, true);
        // Uses BFS to traverse this region.
        while (!q.isEmpty()) {
            Coordinate curr = q.poll();
            final int DIRS[][] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            for (int[] dir : DIRS) {
                Coordinate next = new Coordinate(curr.x + dir[0], curr.y + dir[1]);
                if (next.x >= 0 && next.x < board.size() && next.y >= 0 && next.y < board.get(next.x).size()
                    && board.get(next.x).get(next.y) == 'W' && !visited.get(next.x).get(next.y)) {
                    visited.get(next.x).set(next.y, true);
                    q.add(next);
                }
            }
        }
    }
}
