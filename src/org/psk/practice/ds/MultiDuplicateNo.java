package org.psk.practice.ds;

public class MultiDuplicateNo {

    public static void main(String[] args) {
        final int[] numbers = new int[]{2, 3, 1, 0, 2, 5, 3};
        try {
            final int duplicateNo = duplicate(numbers);
            System.out.println("Duplicate number = " + duplicateNo);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static int duplicate(int numbers[]) {
        int length = numbers.length;
        for (int i = 0; i < length; ++i) {
            if (numbers[i] < 0 || numbers[i] > length - 1) {
                throw new IllegalArgumentException("Invalid numbers.");
            }
        }
        for (int i = 0; i < length; ++i) {
            while (numbers[i] != i) {
                if (numbers[i] == numbers[numbers[i]]) {
                    return numbers[i];
                }

                // swap numbers[i] and numbers[numbers[i]]
                int temp = numbers[i];
                numbers[i] = numbers[temp];
                numbers[temp] = temp;
            }
        }
        throw new IllegalArgumentException("No duplications.");
    }
}