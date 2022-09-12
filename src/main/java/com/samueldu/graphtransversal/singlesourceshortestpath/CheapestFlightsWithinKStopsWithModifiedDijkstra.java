package com.samueldu.graphtransversal.singlesourceshortestpath;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * There are n cities connected by some number of flights. You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.
 *
 * You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
 * Output: 700
 * Explanation:
 * The graph is shown above.
 * The optimal path with at most 1 stop from city 0 to 3 is marked in red and has cost 100 + 600 = 700.
 * Note that the path through cities [0,1,2,3] is cheaper but is invalid because it uses 2 stops.
 * Example 2:
 *
 *
 * Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
 * Output: 200
 * Explanation:
 * The graph is shown above.
 * The optimal path with at most 1 stop from city 0 to 2 is marked in red and has cost 100 + 100 = 200.
 * Example 3:
 *
 *
 * Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
 * Output: 500
 * Explanation:
 * The graph is shown above.
 * The optimal path with no stops from city 0 to 2 is marked in red and has cost 500.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 100
 * 0 <= flights.length <= (n * (n - 1) / 2)
 * flights[i].length == 3
 * 0 <= fromi, toi < n
 * fromi != toi
 * 1 <= pricei <= 104
 * There will not be any multiple flights between two cities.
 * 0 <= src, dst, k < n
 * src != dst
 */
