package com.samueldu.leetcode.topinterviewquestions.sortingandsearching;

import java.util.Arrays;

public class MergeSortedArray {
    /**
     * Approach 1: Merge and sort
     * Intuition
     * <p>
     * A naive approach would be to simply write the values from nums2 into the end of nums1, and then sort nums1. Remember that we do not need to return a value, as we should modify nums1 in-place. While straightforward to code, this approach has a high time complexity as we're not taking advantage of the existing sorting.
     * <p>
     * Time complexity: \mathcal{O}((n + m)\log(n + m))O((n+m)log(n+m)).
     * <p>
     * The cost of sorting a list of length xx using a built-in sorting algorithm is \mathcal{O}(x \log x)O(xlogx). Because in this case we're sorting a list of length m + nm+n, we get a total time complexity of \mathcal{O}((n + m) \log (n + m))O((n+m)log(n+m)).
     * <p>
     * Space complexity: \mathcal{O}(n)O(n), but it can vary.
     * <p>
     * Most programming languages have a built-in sorting algorithm that uses \mathcal{O}(n)O(n) space.
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i < n; i++) {
            nums1[i + m] = nums2[i];
        }
        Arrays.sort(nums1);
    }

    /**
     * Approach 3: Three Pointers (Start From the End)
     * Intuition
     *
     * Interview Tip: This is a medium-level solution to an easy-level problem. Many of LeetCode's easy-level problems have more difficult solutions, and good candidates are expected to find them.
     *
     * Approach 2 already demonstrates the best possible time complexity, \mathcal{O}(n + m)O(n+m), but still uses additional space. This is because the elements of array nums1 have to be stored somwhere so that they aren't overwritten.
     *
     * So, what if instead we start to overwrite nums1 from the end, where there is no information yet?
     *
     * The algorithm is similar to before, except this time we set p1 to point at index m - 1 of nums1, p2 to point at index n - 1 of nums2, and p to point at index m + n - 1 of nums1. This way, it is guaranteed that once we start overwriting the first m values in nums1, we will have already written each into its new position. In this way, we can eliminate the additional space.
     *
     * Interview Tip: Whenever you're trying to solve an array problem in-place, always consider the possibility of iterating backwards instead of forwards through the array. It can completely change the problem, and make it a lot easier.
     *
     * compute
     *
     * Implementation
     *
     * Current
     * 1 / 6
     *
     * Complexity Analysis
     *
     * Time complexity: \mathcal{O}(n + m)O(n+m).
     *
     * Same as Approach 2.
     *
     * Space complexity: \mathcal{O}(1)O(1).
     *
     * Unlike Approach 2, we're not using an extra array.
     */
    public void mergeFromBack(int[] nums1, int m, int[] nums2, int n) {
        // Set p1 and p2 to point to the end of their respective arrays.
        int p1 = m - 1;
        int p2 = n - 1;

        // And move p backwards through the array, each time writing
        // the smallest value pointed at by p1 or p2.
        for (int p = m + n - 1; p >= 0; p--) {
            if (p2 < 0) {
                break;
            }
            if (p1 >= 0 && nums1[p1] > nums2[p2]) {
                nums1[p] = nums1[p1--];
            } else {
                nums1[p] = nums2[p2--];
            }
        }
    }
}

