package com.samueldu.leetcode.dynamicprogramming.stragegies;

public class LognestCommonSubsequenceDynamicProgramming {
    /**
     * Dynamic Programming
     * Intuition
     *
     * In many programming languages, iteration is faster than recursion. Therefore, we often want to convert a top-down memoization approach into a bottom-up dynamic programming one (some people go directly to bottom-up, but most people find it easier to come up with a recursive top-down approach first and then convert it; either way is fine).
     *
     * Observe that the subproblems have a natural "size" ordering; the largest subproblem is the one we start with, and the smallest subproblems are the ones with just one letter left in each word. The answer for each subproblem depends on the answers to some of the smaller subproblems.
     *
     * Remembering too that each subproblem is represented as a pair of indexes, and that there are text1.length() * text2.length() such possible subproblems, we can iterate through the subproblems, starting from the smallest ones, and storing the answer for each. When we get to the larger subproblems, the smaller ones that they depend on will already have been solved. The best way to do this is to use a 2D array.
     *
     * Empty grid for bottom up approach
     *
     * Each cell represents one subproblem. For example, the below cell represents the subproblem lcs("attag", "gtgatcg").
     *
     * Cell highlighted for subproblem
     *
     * Remembering back to Approach 2, there were two cases.
     *
     * The first letter of each string is the same.
     * The first letter of each string is different.
     * For the first case, we solve the subproblem that removes the first letter from each, and add 1. In the grid, this subproblem is always the diagonal immediately down and right.
     *
     * Cell where first letter is same, showing +1 into new cell
     *
     * For the second case, we consider the subproblem that removes the first letter off the first word, and then the subproblem that removes the first letter off the second word. In the grid, these are subproblems immediately right and below.
     *
     * Cell where first letter is same, showing +1 into new cell
     *
     * Putting this all together, we iterate over each column in reverse, starting from the last column (we could also do rows, the final result will be the same). For a cell (row, col), we look at whether or not text1.charAt(row) == text2.charAt(col) is true. if it is, then we set grid[row][col] = 1 + grid[row + 1][col + 1]. Otherwise, we set grid[row][col] = max(grid[row + 1][col], grid[row][col + 1]).
     *
     * For ease of implementation, we add an extra row of zeroes at the bottom, and an extra column of zeroes to the right.
     *
     * Here is an animation showing this algorithm.
     *
     * Current
     * 83 / 83
     * Algorithm
     *
     *
     * Complexity Analysis
     *
     * Time complexity : O(M \cdot N)O(M⋅N).
     *
     * We're solving M \cdot NM⋅N subproblems. Solving each subproblem is an O(1)O(1) operation.
     *
     * Space complexity : O(M \cdot N)O(M⋅N).
     *
     * We'e allocating a 2D array of size M \cdot NM⋅N to save the answers to subproblems.
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {

        // Make a grid of 0's with text2.length() + 1 columns
        // and text1.length() + 1 rows.
        int[][] dpGrid = new int[text1.length() + 1][text2.length() + 1];

        // Iterate up each column, starting from the last one.
        for (int col = text2.length() - 1; col >= 0; col--) {
            for (int row = text1.length() - 1; row >= 0; row--) {
                // If the corresponding characters for this cell are the same...
                if (text1.charAt(row) == text2.charAt(col)) {
                    dpGrid[row][col] = 1 + dpGrid[row + 1][col + 1];
                    // Otherwise they must be different...
                } else {
                    dpGrid[row][col] = Math.max(dpGrid[row + 1][col], dpGrid[row][col + 1]);
                }
            }
        }

        // The original problem's answer is in dp_grid[0][0]. Return it.
        return dpGrid[0][0];
    }
}
