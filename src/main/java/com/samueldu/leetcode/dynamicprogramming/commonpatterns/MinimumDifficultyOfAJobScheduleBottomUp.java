package com.samueldu.leetcode.dynamicprogramming.commonpatterns;

/**
 *
 */
public class MinimumDifficultyOfAJobScheduleBottomUp {
    /**
     *Bottom-up 1D DP
     * Intuition
     *
     * As mentioned in the previous approach, bottom-up DP requires subproblems to be solved in a specific order. While this makes bottom-up DP less flexible than top-down DP, with some clever planning, this constraint allows us to reduce the amount of space used. Because the subproblems are solved in a specific order, sometimes the results of subproblems will be referenced early in the iterative process and then never referenced again. Therefore, once we are certain we do not need to reference the results of these subproblems, the results can be removed from memory. Let's see if we can improve the previous approach by implementing this optimization.
     *
     * Notice that in Approach 2, any state with d days remaining only depends on the results for states with d - 1 days remaining. Therefore, we do not need to store the states with less than d - 1 days remaining. Therefore, the space complexity can be further improved.
     *
     * Algorithm
     *
     * Since the result in min_diff[d][i] only depends on min_diff[d - 1][j], we only need to store the states of the current day and the next day as two DP arrays of size n, where n is the number of jobs.
     *
     * This approach is very similar to Approach 2, the few changes made include: min_diff[d][i] has been replaced by with min_diff_curr_day[i], and min_diff[d - 1][j] has been replaced with min_diff_next_day[j]. min_diff_curr_day[i] records all the results for the current day, while min_diff_next_day[j] records all the results for the next day (with one less remaining day).
     *
     *
     * Complexity Analysis
     *
     * Let nn be the length of the jobDifficulty array, and dd be the total number of days.
     *
     * Time complexity: O(n^2 \cdot d)O(n
     * 2
     *  ⋅d) since there are n \cdot dn⋅d possible states, and we need O(n)O(n) time to calculate the result for each state.
     *
     * Space complexity: O(n)O(n) as we only use two arrays of length n + 1n+1 to store all relevant states at any given time.
     */



        public int minDifficulty(int[] jobDifficulty, int d) {
            int n = jobDifficulty.length;
            // Initialize the minDiff matrix to record the minimum difficulty
            // of the job schedule
            int[][] minDiff = new int[d + 1][n + 1];
            for (int daysRemaining = 0; daysRemaining <= d; daysRemaining++) {
                for (int i = 0; i < n; i++) {
                    minDiff[daysRemaining][i] = Integer.MAX_VALUE;
                }
            }
            for (int daysRemaining = 1; daysRemaining <= d; daysRemaining++) {
                for (int i = 0; i < n - daysRemaining + 1; i++) {
                    int dailyMaxJobDiff = 0;
                    for (int j = i + 1; j < n - daysRemaining + 2; j++) {
                        // Use dailyMaxJobDiff to record maximum job difficulty
                        dailyMaxJobDiff = Math.max(dailyMaxJobDiff, jobDifficulty[j - 1]);
                        if (minDiff[daysRemaining - 1][j] != Integer.MAX_VALUE) {
                            minDiff[daysRemaining][i] = Math.min(minDiff[daysRemaining][i],
                                    dailyMaxJobDiff + minDiff[daysRemaining - 1][j]);
                        }
                    }
                }
            }
            return minDiff[d][0] < Integer.MAX_VALUE ? minDiff[d][0] : -1;
        }


}
