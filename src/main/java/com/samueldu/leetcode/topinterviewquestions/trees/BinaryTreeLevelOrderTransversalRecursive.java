package com.samueldu.leetcode.topinterviewquestions.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[9,20],[15,7]]
 * Example 2:
 *
 * Input: root = [1]
 * Output: [[1]]
 * Example 3:
 *
 * Input: root = []
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 2000].
 * -1000 <= Node.val <= 1000
 */
public class BinaryTreeLevelOrderTransversalRecursive {
/**
 * How to traverse the tree
 * There are two general strategies to traverse a tree:
 *
 * Depth First Search (DFS)
 *
 * In this strategy, we adopt the depth as the priority, so that one would start from a root and reach all the way down to certain leaf, and then back to root to reach another branch.
 *
 * The DFS strategy can further be distinguished as preorder, inorder, and postorder depending on the relative order among the root node, left node and right node.
 *
 * Breadth First Search (BFS)
 *
 * We scan through the tree level by level, following the order of height, from top to bottom. The nodes on higher level would be visited before the ones with lower levels.
 *
 * On the following figure the nodes are numerated in the order you visit them, please follow 1-2-3-4-5 to compare different strategies.
 *
 * postorder
 *
 * Here the problem is to implement split-level BFS traversal : [[1], [2, 3], [4, 5]].
 */
/**
 *
 */

List<List<Integer>> levels = new ArrayList<List<Integer>>();

    /**
     * Complexity Analysis
     *
     * Time complexity : \mathcal{O}(N)O(N) since each node is processed exactly once.
     *
     * Space complexity : \mathcal{O}(N)O(N) to keep the output structure which contains N node values.
     * @param node
     * @param level
     */
    public void helper(TreeNode node, int level) {
        // start the current level
        if (levels.size() == level)
            levels.add(new ArrayList<Integer>());

        // fulfil the current level
        levels.get(level).add(node.val);

        // process child nodes for the next level
        if (node.left != null)
            helper(node.left, level + 1);
        if (node.right != null)
            helper(node.right, level + 1);
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return levels;
        helper(root, 0);
        return levels;
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
