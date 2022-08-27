package com.samueldu.leetcode.studyplan.leetcode75.level3.linkedlist;

/**
 * 25. Reverse Nodes in k-Group
 * Hard
 *
 * 8738
 *
 * 530
 *
 * Add to List
 *
 * Share
 * Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.
 *
 * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.
 *
 * You may not alter the values in the list's nodes, only nodes themselves may be changed.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [2,1,4,3,5]
 * Example 2:
 *
 *
 * Input: head = [1,2,3,4,5], k = 3
 * Output: [3,2,1,4,5]
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is n.
 * 1 <= k <= n <= 5000
 * 0 <= Node.val <= 1000
 *
 * The problem statement clearly mentions that we are not to use any additional space for our solution. So naturally, a recursive solution is not acceptable here because of the space utilized by the recursion stack. However, for the sake of completeness, we shall go over the recursive approach first before moving on to the iterative approach. The interviewer may not specify the space constraint initially and so, a recursive solution would be a quick first approach followed by the iterative version.
 *
 * A Linked list is a recursive structure. A sub-list in itself is a linked list. So, if you think about it, reversing a list consisting of k nodes is simply a linked list reversal algorithm. So, before we look at our actual approaches, we need to know that we will essentially be making use of a linked list reversal function here. There are many ways of reversing a linked list. A lot of programmers like to reverse the links themselves for reversing a linked list. What I personally like to do is to combine linked list traversal with insertion in beginning.
 *
 * Say the linked list we need to reverse has the starting node called head.
 * Now, we will consider another pointer which will act as the head of the reversed linked list. Let's call this rev_head.
 * We will use a pointer, ptr to traverse the original list.
 * For every element, we basically insert it at the beginning of the reverse list which has rev_head as its head.
 * That's it! We keep on moving ptr one step forward and keep inserting the nodes in the beginning of our reverse list and we will end up reversing the entire list.
 *
 * Now that we have the basic linked list reversal stuff out of the way, we can move on with our actual problem which is to reverse the linked list, k nodes at a time. The basic idea is to make use of our reversal function for a linked list. Usually, we start with the head of the list and keep running the reversal algorithm all the way to the end. However, in this case, we will only process k nodes.
 *
 * However, the problem statement also mentions that if there are < k nodes left in the linked list, then we don't have to reverse them. This implies that we first need to count k nodes before we get on with our reversal. If at any point, we find that we don't have k nodes, then we don't reverse that portion of the linked list. Right off the bat, this implies at least two traversals of the list overall. One for counting, and the next, for reversals.
 *
 * Complexity Analysis
 *
 * Time Complexity: O(N)O(N) since we process each node exactly twice. Once when we are counting the number of nodes in each recursive call, and then once when we are actually reversing the sub-list. A slightly optimized implementation here could be that we don't count the number of nodes at all and simply reverse k nodes. If at any point we find that we didn't have enough nodes, we can re-reverse the last set of nodes so as to keep the original structure as required by the problem statement. That ways, we can get rid of the extra counting.
 * Space Complexity: O(N/k)O(N/k) used up by the recursion stack. The number of recursion calls is determined by both kk and NN. In every recursive call, we process kk nodes and then make a recursive call to process the rest.
 *
 */
public class ReverseNodesInKGroupRecursive {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
    */

        public ListNode reverseLinkedList(ListNode head, int k) {

            // Reverse k nodes of the given linked list.
            // This function assumes that the list contains
            // at least k nodes.
            ListNode new_head = null;
            ListNode ptr = head;

            while (k > 0) {

                // Keep track of the next node to process in the
                // original list
                ListNode next_node = ptr.next;

                // Insert the node pointed to by "ptr"
                // at the beginning of the reversed list
                ptr.next = new_head;
                new_head = ptr;

                // Move on to the next node
                ptr = next_node;

                // Decrement the count of nodes to be reversed by 1
                k--;
            }


            // Return the head of the reversed list
            return new_head;

        }

        public ListNode reverseKGroup(ListNode head, int k) {

            int count = 0;
            ListNode ptr = head;

            // First, see if there are atleast k nodes
            // left in the linked list.
            while (count < k && ptr != null) {
                ptr = ptr.next;
                count++;
            }


            // If we have k nodes, then we reverse them
            if (count == k) {

                // Reverse the first k nodes of the list and
                // get the reversed list's head.
                ListNode reversedHead = this.reverseLinkedList(head, k);

                // Now recurse on the remaining linked list. Since
                // our recursion returns the head of the overall processed
                // list, we use that and the "original" head of the "k" nodes
                // to re-wire the connections.
                head.next = this.reverseKGroup(ptr, k);
                return reversedHead;
            }

            return head;
        }



}
