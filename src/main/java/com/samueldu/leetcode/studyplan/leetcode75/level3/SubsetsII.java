package com.samueldu.leetcode.studyplan.leetcode75.level3;

import java.util.*;

/**
 * Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
 *
 * The solution set must not contain duplicate subsets. Return the solution in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,2]
 * Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
 * Example 2:
 *
 * Input: nums = [0]
 * Output: [[],[0]]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 */

/**
 * Complexity Analysis
 *
 * Here nn is the size of the nums array.
 *
 * Time complexity: O(n \cdot 2 ^ n)O(n⋅2
 * n
 *  )
 *
 * Sorting the nums array requires n \log nnlogn time. The outer for loop runs 2 ^ n2
 * n
 *   times. The inner loop runs nn times. We know that average case time complexity for set operations is O(1)O(1). Although, to generate a hash value for each subset O(n)O(n) time will be required. However, we are generating the hashcode while iterating the inner for loop. So the overall time complexity is O(n \log n + 2^n \cdot (nO(nlogn+2
 * n
 *  ⋅(n (for inner loop) + nn (for stringbuilder to string conversion in Java) )))) = O(2 ^ n \cdot n)O(2
 * n
 *  ⋅n).
 *
 * Space complexity: O(n \cdot 2^n)O(n⋅2
 * n
 *  )
 *
 * We need to store at most 2 ^ n2
 * n
 *   number of subsets in the set, seen. The maximum length of any subset can be n.
 *
 * The space complexity of the sorting algorithm depends on the implementation of each programming language. For instance, in Java, the Arrays.sort() for primitives is implemented as a variant of quicksort algorithm whose space complexity is O(\log n)O(logn). In C++ sort() function provided by STL is a hybrid of Quick Sort, Heap Sort and Insertion Sort with the worst case space complexity of O(\log n)O(logn). Thus the use of inbuilt sort() function adds O(\log n)O(logn) to space complexity.
 *
 * The space required for the output list is not considered while analyzing space complexity. Thus the overall space complexity in Big O Notation is O(n \cdot 2^n)O(n⋅2
 * n
 *  ).
 * @param args
 */

public class SubsetsII {

        public List<List<Integer>> subsetsWithDup(int[] nums) {
            List<List<Integer>> subsets = new ArrayList();
            int n = nums.length;

            // Sort the generated subset. This will help to identify duplicates.
            Arrays.sort(nums);

            int maxNumberOfSubsets = (int) Math.pow(2, n);
            // To store the previously seen sets.
            Set<String> seen = new HashSet<>();

            for (int subsetIndex = 0; subsetIndex < maxNumberOfSubsets; subsetIndex++) {
                // Append subset corresponding to that bitmask.
                List<Integer> currentSubset = new ArrayList();
                StringBuilder hashcode = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    // Generate the bitmask
                    int mask = 1 << j;
                    int isSet = mask & subsetIndex;
                    if (isSet != 0) {
                        currentSubset.add(nums[j]);
                        // Generate the hashcode by creating a comma separated string of numbers in the currentSubset.
                        hashcode.append(nums[j]).append(",");
                    }
                }
                if (!seen.contains(hashcode.toString())) {
                    seen.add(hashcode.toString());
                    subsets.add(currentSubset);
                }

            }

