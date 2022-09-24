package com.samueldu.leetcode.topinterviewquestions.dynamicprogramming;

/**
 * Maximum Subarray
 *
 * Solution
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 *
 * A subarray is a contiguous part of an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * Example 2:
 *
 * Input: nums = [1]
 * Output: 1
 * Example 3:
 *
 * Input: nums = [5,4,-1,7,8]
 * Output: 23
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 *
 *
 * Follow up: If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */
public class MaximumSubarrayBruteForce {
/**
 * Approach 1: Optimized Brute Force
 * Intuition
 *
 * This algorithm doesn't reliably run under the time limit here on LeetCode. We'll still look briefly at it though, as in an interview scenario it would be a great start if you're struggling to come up with a better approach.
 *
 * Calculate the sum of all subarrays, and keep track of the best one. To actually generate all subarrays would take O(N^3)O(N
 * 3
 *  ) time, but with a little optimization, we can achieve brute force in O(N^2)O(N
 * 2
 *  ) time. The trick is to recognize that all of the subarrays starting at a particular value will share a common prefix.
 *
 * Algorithm
 *
 * Initialize a variable maxSubarray = -infinity to keep track of the best subarray. We need to use negative infinity, not 0, because it is possible that there are only negative numbers in the array.
 *
 * Use a for loop that considers each index of the array as a starting point.
 *
 * For each starting point, create a variable currentSubarray = 0. Then, loop through the array from the starting index, adding each element to currentSubarray. Every time we add an element it represents a possible subarray - so continuously update maxSubarray to contain the maximum out of the currentSubarray and itself.
 *
 * Return maxSubarray.
 *
 * Implementation
 *
 *
 * Complexity Analysis
 *
 * Time complexity: O(N^2)O(N
 * 2
 *  ), where NN is the length of nums.
 *
 * We use 2 nested for loops, with each loop iterating through nums.
 *
 * Space complexity: O(1)O(1)
 *
 * No matter how big the input is, we are only ever using 2 variables: ans and currentSubarray.
 */

    public int maxSubArray(int[] nums) {
        int maxSubarray = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int currentSubarray = 0;
            for (int j = i; j < nums.length; j++) {
                currentSubarray += nums[j];
                maxSubarray = Math.max(maxSubarray, currentSubarray);
            }
        }

        return maxSubarray;
    }

}
