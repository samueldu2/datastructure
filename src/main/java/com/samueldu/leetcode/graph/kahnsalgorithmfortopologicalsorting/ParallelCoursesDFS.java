package com.samueldu.leetcode.graph.kahnsalgorithmfortopologicalsorting;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ParallelCoursesDFS {
    /**
     * Depth-First Search: Check for Cycles + Find Longest Path
     * Intuition
     *
     * There is an important insight:
     *
     * The number of semesters needed is equal to the length of the longest path in the graph.
     *
     * For example, the longest path in the graph below is 5, so the number of semesters needed is 5:
     *
     * Figure 2.1
     *
     * Why? Treat the path as a sequence of prerequisites, and for each prerequisite, we need to spend one semester to advance to the next node.
     *
     * But there is a problem: if the graph has a cycle, then the longest path would be infinite.
     *
     * Figure 2.3
     *
     * So firstly, we need to check if the graph has a cycle. If it does, we can directly return -1 since we can never finish all courses.
     *
     * Now we break the problem into two parts:
     *
     * Check if the graph has a cycle
     * Calculate the length of the longest path
     * Each of the two parts can be done with DFS. In Approach 3, we will show how to achieve those two-part simultaneously in one single DFS. However, in this approach, for a better understanding, we separate them into two separate DFS traverals.
     *
     * Check If the Graph Has A Cycle
     *
     * Each node has one of the three states: unvisited, visiting, and visited.
     *
     * Before the DFS, we initialize all nodes in the graph to unvisited.
     *
     * When performing a DFS, we mark the current node as visiting until we search all paths out of the node from the node. If we meet a node marked with processing, it must come from the upstream path and therefore, we've detected a cycle.
     *
     * If DFS finishes, and all node are marked as visited, then the graph contains no cycle.
     *
     * Calculate the Length of the Longest Path
     *
     * The DFS function should return the maximum out of the recursive calls for its child nodes, plus one (the node itself).
     *
     * In order to prevent redundant calculations, we need to store the calculated results. This is an example of dynamic programming, as we're storing the result of subproblems.
     *
     * Algorithm
     *
     * Step 1: Build a directed graph from relations.
     *
     * Step 2: Implement a function dfsCheckCycle to check whether the graph has a cycle.
     *
     * Step 3: Implement a function dfsMaxPath to calculate the length of the longest path in the graph.
     *
     * Step 4: Call dfsCheckCycle, return -1 if the graph has a cycle.
     *
     * Step 5: Otherwise, call dfsMaxPath. Return the length of the longest path in the graph.
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
     * Space Complexity: \mathcal{O}(N+E)O(N+E). For the graph, we spend \mathcal{O}(N+E)O(N+E) since we have \mathcal{O}(N)O(N) keys and \mathcal{O}(E)O(E) values. For DFS, we spend \mathcal{O}(N)O(N) since in the worst case, we need to add all nodes to the stack to recursively call DFS. Also, we run DFS twice.
     * @param N
     * @param relations
     * @return
     */
        public int minimumSemesters(int N, int[][] relations) {
            List<List<Integer>> graph = new ArrayList<>(N + 1);
            for (int i = 0; i < N + 1; ++i) {
                graph.add(new ArrayList<Integer>());
            }
            for (int[] relation : relations) {
                graph.get(relation[0]).add(relation[1]);
            }
            // check if the graph contains a cycle
            int[] visited = new int[N + 1];
            for (int node = 1; node < N + 1; node++) {
                // if has cycle, return -1
                if (dfsCheckCycle(node, graph, visited) == -1) {
                    return -1;
                }
            }

            // if no cycle, return the longest path
            int[] visitedLength = new int[N + 1];
            int maxLength = 1;
            for (int node = 1; node < N + 1; node++) {
                int length = dfsMaxPath(node, graph, visitedLength);
                maxLength = Math.max(length, maxLength);
            }
            return maxLength;
        }

    /**
     *
     * visited[i]==0 ==> unvisited
     * visited[i]==-1 ==> is being visited
     * visited[i]==1 ==> visited already.
     */
        private int dfsCheckCycle(int node, List<List<Integer>> graph, int[] visited) {
            // return -1 if has a cycle
            // return 1 if does not have any cycle
            if (visited[node] != 0) {
                return visited[node];
            } else {
                // mark as visiting
                visited[node] = -1;
            }

            /**
             * We keep on visiting the child nodes, so this is DFS
             * if any child node is being visited (visited[i]==-1), then we encountered a cycyle.
             */
            for (int endNode : graph.get(node)) {
                if (dfsCheckCycle(endNode, graph, visited) == -1) {
                    // we meet a cycle!
                    return -1;
                }
            }
            // mark as visited
            visited[node] = 1;
            return 1;
        }

        private int dfsMaxPath(int node, List<List<Integer>> graph, int[] visitedLength) {
            // return the longest path (inclusive)
            if (visitedLength[node] != 0) {
                return visitedLength[node];
            }
            int maxLength = 1;
            for (int endNode : graph.get(node)) {
                int length = dfsMaxPath(endNode, graph, visitedLength);
                maxLength = Math.max(length + 1, maxLength);
            }
            // store it
            visitedLength[node] = maxLength;
            return maxLength;
        }

}
