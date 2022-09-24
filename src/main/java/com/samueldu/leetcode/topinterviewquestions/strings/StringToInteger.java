package com.samueldu.leetcode.topinterviewquestions.strings;

/**
 * Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer (similar to C/C++'s atoi function).
 *
 * The algorithm for myAtoi(string s) is as follows:
 *
 * Read in and ignore any leading whitespace.
 * Check if the next character (if not already at the end of the string) is '-' or '+'. Read this character in if it is either. This determines if the final result is negative or positive respectively. Assume the result is positive if neither is present.
 * Read in next the characters until the next non-digit character or the end of the input is reached. The rest of the string is ignored.
 * Convert these digits into an integer (i.e. "123" -> 123, "0032" -> 32). If no digits were read, then the integer is 0. Change the sign as necessary (from step 2).
 * If the integer is out of the 32-bit signed integer range [-231, 231 - 1], then clamp the integer so that it remains in the range. Specifically, integers less than -231 should be clamped to -231, and integers greater than 231 - 1 should be clamped to 231 - 1.
 * Return the integer as the final result.
 * Note:
 *
 * Only the space character ' ' is considered a whitespace character.
 * Do not ignore any characters other than the leading whitespace or the rest of the string after the digits.
 *
 *
 * Example 1:
 *
 * Input: s = "42"
 * Output: 42
 * Explanation: The underlined characters are what is read in, the caret is the current reader position.
 * Step 1: "42" (no characters read because there is no leading whitespace)
 *          ^
 * Step 2: "42" (no characters read because there is neither a '-' nor '+')
 *          ^
 * Step 3: "42" ("42" is read in)
 *            ^
 * The parsed integer is 42.
 * Since 42 is in the range [-231, 231 - 1], the final result is 42.
 * Example 2:
 *
 * Input: s = "   -42"
 * Output: -42
 * Explanation:
 * Step 1: "   -42" (leading whitespace is read and ignored)
 *             ^
 * Step 2: "   -42" ('-' is read, so the result should be negative)
 *              ^
 * Step 3: "   -42" ("42" is read in)
 *                ^
 * The parsed integer is -42.
 * Since -42 is in the range [-231, 231 - 1], the final result is -42.
 * Example 3:
 *
 * Input: s = "4193 with words"
 * Output: 4193
 * Explanation:
 * Step 1: "4193 with words" (no characters read because there is no leading whitespace)
 *          ^
 * Step 2: "4193 with words" (no characters read because there is neither a '-' nor '+')
 *          ^
 * Step 3: "4193 with words" ("4193" is read in; reading stops because the next character is a non-digit)
 *              ^
 * The parsed integer is 4193.
 * Since 4193 is in the range [-231, 231 - 1], the final result is 4193.
 *
 *
 * Constraints:
 *
 * 0 <= s.length <= 200
 * s consists of English letters (lower-case and upper-case), digits (0-9), ' ', '+', '-', and '.'.
 */
