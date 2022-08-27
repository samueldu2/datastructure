package com.samueldu.leetcode.studyplan.leetcode75.level3.linkedlist;

/**
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 *
 * Merge all the linked-lists into one sorted linked-list and return it.
 *
 *
 *
 * Example 1:
 *
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * merging them into one sorted list:
 * 1->1->2->3->4->4->5->6
 * Example 2:
 *
 * Input: lists = []
 * Output: []
 * Example 3:
 *
 * Input: lists = [[]]
 * Output: []
 *
 *
 * Constraints:
 *
 * k == lists.length
 * 0 <= k <= 104
 * 0 <= lists[i].length <= 500
 * -104 <= lists[i][j] <= 104
 * lists[i] is sorted in ascending order.
 * The sum of lists[i].length will not exceed 104.
 *
 * Approach 5: Merge with Divide And Conquer
 * Intuition & Algorithm
 *
 * This approach walks alongside the one above but is improved a lot. We don't need to traverse most nodes many times repeatedly
 *
 * Pair up \text{k}k lists and merge each pair.
 *
 * After the first pairing, \text{k}k lists are merged into k/2k/2 lists with average 2N/k2N/k length, then k/4k/4, k/8k/8 and so on.
 *
 * Repeat this procedure until we get the final sorted linked list.
 *
 * Thus, we'll traverse almost NN nodes per pairing and merging, and repeat this procedure about \log_{2}{k}log
 * 2
 * ​
 *  k times.
 *
 *
 * Complexity Analysis
 *
 * Time complexity : O(N\log k)O(Nlogk) where \text{k}k is the number of linked lists.
 *
 * We can merge two sorted linked list in O(n)O(n) time where nn is the total number of nodes in two lists.
 * Sum up the merge process and we can get: O\big(\sum_{i=1}^{log_{2}{k}}N \big)= O(N\log k)O(∑
 * i=1
 * log
 * 2
 * ​
 *  k
 * ​
 *  N)=O(Nlogk)
 * Space complexity : O(1)O(1)
 *
 * We can merge two sorted linked lists in O(1)O(1) space.
 */
public class MergeKSortedList {
    public ListNode mergeKLists(ListNode[] lists){
        int amount = lists.length;
        int interval =1;
        while(interval<amount){

            for (int i=0; i<amount-interval; i+=interval*2){
                lists[i]=merge2Lists(lists[i], lists[i+interval]);
            }
            interval*=2;
        }
        if(amount>0)
            return lists[0];
        else
            return null;
    }

    private ListNode merge2Lists(ListNode l1, ListNode l2){
        ListNode head= new ListNode(0);
        ListNode point = head;
        while (l1!=null && l2!=null) {
            if (l1.val <= l2.val) {
                point.next = l1;
                l1 = l1.next;
            } else {
                point.next = l2;
                l2 = l1;
                l1 = point.next.next;
            }
            point = point.next;
        }

        if(l1==null)
            point.next=l2;
        else
            point.next=l1;

        return head.next;
    }

}
