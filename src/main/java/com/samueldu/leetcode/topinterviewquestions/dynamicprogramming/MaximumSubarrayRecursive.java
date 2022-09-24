package com.samueldu.leetcode.topinterviewquestions.dynamicprogramming;

public class MaximumSubarrayRecursive {
    /**
     * Approach 3: Divide and Conquer (Advanced)
     * Intuition
     *
     * This approach is slower than the second approach and uses more space, but it's still a nice and different way to approach the problem. In an interview, sometimes you may be asked for alternative ways to solve a problem - and divide and conquer is an extremely common type of algorithm. This solution will make use of recursion - if you aren't familiar with recursion, check out the recursion explore card.
     *
     * Divide and conquer algorithms involve splitting up the input into smaller chunks until they're small enough to be easily solved, and then combining the solutions to get the final overall solution. If you're unfamiliar with them, check out this explore card.
     *
     * If we were to split our input in half, then logically the optimal subarray either:
     *
     * Uses elements only from the left side
     * Uses elements only from the right side
     * Uses a combination of elements from both the left and right side
     * Thus, the answer is simply the largest of:
     *
     * The maximum subarray contained only in the left side
     * The maximum subarray contained only in the right side
     * The maximum subarray that can use elements from both sides
     * Finding the maximum subarray from the left and right half is straightforward - just recurse using the respective half of the array. So, the hard part is figuring out how to find the best subarray that uses elements from both sides. Lets use a smaller example, nums = [5, -2, 1, -3, 4, -2, 1]. Since we want to use elements from both sides, we also must use the middle element, -3. Now, we can also take from the left and the right, but every element must be connected to the middle (since we're still forming a subarray).
     *
     * The fact that every element must be connected to the middle actually makes our lives a lot easier - every subarray we consider must contain the element immediately beside the center, which means there are only as many subarrays as there are elements. In our example, the right side is [4, -2, 1]. There are only 3 subarrays to consider - [4], [4, -2], and [4, -2, 1]. To find the best chain of elements we can take from a half, iterate from the middle to the end (start of the array for the left half) and continuously update some counter curr.
     *
     * Current
     * 10 / 11
     * Algorithm
     *
     * Now that we know how to find the best subarray containing elements from both sides of any given array, the algorithm is as follows:
     *
     * Define a helper function that we will use for recursion.
     *
     * This function will take an input left and right, which defines the bounds of the array. The return value of this function will be the best possible subarray for the array that fits between left and right.
     * If left > right, we have an empty array. Return negative infinity.
     * Find the midpoint of our array. This is (left + right) / 2, rounded down. Using this midpoint, find the best possible subarray that uses elements from both sides of the array with the algorithm detailed in the animation above.
     * The best subarray using elements from both sides is only 1 of 3 possibilities. We still need to find the best subarray using only the left or right halves. So, call this function again, once with the left half, and once with the right half.
     * Return the largest of the 3 values - the best left half sum, the best right half sum, and the best combined sum.
     * Call our helper function with the entire input array (left = 0, right = length - 1). This is our final answer, so return it.
     *
     * Implementation
     *
     *
     * Complexity Analysis
     *
     * Time complexity: O(N \cdot \log N)O(N⋅logN), where NN is the length of nums.
     *
     * On our first call to findBestSubarray, we use for loops to visit every element of nums. Then, we split the array in half and call findBestSubarray with each half. Both those calls will then iterate through every element in that half, which combined is every element of nums again. Then, both those halves will be split in half, and 4 more calls to findBestSubarray will happen, each with a quarter of nums. As you can see, every time the array is split, we still need to handle every element of the original input nums. We have to do this \log NlogN times since that's how many times an array can be split in half.
     *
     * Space complexity: O(\log N)O(logN), where NN is the length of nums.
     *
     * The extra space we use relative to input size is solely occupied by the recursion stack. Each time the array gets split in half, another call of findBestSubarray will be added to the recursion stack, until calls start to get resolved by the base case - remember, the base case happens at an empty array, which occurs after \log NlogN calls.
     */


        private int[] numsArray;

        public int maxSubArray(int[] nums) {
            numsArray = nums;

            // Our helper function is designed to solve this problem for
            // any array - so just call it using the entire input!
            return findBestSubarray(0, numsArray.length - 1);
        }

        private int findBestSubarray(int left, int right) {
            // Base case - empty array.
            if (left > right) {
                return Integer.MIN_VALUE;
            }

            int mid = Math.floorDiv(left + right, 2);
            int curr = 0;
            int bestLeftSum = 0;
            int bestRightSum = 0;

            // Iterate from the middle to the beginning.
            for (int i = mid - 1; i >= left; i--) {
                curr += numsArray[i];
                bestLeftSum = Math.max(bestLeftSum, curr);
            }

            // Reset curr and iterate from the middle to the end.
            curr = 0;
            for (int i = mid + 1; i <= right; i++) {
                curr += numsArray[i];
                bestRightSum = Math.max(bestRightSum, curr);
            }

            // The bestCombinedSum uses the middle element and the best
            // possible sum from each half.
            int bestCombinedSum = numsArray[mid] + bestLeftSum + bestRightSum;

            // Find the best subarray possible from both halves.
            int leftHalf = findBestSubarray(left, mid - 1);
            int rightHalf = findBestSubarray(mid + 1, right);

            // The largest of the 3 is the answer for any given input array.
            return Math.max(bestCombinedSum, Math.max(leftHalf, rightHalf));
        }


}
