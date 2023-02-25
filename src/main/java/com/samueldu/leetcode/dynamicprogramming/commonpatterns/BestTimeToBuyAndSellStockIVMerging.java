package com.samueldu.leetcode.dynamicprogramming.commonpatterns;

import java.util.ArrayList;

public class BestTimeToBuyAndSellStockIVMerging {
/**
 * Merging
 * Intuition
 *
 * This approach starts from a simple situation with k=infinity, and drecrease k one by one.
 *
 * Consider a weakened problem when k=infinity. Since we already know the prices of tomorrow, our solution is to trade whenever prices[i-1] < prices[i]. Below is an example.
 *
 * k = inf
 *
 * We only used 4 transactions! However, what we need to solve is the case with an actual k. Let's decrease k from inf and see what happens. Our solution can handle all the k >=4, since we only used 4 transactions. But what if k=3?
 *
 * Notice that at day 5, we buy and sell the stock at the same time. We can cancel the redundant transaction without impact the final profit!
 *
 * k = 3
 *
 * We can conclude that for the consecutively increasing subsequence, we only need to buy once at the start and sell once at the end.
 *
 * How about k=2? Maybe we need to delete one transaction. We can iterate all the transactions and delete the one with least revenue. However, deleting can not always achieve our best solution. Consider the following example:
 *
 * delete?
 *
 * When k=2, the best solution is to buy at day 1 and day 9, and to sell on day 6 and day 10. Deleting any transactions cannot reach this solution. However, we can merge the previous two transactions to get to this. A naive approach is iterating all the near transactions and find out the pair with the lowest impact on the revenue. Since we decrease k one by one, reducing one transaction is enough. Ok, let's go to the algorithm part to check the detail.
 *
 * Algorithm
 *
 * The general idea is to store all consecutively increasing subsequence as the initial solution. Then delete or merge transactions until the number of transactions less than or equal to k.
 *
 * Complexity
 *
 * Time Complexity: \mathcal{O}(n(n-k))O(n(n−k)) if 2k \le n2k≤n , \mathcal{O}(n)O(n) if 2k > n2k>n, where nn is the length of the price sequence. The maximum size of transactions is \mathcal{O}(n)O(n), and we need \mathcal{O}(n-k)O(n−k) iterations.
 *
 * Space Complexity: \mathcal{O}(n)O(n), since we need a list to store transactions.
 */
public int maxProfit(int k, int[] prices) {
    int n = prices.length;

    // solve special cases
    if (n <= 0 || k <= 0) {
        return 0;
    }

    // find all consecutively increasing subsequence
    ArrayList<int[]> transactions = new ArrayList<>();
    int start = 0;
    int end = 0;
    for (int i = 1; i < n; i++) {
        if (prices[i] >= prices[i - 1]) {
            end = i;
        } else {
            if (end > start) {
                int[] t = { start, end };
                transactions.add(t);
            }
            start = i;
        }
    }
    if (end > start) {
        int[] t = { start, end };
        transactions.add(t);
    }

    while (transactions.size() > k) {
        // check delete loss
        int delete_index = 0;
        int min_delete_loss = Integer.MAX_VALUE;
        for (int i = 0; i < transactions.size(); i++) {
            int[] t = transactions.get(i);
            int profit_loss = prices[t[1]] - prices[t[0]];
            if (profit_loss < min_delete_loss) {
                min_delete_loss = profit_loss;
                delete_index = i;
            }
        }

        // check merge loss
        int merge_index = 0;
        int min_merge_loss = Integer.MAX_VALUE;
        for (int i = 1; i < transactions.size(); i++) {
            int[] t1 = transactions.get(i - 1);
            int[] t2 = transactions.get(i);
            int profit_loss = prices[t1[1]] - prices[t2[0]];
            if (profit_loss < min_merge_loss) {
                min_merge_loss = profit_loss;
                merge_index = i;
            }
        }

        // delete or merge
        if (min_delete_loss <= min_merge_loss) {
            transactions.remove(delete_index);
        } else {
            int[] t1 = transactions.get(merge_index - 1);
            int[] t2 = transactions.get(merge_index);
            t1[1] = t2[1];
            transactions.remove(merge_index);
        }

    }

    int res = 0;
    for (int[] t : transactions) {
        res += prices[t[1]] - prices[t[0]];
    }

    return res;
}

}
