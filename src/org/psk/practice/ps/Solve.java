package org.psk.practice.ps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solve {

    public static void main(String[] args) {
//        System.out.println(removeDuplicates(new int[] {0,0,1,1,1,2,2,3,3,4}));
//
//        System.out.println(new Solve().multiply("12", "456"));
//        final int[] nums = {0, 1, 0, 3, 12};
//        new Solve().moveZeroes(nums);
//        System.out.println(Arrays.toString(nums));

        System.out.println(matchCore("cat", "c*t"));
        System.out.println(matchCore("cat", "*"));
        System.out.println(matchCore("cat", "*t"));
        System.out.println(matchCore("cat", "*at"));

        System.out.println(matchCore("aaab", "aa*b"));
        System.out.println(matchCore("ababbbbbb", "aa*b*"));
        System.out.println(matchCore("acb", "a*b"));

        final Pattern pattern = Pattern.compile("c*t");
        pattern.matcher("cat").matches();
    }

    public static boolean matchCore(final String text, final String pattern) {
        if (isBlank(pattern)) {
            return isBlank(text);
        } else if (!isBlank(text) && (pattern.length() == 1 && pattern.charAt(0) == '*')) {
            return true;
        }

        if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
            return matchCore(text.substring(1), pattern.substring(2)) // move on to the next state
                   || matchCore(text.substring(1), pattern); // stay on the current state
        } else {
            return matchCore(text.substring(1), pattern.substring(1)); // move on to the next state
        }
    }

    private static boolean isBlank(final String input) {
        return input == null || input.isBlank();
    }

    public void moveZeroes(int[] nums) {
        int zi = 0;
        int i = 0;
        while (nums[i] == 0) {
            zi = i;
            i++;
        }
        while(i < nums.length) {
            if(nums[i] != 0) {
                swap(nums, i, zi);
                zi++;
            }
            i++;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j]= temp;
    }

    private static final String ZERO = "0";

    public String multiply(String num1, String num2) {
        if (ZERO.equals(num1) || ZERO.equals(num2)) {
            return ZERO;
        }
        char[] bigger, smaller;
        if (num1.length() > num2.length()) {
            bigger = num1.toCharArray();
            smaller = num2.toCharArray();
        } else {
            bigger = num2.toCharArray();
            smaller = num1.toCharArray();
        }

        final int[][] temp = new int[smaller.length][bigger.length + 1];
        int row = -1;
        int col;
        for (int big = bigger.length - 1; big >= 0; big--) {
            row++;
            col = temp[0].length - row - 1;
            for (int small = smaller.length - 1; small >= 0; small--) {
                int num = no(bigger[big]) * no(smaller[small]);
                temp[row][col] += num % 10;
                if (num > 10) {
                    temp[row][col - 1] = 1;
                }
                col--;
            }
        }
        final StringBuilder builder = new StringBuilder();
        return builder.toString();
    }

    private int no(final char c) {
        return (int) (c - '0');
    }

    public static int removeDuplicates(int[] nums) {
        if (nums.length < 2) {
            return nums.length;
        }

        int len = 1;
        int actualIndex = 0, movingIndex = 1;
        while (movingIndex < nums.length) {
            if (nums[actualIndex] != nums[movingIndex]) {
                actualIndex++;
                len++;
                nums[actualIndex] = nums[movingIndex];
            }
            movingIndex++;
        }
        return len;
    }

    public static int quickestWayUp(List<List<Integer>> ladders, List<List<Integer>> snakes) {
        Map<Integer, Integer> ladderMap = ladders.stream()
                .collect(Collectors.toMap(ladder -> ladder.get(0), ladder -> ladder.get(1)));
        Map<Integer, Integer> snakeMap = snakes.stream()
                .collect(Collectors.toMap(snake -> snake.get(0), snake -> snake.get(1)));
        Queue<Integer> queue = new LinkedList<>();
        Map<Integer, Integer> visited = new HashMap<>();
        visited.put(1, 0);
        queue.add(1);
        while (!queue.isEmpty()) {
            Integer element = queue.poll();
            if (element == 100) {
                return visited.get(100);
            }
            for (int i = 1; i <= 6; i++) {
                Integer pos = i + element;
                if (ladderMap.containsKey(pos)) {
                    pos = ladderMap.get(pos);
                } else if (snakeMap.containsKey(pos)) {
                    pos = snakeMap.get(pos);
                }
                if (visited.get(pos) == null) {
                    visited.put(pos, visited.get(element) + 1);
                    queue.add(pos);
                } else if (visited.get(element) + 1 < visited.get(pos)) {
                    visited.put(pos, visited.get(element + 1));
                }
            }
        }

        return -1;
    }

}
