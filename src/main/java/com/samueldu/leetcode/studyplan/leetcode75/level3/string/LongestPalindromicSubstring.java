package com.samueldu.leetcode.studyplan.leetcode75.level3.string;

public class LongestPalindromicSubstring {

    /**
     * Approach 4: Expand Around Center
     * In fact, we could solve it in O(n^2)O(n
     * 2
     *  ) time using only constant space.
     *
     * We observe that a palindrome mirrors around its center. Therefore, a palindrome can be expanded from its center, and there are only 2n - 12n−1 such centers.
     *
     * You might be asking why there are 2n - 12n−1 but not nn centers? The reason is the center of a palindrome can be in between two letters. Such palindromes have even number of letters (such as "abba") and its center are between the two 'b's.
     *
     *
     * Complexity Analysis
     *
     * Time complexity : O(n^2)O(n
     * 2
     *  ). Since expanding a palindrome around its center could take O(n)O(n) time, the overall complexity is O(n^2)O(n
     * 2
     *  ).
     *
     * Space complexity : O(1)O(1).
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

}