            return subsets;

        }


    /**
     *
     * Approach 2: Cascading (Iterative)
     * Intuition
     *
     * Assume the given array has no duplicate elements. In this case, there will be a total of 2 ^ n2
     * n
     *   distinct subsets. To find all the subsets we start with an empty subset. This will be the first subset. Next, we consider one element at a time and add it to each of the existing subsets. This can be better understood in the following animation:
     *
     * Current
     * 1 / 2
     *
     * However, in this problem, the given array can have duplicate elements which will produce duplicate subsets if we follow the previously mentioned approach. Thus, we need to omit the duplicate subsets. For this, we need to sort the given array first. To avoid adding duplicate subsets we follow this rule:
     *
     * Whenever the element under consideration has duplicates, we add one of the duplicate elements to all the existing subsets to create new subsets. For the rest of the duplicates, we only add them to the subsets created in the previous step. By convention, whenever a value is encountered for the first time, we add it to all the existing subsets. Then onwards we add its duplicates only to the subsets created in the previous step.
     *
     * In other words, we treat a group of duplicate elements as an array. Suppose we have an array [3, 3, 3]. The ways to add the elements from this array to the existing subsets are as follows:
     *
     * Not add any element having value 3 in any subset.
     *
     * Add one 3 in all the subsets.
     *
     * Add two 3 in all the subsets.
     *
     * Add three 3 in all the subsets.
     *
     * This can be better understood with the following animation:
     *
     * Current
     * 4 / 4
     *
     * Algorithm
     *
     * First, sort the array in ascending order.
     *
     * Initialize a variable subsetSize to 0. subsetSize holds the index of the subset in the subsets list from where we should start adding the current element if the current element is a duplicate. In other words, it holds the index of the first subset generated in the previous step.
     *
     * Iterate over the nums array considering one element at a time.
     *
     * If we haven't seen the value of the current element before, we need to add this element to all the previously generated subsets. So set startingIndex to 0.
     *
     * If the current element is a duplicate element, add it only to subsets that were created in the previous iteration. This means we will skip every subset that was created earlier than the previous iteration. So instead of setting startingIndex to 0, set it equal to subsetSize.
     *
     * Set subsetSize to the current subsets size. This will be the starting index of the subsets generated in this iteration.
     *
     * Add the current element to all the subsets in the subsets list created before the current iteration starting from startingIndex.
     *
     * Return subsets list.
     */

    /**
     *Complexity Analysis
     *
     * Here nn is the number of elements present in the given array.
     *
     * Time complexity: O(n \cdot 2 ^ n)O(n⋅2
     * n
     *  )
     *
     * At first, we need to sort the given array which adds O(n \log n)O(nlogn) to the time complexity. Next, we use two for loops to create all possible subsets. In the worst case, i.e., with an array of n distinct integers, we will have a total of 2 ^ n2
     * n
     *   subsets. Thus the two for loops will add O(2 ^ n)O(2
     * n
     *  ) to time complexity. Also in the inner loop, we deep copy the previously generated subset before adding the current integer (to create a new subset). This in turn requires the time of order nn as the maximum number of elements in the currentSubset will be at most nn. Thus, the time complexity in the subset generation step using two loops is O(n \cdot 2 ^ n)O(n⋅2
     * n
     *  ). Thereby, the overall time complexity is O(n \log n + n \cdot 2 ^ n)O(nlogn+n⋅2
     * n
     *  ) = O(n \cdot (\log n + 2 ^ n))O(n⋅(logn+2
     * n
     *  )) ~ O(n \cdot 2 ^ n)O(n⋅2
     * n
     *  ).
     *
     * Space complexity: O(\log n)O(logn)
     *
     * The space complexity of the sorting algorithm depends on the implementation of each programming language. For instance, in Java, the Arrays.sort() for primitives is implemented as a variant of quicksort algorithm whose space complexity is O(\log n)O(logn). In C++ sort() function provided by STL is a hybrid of Quick Sort, Heap Sort and Insertion Sort with the worst case space complexity of O(\log n)O(logn). Thus the use of inbuilt sort() function adds O(\log n)O(logn) to space complexity.
     *
     * The space required for the output list is not considered while analyzing space complexity. Thus the overall space complexity in Big O Notation is O(\log n)O(logn).
     */
    public List<List<Integer>> subsetsWithDupCascading(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> subsets = new ArrayList<>();
        subsets.add(new ArrayList<Integer>());

        int subsetSize = 0;

        for (int i = 0; i < nums.length; i++) {
            int startingIndex = (i >= 1 && nums[i] == nums[i - 1]) ? subsetSize : 0;
            // subsetSize refers to the size of the subset in the previous step. This value also indicates the starting index of the subsets generated in this step.
            subsetSize = subsets.size();
            for (int j = startingIndex; j < subsetSize; j++) {
                List<Integer> currentSubset = new ArrayList<>(subsets.get(j));
                currentSubset.add(nums[i]);
                subsets.add(currentSubset);
            }
        }
        return subsets;
    }


    public static void main(String [] args){
            SubsetsII sii= new SubsetsII();
        List<List<Integer>> result= sii.subsetsWithDup(new int[]{3, 2});
        System.out.println(result);
    }
}
