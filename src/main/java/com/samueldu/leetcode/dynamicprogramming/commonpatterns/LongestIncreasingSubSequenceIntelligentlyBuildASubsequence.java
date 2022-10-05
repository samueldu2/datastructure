package com.samueldu.leetcode.dynamicprogramming.commonpatterns;

import java.util.ArrayList;

public class LongestIncreasingSubSequenceIntelligentlyBuildASubsequence {
/**
 * Intelligently Build a Subsequence
 * Intuition
 *
 * As stated in the previous approach, the difficult part of this problem is deciding if an element is worth using or not. Consider the example nums = [8, 1, 6, 2, 3, 10]. Let's try to build an increasing subsequence starting with an empty one: sub = [].
 *
 * At the first element 8, we might as well take it since it's better than nothing, so sub = [8].
 *
 * At the second element 1, we can't increase the length of the subsequence since 8 >= 1, so we have to choose only one element to keep. Well, this is an easy decision, let's take the 1 since there may be elements later on that are greater than 1 but less than 8, now we have sub = [1].
 *
 * At the third element 6, we can build on our subsequence since 6 > 1, now sub = [1, 6].
 *
 * At the fourth element 2, we can't build on our subsequence since 6 >= 2, but can we improve on it for the future? Well, similar to the decision we made at the second element, if we replace the 6 with 2, we will open the door to using elements that are greater than 2 but less than 6 in the future, so sub = [1, 2].
 *
 * At the fifth element 3, we can build on our subsequence since 3 > 2. Notice that this was only possible because of the swap we made in the previous step, so sub = [1, 2, 3].
 *
 * At the last element 10, we can build on our subsequence since 10 > 3, giving a final subsequence sub = [1, 2, 3, 10]. The length of sub is our answer.
 *
 * It appears the best way to build an increasing subsequence is: for each element num, if num is greater than the largest element in our subsequence, then add it to the subsequence. Otherwise, perform a linear scan through the subsequence starting from the smallest element and replace the first element that is greater than or equal to num with num. This opens the door for elements that are greater than num but less than the element replaced to be included in the sequence.
 *
 * One thing to add: this algorithm does not always generate a valid subsequence of the input, but the length of the subsequence will always equal the length of the longest increasing subsequence. For example, with the input [3, 4, 5, 1], at the end we will have sub = [1, 4, 5], which isn't a subsequence, but the length is still correct. The length remains correct because the length only changes when a new element is larger than any element in the subsequence. In that case, the element is appended to the subsequence instead of replacing an existing element.
 *
 * Algorithm
 *
 * Initialize an array sub which contains the first element of nums.
 *
 * Iterate through the input, starting from the second element. For each element num:
 *
 * If num is greater than any element in sub, then add num to sub.
 * Otherwise, iterate through sub and find the first element that is greater than or equal to num. Replace that element with num.
 * Return the length of sub.
 */

public int lengthOfLIS(int[] nums) {
    ArrayList<Integer> sub = new ArrayList<>();
    sub.add(nums[0]);

    for (int i = 1; i < nums.length; i++) {
        int num = nums[i];
        if (num > sub.get(sub.size() - 1)) {
            sub.add(num);
        } else {
            // Find the first element in sub that is greater than or equal to num
            int j = 0;
            while (num > sub.get(j)) {
                j += 1;
            }

            sub.set(j, num);
        }
    }

    return sub.size();
}
/**
 * Complexity Analysis
 *
 * Given NN as the length of nums,
 *
 * Time complexity: O(N^2)O(N
 * 2
 *  )
 *
 * This algorithm will have a runtime of O(N^2)O(N
 * 2
 *  ) only in the worst case. Consider an input where the first half is [1, 2, 3, 4, ..., 99998, 99999], then the second half is [99998, 99998, 99998, ..., 99998, 99998]. We would need to iterate (N / 2)^2(N/2)
 * 2
 *   times for the second half because there are N / 2N/2 elements equal to 99998, and a linear scan for each one takes N / 2N/2 iterations. This gives a time complexity of O(N^2)O(N
 * 2
 *  ).
 *
 * Despite having the same time complexity as the previous approach, in the best and average cases, it is much more efficient.
 *
 * Space complexity: O(N)O(N)
 *
 * When the input is strictly increasing, the sub array will be the same size as the input.
 */


/**
 * Improved above with Binary Search:
 *  Improve With Binary Search
 * Intuition
 *
 * In the previous approach, when we have an element num that is not greater than all the elements in sub, we perform a linear scan to find the first element in sub that is greater than or equal to num. Since sub is in sorted order, we can use binary search instead to greatly improve the efficiency of our algorithm.
 *
 * Algorithm
 *
 * Initialize an array sub which contains the first element of nums.
 *
 * Iterate through the input, starting from the second element. For each element num:
 *
 * If num is greater than any element in sub, then add num to sub.
 * Otherwise, perform a binary search in sub to find the smallest element that is greater than or equal to num. Replace that element with num.
 * Return the length of sub.
 *
 * Implementation
 */


    public int lengthOfLISWithBinarySearch(int[] nums) {
        ArrayList<Integer> sub = new ArrayList<>();
        sub.add(nums[0]);

        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (num > sub.get(sub.size() - 1)) {
                sub.add(num);
            } else {
                int j = binarySearch(sub, num);
                sub.set(j, num);
            }
        }

        return sub.size();
    }

    private int binarySearch(ArrayList<Integer> sub, int num) {
        int left = 0;
        int right = sub.size() - 1;
        int mid = (left + right) / 2;

        while (left < right) {
            mid = (left + right) / 2;
            if (sub.get(mid) == num) {
                return mid;
            }

            if (sub.get(mid) < num) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
    /**
     * Complexity Analysis
     *
     * Given NN as the length of nums,
     *
     * Time complexity: O(N \cdot \log(N))O(N⋅log(N))
     *
     * Binary search uses \log(N)log(N) time as opposed to the O(N)O(N) time of a linear scan, which improves our time complexity from O(N^2)O(N
     * 2
     *  ) to O(N \cdot \log(N))O(N⋅log(N)).
     *
     * Space complexity: O(N)O(N)
     *
     * When the input is strictly increasing, the sub array will be the same size as the input.
     */



}
