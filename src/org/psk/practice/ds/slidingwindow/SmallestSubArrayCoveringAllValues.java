package org.psk.practice.ds.slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Write a program which takes an array of strings and a set of strings, and return the indices of the starting and
 * ending index of a shortest subarray of the given array that "covers" the set, i.e., contains all strings in the set.
 */
public class SmallestSubArrayCoveringAllValues {

    /**
     * <pre>The brute force approach is to iterate over all subarrays, testing if the subarray contains all strings in
     * the set. If the array length is n, there are 0(n2) subarrays. Testing whether the subarray contains each string in
     * the set is an 0(n) operation using a hash table to record which strings are present in the subarray. The overall
     * time complexity is 0(n3).
     * We can improve the time complexity to0(n2) by growing the subarrays incrementally. Specifically, we can
     * consider all subarrays starting at i in order of increasing length, stopping as soon as the
     * set is covered. We use a hash table to record which strings in the set remain to be covered. Each time we
     * increment the subarray length, we need 0(1) time to update the set of remaining strings.
     * We can further improve the algorithm by noting that when we move from i to i +1 we can reuse the work performed
     * from i. Specifically, let's say the smallest subarray starting at i covering the set ends at j. There is no
     * point in considering subarrays starting at i + 1and ending before j, since we know they cannot cover the set.
     * When we advance to i + 1, either we still cover the set, or we have to advance j to cover the set. We
     * continuously advance one of i or j, which implies an 0(n) time complexity.
     * As a concrete example, consider the array (apple, banana, apple, apple, dog, cat, apple, dog, banana, apple,
     * cat, dog) and the set {banana, cat}. The smallest subarray covering the set starting at 0 ends at 5. Next, we
     * advance to 1. Since the element at 0 is not in the set, the smallest subarray covering the set still ends at 5
     * . Next, we advance to 2. Now we do not cover the set, so we advance from 5 to 8—now the subarray from 2 to 8
     * covers the set. We update the start index from 2 to 3 to 4 to 5 and continue to cover the set. When we advance
     * to 6, we no longer cover the set, so we advance the end index till we get to 10. We can advance the start
     * index to 8 and still cover the set. After we move past 8, we cannot cover the set. The shortest subarray
     * covering the set is from 8 to 10.</pre>
     */

    // Represent subarray by starting and ending indices, inclusive .
    private static class Subarray {

        public Integer start;
        public Integer end;

        public Subarray(Integer start, Integer end) {
            this.start = start;
            this.end = end;
        }
    }

    public static Subarray findSmallestSubarrayCoveringSet(List<String> paragraph,
                                                           Set<String> keywords) {
        Map<String, Integer> keywordsToCover = new HashMap<>();
        for (String keyword : keywords) {
            keywordsToCover.put(keyword, keywordsToCover.containsKey(keyword)
                                         ? keywordsToCover.get(keyword) + 1
                                         : 1);
        }
        Subarray result = new Subarray(-1, -1);
        int remainingToCover = keywords.size();
        for (int left = 0, right = 0; right < paragraph.size(); ++right) {
            Integer keywordCount = keywordsToCover.get(paragraph.get(right));
            if (keywordCount != null) {
                keywordsToCover.put(paragraph.get(right), --keywordCount);
                if (keywordCount >= 0) {
                    --remainingToCover;
                }
            }
            // Keeps advancing left until it reaches end or keywordsToCover does not have all keywords.
            while (remainingToCover == 0) {
                if ((result.start == -1 && result.end == -1)
                    || right - left < result.end - result.start) {
                    result.start = left;
                    result.end = right;
                }
                keywordCount = keywordsToCover.get(paragraph.get(left));
                if (keywordCount != null) {
                    keywordsToCover.put(paragraph.get(left), ++keywordCount);
                    if (keywordCount > 0) {
                        ++remainingToCover;
                    }
                }
                ++left;
            }
        }
        return result;
    }

    /**
     * <pre>The complexity is 0(n), where n is the length of the array, since for each of the two indices we spend 0(1)
     * time per advance, and each is advanced at most n-1 times.
     * The disadvantage of this approach is that we need to keep the subarrays in memory. We can achieve a streaming
     * algorithm by keeping track of latest occurrences of query keywords as we process A. We use a doubly linked
     * list L to store the last occurrence (index) of each keyword in O, and hash table H to map each keyword in O to
     * the corresponding node in L. Each time a word in O is encountered, we remove its node from L (which we find by
     * using H),create a new node which records the current index in A, and append the new node to the end of L. We
     * also update H. By doing this, each keyword in L is ordered by its order in A; therefore, if L has nQ words (i.e,
     * all keywords are shown) and the current index minus the index stored in the first node in L is less than
     * current best, we update current best. The complexity is still 0(n).</pre>
     */

