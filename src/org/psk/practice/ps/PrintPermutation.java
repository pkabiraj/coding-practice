package org.psk.practice.ps;

/**
 * The Class PrintPermutation to print all the permutations of the input string. May not be the best solution but it
 * works fine.
 */
public class PrintPermutation {

	public static void main(String[] args) {
		String input = "abcd";

		permutate(input.toCharArray(), 0);
	}

	private static void permutate(char[] str, int index) {
		if (index == str.length) { // Got new permutation, print it
			System.out.println(str);
			return;
		}

		for (int i = index; i < str.length; i++) {
			str = swap(str, index, i); // Swap characters to get new permutation

			permutate(str, index + 1); // Recursive call

			str = swap(str, index, i); // Go back to previous version of string
		}
	}

	private static char[] swap(char[] str, int index, int i) {
		char temp = str[index];
		str[index] = str[i];
		str[i] = temp;

		return str;
	}
}