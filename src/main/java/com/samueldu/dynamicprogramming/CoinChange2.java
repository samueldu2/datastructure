package com.samueldu.dynamicprogramming;

/**
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
 *
 * Return the number of combinations that make up that amount. If that amount of money cannot be made up by any combination of the coins, return 0.
 *
 * You may assume that you have an infinite number of each kind of coin.
 *
 * The answer is guaranteed to fit into a signed 32-bit integer.
 *
 *
 *
 * Example 1:
 *
 * Input: amount = 5, coins = [1,2,5]
 * Output: 4
 * Explanation: there are four ways to make up the amount:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * Example 2:
 *
 * Input: amount = 3, coins = [2]
 * Output: 0
 * Explanation: the amount of 3 cannot be made up just with coins of 2.
 * Example 3:
 *
 * Input: amount = 10, coins = [10]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= coins.length <= 300
 * 1 <= coins[i] <= 5000
 * All the values of coins are unique.
 * 0 <= amount <= 5000
 */
public class CoinChange2 {
    /**
     * Template
     *
     * This is a classical dynamic programming problem.
     *
     * Here is a template one could use:
     *
     * Define the base cases for which the answer is obvious.
     *
     * Develop the strategy to compute more complex case from more simple one.
     *
     * Link the answer to base cases with this strategy.
     * @param amount
     * @param coins
     * @return
     *
     * Now the strategy is here:
     *
     * Add coins one-by-one, starting from base case "no coins".
     *
     * For each added coin, compute recursively the number of combinations for each amount of money from 0 to amount.
     *
     * Algorithm
     *
     * Initiate number of combinations array with the base case "no coins": dp[0] = 1, and all the rest = 0.
     *
     * Loop over all coins:
     *
     * For each coin, loop over all amounts from 0 to amount:
     *
     * For each amount x, compute the number of combinations: dp[x] += dp[x - coin].
     * Return dp[amount].
     */


    public int change(int amount, int[] coins) {
       int [] dp = new int[amount+1];
        /**
         * choices with no coins
         */
        dp[0]=1;
        if(amount==0|| coins.length==0) return 1;
        for (int coin :coins) {
            for (int i = 0; i < amount+1; i++) {
                if(i-coin>=0)
                    dp[i] = dp[i] + dp[i - coin];
            }
        }
        return dp[amount];
    }

}
