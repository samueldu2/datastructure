package com.samueldu.graphtransversal.singlesourceshortestpath;


import javafx.util.Pair;

import java.util.HashMap;
import java.util.LinkedList;

public class CheapestFlightWithinKStopsWithBFS {
    /**
     * Approach 4: Breadth First Search
     * Intuition
     *
     * We say that the breadth-first search is a good algorithm to use if we want to find the shortest path in an undirected, unweighted graph. The claim for BFS is that the first time a node is discovered during the traversal, that distance from the source would give us the shortest path. The same cannot be said for a weighted graph. For a weighted graph, there is no correlation between the number of edges composing the path and the actual length of the path which is composed of the weights of all the edges in it. Thus, we cannot employ breadth-first search for weighted graphs.
     *
     * Breadth-first search has no way of knowing if a particular discovery of a node would give us the shortest path to that node. And so, the only possible way for BFS (or DFS) to find the shortest path in a weighted graph is to search the entire graph and keep recording the minimum distance from source to the destination vertex.
     *
     * That being said, Breadth-first search is actually a great algorithm of choice for this problem because the number of levels to be explored by the algorithm is bounded by K
     *
     * The number of levels that the search would go to is limited by the value K+1 in the question. So essentially, we would be trying to find the shortest path, but we won’t have to explore the entire graph as such. We will just go up to the level K+1 and we just need to return the shortest path to the destination (if reachable by level K+1) at the end of the algorithm.
     *
     * An important consideration here is the size of the queue. We need to control it somehow otherwise, even at very small depths, the graph could grow exponentially. For this very problem however, we will be able to bound the size of a given level (and hence the queue) by VV, the number of vertices in the graph. Let's think about what it means to encounter the same node multiple times during breadth first traversal.
     *
     * Since we will be going only till the level K+1, we don't really have to worry about the number of stops getting exhausted or something. So if the number of stops are out of the way, the only way we will consider adding a node again to the queue is if we found a shorter distance from the source than what we already have stored for that node. If that is not the case then on encountering a node again during the traversal, we can safely discard it i.e not add it to the queue again.
     *
     * Since this is weighted graph, we cannot assume anything about the shortest distance from source to a node when its first discovered after being popped from the queue. We will have to go to all the K+1 levels and once we've exhausted K+1 levels, we can be sure that the shortest distances we have are the "best" we can find with K+1 edges or less.
     *
     * Algorithm
     *
     * This is standard BFS and we'll be using a queue here. Let's call it Q.
     * We'll need a dictionary to keep track of shortest distances from the source. An important thing to note in this approach is that we need to keep a dictionary with the node, stops as the key. Basically, we need to keep track of the shortest distance of a node from the source provided that it takes stops stops to reach it.
     * Add the source node to the queue. There are multiple ways of tracking the level of a node during breadth-first traversal. We'll be using the size of the queue at the beginning of the level to loop over a particular level.
     * We iterate until we exhaust the queue or K+1 levels whichever comes first.
     * For each iteration, we pop a node from the queue and iterate over its neighbors which we can get from the adjacency matrix.
     * For each of the neighbors, we check if the current edge improves that neighbor's shortest distance from source or not. If it does, then we update the shortest distance dictionary (array) accordingly and also add the neighbor to the queue.
     * We continue doing this for until one of our terminal conditions are met.
     * We will also maintain an ans variable to track the minimum distance of the destination from the source. At each step, whenever we update the shortest distance of a node from source, we check if that node is the destination and if it is, we will update the ans variable accordingly.
     * At the end, we simply check if we were able to reach the destination node by looking at the ans variable's value. If we did reach it, then the recorded distance would be the shortest in under K hops (or K + 1 edges at most).
     *
     * Complexity Analysis
     *
     * Time Complexity: O(\text{E} \cdot \text{K})O(E⋅K) since we can process each edge multiple times depending upon the improvement in the shortest distances. However, the maximum number of times an edge would be processed is bounded by \text{K + 1}K + 1 since that's the number of levels we are going to explore in this algorithm.
     * Space Complexity: O(\text{V}^2 + \text{V} \cdot \text{K})O(V
     * 2
     *  +V⋅K). The first part is the standard memory occupied by the adjacency matrix and in addition to that, the distances dictionary can occupy a maximum of O(\text{V} \cdot \text{K})O(V⋅K).
     */



        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {

            // Build the adjacency matrix
            int adjMatrix[][] = new int[n][n];
            for (int[] flight: flights) {
                adjMatrix[flight[0]][flight[1]] = flight[2];
            }

            // Shortest distances dictionary
            HashMap<Pair<Integer, Integer>, Long> distances = new HashMap<Pair<Integer, Integer>, Long>();
            distances.put(new Pair<Integer, Integer>(src, 0), 0L);

            // Number of stops done
            int stops = 0;

            // Final answer
            long ans = Long.MAX_VALUE;

            // BFS Queue
            LinkedList<Integer> bfsQueue = new LinkedList<Integer>();
            bfsQueue.add(src);

            // Iterate until we exhaust K+1 levels or the queue gets empty
            while (!bfsQueue.isEmpty() && stops < K + 1) {

                // Iterate on current level
                int length = bfsQueue.size();
                for (int i = 0; i < length; ++i) {

                    // Loop over neighbors of popped node
                    int node = bfsQueue.poll();
                    for (int nei = 0; nei < n; ++nei) {

                        if (adjMatrix[node][nei] > 0) {

                            long dU = distances.getOrDefault(new Pair<Integer, Integer>(node, stops), Long.MAX_VALUE);
                            long dV = distances.getOrDefault(new Pair<Integer, Integer>(nei, stops + 1), Long.MAX_VALUE);
                            long wUV = adjMatrix[node][nei];

                            // No need to update the minimum cost if we have already exhausted our K stops.
                            if (stops == K && nei != dst) {
                                continue;
                            }

                            if (dU + wUV < dV) {
                                distances.put(new Pair<Integer, Integer>(nei, stops + 1), dU + wUV);
                                bfsQueue.add(nei);

                                // If the neighbor is infact the destination, update the answer accordingly
                                if (nei == dst) {
                                    ans = Math.min(ans, dU + wUV);
                                }
                            }
                        }
                    }
                }

                stops++;
            }

            return ans == Long.MAX_VALUE ? -1 : (int) ans;
        }

}
