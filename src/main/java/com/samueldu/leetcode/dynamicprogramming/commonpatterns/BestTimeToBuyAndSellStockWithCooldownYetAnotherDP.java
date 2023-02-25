package com.samueldu.leetcode.dynamicprogramming.commonpatterns;

/**
 * Best Time to Buy and Sell Stock with Cooldown
 *
 * Solution
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:
 *
 * After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 *
 *
 * Example 1:
 *
 * Input: prices = [1,2,3,0,2]
 * Output: 3
 * Explanation: transactions = [buy, sell, cooldown, buy, sell]
 * Example 2:
 *
 * Input: prices = [1]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= prices.length <= 5000
 * 0 <= prices[i] <= 1000
 */
public class BestTimeToBuyAndSellStockWithCooldownYetAnotherDP {
    /**
     *
     Approach 2: Yet-Another Dynamic Programming
     Intuition

     Most of the times, there are more than one approaches to decompose the problem, so that we could apply the technique of dynamic programming.

     Here we would like to propose a different perspective on how to model the problem purely with mathematical formulas.

     Again, this would be a journey loaded with mathematical notations, which might be complicated, but it showcases how the mathematics could help one with the dynamic programming (pun intended).

     Definition

     For a sequence of prices, denoted as \text{price}[0, 1, ..., n]price[0,1,...,n], let us first define our target function called \text{MP}(i)MP(i). The function \text{MP}(i)MP(i) gives the maximal profits that we can gain for the price subsequence starting from the index ii, i.e. \text{price}[i, i+1, ..., n]price[i,i+1,...,n].

     Given the definition of the \text{MP}(i)MP(i) function, one can see that when i=0i=0 the output of the function, i.e. \text{MP}(0)MP(0), is exactly the result that we need to solve the problem, which is the maximal profits that one can gain for the price subsequence of \text{price}[0, 1, ..., n]price[0,1,...,n].

     Suppose that we know all the values for \text{MP}(i)MP(i) onwards until \text{MP}(n)MP(n), i.e. we know the maximal profits that we can gain for any subsequence of \text{price}[k...n] \quad k \in [i, n]price[k...n]k∈[i,n].

     Now, let us add a new price point \text{price}[i-1]price[i−1] into the subsequence \text{price}[i...n]price[i...n], all we need to do is to deduce the value for the unknown \text{MP}(i-1)MP(i−1).

     Up to this point, we have just modeled the problem with our target function \text{MP}(i)MP(i), along with a series of definitions. The problem now is boiled down to deducing the formula for \text{MP}(i-1)MP(i−1).

     In the following section, we will demonstrate how to deduce the formula for \text{MP}(i-1)MP(i−1).

     Deduction

     With the newly-added price point \text{price}[i-1]price[i−1], we need to consider all possible transactions that we can do to the stock at this price point, which can be broken down into two cases:

     Case 1): we buy this stock with \text{price}[i-1]price[i−1] and then sell it at some point in the following price sequence of \text{price}[i...n]price[i...n]. Note that, once we sell the stock at a certain point, we need to cool down for a day, then we can reengage with further transactions. Suppose that we sell the stock right after we bought it, at the next price point \text{price}[i]price[i], the maximal profits we would gain from this choice would be the profit of this transaction (i.e. \text{price}[i] - \text{price}[i-1]price[i]−price[i−1]) plus the maximal profits from the rest of the price sequence, as we show in the following:
     example of profit calculation

     In addition, we need to enumerate all possible points to sell this stock, and take the maximum among them. The maximal profits that we could gain from this case can be represented by the following:

     C_1 = \max_{\{k \in [i, n]\}}\big( \text{price}[k] - \text{p}[i-1] + \text{MP}(k+2) \big)C
     1
     ​
     =max
     {k∈[i,n]}
     ​
     (price[k]−p[i−1]+MP(k+2))

     Case 2): we simply do nothing with this stock. Then the maximal profits that we can gain from this case would be \text{MP}(i)MP(i), which are also the maximal profits that we can gain from the rest of the price sequence.
     C_2 = \text{MP}(i)C
     2
     ​
     =MP(i)

     By combining the above two cases, i.e. selecting the max value among them, we can obtain the value for \text{MP}(i-1)MP(i−1), as follows:

     \text{MP}(i-1) = \max(C_1, C_2)MP(i−1)=max(C
     1
     ​
     ,C
     2
     ​
     )

     \text{MP}(i-1) = \max\Big(\max_{\{k \in [i, n]\}}\big( \text{price}[k] - \text{price}[i-1] + \text{MP}(k+2) \big), \quad \text{MP}(i) \Big)MP(i−1)=max(max
     {k∈[i,n]}
     ​
     (price[k]−price[i−1]+MP(k+2)),MP(i))

     By the way, the base case for our recursive function \text{MP}(i)MP(i) would be \text{MP}(n)MP(n) which is the maximal profits that we can gain from the sequence with a single price point \text{price}[n]price[n]. And the best thing we should do with a single price point is to do no transaction, hence we would neither lose money nor gain any profit, i.e. \text{MP}(n) = 0MP(n)=0.

     The above formulas do model the problem soundly. In addition, one should be able to translate them directly into code.

     Algorithm

     With the final formula we derived for our target function \text{MP}(i)MP(i), we can now go ahead and translate it into any programming language.

     Since the formula deals with subsequences of price that start from the last price point, we then could do an iteration over the price list in the reversed order.

     We define an array MP[i] to hold the values for our target function \text{MP}(i)MP(i). We initialize the array with zeros, which correspond to the base case where the minimal profits that we can gain is zero. Note that, here we did a trick to pad the array with two additional elements, which is intended to simplify the branching conditions, as one will see later.

     To calculate the value for each element MP[i], we need to look into two cases as we discussed in the previous section, namely:

     Case 1). we buy the stock at the price point price[i], then we sell it at a later point. As one might notice, the initial padding on the MP[i] array saves us from getting out of boundary in the array.

     Case 2). we do no transaction with the stock at the price point price[i].

     At the end of each iteration, we then pick the largest value from the above two cases as the final value for MP[i].

     At the end of the loop, the MP[i] array will be populated. We then return the value of MP[0], which is the desired solution for the problem.
     *
     * Complexity Analysis
     *
     * Time Complexity: \mathcal{O}(N^2)O(N
     * 2
     *  ) where NN is the length of the price list.
     *
     * As one can see, we have nested loops over the price list. The number of iterations in the outer loop is NN. The number of iterations in the inner loop varies from 11 to NN. Therefore, the total number of iterations that we perform is \sum_{i=1}^{N} i = \frac{N\cdot(N+1)}{2}∑
     * i=1
     * N
     * ​
     *  i=
     * 2
     * N⋅(N+1)
     * ​
     *  .
     *
     * As a result, the overall time complexity of the algorithm is \mathcal{O}(N^2)O(N
     * 2
     *  ).
     *
     * Space Complexity: \mathcal{O}(N)O(N) where NN is the length of the price list.
     *
     * We allocated an array to hold all the values for our target function \text{MP}(i)MP(i).
     */
    public int maxProfit(int[] prices) {
        int[] MP = new int[prices.length + 2];

        for (int i = prices.length - 1; i >= 0; i--) {
            int C1 = 0;
            // Case 1). buy and sell the stock
            for (int sell = i + 1; sell < prices.length; sell++) {
                int profit = (prices[sell] - prices[i]) + MP[sell + 2];
                C1 = Math.max(profit, C1);
            }

            // Case 2). do no transaction with the stock p[i]
            int C2 = MP[i + 1];

            // wrap up the two cases
            MP[i] = Math.max(C1, C2);
        }
        return MP[0];
    }

}
