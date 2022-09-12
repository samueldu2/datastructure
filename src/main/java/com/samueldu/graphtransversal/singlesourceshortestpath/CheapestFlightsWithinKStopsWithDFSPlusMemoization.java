package com.samueldu.graphtransversal.singlesourceshortestpath;

import javafx.util.Pair;

import java.util.HashMap;

/**
 * Approach 2: Depth-First-Search with Memoization
 * Intuition
 *
 * This problem can easily be modeled as a dynamic programming problem on graphs. What does a dynamic programming problem entail?
 *
 * It has a recursive structure.
 * A bunch of choices to explore at each step.
 * Use the optimal solutions for sub-problems to solve top-level problems.
 * A base case.
 * This problem fits the bill. We have a dedicated start and endpoint. We have a bunch of choices for each node in the form of its neighbors. And, we want to minimize the overall shortest distance from the source to the destination which can be represented as a recursive structure in terms of shortest distances of its neighbors to the destination. So, we can apply a dynamic programming approach to solve this problem. We'll look at a recursive implementation here with memoization first and then talk about the iterative approach as well.
 *
 * As with any recursive approach, we need to figure out the state of recursion. There are two parameters here which will control our recursion. One is obviously the node itself. The other is the number of steps. Let's call our recursion function recurse and define what the state of recursion looks like. \text{recurse}(\text{node},\text{stops})recurse(node,stops) will basically return the shortest distance for us to reach the destination from \text{node}node considering that there are stops left. This being said, it's easy to figure out what the top-level problem would be. It would be \text{recurse}(\text{0},\text{K})recurse(0,K).
 *
 * Let's consider the following graph to understand why memoization (or caching) is required here.
 *
 *
 * Say we start the source node A and build our recursion tree from there. There are two possible routes of getting to the node C with exactly 2 stops. Let's look at what these are.
 *
 *
 * While the cost of these two paths is different, once we are at the node C, we have 2 steps less than what we had when we started off from the source node A. Our recursion representation doesn't care about the path you took to get to a node. It is about the shortest (cheapest) path from the current node with the given number of steps to get to a destination. In that sense, both these scenarios are exactly the same because both lead us to the same recursion state which is (\text{recurse}(\text{C}, \text{K-2}))(recurse(C,K-2)) and hence, the result for this recursion state can be cached or memoized.
 *
 * Algorithm
 *
 * We'll define a function called recurse which will take two inputs: node and stops.
 *
 * We'll also define a dictionary memo of tuples that will store the optimal solution for each recursion state encountered.
 *
 * At each stage, we'll first check if we have reached the destination or not. If we have, then no more moves have to be made and we return a value of 0 since the destination is at a zero distance from itself.
 *
 * Next, we check if we have any more stops left. If we don't then we return inf basically representing that we cannot reach the destination from the current recursion state.
 *
 * Finally, we check if the current recursion state is cached in the memo dictionary and if it is, we return the answer right away.
 *
 * If none of these conditions are met,we progress in our recursion. For that we will iterate over the adjacency matrix to obtain the neighbors for the current node and make a recursive call for each one of them. The node would be the neighboring node and the number of stops would incremeneted by 1.
 *
 * To each of these recursion calls, we add the weight of the corresponding edge i.e.
 *
 * recurse(neighbor, stops + 1) + weight(node, neighbor)
 * We need to return the result of recurse(src, 0) as the answer.
 *
 * Complexity Analysis
 *
 * Time Complexity: The time complexity for a recursive solution is defined by the number of recursive calls we make and the time it takes to process one recursive call. The number of recursive calls we can potentially make is \text{O}(\text{V} \cdot \text{K})O(V⋅K). In each recursive call, we iterate over a given node's neighbors. That takes time O(\text{V})O(V) because we are using an adjacency matrix. Thus, the overall time complexity is \text{O}(\text{V}^2 \cdot \text{K})O(V
 * 2
 *  ⋅K).
 * Space Complexity: \text{O}(\text{V} \cdot \text{K} + \text{V}^2)O(V⋅K+V
 * 2
 *  ) where \text{O}(\text{V} \cdot \text{K})O(V⋅K) is occupied by the memo dictionary and the rest by the adjacency matrix structure we build in the beginning.
 */
public class CheapestFlightsWithinKStopsWithDFSPlusMemoization {


        private int[][] adjMatrix;
        private HashMap<Pair<Integer, Integer>, Long> memo;

        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {

            this.adjMatrix = new int[n][n];
            this.memo = new HashMap<Pair<Integer, Integer>, Long>();

            for (int[] flight: flights) {
                this.adjMatrix[flight[0]][flight[1]] = flight[2];
            }

            long ans = this.findShortest(src, K, dst, n);
            return ans >= Integer.MAX_VALUE ? -1 : (int)ans;
        }

        public long findShortest(int node, int stops, int dst, int n) {

            // No need to go any further if the destination is reached
            if (node == dst) {
                return 0;
            }


            // Can't go any further if no stops left
            if (stops < 0) {
                return Integer.MAX_VALUE;
            }

            Pair<Integer, Integer> key = new Pair<Integer, Integer>(node, stops);


            // If the result of this state is already cached, return it
            if (this.memo.containsKey(key)) {
                return this.memo.get(key);
            }

            // Recursive calls over all the neighbors
            long ans = Integer.MAX_VALUE;
            for (int neighbor = 0; neighbor < n; ++neighbor) {

                int weight = this.adjMatrix[node][neighbor];

                // 0 value means no edge
                if (weight > 0) {
                    ans = Math.min(ans, this.findShortest(neighbor, stops - 1, dst, n) + weight);
                }
            }

            // Cache the result
            this.memo.put(key, ans);
            return ans;
        }


}
