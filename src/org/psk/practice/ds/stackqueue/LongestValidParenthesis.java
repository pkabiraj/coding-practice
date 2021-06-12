package org.psk.practice.ds.stackqueue;

import java.util.Stack;

public class LongestValidParenthesis {

    public static void main(String[] args) {
        final int length = longestValidParentheses("(())())()(");
        System.out.println(length);
    }

    public static int longestValidParentheses(String s) {
        int maxLength = 0, end = -1;
        Stack<Integer> leftParenthesesIndices = new Stack<>();
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(') {
                leftParenthesesIndices.push(i);
            } else if (leftParenthesesIndices.isEmpty()) {
                end = i;
            } else {
                leftParenthesesIndices.pop();
                int start = leftParenthesesIndices.isEmpty() ? end : leftParenthesesIndices.peek();
                maxLength = Math.max(maxLength, i - start);
            }
        }
        return maxLength;
    }
}
