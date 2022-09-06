package com.samueldu.leetcode.studyplan.leetcode75.level3.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreeZigzagLevelOrderTransversal {
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     *
     * Solution
     * Approach 1: BFS (Breadth-First Search)
     * Intuition
     *
     * Following the description of the problem, the most intuitive solution would be the BFS (Breadth-First Search) approach through which we traverse the tree level-by-level.
     *
     * The default ordering of BFS within a single level is from left to right. As a result, we should adjust the BFS algorithm a bit to generate the desired zigzag ordering.
     *
     * One of the keys here is to store the values that are of the same level with the deque (double-ended queue) data structure, where we could add new values on either end of a queue.
     *
     * So if we want to have the ordering of FIFO (first-in-first-out), we simply append the new elements to the tail of the queue, i.e. the late comers stand last in the queue. While if we want to have the ordering of FILO (first-in-last-out), we insert the new elements to the head of the queue, i.e. the late comers jump the queue.
     *
     * pic
     *
     * Algorithm
     *
     * There are several ways to implement the BFS algorithm.
     *
     * One way would be that we run a two-level nested loop, with the outer loop iterating each level on the tree, and with the inner loop iterating each node within a single level.
     * We could also implement BFS with a single loop though. The trick is that we append the nodes to be visited into a queue and we separate nodes of different levels with a sort of delimiter (e.g. an empty node). The delimiter marks the end of a level, as well as the beginning of a new level.
     * Here we adopt the second approach above. One can start with the normal BFS algorithm, upon which we add a touch of zigzag order with the help of deque. For each level, we start from an empty deque container to hold all the values of the same level. Depending on the ordering of each level, i.e. either from-left-to-right or from-right-to-left, we decide at which end of the deque to add the new element:
     *
     * pic
     *
     * For the ordering of from-left-to-right (FIFO), we append the new element to the tail of the queue, so that the element that comes late would get out late as well. As we can see from the above graph, given an input sequence of [1, 2, 3, 4, 5], with FIFO ordering, we would have an output sequence of [1, 2, 3, 4, 5].
     *
     * For the ordering of from-right-to-left (FILO), we insert the new element to the head of the queue, so that the element that comes late would get out first. With the same input sequence of [1, 2, 3, 4, 5], with FILO ordering, we would obtain an output sequence of [5, 4, 3, 2, 1].
     *
     * Note: as an alternative approach, one can also implement the normal BFS algorithm first, which would generate the ordering of from-left-to-right for each of the levels. Then, at the end of the algorithm, we can simply reverse the ordering of certain levels, following the zigzag steps.
     *
     * Complexity Analysis
     *
     * Time Complexity: \mathcal{O}(N)O(N), where NN is the number of nodes in the tree.
     *
     * We visit each node once and only once.
     *
     * In addition, the insertion operation on either end of the deque takes a constant time, rather than using the array/list data structure where the inserting at the head could take the \mathcal{O}(K)O(K) time where KK is the length of the list.
     *
     * Space Complexity: \mathcal{O}(N)O(N) where NN is the number of nodes in the tree.
     *
     * The main memory consumption of the algorithm is the node_queue that we use for the loop, apart from the array that we use to keep the final output.
     *
     * As one can see, at any given moment, the node_queue would hold the nodes that are at most across two levels. Therefore, at most, the size of the queue would be no more than 2 \cdot L2⋅L, assuming LL is the maximum number of nodes that might reside on the same level. Since we have a binary tree, the level that contains the most nodes could occur to consist all the leave nodes in a full binary tree, which is roughly L = \frac{N}{2}L=
     * 2
     * N
     * ​
     *  . As a result, we have the space complexity of 2 \cdot \frac{N}{2} = N2⋅
     * 2
     * N
     * ​
     *  =N in the worst case.
     *
     */

        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            if (root == null) {
                return new ArrayList<List<Integer>>();
            }

            List<List<Integer>> results = new ArrayList<List<Integer>>();

            // add the root element with a delimiter to kick off the BFS loop
            LinkedList<TreeNode> node_queue = new LinkedList<TreeNode>();
            node_queue.addLast(root);
            //add null "delimiter" for end of root level.
            node_queue.addLast(null);

            LinkedList<Integer> level_list = new LinkedList<Integer>();
            boolean is_order_left = true;

            while (node_queue.size() > 0) {
                TreeNode curr_node = node_queue.pollFirst();
                if (curr_node != null) {
                    if (is_order_left)
                        level_list.addLast(curr_node.val);
                    else
                        level_list.addFirst(curr_node.val);

                    if (curr_node.left != null)
                        node_queue.addLast(curr_node.left);
                    if (curr_node.right != null)
                        node_queue.addLast(curr_node.right);

                } else {
                    // we finish the scan of one level
                    results.add(level_list);

                    /**
                     * this is the list only for this level, it's order is affected by the zigzag flag is_order_left.
                     */
                    level_list = new LinkedList<Integer>();
                    // prepare for the next level
                    /**
                     * added null "delimiter" for end of this level.
                     */
                    if (node_queue.size() > 0)
                        node_queue.addLast(null);
                    //the zig zag flag.
                    is_order_left = !is_order_left;
                }
            }
            return results;
        }

}
