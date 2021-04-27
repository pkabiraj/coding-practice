package org.psk.practice.ds.slidingwindow;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LengthOfLongestSubstringTwoDistinct {

    private static final int DISTINCT_ELEM = 2;

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        int len = s.length();
        if (len <= DISTINCT_ELEM) {
            return len;
        }

        // sliding window left and right pointers
        int left = 0;
        int right = 0;
        // hashmap character -> its rightmost position
        // in the sliding window
        final Map<Character, Integer> charIndexMap = new HashMap<Character, Integer>();
        int maxLength = DISTINCT_ELEM;

        while (right < len) {
            // slidewindow contains less than 3 characters
            charIndexMap.put(s.charAt(right), right++);

            // slidewindow contains 3 characters
            if (charIndexMap.size() == DISTINCT_ELEM + 1) {
                // delete the leftmost character
                int delIndex = Collections.min(charIndexMap.values());
                charIndexMap.remove(s.charAt(delIndex));
                // move left pointer of the slidewindow
                left = delIndex + 1;
            }

            maxLength = Math.max(maxLength, right - left);
        }
        return maxLength;
    }
}
