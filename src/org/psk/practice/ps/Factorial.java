package org.psk.practice.ps;

import java.math.BigInteger;

/**
 * @author pkabiraj
 *
 */
public class Factorial {
	public static void main(String[] args) {
		int value = 5;
		BigInteger factorial = new Factorial().findFactorial(value);

		System.out.println("Factorial of " + value + " = " + factorial);
	}

	private BigInteger findFactorial(int value) {
		if (value < 0) {
			System.out.println("Value has to be greater than zero");
			return BigInteger.valueOf(-1);
		} else if (value == 0 || value == 1) {
			return BigInteger.ONE;
		}

		BigInteger factorial = BigInteger.valueOf(value);

		for (int i = 2; i < value; i++) {
			factorial = factorial.multiply(BigInteger.valueOf(i));
		}

		return factorial;
	}
}
