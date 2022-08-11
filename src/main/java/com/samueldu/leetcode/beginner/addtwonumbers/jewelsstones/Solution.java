package com.samueldu.leetcode.beginner.addtwonumbers.jewelsstones;

public class Solution {

    public static void main(String [] args){
        Solution s= new Solution();
        System.out.println(s.numJewelsInStones("aA", "aAAbbb"));
    }
        public int numJewelsInStones(String jewels, String stones) {
            int [] jewelInt = new int[124];
            char[] jewel= jewels.toCharArray();
            for (char c : jewel){
                jewelInt[c]=1;
            }
            char[] st=stones.toCharArray();
            int jewelCount=0;
            for(char c: st){
                if(jewelInt[c]==1)
                    jewelCount++;
            }
            return jewelCount;

        }

}
