package org.psk.practice.ps;

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
	}
}
