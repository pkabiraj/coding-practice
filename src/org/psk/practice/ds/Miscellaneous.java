package org.psk.practice.ds;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Miscellaneous {

    public static void main(String[] args) throws InterruptedException {

        // System.out.println(parseInt("123", 10));

        int[] arr = {1, 3, 5, 4, 4, 6, 7, -1, 9, 1};
        findSumHashMap(arr, 9);
        System.out.println(".....................");
        findSum(arr, 9);

        // removeChars("Battle of the Vowels: Hawaii vs. Grozny", "aeiou");

        // formTeam(1, 5, 3, "");

        // int[] arr = { 6, 7, 8, 1, 2, 3, 4, 5 };
        // System.out.println(findRotation(arr));

        // findLangford(new int[3 * 2], 3);

        // for (int nos = 0; nos < 10; nos++) {
        // generateNos(nos, 3);
        // }

        // int arr[] = new int[] {1,2,3,4,5,6,7,8,9,10};
        // Map<Integer, Triplet> map = new HashMap<Integer, Triplet>();
        // for (int i = 0; i < arr.length; i++) {
        // int square = arr[i]*arr[i];
        // map.put(square, new Triplet(arr[i], square));
        // }
        //
        // Set<Entry<Integer,Triplet>> entrySet = map.entrySet();
        // boolean first = true;
        // for (Entry<Integer, Triplet> entry : entrySet) {
        // if(first) {
        //
        // continue;
        // }
        // }

        //        longestSubStringWithoutrepeatation();

        // String input = "Pinaki Sankar Kabiraj";
        // reverseSentence(input.toCharArray(), input.length());

        // int STOCK[] = { 13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5,
        // -22, 15, -4, 7 };
        // System.out.println(maxSubArrSum(STOCK, STOCK.length));

        //		printStrings("ABCD", "", 0, "ABCD".length());
    }

    private static void printStrings(String string, String temp, int i, int length) {
        temp = temp + string.charAt(i);

        if (i == length - 1) {
            System.out.println(temp);
            return;
        }

        printStrings(string, temp, i + 1, length);

        temp = temp + " ";

        printStrings(string, temp, i + 1, length);
    }

    public static void longestSubStringWithoutrepeatation() {
        String str = "java2novice";
        String longestSoFar = "";
        String longestSubStr = "";
        char current;
        int[] lastSeen = new int[256];

        for (int i = 0; i < 256; i++) {
            lastSeen[i] = -1;
        }

        for (int i = 0; i < str.length(); i++) {
            current = str.charAt(i);

            if (lastSeen[current] == -1) {
                longestSoFar = longestSoFar + current;
            } else {
                longestSoFar = str.substring(lastSeen[current] + 1, i + 1);
            }

            lastSeen[current] = i;

            if (longestSoFar.length() > longestSubStr.length()) {
                longestSubStr = longestSoFar;
            }
        }

        System.out.println(longestSubStr);
    }

    private static class Triplet implements Comparable<Integer> {

        private final int square;
        private final int value;

        public Triplet(int value,
                       int square) {
            this.value = value;
            this.square = square;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Triplet triplet = (Triplet) obj;
            return value == triplet.value && square == triplet.square;
        }

        @Override
        public int hashCode() {
            int hash = 31 * 17;
            hash = hash * value;
            hash = hash * square;
            return hash;
        }

        @Override
        public int compareTo(Integer o) {
            // TODO Auto-generated method stub
            return 0;
        }
    }

    public static int getBitAtPosition(int x, int position) {
        return x >> position & 1;
    }

    public static int findFirstBitWithSetBit(int x) {
        int position = 0;
        while ((x & 1) != 1) {
            position++;
            x = x >> 1;
        }
        return position;
    }

    private static void generateNos(int nos, int limit) {
        if (limit == 0) {
            System.out.println(nos);
            return;
        }
        if (nos % 10 != 9) {
            generateNos(nos * 10 + nos % 10 + 1, limit - 1);
        }

        if (nos % 10 - 1 > -1) {
            generateNos(nos * 10 + nos % 10 - 1, limit - 1);
        }
    }

    private static void findLangford(int[] arr, int n) {
        if (n == 0) {
            for (int element : arr) {
                System.out.print(element + " ");
            }
            System.out.print("\n");
            return;
        }
        for (int i = 0; i < arr.length - n - 1; i++) {
            if (arr[i] == 0 && arr[i + n + 1] == 0) { // empty slot
                arr[i] = arr[i + n + 1] = n;
                findLangford(arr, n - 1);
                arr[i] = arr[i + n + 1] = 0; // undo
            }
        }
    }

    private static int findRotation(int[] arr) {
        int lo = 0;
        int hi = arr.length - 1;
        return findRotation(arr, lo, hi);
    }

    private static int findRotation(int[] arr, int lo, int hi) {
        int middle = (lo + hi) / 2;
        while (middle > lo) {
            if (arr[middle] < arr[hi]) {
                hi = middle;
            } else {
                lo = middle;
            }
            middle = (hi + lo) / 2;
        }
        return middle + 1;
    }

    private static void findSum(int[] arr, int key) {
        Arrays.sort(arr);
        int start = 0;
        int end = arr.length - 1;

        while (start < end) {
            int val = arr[start] + arr[end];
            if (val <= key) {
                if (val == key) {
                    System.out.println(arr[start] + "  " + arr[end]);
                }
                int startVal = arr[start];
                while (arr[start] == startVal) {
                    start++;
                }
            } else if (val > key) {
                int endVal = arr[end];
                while (arr[end] == endVal) {
                    end--;
                }
            }
        }
    }

    private static void formTeam(int c, int n, int k, String team) {
        int currentLength = team.length();
        if (currentLength == k) {
            System.out.println(team);
            return;
        }
        for (int idx = c; n - idx + 1 >= k - currentLength; idx++) {
            formTeam(idx + 1, n, k, team + idx);
        }
    }

    private static void revString(char[] A, int l, int r) {
        if (l >= r) {
            return;
        }

        while (l < r) {
            char temp = A[l];
            A[l] = A[r];
            A[r] = temp;
            l++;
            r--;
        }
    }

    private static void reverseSentence(char[] A, int n) { // n is length
        revString(A, 0, n - 1);
        System.out.println(A);
        int i = 0;
        int l = 0;
        while (i <= n) {
            if (i == n || A[i] == ' ') { // assuming breaking only on space
                revString(A, l, i - 1);
                l = i + 1;
            }
            i++;
        }
        System.out.println(A);
    }

    private static void removeChars(String input, String remove) {
        char[] src = input.toCharArray();
        char[] rmv = remove.toCharArray();
        boolean[] flags = new boolean[128];

        for (int i = 0; i < rmv.length; i++) {
            flags[rmv[i]] = true;
        }

        int start = 0, dest = 0, len = src.length;
        while (start < len) {
            if (!flags[src[start]]) {
                src[dest++] = src[start];
            }
            start++;
        }

        System.out.println(new String(src, 0, dest));
    }

    public static void findSumHashMap(int[] arr, int key) {
        Map<Integer, Integer> valMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            valMap.put(arr[i], i);
        }
        for (int i = 0; i < arr.length; i++) {
            if (valMap.containsKey(key - arr[i]) && valMap.get(key - arr[i]) < i) {
                int diff = key - arr[i];
                System.out.println(arr[i] + " " + diff);
            }
        }
    }

    public static int maxSubArrSum(int[] numbers, int length) {
        // Initialize variables here
        int max_so_far = numbers[0], max_ending_here = numbers[0];

        // Find sequence by looping through
        for (int i = 1; i < length; i++) {
            // calculate max_ending_here
            if (max_ending_here < 0) {
                max_ending_here = numbers[i];
            } else {
                max_ending_here += numbers[i];
            }

            // calculate max_so_far
            if (max_ending_here >= max_so_far) {
                max_so_far = max_ending_here;
            }
        }
        return max_so_far;
    }

    public static int parseInt(String s, int radix) {
        int result = 0;
        int count = 0;
        boolean negative = false;
        char[] chars = s.toCharArray();
        int mult = 1;

        if (s.charAt(0) == '-') {
            negative = true;
            count++;
        }

        for (int i = chars.length - 1; i >= count; i--) {
            char ch = chars[i];
            int digit = Character.digit(ch, radix);
            result += mult * digit;
            mult *= radix;
        }

        if (negative) {
            result = -result;
        }
        return result;
    }

    public static int minWindow(String x, Set<Character> y) {
        int ret = x.length();
        Map<Character, Integer> index = new LinkedHashMap<>();

        for (int j = 0; j < x.length(); j++) {
            char ch = x.charAt(j);
            if (y.contains(ch)) {
                index.remove(ch);
                index.put(ch, j);
                if (index.size() == y.size()) {
                    int i = index.values().iterator().next();
                    if (ret > j - i + 1) {
                        ret = j - i + 1;
                    }
                }
            }
        }
        return ret;
    }
}