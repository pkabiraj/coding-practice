package org.psk.practice.ps;

/**
 * Find an algorithm to find all valid permutation of parenthesis for given n.
 * <p>
 * For n =2, the O/P should be
 * {}{}
 * {{}}
 * <p>
 * for n=3, O/P should be
 * {}{}{}
 * {{{}}}
 * {{}}{}
 * {}{{}}
 * {{}{}}
 */

public class Parenthesis {

    public static void main(String[] args) {
        printParenthesis(3);
    }

    private static void printParenthesis(int maxNo) {
        printParenthesis(maxNo, 0, 0, new StringBuilder());
    }

    private static void printParenthesis(int maxNo, int openCount, int closeCount, StringBuilder output) {
        if (closeCount == maxNo) {
            System.out.println(output.toString());
        } else {
            if (openCount < maxNo) {
                output.append('{');
                printParenthesis(maxNo, openCount + 1, closeCount, output);
                output.setLength(output.length() - 1);
            }
            if (openCount > closeCount) {
                output.append('}');
                printParenthesis(maxNo, openCount, closeCount + 1, output);
                output.setLength(output.length() - 1);
            }
        }
    }
}
