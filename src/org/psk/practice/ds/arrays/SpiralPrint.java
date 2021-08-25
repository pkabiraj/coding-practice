package org.psk.practice.ds.arrays;

import java.util.ArrayList;
import java.util.List;

public class SpiralPrint {

    public static void main(String[] args) {
        int rowColCount = 5;
        int no = 1;
        int[][] spiralArr = new int[rowColCount][rowColCount];
        int rotation;

        // Find out the no of rotation
        if (rowColCount % 2 == 0) {
            rotation = rowColCount / 2;
        } else {
            rotation = rowColCount / 2 + 1;
        }

        // Put the numbers in the array at correct location
        int row = 0, col = -1;
        for (int i = 0; i < rotation; i++) {
            // Horizontal => Left -> Right
            while (col < rowColCount - i - 1) {
                spiralArr[row][++col] = no++;
            }

            // Vertical => Top -> Bottom
            while (row < rowColCount - i - 1) {
                spiralArr[++row][col] = no++;
            }

            // Horizontal => Right -> Left
            while (col > i) {
                spiralArr[row][--col] = no++;
            }

            // Vertical => Bottom -> Top
            while (row > i + 1) {
                spiralArr[--row][col] = no++;
            }
        }

        // Print the array to show spiral rotation...
        for (int i = 0; i < rowColCount; i++) {
            for (int j = 0; j < rowColCount; j++) {
                System.out.print("\t" + spiralArr[i][j]);
            }
            System.out.println();
        }

        System.out.println(new SpiralPrint().spiralOrder(new int[][] {
                {1, 2,  3,  4},
                {5, 6,  7,  8},
                {9, 10, 11, 12}}));
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        int rowCount = matrix.length;
        int colCount = matrix[0].length;
        int rotation;

        // Find out the no of rotation
        if (rowCount % 2 == 0) {
            rotation = rowCount / 2;
        } else {
            rotation = rowCount / 2 + 1;
        }

        List<Integer> spirals = new ArrayList<>();
        int row = 0, col = -1;
        for (int i = 0; i < rotation; i++) {
            // Horizontal => Left -> Right
            while (col < colCount - i - 1) {
                spirals.add(matrix[row][++col]);
            }

            // Vertical => Top -> Bottom
            while (row < rowCount - i - 1) {
                spirals.add(matrix[++row][col]);
            }

            // Horizontal => Right -> Left
            while (col > i) {
                spirals.add(matrix[row][--col]);
            }

            // Vertical => Bottom -> Top
            while (row > i + 1) {
                spirals.add(matrix[--row][col]);
            }
        }

        return spirals;
    }
}
