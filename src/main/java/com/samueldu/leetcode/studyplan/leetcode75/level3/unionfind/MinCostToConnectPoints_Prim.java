package com.samueldu.leetcode.studyplan.leetcode75.level3.unionfind;

import javafx.util.Pair;

import java.util.PriorityQueue;

/**
 * Approach 2: Prim's Algorithm
 * Intuition
 *
 * Prim's algorithm is also a greedy algorithm for building a minimum spanning tree in a weighted and undirected graph.
 *
 * In this algorithm, we include an arbitrary node in the MST and keep on adding the lowest-weighted edges of the nodes present in the MST until all nodes are included in the MST and no cycles are formed.
 *
 * prims
 *
 *
 * Just like the previous approach, we will use the input array indices to represent the nodes.
 *
 * In this algorithm, we can pick any node to start with. Then we will choose the lowest-weighted edge that connects a node present in the MST to a node not present in the MST. We could keep all of the edges in an array and then sort them. But then, for each new node that we add to the MST, we would have to add the new node's edges to the array and sort the array again. This would be a costly operation when done repeatedly.
 *
 * A more efficient way to track which edges are available and which of these edges has the lowest weight is to use a min-heap data structure. A min-heap is a tree-like data structure that always stores the minimum valued element (edge weight here) at the root and where insertion and removal of elements (edges) take logarithmic time.
 *
 * Now, we know how to greedily pick the lowest-weighted edge, but how can we check if including an edge will form a cycle in the MST?
 * Consider the example below. We can say, for the node 00 of the MST there exists an edge that is greedily best to choose and it connects to node 44. If node 44 is already present in the MST it means there already exists a path from node 00 to 44 and hence adding this edge would form a loop.
 *
 * include edge
 *
 * Thus, we can use one boolean array to record which nodes are already present in the MST. If both of an edge's nodes are already present in the MST, we will discard the edge. Otherwise, we will include this edge and mark the newly added node as present in the MST.
 *
 * Since an MST can only have n - 1n−1 edges, we can use it as an early exit condition to stop iterating over heap elements.
 *
 * Algorithm
 *
 * Initialize some variables:
 *
 * nn - Number of nodes of the graph.
 * mstCostmstCost - Cost to build the MST.
 * edgesUsededgesUsed - Number of edges included in the MST.
 * inMSTinMST - Array to track if a node was already included in MST or not.
 * heapheap - A min-heap to pick minimum weight edge, each element of heap is a pair of (edge \space weight, \space node)(edge weight, node).
 * Initially, we start with node 00 and the cost to include this node will be 00, thus we push all adjacent edges of node 00 in heapheap with their respective weightsweights using a for-loop. However, to make the code implementation cleaner, we will simply initialize the heapheap with the pair (0, \space 0)(0, 0), which represents a temporary edge to node 00 with a weight of 00.
 *
 * We pop elements from the heapheap and attempt to add them to the tree until edgesUsededgesUsed becomes equal to nn. We initially added one temporary edge, thus we stop when nn edges are added in the MST.
 *
 * We get the minimum weighted edge and the node from the top of heapheap and pop it.
 * If this node is already present in our MST ( inMST[node] == true )(inMST[node]==true) we discard this edge.
 * Otherwise, we include this node in our MST, increment edgesUsededgesUsed by 11, add the edge's weight to the mstCostmstCost, and push the edges of this node into the heapheap.
 * We return the total cost of MST, mstCostmstCost.
 *
 * Current
 *
 * Complexity Analysis
 *
 * If NN is the number of points in the input array.
 *
 * Time complexity: O(N^2 \cdot \log(N))O(N
 * 2
 *  ⋅log(N)).
 *
 * In the worst-case, we push/pop N \cdot (N-1) / 2 \approx N^2N⋅(N−1)/2≈N
 * 2
 *   edges of our graph in the heap. Each push/pop operation takes O(\log(N^2)) \approx \log(N)O(log(N
 * 2
 *  ))≈log(N) time.
 * Thus, the overall time complexity is O(N^2 \cdot \log(N))O(N
 * 2
 *  ⋅log(N)).
 * Space complexity: O(N^2)O(N
 * 2
 *  ).
 *
 * In the worst-case, we push N \cdot (N-1) / 2 \approx N^2N⋅(N−1)/2≈N
 * 2
 *   edges into the heap.
 * We use an array inMSTinMST of size NN to mark which nodes are included in MST.
 * Thus, the overall space complexity is O(N^2 + N) \approx O(N^2)O(N
 * 2
 *  +N)≈O(N
 * 2
 *  ).
 */
public class MinCostToConnectPoints_Prim {


        public int minCostConnectPoints(int[][] points) {
            int n = points.length;

            // Min-heap to store minimum weight edge at top.
            PriorityQueue<Pair<Integer, Integer>> heap = new PriorityQueue<>((a, b) -> (a.getKey() - b.getKey()));;

            // Track nodes which are included in MST.
            boolean[] inMST = new boolean[n];

            heap.add(new Pair(0, 0));
            int mstCost = 0;
            int edgesUsed = 0;

            while (edgesUsed < n) {
                Pair<Integer, Integer> topElement = heap.poll();

                int weight = topElement.getKey();
                int currNode = topElement.getValue();

                // If node was already included in MST we will discard this edge.
                if (inMST[currNode]) {
                    continue;
                }

                inMST[currNode] = true;
                mstCost += weight;
                edgesUsed++;

                for (int nextNode = 0; nextNode < n; ++nextNode) {
                    // If next node is not in MST, then edge from curr node
                    // to next node can be pushed in the priority queue.
                    if (!inMST[nextNode]) {
                        int nextWeight = Math.abs(points[currNode][0] - points[nextNode][0]) +
                                Math.abs(points[currNode][1] - points[nextNode][1]);

                        heap.add(new Pair(nextWeight, nextNode));
                    }
                }
            }

            return mstCost;
        }
    public static void main(String [] args){
        MinCostToConnectPoints_Prim m= new MinCostToConnectPoints_Prim();
        int minDistance = m.minCostConnectPoints(new int [][]{{0, 0}, {2,2},{3, 10}, {5, 2}, {7,0}});
        System.out.println(minDistance);
    }
}
