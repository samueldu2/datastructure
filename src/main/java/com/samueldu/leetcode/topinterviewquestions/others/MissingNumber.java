package com.samueldu.leetcode.topinterviewquestions.others;

/**
 * Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,0,1]
 * Output: 2
 * Explanation: n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2 is the missing number in the range since it does not appear in nums.
 * Example 2:
 *
 * Input: nums = [0,1]
 * Output: 2
 * Explanation: n = 2 since there are 2 numbers, so all numbers are in the range [0,2]. 2 is the missing number in the range since it does not appear in nums.
 * Example 3:
 *
 * Input: nums = [9,6,4,2,3,5,7,0,1]
 * Output: 8
 * Explanation: n = 9 since there are 9 numbers, so all numbers are in the range [0,9]. 8 is the missing number in the range since it does not appear in nums.
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 104
 * 0 <= nums[i] <= n
 * All the numbers of nums are unique.
 *
 *
 * Follow up: Could you implement a solution using only O(1) extra space complexity and O(n) runtime complexity?
 */
public class MissingNumber {

    /**
     * Bit Manipulation
     * Intuition
     *
     * We can harness the fact that XOR is its own inverse to find the missing element in linear time.
     *
     * Algorithm
     *
     * Because we know that nums contains nn numbers and that it is missing exactly one number on the range [0..n-1][0..n−1], we know that nn definitely replaces the missing number in nums. Therefore, if we initialize an integer to nn and XOR it with every index and value, we will be left with the missing number. Consider the following example (the values have been sorted for intuitive convenience, but need not be):
     *
     * Index	0	1	2	3
     * Value	0	1	3	4
     * \begin{align} missing &= 4 \wedge (0 \wedge 0) \wedge (1 \wedge 1) \wedge (2 \wedge 3) \wedge (3 \wedge 4) \\ &= (4 \wedge 4) \wedge (0 \wedge 0) \wedge (1 \wedge 1) \wedge (3 \wedge 3) \wedge 2 \\ &= 0 \wedge 0 \wedge 0 \wedge 0 \wedge 2 \\ &= 2 \end{align}
     *
     *
     * Complexity Analysis
     *
     * Time complexity : \mathcal{O}(n)O(n)
     *
     * Assuming that XOR is a constant-time operation, this algorithm does constant work on nn iterations, so the runtime is overall linear.
     *
     * Space complexity : \mathcal{O}(1)O(1)
     *
     * This algorithm allocates only constant additional space.
     * @param nums
     * @return
     */
    public int missingNumberBitManip(int[] nums) {
        int missing = nums.length;
        for (int i = 0; i < nums.length; i++) {
            missing ^= i ^ nums[i];
        }
        return missing;
    }


    /**
     * Gauss' Formula [Accepted]
     * Intuition
     *
     * One of the most well-known stories in mathematics is of a young Gauss, forced to find the sum of the first 100 natural numbers by a lazy teacher. Rather than add the numbers by hand, he deduced a closed-form expression for the sum, or so the story goes. You can see the formula below:
     *
     * \sum_{i=0}^{n}i = \frac{n(n+1)}{2}∑
     * i=0
     * n
     * ​
     *  i=
     * 2
     * n(n+1)
     * ​
     *
     *
     * Algorithm
     *
     * We can compute the sum of nums in linear time, and by Gauss' formula, we can compute the sum of the first nn natural numbers in constant time. Therefore, the number that is missing is simply the result of Gauss' formula minus the sum of nums, as nums consists of the first nn natural numbers minus some number.
     *
     *
     * Complexity Analysis
     *
     * Time complexity : \mathcal{O}(n)O(n)
     *
     * Although Gauss' formula can be computed in \mathcal{O}(1)O(1) time, summing nums costs us \mathcal{O}(n)O(n) time, so the algorithm is overall linear. Because we have no information about which number is missing, an adversary could always design an input for which any algorithm that examines fewer than nn numbers fails. Therefore, this solution is asymptotically optimal.
     *
     * Space complexity : \mathcal{O}(1)O(1)
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        int expectedSum = nums.length*(nums.length + 1)/2;
        int actualSum = 0;
        for (int num : nums) actualSum += num;
        return expectedSum - actualSum;
    }
}
