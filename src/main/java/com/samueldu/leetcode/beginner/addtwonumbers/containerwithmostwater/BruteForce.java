package com.samueldu.leetcode.beginner.addtwonumbers.containerwithmostwater;

/**
 * You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
 *
 * Find two lines that together with the x-axis form a container, such that the container contains the most water.
 *
 * Return the maximum amount of water a container can store.
 */
public class BruteForce {


    public int maxArea(int[] height) {
        int maxArea=0;
        for (int left=0; left<height.length; left++){
            for (int right =left+1; right<height.length; right++){
                int width=right-left;
                maxArea=Math.max(maxArea, Math.min(height[left], height[right])*width);
            }
        }
        return maxArea;
    }
}

/**
 * Time complexity: O(n^2)O(n^2).
 * Calculating area for all n(n-1)/2  height pairs.
 * Space complexity: O(1)O(1). Constant extra space is used.
 */
