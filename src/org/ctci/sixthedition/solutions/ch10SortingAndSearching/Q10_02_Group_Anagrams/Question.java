package org.ctci.sixthedition.solutions.ch10SortingAndSearching.Q10_02_Group_Anagrams;

import java.util.Arrays;

import org.ctci.sixthedition.solutions.CtCILibrary.AssortedMethods;

public class Question {
	public static void main(String[] args) {
		String[] array = {"apple", "banana", "carrot", "ele", "duck", "papel", "tarroc", "cudk", "eel", "lee"};
		System.out.println(AssortedMethods.stringArrayToString(array));
		Arrays.sort(array, new AnagramComparator());
		System.out.println(AssortedMethods.stringArrayToString(array));
	}
}
