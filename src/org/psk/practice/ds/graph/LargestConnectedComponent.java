package org.psk.practice.ds.graph;

/**
 * Given a grid with different colors in a different cell, each color represented by a different number. The task is to
 * find out the largest connected component on the grid. Largest component grid refers to a maximum set of cells such
 * that you can move from any cell to any other cell in this set by only moving between side-adjacent cells from the
 * set.
 */
public class LargestConnectedComponent {

    private static final int n = 6;
    private static final int m = 8;

    // stores information about which cell are already visited in a particular BFS
    private static final int[][] visited = new int[n][m];

    // result stores the final result grid
    private static final int[][] result = new int[n][m];

    // stores the count of cells in the largest connected component
    private static int COUNT;

    public static void main(String[] args) {
        int[][] input = {{1, 4, 4, 4, 4, 3, 3, 1},
                         {2, 1, 1, 4, 3, 3, 1, 1},
                         {3, 2, 1, 1, 2, 3, 2, 1},
                         {3, 3, 2, 1, 2, 2, 2, 2},
                         {3, 1, 3, 1, 1, 4, 4, 4},
                         {1, 1, 3, 1, 1, 4, 4, 4}};

        // function to compute the largest connected component in the grid
        computeLargestConnectedGrid(input);
    }

    // function to calculate the largest connected component
    private static void computeLargestConnectedGrid(int[][] input) {
        int currentMax = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                currentMax = getCurrentMax(input, currentMax, i, j, j + 1, m, false);

                currentMax = getCurrentMax(input, currentMax, i, j, i + 1, n, true);
            }
        }
        printResult(currentMax);
    }

    private static int getCurrentMax(final int[][] input, int currentMax, final int i, final int j, final int index,
                                     final int max, final boolean row) {
        resetVisited();
        COUNT = 0;

        // checking cell to the right
        if (index < max) {
            if (row) {
                bfs(input[i][j], input[i + 1][j], i, j, input);
            } else {
                bfs(input[i][j], input[i][j + 1], i, j, input);
            }
        }

        // updating result
        if (COUNT >= currentMax) {
            currentMax = COUNT;
            resetResult(input[i][j], input);
        }
        return currentMax;
    }

    // called every time before a BFS so that visited array is reset to zero
    private static void resetVisited() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = 0;
            }
        }
    }

    // BFS to find all cells in connection with key = input[i][j]
    private static void bfs(int x, int y, int i, int j, int[][] input) {
        // terminating case for BFS
        if (x != y) {
            return;
        }

        visited[i][j] = 1;
        COUNT++;

        // xMove and yMove arrays are the possible movements in x or y direction
        int[] xMove = {0, 0, 1, -1};
        int[] yMove = {1, -1, 0, 0};

        // checks all four points connected with input[i][j]
        for (int u = 0; u < 4; u++) {
            if (isValid(i + yMove[u], j + xMove[u], x, input)) {
                bfs(x, y, i + yMove[u], j + xMove[u], input);
            }
        }
    }

    // Function checks if a cell is valid i.e it is inside the grid and equal to the key
    private static boolean isValid(int x, int y, int key, int[][] input) {
        if ((x < n && y < m) && (x >= 0 && y >= 0)) {
            return visited[x][y] == 0 && input[x][y] == key;
        }
        return false;
    }

    // If a larger connected component is found this function is called to store information about that component.
    private static void resetResult(int key, int[][] input) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] == 1 && input[i][j] == key) {
                    result[i][j] = visited[i][j];
                } else {
                    result[i][j] = 0;
                }
            }
        }
    }

    // function to print the result
    private static void printResult(int res) {
        System.out.println("The largest connected component of the grid is :" + res);

        // prints the largest component
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (result[i][j] != 0) {
                    System.out.print(result[i][j] + " ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
}
