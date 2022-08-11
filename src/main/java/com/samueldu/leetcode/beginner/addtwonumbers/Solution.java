package com.samueldu.leetcode.beginner.addtwonumbers;

import java.util.Stack;

/**
 * Definition for singly-linked list.

 *
 *     You are given two non-empty linked lists representing two non-negative integers.
 *     The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 *
 * }
 */
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Long i1= getValue(l1);
        Long i2 = getValue(l2);

        Long sum = i1+i2;
        ListNode result = format(sum);
        return result;

    }
    public static void main (String [] args){
        Solution s = new Solution();
        ListNode ln=new ListNode(3);
        ListNode ln2= new ListNode(4, ln);
        ListNode ln3= new ListNode(2, ln2);

        ListNode LL1= new ListNode(4);
        ListNode LL2= new ListNode(6, LL1);
        ListNode LL3 = new ListNode(5, LL2);


        ListNode result = s.addTwoNumbers(ln3, LL3);
/*
        ListNode ln=new ListNode(0);
        ListNode LL1= new ListNode(0);
        ListNode result = s.addTwoNumbers(ln, LL1);

 */
        print(result);
    }

    public static void print(ListNode result){
        System.out.print(result.val);
        while (result.next!=null){
            result = result.next;
            System.out.print("-->"+result.val);
        }
    }
    private ListNode format(Long sum){

        ListNode head = null;
        ListNode lastNext = null;
        if(sum==0){
            ListNode newl= new ListNode();
            newl.val=0;
            return newl;
        }
        while (sum!=0){
            long signifcant = sum/10;
            int remainder = (int) (sum-signifcant*10);

            if(lastNext==null){
                lastNext= new ListNode();
                lastNext.val=remainder;
                sum/=10;
                head = lastNext;
                continue;
            }
            ListNode newLN= new ListNode();
            newLN.val=remainder;
            lastNext.next=newLN;
            lastNext=newLN;
            sum/=10;
        }
        return head;
    }
    private Long getValue(ListNode l){
        Stack<Integer> s= new Stack<>();
        s.push(l.val);
        while(l.next!=null){
            l=l.next;
            s.push(l.val);
        }
        int scaler =1;
        long result=0;
        int next =0;
        while(!s.isEmpty()){
            next=s.pop();
            result+=next*scaler;
            scaler*=10;
        }
        return result;
    }




}