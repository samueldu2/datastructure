package com.samueldu.leetcode.studyplan.leetcode75.level3.bitOperations.backtracking;

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

    /**
     * Approach 3: Backtracking
     * Intuition
     *
     * If you're not familiar with backtracking, check out our Explore Card.
     *
     * As demonstrated in the previous approaches, the key to this problem is figuring out how to avoid duplicate subsets. In the previous approach, we discussed an iterative way to do so. Although the iterative approach is optimal in terms of time and space complexity, recursive approaches tend to be more intuitive, so let's see how we can solve this problem recursively.
     *
     * When designing our recursive function, there are two main points that we need to consider at each function call:
     *
     * Whether the element under consideration has duplicates or not.
     * If the element has duplicates, which element among the duplicates should be considered while creating a subset.
     * Before discussing the approach in detail, let's first have a quick look at the recursion tree for better understanding:
     *
     * fig
     *
     * The recursion tree illustrating how distinct subsets are created at each function call.Here the numbers in blue indicate the starting position of the nums array where we should start scanning at that function call.
     *
     *
     * The above illustration gives us a rough idea about how we get the solution in a backtracking manner. Note that the order of the subsets in the result is the preorder traversal of the recursion tree. All that is left to do is to code the solution.
     *
     * Start with an empty list and the starting index set to 0. At each function call, add a new subset to the output list of subsets. Scan through all the elements in the nums array from the starting index (written in blue in the above diagram) to the end. Consider one element at a time and decide whether to keep it or not. If we haven't seen the current element before, then add it to the current list and make a recursive function call with the starting index incremented by one. Otherwise, the subset is a duplicate and so we ignore it. Thus, if in a particular function call we scan through k distinct elements, there will be k different branches.
     *
     * Algorithm
     *
     * First, sort the array in ascending order.
     *
     * Use a recursive helper function subsetsWithDupHelper to generate all possible subsets. The subsetsWithDupHelper has the following parameters:
     *
     * Output list of subsets (subsets).
     * Current subset (currentSubset).
     * nums array.
     * the index in the nums array from where we should start scanning elements at that function call (index).
     * At each recursive function call:
     *
     * Add the currentSubset to the subsets list.
     *
     * Iterate over the nums array from index to the array end.
     *
     * If the element is considered for the first time in that function call, add it to the currentSubset list. Make a function call to subsetsWithDupHelper with index = current element position + 1.
     * Otherwise, the element is a duplicate. So we skip it as it will generate duplicate subsets (refer to the figure above).
     * While backtracking, remove the last added element from the currentSubset and continue the iteration.
     * Return subsets list.
     */
    /**
     * Complexity Analysis
     *
     * Here nn is the number of elements present in the given array.
     *
     * Time complexity: O(n \cdot 2 ^ n)O(n⋅2
     * n
     *  )
     *
     * As we can see in the diagram above, this approach does not generate any duplicate subsets. Thus, in the worst case (array consists of nn distinct elements), the total number of recursive function calls will be 2 ^ n2
     * n
     *  . Also, at each function call, a deep copy of the subset currentSubset generated so far is created and added to the subsets list. This will incur an additional O(n)O(n) time (as the maximum number of elements in the currentSubset will be nn). So overall, the time complexity of this approach will be O(n \cdot 2 ^ n)O(n⋅2
     * n
     *  ).
     *
     * Space complexity: O(n)O(n)
     *
     * The space complexity of the sorting algorithm depends on the implementation of each programming language. For instance, in Java, the Arrays.sort() for primitives is implemented as a variant of quicksort algorithm whose space complexity is O(\log n)O(logn). In C++ sort() function provided by STL is a hybrid of Quick Sort, Heap Sort and Insertion Sort with the worst case space complexity of O(\log n)O(logn). Thus the use of inbuilt sort() function adds O(\log n)O(logn) to space complexity.
     *
     * The recursion call stack occupies at most O(n)O(n) space. The output list of subsets is not considered while analyzing space complexity. So, the space complexity of this approach is O(n)O(n).
     * @param args
     */
    public List<List<Integer>> subsetsWithDupBackTracking(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> subsets = new ArrayList<>();
        List<Integer> currentSubset = new ArrayList<>();

        subsetsWithDupHelper(subsets, currentSubset, nums, 0);
        return subsets;
    }

    private void subsetsWithDupHelper(List<List<Integer>> subsets, List<Integer> currentSubset, int[] nums, int index) {
        // Add the subset formed so far to the subsets list.
        subsets.add(new ArrayList<>(currentSubset));

        for (int i = index; i < nums.length; i++) {
            // If the current element is a duplicate, ignore.
            if (i != index && nums[i] == nums[i - 1]) {
                continue;
            }
            currentSubset.add(nums[i]);
            subsetsWithDupHelper(subsets, currentSubset, nums, i + 1);
            currentSubset.remove(currentSubset.size() - 1);
        }
    }

    public static void main(String [] args){
            SubsetsII sii= new SubsetsII();
        List<List<Integer>> result= sii.subsetsWithDup(new int[]{3, 2});
        System.out.println(result);
    }
}
