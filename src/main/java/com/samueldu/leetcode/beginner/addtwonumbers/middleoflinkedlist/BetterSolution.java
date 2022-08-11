package com.samueldu.leetcode.beginner.addtwonumbers.middleoflinkedlist;

/**
 * When traversing the list with a pointer slow, make another pointer fast that traverses twice as fast.
 * When fast reaches the end of the list, slow must be in the middle.
 */

public class BetterSolution {

        public ListNode middleNode(ListNode head) {
            ListNode slow = head;
            ListNode fast = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

}
