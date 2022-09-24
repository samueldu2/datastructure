package com.samueldu.leetcode.topinterviewquestions.strings;

import java.util.Arrays;

/**
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 * Example 2:
 *
 * Input: s = "rat", t = "car"
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= s.length, t.length <= 5 * 104
 * s and t consist of lowercase English letters.
 */
public class ValidAnagram {
/**
 * Approach 1: Sorting
 * Algorithm
 *
 * An anagram is produced by rearranging the letters of ss into tt. Therefore, if tt is an anagram of ss, sorting both strings will result in two identical strings. Furthermore, if ss and tt have different lengths, tt must not be an anagram of ss and we can return early.
 *
 *
 * Complexity Analysis
 *
 * Time complexity: O(n \log n)O(nlogn). Assume that nn is the length of ss, sorting costs O(n \log n)O(nlogn) and comparing two strings costs O(n)O(n). Sorting time dominates and the overall time complexity is O(n \log n)O(nlogn).
 *
 * Space complexity: O(1)O(1). Space depends on the sorting implementation which, usually, costs O(1)O(1) auxiliary space if heapsort is used. Note that in Java, toCharArray() makes a copy of the string so it costs O(n)O(n) extra space, but we ignore this for complexity analysis because:
 *
 * It is a language dependent detail.
 * It depends on how the function is designed. For example, the function parameter types can be changed to char[].
 */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }

    /**
     * Approach 2: Frequency Counter
     * Algorithm
     *
     * To examine if tt is a rearrangement of ss, we can count occurrences of each letter in the two strings and compare them. We could use a hash table to count the frequency of each letter, however, since both ss and tt only contain letters from aa to zz, a simple array of size 26 will suffice.
     *
     * Do we need two counters for comparison? Actually no, because we can increment the count for each letter in ss and decrement the count for each letter in tt, and then check if the count for every character is zero.
     *
     *
     * Or we could first increment the counter for ss, then decrement the counter for tt. If at any point the counter drops below zero, we know that tt contains an extra letter not in ss and return false immediately.
     *
     * Complexity Analysis
     *
     * Time complexity: O(n)O(n). Time complexity is O(n)O(n) because accessing the counter table is a constant time operation.
     *
     * Space complexity: O(1)O(1). Although we do use extra space, the space complexity is O(1)O(1) because the table's size stays constant no matter how large nn is.
     */
    public boolean isAnagramUsingFrequencyCounter(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] table = new int[26];
        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            table[t.charAt(i) - 'a']--;
            if (table[t.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

}
