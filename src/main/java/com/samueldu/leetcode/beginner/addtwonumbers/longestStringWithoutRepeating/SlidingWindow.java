package com.samueldu.leetcode.beginner.addtwonumbers.longestStringWithoutRepeating;

public class SlidingWindow {
    public static void main(String[] args){

    }

    public int lengthOfLongestSubstring(String s) {
        int [] chars = new int[128];//hold the count of each appearance

        int left=0;
        int right =0;

        int res=0;

        while (right<s.length()){
            char r = s.charAt(right);
            chars[r]++;

            while (chars[r]>1){//s.charAt(right) is duplicated. need to contract the window.
                char l = s.charAt(left);
                chars[l]--;
                left++;
            }

            res=Math.max(res, right-left+1);

            right++;
        }
        return res;
    }

}
