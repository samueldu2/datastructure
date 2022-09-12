package com.samueldu.graphtransversal.singlesourceshortestpath;

import javafx.util.Pair;

import java.util.*;

/**
 * Network Delay Time
 *
 * Solution
 * You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the time it takes for a signal to travel from source to target.
 *
 * We will send a signal from a given node k. Return the minimum time it takes for all the n nodes to receive the signal. If it is impossible for all the n nodes to receive the signal, return -1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
 * Output: 2
 * Example 2:
 *
 * Input: times = [[1,2,1]], n = 2, k = 1
 * Output: 1
 * Example 3:
 *
 * Input: times = [[1,2,1]], n = 2, k = 2
 * Output: -1
 *
 *
 * Constraints:
 *
 * 1 <= k <= n <= 100
 * 1 <= times.length <= 6000
 * times[i].length == 3
 * 1 <= ui, vi <= n
 * ui != vi
 * 0 <= wi <= 100
 * All the pairs (ui, vi) are unique. (i.e., no multiple edges.)
 *
 *  Hide Hint #1
 * We visit each node at some time, and if that time is better than the fastest time we've reached this node, we travel along outgoing edges in sorted order. Alternatively, we could use Dijkstra's algorithm.
 *
 */
public class NetworkDelayTime {

    /**
     * Overview
     * We have a network consisting of some nodes and directed edges. Each edge has three components: source, destination, and time. The time of an edge denotes the time it takes for a signal to travel from the source node to the destination node. A signal sent from node kk will travel along the edges and will reach some or all the nodes in the network. Our goal is to determine how much time the signal takes to reach every node in the network. If the signal cannot reach every node, we will return -1.
     *
     * It is possible for a node to receive signals from multiple adjacent nodes at different times. The figure below shows that node a receives signals from node k and node b at timestamps 1 and 2, respectively. The two signals are identical; hence, the timestamp at which a node receives the signal is the time that the first signal reaches the node. In the following example, the time required for node a to receive the signal will be 1 unit as this is the first signal to reach node a.
     *
     * fig
     *
     * Therefore, the problem boils down to finding the time required for each node to receive the signal, and the answer will be the maximum time required by any of the nodes. Why maximum? Because we need to find the time at which all nodes receive the signal, so the timestamp at which the last node receives the signal is the answer.
     */

