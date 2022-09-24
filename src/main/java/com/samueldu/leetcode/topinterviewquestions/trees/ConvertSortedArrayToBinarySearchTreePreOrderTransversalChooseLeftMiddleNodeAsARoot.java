package com.samueldu.leetcode.topinterviewquestions.trees;

/**
 * Given an integer array nums where the elements are sorted in ascending order, convert it to a height-balanced binary search tree.
 *
 * A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node never differs by more than one.
 *
 * Input: nums = [-10,-3,0,5,9]
 * Output: [0,-3,9,-10,null,5]
 * Explanation: [0,-10,5,null,-3,null,9] is also accepted:
 *
 * Example 2:
 *
 *
 * Input: nums = [1,3]
 * Output: [3,1]
 * Explanation: [1,null,3] and [3,1] are both height-balanced BSTs.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums is sorted in a strictly increasing order.
 */
public class ConvertSortedArrayToBinarySearchTreePreOrderTransversalChooseLeftMiddleNodeAsARoot {

    /**
     * Approach 1: Preorder Traversal: Always Choose Left Middle Node as a Root
     * Algorithm
     *
     * postorder
     *
     * Implement helper function helper(left, right), which constructs BST from nums elements between indexes left and right:
     *
     * If left > right, then there is no elements available for that subtree. Return None.
     *
     * Pick left middle element: p = (left + right) // 2.
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

    public TreeNode helper(int left, int right) {
        if (left > right) return null;

        // always choose left middle node as a root
        int p = (left + right) / 2;

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
