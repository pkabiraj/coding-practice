package org.psk.practice.ps;

public class PrintCombination {

	public static void main(String[] args) {
		String input = "abcd";

		doCombine(input.toCharArray(), new StringBuilder(), input.length(), 0);
	}

	private static void doCombine(char[] instr, StringBuilder outstr, int length, int start) {
		for (int i = start; i < length; i++) {
			outstr.append(instr[i]);
			System.out.println(outstr);

			if (i < length - 1) {
				doCombine(instr, outstr, length, i + 1);
			}

			outstr.setLength(outstr.length() - 1);
		}
	}
}