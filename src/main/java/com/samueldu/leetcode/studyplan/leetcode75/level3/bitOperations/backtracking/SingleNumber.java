package com.samueldu.leetcode.studyplan.leetcode75.level3.bitOperations.backtracking;

import java.util.Arrays;
import java.util.HashSet;


/**
 * Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
 *
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,2,1]
 * Output: 1
 * Example 2:
 *
 * Input: nums = [4,1,2,1,2]
 * Output: 4
 * Example 3:
 *
 * Input: nums = [1]
 * Output: 1
 */
public class SingleNumber {

    public int singleNumberRegular(int[] nums) {
        HashSet<Integer> h = new HashSet<>();
        for (int n:nums){
            Integer i = n;
            if(h.contains(i)){
                h.remove (i);
            }else{
                h.add(i);
            }
        }
        return h.iterator().next();
    }

    /**
     * Approach 4: Bit Manipulation
     * Concept
     *
     * If we take XOR of zero and some bit, it will return that bit
     * a⊕0=a
     * If we take XOR of two same bits, it will return 0
     * a⊕a=0
     * a⊕b⊕a=(a⊕a)⊕b=0⊕b=b
     * So we can XOR all bits together to find the unique number.
     *
     * XOR table is:
     * 0⊕0=0
     * 0⊕1=1
     * 1⊕0=1
     * 1⊕1=0
     */
    public int singleNumber(int[] nums){
        int a=0;
        for (int i:nums){
            a^=i;
        }
        return a;
    }
}
