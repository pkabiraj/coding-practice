package org.psk.practice.ds.recursionAndDP;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>A non-attacking placement of queens is one in which no two queens are in the same row, column, or diagonal.
 * Write a program which returns all distinct non-attacking placements of n queens on an nxn chessboard, where n is
 * an input to the program.</pre>
 */
public class NQueenNonAttackingPlacement {

    /**
     * <pre>A brute-force approach is to consider all possible placements of the n
     * queens—there are (n2 n) possible placements which grows very large with n.
     * Since we never would place two queens on the same row, a much faster solution
     * is to enumerate placements that use distinct rows. Such a placement cannot lead to
     * conflicts on rows, but it may lead to conflicts on columns and diagonals. It can be
     * represented by an array of length n, where the ith entry is the location of the queen
     * on Row i.
     * As an example, if n = 4, begin by placing the first row's queen at Column 0.
     * Now we enumerate all placements of the form (0, _, _, _). Placing the second row's
     * queen at Column 0 leads to a column conflict, so we skip all placements of the form
     * (0, 0, _, _). Placing the second row's queen at Column 1 leads to a diagonal conflict,
     * so we skip all placements of the form (0,1, _, _). Now we turn to placements of the
     * form (0, 2, 0, _). Such placements are conflicting because of the conflict on Column 0.
     * Now we turn to placements of the form (0, 2,1, _) and (0, 2, 2, _). Such placements
     * are conflicting because of the diagonal conflict between the queens at Row 1 and
     * Column 2 and Row 2 and Column 1, and the column conflict between the queens
     * at Row 1 and Column 2 and Row 2 and Column 2, respectively, so we move on to
     * (0, 2, 3, _), which also violates a diagonal constraint. Now we advance to placements
     * of the form (0,3, _, _). Both (0, 3, 1, _) and (0, 3, 2, _) lead to conflicts, implying there
     * is no non-attacking placement possible with a queen placed at Row 0 and Column 0.
     * The first non-attacking placement is (1, 3, 0, 2); the only other non-attacking placement
     * is (2, 0,3,1).</pre>
     * <pre>The time complexity is lower bounded by the number of non-attacking placements.
     * No exact form is known for this quantity as a function of n, but it is conjectured to
     * tend to n!/cn, where c * 2.54, which is super-exponential.</pre>
     */
    public static List<List<Integer>> nQueens(int n) {
        List<List<Integer>> result = new ArrayList<>();
        solveNQueens(n, 0, new ArrayList<>(), result);
        return result;
    }

    private static void solveNQueens(int n, int row, List<Integer> colPlacement, List<List<Integer>> result) {
        if (row == n) {
            // All queens are legally placed.
            result.add(new ArrayList<>(colPlacement));
        } else {
            for (int col = 0; col < n; ++col) {
                colPlacement.add(col);
                if (isValid(colPlacement)) {
                    solveNQueens(n, row + 1, colPlacement, result);
                }
                colPlacement.remove(colPlacement.size() - 1);
            }
        }
    }

    // Test if a newly placed queen will conflict any earlier queens placed before.
    private static boolean isValid(List<Integer> colPlacement) {
        int rowID = colPlacement.size() - 1;
        for (int i = 0; i < rowID; ++i) {
            int diff = Math.abs(colPlacement.get(i) - colPlacement.get(rowID));
            if (diff == 0 || diff == rowID - i) {
                return false;
            }
        }
        return true;
    }
}
