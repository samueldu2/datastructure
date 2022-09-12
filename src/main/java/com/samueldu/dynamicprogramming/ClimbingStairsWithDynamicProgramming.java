package com.samueldu.dynamicprogramming;

/**
 * Approach 3: Dynamic Programming
 * Algorithm
 *
 * As we can see this problem can be broken into subproblems, and it contains the optimal substructure property i.e. its optimal solution can be constructed efficiently from optimal solutions of its subproblems, we can use dynamic programming to solve this problem.
 *
 * One can reach i^{th}i
 * th
 *   step in one of the two ways:
 *
 * Taking a single step from (i-1)^{th}(i−1)
 * th
 *   step.
 *
 * Taking a step of 22 from (i-2)^{th}(i−2)
 * th
 *   step.
 *
 * So, the total number of ways to reach i^{th}i
 * th
 *   is equal to sum of ways of reaching (i-1)^{th}(i−1)
 * th
 *   step and ways of reaching (i-2)^{th}(i−2)
 * th
 *   step.
 *
 * Let dp[i]dp[i] denotes the number of ways to reach on i^{th}i
 * th
 *   step:
 *
 * dp[i]=dp[i-1]+dp[i-2]dp[i]=dp[i−1]+dp[i−2]
 *
 * Complexity Analysis
 *
 * Time complexity : O(n)O(n). Single loop upto nn.
 *
 * Space complexity : O(n)O(n). dpdp array of size nn is used.
 */
public class ClimbingStairsWithDynamicProgramming {

    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
