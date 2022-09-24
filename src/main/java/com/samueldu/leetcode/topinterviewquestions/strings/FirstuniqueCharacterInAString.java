package com.samueldu.leetcode.topinterviewquestions.strings;

import java.util.HashMap;

/**
 * Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "leetcode"
 * Output: 0
 * Example 2:
 *
 * Input: s = "loveleetcode"
 * Output: 2
 * Example 3:
 *
 * Input: s = "aabb"
 * Output: -1
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s consists of only lowercase English letters.
 */
public class FirstuniqueCharacterInAString {

    /**
     * Approach 1: Linear time solution
     * The best possible solution here could be of a linear time because to ensure that the character is unique you have to check the whole string anyway.
     *
     * The idea is to go through the string and save in a hash map the number of times each character appears in the string. That would take \mathcal{O}(N)O(N) time, where N is a number of characters in the string.
     *
     * And then we go through the string the second time, this time we use the hash map as a reference to check if a character is unique or not.
     * If the character is unique, one could just return its index. The complexity of the second iteration is \mathcal{O}(N)O(N) as well.
     *
     * Complexity Analysis
     *
     * Time complexity : \mathcal{O}(N)O(N) since we go through the string of length N two times.
     * Space complexity : \mathcal{O}(1)O(1) because English alphabet contains 26 letters.
     */
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> count = new HashMap<Character, Integer>();
        int n = s.length();
        // build hash map : character and how often it appears
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        // find the index
        for (int i = 0; i < n; i++) {
            if (count.get(s.charAt(i)) == 1)
                return i;
        }
        return -1;
    }
}
