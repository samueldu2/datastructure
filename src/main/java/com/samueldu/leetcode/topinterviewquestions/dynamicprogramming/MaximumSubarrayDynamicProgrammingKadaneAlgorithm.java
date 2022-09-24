package com.samueldu.leetcode.topinterviewquestions.dynamicprogramming;

public class MaximumSubarrayDynamicProgrammingKadaneAlgorithm {
    /**
     * Approach 2: Dynamic Programming, Kadane's Algorithm
     * Intuition
     *
     * Whenever you see a question that asks for the maximum or minimum of something, consider Dynamic Programming as a possibility. The difficult part of this problem is figuring out when a negative number is "worth" keeping in a subarray. This question in particular is a popular problem that can be solved using an algorithm called Kadane's Algorithm. If you're good at problem solving though, it's quite likely you'll be able to come up with the algorithm on your own. This algorithm also has a very greedy-like intuition behind it.
     *
     * Let's focus on one important part: where the optimal subarray begins. We'll use the following example.
     *
     * nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
     *
     * We can see that the optimal subarray couldn't possibly involve the first 3 values - the overall sum of those numbers would always subtract from the total. Therefore, the subarray either starts at the first 4, or somewhere further to the right.
     *
     * What if we had this example though?
     *
     * nums = [-2,1000000000,-3,4,-1,2,1,-5,4]
     *
     * We need a general way to figure out when a part of the array is worth keeping.
     *
     * As expected, any subarray whose sum is positive is worth keeping. Let's start with an empty array, and iterate through the input, adding numbers to our array as we go along. Whenever the sum of the array is negative, we know the entire array is not worth keeping, so we'll reset it back to an empty array.
     *
     * However, we don't actually need to build the subarray, we can just keep an integer variable current_subarray and add the values of each element there. When it becomes negative, we reset it to 0 (an empty array).
     *
     * Current
     * 10 / 10
     * Algorithm
     *
     * Initialize 2 integer variables. Set both of them equal to the first value in the array.
     *
     * currentSubarray will keep the running count of the current subarray we are focusing on.
     * maxSubarray will be our final return value. Continuously update it whenever we find a bigger subarray.
     * Iterate through the array, starting with the 2nd element (as we used the first element to initialize our variables). For each number, add it to the currentSubarray we are building. If currentSubarray becomes negative, we know it isn't worth keeping, so throw it away. Remember to update maxSubarray every time we find a new maximum.
     *
     * Return maxSubarray.
     *
     * Implementation
     *
     * A clever way to update currentSubarray is using currentSubarray = max(num, currentSubarray + num). If currentSubarray is negative, then num > currentSubarray + num.
     *
     *
     * Complexity Analysis
     *
     * Time complexity: O(N)O(N), where NN is the length of nums.
     *
     * We iterate through every element of nums exactly once.
     *
     * Space complexity: O(1)O(1)
     *
     * No matter how long the input is, we are only ever using 2 variables: currentSubarray and maxSubarray.
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        // Initialize our variables using the first element.
        int currentSubarray = nums[0];
        int maxSubarray = nums[0];

        // Start with the 2nd element since we already used the first one.
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            // If current_subarray is negative, throw it away. Otherwise, keep adding to it.
            currentSubarray = Math.max(num, currentSubarray + num);
            maxSubarray = Math.max(maxSubarray, currentSubarray);
        }

        return maxSubarray;
    }
}
