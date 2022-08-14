package com.samueldu.leetcode.studyplan.leetcode75.level1;

/**
 * Given an array nums. We define a running sum of an array as runningSum[i] = sum(nums[0]…nums[i]).
 *
 * Return the running sum of nums.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4]
 * Output: [1,3,6,10]
 * Explanation: Running sum is obtained as follows: [1, 1+2, 1+2+3, 1+2+3+4].
 * Example 2:
 *
 * Input: nums = [1,1,1,1,1]
 * Output: [1,2,3,4,5]
 * Explanation: Running sum is obtained as follows: [1, 1+1, 1+1+1, 1+1+1+1, 1+1+1+1+1].
 * Example 3:
 *
 * Input: nums = [3,1,2,10,1]
 * Output: [3,4,6,16,17]
 */
public class RunningSumOf1dArray {
    public int[] runningSum(int[] nums) {
        int[] result = new int[nums.length];
        result[0]=nums[0];
        for(int i=1; i<nums.length; i++){
            result[i]=nums[i]+result[i-1];
        }
        return result;
    }
}
