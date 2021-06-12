package org.psk.practice.ds.recursionAndDP;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Write a program that takes an array of digits and a target value, and returns true if it is possible to intersperse
 * multiplies (x) and adds (+) with the digits of the array such that the resulting expression evaluates to the target
 * value. For example, if the array is (1, 2, 3, 2, 5, 3, 7, 8, 5, 9} and the target value is 995, then the target value
 * can be realized by the expression 123 + 2 + 5 x 3 x 7 + 85 x 9, so your program should return true.
 */
public class SynthesizeExpression {

    // TODO pkabiraj: See if DP/memoization approach can be applied

    public static boolean expressionSynthesis(List<Integer> digits, int target) {
        List<Character> operators = new ArrayList<>();
        List<Integer> operands = new ArrayList<>();
        return directedExpressionSynthesis(digits, target, 0, 0, operands, operators);
    }

    private static boolean directedExpressionSynthesis(List<Integer> digits, int target, int currentTerm, int offset,
                                                       List<Integer> operands, List<Character> operators) {
        currentTerm = currentTerm * 10 + digits.get(offset);
        if (offset == digits.size() - 1) {
            operands.add(currentTerm);
            if (evaluate(operands, operators) == target) {// Found a match.
                return true;
            }
            operands.remove(operands.size() - 1);
            return false;
        }
        // No operator.
        if (directedExpressionSynthesis(digits, target, currentTerm, offset + 1, operands, operators)) {
            return true;
        }
        // Tries multiplication operator
        operands.add(currentTerm);
        operators.add('*');
        if (directedExpressionSynthesis(digits, target, 0, offset + 1, operands, operators)) {
            return true;
        }
        operators.remove(operators.size() - 1);
        operands.remove(operands.size() - 1);
        // Tries addition operator .
        operands.add(currentTerm);
        if (target - evaluate(operands, operators) <= remainingInt(digits, offset + 1)) {
            operators.add('+');
            if (directedExpressionSynthesis(digits, target, 0, offset + 1, operands, operators)) {
                return true;
            }
            operators.remove(operators.size() - 1);
        }
        operands.remove(operands.size() - 1);
        return false;
    }

    // Calculates the int represented by digits . subList (idx , digits .size ()) .
    private static int remainingInt(List<Integer> digits, int idx) {
        int val = 0;
        for (int i = idx; i < digits.size(); ++i) {
            val = val * 10 + digits.get(i);
        }
        return val;
    }

    private static int evaluate(List<Integer> operands, List<Character> operators) {
        Deque<Integer> intermediateOperands = new LinkedList<>();
        int operandIdx = 0;
        intermediateOperands.addFirst(operands.get(operandIdx++));
        // Evaluates '*' first.
        for (char oper : operators) {
            if (oper == '*') {
                intermediateOperands.addFirst(intermediateOperands.removeFirst() * operands.get(operandIdx++));
            } else {
                intermediateOperands.addFirst(operands.get(operandIdx++));
            }
        }
        // Evaluates '+' second.
        int sum = 0;
        while (!intermediateOperands.isEmpty()) {
            sum += intermediateOperands.removeFirst();
        }
        return sum;
    }
}
