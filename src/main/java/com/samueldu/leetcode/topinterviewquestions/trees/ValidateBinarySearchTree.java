package com.samueldu.leetcode.topinterviewquestions.trees;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Validate Binary Search Tree
 *
 * Solution
 * Given the root of a binary tree, determine if it is a valid binary search tree (BST).
 *
 * A valid BST is defined as follows:
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 *
 * Example 1:
 *
 *
 * Input: root = [2,1,3]
 * Output: true
 * Example 2:
 *
 *
 * Input: root = [5,1,4,null,null,3,6]
 * Output: false
 * Explanation: The root node's value is 5 but its right child's value is 4.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 104].
 * -231 <= Node.val <= 231 - 1
 */
public class ValidateBinarySearchTree {

    /**
     *Approach 1: Recursive Traversal with Valid Range
     * The idea above could be implemented as a recursion. One compares the node value with its upper and lower limits if they are available. Then one repeats the same step recursively for left and right subtrees.
     *
     * Current
     * 1 / 4
     *
     * Complexity Analysis
     *
     * Time complexity : \mathcal{O}(N)O(N) since we visit each node exactly once.
     * Space complexity : \mathcal{O}(N)O(N) since we keep up to the entire tree.
     */
    public boolean validateUsingRange(TreeNode root, Integer low, Integer high) {
        // Empty trees are valid BSTs.
        if (root == null) {
            return true;
        }
        // The current node's value must be between low and high.
        if ((low != null && root.val <= low) || (high != null && root.val >= high)) {
            return false;
        }
        // The left and right subtree must also be valid.
        return validateUsingRange(root.right, root.val, high) && validateUsingRange(root.left, low, root.val);
    }

    public boolean isValidBST(TreeNode root) {
        return validateUsingRange(root, null, null);
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
     * Approach 3: Recursive Inorder Traversal
     * Algorithm
     *
     * Let's use the order of nodes in the inorder traversal Left -> Node -> Right.
     *
     * postorder
     *
     * Here the nodes are enumerated in the order you visit them, and you could follow 1-2-3-4-5 to compare different strategies.
     *
     * Left -> Node -> Right order of inorder traversal means for BST that each element should be smaller than the next one.
     *
     * Hence the algorithm with \mathcal{O}(N)O(N) time complexity and \mathcal{O}(N)O(N) space complexity could be simple:
     *
     * Compute inorder traversal list inorder.
     *
     * Check if each element in inorder is smaller than the next one.
     *
     * postorder
     *
     * Do we need to keep the whole inorder traversal list?
     *
     * Actually, no. The last added inorder element is enough to ensure at each step that the tree is BST (or not). Hence one could merge both steps into one and reduce the used space.
     */
    // We use Integer instead of int as it supports a null value.
    private Integer prev;

    public boolean isValidBSTInOrderTransversal(TreeNode root) {
        prev = null;
        return inorder(root);
    }

    private boolean inorder(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!inorder(root.left)) {
            return false;
        }
        if (prev != null && root.val <= prev) {
            return false;
        }
        prev = root.val;
        return inorder(root.right);
    }


    /**
     * Approach 2: Iterative Traversal with Valid Range
     * The above recursion could be converted into iteration, with the help of an explicit stack. DFS would be better than BFS since it works faster here.
     *
     *
     * Complexity Analysis
     *
     * Time complexity : \mathcal{O}(N)O(N) since we visit each node exactly once.
     * Space complexity : \mathcal{O}(N)O(N) since we keep up to the entire tree.
     */



        private Deque<TreeNode> stack = new LinkedList();
        private Deque<Integer> upperLimits = new LinkedList();
        private Deque<Integer> lowerLimits = new LinkedList();

        public void update(TreeNode root, Integer low, Integer high) {
            stack.add(root);
            lowerLimits.add(low);
            upperLimits.add(high);
        }

        public boolean isValidBSTIterativeTransversalWithValidRange(TreeNode root) {
            Integer low = null, high = null, val;
            update(root, low, high);

            while (!stack.isEmpty()) {
                root = stack.poll();
                low = lowerLimits.poll();
                high = upperLimits.poll();

                if (root == null) continue;
                val = root.val;
                if (low != null && val <= low) {
                    return false;
                }
                if (high != null && val >= high) {
                    return false;
                }
                update(root.right, val, high);
                update(root.left, low, val);
            }
            return true;
        }


}
