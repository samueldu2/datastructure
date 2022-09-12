package com.samueldu.graphtransversal.tree;

/**
 * You have a graph of n nodes labeled from 0 to n - 1. You are given an integer n and a list of edges where edges[i] = [ai, bi] indicates that there is an undirected edge between nodes ai and bi in the graph.
 *
 * Return true if the edges of the given graph make up a valid tree, and false otherwise.
 *
 * Recall that a graph, G, is a tree iff the following two conditions are met:
 *
 * G is fully connected. In other words, for every pair of nodes in G, there is a path between them.
 * G contains no cycles. In other words, there is exactly one path between each pair of nodes in G.
 */

import java.util.*;

/**
 * Constraints:
 *
 * 1 <= n <= 2000
 * 0 <= edges.length <= 5000
 * edges[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * There are no self-loops or repeated edges.
 */
public class TreeValidation {
    /**
     * Approach 1: Graph Theory + Iterative Depth-First Search
     * Intuition
     * <p>
     * Note that this same approach also works with recursive depth-first search and iterative breadth-first search. We'll look at these briefly in the Algorithm section.
     * <p>
     * Recall that a graph, G, is a tree iff the following two conditions are met:
     * <p>
     * G is fully connected. In other words, for every pair of nodes in G, there is a path between them.
     * G contains no cycles. In other words, there is exactly one path between each pair of nodes in G.
     * Depth-first search is a classic graph-traversal algorithm that can be used to check for both of these conditions:
     * <p>
     * G is fully connected if, and only if, we started a depth-first search from a single source and discovered all nodes in G during it.
     * G contains no cycles if, and only if, the depth-first search never goes back to an already discovered node. We need to be careful
     * though not to count trivial cycles of the form A → B → A that occur with most implementations of undirected edges.
     * Depth-first search requires being able to look up the adjacent (immediate neighbours) of a given node. Like many graph interview problems though,
     * the input format we're given doesn't allow us to quickly get the neighbours of a node. Therefore, our first step is to convert the input into an adjacency list.
     * Recall that an adjacency list is where we have a list of sub-lists, where each sub-list is the list of the immediate neighbours for the i'th node.
     * <p>
     * <p>
     * Before we move onto actually carrying out the depth-first search, let's quickly reassure ourselves that an adjacency list was the best graph representation for this problem. The other 2 choices would have been an adjacency matrix or a linked representation.
     * <p>
     * An adjacency matrix would be an acceptable, although not ideal, representation for this problem. Often, we'd only use an adjacency matrix if we know that the number of edges is substantially higher than the number of nodes. We have no reason to believe that is the case here. Approach 2 will also provide some useful insight into this.
     * A linked representation, where you make actual nodes objects, would be an overly complicated representation and could suggest to an interviewer that you have a limited understanding of adjacency lists and adjacency matrices. They are not commonly used in interview questions.
     * Anyway, let's get started on the depth-first search. Recall that most depth-first searches follow a template like the one below for iterative depth-first search. Note that this doesn't yet solve the problem of determining whether or not the input graph is a tree—we're simply using it as a step towards building up a solution.
     */

    /**
     * iterative depth first search
     */
    public boolean validTreeIterativeDFS(int n, int[][] edges) {

        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {//create adjacency list.
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }
        /**
         * this mapping contains already walked edges in reverse direction, where key is the TO node, and value is FROM node.
         */
        Map<Integer, Integer> visitedEdgeToFromMapping = new HashMap<>(); //tracks the edges we used so far.
        visitedEdgeToFromMapping.put(0, -1); //root node has no visitedEdgeToFromMapping, so mark the visitedEdgeToFromMapping as -1.
        Stack<Integer> stack = new Stack<>(); //DFS, since we are using a stack.
        stack.push(0); //start from root

        while (!stack.isEmpty()) {
            int node = stack.pop();

            /**
             * we are walking from node to all of its neighbours
             */
            for (int neighbour : adjacencyList.get(node)) {

                /**
                 * we walked in reverse direction before along the same edge, so we skip this edge to avoid detecting trivial cycles in the form  of A->B->A.
                 */
                if (visitedEdgeToFromMapping.get(node) == neighbour) {
                    continue;
                }
                /**
                 * we waled into this neighbour before, we can only walk forward, and not allowed to back track, yet we still ended up in the same node again,
                 * that means we waled in a cycle. so return false to indicate that the graph is invalid.
                 */
                if (visitedEdgeToFromMapping.containsKey(neighbour)) {
                    return false;
                }
                stack.push(neighbour);

                /**
                 * record the edge we've visited already, in the format of to, from pairs.
                 */
                visitedEdgeToFromMapping.put(neighbour, node);
            }
        }

        return visitedEdgeToFromMapping.size() == n; //if size is less than n, then some nodes are not reachable from the root.
    }

    /**
     *
     * Yet another variant is to use iterative breadth-first search. Recall that breadth-first search and depth-first search are almost the same algorithm,
     * just with a different data structure.
     */
    public boolean validTreeIterativeBFS(int n, int[][] edges) {

        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }

        Map<Integer, Integer> parent = new HashMap<>();
        parent.put(0, -1);
        Queue<Integer> queue = new LinkedList<>();//since we use a queue, this is BFS.
        queue.offer(0);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbour : adjacencyList.get(node)) {
                if (parent.get(node) == neighbour) {
                    continue;
                }
                if (parent.containsKey(neighbour)) {
                    return false;
                }
                queue.offer(neighbour);
                parent.put(neighbour, node);
            }
        }

        return parent.size() == n;
    }

    /**
     * Alternatively, you could use recursion, as long as you're fairly confident with it.
     * The recursive approach is more elegant, but is considered inferior to the iterative version in some programming languages, such as Python.
     * This is because the space used by run-time stacks vary between programming languages.
     *
     * On the plus side, we can use a simple seen set and just pass a parent parameter. This makes the code a bit simpler!
     * @param n
     * @param edges
     * @return
     */
    public boolean validTreeRecursive(int n, int[][] edges) {
         List<List<Integer>> adjacencyList = new ArrayList<>();
         Set<Integer> seen = new HashSet<>();
        if (edges.length != n - 1) return false;

        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }

        // We return true iff no cycles were detected,
        // AND the entire graph has been reached.
        return dfs(0, -1,adjacencyList, seen) && seen.size() == n;
    }

    public boolean dfs(int node, int parent,  List<List<Integer>> adjacencyList, Set<Integer> seen) {
        if (seen.contains(node)) return false;
        seen.add(node);
        for (int neighbour : adjacencyList.get(node)) {
            if (parent != neighbour) { //don't consider trivial cycle of A-->B-->A
                boolean result = dfs(neighbour, node, adjacencyList, seen);
                if (!result) return false;
            }
        }
        return true;
    }

}