public class CheapestFlightsWithinKStopsWithModifiedDijkstra {
    /**
     *    Approach 1: Dijkstra's Algorithm
     * Intuition
     *
     * If we forget about the part where the number of stops is limited, then the problem simply becomes the shortest path problem on a weighted graph, right? We can treat this as a graph problem where:
     *
     * the cities can be treated as nodes in a graph
     * the connections between each of the cities can be treated as the edges and finally
     * the cost of going from one city to another would be the weight of the edges in the graph.
     * It's important to model the problem in a way that standard algorithms or their slight variations can be used for the solutions. Whenever we have a problem where we're given a bunch of entities and they have some sort of connections between them, more often than not it can be modeled as a graph problem. Once you've figured out that the question can be modeled as a graph problem, you then need to think about the various aspects of a graph i.e.
     *
     * directed vs undirected
     * weighted vs unweighted
     * cyclic vs acyclic
     * These aspects will help define the algorithm that you can consider for solving the problem at hand. For example a standard rule of thumb that is followed for solving shortest path problems is that we mostly use Breadth-first search for unweighted graphs and use Dijkstra's algorithm for weighted graphs. An implied condition to apply the Dijkstra's algorithm is that the weights of the graph must be positive. If the graph has negative weights and can have negative weighted cycles, we would have to employ another algorithm called the Bellman Ford's. The point here is that the properties of the graph and the goal define the kind of algorithms we might be able to use.
     *
     * Coming back to the original statement at the beginning of the article. If we don't consider the part where the number of stops is limited, this problem becomes a standard shortest paths problem in a weighted graph with positive weights and hence, it becomes a prime candidate for Dijkstra's. As we all know, Dijkstra's uses a min-heap (priority queue) as the main data structure for always picking out the node which can be reached in the shortest amount of time/cost/weight from the current point starting all the way from the source. That approach as it is won't work out for this problem.
     *
     * First of all, we need to keep track of the number of stops taken to reach a node (city), in addition to the shortest path from the source node. This is important because if at any point we find that we have exhausted KK stops, we can't progress any further from that node because the number of stops are bounded by the problem. Let's consider a simple example and run through it with the basic Dijkstra's algorithm and see why we might run into a problem with the off-the-shelf code i.e. Dijkstra's without any modifications.
     *
     *
     * Now suppose that we want to go from the source node A in the graph above to the destination node E via the cheapest possible route with at most 2 stops. Let's ignore the number of stops for now and see how the usual Dijkstra would unfold and pick the nodes. So first of all, we will consider the neighbors of the source node and add them to our min-heap. Next, we will pick the element with the current shortest distance which would be D with a value of 5 as opposed to B with 7.
     *
     *
     * Moving on, the next node that will be picked is B since it has the current shortest distance from the source. Let's see what the heap looks like once we pick B and process its neighbors. Note that according to the algorithm, once a node has been processed i.e., once a node is popped from the min-heap, we never consider that node again in some other node's neighbors i.e., we never add it again to the heap down the line. This is because of the greedy nature of the algorithm. When a node is removed from the heap, it is guaranteed that the distance from the source at that point is the shortest distance. The processed nodes are marked in blue in the figures here.
     *
     *
     * Moving on, the algorithm will pick C and its neighbor E will be added into the heap. You'll notice that there are two nodes containing the city E which is fine since E hasn't been processed yet and this just means there are multiple paths of reaching E.
     *
     *
     * Next, we will remove the node E with a distance of 12 from the source and 3 stops from the source. At this point, we cannot go any further i.e. we cannot consider its neighbor because We have already exhausted the number of stops in this example. So, we don't add the neighbor which also happens to be the destination node to the heap. The only node left in the heap is E with a distance of 15 from source and 2 stops from the source.
     *
     * Here's the problem now. We will not consider this node because we have already processed the node E in the previous step. Clearly, the distance 15 is greater than 12. So Dijkstra's will discard this heap node and the algorithm will finish, without ever reaching the destination!
     *
     * The thing we need to modify here is that we need to re-consider a node if the distance from the source is shorter than what we have recorded. So we won't change the min-heap's priority which is to pick nodes with the shortest distance from the source. However, if we ever encounter a node that has already been processed before but the number of stops from the source is lesser than what was recorded before, we will add it to the heap so that it gets considered again! That's the only change we need to make to make Dijkstra's compliant with the limitation on the number of stops.
     *
     * Algorithm
     *
     * Initialize a min-heap or a priority queue. Let's call it H for our algorithm.
     *
     * We will need a couple of arrays here. One would be for maintaining the shortest distances of each node from the source and another one would be for maintaining the shortest number of stops from the source.
     *
     * Next, we need to convert the input into an adjacency matrix format. So, we will process the given input and build an adjacency matrix out of it.
     *
     * Add (\text{source}, 0, 0)(source,0,0) into the heap. The middle value represents the current shortest distance from the source and the last value represents the current minimum number of stops from the source to reach this node.
     *
     * We assume that these values for all the other nodes in the graph are inf.
     *
     * We continue processing the nodes until either of the following conditions are met:
     *
     * We reach the destination node or
     * We exhaust the heap which would mean we were not able to reach the destination at all.
     * At each step, we remove a node from the heap i.e. ExtractMin operation on the min-heap. This would represent the node with the shortest distance from the source amongst the ones in the heap. Let's call this node C.
     *
     * We iterate over all of C's neighbors which we can obtain from our adjacency matrix. For each neighbor, we check if the value \text{dC} + \text{W}_\text{C, V}dC+W
     * C, V
     * ​
     *   is less than \text{dV}dV where VV represents the neighbor node, \text{dC}dC and \text{dV}dV represent the shortest distances (from the dictionary) of these nodes from the source and finally, \text{W}_\text{C, V}W
     * C, V
     * ​
     *   represents the weight (cost of the flight) from node (city) CC to VV.
     *
     * If this is not the case then we check if number of stops for node CC + 1 is lower than the number of stops for the node VV (from the other dictionary). If that is the case, then it means there is a path from the source to the node VV which is slightly expensive than what we have right now, but it has lesser stops and hence, it should be considered.
     *
     * If either of the two conditions above are satisfied, we add the node VV to the heap with updated distance and number of stops. In any case, we will update the corresponding dictionary as well.
     *
     * Complexity Analysis
     *
     * Time Complexity: Let EE represent the number of flights and VV represent the number of cities. The time complexity is mainly governed by the number of times we pop and push into the heap. We will process each node (city) atleast once and for each city popped from the queue, we iterate over its adjacency matrix and can potentially add all its neighbors to the heap. Thus, the time taken for extract min and then addition to the heap (or simply, heap replace) would be \text{O}(\text{V}^2 \cdot \text{log V})O(V
     * 2
     *  ⋅log V).
     *
     * Let's talk a bit more about the implementation of Dijkstra's here. The traditional algorithm is not exactly written the way we've explained above.
     * The traditional algorithm adds all the nodes into the heap with the source having a distance value of 0 and all others having a value inf.
     * When we process the neighbors of a node and find that a particular neighbor can be reached in a shorter distance (or lesser number of stops), we update its value in the heap. In our implementation, we add a new node with updated values rather than updating the value of the existing node. To do that, we will need another dictionary that will probably keep the index location for a node in the heap or something like that. This would be necessary because a heap is not a binary search tree and it doesn't have any search properties for quick search and updates.
     * If we keep the number of nodes in the heap fixed to VV, then the complexity would be \text{O}((\text{V} + \text{E}) \cdot \text{log V})O((V+E)⋅log V). Granted, in our case, the heap might contain more than \text{V}V nodes at some point due to the same city being added multiple times. Therefore, the complexity would be slightly more. That is not being accounted for here since that is an implementation detail and not necessary for the algorithm we discussed here.
     * Yet another point to keep in mind here is that we are using an adjacency matrix rather than adjacency list here. The typical Dijkstra's algorithm would use an adjacency list and that brings down the complexity slightly because you don't "check" if a connection exists or not unlike in adjacency matrix. However, since the number of nodes are very less for this problem, we preferred to take the route of adjacency matrix as that gives us sequential access to elements and leads to speed-ups due to cache localization.
     * Space Complexity: \text{O}(\text{V}^2)O(V
     * 2
     *  ) is the overall space complexity. \text{O}(\text{V})O(V) is occupied by the two dictionaries and also by the heap and \text{V}^2V
     * 2
     *   by the adjacency matrix structure. As mentioned above, there might be duplicate cities in the heap with different distances and number of stops due to our implementation. But we are not taking that into consideration here. This is the space complexity of the traditional Dijkstra's and it doesn't change with the algorithm modifications (not the implementation modifications) we've done here.
     */



