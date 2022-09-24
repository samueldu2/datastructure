package com.samueldu.leetcode.topinterviewquestions.trees;

import java.util.Random;

public class ConvertSortedArrayToBinarySearchTreePreOrderTransversalChooseRandomMiddleNodeAsARoot {
    /**
     * Approach 3: Preorder Traversal: Choose Random Middle Node as a Root
     * This one is for fun. Instead of predefined choice we will pick randomly left or right middle node at each step. Each run will result in different solution and they all will be accepted.
     *
     * postorder
     *
     * Algorithm
     *
     * Implement helper function helper(left, right), which constructs BST from nums elements between indexes left and right:
     *
     * If left > right, then there is no elements available for that subtree. Return None.
     *
     * Pick random middle element:
     *
     * p = (left + right) // 2.
     *
     * If left + right is odd, add randomly 0 or 1 to p-index.
     *
     * Initiate the root: root = TreeNode(nums[p]).
     *
     * Compute recursively left and right subtrees: root.left = helper(left, p - 1), root.right = helper(p + 1, right).
     *
     * Return helper(0, len(nums) - 1).
     *
     * Complexity Analysis
     *
     * Time complexity: O(N)O(N) since we visit each node exactly once.
     *
     * Space complexity: O(\log N)O(logN).
     *
     * The recursion stack requires O(\log N)O(logN) space because the tree is height-balanced. Note that the O(N)O(N) space used to store the output does not count as auxiliary space, so it is not included in the space complexity.
     */

        int[] nums;
        Random rand = new Random();

        public TreeNode helper(int left, int right) {
            if (left > right) return null;

            // choose random middle node as a root
            int p = (left + right) / 2;
            if ((left + right) % 2 == 1) p += rand.nextInt(2);

            // preorder traversal: node -> left -> right
            TreeNode root = new TreeNode(nums[p]);
            root.left = helper(left, p - 1);
            root.right = helper(p + 1, right);
            return root;
        }

        public TreeNode sortedArrayToBST(int[] nums) {
            this.nums = nums;
            return helper(0, nums.length - 1);
        }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
