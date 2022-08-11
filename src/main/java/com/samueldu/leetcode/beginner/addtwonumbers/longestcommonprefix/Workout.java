package com.samueldu.leetcode.beginner.addtwonumbers.longestcommonprefix;

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 *
 * If there is no common prefix, return an empty string "".
 *
 * Example 1:
 *
 * Input: strs = ["flower","flow","flight"]
 * Output: "fl"
 * Example 2:
 *
 * Input: strs = ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 */
public class Workout {

    public static void main(String [] args){
        Workout w = new Workout();
        System.out.println(w.longestCommonPrefix(new String []{"abc", "abd", "abe"}));
        System.out.println(w.longestCommonPrefix2(new String []{"abc", "abd", "abe"}));
    }
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++)
            while (strs[i].indexOf(prefix) != 0) {
                //reduce prefix one at a time until returning 0.
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        return prefix;
    }

    /**
     * vertical scanning
     */

    public String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        for (int i = 0; i < strs[0].length() ; i++){
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j ++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c)
                    return strs[0].substring(0, i);
            }
        }
        return strs[0];
    }
}



/**
 * Time complexity : O(S)O(S) , where S is the sum of all characters in all strings.
 *
 * In the worst case all nn strings are the same. The algorithm compares the string S1S1 with the other strings [S_2 \ldots S_n][S
 * 2
 * ​
 *  …S
 * n
 * ​
 *  ] There are SS character comparisons, where SS is the sum of all characters in the input array.
 *
 * Space complexity : O(1)O(1). We only used constant extra space.
 */
