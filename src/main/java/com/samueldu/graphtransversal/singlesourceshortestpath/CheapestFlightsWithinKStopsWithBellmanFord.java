package com.samueldu.graphtransversal.singlesourceshortestpath;

import java.util.Arrays;

public class CheapestFlightsWithinKStopsWithBellmanFord {
    /**
     * Approach 3: Bellman-Ford
     * Intuition
     *
     * Let's look at the official definition of the Bellman-Ford algorithm straight from Wikipedia:
     *
     * Like Dijkstra's algorithm, Bellman-Ford proceeds by relaxation, in which approximations to the correct distance are replaced by better ones until they eventually reach the solution. In both algorithms, the approximate distance to each vertex is always an overestimate of the true distance and is replaced by the minimum of its old value and the length of a newly found path.
     *
     * However, Dijkstra's algorithm uses a priority queue to greedily select the closest vertex that has not yet been processed, and performs this relaxation process on all of its outgoing edges; by contrast, the Bellman-Ford algorithm simply relaxes all the edges and does this {|V|-1}∣V∣−1 times, where |V|∣V∣ is the number of vertices in the graph. In each of these repetitions, the number of vertices with correctly calculated distances grows, from which it follows that eventually, all vertices will have their correct distances. This method allows the Bellman-Ford algorithm to be applied to a wider class of inputs than Dijkstra.
     *
     * The term relax an edge simply means that for a given edge U -> V we check if \text{dU} + W_{\text{U,V}} < \text{dV}dU+W
     * U,V
     * ​
     *  <dV where \text{dU}dU and \text{dV}dV represent the shortest path distances of these nodes from the source right now. To relax an edge means to see if the shortest distance can be updated or not.
     *
     * An important part to understanding the Bellman Ford's working is that at each step, the relaxations lead to the discovery of new shortest paths to nodes. After the first iteration over all the vertices, the algorithm finds out all the shortest paths from the source to nodes which can be reached with one hop (one edge). That makes sense because the only edges we'll be able to relax are the ones that are directly connected to the source as all the other nodes have shortest distances set to inf initially.
     *
     * Similarly, after the (\text{K}+1)^{\text{th}}(K+1)
     * th
     *   step, Bellman-Ford will find the shortest distances for all the nodes that can be reached from the source using a maximum of K stops. Isn't that what the question asks us to do? If we run Bellman-Ford for \text{K} + 1K+1 iterations, it will find out shortest paths of length KK or less and it will find all such paths. We can then check if our destination node was reached or not and if it was, then the value for that node would be our shortest path!
     *
     * Let's quickly look at a couple of iterations of Bellman-Ford on a sample graph to understand how relaxation works and how \text{K+1}K+1 iterations can possibly give us our solution. The image below showcases the initial setup before the first iteration of Bellman-Ford is executed.
     *
     *
     * Let's look at what the graph looks like after a single iteration.
     *
     *
     * It's important to understand the meaning of what we said in the figure above. We are not saying that after the first iteration we will find the absolute shortest distance from A to B and D. We are just saying that the shortest distance using a single edge only will be found after first iteration. What happens in the next iteration? Well, we will find all the shortest paths that can be reached from the source by using at-most 2 edges. In this example, since the values for nodes B and D were updated in the previous iteration, they will be re-used in the next iteration to relax edges B -> E, B -> C, and D -> C.
     *
     * Isn't that what Dynamic Programming is all about......
     *
     * Well, yes! Using the optimal solutions to sub-problems to find optimal solutions to bigger problems. We use the optimal solutions to shortest paths using 1 edge to find shortest paths using 2 edges and so on.
     *
     *
     * We'll go one final iteration here since this is where things get interesting and this will bring some more clarity. The node E was discovered in the second iteration and we have the value 15 corresponding to it. However, one of the incoming edges C -> E wasn't relaxed in the second iteration because C was also discovered during that iteration. Now that we have a non-infinite value associated with C, we can use it to relax the edge C -> E and that leads to an even shorter path from A ... E!
     *
     *
     * Another important thing to note about this algorithm is that we don't need to build an adjacency matrix. This algorithm simply iterates over the edges of the graph and that information is already available in the input for the program. So we save on space there as opposed to other algorithms which we've seen.
     *
     * Algorithm
     *
     * We have a loop that does K + 1 iterations. The plus one is because we need to find the cheapest flight route with at most K stops in between. That translates to K + 1 edges at most.
     *
     * In each iteration, we loop over all the edges in the graph and try to relax each one of them. Again, note that the edges or the flights are already given to us in the input and don't need to build any kind of adjacency list or matrix structure which is otherwise standard for other graph algorithms.
     *
     * After K + 1 iterations, we check if the destination has been reached or not. If it's been discovered, then the distance at that point will be the shortest using at most K + 1 edges.
     *
     * We use an array to store the current shortest distances of each node from the source. This is possible because the number of nodes is less and we don't need to use a dictionary here. However, a single array is not sufficient here because any values updated in a particular iteration cannot be used to update other values in the same iteration. Thus, we need another distance array which will kind of server as values in the previous iteration. So we essentially use 2 arrays of size VV and we swap between them in each iteration i.e.
     *
     * Iteration-0 ----
     * Array-1 is the main array
     * Array-2 becomes the previous array
     * Iteration-1 ----
     * Array-2 is the main array
     * Array-1 becomes the previous array
     * Let's look at how the two arrays look like at the start of the first iteration. We'll take a look at a couple of iterations so that the it's easier to understand the implementations.
     *
     *
     * We discovered two new vertices which are directly connected from the source and their corresponding distances were updated accordingly.
     *
     *
     * Now let's look at how the two arrays would look like at the start of the second iteration. Now the roles would be reversed. The current array in the previous iteration now servers as the previous array.
     *
     *
     * Notice how the two arrays have swapped roles. You might be thinking that even though the red array is the current one, it doesn't have the latest values 7 and 5. Well, they will be used for the calculation of distance of node C and also, they will be copied over (re-calculated again due to the node A in previous array). Let's see how the two arrays look after the second iteration is complete.
     *
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: O(\text{K} \cdot \text{E})O(K⋅E) since we have \text{K}+1K+1 iterations and in each iteration, we go over all the edges in the graph.
     * Space Complexity: \text{O}(\text{V})O(V) occupied by the two distance arrays.
     */


        public int findCheapestPriceBF(int n, int[][] flights, int src, int dst, int K) {

            // We use two arrays for storing distances and keep swapping
            // between them to save on the memory
            long[][] distances = new long[2][n];
            Arrays.fill(distances[0], Integer.MAX_VALUE);
            Arrays.fill(distances[1], Integer.MAX_VALUE);
            distances[0][src] = distances[1][src] = 0;

            // K + 1 iterations of Bellman Ford
            for (int iterations = 0; iterations < K + 1; iterations++) {

                // Iterate over all the edges
                for (int[] edge : flights) {

                    int s = edge[0], d = edge[1], wUV = edge[2];

                    // Current distance of node "s" from src
                    long dU = distances[1 - iterations&1][s];

                    // Current distance of node "d" from src
                    // Note that this will port existing values as
                    // well from the "previous" array if they didn't already exist
                    long dV = distances[iterations&1][d];

                    // Relax the edge if possible
                    if (dU + wUV < dV) {
                        distances[iterations&1][d] = dU + wUV;
                    }
                }
            }

            return distances[K&1][dst] < Integer.MAX_VALUE ? (int)distances[K&1][dst] : -1;
        }


}
