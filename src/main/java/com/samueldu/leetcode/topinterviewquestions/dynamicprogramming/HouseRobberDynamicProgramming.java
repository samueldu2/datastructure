package com.samueldu.leetcode.topinterviewquestions.dynamicprogramming;

public class HouseRobberDynamicProgramming {
    /**
     * Approach 2: Dynamic Programming
     * Intuition
     *
     * The idea here is the same as before except that instead of following a recursive approach, we will be sticking with a tabular approach. The recursive approach may run into trouble when the recursion stack grows too large. It may also run into trouble because, for each recursive call, the compiler must do additional work to maintain the call stack (function variables, etc.) which results in unwanted overhead. The dynamic programming approach is simply a tabular formulation of the ideas presented above.
     *
     * The cache we had before will still exist in this approach but instead of calling it a cache, we will refer to it as our dynamic programming table. Every DP solution has a table that we populate starting with the base case or the simplest of cases for which we already know the answer. E.g. for our problem, we know that in the absence of houses, the robber will make 0 profit. Similarly, if there is just one house left to rob, the robber will rob that house, and that will be the maximum profit.
     *
     * We start by populating the dynamic programming table with these initial values and then build the table in a bottom-up fashion which is the essence of this solution. Let's look at the algorithm to formalize this idea.
     *
     * Algorithm
     *
     * We define a table which we will use to store the results of our sub-problems. Let's call this table maxRobbedAmount where maxRobbedAmount[i] is the same value that would be returned by recurse(i) in the previous solution.
     * We set maxRobbedAmount[N] to 0 since this means the robber doesn't have any houses left to rob, thus zero profit. Additionally, we set maxRobbedAmount[N - 1] to nums[N - 1] because in this case, there is only one house to rob which is the last house. Robbing it will yield the maximum profit.
     * We iterate from N - 2 down to 0 and we set maxRobbedAmount[i] = max(maxRobbedAmount[i + 1], maxRobbedAmount[i + 2] + nums[i]). Note that this is the same as the recursive formulation in the previous solution. The only difference is that we have already calculated the solutions to the sub-problems and we simply reuse the solutions in O(1) time when calculating the solution to the main problem.
     * We return the value in maxRobbedAmount[0].
     * Implementation
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(N)O(N) since we have a loop from N - 2 \cdots 0N−2⋯0 and we simply use the pre-calculated values of our dynamic programming table for calculating the current value in the table which is a constant time operation.
     *
     * Space Complexity: O(N)O(N) which is used by the table. So what is the real advantage of this solution over the previous solution? In this case, we don't have a recursion stack. When the number of houses is large, a recursion stack can become a serious limitation, because the recursion stack size will be huge and the compiler will eventually run into stack-overflow problems (no pun intended!).
     * @param nums
     * @return
     */
    public int rob(int[] nums) {

        int N = nums.length;

        // Special handling for empty array case.
        if (N == 0) {
            return 0;
        }

        int[] maxRobbedAmount = new int[nums.length + 1];

        // Base case initializations.
        maxRobbedAmount[N] = 0;
        maxRobbedAmount[N - 1] = nums[N - 1];

        // DP table calculations.
        for (int i = N - 2; i >= 0; --i) {

            // Same as the recursive solution.
            maxRobbedAmount[i] = Math.max(maxRobbedAmount[i + 1], maxRobbedAmount[i + 2] + nums[i]);
        }

        return maxRobbedAmount[0];
    }
}
