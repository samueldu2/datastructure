package com.samueldu.leetcode.topinterviewquestions.array;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must appear as many times as it shows in both arrays and you may return the result in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2,2]
 * Example 2:
 *
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [4,9]
 * Explanation: [9,4] is also accepted.
 *
 *
 * Constraints:
 *
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 1000
 *
 *
 * Follow up:
 *
 * What if the given array is already sorted? How would you optimize your algorithm?
 * What if nums1's size is small compared to nums2's size? Which algorithm is better?
 * What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 */
public class IntersectionOfTwoArraysII {
/**
 * using hashmap
 * Approach 1: Hash Map
 * For the previous problem, we used a hash set to achieve a linear time complexity. Here, we need to use a hash map to track the count for each number.
 *
 * We collect numbers and their counts from one of the arrays into a hash map. Then, we iterate along the second array, and check if the number exists in the hash map and its count is positive. If so - add the number to the result and decrease its count in the hash map.
 *
 * Hash Map Illustration
 *
 * It's a good idea to check array sizes and use a hash map for the smaller array. It will reduce memory usage when one of the arrays is very large.
 *
 * Algorithm
 *
 * If nums1 is larger than nums2, swap the arrays.
 *
 * For each element in nums1:
 *
 * Add it to the hash map m.
 *
 * Increment the count if the element is already there.
 * Initialize the insertion pointer (k) with zero.
 *
 * Iterate along nums2:
 *
 * If the current number is in the hash map and count is positive:
 *
 * Copy the number into nums1[k], and increment k.
 *
 * Decrement the count in the hash map.
 *
 * Return first k elements of nums1.
 *
 * For our solutions here, we use one of the arrays to store the result. As we find common numbers, we copy them to the first array starting from the beginning. This idea is from this solution by sankitgupta.
 *
 * Complexity Analysis
 *
 * Time Complexity: \mathcal{O}(n + m)O(n+m), where nn and mm are the lengths of the arrays. We iterate through the first, and then through the second array; insert and lookup operations in the hash map take a constant time.
 *
 * Space Complexity: \mathcal{O}(\min(n, m))O(min(n,m)). We use hash map to store numbers (and their counts) from the smaller array.
 */
    public int[] intersectUsingHashMap(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersectUsingHashMap(nums2, nums1);
        }
        HashMap<Integer, Integer> m = new HashMap<>();
        for (int n : nums1) {
            m.put(n, m.getOrDefault(n, 0) + 1);
        }
        int k = 0;
        for (int n : nums2) {
            int cnt = m.getOrDefault(n, 0);
            if (cnt > 0) {
                nums1[k++] = n;
                m.put(n, cnt - 1);
            }
        }
        return Arrays.copyOfRange(nums1, 0, k);
    }


/**
 *Approach 2: Sort
 * You can recommend this method when the input is sorted, or when the output needs to be sorted. Here, we sort both arrays (assuming they are not sorted) and use two pointers to find common numbers in a single scan.
 *
 * Sort Illustration
 *
 * Algorithm
 *
 * Sort nums1 and nums2.
 *
 * Initialize i, j and k with zero.
 *
 * Move indices i along nums1, and j through nums2:
 *
 * Increment i if nums1[i] is smaller.
 *
 * Increment j if nums2[j] is smaller.
 *
 * If numbers are the same, copy the number into nums1[k], and increment i, j and k.
 *
 * Return first k elements of nums1.
 * Complexity Analysis
 *
 * Time Complexity: \mathcal{O}(n\log{n} + m\log{m})O(nlogn+mlogm), where nn and mm are the lengths of the arrays. We sort two arrays independently, and then do a linear scan.
 *
 * Space Complexity: from \mathcal{O}(\log{n} + \log{m})O(logn+logm) to \mathcal{O}(n + m)O(n+m), depending on the implementation of the sorting algorithm. For the complexity analysis purposes, we ignore the memory required by inputs and outputs.
 *
 */
public int[] intersectWithSortedData(int[] nums1, int[] nums2) {
    Arrays.sort(nums1);
    Arrays.sort(nums2);
    int i = 0, j = 0, k = 0;
    while (i < nums1.length && j < nums2.length) {
        if (nums1[i] < nums2[j]) {
            ++i;
        } else if (nums1[i] > nums2[j]) {
            ++j;
        } else {
            nums1[k++] = nums1[i++];
            ++j;
        }
    }
    return Arrays.copyOfRange(nums1, 0, k);
}






}
