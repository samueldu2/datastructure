package com.samueldu.leetcode.topinterviewquestions.design;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Shuffle an Array
 *
 * Solution
 * Given an integer array nums, design an algorithm to randomly shuffle the array. All permutations of the array should be equally likely as a result of the shuffling.
 *
 * Implement the Solution class:
 *
 * Solution(int[] nums) Initializes the object with the integer array nums.
 * int[] reset() Resets the array to its original configuration and returns it.
 * int[] shuffle() Returns a random shuffling of the array.
 *
 *
 * Example 1:
 *
 * Input
 * ["Solution", "shuffle", "reset", "shuffle"]
 * [[[1, 2, 3]], [], [], []]
 * Output
 * [null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]
 *
 * Explanation
 * Solution solution = new Solution([1, 2, 3]);
 * solution.shuffle();    // Shuffle the array [1,2,3] and return its result.
 *                        // Any permutation of [1,2,3] must be equally likely to be returned.
 *                        // Example: return [3, 1, 2]
 * solution.reset();      // Resets the array back to its original configuration [1,2,3]. Return [1, 2, 3]
 * solution.shuffle();    // Returns the random shuffling of array [1,2,3]. Example: return [1, 3, 2]
 *
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 50
 * -106 <= nums[i] <= 106
 * All the elements of nums are unique.
 * At most 104 calls in total will be made to reset and shuffle.
 */
public class ShuffleAnArray {

    private int[] array;
    private int[] original;

    private Random rand = new Random();

    private List<Integer> getArrayCopy() {
        List<Integer> asList = new ArrayList<Integer>();
        for (int i = 0; i < array.length; i++) {
            asList.add(array[i]);
        }
        return asList;
    }

    public ShuffleAnArray(int[] nums) {
        array = nums;
        original = nums.clone();
    }

    public int[] reset() {
        array = original;
        original = original.clone();
        return array;
    }

    /**
     * Time complexity : \mathcal{O}(n^2)O(n
     * 2
     *  )
     *
     * The quadratic time complexity arises from the calls to list.remove (or list.pop), which run in linear time. nn linear list removals occur, which results in a fairly easy quadratic analysis.
     *
     * Space complexity : \mathcal{O}(n)O(n)
     *
     * Because the problem also asks us to implement reset, we must use linear additional space to store the original array. Otherwise, it would be lost upon the first call to shuffle.
     * @return
     */
    public int[] shuffle() {
        List<Integer> aux = getArrayCopy();

        for (int i = 0; i < array.length; i++) {
            int removeIdx = rand.nextInt(aux.size());
            array[i] = aux.get(removeIdx);
            aux.remove(removeIdx);
        }

        return array;
    }
}
