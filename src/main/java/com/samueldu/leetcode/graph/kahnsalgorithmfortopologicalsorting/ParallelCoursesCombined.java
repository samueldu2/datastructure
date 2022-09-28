package com.samueldu.leetcode.graph.kahnsalgorithmfortopologicalsorting;

import java.util.ArrayList;
import java.util.List;

public class ParallelCoursesCombined {
/**
 * Depth-First Search: Combine
 * Intuition
 *
 * This approach is an improvement of Approach 2. It is recommended to ensure that you fully understood Approach 2 before continuing onto this final approach.
 *
 * Here, we combine the two functions in Approach 2, dfsCheckCycle and dfsMaxPath, into one single function, dfs.
 *
 * The new dfs should return -1 if a cycle is detected, and return the longest length otherwise.
 *
 * Just simple modifications on dfsCheckCycle will do:
 *
 * Recall in dfsCheckCycle, each node has three states: unvisited, visiting, and visited.
 *
 * We can change the visited state to the longest length starting from the current node, and let the dfs return the longest length starting from the current node.
 *
 * The pseudo-code is as below:
 *
 * set states of all nodes to unvisited
 *
 * def dfs(node):
 *     if the state of node is visiting:
 *         # detects cycles
 *         return -1
 *     else if the state of node is visited:
 *         return the state of node # the longest length
 *
 *     set the state of node to visiting
 *
 *     max_length = -1
 *     for child_node in child_nodes:
 *         child_answer = dfs(child_node)
 *         # if detects cycles in child_node
 *         if child_answer == -1:
 *             return -1
 *         else:
 *             max_length = max(max_length, child_answer)
 *
 *     set the state of node to max_length
 *     return max_length
 * Algorithm
 *
 * Step 1: Build a directed graph from relations.
 *
 * Step 2: Implement a function dfs to check whether the graph has a cycle and calculate the length of the longest path in the graph.
 *
 * Step 3: Call dfs; return -1 if the graph has a cycle. Otherwise, return the length of the longest path in the graph.
 *
 * Implementation
 *
 *
 * Complexity Analysis
 *
 * Let EE be the length of relations.
 *
 * Time Complexity: \mathcal{O}(N+E)O(N+E). For building the graph, we spend \mathcal{O}(N)O(N) to initialize the graph, and spend \mathcal{O}(E)O(E) to add egdes since we iterate relations once. For DFS, we spend \mathcal{O}(N+E)O(N+E) since we need to visit every node and edge once in DFS in the worst case.
 *
 * Space Complexity: \mathcal{O}(N+E)O(N+E). For the graph, we spend \mathcal{O}(N+E)O(N+E) since we have \mathcal{O}(N)O(N) keys and \mathcal{O}(E)O(E) values. For DFS, we spend \mathcal{O}(N)O(N) since in the worst case, we need to add all nodes to the stack to recursively call DFS.
 */

    public int minimumSemesters(int N, int[][] relations) {
        List<List<Integer>> graph = new ArrayList<>(N + 1);
        for (int i = 0; i < N + 1; ++i) {
            graph.add(new ArrayList<Integer>());
        }
        for (int[] relation : relations) {
            graph.get(relation[0]).add(relation[1]);
        }
        int[] visited = new int[N + 1];

        int maxLength = 1;
        for (int node = 1; node < N + 1; node++) {
            int length = dfs(node, graph, visited);
            // we meet a cycle!
            if (length == -1) {
                return -1;
            }
            maxLength = Math.max(length, maxLength);
        }
        return maxLength;
    }

    private int dfs(int node, List<List<Integer>> graph, int[] visited) {
        // return the longest path (inclusive)
        if (visited[node] != 0) {
            return visited[node];
        } else {
            // mark as visiting
            visited[node] = -1;
        }
        int maxLength = 1;
        for (int endNode : graph.get(node)) {
            int length = dfs(endNode, graph, visited);
            // we meet a cycle!
            if (length == -1) {
                return -1;
            }
            maxLength = Math.max(length + 1, maxLength);
        }
        // mark as visited
        visited[node] = maxLength;
        return maxLength;
    }


}
