package com.samueldu.leetcode.dynamicprogramming.commonpatterns;

import java.util.Arrays;

public class CoinChangeDPBottomUp {
    /**
     * Approach 3 (Dynamic programming - Bottom up) [Accepted]
     * Algorithm
     * For the iterative solution, we think in bottom-up manner. Before calculating F(i)F(i), we have to compute all minimum counts for amounts up to ii. On each iteration ii of the algorithm F(i)F(i) is computed as \min_{j=0 \ldots n-1}{F(i -c_j)} + 1min
     * j=0…n−1
     * ​
     *  F(i−c
     * j
     * ​
     *  )+1
     *
     * Bottom-up approach using a table to build up the solution to F6.
     *
     * In the example above you can see that:
     *
     * \begin{aligned} F(3) &= \min\{{F(3- c_1), F(3-c_2), F(3-c_3)}\} + 1 \\ &= \min\{{F(3- 1), F(3-2), F(3-3)}\} + 1 \\ &= \min\{{F(2), F(1), F(0)}\} + 1 \\ &= \min\{{1, 1, 0}\} + 1 \\ &= 1 \end{aligned}
     * F(3)
     * ​
     *
     * =min{F(3−c
     * 1
     * ​
     *  ),F(3−c
     * 2
     * ​
     *  ),F(3−c
     * 3
     * ​
     *  )}+1
     * =min{F(3−1),F(3−2),F(3−3)}+1
     * =min{F(2),F(1),F(0)}+1
     * =min{1,1,0}+1
     * =1
     * ​
     *
     *
     * Implementation
     *
     * Complexity Analysis
     * Time complexity : O(S*n)O(S∗n). On each step the algorithm finds the next F(i)F(i) in nn iterations, where 1\leq i \leq S1≤i≤S. Therefore in total the iterations are S*nS∗n.
     * Space complexity : O(S)O(S). We use extra space for the memoization table.
     */
    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

}
