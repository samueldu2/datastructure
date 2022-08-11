package com.samueldu.leetcode.beginner.addtwonumbers.middleoflinkedlist;

public class Solution {
    public static void main(String[] args){
        ListNode next= new ListNode(5);
        ListNode finalNode=null;
        for (int i =4; i>=1; i--){
            ListNode n= new ListNode(i, next);
            next=n;
            finalNode=n;
        }
        Solution s= new Solution();
        ListNode m = s.middleNode(finalNode);
        System.out.println(m.val);
    }

        public ListNode middleNode(ListNode head) {
            int size = getSize(head);
            int location=0;
            location=size==0?0:size/2+1;
            ListNode middle=head;
            while(--location!=0){
                middle=middle.next;
            }
            return middle;
        }

        private int getSize(ListNode head){
            int count=1;
            while(head.next!=null){
                head=head.next;
                count++;
            }
            return count;
        }
    public static class ListNode {
      int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }
}
