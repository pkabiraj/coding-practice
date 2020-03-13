package org.psk.practice.ps;

public class DiagonalSwap {

	public static void main(String[] args) {
		int[][] arr = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
		// int[][] arr = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };

		print(arr);

		doSwap(arr);

		print(arr);
	}

	private static void doSwap(int[][] arr) {
		int len = arr.length - 1;

		for (int row = 0; row <= len; row++) {
			int col = len - row;

			for (int j = 1; j <= col; j++) {
				int temp = arr[row][col - j];
				arr[row][col - j] = arr[row + j][col];
				arr[row + j][col] = temp;
			}
		}
	}

	private static void print(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				System.out.print("\t" + arr[i][j]);
			}
			System.out.println();
		}

		System.out.println("............................................................");
	}
}
