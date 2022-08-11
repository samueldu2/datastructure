package com.samueldu.leetcode.beginner.addtwonumbers.twosum;

import java.util.HashMap;
import java.util.Map;

public class Solution {

    /**
     * Time: O(n)
     * Space: O(n)
     */
    public int [] twoSum(int[] nums, int target){
        Map<Integer, Integer> map = new HashMap();
        int curr;
        for (int i=0; i<nums.length; i++){
            curr=nums[i];
            int needed = target-curr;
            if (map.containsKey(needed)) {
                return new int[]{map.get(needed), i};
            }
            map.put(curr, i);
        }
        return null;
    }

    /**
     * Time: O(n^2)
     * Space O(1)
     */
    public int[] twoSumBruteForce(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[] { i, j };
                }
            }
        }
        // In case there is no solution, we'll just return null
        return null;
    }
}
