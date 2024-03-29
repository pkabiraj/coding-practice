package org.ctci.sixthedition.solutions.ch08RecursionAndDynamicProgramming.Q8_02_Robot_in_a_Grid;

import java.util.ArrayList;

import org.ctci.sixthedition.solutions.CtCILibrary.AssortedMethods;

public class Tester {
	public static void main(String[] args) {
		int size = 5;
		boolean[][] maze = AssortedMethods.randomBooleanMatrix(size, size, 70);
		
		AssortedMethods.printMatrix(maze);
		
		ArrayList<Point> pathA = QuestionA.getPath(maze);
		ArrayList<Point> pathB = QuestionB.getPath(maze);
		if (pathA != null) {
			System.out.println(pathA.toString());
		} else {
			System.out.println("No path found.");
		}
		
		if (pathB != null) {
			System.out.println(pathB.toString());
		} else {
			System.out.println("No path found.");
		}		
	}
}
