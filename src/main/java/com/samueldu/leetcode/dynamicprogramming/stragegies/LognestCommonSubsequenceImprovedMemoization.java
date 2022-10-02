package com.samueldu.leetcode.dynamicprogramming.stragegies;

public class LognestCommonSubsequenceImprovedMemoization {
    /**
     * Approach 2: Improved Memoization
     * Intuition
     *
     * There is an alternative way of expressing the solution recursively. The code is simpler, and will also translate a lot more easily into a bottom-up dynamic programming approach.
     *
     * The subproblems are of the same structure as before; represented as two indexes. Also, like before, we're going to be considering multiple possible decisions and then going with the one that has the highest answer. The difference is that the way we break a problem into subproblems is a bit different. For example, here is how our example from before breaks into subproblems.
     *
     * Graph of subproblems showing only links between ones with first character differing
     *
     * If the first character of each string is not the same, then either one or both of those characters will not be used in the final result (i.e. not have a line drawn to or from it). Therefore, the length of the longest common subsequence is max(LCS(p1 + 1, p2), LCS(p1, p2 + 1)).
     *
     * Now, what about subproblems such as LCS("tgattag", "tgtgatcg")? The first letter of each string is the same, and so we could draw a line between them. Should we? Well, there is no reason not to draw a line between the first characters when they're the same. This is because it won't block any later (optimal) decisions. No letters other than those used for the line are removed from consideration by it. Therefore, we don't need to make a decision in this case.
     *
     * When the first character of each string is the same, the length of the longest common subsequence is 1 + LCS(p1 + 1, p2 + 1). In other words, we draw a line a line between the first two characters, adding 1 to the length to represent that line, and then solving the resulting subproblem (that has the first character removed from each string).
     *
     * Here is a few more of the subproblems for the above example.
     *
     * Graph of subproblems showing links between all nodes
     *
     * Like before, we still have overlapping subproblems, i.e. subproblems that appear on more than one branch. Therefore, we should still be using a memoization table, just like before.
     *
     * Algorithm
     *
     *
     * Complexity Analysis
     *
     * Time complexity : O(M \cdot N)O(M⋅N).
     *
     * This time, solving each subproblem has a cost of O(1)O(1). Again, there are M \cdot NM⋅N subproblems, and so we get a total time complexity of O(M \cdot N)O(M⋅N).
     *
     * Space complexity : O(M \cdot N)O(M⋅N).
     *
     * We need to store the answer for each of the M \cdot NM⋅N subproblems.
     */


        private int[][] memo;
        private String text1;
        private String text2;

        public int longestCommonSubsequence(String text1, String text2) {
            // Make the memo big enough to hold the cases where the pointers
            // go over the edges of the strings.
            this.memo = new int[text1.length() + 1][text2.length() + 1];
            // We need to initialise the memo array to -1's so that we know
            // whether or not a value has been filled in. Keep the base cases
            // as 0's to simplify the later code a bit.
            for (int i = 0; i < text1.length(); i++) {
                for (int j = 0; j < text2.length(); j++) {
                    this.memo[i][j] = -1;
                }
            }
            this.text1 = text1;
            this.text2 = text2;
            return memoSolve(0, 0);
        }

        private int memoSolve(int p1, int p2) {
            // Check whether or not we've already solved this subproblem.
            // This also covers the base cases where p1 == text1.length
            // or p2 == text2.length.
            if (memo[p1][p2] != -1) {
                return memo[p1][p2];
            }

            // Recursive cases.
            int answer = 0;
            if (text1.charAt(p1) == text2.charAt(p2)) {
                answer = 1 + memoSolve(p1 + 1, p2 + 1);
            } else {
                answer = Math.max(memoSolve(p1, p2 + 1), memoSolve(p1 + 1, p2));
            }

            // Add the best answer to the memo before returning it.
            memo[p1][p2] = answer;
            return memo[p1][p2];
        }

}
