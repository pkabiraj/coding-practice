package org.psk.practice.ps;

public class PrimeNo {

	public static void main(String[] args) {
		long no = 9999;

		isPrime(no);

		printPrimeNos(no);
	}

	private static void isPrime(long no) {
		for (long i = 2; i * i <= no; i++) {
			if (no % i == 0) {
				System.out.println(no + " is NOT a prime no. Divisible by " + i);
				return;
			}
		}

		System.out.println(no + " is a PRIME no.");
	}

	private static void printPrimeNos(long max) {
		System.out.println("Prime nos upto " + max + " are mentioned below -");

		long millis = System.currentTimeMillis();
		boolean prime;
		for (long i = 2; i <= max; i++) {
			prime = true;
			for (long j = 2; j * j <= i; j++) {
				if (i % j == 0) {
					prime = false;
					break;
				}
			}

			if (prime) {
				System.out.println(i);
			}
		}

		System.out.println("Time taken : " + (System.currentTimeMillis() - millis));
	}
}