    private static Integer getValueForFirstEntry(LinkedHashMap<String, Integer> m) {
        // LinkedHashMap guarantees iteration over key-value pairs takes place in insertion order, most recent first.
        Integer result = null;
        for (Map.Entry<String, Integer> entry : m.entrySet()) {
            result = entry.getValue();
            break;
        }
        return result;
    }

    public static Subarray findSmallestSubarrayCoveringSubset(Iterator<String> iter, List<String> queryStrings) {
        LinkedHashMap<String, Integer> dict = new LinkedHashMap<>();
        for (String s : queryStrings) {
            dict.put(s, null);
        }
        int numStringsFromQueryStringsSeenSoFar = 0;
        Subarray res = new Subarray(-1, -1);
        int idx = 0;
        while (iter.hasNext()) {
            String s = iter.next();
            if (dict.containsKey(s)) {// s is in queryStrings.
                Integer it = dict.get(s);
                if (it == null) {
                    // First time seeing this string from queryStrings.
                    numStringsFromQueryStringsSeenSoFar++;
                }
                // dict.put(s,idx) won't work because it does not move the entry to
                // the front of the queue if an entry with key s is already present.
                // So we explicitly remove the existing entry with key s, then put
                // (s,idx).
                dict.remove(s);
                dict.put(s, idx);
            }
            if (numStringsFromQueryStringsSeenSoFar == queryStrings.size()) {
                // We have seen all strings in queryStrings, let’s get to work.
                if ((res.start == -1 && res.end == -1)
                    || idx - getValueForFirstEntry(dict) < res.end - res.start) {
                    res.start = getValueForFirstEntry(dict);
                    res.end = idx;
                }
            }
            ++idx;
        }
        return res;
    }

    // Covering Sequentially
    public static Subarray findSmallestSequentiallyCoveringSubset(List<String> paragraph, List<String> keywords) {
        // Maps each keyword to its index in the keywords array.
        Map<String, Integer> keywordToIdx = new HashMap<>();
        // Since keywords are uniquely identified by their indices in keywords array, we can use those indices as
        // keys to lookup in a vector.
        List<Integer> latestOccurrence = new ArrayList<>(keywords.size());
        // For each keyword (identified by its index in keywords array), stores the length of the shortest subarray
        // ending at the most recent occurrence of that keyword that sequentially cover all keywords up to that keyword.
        List<Integer> shortestSubarrayLength = new ArrayList<>(keywords.size());
        // Initializes latestOccurrence , shortestSubarrayLength , and keywordToIdx.
        for (int i = 0; i < keywords.size(); ++i) {
            latestOccurrence.add(-1);
            shortestSubarrayLength.add(Integer.MAX_VALUE);
            keywordToIdx.put(keywords.get(i), i);
        }
        int shortestDistance = Integer.MAX_VALUE;
        Subarray result = new Subarray(-1, -1);
        for (int i = 0; i < paragraph.size(); ++i) {
            Integer keywordIdx = keywordToIdx.get(paragraph.get(i));
            if (keywordIdx != null) {
                if (keywordIdx == 0) { // First keyword.
                    shortestSubarrayLength.set(0, 1);
                } else if (shortestSubarrayLength.get(keywordIdx - 1) != Integer.MAX_VALUE) {
                    int distanceToPreviousKeyword = i - latestOccurrence.get(keywordIdx - 1);
                    shortestSubarrayLength.set(keywordIdx,
                                               distanceToPreviousKeyword + shortestSubarrayLength.get(keywordIdx - 1));
                }
                latestOccurrence.set(keywordIdx, i);
                // Last keyword, look for improved subarray.
                if (keywordIdx == keywords.size() - 1
                    && shortestSubarrayLength.get(shortestSubarrayLength.size() - 1) < shortestDistance) {
                    shortestDistance = shortestSubarrayLength.get(shortestSubarrayLength.size() - 1);
                    result.start = i - shortestSubarrayLength.get(shortestSubarrayLength.size() - 1) + 1;
                    result.end = i;
                }
            }
        }
        return result;
    }
}
