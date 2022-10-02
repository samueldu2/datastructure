package com.samueldu.leetcode.dynamicprogramming.stragegies;

/**
 *
 */
public class MaximumScoreFromPerformingMultiplicationBottomUp {
    /**
     * Bottom-up Implementation
     * In the bottom-up implementation, the array works the same way as the function from top-down. \text{dp[i][left]}dp[i][left] represents the max score possible if \text{i}i operations have been performed and \text{left}left left operations have been performed.
     *
     * Earlier in the explore card, we learned that while bottom-up is typically faster than top-down, it is often harder to implement. This is because the order in which we iterate needs to be precise. You'll see in the implementations below that we use the same math to calculate \text{right}right, and the same recurrence relation but we need to iterate backwards starting from \text{m}m (because the base case happens when \text{i}i equals \text{m}m). We also need to initialize \text{dp}dp with one extra row so that we don't go out of bounds in the first iteration of the outer loop.
     *
     *
     * The time and space complexity of both implementations is O(m^2)O(m
     * 2
     *  ) where \text{m}m is the length of \text{multipliers}multipliers. We will talk about more in depth about time and space complexity at the end of this chapter.
     * @param nums
     * @param multipliers
     * @return
     */
    public int maximumScore(int[] nums, int[] multipliers) {
        int n = nums.length;
        int m = multipliers.length;
        int[][] dp = new int[m + 1][m + 1];

        for (int i = m - 1; i >= 0; i--) {
            for (int left = i; left >= 0; left--) {
                int mult = multipliers[i];
                int right = n - 1 - (i - left);
                dp[i][left] = Math.max(mult * nums[left] + dp[i + 1][left + 1],
                        mult * nums[right] + dp[i + 1][left]);
            }
        }

        return dp[0][0];
    }
}
