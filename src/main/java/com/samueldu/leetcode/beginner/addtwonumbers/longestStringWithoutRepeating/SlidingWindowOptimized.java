package com.samueldu.leetcode.beginner.addtwonumbers.longestStringWithoutRepeating;

public class SlidingWindowOptimized {
    public static void main(String [] args){
        BruteForceSolution s = new BruteForceSolution();

        int l = s.lengthOfLongestSubstring("abcb");
        System.out.println(l);
    }
    public int lengthOfLongestSubstring(String s) {
        Integer [] chars = new Integer[128];//stores the index of the characters.

        int left=0;
        int right =0;

        int res=0;

        while (right<s.length()){
            char r = s.charAt(right);

            Integer index = chars[r];

            //if index !=null, then char r appeared before at the location index, so we advance left pointer to pass previous occurance.
            if(index!=null && index >=left && index<right)
                left=index+1;

            //now the substring from left pointer to right pointer is sure to have no duplicated characters.
            res=Math.max(res, right-left+1);

            //we set the index value of the character r.
            chars[r]=right;

            right++;
        }
        return res;
    }
}

// time complexity : O(n)
// space complexity: O(m), where the m is the size of the table Integer [] chars.