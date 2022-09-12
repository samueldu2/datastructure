package com.samueldu.graphtransversal.singlesourceshortestpath;

import javafx.util.Pair;

import java.util.*;

public class DijkstraAlgorithmForNetworkDelayTime {
    /**
     * Approach 3: Dijkstra's Algorithm
     * Intuition
     *
     * If you're not familiar with Dijkstra's Algorithm, check out this topic in our Graph Explore Card.
     *
     * As mentioned earlier, our objective is to find the fastest path from node kk to every other node. This is a typical use case for the Single Source Shortest Path algorithm. Hence, In this approach, we will use Dijkstra's Algorithm to find the fastest path to every node from node kk.
     *
     * This approach is very similar to the previous BFS approach. We will start with node kk and then iterate over every adjacent node neighborNode. In the previous approach, we used a queue and hence broadcasted the signal from visited nodes in a FIFO manner. However, in this approach, we will use a priority queue to traverse the nodes in increasing order of the time required to reach them. Therefore, in each iteration, we will visit the node with the shortest travel time. This will help us in finding the fastest time path first.
     *
     * Algorithm
     *
     * Create an adjacency list such that adj[source] contains all destination nodes (dest) that the signal can travel to from the source node (source). For each destination node, there will be a pair (time, dest). Here, time denotes the time required for the signal to travel from source to dest.
     *
     * For all nodes, initialize signalReceivedAt as a large value to signify that, so far, no signal has been received.
     *
     * Initialize priority queue with the pair of starting node kk and its distance 00, store its distance in signalReceivedAt as 00. While the priority queue is not empty:
     *
     * Pop the top node currNode from the priority queue.
     * Traverse all outgoing edges connected to currNode.
     * Add the adjacent node neighborNode to the priority queue only if the current path takes less time than the value at signalReceivedAt[neighborNode]. Update the time at signalReceivedAt[neighborNode] to current path time.
     * Find the maximum value in the array signalReceivedAt. If any value in signalReceivedAt is still the large value we initialized the array with, then return -1 as that node is not reachable from k. Otherwise, return the maximum value in the array.
     *
     * Complexity Analysis
     *
     * Here NN is the number of nodes and EE is the number of total edges in the given network.
     *
     * Time complexity: O(N + E \log N)O(N+ElogN)
     *
     * Dijkstra's Algorithm takes O(E \log N)O(ElogN). Finding the minimum time required in signalReceivedAt takes O(N)O(N).
     *
     * The maximum number of vertices that could be added to the priority queue is EE. Thus, push and pop operations on the priority queue take O(\log E)O(logE) time. The value of EE can be at most N \cdot (N - 1)N⋅(N−1). Therefore, O(\log E)O(logE) is equivalent to O(\log N^2)O(logN
     * 2
     *  ) which in turn equivalent to O(2 \cdot \log N)O(2⋅logN). Hence, the time complexity for priority queue operations equals O(\log N)O(logN).
     *
     * Although the number of vertices in the priority queue could be equal to EE, we will only visit each vertex only once. If we encounter a vertex for the second time, then currNodeTime will be greater than signalReceivedAt[currNode], and we can continue to the next vertex in the priority queue. Hence, in total EE edges will be traversed and for each edge, there could be one priority queue insertion operation.
     *
     * Hence, the time complexity is equal to O(N + E \log N)O(N+ElogN).
     *
     * Space complexity: O(N + E)O(N+E)
     *
     * Building the adjacency list will take O(E)O(E) space. Dijkstra's algorithm takes O(E)O(E) space for priority queue because each vertex could be added to the priority queue N - 1N−1 time which makes it N * (N - 1)N∗(N−1) and O(N^2)O(N
     * 2
     *  ) is equivalent to O(E)O(E). signalReceivedAt takes O(N)O(N) space.
     */


        // Adjacency list
        Map<Integer, List<Pair<Integer, Integer>>> adj = new HashMap<>();

        private void dijkstra(int[] signalReceivedAt, int source, int n) {
            // Note this queue is sorted with shortest on top.
            //this is what make Dijkstra different from standard BFS.
            Queue<Pair<Integer, Integer>> pq = new PriorityQueue<Pair<Integer,Integer>>
                    (Comparator.comparing(Pair::getKey));
            pq.add(new Pair(0, source));

            // Time for starting node is 0
            signalReceivedAt[source] = 0;

            while (!pq.isEmpty()) {
                Pair<Integer, Integer> topPair = pq.remove();

                int currNode = topPair.getValue();
                int currNodeTime = topPair.getKey();

                if (currNodeTime > signalReceivedAt[currNode]) {
                    continue;
                }

                if (!adj.containsKey(currNode)) {
                    continue;
                }

                // Broadcast the signal to adjacent nodes
                for (Pair<Integer, Integer> edge : adj.get(currNode)) {
                    int time = edge.getKey();
                    int neighborNode = edge.getValue();

                    // Fastest signal time for neighborNode so far
                    // signalReceivedAt[currNode] + time :
                    // time when signal reaches neighborNode
                    if (signalReceivedAt[neighborNode] > currNodeTime + time) {
                        signalReceivedAt[neighborNode] = currNodeTime + time;
                        pq.add(new Pair(signalReceivedAt[neighborNode], neighborNode));
                    }
                }
            }
        }

        public int networkDelayTime(int[][] times, int n, int k) {
            // Build the adjacency list
            for (int[] time : times) {
                int source = time[0];
                int dest = time[1];
                int travelTime = time[2];

                adj.putIfAbsent(source, new ArrayList<>());
                adj.get(source).add(new Pair(travelTime, dest));
            }

            int[] signalReceivedAt = new int[n + 1];
            Arrays.fill(signalReceivedAt, Integer.MAX_VALUE);

            dijkstra(signalReceivedAt, k, n);

            int answer = Integer.MIN_VALUE;
            for (int i = 1; i <= n; i++) {
                answer = Math.max(answer, signalReceivedAt[i]);
            }

            // INT_MAX signifies atleat one node is unreachable
            return answer == Integer.MAX_VALUE ? -1 : answer;
        }

}
