package com.samueldu.graphtransversal.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3
 * Output: ["((()))","(()())","(())()","()(())","()()()"]
 * Example 2:
 *
 * Input: n = 1
 * Output: ["()"]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 8
 */
public class GenerateParenthesis {
    /**
     * back tracking
     * Approach 2: Backtracking
     * Intuition and Algorithm
     * <p>
     * Instead of adding '(' or ')' every time as in Approach 1, let's only add them when we know it will remain a valid sequence. We can do this by keeping track of the number of opening and closing brackets we have placed so far.
     * <p>
     * We can start an opening bracket if we still have one (of n) left to place. And we can start a closing bracket if it would not exceed the number of opening brackets.
     * Complexity Analysis
     * <p>
     * Our complexity analysis rests on understanding how many elements there are in generateParenthesis(n). This analysis is outside the scope of this article, but it turns out this is the n-th Catalan number \dfrac{1}{n+1}\binom{2n}{n}
     * n+1
     * 1
     * ​
     * (
     * n
     * 2n
     * ​
     * ), which is bounded asymptotically by \dfrac{4^n}{n\sqrt{n}}
     * n
     * n
     * ​
     * <p>
     * 4
     * n
     * <p>
     * ​
     * .
     * <p>
     * Time Complexity : O(\dfrac{4^n}{\sqrt{n}})O(
     * n
     * ​
     * <p>
     * 4
     * n
     * <p>
     * ​
     * ). Each valid sequence has at most n steps during the backtracking procedure.
     * <p>
     * Space Complexity : O(\dfrac{4^n}{\sqrt{n}})O(
     * n
     * ​
     * <p>
     * 4
     * n
     * <p>
     * ​
     * ), as described above, and using O(n)O(n) space to store the sequence.
     */
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }

        if (open < max) {
            cur.append("(");
            backtrack(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(")");
            backtrack(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

    /**
     * Approach 3: Closure Number
     * Intuition
     *
     * To enumerate something, generally we would like to express it as a sum of disjoint subsets that are easier to count.
     *
     * Consider the closure number of a valid parentheses sequence S: the least index >= 0 so that S[0], S[1], ..., S[2*index+1] is valid. Clearly, every parentheses sequence has a unique closure number. We can try to enumerate them individually.
     *
     * Algorithm
     *
     * For each closure number c, we know the starting and ending brackets must be at index 0 and 2*c + 1. Then, the 2*c elements between must be a valid sequence, plus the rest of the elements must be a valid sequence.
     *
     * Complexity Analysis
     *
     * Time and Space Complexity : O(\dfrac{4^n}{\sqrt{n}})O(
     * n
     * ​
     *
     * 4
     * n
     *
     * ​
     *  ). The analysis is similar to Approach 2.
     * @param n
     * @return
     */
    public List<String> generateParenthesisClosureNumber(int n) {
        List<String> ans = new ArrayList();
        if (n == 0) {
            ans.add("");
        } else {
            for (int c = 0; c < n; ++c)
                for (String left: generateParenthesis(c))
                    for (String right: generateParenthesis(n-1-c))
                        ans.add("(" + left + ")" + right);
        }
        return ans;
    }

    public static void main(String [] args){
        GenerateParenthesis gp=new GenerateParenthesis();
        System.out.println(gp.generateParenthesis(4));
    }


}




