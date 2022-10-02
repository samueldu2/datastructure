package com.samueldu.leetcode.dynamicprogramming.commonpatterns;

public class ConChangeDynamicProgrammingTopDown {

    /**
     * Approach 2 (Dynamic programming - Top down) [Accepted]
     * Intuition
     * Could we improve the exponential solution above? Definitely! The problem could be solved with polynomial time using Dynamic programming technique. First, let's define:
     *
     * F(S)F(S) - minimum number of coins needed to make change for amount SS using coin denominations [{c_0\ldots c_{n-1}}][c
     * 0
     * ​
     *  …c
     * n−1
     * ​
     *  ]
     *
     * We note that this problem has an optimal substructure property, which is the key piece in solving any Dynamic Programming problems. In other words, the optimal solution can be constructed from optimal solutions of its subproblems. How to split the problem into subproblems? Let's assume that we know F(S)F(S) where some change val_1, val_2, \ldotsval
     * 1
     * ​
     *  ,val
     * 2
     * ​
     *  ,… for SS which is optimal and the last coin's denomination is CC. Then the following equation should be true because of optimal substructure of the problem:
     *
     * F(S) = F(S - C) + 1F(S)=F(S−C)+1
     *
     * But we don't know which is the denomination of the last coin CC. We compute F(S - c_i)F(S−c
     * i
     * ​
     *  ) for each possible denomination c_0, c_1, c_2 \ldots c_{n -1}c
     * 0
     * ​
     *  ,c
     * 1
     * ​
     *  ,c
     * 2
     * ​
     *  …c
     * n−1
     * ​
     *   and choose the minimum among them. The following recurrence relation holds:
     *
     * F(S) = \min_{i=0 ... n-1} { F(S - c_i) } + 1 \\ \text{subject to} \ S-c_i \geq 0 \\F(S)=min
     * i=0...n−1
     * ​
     *  F(S−c
     * i
     * ​
     *  )+1
     * subject to S−c
     * i
     * ​
     *  ≥0
     *
     * F(S) = 0 , \text{when} S = 0 \\ F(S) = -1 , \text{when} n = 0F(S)=0,whenS=0
     * F(S)=−1,whenn=0
     *
     * Recursion tree for finding coin change of amount 6 with coin denominations {1,2,3}.
     *
     * In the recursion tree above, we could see that a lot of subproblems were calculated multiple times. For example the problem F(1)F(1) was calculated 1313 times. Therefore we should cache the solutions to the subproblems in a table and access them in constant time when necessary
     *
     * Algorithm
     * The idea of the algorithm is to build the solution of the problem from top to bottom. It applies the idea described above. It use backtracking and cut the partial solutions in the recursive tree, which doesn't lead to a viable solution. Тhis happens when we try to make a change of a coin with a value greater than the amount SS. To improve time complexity we should store the solutions of the already calculated subproblems in a table.
     *
     * Implementation
     *
     * Complexity Analysis
     * Time complexity : O(S*n)O(S∗n). where S is the amount, n is denomination count. In the worst case the recursive tree of the algorithm has height of SS and the algorithm solves only SS subproblems because it caches precalculated solutions in a table. Each subproblem is computed with nn iterations, one by coin denomination. Therefore there is O(S*n)O(S∗n) time complexity.
     *
     * Space complexity : O(S)O(S), where SS is the amount to change We use extra space for the memoization table.
     * @param coins
     * @param amount
     * @return
     */
        public int coinChange(int[] coins, int amount) {
            if (amount < 1) return 0;
            return coinChange(coins, amount, new int[amount]);
        }

        private int coinChange(int[] coins, int rem, int[] count) {
            if (rem < 0) return -1;
            if (rem == 0) return 0;
            // indexed from 0, so count[rem-1] is for the rem-th entry.
            if (count[rem - 1] != 0) return count[rem - 1];
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                int res = coinChange(coins, rem - coin, count);
                if (res >= 0 && res < min)
                    min = 1 + res;
            }
            count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
            return count[rem - 1];
        }


}
