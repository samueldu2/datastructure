package com.samueldu.graphtransversal.depthfirst;

import java.util.*;

/**
 * Find if Path Exists in Graph
 * There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive).
 * The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional
 * edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.
 *
 * You want to determine if there is a valid path that exists from vertex source to vertex destination.
 *
 * Given edges and the integers n, source, and destination, return true if there is a valid path from source to destination, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
 * Output: true
 * Explanation: There are two paths from vertex 0 to vertex 2:
 * - 0 → 1 → 2
 * - 0 → 2
 * Example 2:
 *
 *
 * Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
 * Output: false
 * Explanation: There is no path from vertex 0 to vertex 5.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 2 * 105
 * 0 <= edges.length <= 2 * 105
 * edges[i].length == 2
 * 0 <= ui, vi <= n - 1
 * ui != vi
 * 0 <= source, destination <= n - 1
 * There are no duplicate edges.
 * There are no self edges.
 *
 * Complexity Analysis
 * Time Complexity: O(V + E)O(V+E). Here, VV represents the number of vertices, and EE represents the number of edges.
 *
 * To create the adjacency list, we must iterate over each of the EE edges.
 * In the while loop, at most, we will visit vertex once.
 * The for loop inside the while loop will have a cumulative sum of at most EE iterations since it will iterate over all of the node's neighbors for each node.
 * Space Complexity: O(V + E)O(V+E).
 *
 * The adjacency list will contain O(V + E)O(V+E) elements.
 * The stack will also contain O(E)O(E) elements. However, this can be reduced to O(V)O(V) by checking whether a neighbor node has been seen before adding it to the stack.
 * The seen set will use O(V)O(V) space to store the visited nodes.
 */
public class FindIfPathExistsInGraph {

        public boolean validPath(int n, int[][] edges, int start, int end) {
            List<List<Integer>> adjacency_list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                adjacency_list.add(new ArrayList<>());
            }

            for (int[] edge : edges) {
                adjacency_list.get(edge[0]).add(edge[1]);
                adjacency_list.get(edge[1]).add(edge[0]);
            }

            Deque<Integer> stack = new ArrayDeque<>();
            stack.push(start);
            boolean seen[] = new boolean[n];
            Arrays.fill(seen, false);

            while (!stack.isEmpty()) {
                // Get the current node.
                int node = stack.pop();

                // Check if we have reached the target node.
                if (node == end) {
                    return true;
                }

                // Check if we've already visited this node.
                if (seen[node]) {
                    continue;
                }
                seen[node] = true;

                // Add all neighbors to the stack.
                for (int neighbor : adjacency_list.get(node)) {
                    stack.push(neighbor);
                }
            }

            return false;
        }

}
