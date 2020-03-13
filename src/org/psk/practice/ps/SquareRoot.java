package org.psk.practice.ps;

public class SquareRoot {

	public static void main(String[] args) {
		float no = 5;
		System.out.println("Square root of - " + no + " = " + sqrt(no));
	}

	private static float sqrt(float no) {
		float low = 0, high = no;
		float mid = (low + high) / 2;
		while (Math.abs(mid * mid - no) > 0.000001) {
			if (mid * mid < no) {
				low = mid;
			} else if (mid * mid > no) {
				high = mid;
			}
			mid = (low + high) / 2;
		}
		return mid;
	}
}
