package com.samueldu.leetcode.dynamicprogramming.stragegies;

/**
 * 1770. Maximum Score from Performing Multiplication Operations
 * Hard
 *
 * 2197
 *
 * 503
 *
 * Add to List
 *
 * Share
 * You are given two 0-indexed integer arrays nums and multipliers of size n and m respectively, where n >= m.
 *
 * You begin with a score of 0. You want to perform exactly m operations. On the ith operation (0-indexed) you will:
 *
 * Choose one integer x from either the start or the end of the array nums.
 * Add multipliers[i] * x to your score.
 * Note that multipliers[0] corresponds to the first operation, multipliers[1] to the second operation, and so on.
 * Remove x from nums.
 * Return the maximum score after performing m operations.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3], multipliers = [3,2,1]
 * Output: 14
 * Explanation: An optimal solution is as follows:
 * - Choose from the end, [1,2,3], adding 3 * 3 = 9 to the score.
 * - Choose from the end, [1,2], adding 2 * 2 = 4 to the score.
 * - Choose from the end, [1], adding 1 * 1 = 1 to the score.
 * The total score is 9 + 4 + 1 = 14.
 * Example 2:
 *
 * Input: nums = [-5,-3,-3,-2,7,1], multipliers = [-10,-5,3,4,6]
 * Output: 102
 * Explanation: An optimal solution is as follows:
 * - Choose from the start, [-5,-3,-3,-2,7,1], adding -5 * -10 = 50 to the score.
 * - Choose from the start, [-3,-3,-2,7,1], adding -3 * -5 = 15 to the score.
 * - Choose from the start, [-3,-2,7,1], adding -3 * 3 = -9 to the score.
 * - Choose from the end, [-2,7,1], adding 1 * 4 = 4 to the score.
 * - Choose from the end, [-2,7], adding 7 * 6 = 42 to the score.
 * The total score is 50 + 15 - 9 + 4 + 42 = 102.
 *
 *
 * Constraints:
 *
 * n == nums.length
 * m == multipliers.length
 * 1 <= m <= 300
 * m <= n <= 105
 * -1000 <= nums[i], multipliers[i] <= 1000
 */
public class MaximumScoreFromPerformingMultiplicationOperationsTopDown {

