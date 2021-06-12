package org.psk.practice.ds.recursionAndDP;

/**
 * Given a 2D boolean array, find the largest square subarray of true values. The return value should be the side length
 * of the largest square subarray subarray.
 */
public class SquareSubMatrixDP {

    // Brute force solution. From each cell find the biggest square submatrix for which it is the upper left-hand corner
    public int squareSubmatrixBF(boolean[][] arr) {
        int max = 0;
        // Compute for each cell the biggest subarray
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j]) {
                    max =
                            Math.max(max, squareSubmatrix(arr, i, j));
                }
            }
        }
        return max;
    }

    // Overloaded recursive function
    private int squareSubmatrix(boolean[][] arr, int i, int j) {
        // Base case at bottom or right of the matrix
        if (i == arr.length || j == arr[0].length) {
            return 0;
        }
        // If the cell is false then it’s not part of a valid submatrix
        if (!arr[i][j]) {
            return 0;
        }
        // Find the size of the right, bottom, and bottom right submatrices and add 1 to the minimum of those 3 to
        // get the result
        return 1 + Math.min(
                Math.min(squareSubmatrix(arr, i + 1, j), squareSubmatrix(arr, i, j + 1)),
                squareSubmatrix(arr, i + 1, j + 1));
    }

    // Top down dynamic programming solution. Cache the values to avoid repeating computations
    public int squareSubmatrixMemo(boolean[][] arr) {
        // Initialize cache. Don't need to initialize to -1 because the only cells that will be 0 are ones that are
        // false and we want to skip those ones anyway
        int[][] cache = new int[arr.length][arr[0].length];
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j]) {
                    max = Math.max(max, squareSubmatrix(arr, i, j, cache));
                }
            }
        }
        return max;
    }

    // Overloaded recursive function
    private int squareSubmatrix(boolean[][] arr, int i, int j, int[][] cache) {
        if (i == arr.length || j == arr[0].length) {
            return 0;
        }
        if (!arr[i][j]) {
            return 0;
        }
        // If the value is set in the cache return
        // it. Otherwise compute and save to cache
        if (cache[i][j] > 0) {
            return cache[i][j];
        }
        cache[i][j] = 1 + Math.min(
                Math.min(squareSubmatrix(arr, i + 1, j, cache), squareSubmatrix(arr, i, j + 1, cache)),
                squareSubmatrix(arr, i + 1, j + 1, cache));
        return cache[i][j];
    }

    // Bottom up solution. Start from the upper left corner and compute each larger submatrix
    public int squareSubmatrixDP(boolean[][] arr) {
        int max = 0;
        // Initialize cache
        int[][] cache = new int[arr.length][arr[0].length];
        // Iterate over matrix to compute each value
        for (int i = 0; i < cache.length; i++) {
            for (int j = 0; j < cache[0].length; j++) {
                // If we’re in the first row/column then the value is just 1 if that cell is true and 0 otherwise. In
                // other rows and columns need to look up and to the left
                if (i == 0 || j == 0) {
                    cache[i][j] = arr[i][j] ? 1 : 0;
                } else if (arr[i][j]) {
                    cache[i][j] = Math.min(Math.min(cache[i][j - 1], cache[i - 1][j]), cache[i - 1][j - 1]) + 1;
                }
                if (cache[i][j] > max) {
                    max = cache[i][j];
                }
            }
        }
        return max;
    }
}