    /**
     * Approach 1: Depth-First Search (DFS)
     * Intuition
     *
     * If you're not familiar with DFS, check out our Graph Explore Card.
     *
     * In this approach, we will simulate the signal and send it through the nodes as per the problem description to find the answer. Starting from node kk, the signal will travel to the adjacent nodes along the directed edges. We will track the signal movement with respect to time in a Depth-First Search manner.
     *
     * Start the DFS with node currNode = k and current timestamp currTime = 0. Before we traverse to the adjacent we mark the time required for the currNode in the array signalReceivedAt as currTime (signalReceivedAt[currNode] = currTime]). Now we will traverse all the adjacent nodes to the currNode. For each adjacent node, we will start a DFS with the updated timestamp i.e., equal to the sum of currTime and the time it takes to traverse the edge from currNode to the adjacent node.
     *
     * As we discussed before, there can be multiple signals received at a particular node and we are only interested in the time that the first signal reached the node. Hence, we will perform the DFS only if the currTime is less than the time we have stored corresponding to currNode in signalReceivedAt. This is because if the currTime is greater than or equal to signalReceivedAt[currNode], it means that currNode received a signal before the current signal could reach it.
     *
     * There is a trick that can reduce the execution time. Instead of traversing adjacent nodes arbitrarily, we can traverse them in increasing order of their travel time. Although this will increase the time complexity of the algorithm, it will increase the probability of finding the fastest time path first. Hence there could be fewer DFS calls and hence better execution time. The below slideshow demonstrates the algorithm.
     *
     * Current
     * 10 / 10
     * Algorithm
     *
     * Create an adjacency list such that adj[source] contains all destination nodes (dest) that the signal can travel to from the source node (source). For each destination node, there will be a pair (time, dest). Here, time denotes the time required for the signal to travel from source to dest.
     * Sort the edges connecting to every node in adj in increasing order of their travel time.
     * For all nodes, initialize signalReceivedAt as a large value to signify that, so far, no signal has been received.
     * Perform DFS on the node currNode as kk and with the currTime as 0. For each recursive call:
     * If the currTime is greater than or equal to signalReceivedAt[currNode] then return.
     * Otherwise, set signalReceivedAt[currNode] equal to currTime which is the new shortest time required to reach currNode. Then, perform a DFS for each of the adjacent nodes using the updated timestamp.
     * Find the maximum value in the array signalReceivedAt. If any value in signalReceivedAt is still the large value we initialized the array with, then return -1 as that node is not reachable from k. Otherwise, return the maximum value in the array.
     *
     *Complexity Analysis
     *
     * Here NN is the number of nodes and EE is the number of total edges in the given network.
     *
     * Time complexity: O((N - 1)! + E \log E)O((N−1)!+ElogE)
     *
     * In a complete graph with NN nodes and N*(N - 1)N∗(N−1) directed edges, we can end up traversing all the paths of all the possible lengths. The total number of paths can be represented as \sum_{len=1}^{N} {{N} \choose {len}} * len!∑
     * len=1
     * N
     * ​
     *  (
     * len
     * N
     * ​
     *  )∗len!, where len is the length of path which can be 11 to NN. This number can be represented as e.N!e.N!, it's essentially equal to the number of arrangements for NN elements. In our case, the first element will always be KK, hence the number of arrangements is e.(N - 1)!e.(N−1)!.
     *
     * Also, we sort the edges corresponding to each node, this can be expressed as E \log EElogE because sorting each small bucket of outgoing edges is bounded by sorting all of them, using the inequality x \log x + y \log y \leq (x+y) \log (x+y)xlogx+ylogy≤(x+y)log(x+y). Also, finding the minimum time required in signalReceivedAt takes O(N)O(N).
     *
     * Space complexity: O(N + E)O(N+E)
     *
     * Building the adjacency list will take O(E)O(E) space and the run-time stack for DFS can have at most NN active functions calls hence, O(N)O(N) space.
     *
     */

        // Adjacency list
        //Map<sourceID, List<Pair<travelTime, destinationID>>>
        Map<Integer, List<Pair<Integer, Integer>>> adj = new HashMap<>();

        private void DFS(int[] signalReceivedAt, int currNode, int currTime) {
            // If the current time is greater than or equal to the fastest signal received
            // Then no need to iterate over adjacent nodes
            if (currTime >= signalReceivedAt[currNode]) {
                return;
            }

            // Fastest signal time for currNode so far
            signalReceivedAt[currNode] = currTime;

            if (!adj.containsKey(currNode)) {
                return;
            }

            // Broadcast the signal to adjacent nodes
            for (Pair<Integer, Integer> edge : adj.get(currNode)) {
                int travelTime = edge.getKey();
                int neighborNode = edge.getValue();

                // currTime + time : time when signal reaches neighborNode
                DFS(signalReceivedAt, neighborNode, currTime + travelTime);
            }
        }

        public int networkDelayTimeDFS(int[][] times, int n, int k) {
            // Build the adjacency list
            for (int[] time : times) {
                int source = time[0];
                int dest = time[1];
                int travelTime = time[2];

                adj.putIfAbsent(source, new ArrayList<>());
                adj.get(source).add(new Pair(travelTime, dest));
            }

            // Sort the edges connecting to every node
            for (int node : adj.keySet()) {
                Collections.sort(adj.get(node), (a, b) -> a.getKey() - b.getKey());
            }

            int[] signalReceivedAt = new int[n + 1];
            Arrays.fill(signalReceivedAt, Integer.MAX_VALUE);

            DFS(signalReceivedAt, k, 0);

            int answer = Integer.MIN_VALUE;
            for (int node = 1; node <= n; node++) {
                answer = Math.max(answer, signalReceivedAt[node]);
            }

            // Integer.MAX_VALUE signifies atleat one node is unreachable
            return answer == Integer.MAX_VALUE ? -1 : answer;
        }


