package org.psk.practice.ps;

public class DecimalToBinaryConverter {

	public static void main(String[] args) {
		long decimalNo = 11;
		int pow = 0;
		long binaryNo = 0;

		long no = decimalNo;

		while (no > 0) {
			long remainder = no % 2;
			no = no / 2;

			binaryNo += remainder * Math.pow(10, pow++);
		}

		System.out.println("Decimal = " + decimalNo + "   Equivalent Binary No = " + binaryNo);
	}
}
