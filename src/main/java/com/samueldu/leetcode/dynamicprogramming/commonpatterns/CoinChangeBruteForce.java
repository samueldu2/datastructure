package com.samueldu.leetcode.dynamicprogramming.commonpatterns;

/**
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
 *
 * Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 *
 * You may assume that you have an infinite number of each kind of coin.
 *
 *
 *
 * Example 1:
 *
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * Example 2:
 *
 * Input: coins = [2], amount = 3
 * Output: -1
 * Example 3:
 *
 * Input: coins = [1], amount = 0
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 231 - 1
 * 0 <= amount <= 104
 */
public class CoinChangeBruteForce {
    /**
     * Approach 1 (Brute force) [Time Limit Exceeded]

     *
     * Space complexity : O(n)O(n). In the worst case the maximum depth of recursion is nn. Therefore we need O( n)O(n) space used by the system recursive stack.
     * Complexity Analysis
     * Time complexity : O(S^n)O(S
     * n
     *  ). In the worst case, complexity is exponential in the number of the coins nn. The reason is that every coin denomination c_ic
     * i
     * ​
     *   could have at most \frac{S}{c_i}
     * c
     * i
     * ​
     *
     * S
     * ​
     *   values. Therefore the number of possible combinations is :
     * \frac{S}{c_1}*\frac{S}{c_2}*\frac{S}{c_3}\ldots\frac{S}{c_n} = \frac{S^{n}}{{c_1}*{c_2}*{c_3}\ldots{c_n}}
     * c
     * 1
     * ​
     *
     * S
     * ​
     *  ∗
     * c
     * 2
     * ​
     *
     * S
     * ​
     *  ∗
     * c
     * 3
     * ​
     *
     * S
     * ​
     *  …
     * c
     * n
     * ​
     *
     * S
     * ​
     *  =
     * c
     * 1
     * ​
     *  ∗c
     * 2
     * ​
     *  ∗c
     * 3
     * ​
     *  …c
     * n
     * ​
     *
     * S
     * n
     *
     * ​
     *
     *
     * Space complexity : O(n)O(n). In the worst case the maximum depth of recursion is nn. Therefore we need O( n)O(n) space used by the system recursive stack.
     */

        public int coinChange(int[] coins, int amount) {
            return coinChange(0, coins, amount);
        }

        private int coinChange(int idxCoin, int[] coins, int amount) {
            if (amount == 0)
                return 0;
            if (idxCoin < coins.length && amount > 0) {
                int maxVal = amount/coins[idxCoin];
                int minCost = Integer.MAX_VALUE;
                for (int x = 0; x <= maxVal; x++) {
                    if (amount >= x * coins[idxCoin]) {
                        int res = coinChange(idxCoin + 1, coins, amount - x * coins[idxCoin]);
                        if (res != -1)
                            minCost = Math.min(minCost, res + x);
                    }
                }
                return (minCost == Integer.MAX_VALUE)? -1: minCost;
            }
            return -1;
        }


// Time Limit Exceeded
}
