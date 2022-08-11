package com.samueldu.leetcode.beginner.addtwonumbers.longestStringWithoutRepeating;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s, find the length of the longest substring without repeating characters.
 */
public class BruteForceSolution {
    public static void main(String [] args){
       BruteForceSolution s = new BruteForceSolution();

       int l = s.lengthOfLongestSubstring("abcb");
       System.out.println(l);
    }
    public int lengthOfLongestSubstring(String s) {
        int max =0;
        if (s.length()==0) return max;
        for (int i =0; i<s.length(); i++){
            Set<Character> characters = new HashSet<Character>();
            for (int j=i;j<s.length();j++){
                if(noDuplicatedCharacter(s, i, j)) max=Math.max(max, j-i+1);
            }
            if(max<characters.size()){
                max=characters.size();
            }
        }
        return max;
    }

    private boolean noDuplicatedCharacter(String s , int start, int end){
        Set<Character> h = new HashSet<Character>();
        for (int i=start; i<=end ; i++){
            Character c = s.charAt(i);
            boolean added =h.add(c);
            if(!added)
                return false;

        }
        return true;
    }
}