        public int findCheapestPriceMOdifiedDijkstra(int n, int[][] flights, int src, int dst, int K) {

            // Build the adjacency matrix
            int adjMatrix[][] = new int[n][n];
            for (int[] flight: flights) {
                adjMatrix[flight[0]][flight[1]] = flight[2];
            }

            // Shortest distances array
            int[] distances = new int[n];

            // Shortest steps array
            int[] currentStops = new int[n];
            Arrays.fill(distances, Integer.MAX_VALUE);
            Arrays.fill(currentStops, Integer.MAX_VALUE);
            distances[src] = 0;
            currentStops[src] = 0;

            // The priority queue would contain (node, cost, stops)
            PriorityQueue<int[]> minHeap = new PriorityQueue<int[]>((a, b) -> a[1] - b[1]);
            minHeap.offer(new int[]{src, 0, 0});

            while (!minHeap.isEmpty()) {

                int[] info = minHeap.poll();
                int node = info[0], stops = info[2], cost = info[1];

                // If destination is reached, return the cost to get here
                if (node == dst) {
                    return cost;
                }

                // If there are no more steps left, continue
                if (stops == K + 1) {
                    continue;
                }

                // Examine and relax all neighboring edges if possible
                for (int nei = 0; nei < n; nei++) {
                    if (adjMatrix[node][nei] > 0) {
                        int dU = cost, dV = distances[nei], wUV = adjMatrix[node][nei];

                        // Better cost?
                        if (dU + wUV < dV) {
                            minHeap.offer(new int[]{nei, dU + wUV, stops + 1});
                            distances[nei] = dU + wUV;
                            currentStops[nei] = stops;
                        }
                        else if (stops < currentStops[nei]) {
                            // Better steps?
                            minHeap.offer(new int[]{nei, dU + wUV, stops + 1});
                        }
                    }
                }
            }

            return distances[dst] == Integer.MAX_VALUE? -1 : distances[dst];
        }

}