    /**
     *Example 1770. Maximum Score from Performing Multiplication Operations
     * Report Issue
     * For this problem, we will again start by looking at a top-down approach.
     *
     * In this article, we're going to be looking at the problem Maximum Score from Performing Multiplication Operations. We can tell this is a DP problem because it is asking for a maximum score, and every time we choose to use a number from \text{nums}nums, it affects all future possibilities. Let's solve this problem with the framework:
     *
     * 1. A function or array that answers the problem for a given state
     *
     * In the following discussion, we will use 0-index, since it is more convienient for thinking and coding.
     *
     * Since we're doing top-down, we need to decide on two things for our function \text{dp}dp. What state variables we need to pass to it, and what it will return. We are given two input arrays: \text{nums}nums and \text{multipliers}multipliers. The problem says we need to do \text{m}m operations, and on the i^{th}i
     * th
     *   operation, we gain score equal to \text{multipliers[i]}multipliers[i] times a number from either the left or right end of \text{nums}nums, which we remove after the operation. That means we need to know 3 things for each operation:
     *
     * How many operations have we done so far; this tells us what number from \text{multipliers}multipliers we will be using?
     * The index of the leftmost number remaining in \text{nums}nums.
     * The index of the rightmost number remaining in \text{nums}nums.
     * We can use one state variable, \text{i}i, to indicate how many operations we have done so far, which means \text{multipliers[i]}multipliers[i] is the current multiplier to be used. For the leftmost number remaining in \text{nums}nums, we can use another state variable, \text{left}left, that indicates how many left operations we have done so far. If we have done, say 3 left operations, if we were to do another left operation we would use \text{nums[3]}nums[3]. We can say the same thing for the rightmost remaining number - let's use a state variable \text{right}right that indicates how many right operations we have done so far.
     *
     * It may seem like we need all 3 of these state variables, but we can formulate an equation for one of them using the other two. If we know how many elements we have picked from the leftside, \text{left}left, and we know how many elements we have picked in total, \text{i}i, then we know that we must have picked \text{i - left}i - left elements from the rightside. The original length of \text{nums}nums is \text{n}n, which means the index of the rightmost element is \text{right = n - 1 - (i - left)}right = n - 1 - (i - left). Therefore, we only need 2 state variables: \text{i}i and \text{left}left, and we can calculate \text{right}right inside the function.
     *
     * Now that we have our state variables, what should our function return? The problem is asking for the maximum score from some number of operations, so let's have our function \text{dp(i, left)}dp(i, left) return the maximum possible score if we have already done \text{i}i total operations and used \text{left}left numbers from the left side. To answer the original problem, we should return \text{dp(0, 0)}dp(0, 0).
     *
     * Current
     * 3 / 3
     *
     *
     * 2. A recurrence relation to transition between states
     *
     * At each state, we have to perform an operation. As stated in the problem description, we need to decide whether to take from the left end (\text{nums[left]}nums[left]) or the right end (\text{nums[right]}nums[right]) of the current \text{nums}nums. Then we need to multiply the number we choose by \text{multipliers[i]}multipliers[i], add this value to our score, and finally remove the number we chose from \text{nums}nums. For implementation purposes, "removing" a number from \text{nums}nums means incrementing our state variables \text{i}i and \text{left}left so that they point to the next two left and right numbers.
     *
     * Let \text{mult} = \text{multipliers[i]}mult=multipliers[i] and \text{right = nums.length - 1 - (i - left)}right = nums.length - 1 - (i - left). The only decision we have to make is whether to take from the left or right of \text{nums}nums.
     *
     * If we choose left, we gain \text{mult} \cdot \text{nums[left]}mult⋅nums[left] points from this operation. Then, the next operation will occur at \text{(i + 1, left + 1)}(i + 1, left + 1). \text{i}i gets incremented at every operation because it represents how many operations we have done, and \text{left}left gets incremented because it represents how many left operations we have done. Therefore, our total score is \text{mult} \cdot \text{nums[left] + dp(i + 1, left + 1)}mult⋅nums[left] + dp(i + 1, left + 1).
     * If we choose right, we gain \text{mult} \cdot \text{nums[right]}mult⋅nums[right] points from this operation. Then, the next operation will occur at \text{(i + 1, left)}(i + 1, left). Therefore, our total score is \text{mult} \cdot \text{nums[right] + dp(i + 1, left)}mult⋅nums[right] + dp(i + 1, left).
     * Since we want to maximize our score, we should choose the side that gives more points. This gives us our recurrence relation:
     *
     * \text{dp(i, left)} = \max(\text{mult} \cdot \text{nums[left]} + \text{dp(i + 1, left + 1)}, \text{ mult} \cdot \text{nums[right]} + \text{dp(i + 1, left)})dp(i, left)=max(mult⋅nums[left]+dp(i + 1, left + 1), mult⋅nums[right]+dp(i + 1, left))
     *
     * Where \text{mult} \cdot \text{nums[left]} + \text{dp(i + 1, left + 1)}mult⋅nums[left]+dp(i + 1, left + 1) represents the points we gain by taking from the left end of \text{nums}nums plus the maximum points we can get from the remaining \text{nums}nums array and \text{mult} \cdot \text{nums[right]} + \text{dp(i + 1, left)}mult⋅nums[right]+dp(i + 1, left) represents the points we gain by taking from the right end of \text{nums}nums plus the maximum points we can get from the remaining \text{nums}nums array.
     *
     * 3. Base cases
     *
     * The problem statement says that we need to perform \text{m}m operations. When \text{i}i equals \text{m}m, that means we have no operations left. Therefore, we should return \text{0}0.
     */

    /**
     * TOP Down
     * @param nums
     * @param multipliers
     * @return
     */
    private int[][] memo;
    private int[] nums, multipliers;
    private int n, m;

    private int dp(int i, int left) {
        if (i == m) {
            return 0; // Base case
        }

        int mult = multipliers[i];
        int right = n - 1 - (i - left);

        if (memo[i][left] == 0) {
            // Recurrence relation
            memo[i][left] = Math.max(mult * nums[left] + dp(i + 1, left + 1),
                    mult * nums[right] + dp(i + 1, left));
        }

        return memo[i][left];
    }

    public int maximumScore(int[] nums, int[] multipliers) {
        n = nums.length;
        m = multipliers.length;
        this.nums = nums;
        this.multipliers = multipliers;
        this.memo = new int[m][m];
        return dp(0, 0);
    }
}