public class StringToInteger {
    /**
     *Approach 1: Follow the Rules
     * Intuition
     *
     * Given the rules outlined by the problem's description, we can iterate over the input string and use the given rules to validate it.
     *
     * First read through the problem statement very carefully. Let's see what are all the possible characters in the input string:
     *
     * Whitespaces (' ')
     * Digits ('0', '1', '2', '3', '4', '5', '6', '7', '8', '9')
     * A sign ('+' or '-')
     * Anything else (alphabetic characters, symbols, special characters, etc.)
     * And write down all the rules for building the integer for each one these characters which will help us in writing down the conditions while building the algorithm.
     *
     * Rules
     *
     * Whitespaces:
     * If any whitespaces occur at the beginning of the input string, we discard them.
     * However, if whitespace occurs anywhere else in the input, then we stop and discard the rest of the input.
     * '   1234' => 1234 (whitespaces at beginning are removed)
     * '      4' => 4    (whitespaces at beginning are removed)
     * ' 12   4' => 12   (only the leading whitespaces are removed)
     *
     * Digits:
     * Discard any leading zeros.
     * Read in all the digit characters until the first non-digit character or the end of the input occur and append those to the output number.
     * If no digits were found, return 0.
     * '12345 567 v' => 12345 (digits are appended until a non-digit character occurs)
     * '00123'       => 00123 => 123 (0s in the beginning of the numbers are discarded)
     *
     * Sign:
     * There could be at most one sign character presented at the beginning, or after skipping some whitespaces from the beginning of the input string. Otherwise, a sign anywhere else in the input string is not valid and is considered a non-digit character and we stop building the integer.
     * If a '+' or no sign is present, the final number will be a positive integer. On the other hand, the final number will be negative if '-' is the first non-whitespace character in the string.
     * '123'  => 123 (a number with no sign is a positive number)
     * '+123' => 123 (a number with '+' sign is a positive number)
     * '-12'  => -12 (a number with '-' sign is a negative number)
     * '-+12' => 0   (another sign after one sign is considered as non-digit character)
     *
     * Anything else:
     * If any other character not covered by previously defined rules is spotted, we stop building the output number.
     * '-23a45 567 v' => -23 (we stopped when 'a' character occured)
     * '123 45 567 v' => 123 (we stopped when a space character occured)
     * 'a+123 bcd 45' => 0   (we stopped when 'a' character occured in the beginning)
     *
     * 32-bit Integer Range:
     * If the integer exceeds 2^{31}-12
     * 31
     *  −1 then it will be clamped to 2^{31}-12
     * 31
     *  −1.
     * And if the integer becomes less than -2^{31}−2
     * 31
     *   then it will be clamped to -2^{31}−2
     * 31
     *  .
     * '12345' => 12345 (integer is in 32-bit range)
     * '9999999999999' => 2^31-1 (integer overflow)
     * '-999999999999' => -2^31   (integer underflow)
     *
     * How to check overflows/underflows?
     *
     * If we were using a long, BigInteger, or any other numeric data types to store the integers, we could check if the integer exceeds the 32-bit range, stop building the output number, and return the clamped value.
     *
     * num = num * 10 + digit
     * if num > 2^31 - 1 then return 2^31 - 1
     * else if num < -2^31 then return -2^31
     * However, here we will assume our environment doesn't allow us to use these data types which could be a constraint imposed by the interviewer. But we can't directly use a 32-bit integer to store the final result.
     *
     * For example, assume currently result is 1000000000 and digit is 1, we can't append the current digit to result as 10000000001 is more than 2^{31} - 12
     * 31
     *  −1. So, performing the result = result * 10 + digit operation will result in Runtime Error.
     *
     * Hence, first we need to check if appending the digit to the result is safe or not. If it is safe to append then update the result. Otherwise, handle the overflow/underflow.
     *
     * Let's first consider the case for overflow.
     *
     * We will denote the maximum 32-bit integer value 2^{31} - 1 \space = \space 21474836472
     * 31
     *  −1 = 2147483647 with INT_MAX, and we will append the digits one by one to the final number.
     *
     * So there could be 3 cases:
     *
     * Case 1: If the current number is less than INT_MAX / 10 = 214748364, we can append any digit, and the new number will always be less than INT_MAX.
     * '214748363' (less than INT_MAX / 10) + '0' = '2147483630' (less than INT_MAX)
     * '214748363' (less than INT_MAX / 10) + '9' = '2147483639' (less than INT_MAX)
     * '1' (less than INT_MAX / 10) + '9' = '19' (less than INT_MAX)
     * Case 2: If the current number is more than INT_MAX / 10 = 214748364, appending any digit will result in a number greater than INT_MAX.
     * '214748365' + '0' = '2147483650' (more than INT_MAX)
     * '214748365' + '9' = '2147483659' (more than INT_MAX)
     * '2147483646' + '8' = '21474836468' (more than INT_MAX)
     * Case 3: If the current number is equal to INT_MAX / 10 = 214748364, we can only append digits from 0-7 such that the new number will always be less than or equal to INT_MAX.
     * '214748364' + '0' = '2147483640' (which is less than INT_MAX)
     * '214748364' + '7' = '2147483647' (which is equal to INT_MAX)
     * '214748364' + '8' = '2147483648' (which is more than INT_MAX)
     *
     * Similarly for underflow.
     * The minimum 32-bit integer value is -2^{31} \space = \space -2147483648−2
     * 31
     *   = −2147483648 denote it with INT_MIN.
     *
     * We append the digits one by one to the final number.
     * Just like before, there could be 3 cases:
     *
     * Case 1: If the current number is greater than INT_MIN / 10 = -214748364, then we can append any digit and the new number will always be greater than INT_MIN.
     *
     * Case 2: If the current number is less than INT_MIN / 10 = -214748364, appending any digit will result in a number less than INT_MIN.
     *
     * Case 3: If the current number is equal to INT_MIN / 10 = -214748364, then we can only append digits from 0-8, such that the new number will always be greater than or equal to INT_MIN.
     * Notice that cases 1 and 2 are similar for overflow and underflow. The only difference is case 3: for overflow, we can append any digit between 0 and 7, but for underflow, we can append any digit between 0 and 8.
     *
     * So we can combine both the underflow and overflow checks as follows:
     *
     * Initially, store the sign for the final result and consider only the absolute values to build the integer and return the final result with a correct sign at the end.
     * If the current number is less than 214748364 = (INT_MAX / 10), we can append the next digit.
     * If the current number is greater than 214748364:
     * And, the sign for the result is '+', then the result will overflow, so return INT_MAX;
     * Or, the sign for the result is '-', then the result will underflow, so return INT_MIN.
     * If the current number is equal to 214748364:
     * If the next digit is between 0-7, the result will always be in range.
     * If, next digit is 8:
     * and the sign is '+' the result will overflow, so return INT_MAX;
     * if the sign is '-', the result will not underflow but will still be equal to INT_MIN, so that we can return INT_MIN.
     * But if, the next digit is greater than 8:
     * and the sign is '+' the result will overflow, so return INT_MAX;
     * if the sign is '-', the result will underflow, so return INT_MIN.
     * Note: We do not need to handle 0-7 for positive and 0-8 for negative integers separately. If the sign is negative and the current number is 214748364, then appending the digit 8, which is more than 7, will also lead to the same result, i.e., INT_MIN.
     *
     * Complexity Analysis
     *
     * If NN is the number of characters in the input string.
     *
     * Time complexity: O(N)O(N)
     *
     * We visit each character in the input at most once and for each character we spend a constant amount of time.
     *
     * Space complexity: O(1)O(1)
     *
     * We have used only constant space to store the sign and the result.
     *Complexity Analysis
     *
     * If NN is the number of characters in the input string.
     *
     * Time complexity: O(N)O(N)
     *
     * We iterate over the input string exactly once, and each state transition only requires constant time.
     *
     * Space complexity: O(1)O(1)
     *
     * We have used only constant space to store the state, sign, and result.
     */
        public int myAtoi(String input) {
            int sign = 1;
            int result = 0;
            int index = 0;
            int n = input.length();

            // Discard all spaces from the beginning of the input string.
            while (index < n && input.charAt(index) == ' ') {
                index++;
            }

            // sign = +1, if it's positive number, otherwise sign = -1.
            if (index < n && input.charAt(index) == '+') {
                sign = 1;
                index++;
            } else if (index < n && input.charAt(index) == '-') {
                sign = -1;
                index++;
            }

            // Traverse next digits of input and stop if it is not a digit
            while (index < n && Character.isDigit(input.charAt(index))) {
                int digit = input.charAt(index) - '0';

                // Check overflow and underflow conditions.
                if ((result > Integer.MAX_VALUE / 10) ||
                        (result == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)) {
                    // If integer overflowed return 2^31-1, otherwise if underflowed return -2^31.
                    return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }

                // Append current digit to the result.
                result = 10 * result + digit;
                index++;
            }

            // We have formed a valid number without any overflow/underflow.
            // Return it after multiplying it with its sign.
            return sign * result;
        }

