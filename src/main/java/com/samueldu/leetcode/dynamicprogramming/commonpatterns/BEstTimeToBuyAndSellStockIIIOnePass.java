package com.samueldu.leetcode.dynamicprogramming.commonpatterns;

public class BEstTimeToBuyAndSellStockIIIOnePass {
    /**
     * One-pass Simulation
     * Intuition
     *
     * Just when we think that the space complexity of \mathcal{O}(N)O(N) is the best we can get for this problem, many users in the Discussion forum proposed a more optimized solution that reduced the space complexity to O(1)O(1), (just to name a few of them @weijiac, @shetty4l). The idea is quite brilliant, and requires only a single iteration without the additional DP arrays.
     *
     * The intuition is that we can consider the problem as a game, and we as agent could make at most two transactions in order to gain the maximum points (profits) from the game.
     *
     * The two transactions be decomposed into 4 actions: "buy of transaction #1", "sell of transaction #1", "buy of transaction #2" and "sell of transaction #2".
     *
     * To solve the game, we simply run a simulation along the sequence of prices, at each time step, we calculate the potential outcomes for each of our actions. At the end of the simulation, the outcome of the final action "sell of transaction #2" would be the desired output of the problem.
     *
     * game simulation
     *
     * Algorithm
     *
     * Overall, we run an iteration over the sequence of prices.
     *
     * Over the iteration, we calculate 4 variables which correspond to the costs or the profits of each action respectively, as follows:
     *
     * t1_cost: the minimal cost of buying the stock in transaction #1. The minimal cost to acquire a stock would be the minimal price value that we have seen so far at each step.
     *
     * t1_profit: the maximal profit of selling the stock in transaction #1. Actually, at the end of the iteration, this value would be the answer for the first problem in the series, i.e. Best Time to Buy and Sell Stock.
     *
     * t2_cost: the minimal cost of buying the stock in transaction #2, while taking into account the profit gained from the previous transaction #1. One can consider this as the cost of reinvestment. Similar with t1_cost, we try to find the lowest price so far, which in addition would be partially compensated by the profits gained from the first transaction.
     *
     * t2_profit: the maximal profit of selling the stock in transaction #2. With the help of t2_cost as we prepared so far, we would find out the maximal profits with at most two transactions at each step.
     *
     *
     * Complexity
     *
     * Time Complexity: \mathcal{O}(N)O(N), where NN is the length of the input sequence.
     *
     * Space Complexity: \mathcal{O}(1)O(1), only constant memory is required, which is invariant from the input sequence.
     */

        public int maxProfit(int[] prices) {
            int t1Cost = Integer.MAX_VALUE,
                    t2Cost = Integer.MAX_VALUE;
            int t1Profit = 0,
                    t2Profit = 0;

            for (int price : prices) {
                // the maximum profit if only one transaction is allowed
                t1Cost = Math.min(t1Cost, price);
                t1Profit = Math.max(t1Profit, price - t1Cost);
                // reinvest the gained profit in the second transaction
                t2Cost = Math.min(t2Cost, price - t1Profit);
                t2Profit = Math.max(t2Profit, price - t2Cost);
            }

            return t2Profit;
        }


}
