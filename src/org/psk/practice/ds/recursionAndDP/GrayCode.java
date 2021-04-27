package org.psk.practice.ds.recursionAndDP;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <pre>An n-bit Gray code is a permutation of {0,1, 2,...,2" - 1) such that the binary representations of successive
 * integers in the sequence differ in only one place. (This is with wraparound, i.e., the last and first elements must
 * also differ in only one place.) For example, both <(000)2, (100)2,(101)2, (111)2,(110)2, (010)2,(011)2, (001)2> = (0,
 * 4, 5, 7,6, 2,3, 1) and (0, 1,3, 2,6, 7, 5, 4) are Gray codes for n = 3.
 * Write a program which takes n as input and returns an n-bit Gray code.</pre>
 */
public class GrayCode {

    public static List<Integer> grayCodeRecursive(int numBits) {
        List<Integer> result = new ArrayList<>(Collections.singletonList(0));
        directedGrayCode(numBits, new HashSet<>(Collections.singletonList(0)), result);
        return result;
    }

    private static boolean directedGrayCode(int numBits, Set<Integer> history, List<Integer> result) {
        if (result.size() == (1 << numBits)) {
            return differsByOneBit(result.get(0), result.get(result.size() - 1));
        }
        for (int i = 0; i < numBits; ++i) {
            int previousCode = result.get(result.size() - 1);
            int candidateNextCode = previousCode ^ (1 << i);
            if (!history.contains(candidateNextCode)) {
                history.add(candidateNextCode);
                result.add(candidateNextCode);
                if (directedGrayCode(numBits, history, result)) {
                    return true;
                }
                history.remove(candidateNextCode);
                result.remove(result.size() - 1);
            }
        }
        return false;
    }

    private static boolean differsByOneBit(int x, int y) {
        int bitDifference = x ^ y;
        return bitDifference != 0 && (bitDifference & (bitDifference - 1)) == 0;
    }

    /**
     * Assuming we operate on integers that fit within the size of the integer word, the time
     * complexity T(n) satisfies T(n) = T(n-1) + 0( pow(2, n-1)). the time complexity is 0(pow(2, n)).
     */
    public static List<Integer> grayCode(int numBits) {
        if (numBits == 0) {
            return new ArrayList<>(Collections.singletonList(0));
        }
        // These implicitly begin with 0 at bit-index (numBits - 1).
        List<Integer> grayCodeNumBitsMinus1 = grayCode(numBits - 1);
        // Now, add a 1 at bit-index (numBits - 1) to all entries in grayCodeNumBitsMinus1.
        int leadingBitOne = 1 << (numBits - 1);
        // Process in reverse order to achieve reflection of grayCodeNumBitsMinus1.
        for (int i = grayCodeNumBitsMinus1.size() - 1; i >= 0; --i) {
            grayCodeNumBitsMinus1.add(leadingBitOne | grayCodeNumBitsMinus1.get(i));
        }
        return grayCodeNumBitsMinus1;
    }
}
