package com.samueldu.leetcode.topinterviewquestions.strings;

public class ReverseString {

    /**
     * Approach 1: Recursion, In-Place, \mathcal{O}(N)O(N) Space
     * Does in-place mean constant space complexity?
     *
     * No. By definition, an in-place algorithm is an algorithm which transforms input using no auxiliary data structure.
     *
     * The tricky part is that space is used by many actors, not only by data structures. The classical example is to use recursive function without any auxiliary data structures.
     *
     * Is it in-place? Yes.
     *
     * Is it constant space? No, because of recursion stack.
     * Algorithm
     *
     * Here is an example. Let's implement recursive function helper which receives two pointers, left and right, as arguments.
     *
     * Base case: if left >= right, do nothing.
     *
     * Otherwise, swap s[left] and s[right] and call helper(left + 1, right - 1).
     *
     * To solve the problem, call helper function passing the head and tail indexes as arguments: return helper(0, len(s) - 1).
     * @param s
     * @param left
     * @param right
     */
    public void helper(char[] s, int left, int right) {
        if (left >= right) return;
        char tmp = s[left];
        s[left++] = s[right];
        s[right--] = tmp;
        helper(s, left, right);
    }

    public void reverseString(char[] s) {
        helper(s, 0, s.length - 1);
    }

    /**
     * Approach 2: Two Pointers, Iteration, \mathcal{O}(1)O(1) Space
     * Two Pointers Approach
     *
     * In this approach, two pointers are used to process two array elements at the same time. Usual implementation is to set one pointer in the beginning and one at the end and then to move them until they both meet.
     *
     * Sometimes one needs to generalize this approach in order to use three pointers, like for classical Sort Colors problem.
     *
     * Algorithm
     *
     * Set pointer left at index 0, and pointer right at index n - 1, where n is a number of elements in the array.
     *
     * While left < right:
     *
     * Swap s[left] and s[right].
     *
     * Move left pointer one step right, and right pointer one step left.
     *
     * fig
     *
     * Implementation
     *
     *
     * Complexity Analysis
     *
     * Time complexity : \mathcal{O}(N)O(N) to swap N/2N/2 element.
     *
     * Space complexity : \mathcal{O}(1)O(1), it's a constant space solution.
     */

    public void reverseStringUsingTwoPointers(char[] s) {
        int left = 0, right = s.length - 1;
        while (left < right) {
            char tmp = s[left];
            s[left++] = s[right];
            s[right--] = tmp;
        }
    }
}
