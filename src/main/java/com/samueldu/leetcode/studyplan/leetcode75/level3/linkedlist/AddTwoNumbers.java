package com.samueldu.leetcode.studyplan.leetcode75.level3.linkedlist;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 * Example 2:
 *
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 * Example 3:
 *
 * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * Output: [8,9,9,9,0,0,0,1]
 *
 *
 * Constraints:
 *
 * The number of nodes in each linked list is in the range [1, 100].
 * 0 <= Node.val <= 9
 * It is guaranteed that the list represents a number that does not have leading zeros.
 *
 * Approach 1: Elementary Math
 * Intuition
 *
 * Keep track of the carry using a variable and simulate digits-by-digits sum starting from the head of list, which contains the least-significant digit.
 *
 * Illustration of Adding two numbers
 *
 * Figure 1. Visualization of the addition of two numbers: 342 + 465 = 807342+465=807.
 * Each node contains a single digit and the digits are stored in reverse order.
 *
 * Algorithm
 *
 * Just like how you would sum two numbers on a piece of paper, we begin by summing the least-significant digits, which is the head of l1l1 and l2l2. Since each digit is in the range of 0 \ldots 90…9, summing two digits may "overflow". For example 5 + 7 = 125+7=12. In this case, we set the current digit to 22 and bring over the carry = 1carry=1 to the next iteration. carrycarry must be either 00 or 11 because the largest possible sum of two digits (including the carry) is 9 + 9 + 1 = 199+9+1=19.
 *
 * The pseudocode is as following:
 *
 * Initialize current node to dummy head of the returning list.
 * Initialize carry to 00.
 * Loop through lists l1l1 and l2l2 until you reach both ends and crarry is 00.
 * Set xx to node l1l1's value. If l1l1 has reached the end of l1l1, set to 00.
 * Set yy to node l2l2's value. If l2l2 has reached the end of l2l2, set to 00.
 * Set sum = x + y + carrysum=x+y+carry.
 * Update carry = sum / 10carry=sum/10.
 * Create a new node with the digit value of (sum \bmod 10)(summod10) and set it to current node's next, then advance current node to next.
 * Advance both l1l1 and l2l2.
 * Return dummy head's next node.
 * Note that we use a dummy head to simplify the code. Without a dummy head, you would have to write extra conditional statements to initialize the head's value.
 *
 * Take extra caution of the following cases:
 *
 * Test case	Explanation
 * l1=[0,1]l1=[0,1]
 * l2=[0,1,2]l2=[0,1,2]	When one list is longer than the other.
 * l1=[]l1=[]
 * l2=[0,1]l2=[0,1]	When one list is null, which means an empty list.
 * l1=[9,9]l1=[9,9]
 * l2=[1]l2=[1]	The sum could have an extra carry of one at the end, which is easy to forget.
 *
 * Approach 1: Elementary Math
 * Intuition
 *
 * Keep track of the carry using a variable and simulate digits-by-digits sum starting from the head of list, which contains the least-significant digit.
 *
 * Illustration of Adding two numbers
 *
 * Figure 1. Visualization of the addition of two numbers: 342 + 465 = 807342+465=807.
 * Each node contains a single digit and the digits are stored in reverse order.
 *
 * Algorithm
 *
 * Just like how you would sum two numbers on a piece of paper, we begin by summing the least-significant digits, which is the head of l1l1 and l2l2. Since each digit is in the range of 0 \ldots 90…9, summing two digits may "overflow". For example 5 + 7 = 125+7=12. In this case, we set the current digit to 22 and bring over the carry = 1carry=1 to the next iteration. carrycarry must be either 00 or 11 because the largest possible sum of two digits (including the carry) is 9 + 9 + 1 = 199+9+1=19.
 *
 * The pseudocode is as following:
 *
 * Initialize current node to dummy head of the returning list.
 * Initialize carry to 00.
 * Loop through lists l1l1 and l2l2 until you reach both ends and crarry is 00.
 * Set xx to node l1l1's value. If l1l1 has reached the end of l1l1, set to 00.
 * Set yy to node l2l2's value. If l2l2 has reached the end of l2l2, set to 00.
 * Set sum = x + y + carrysum=x+y+carry.
 * Update carry = sum / 10carry=sum/10.
 * Create a new node with the digit value of (sum \bmod 10)(summod10) and set it to current node's next, then advance current node to next.
 * Advance both l1l1 and l2l2.
 * Return dummy head's next node.
 * Note that we use a dummy head to simplify the code. Without a dummy head, you would have to write extra conditional statements to initialize the head's value.
 *
 * Take extra caution of the following cases:
 *
 * Test case	Explanation
 * l1=[0,1]l1=[0,1]
 * l2=[0,1,2]l2=[0,1,2]	When one list is longer than the other.
 * l1=[]l1=[]
 * l2=[0,1]l2=[0,1]	When one list is null, which means an empty list.
 * l1=[9,9]l1=[9,9]
 * l2=[1]l2=[1]	The sum could have an extra carry of one at the end, which is easy to forget.
 */
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (l1 != null)
                l1 = l1.next;
            if (l2 != null)
                l2 = l2.next;
        }
        return dummyHead.next;
    }

}
