package com.samueldu.leetcode.topinterviewquestions.LinkedList;

public class PalindromeLinkedListReverseSecondHalfInPlace {
    /**
     *Approach 3: Reverse Second Half In-place
     * Intuition
     *
     * The only way we can avoid using O(n)O(n) extra space is by modifying the input in-place.
     *
     * The strategy we can use is to reverse the second half of the Linked List in-place (modifying the Linked List structure), and then comparing it with the first half. Afterwards, we should re-reverse the second half and put the list back together. While you don't need to restore the list to pass the test cases, it is still good programming practice because the function could be a part of a bigger program that doesn't want the Linked List broken.
     *
     * Algorithm
     *
     * Specifically, the steps we need to do are:
     *
     * Find the end of the first half.
     * Reverse the second half.
     * Determine whether or not there is a palindrome.
     * Restore the list.
     * Return the result.
     * To do step 1, we could count the number of nodes, calculate how many nodes are in the first half, and then iterate back down the list to find the end of the first half. Or, we could do it in a single parse using the two runners pointer technique. Either is acceptable, however we'll have a look at the two runners pointer technique here.
     *
     * Imagine we have 2 runners one fast and one slow, running down the nodes of the Linked List. In each second, the fast runner moves down 2 nodes, and the slow runner just 1 node. By the time the fast runner gets to the end of the list, the slow runner will be half way. By representing the runners as pointers, and moving them down the list at the corresponding speeds, we can use this trick to find the middle of the list, and then split the list into two halves.
     *
     * If there is an odd-number of nodes, then the "middle" node should remain attached to the first half.
     *
     * Step 2 uses the algorithm that can be found in the solution article for the Reverse Linked List problem to reverse the second half of the list.
     *
     * Step 3 is fairly straightforward. Remember that we have the first half, which might also contain a "middle" node at the end, and the second half, which is reversed. We can step down the lists simultaneously ensuring the node values are equal. When the node we're up to in the second list is null, we know we're done. If there was a middle value attached to the end of the first list, it is correctly ignored by the algorithm. The result should be saved, but not returned, as we still need to restore the list.
     *
     * Step 4 requires using the same function you used for step 2, and then for step 5 the saved result should be returned.
     *
     * Complexity Analysis
     *
     * Time complexity : O(n)O(n), where nn is the number of nodes in the Linked List.
     *
     * Similar to the above approaches. Finding the middle is O(n)O(n), reversing a list in place is O(n)O(n), and then comparing the 2 resulting Linked Lists is also O(n)O(n).
     *
     * Space complexity : O(1)O(1).
     *
     * We are changing the next pointers for half of the nodes. This was all memory that had already been allocated, so we are not using any extra memory and therefore it is O(1)O(1).
     *
     * I have seen some people on the discussion forum saying it has to be O(n)O(n) because we're creating a new list. This is incorrect, because we are changing each of the pointers one-by-one, in-place. We are not needing to allocate more than O(1)O(1) extra memory to do this work, and there is O(1)O(1) stack frames going on the stack. It is the same as reversing the values in an Array in place (using the two-pointer technique).
     *
     * The downside of this approach is that in a concurrent environment (multiple threads and processes accessing the same data), access to the Linked List by other threads or processes would have to be locked while this function is running, because the Linked List is temporarily broken. This is a limitation of many in-place algorithms though.
     */



        public boolean isPalindrome(ListNode head) {

            if (head == null) return true;

            // Find the end of first half and reverse second half.
            ListNode firstHalfEnd = endOfFirstHalf(head);
            ListNode secondHalfStart = reverseList(firstHalfEnd.next);

            // Check whether or not there is a palindrome.
            ListNode p1 = head;
            ListNode p2 = secondHalfStart;
            boolean result = true;
            while (result && p2 != null) {
                if (p1.val != p2.val) result = false;
                p1 = p1.next;
                p2 = p2.next;
            }

            // Restore the list and return the result.
            firstHalfEnd.next = reverseList(secondHalfStart);
            return result;
        }

        // Taken from https://leetcode.com/problems/reverse-linked-list/solution/
        private ListNode reverseList(ListNode head) {
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

        private ListNode endOfFirstHalf(ListNode head) {
            ListNode fast = head;
            ListNode slow = head;
            while (fast.next != null && fast.next.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            return slow;
        }

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

}
