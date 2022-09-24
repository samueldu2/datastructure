package com.samueldu.leetcode.topinterviewquestions.design;

import java.util.HashMap;
import java.util.Map;

public class RomanToIntegerImproved {


    /**
     * Approach 2: Left-to-Right Pass Improved
     * Intuition
     *
     * Instead of viewing a Roman Numeral as having 7 unique symbols, we could instead view it as having 13 unique symbols—some of length-1 and some of length-2.
     *
     * Symbol mapping
     *
     * For example, here is the Roman Numeral MMCMLXXXIX broken into its symbols using this definition:
     *
     * Splitting the numeral into parts
     *
     * We can then look up the value of each symbol and add them together.
     *
     * Adding up the sum of the numeral
     *
     * After making a Map of String -> Integer with the 13 "symbols", we need to work our way down the string in the same way as before (we'll do left-to-right, however right-to-left will work okay too), firstly checking if we're at a length-2 symbol, and if not, then treating it as a length-1 symbol.
     *
     * total = 0
     * i = 0
     * while i < s.length:
     *     if at least 2 characters remaining and s.substing(i, i + 1) is in values:
     *         total = total + (value of s.substring(i, i + 1))
     *         i = i + 2
     *     else:
     *         total = total + (value of s[i])
     *         i = i + 1
     * return total
     * Here is an animation showing the algorithm.
     *
     * Current
     * 1 / 8
     * Algorithm
     *
     *
     * Complexity Analysis
     *
     * Time complexity : O(1)O(1).
     *
     * Same as Approach 1.
     *
     * Space complexity : O(1)O(1).
     *
     * Same as Approach 1.
     */




    static Map<String, Integer> values = new HashMap<>();

    static {
        values.put("I", 1);
        values.put("V", 5);
        values.put("X", 10);
        values.put("L", 50);
        values.put("C", 100);
        values.put("D", 500);
        values.put("M", 1000);
        values.put("IV", 4);
        values.put("IX", 9);
        values.put("XL", 40);
        values.put("XC", 90);
        values.put("CD", 400);
        values.put("CM", 900);
    }

    public int romanToInt(String s) {

        int sum = 0;
        int i = 0;
        while (i < s.length()) {
            if (i < s.length() - 1) {
                String doubleSymbol = s.substring(i, i + 2);
                // Check if this is the length-2 symbol case.
                if (values.containsKey(doubleSymbol)) {
                    sum += values.get(doubleSymbol);
                    i += 2;
                    continue;
                }
            }
            // Otherwise, it must be the length-1 symbol case.
            String singleSymbol = s.substring(i, i + 1);
            sum += values.get(singleSymbol);
            i += 1;
        }
        return sum;
    }


}
