package com.samueldu.leetcode.dynamicprogramming.commonpatterns;

import java.util.Arrays;

/**
 * Given an integer array nums, return the length of the longest strictly increasing subsequence.
 *
 * A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 * Example 2:
 *
 * Input: nums = [0,1,0,3,2,3]
 * Output: 4
 * Example 3:
 *
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2500
 * -104 <= nums[i] <= 104
 */
public class LongestIncreasingSubSequence {
    /**
     *  Dynamic Programming
     * Realizing a Dynamic Programming Problem
     *
     * This problem has two important attributes that let us know it should be solved by dynamic programming. First, the question is asking for the maximum or minimum of something. Second, we have to make decisions that may depend on previously made decisions, which is very typical of a problem involving subsequences.
     *
     * As we go through the input, each "decision" we must make is simple: is it worth it to consider this number? If we use a number, it may contribute towards an increasing subsequence, but it may also eliminate larger elements that came before it. For example, let's say we have nums = [5, 6, 7, 8, 1, 2, 3]. It isn't worth using the 1, 2, or 3, since using any of them would eliminate 5, 6, 7, and 8, which form the longest increasing subsequence. We can use dynamic programming to determine whether an element is worth using or not.
     *
     * A Framework to Solve Dynamic Programming Problems
     *
     * Typically, dynamic programming problems can be solved with three main components. If you're new to dynamic programming, this might be hard to understand but is extremely valuable to learn since most dynamic programming problems can be solved this way.
     *
     * First, we need some function or array that represents the answer to the problem from a given state. For many solutions on LeetCode, you will see this function/array named "dp". For this problem, let's say that we have an array dp. As just stated, this array needs to represent the answer to the problem for a given state, so let's say that dp[i] represents the length of the longest increasing subsequence that ends with the i^{th}i
     * th
     *   element. The "state" is one-dimensional since it can be represented with only one variable - the index i.
     *
     * Second, we need a way to transition between states, such as dp[5] and dp[7]. This is called a recurrence relation and can sometimes be tricky to figure out. Let's say we know dp[0], dp[1], and dp[2]. How can we find dp[3] given this information? Well, since dp[2] represents the length of the longest increasing subsequence that ends with nums[2], if nums[3] > nums[2], then we can simply take the subsequence ending at i = 2 and append nums[3] to it, increasing the length by 1. The same can be said for nums[0] and nums[1] if nums[3] is larger. Of course, we should try to maximize dp[3], so we need to check all 3. Formally, the recurrence relation is: dp[i] = max(dp[j] + 1) for all j where nums[j] < nums[i] and j < i.
     *
     * The third component is the simplest: we need a base case. For this problem, we can initialize every element of dp to 1, since every element on its own is technically an increasing subsequence.
     *
     * Algorithm
     *
     * Initialize an array dp with length nums.length and all elements equal to 1. dp[i] represents the length of the longest increasing subsequence that ends with the element at index i.
     *
     * Iterate from i = 1 to i = nums.length - 1. At each iteration, use a second for loop to iterate from j = 0 to j = i - 1 (all the elements before i). For each element before i, check if that element is smaller than nums[i]. If so, set dp[i] = max(dp[i], dp[j] + 1).
     *
     * Return the max value from dp.
     *
     * Given NN as the length of nums,
     *
     * Time complexity: O(N^2)O(N
     * 2
     *  )
     *
     * We use two nested for loops resulting in 1 + 2 + 3 + 4 + ... + N = \dfrac {N * (N + 1)}{2}1+2+3+4+...+N=
     * 2
     * N∗(N+1)
     * ​
     *   operations, resulting in a time complexity of O(N^2)O(N
     * 2
     *  ).
     *
     * Space complexity: O(N)O(N)
     *
     * The only extra space we use relative to input size is the dp array, which is the same length as nums.
     */

    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int longest = 0;
        for (int c: dp) {
            longest = Math.max(longest, c);
        }

        return longest;
    }
}