    /**
     * state machine
     */
    enum State { q0, q1, q2, qd }

    class StateMachine {
        // Store current state value.
        private State currentState;
        // Store result formed and it's sign.
        private int result, sign;

        public StateMachine() {
            currentState = State.q0;
            result = 0;
            sign = 1;
        }

        // Transition to state q1.
        private void toStateQ1(char ch) {
            sign = (ch == '-') ? -1 : 1;
            currentState = State.q1;
        }

        // Transition to state q2.
        private void toStateQ2(int digit) {
            currentState = State.q2;
            appendDigit(digit);
        }

        // Transition to dead state qd.
        private void toStateQd() {
            currentState = State.qd;
        }

        // Append digit to result, if out of range return clamped value.
        private void appendDigit(int digit) {
            if ((result > Integer.MAX_VALUE / 10) ||
                    (result == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)) {
                if (sign == 1) {
                    // If sign is 1, clamp result to Integer.MAX_VALUE.
                    result = Integer.MAX_VALUE;
                } else {
                    // If sign is -1, clamp result to Integer.MIN_VALUE.
                    result = Integer.MIN_VALUE;
                    sign = 1;
                }

                // When the 32-bit int range is exceeded, a dead state is reached.
                toStateQd();
            } else {
                // Append current digit to the result.
                result = result * 10 + digit;
            }
        }

        // Change state based on current input character.
        public void transition(char ch) {
            if (currentState == State.q0) {
                // Beginning state of the string (or some whitespaces are skipped).
                if (ch == ' ') {
                    // Current character is a whitespaces.
                    // We stay in same state.
                    return;
                } else if (ch == '-' || ch == '+') {
                    // Current character is a sign.
                    toStateQ1(ch);
                } else if (Character.isDigit(ch)) {
                    // Current character is a digit.
                    toStateQ2(ch - '0');
                } else {
                    // Current character is not a space/sign/digit.
                    // Reached a dead state.
                    toStateQd();
                }
            } else if (currentState == State.q1 || currentState == State.q2) {
                // Previous character was a sign or digit.
                if (Character.isDigit(ch)) {
                    // Current character is a digit.
                    toStateQ2(ch - '0');
                } else {
                    // Current character is not a digit.
                    // Reached a dead state.
                    toStateQd();
                }
            }
        }

        // Return the final result formed with it's sign.
        public int getInteger() {
            return sign * result;
        }

        // Get current state.
        public State getState() {
            return currentState;
        }
    };


        public int myAtoiUsingStateMachine(String s) {
            StateMachine Q = new StateMachine();

            for (int i = 0; i < s.length() && Q.getState() != State.qd; ++i) {
                Q.transition(s.charAt(i));
            }

            return Q.getInteger();
        }

}
