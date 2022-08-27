package com.samueldu.leetcode.studyplan.leetcode75.level3.tree;

import java.util.HashMap;

/**
 * Prefix Sum: How to Use: Number of Continuous Subarrays that Sum to Target
 * You might want to use the prefix sum technique for the problems like "Find a number of continuous subarrays/submatrices/tree paths that sum to target".
 *
 * Before going to the current problem with the tree, let's check the idea on a simpler example Find a number of continuous subarrays that sum to target.
 *
 * Use a variable to track the current prefix sum and a hashmap "prefix sum -> how many times was it seen so far".
 * append Figure 4. Find a number of continuous subarrays that sum to target.
 *
 * Parse the input structure and count the requested subarrays/submatrices/tree paths along the way with the help of that hashmap. How to count?
 * There could be two situations. In situation 1, the subarray with the target sum starts from the beginning of the array. That means that the current prefix sum is equal to the target sum, and we increase the counter by 1.
 *
 * append Figure 5. Situation 1: The subarray starts from the beginning of the array.
 *
 * In situation 2, the subarray with the target sum starts somewhere in the middle. That means we should add to the counter the number of times we have seen the prefix sum curr_sum - target so far: count += h[curr_sum - target].
 *
 * The logic is simple: the current prefix sum is curr_sum, and some elements before the prefix sum was curr_sum - target. All the elements in between sum up to curr_sum - (curr_sum - target) = target.
 */
public class NumberofContinuousSubarraysThatSumtoTarget {
    /**
     *
     * @param nums
     * @param k: the target.
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0, currSum = 0;
        HashMap<Integer, Integer> h = new HashMap();

        for (int num : nums) {
            // current prefix sum
            currSum += num;

            // situation 1:
            // continuous subarray starts
            // from the beginning of the array
            if (currSum == k)
                count++;

            // situation 2:
            // number of times the curr_sum − k has occured already,
            // determines the number of times a subarray with sum k
            // has occured up to the current index
            count += h.getOrDefault(currSum - k, 0);

            // add the current sum
            h.put(currSum, h.getOrDefault(currSum, 0) + 1);
        }

        return count;
    }
}
