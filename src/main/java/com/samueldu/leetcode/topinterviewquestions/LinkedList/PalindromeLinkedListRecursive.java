package com.samueldu.leetcode.topinterviewquestions.LinkedList;

public class PalindromeLinkedListRecursive {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }



        private ListNode frontPointer;

    /**
     * Approach 2: Recursive (Advanced)
     * Intuition
     *
     * In an attempt to come up with a way of using O(1)O(1) space, you might have thought of using recursion. However, this is still O(n)O(n) space. Let's have a look at it and understand why it is not O(1)O(1) space.
     *
     * Recursion gives us an elegant way to iterate through the nodes in reverse. For example, this algorithm will print out the values of the nodes in reverse. Given a node, the algorithm checks if it is null. If it is null, nothing happens. Otherwise, all nodes after it are processed, and then the value for the current node is printed.
     *
     * function print_values_in_reverse(ListNode head)
     *     if head is NOT null
     *         print_values_in_reverse(head.next)
     *         print head.val
     * If we iterate the nodes in reverse using recursion, and iterate forward at the same time using a variable outside the recursive function, then we can check whether or not we have a palindrome.
     *
     * Algorithm
     *
     * When given the head node (or any other node), referred to as currentNode, the algorithm firstly checks the rest of the Linked List. If it discovers that further down that the Linked List is not a palindrome, then it returns false. Otherwise, it checks that currentNode.val == frontPointer.val. If not, then it returns false. Otherwise, it moves frontPointer forward by 1 node and returns true to say that from this point forward, the Linked List is a valid palindrome.
     *
     * It might initially seem surprisingly that frontPointer is always pointing where we want it. The reason it works is because the order in which nodes are processed by the recursion is in reverse (remember our "printing" algorithm above). Each node compares itself against frontPointer and then moves frontPointer down by 1, ready for the next node in the recursion. In essence, we are iterating both backwards and forwards at the same time.
     *
     * Here is an animation that shows how the algorithm works. The nodes have each been given a unique identifier (e.g. 1` and `1‘and‘4) so that they can more easily be referred to in the explanations. The computer needs to use its runtime stack for recursive functions.
     *
     * Current
     * 1 / 34
     *
     * Complexity Analysis
     *
     * Time complexity : O(n)O(n), where nn is the number of nodes in the Linked List.
     *
     * The recursive function is run once for each of the nn nodes, and the body of the recursive function is O(1)O(1). Therefore, this gives a total of O(n)O(n).
     *
     * Space complexity : O(n)O(n), where nn is the number of nodes in the Linked List.
     *
     * I hinted at the start that this is not using O(1)O(1) space. This might seem strange, after all we aren't creating any new data structures. So, where is the O(n)O(n) extra memory we're using? Understanding what is happening here requires understanding how the computer runs a recursive function.
     *
     * Each time a function is called within a function, the computer needs to keep track of where it is up to (and the values of any local variables) in the current function before it goes into the called function. It does this by putting an entry on something called the runtime stack, called a stack frame. Once it has created a stack frame for the current function, it can then go into the called function. Then once it is finished with the called function, it pops the top stack frame to resume the function it had been in before the function call was made.
     *
     * Before doing any palindrome checking, the above recursive function creates nn of these stack frames because the first step of processing a node is to process the nodes after it, which is done with a recursive call. Then once it has the nn stack frames, it pops them off one-by-one to process them.
     *
     * So, the space usage is on the runtime stack because we are creating nn stack frames. Always make sure to consider what's going on the runtime stack when analyzing a recursive function. It's a common mistake to forget to.
     *
     * Not only is this approach still using O(n)O(n) space, it is worse than the first approach because in many languages (such as Python), stack frames are large, and there's a maximum runtime stack depth of 1000 (you can increase it, but you risk causing memory errors with the underlying interpreter). With every node creating a stack frame, this will greatly limit the maximum Linked List size the algorithm can handle.
     * @param currentNode
     * @return
     */
        private boolean recursivelyCheck(ListNode currentNode) {
            if (currentNode != null) {
                if (!recursivelyCheck(currentNode.next)) return false;
                if (currentNode.val != frontPointer.val) return false;
                frontPointer = frontPointer.next;
            }
            return true;
        }

        public boolean isPalindrome(ListNode head) {
            frontPointer = head;
            return recursivelyCheck(head);
        }

}
