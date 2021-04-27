package org.psk.practice.ds.arrays;

import java.util.BitSet;

public class FindMissingIP {

    /**
     * <pre>Solution: Since the file can be treated as consisting of 32-bit integers, we can sort the
     * input file and then iterate through it, searching for a gap between values. The time
     * complexity is0(n log n),where n is number of entries. Furthermore, to keep theRAM
     * usage low, the sort will have to use disk as storage, which in practice is very slow.
     * Note that we cannot just compute the largest entry and add one to it, since if the
     * largest entry is 255.255.255.255 (the highest possible IP address), adding one to it
     * leads to overflow. The same holds for the smallest entry. (In practice this would be a
     * good heuristic.)
     * Wecould add all the IP addresses in the file to a hash table, and then enumerate IP
     * addresses, starting with 0.0.0.0, until we find one not in the hash table. However, a
     * hash table requires considerable overhead—of the order of 10 bytes for each integer,
     * i.e., of the order of 10 gigabytes.
     * We can reduce the storage requirement by an order of magnitude by using a bit
     * array representation for the set of all possible IP addresses. Specifically, we allocate
     * an array of 232 bits, initialized to 0, and write a1at each index that corresponds to an
     * IP address in the file. Then we iterate through the bit array, looking for an entry set
     * to 0. There are 232 * 4 X 109 possible IP addresses, so not all IP addresses appear in
     * the file. The storage is 232/8 bytes, is half a gigabyte. This is still well in excess of the
     * storage limit.
     * Since the input is in a file, we can make multiple passes through it. We can use
     * this to narrow the search down to subsets of the space of all IP addresses as follows.
     * We make a pass through the file to count the number of IP addresses present whose
     * leading bit is a 1, and the number of IP addresses whose leading bit is a 0. At least
     * one IP address must exist which is not present in the file, so at least one of these two
     * counts is below 231. For example, suppose we have determined using counting that
     * there must be an IP address which begins with 0 and is absent from the file. We can
     * focus our attention on IP addresses in the file that begin with 0, and continue the
     * process of elimination based on the second bit. This entails 32 passes, and uses only
     * two integer-valued count variables as storage.
     * Since we have more storage, we can count on groups of bits. Specifically, we can
     * count the number of IP addresses in the file that begin with 0,1, 2, . . . , 216 -1 using
     * an array of 216 32-bit integers. For every IP address in the file, we take its 16 MSBs to
     * index into this array and increment the count of that number. Since the file contains
     * fewer than 232 numbers, there must be one entry in the array that is less than 216. This
     * tells us that there is at least one IP address which has those upper bits and is not in
     * the file. In the second pass, we can focus only on the addresses whose leading 16
     * bits match the one we have found, and use a bit array of size 216 to identify a missing
     * address.</pre>
     */
    private static final int NUM_BUCKET = 1 << 16;

    public static int findMissingElement(Iterable<Integer> sequence) {
        int[] counter = new int[NUM_BUCKET];
        // Search from the beginning again.
        for (final Integer integer : sequence) {
            int idx = integer >>> 16;
            ++counter[idx];
        }
        for (int i = 0; i < counter.length; ++i) {
            // Look for a bucket that contains less than NUM_BUCKET elements.
            if (counter[i] < NUM_BUCKET) {
                BitSet bitVec = new BitSet(NUM_BUCKET);
                // Search from the beginning again.
                for (final int x : sequence) {
                    if (i == (x >>> 16)) {
                        bitVec.set(((NUM_BUCKET) - 1) & x); // Gets the lower 16 bits of x.
                    }
                }
                for (int j = 0; j < (1 << 16); ++j) {
                    if (!bitVec.get(j)) {
                        return (i << 16) | j;
                    }
                }
            }
        }
        return -1; // Will not reach here
    }
}
