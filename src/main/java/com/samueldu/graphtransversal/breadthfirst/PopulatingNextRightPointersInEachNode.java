package com.samueldu.graphtransversal.breadthfirst;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Populating Next Right Pointers in Each Node
 *
 * Solution
 * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 *
 * Initially, all next pointers are set to NULL.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: root = [1,2,3,4,5,6,7]
 * Output: [1,#,2,3,#,4,5,6,7,#]
 * Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B. The serialized output is in level order as connected by the next pointers, with '#' signifying the end of each level.
 * Example 2:
 *
 * Input: root = []
 * Output: []
 *
 *
 * Constraints:
 *
 * The number of nodes in the tree is in the range [0, 212 - 1].
 * -1000 <= Node.val <= 1000
 *
 *
 * Follow-up:
 *
 * You may only use constant extra space.
 * The recursive approach is fine. You may assume implicit stack space does not count as extra space for this problem.
 */
public class PopulatingNextRightPointersInEachNode {

        public Node connect(Node root) {

            if (root == null) {
                return root;
            }

            // Initialize a queue data structure which contains
            // just the root of the tree
            Queue<Node> Q = new LinkedList<Node>();
            Q.add(root);

            // Outer while loop which iterates over
            // each level
            while (Q.size() > 0) {

                // Note the size of the queue
                int size = Q.size();

                // Iterate over all the nodes on the current level
                for(int i = 0; i < size; i++) {

                    // Pop a node from the front of the queue
                    Node node = Q.poll();

                    // This check is important. We don't want to
                    // establish any wrong connections. The queue will
                    // contain nodes from 2 levels at most at any
                    // point in time. This check ensures we only
                    // don't establish next pointers beyond the end
                    // of a level
                    if (i < size - 1) {
                        node.next = Q.peek();
                    }

                    // Add the children, if any, to the back of
                    // the queue
                    if (node.left != null) {
                        Q.add(node.left);
                    }
                    if (node.right != null) {
                        Q.add(node.right);
                    }
                }
            }

            // Since the tree has now been modified, return the root node
            return root;
        }
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

}

