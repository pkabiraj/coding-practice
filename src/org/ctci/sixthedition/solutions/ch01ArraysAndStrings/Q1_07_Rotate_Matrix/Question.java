package org.ctci.sixthedition.solutions.ch01ArraysAndStrings.Q1_07_Rotate_Matrix;

import org.ctci.sixthedition.solutions.CtCILibrary.AssortedMethods;

public class Question {

	public static void rotate(int[][] matrix) {
		if (matrix.length == 0 || matrix.length != matrix[0].length) return; // Not a square
		int n = matrix.length;
		
		for (int layer = 0; layer < n / 2; layer++) {
			int last = n - 1 - layer;
			for(int i = layer; i < last; i++) {
				int offset = i - layer;
				int top = matrix[layer][i]; // save top

				// left -> top
				matrix[layer][i] = matrix[last - offset][layer];

				// bottom -> left
				matrix[last-offset][layer] = matrix[last][last - offset];

				// right -> bottom
				matrix[last][last - offset] = matrix[i][last]; 

				// top -> right
				matrix[i][last] = top; // right <- saved top
			}
		}
	}
	
	public static void main(String[] args) {
		int[][] matrix = AssortedMethods.randomMatrix(3, 3, 0, 9);
		AssortedMethods.printMatrix(matrix);
		rotate(matrix);
		System.out.println();
		AssortedMethods.printMatrix(matrix);
	}

}
