package com.samueldu.leetcode.beginner.addtwonumbers.zigzagconversion;

import java.util.ArrayList;
import java.util.List;

/**
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 *
 * Write the code that will take a string and make this conversion given a number of rows:
 *
 * string convert(string s, int numRows);
 */
public class Workout {

    /**
     * Intuition
     *
     * By iterating through the string from left to right, we can easily determine which row in the Zig-Zag pattern that a character belongs to.
     *
     * Algorithm
     *
     * We can use \text{min}( \text{numRows}, \text{len}(s))min(numRows,len(s)) lists to represent the non-empty rows of the Zig-Zag Pattern.
     *
     * Iterate through ss from left to right, appending each character to the appropriate row. The appropriate row can be tracked using two variables: the current row and the current direction.
     *
     * The current direction changes only when we moved up to the topmost row or moved down to the bottommost row.
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {

        if (numRows == 1) return s;

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++)
            rows.add(new StringBuilder());

        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) ret.append(row);
        return ret.toString();
    }

}

/**
 * Complexity Analysis
 *
 * Time Complexity: O(n)O(n), where n == \text{len}(s)n==len(s)
 * Space Complexity: O(n)O(n)
 */
