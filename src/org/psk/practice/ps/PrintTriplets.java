package org.psk.practice.ps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class PrintTriplets {

	private class Triplet implements Comparable<Triplet> {
		private int a;
		private int b;
		private int c;

		private Triplet(int a, int b, int c) {
			int[] arr = new int[] { a, b, c };
			Arrays.sort(arr);
			this.a = arr[0];
			this.b = arr[1];
			this.c = arr[2];
		}

		boolean isZero() {
			return a + b + c == 0;
		}

		@Override
		public String toString() {
			return a + " " + b + " " + c;
		}

		@Override
		public int compareTo(Triplet x) {
			if (a < x.a) {
				return -1;
			} else if (a > x.a) {
				return 1;
			}
			if (b < x.b) {
				return -1;
			} else if (b > x.b) {
				return 1;
			}
			if (c < x.c) {
				return -1;
			} else if (c > x.c) {
				return 1;
			}
			return 0;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			Triplet triplet = (Triplet) o;
			if (a != triplet.a) {
				return false;
			}
			if (b != triplet.b) {
				return false;
			}
			if (c != triplet.c) {
				return false;
			}
			return true;
		}

		@Override
		public int hashCode() {
			int result = a;
			result = 31 * result + b;
			result = 31 * result + c;
			return result;
		}
	}

	public Set<Triplet> findTriplets(int[] array) {
		int n = 0;
		for (int i = 0; i < array.length; i++) {
			n |= 1 << i;
		}
		Set<Triplet> triplets = new TreeSet<>();
		for (int i = 0; i <= n; i++) {
			List<Integer> list = new ArrayList<>();
			for (int j = 0; j < array.length; j++) {
				if ((i & 1 << j) != 0) {
					list.add(array[j]);
				}
				if (list.size() > 3) {
					continue;
				}
			}
			if (list.size() == 3) {
				Triplet t = new Triplet(list.get(0), list.get(1), list.get(2));
				if (!t.isZero()) {
					continue;
				}
				triplets.add(t);
			}
		}
		return triplets;
	}

	public static void main(String[] args) throws Exception {
		int[] arr = readInput();
		if (arr.length < 3) {
			System.out.println("None");
			return;
		}
		PrintTriplets sum = new PrintTriplets();
		Set<Triplet> result = sum.findTriplets(arr);
		if (result.isEmpty()) {
			System.out.println("None");
			return;
		}
		for (Triplet t : result) {
			System.out.println(t);
		}
	}

	private static int[] readInput() throws IOException {
		// BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		// String line = reader.readLine();
		String line = "-1 0 1 2 -1 -4";
		// line = "-1 2 -1″;
		line = line.trim();
		String[] tmp = line.split("\\s+");
		int[] result = new int[tmp.length];
		for (int i = 0; i < tmp.length; i++) {
			result[i] = Integer.parseInt(tmp[i]);
		}
		return result;
	}
}