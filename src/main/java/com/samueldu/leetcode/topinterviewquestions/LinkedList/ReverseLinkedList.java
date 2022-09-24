package com.samueldu.leetcode.topinterviewquestions.LinkedList;

/**
 * Reverse Linked List
 *
 * Solution
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 * Example 2:
 *
 *
 * Input: head = [1,2]
 * Output: [2,1]
 * Example 3:
 *
 * Input: head = []
 * Output: []
 *
 * Constraints:
 *
 * The number of nodes in the list is the range [0, 5000].
 * -5000 <= Node.val <= 5000
 */
public class ReverseLinkedList {

    /**
     * Approach 1: Iterative
     * Intuition
     *
     * Assume that we have linked list 1 → 2 → 3 → Ø, we would like to change it to Ø ← 1 ← 2 ← 3.
     *
     * While traversing the list, we can change the current node's next pointer to point to its previous element. Since a node does not have reference to its previous node, we must store its previous element beforehand. We also need another pointer to store the next node before changing the reference. Do not forget to return the new head reference at the end!
     *
     * Implementation
     *
     *
     * Complexity analysis
     *
     * Time complexity : O(n)O(n). Assume that nn is the list's length, the time complexity is O(n)O(n).
     *
     * Space complexity : O(1)O(1).
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    /**
     *Approach 2: Recursive
     * Intuition
     *
     * The recursive version is slightly trickier and the key is to work backwards. Assume that the rest of the list had already been reversed, now how do we reverse the front part? Let's assume the list is: n1 → … → nk-1 → nk → nk+1 → … → nm → Ø
     *
     * Assume from node nk+1 to nm had been reversed and we are at node nk.
     *
     * n1 → … → nk-1 → nk → nk+1 ← … ← nm
     *
     * We want nk+1’s next node to point to nk.
     *
     * So,
     *
     * nk.next.next = nk;
     *
     * Be very careful that n1's next must point to Ø. If you forget about this, your linked list will have a cycle in it. This bug could be caught if you test your code with a linked list of size 2.
     *
     * Implementation
     *
     *
     * Complexity Analysis
     *
     * Time complexity : O(n)O(n). Assume that nn is the list's length, the time complexity is O(n)O(n).
     *
     * Space complexity : O(n)O(n). The extra space comes from implicit stack space due to recursion. The recursion could go up to nn levels deep.
     *
     * Awesome visual explanation of recursive approach :
     * https://www.youtube.com/watch?v=S92RuTtt9EE
     */

    public ListNode reverseListRecursive(ListNode node) {
        if (node == null ) {//this handles special situation when a null list is passed in.
            return node;
        }
        if(node.next == null) { //we reach here only when we are at the last node of the list.
            return node;
        }
        ListNode newHead = reverseListRecursive(node.next);
        node.next.next = node; //reverse the pointer of the next node.
        node.next = null; // the new end of the list must point to null.
        return newHead;
    }
}
