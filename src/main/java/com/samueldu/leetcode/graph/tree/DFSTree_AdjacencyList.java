package com.samueldu.leetcode.graph.tree;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class DFSTree_AdjacencyList {
    private AdjacencyList adjacencyList;
    private static void main(String [] args){

    }
    public void DFS(){
        //Use a stack to keep track of unexplored nodes
        Stack<Integer>stack= new Stack<>();
        stack.push(0);

        //use a set to keep track of already seen nodes to avoid infinite looping
        Set<Integer> seen = new HashSet<>();
        /**
         * this is how you can do depth first search without recursion.
         */
        while (!stack.isEmpty()) {
            int node = stack.pop();
            for (int neighbour : adjacencyList.get(node)){
                if(seen.contains(neighbour)){
                    continue; // already seen this node
                }
                stack.push(neighbour);
                seen.add(neighbour);
            }
        }
    }
}
