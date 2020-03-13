package org.psk.practice.ps;


public class RodCut {

	public static void main(String[] args) {
		int p[] = { 1, 5, 8, 9, 10, 17, 17, 20, 24, 30 };
		int n = 4;

		long millis1 = System.nanoTime();
		System.out.println(rodRecursiveCut(p, n));
		long millis2 = System.nanoTime();

		System.out.println(rodBottomUpCut(p, n));
		long millis3 = System.nanoTime();

		System.out.println("..." + (millis3 - millis2) + "...." + (millis2 - millis1));
	}

	private static int rodBottomUpCut(int[] p, int n) {
		int[] r = new int[n + 1];
		r[0] = 0;
		for (int i = 1; i <= n; i++) {
			int q = -1;
			for (int j = 0; j < i; j++) {
				q = Math.max(q, p[j] + r[i - j - 1]);
			}
			r[i] = q;
		}
		return r[n];
	}

	private static int rodRecursiveCut(int[] p, int n) {
		if (n == 0) {
			return 0;
		}

		int q = -1;
		for (int i = 0; i < n; i++) {
			q = Math.max(q, p[i] + rodRecursiveCut(p, n - i - 1));
		}
		return q;
	}
}