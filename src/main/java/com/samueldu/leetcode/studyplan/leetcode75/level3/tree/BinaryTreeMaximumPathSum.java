package com.samueldu.leetcode.studyplan.leetcode75.level3.tree;

/**
 * 124. Binary Tree Maximum Path Sum
 * Hard
 *
 * 11206
 *
 * 580
 *
 * Add to List
 *
 * Share
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
 *
 * The path sum of a path is the sum of the node's values in the path.
 *
 * Given the root of a binary tree, return the maximum path sum of any non-empty path.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3]
 * Output: 6
 * Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
 * Example 2:
 *
 *
 * Input: root = [-10,9,20,null,null,15,7]
 * Output: 42
 * Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [1, 3 * 104].
 * -1000 <= Node.val <= 1000
 *
 * Approach 1: Recursion
 * Intuition
 *
 * First of all, let's simplify the problem and implement a function max_gain(node) which takes a node as an argument and computes a maximum contribution that this node and one/zero of its subtrees could add.
 *
 * In other words, it's a maximum gain one could have including the node (and maybe one of its subtrees) into the path.
 *
 * gains
 *
 * Hence if one would know for sure that the max path contains root, the problem would be solved as max_gain(root). Unfortunately, the max path does not need to go through the root, and here is an example of such a tree
 *
 * gains
 *
 * That means one needs to modify the above function and to check at each step what is better : to continue the current path or to start a new path with the current node as a highest node in this new path.
 *
 * Algorithm
 *
 * Now everything is ready to write down an algorithm.
 *
 * Initiate max_sum as the smallest possible integer and call max_gain(node = root).
 * Implement max_gain(node) with a check to continue the old path/to start a new path:
 * Base case : if node is null, the max gain is 0.
 * Call max_gain recursively for the node children to compute max gain from the left and right subtrees : left_gain = max(max_gain(node.left), 0) and
 * right_gain = max(max_gain(node.right), 0).
 * Now check to continue the old path or to start a new path. To start a new path would cost price_newpath = node.val + left_gain + right_gain. Update max_sum if it's better to start a new path.
 * For the recursion return the max gain the node and one/zero of its subtrees could add to the current path : node.val + max(left_gain, right_gain).
 * Tree Node
 *
 * Here is the definition of the TreeNode which we would use in the following implementation.
 *
 *
 * Implementation
 *
 * Current
 * 1 / 7
 *
 * Complexity Analysis
 *
 * Time complexity: \mathcal{O}(N)O(N), where N is number of nodes, since we visit each node not more than 2 times.
 *
 * Space complexity: \mathcal{O}(H)O(H), where HH is a tree height, to keep the recursion stack. In the average case of balanced tree, the tree height H = \log NH=logN, in the worst case of skewed tree, H = NH=N.
 */
public class BinaryTreeMaximumPathSum {
    int max_sum = Integer.MIN_VALUE;

    public int max_gain(TreeNode node) {
        if (node == null) return 0;

        // max sum on the left and right sub-trees of node
        int left_gain = Math.max(max_gain(node.left), 0);
        int right_gain = Math.max(max_gain(node.right), 0);

        // the price to start a new path where `node` is a highest node
        int price_newpath = node.val + left_gain + right_gain;

        // update max_sum if it's better to start a new path
        max_sum = Math.max(max_sum, price_newpath);

        // for recursion :
        // return the max gain if continue the same path
        return node.val + Math.max(left_gain, right_gain);
    }

    public int maxPathSum(TreeNode root) {
        max_gain(root);
        return max_sum;
    }

}
