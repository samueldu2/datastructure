package com.samueldu.leetcode.topinterviewquestions.strings;

/**
 *A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric characters include letters and numbers.
 *
 * Given a string s, return true if it is a palindrome, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "A man, a plan, a canal: Panama"
 * Output: true
 * Explanation: "amanaplanacanalpanama" is a palindrome.
 * Example 2:
 *
 * Input: s = "race a car"
 * Output: false
 * Explanation: "raceacar" is not a palindrome.
 * Example 3:
 *
 * Input: s = " "
 * Output: true
 * Explanation: s is an empty string "" after removing non-alphanumeric characters.
 * Since an empty string reads the same forward and backward, it is a palindrome.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 2 * 105
 * s consists only of printable ASCII characters.
 */
public class ValidPalindrome {
    /**
     * Approach 1: Compare with Reverse
     * Intuition
     *
     * A palindrome is a word, phrase, or sequence that reads the same backwards as forwards. e.g. madam
     *
     * A palindrome, and its reverse, are identical to each other.
     *
     * Algorithm
     *
     * We'll reverse the given string and compare it with the original. If those are equivalent, it's a palindrome.
     *
     * Since only alphanumeric characters are considered, we'll filter out all other types of characters before we apply our algorithm.
     *
     * Additionally, because we're treating letters as case-insensitive, we'll convert the remaining letters to lower case. The digits will be left the same.
     *
     *
     * Complexity Analysis
     *
     * Time complexity : O(n)O(n), in length nn of the string.
     *
     * We need to iterate thrice through the string:
     *
     * When we filter out non-alphanumeric characters, and convert the remaining characters to lower-case.
     * When we reverse the string.
     * When we compare the original and the reversed strings.
     * Each iteration runs linear in time (since each character operation completes in constant time). Thus, the effective run-time complexity is linear.
     *
     * Space complexity : O(n)O(n), in length nn of the string. We need O(n)O(n) additional space to stored the filtered string and the reversed string.
     */

        public boolean isPalindrome(String s) {
            StringBuilder builder = new StringBuilder();

            for (char ch : s.toCharArray()) {
                if (Character.isLetterOrDigit(ch)) {
                    builder.append(Character.toLowerCase(ch));
                }
            }

            String filteredString = builder.toString();
            String reversedString = builder.reverse().toString();

            return filteredString.equals(reversedString);
        }

        /** An alternate solution using Java 8 Streams */
        public boolean isPalindromeUsingStreams(String s) {
            StringBuilder builder = new StringBuilder();

            s.chars()
                    .filter(c -> Character.isLetterOrDigit(c))
                    .mapToObj(c -> Character.toLowerCase((char) c))
                    .forEach(builder::append);

            return builder.toString().equals(builder.reverse().toString());
        }

    /**
     * going from outwards to middle.
     *Time complexity : O(n)O(n), in length nn of the string. We traverse over each character at-most once, until the two pointers meet in the middle, or when we break and return early.
     *
     * Space complexity : O(1)O(1). No extra space required, at all.
     *
     */
    public boolean isPalindrome2(String s) {
        for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
            while (i < j && !Character.isLetterOrDigit(s.charAt(i))) {
                i++;
            }
            while (i < j && !Character.isLetterOrDigit(s.charAt(j))) {
                j--;
            }

            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j)))
                return false;
        }

        return true;
    }

}
