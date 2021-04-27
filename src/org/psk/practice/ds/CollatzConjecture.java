package org.psk.practice.ds;

import java.util.HashSet;
import java.util.Set;

/**
 * <pre>The Collatz conjecture is the following: Take any natural number. If it is odd, triple
 * it and add one; if it is even, halve it. Repeat the process indefinitely. No matter what
 * number you begin with, you will eventually arrive at 1.
 * As an example, if we start with 11 we get the sequence 11,34,17,52,26,13,40,
 * 20,10,5,16,8,4,2,1. Despite intense efforts, the Collatz conjecture has not been
 * proved or disproved.
 * Suppose you were given the task of checking the Collatz conjecture for the first
 * billion integers. A direct approach would be to compute the convergence sequence
 * for each number in this set.
 * Test the Collatz conjecture for the first n positive integers.</pre>
 */
public class CollatzConjecture {

    public static boolean testCollatzConjecture(int n) {
        // Stores odd numbers already tested to converge to 1.
        Set<Long> verifiedNumbers = new HashSet<>();
        // Starts from 3, since hypothesis holds trivially for 1 and 2.
        for (int i = 3; i <= n; i += 2) {
            Set<Long> sequence = new HashSet<>();
            long testI = i;
            while (testI >= i) {
                if (!sequence.add(testI)) {
                    // We previously encountered testI, so the Collatz sequence has fallen into a loop. This
                    // disproves the hypothesis, so we short-circuit, returning false.
                    return false;
                }
                if ((testI % 2) != 0) { // Odd number
                    if (!verifiedNumbers.add(testI)) {
                        break; // testI has already been verified to converge to 1.
                    }
                    long nextTestl = 3 * testI + 1; // Multiply by 3 and add 1.
                    if (nextTestl <= testI) {
                        throw new ArithmeticException("Collatz sequence overflow for " + i);
                    }
                    testI = nextTestl;
                } else {
                    testI /= 2; // Even number, halve it.
                }
            }
        }
        return true;
    }
}