    /**
     *Approach 2: Breadth-First Search (BFS)
     * Intuition
     *
     * If you're not familiar with BFS, check out our Graph Explore Card.
     *
     * Similar to the previous approach, we will simulate the signal and send it through the nodes as per the problem description but this time using BFS. Starting from node kk, the signal will travel to the adjacent nodes along the directed edges. We will track the signal movement with respect to time in a Breadth-First Search manner.
     *
     * We will initialize the queue with the node currNode as kk and store the corresponding time required in signalReceivedAt as 0. The signal from node currNode will travel to every adjacent node. Iterate over every adjacent node neighborNode. We will add each adjacent node to the queue only if the signal from currNode via the current edge takes less time than the fastest signal to reach the adjacent node so far. Time taken by the fastest signal for currNode is denoted by signalReceivedAt[currNode].
     *
     * Algorithm
     *
     * Create an adjacency list such that adj[source] contains all destination nodes (dest) that the signal can travel to from the source node (source). For each destination node, there will be a pair (time, dest). Here, time denotes the time required for the signal to travel from source to dest.
     *
     * For all nodes, initialize signalReceivedAt as a large value to signify that, so far, no signal has been received.
     *
     * Add kk to the queue. While the queue is not empty:
     *
     * Pop the front node currNode from the queue
     * Traverse all the edges connected to currNode. Add the adjacent node neighborNode to the queue only if the signal takes less time than the value at signalReceivedAt[neighborNode]. Update the time at signalReceivedAt[neighborNode] to current signal time.
     * Find the maximum value in the array signalReceivedAt. If any value in signalReceivedAt is still the large value we initialized the array with, then return -1 as that node is not reachable from k. Otherwise, return the maximum value in the array.
     *
     * Complexity Analysis
     *
     * Here NN is the number of nodes and EE is the number of total edges in the given network.
     *
     * Time complexity: O(N \cdot E)O(N⋅E)
     *
     * Each of the NN nodes can be added to the queue for all the edges connected to it, hence in a complete graph, the total number of operations would be O(NE)O(NE). Also, finding the minimum time required in signalReceivedAt takes O(N)O(N).
     *
     * Space complexity: O(N \cdot E)O(N⋅E)
     *
     * Building the adjacency list will take O(E)O(E) space and the queue for BFS will use O(N \cdot E)O(N⋅E) space as there can be this much number of nodes in the queue.
     */


        // Adjacency list
        //Map<Integer, List<Pair<Integer, Integer>>> adj = new HashMap<>();

        private void BFS(int[] signalReceivedAt, int sourceNode) {
            //this Queue is simple FIFO.
            Queue<Integer> q = new LinkedList<>();
            q.add(sourceNode);

            // Time for starting node is 0
            signalReceivedAt[sourceNode] = 0;

            while (!q.isEmpty()) {
                int currNode = q.remove();

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
                    int arrivalTime = signalReceivedAt[currNode] + time;
                    if (signalReceivedAt[neighborNode] > arrivalTime) {
                        signalReceivedAt[neighborNode] = arrivalTime;
                        q.add(neighborNode);
                    }
                }
            }
        }

        public int networkDelayTimeBFS(int[][] times, int n, int k) {
            // Build the adjacency list
            for (int[] time : times) {
                int source = time[0];
                int dest = time[1];
                int travelTime = time[2];

                adj.putIfAbsent(source, new ArrayList<>());
                adj.get(source).add(new Pair(travelTime, dest));
            }
            //note the additional starting node is zero.
            int[] signalReceivedAt = new int[n + 1];
            Arrays.fill(signalReceivedAt, Integer.MAX_VALUE);

            BFS(signalReceivedAt, k);

            int answer = Integer.MIN_VALUE;
            for (int i = 1; i <= n; i++) {
                answer = Math.max(answer, signalReceivedAt[i]);
            }

            // INT_MAX signifies at least one node is unreachable
            return answer == Integer.MAX_VALUE ? -1 : answer;
        }

}
