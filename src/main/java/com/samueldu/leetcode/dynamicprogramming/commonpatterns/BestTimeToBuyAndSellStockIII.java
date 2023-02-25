package com.samueldu.leetcode.dynamicprogramming.commonpatterns;

/**
 * Best Time to Buy and Sell Stock III
 * Hard
 *
 * 7106
 *
 * 140
 *
 * Add to List
 *
 * Share
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * Find the maximum profit you can achieve. You may complete at most two transactions.
 *
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 *
 *
 * Example 1:
 *
 * Input: prices = [3,3,5,0,0,3,1,4]
 * Output: 6
 * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 * Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
 * Example 2:
 *
 * Input: prices = [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
 * Example 3:
 *
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 *
 *
 * Constraints:
 *
 * 1 <= prices.length <= 105
 * 0 <= prices[i] <= 105
 */
public class BestTimeToBuyAndSellStockIII {
    /**
     * Bidirectional Dynamic Programming
     * Intuition
     *
     * The only difference between this problem and the previous two problems is that in this problem we are allowed to make at most two transactions.
     *
     * Additionally, there is a constraint on the order of transactions stated in the problem description as follows:
     *
     * You may not engage in multiple transactions at the same time, (i.e. you must sell the stock before you buy again).
     *
     * We could interpret this constraint as that there would be no overlapping in the sequence of transactions.
     *
     * order of transaction actions
     *
     * In other words, the two transactions that we should make would situate in two different subsequences of the stock prices, without any overlapping, which we illustrate in the above graph.
     *
     * That being said, we can solve the problem in a divide-and-conquer manner, where we divide the original sequence of prices into two subsequences and then we calculate the maximum profit that we could gain from making a single transaction in each subsequence.
     *
     * The total profits would be the sum of profits from each subsequence. If we enumerate all possible divisions (or we could consider them as combinations of subsequences), we could find the maximum total profits among them, which is also the desired result of the problem.
     *
     * visual of partitions on sequence
     *
     * So we divide this problem into two subproblems, and each subproblem is actually of the same problem of Best Time to Buy and Sell Stock as we discussed in the overview section.
     *
     * Algorithm
     *
     * A naive implementation of the above idea would be to divide the sequences into two and then enumerate each of the subsequences, though this is definitely not the most optimized solution.
     *
     * For a sequence of length NN, we would have NN possible divisions (including no division), each of the elements would be visited once in each division. As a result, the overall time complexity of this naive implementation would be \mathcal{O}(N^2)O(N
     * 2
     *  ).
     *
     * We could do better than the naive \mathcal{O}(N^2)O(N
     * 2
     *  ) implementation. Regarding the algorithms of divide-and-conquer, one common technique that we can apply in order to optimize the time complexity is called dynamic programming (DP) where we trade less repetitive calculation with some extra space.
     *
     * In dynamic programming algorithms, normally we create an array of one or two dimensions to keep the intermediate optimal results. In this problem though, we would use two arrays, with one array keeping the results of sequence from left to right and the other array keeping the results of sequence from right to left. For the sake of name, we could call it bidirectional dynamic programming.
     *
     * visualization of dp arrays
     *
     * First, we denote a sequence of prices as Prices[i], with index starting from 0 to N-1. Then we define two arrays, namely left_profits[i] and right_profits[i].
     *
     * As suggested by the name, each element in the left_profits[i] array would hold the maximum profits that one can gain from doing one single transaction on the left subsequence of prices from the index zero to i, (i.e. Prices[0], Prices[1], ... Prices[i]). For instance, for the subsequences of [7, 1, 5], the corresponding left_profits[2] would be 4, which is to buy the price of 1 and sell it at the price of 5.
     *
     * And each element in the right_profits[i] array would hold the maximum profits that one can gain from doing one single transaction on the right subsequence of the prices from the index i up to N-1, (i.e. Prices[i], Prices[i+1], ... Prices[N-1]). For example, for the right subsequence of [3, 6, 4], the corresponding right_profits[3] would be 3, which is to buy at the price of 3 and then sell it at the price of 6.
     *
     * Now, if we divide the sequence of prices around the element at the index i, into two subsequences, with left subsequences as Prices[0], Prices[1], ... Prices[i] and the right subsequence as Prices[i+1], ... Prices[N-1], then the total maximum profits that we obtain from this division (denoted as max_profits[i]) can be expressed as follows: \text{max\_profits[i]} = \text{left\_profits[i]} + \text{right\_profits[i+1]}max_profits[i]=left_profits[i]+right_profits[i+1]
     *
     * Then if we exhaust all possible divisions, i.e. we place the two transactions in all possible combinations of subsequences, we would then obtain the global maximum profits that we could gain from this sequence of stock prices, which can be expressed as follows: \max_{i=[0, N)}{(\text{max\_profits[i]})}max
     * i=[0,N)
     * ​
     *  (max_profits[i])
     *
     * We demonstrate how the DP arrays are calculated in the following animation.
     *
     * Current
     * 9 / 9
     * Following the above idea, Here are some sample implementations.
     *
     * @param prices
     * @return
     *
     * In the above implementations, we refined the code a bit to make it a bit more concise and hopefully more intuitive. Here are some tweaks that we applied.
     *
     * Rather than constructing the two DP arrays in two separate loops, we do the calculation in a single loop (two birds with one stone).
     *
     * We pad the right_profits[i] array with an additional zero, which indicates the maximum profits that we can gain from an empty right subsequence, so that we can compare the result of having only one transaction (i.e. left_profits[N-1]) with the profits gained from doing two transactions.
     *
     * By the way, one can try the above algorithm on another problem called Sliding Window Maximum.
     *
     * Complexity
     *
     * Time Complexity: \mathcal{O}(N)O(N) where NN is the length of the input sequence, since we have two iterations of length NN.
     *
     * Space Complexity: \mathcal{O}(N)O(N) for the two arrays that we keep in the algorithm.
     */
        public int maxProfit(int[] prices) {
            int length = prices.length;
            if (length <= 1) return 0;

            int leftMin = prices[0];
            int rightMax = prices[length - 1];

            int[] leftProfits = new int[length];
            // pad the right DP array with an additional zero for convenience.
            int[] rightProfits = new int[length + 1];

            // construct the bidirectional DP array
            for (int l = 1; l < length; ++l) {
                leftProfits[l] = Math.max(leftProfits[l - 1], prices[l] - leftMin);
                leftMin = Math.min(leftMin, prices[l]);

                int r = length - 1 - l;
                rightProfits[r] = Math.max(rightProfits[r + 1], rightMax - prices[r]);
                rightMax = Math.max(rightMax, prices[r]);
            }

            int maxProfit = 0;
            for (int i = 0; i < length; ++i) {
                maxProfit = Math.max(maxProfit, leftProfits[i] + rightProfits[i + 1]);
            }
            return maxProfit;
        }

}
