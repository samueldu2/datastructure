package com.samueldu.leetcode.topinterviewquestions.dynamicprogramming;

import java.util.Arrays;

/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * Example 2:
 *
 * Input: nums = [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 */
public class HouseRobberRecursive {
/**
 * Approach 1: Recursion with Memoization
 * Intuition
 *
 * As we mentioned above, the easiest approach here is to try all possible combinations of house choices and then use the combination that gives the maximum amount of money to the robber. We do this because there is no plausible greedy strategy that we can use to decide if the robber should rob a particular house or not.
 *
 * We rely on our good friend recursion whenever we have choices involved in solving a problem. Technically, a robber can come back and rob a house that they previously rejected. However, since we are trying all options, we will not go back and rob an unrobbed house since that scenario will be covered in a different recursive path.
 *
 * The basic choice that we make is whether to rob the current house or not. If the robber decides to rob the current house, they have to skip the next house. Otherwise, they can evaluate the same choice on the next house i.e. to rob or not to rob.
 *
 * Subproblems
 *
 * To approach a problem recursively, we need to make sure that it can be broken down into sub-problems. Additionally, we need to ensure that the optimal solution to these sub-problems can be used to form the solution to the main problem. Let's see how we can divide this problem into smaller recursive problems.
 *
 * Let's say that we have a function called robFrom which we will use to solve this problem. The only input this function takes is an index, position. This position essentially represents a suffix in the array which we, the robber, have yet to scan. Essentially, the position indicates that the robber has yet to scan houses [\text{position}, \cdots, N][position,⋯,N] where NN represents the total number of houses.
 *
 * Naturally, the answer to our problem would be the function call robFrom(0) which means scan all the houses and return the maximum profit. Now let's think about robFrom(i) for a moment. This simply represents a sub-array or a suffix from the main array. We can think about this as a smaller max-profit problem in itself, can't we?
 *
 * A suffix of the initial set of houses simply means a smaller set of houses that the robber has to consider. We will be working with the assumption that in the function call robFrom(i), the robber has to maximize their profit from i..N houses.
 *
 * At each step, the robber has two options. If he chooses to rob the current house, he will have to skip the next house on the list by moving two steps forward. If he chooses not to rob the current house, he can simply move on to the next house in the list. Let's see this mathematically.
 *
 * \text{robFrom}(i) = \text{max}(\text{robFrom}(i + 1), \text{robFrom}(i + 2) + \text{nums}(i))robFrom(i)=max(robFrom(i+1),robFrom(i+2)+nums(i))
 *
 * Recursion tree and memoization
 *
 * Now that we have an idea about the recursive structure of our problem, let's look at the recursion tree which will be formed. This is important so that we can determine if we have repeating sub-problems, in which case we can use memoization or caching to reduce the overall solution complexity.
 *
 * overlapping subproblems in the recursion tree
 *
 * Figure 4. Overlapping sub-problems in the recursion tree.
 *
 * As we can see in the recursion tree above, we have the repeating sub-problems (nodes) marked in the same color. A repeating node in the tree represents an entire subtree calculation that has to be repeated which is computationally expensive. Hence, we cache the already computed results so that we don't need to re-calculate the maximum profit for previously seen sub-problems.
 *
 * Let's formalize this idea concretely in the algorithm section below.
 *
 * Algorithm
 *
 * We define a function called robFrom() which takes the index of the house that the robber currently has to examine. Also, this function takes the nums array which is required during the calculations.
 * At each step of our recursive call, the robber has two options. He can either rob the current house or not.
 * If the robber chooses to rob the current house, then he have to skip the next house i.e robFrom(i + 2, nums). The answer here would be whatever is returned by robFrom(i + 2, nums) in addition to the amount that the robber will get by robbing the current house i.e. nums[i].
 * Otherwise, he can simply move on to the house next door and return whatever profit he will make in the sub-problem i.e. robFrom(i + 1, nums).
 * We need to find, cache, and return the maximum of these two options at each step.
 * robFrom(0, nums) will give us the answer to the entire problem.
 * Implementation
 *
 *
 * Complexity Analysis
 *
 * Time Complexity: O(N)O(N) since we process at most NN recursive calls, thanks to caching, and during each of these calls, we make an O(1)O(1) computation which is simply making two other recursive calls, finding their maximum, and populating the cache based on that.
 *
 * Space Complexity: O(N)O(N) which is occupied by the cache and also by the recursion stack.
 */
private int[] memo;

    public int rob(int[] nums) {

        this.memo = new int[100];

        // Fill with sentinel value representing not-calculated recursions.
        Arrays.fill(this.memo, -1);

        return this.robFrom(0, nums);
    }

    private int robFrom(int i, int[] nums) {

        // No more houses left to examine.
        if (i >= nums.length) {
            return 0;
        }

        // Return cached value.
        if (this.memo[i] > -1) {
            return this.memo[i];
        }

        // Recursive relation evaluation to get the optimal answer.
        int ans = Math.max(this.robFrom(i + 1, nums), this.robFrom(i + 2, nums) + nums[i]);

        // Cache for future use.
        this.memo[i] = ans;
        return ans;
    }
}
