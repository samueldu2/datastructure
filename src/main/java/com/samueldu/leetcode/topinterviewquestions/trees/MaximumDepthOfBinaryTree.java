package com.samueldu.leetcode.topinterviewquestions.trees;

import java.util.LinkedList;
import java.util.Stack;

public class MaximumDepthOfBinaryTree {
    /**
     * Complexity analysis
     *
     * Time complexity : we visit each node exactly once, thus the time complexity is \mathcal{O}(N)O(N), where NN is the number of nodes.
     *
     * Space complexity : in the worst case, the tree is completely unbalanced, e.g. each node has only left child node, the recursion call would occur NN times (the height of the tree), therefore the storage to keep the call stack would be \mathcal{O}(N)O(N). But in the best case (the tree is completely balanced), the height of the tree would be \log(N)log(N). Therefore, the space complexity in this case would be \mathcal{O}(\log(N))O(log(N)).
     * @param root
     * @return
     */
    public int maxDepthRecursive(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int left_height = maxDepthRecursive(root.left);
            int right_height = maxDepthRecursive(root.right);
            return java.lang.Math.max(left_height, right_height) + 1;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * Approach 3: Iteration
     * Intuition
     *
     * We could also convert the above recursion into iteration, with the help of the stack data structure. Similar with the behaviors of the function call stack, the stack data structure follows the pattern of FILO (First-In-Last-Out), i.e. the last element that is added to a stack would come out first.
     *
     * With the help of the stack data structure, one could mimic the behaviors of function call stack that is involved in the recursion, to convert a recursive function to a function with iteration.
     *
     * Algorithm
     *
     * The idea is to keep the next nodes to visit in a stack. Due to the FILO behavior of stack, one would get the order of visit same as the one in recursion.
     *
     * We start from a stack which contains the root node and the corresponding depth which is 1. Then we proceed to the iterations: pop the current node out of the stack and push the child nodes. The depth is updated at each step.
     *
     * Complexity analysis
     *
     * Time complexity : \mathcal{O}(N)O(N).
     *
     * Space complexity : in the worst case, the tree is completely unbalanced, e.g. each node has only left child node, the recursion call would occur NN times (the height of the tree), therefore the storage to keep the call stack would be \mathcal{O}(N)O(N). But in the average case (the tree is balanced), the height of the tree would be \log(N)log(N). Therefore, the space complexity in this case would be \mathcal{O}(\log(N))O(log(N)).
     */


        public int maxDepthIterative(TreeNode root) {
            Stack<TreeNode> stack = new Stack<>();
            Stack<Integer> depths = new Stack<>();
            if (root == null) return 0;

            stack.push(root);
            depths.add(1);

            int depth = 0, current_depth = 0;
            while(!stack.isEmpty()) {
                root = stack.pop();
                current_depth = depths.pop();
                if (root != null) {
                    depth = Math.max(depth, current_depth);
                    stack.push(root.left);
                    stack.push(root.right);
                    depths.push(current_depth + 1);
                    depths.push(current_depth + 1);
                }
            }
            return depth;
        }

}
