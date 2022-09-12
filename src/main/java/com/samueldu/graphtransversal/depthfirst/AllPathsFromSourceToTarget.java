package com.samueldu.graphtransversal.depthfirst;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to node n - 1 and return them in any order.
 *
 * The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge from node i to node graph[i][j]).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: graph = [[1,2],[3],[3],[]]
 * Output: [[0,1,3],[0,2,3]]
 * Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
 * Example 2:
 *
 *
 * Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
 * Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
 *
 *
 * Constraints:
 *
 * n == graph.length
 * 2 <= n <= 15
 * 0 <= graph[i][j] < n
 * graph[i][j] != i (i.e., there will be no self-loops).
 * All the elements of graph[i] are unique.
 * The input graph is guaranteed to be a DAG.
 *
 */
public class AllPathsFromSourceToTarget {

        private int target;
        private int[][] graph;
        private List<List<Integer>> results;

    /**
     * note this is for DIRECTED ACyclic Graph, so we don't need to keep what was seen.
     * @param currNode
     * @param path
     *
     * No need to prevent infinite loops, as the graph is DAG, and acyclic.
     */
    protected void backtrack(int currNode, LinkedList<Integer> path) {
            if (currNode == this.target) {
                // Note: one should make a deep copy of the path
                this.results.add(new ArrayList<Integer>(path));
                return;
            }
            // explore the neighbor nodes one after another.
            for (int nextNode : this.graph[currNode]) {
                // mark the choice, before backtracking.
                path.addLast(nextNode);
                this.backtrack(nextNode, path);
                // remove the previous choice, to try the next choice
                // this essential for back tracking.
                path.removeLast();
            }
        }

        public List<List<Integer>> allPathsSourceTarget(int[][] graph) {

            this.target = graph.length - 1;
            this.graph = graph;
            this.results = new ArrayList<List<Integer>>();
            // adopt the LinkedList for fast access to the tail element.
            LinkedList<Integer> path = new LinkedList<Integer>();
            path.addLast(0);
            // kick of the backtracking, starting from the source (node 0)
            this.backtrack(0, path);
            return this.results;
        }
}
