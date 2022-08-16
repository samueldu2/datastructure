package com.samueldu.leetcode.studyplan.leetcode75.level3.bitOperations.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].
 *
 * You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 4, k = 2
 * Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
 * Explanation: There are 4 choose 2 = 6 total combinations.
 * Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same combination.
 * Example 2:
 *
 * Input: n = 1, k = 1
 * Output: [[1]]
 * Explanation: There is 1 choose 1 = 1 total combination.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 20
 * 1 <= k <= n
 */
public class Combinations {
    List<List<Integer>> output = new LinkedList();
    int n;
    int k;

    /**
     * Complexity Analysis
     *
     * Time complexity : \mathcal{O}(k C_N^k)O(kC
     * N
     * k
     * ​
     *  ), where C_N^k = \frac{N!}{(N - k)! k!}C
     * N
     * k
     * ​
     *  =
     * (N−k)!k!
     * N!
     * ​
     *   is a number of combinations to build. append / pop (add / removeLast) operations are constant-time ones and the only consuming part here is to append the built combination of length k to the output.
     *
     * Space complexity : \mathcal{O}(C_N^k)O(C
     * N
     * k
     * ​
     *  ) to keep all the combinations for an output.
     * @param first
     * @param curr
     */
    public void backtrack(int first, LinkedList<Integer> curr) {
        // if the combination is done
        if (curr.size() == k)
            output.add(new LinkedList(curr));

        for (int i = first; i < n + 1; ++i) {
            // add i into the current combination
            curr.add(i);
            // use next integers to complete the combination
            //this back tracking automatically removed all integers smaller than i, for which combinations are covered already.
            backtrack(i + 1, curr);
            // backtrack
            // it is important to revert all state variables to before state.
            curr.removeLast();
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        this.n = n;
        this.k = k;
        backtrack(1, new LinkedList<Integer>());
        return output;
    }

        public static void main(String [] args){
            Combinations c=new Combinations();
           System.out.println(c.combineLexicographic(4, 2));

        }

    /**
     * Algorithm
     *
     * The algorithm is quite straightforward :
     *
     * Initiate nums as a list of integers from 1 to k. Add n + 1 as a last element, it will serve as a sentinel. Set the pointer in the beginning of the list j = 0.
     *
     * While j < k :
     *
     * Add the first k elements from nums into the output, i.e. all elements but the sentinel.
     *
     * Find the first number in nums such that nums[j] + 1 != nums[j + 1] and increase it by one nums[j]++ to move to the next combination.
     *
     * Implementation
     *
     *
     * Complexity Analysis
     *
     * Time complexity : \mathcal{O}(k C_N^k)O(kC
     * N
     * k
     * ​
     *  ), where C_N^k = \frac{N!}{(N - k)! k!}C
     * N
     * k
     * ​
     *  =
     * (N−k)!k!
     * N!
     * ​
     *   is a number of combinations to build.
     *
     * The external while loop is executed C_N^kC
     * N
     * k
     * ​
     *   times since the number of combinations is C_N^kC
     * N
     * k
     * ​
     *  . The inner while loop is performed C_{N - j}^{k - j}C
     * N−j
     * k−j
     * ​
     *   times for a given j. In average over C_N^kC
     * N
     * k
     * ​
     *   visits from the external loop that results in less than one execution per visit.
     * Hence the most consuming part here is to append each combination of length kk (C_N^kC
     * N
     * k
     * ​
     *   combinations in total) to the output, that takes \mathcal{O}(k C_N^k)O(kC
     * N
     * k
     * ​
     *  ) time.
     *
     * You could notice that the second algorithm is much faster than the first one despite they both have the same time complexity. It's a consequence of dealing with the recursive call stack frame for the first algorithm, and the effect is much more pronounced in Python than in Java.
     *
     * Space complexity : \mathcal{O}(C_N^k)O(C
     * N
     * k
     * ​
     *  ) to keep all the combinations for an output.
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combineLexicographic (int n, int k) {
        // init first combination
        LinkedList<Integer> nums = new LinkedList<Integer>();
        for(int i = 1; i < k + 1; ++i)
            nums.add(i);
        nums.add(n + 1);

        List<List<Integer>> output = new ArrayList<List<Integer>>();
        int j = 0;
        while (j < k) {
            // add current combination
            output.add(new LinkedList(nums.subList(0, k)));
            // increase first nums[j] by one
            // if nums[j] + 1 != nums[j + 1]
            j = 0;
            while ((j < k) && (nums.get(j + 1) == nums.get(j) + 1))
                nums.set(j, j++ + 1);
            nums.set(j, nums.get(j) + 1);
        }
        return output;
    }
}
