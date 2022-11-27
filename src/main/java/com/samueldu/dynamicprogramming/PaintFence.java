package com.samueldu.dynamicprogramming;

import java.util.HashMap;

/**
 * You are painting a fence of n posts with k different colors. You must paint the posts following these rules:
 *
 * Every post must be painted exactly one color.
 * There cannot be three or more consecutive posts with the same color.
 * Given the two integers n and k, return the number of ways you can paint the fence.
 *
 * Example 1:
 *
 *
 * Input: n = 3, k = 2
 * Output: 6
 * Explanation: All the possibilities are shown.
 * Note that painting all the posts red or all the posts green is invalid because there cannot be three posts in a row with the same color.
 * Example 2:
 *
 * Input: n = 1, k = 1
 * Output: 1
 * Example 3:
 *
 * Input: n = 7, k = 2
 * Output: 42
 *
 *Constraints:
 *
 * 1 <= n <= 50
 * 1 <= k <= 105
 * The testcases are generated such that the answer is in the range [0, 231 - 1] for the given n and k.
 */

/**
 * Realizing This is a Dynamic Programming Problem
 *
 * There are two parts to this problem that tell us it can be solved with dynamic programming.
 *
 * First, the question is asking for the "number of ways" to do something.
 *
 * Second, we need to make decisions that may depend on previously made decisions. In this problem, we need to decide what color we should paint a given post, which may change depending on previous decisions. For example, if we paint the first two posts the same color, then we are not allowed to paint the third post the same color.
 *
 * Both of these things are characteristic of dynamic programming problems.
 *
 * A Framework to Solve Dynamic Programming Problems
 *
 * A dynamic programming algorithm typically has 3 components. Learning these components is extremely valuable, as most dynamic programming problems can be solved this way.
 *
 * First, we need some function or array that represents the answer to the problem for a given state. For this problem, let's say that we have a function totalWays, where totalWays(i) returns the number of ways to paint i posts. Because we only have one argument, this is a one-dimensional dynamic programming problem.
 *
 * Second, we need a way to transition between states, such as totalWays(3) and totalWays(4). This is called a recurrence relation and figuring it out is usually the hardest part of solving a problem with dynamic programming. We'll talk about the recurrence relation for this problem below.
 *
 * The third component is establishing base cases. If we have one post, there are k ways to paint it. If we have two posts, then there are k * k ways to paint it (since we are allowed to paint have two posts in a row be the same color). Therefore, totalWays(1) = k, totalWays(2) = k * k.
 *
 * Finding The Recurrence Relation
 *
 * We know the values for totalWays(1) and totalWays(2), now we need a formula for totalWays(i), where 3 <= i <= n. Let's think about how many ways there are to paint the i^{th}i
 * th
 *   post. We have two options:
 *
 * Use a different color than the previous post. If we use a different color, then there are k - 1 colors for us to use. This means there are (k - 1) * totalWays(i - 1) ways to paint the i^{th}i
 * th
 *   post a different color than the (i - 1)^{th}(i−1)
 * th
 *   post.
 *
 * Use the same color as the previous post. There is only one color for us to use, so there are 1 * totalWays(i - 1) ways to paint the i^{th}i
 * th
 *   post the same color as the (i - 1)^{th}(i−1)
 * th
 *   post. However, we have the added restriction of not being allowed to paint three posts in a row the same color. Therefore, we can paint the i^{th}i
 * th
 *   post the same color as the (i - 1)^{th}(i−1)
 * th
 *   post only if the (i - 1)^{th}(i−1)
 * th
 *   post is a different color than the (i - 2)^{th}(i−2)
 * th
 *   post.
 *
 * So, how many ways are there to paint the (i - 1)^{th}(i−1)
 * th
 *   post a different color than the (i - 2)^{th}(i−2)
 * th
 *   post? Well, as stated in the first option, there are (k - 1) * totalWays(i - 1) ways to paint the i^{th}i
 * th
 *   post a different color than the (i - 1)^{th}(i−1)
 * th
 *   post, so that means there are 1 * (k - 1) * totalWays(i - 2) ways to paint the (i - 1)^{th}(i−1)
 * th
 *   post a different color than the (i - 2)^{th}(i−2)
 * th
 *   post.
 *
 * Adding these two scenarios together gives totalWays(i) = (k - 1) * totalWays(i - 1) + (k - 1) * totalWays(i - 2), which can be simplified to:
 *
 * totalWays(i) = (k - 1) * (totalWays(i - 1) + totalWays(i - 2))
 *
 * This is our recurrence relation which we can use to solve the problem from the base cases.
 */
public class PaintFence {
    /**
     * DP element 1
     * memoize
     */
    private HashMap<Integer, Integer> memo = new HashMap<Integer, Integer>();

    private int totalWays(int i, int k) {
        /**
         * DB element 2, base cases.
         */
        if (i == 1) return k;
        if (i == 2) return k * k;

        // Check if we have already calculated totalWays(i)
        if (memo.containsKey(i)) {
            return memo.get(i);
        }

        /**
         * DP element 3 recurrence logic.
         */
        // Use the recurrence relation to calculate totalWays(i)
        memo.put(i, (k - 1) * (totalWays(i - 1, k) + totalWays(i - 2, k)));
        return memo.get(i);
    }

    public int numWays(int n, int k) {
        return totalWays(n, k);
    }
}
