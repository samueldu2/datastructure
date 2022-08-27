package com.samueldu.leetcode.studyplan.leetcode75.level3.linkedlist;

/**
 *Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4]
 * Output: [2,1,4,3]
 * Example 2:
 *
 * Input: head = []
 * Output: []
 * Example 3:
 *
 * Input: head = [1]
 * Output: [1]
 *
 *
 */
public class SwapNodeInPairs {
    public ListNode swapPairs(ListNode head) {

        // If the list has no node or has only one node left.
        if ((head == null) || (head.next == null)) {
            return head;
        }

        // Nodes to be swapped
        ListNode firstNode = head;
        ListNode secondNode = head.next;

        // Swapping
        // by setting firstNode.next to secondNode.next, we jump 2 nodes (or a pair fo node) at a time
        // by calling swapPairs recursively, we demand the next pair to be swapped first recursively downwards.
        firstNode.next  = swapPairs(secondNode.next);
        secondNode.next = firstNode;

        // Now the head is the second node
        // the new head node after swap is assigned to firstNode.next of the previous call.
        return secondNode;
    }
}
