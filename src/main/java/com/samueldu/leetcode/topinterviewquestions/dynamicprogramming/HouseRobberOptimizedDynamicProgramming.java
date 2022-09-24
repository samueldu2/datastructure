package com.samueldu.leetcode.topinterviewquestions.dynamicprogramming;

/**
 * Approach 3: Optimized Dynamic Programming
 * Intuition
 *
 * This is the exact same solution as the previous one with the exception that we will be optimizing the space complexity here. Let's look at the recursive relation again to see where we can reduce the amount of space used.
 *
 * \text{robFrom}(i) = \text{max}(\text{robFrom}(i + 1), \text{robFrom}(i + 2) + \text{nums}(i))robFrom(i)=max(robFrom(i+1),robFrom(i+2)+nums(i))
 *
 * To calculate the current value, we just need to rely on the next two consecutive values/recursive calls.
 *
 * Porting this idea over to our dynamic programming solution we see that in order to calculate the value at a current index in the dynamic programming table, we simply need to know the next two values i.e. maxRobbedAmount[i + 1] and maxRobbedAmount[i + 2]. In the end we will return the value of maxRobbedAmount[0].
 *
 * Instead of keeping an entire table for storing these cached values, we can get by with simply keeping track of the "next" two values.
 *
 * Let's see this more concretely in the algorithm section.
 *
 * Algorithm
 *
 * We will make use of two variables here called robNext and robNextPlusOne which at any point will represent the optimal solution for maxRobbedAmount[i + 1] and maxRobbedAmount[i + 2]. These are the two values that we need to calculate the current value.
 *
 * We set robNextPlusOne to 0 since this means the robber doesn't have any houses left to rob, thus zero profit. Additionally, we set robNext to nums[N - 1] because in this case there is only one house to rob which is the last house. Robbing it will yield the maximum profit.
 *
 * Note We are assuming that robNextPlusOne is the value of maxRobbedAmount[N] and robNext is maxRobbedAmount[N-1] initially.
 *
 * We iterate from N - 2 down to 0 and set current = max(robNext, robNextPlusOne + nums[i]). Note that this is the same as the dynamic programming solution except that we are making use of our variables and not entries from the table.
 *
 * Set robNextPlusOne = robNext.
 *
 * Set robNext = current. Updating the two variables is important as we iterate down to 0.
 *
 * We return the value in robNext.
 *
 * Time Complexity
 *
 * Time Complexity: O(N)O(N) since we have a loop from N - 2 \cdots 0N−2⋯0 and we use the precalculated values of our dynamic programming table to calculate the current value in the table which is a constant time operation.
 *
 * Space Complexity: O(1)O(1) since we are not using a table to store our values. Simply using two variables will suffice for our calculations.
 */
public class HouseRobberOptimizedDynamicProgramming {

        public int rob(int[] nums) {

            int N = nums.length;

            // Special handling for empty array case.
            if (N == 0) {
                return 0;
            }

            int robNext, robNextPlusOne;

            // Base case initializations.
            robNextPlusOne = 0;
            robNext= nums[N - 1];

            // DP table calculations. Note: we are not using any
            // table here for storing values. Just using two
            // variables will suffice.
            for (int i = N - 2; i >= 0; --i) {

                // Same as the recursive solution.
                int current = Math.max(robNext, robNextPlusOne + nums[i]);

                // Update the variables
                robNextPlusOne = robNext;
                robNext = current;
            }

            return robNext;
        }


}
