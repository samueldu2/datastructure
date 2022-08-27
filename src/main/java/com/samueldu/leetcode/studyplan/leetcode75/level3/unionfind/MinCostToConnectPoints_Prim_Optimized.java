package com.samueldu.leetcode.studyplan.leetcode75.level3.unionfind;

/**
 * Approach 3: Prim's Algorithm (Optimized)
 * Intuition
 *
 * Although the min-heap method is often used to implement Prim's algorithm (as it's fairly easy to understand), due to its use of a heap to store the edges, its time complexity is suboptimal. Thus, we will present a more efficient way of implementing Prim's algorithm, which eliminates the use of min-heap to find the next lowest-weighted edge.
 *
 * In this approach, we use one minDistminDist array, where minDist[i]minDist[i] stores the weight of the smallest weighted edge to reach the i^{th}i
 * th
 *   node from any node in the current tree. We will iterate over the minDistminDist array and greedily pick the node that is not in the MST and has the smallest edge weight. We will add this node to the MST, and for all of its neighbors, we will try to update the value in minDistminDist.
 * We will repeat this process until all nodes are part of the MST.
 *
 * Initially, we can start with any node, say node 00. Thus we markminDist[0] = 0minDist[0]=0, and for the remaining nodes, the min distance to reach them is \infty∞.
 * Just like in the previous approach, we assume a 00 weighted temporary edge is used to reach the first node.
 *
 * Thus in this method, we will use this new way of selecting the min weight edges (instead of using a heap). Just like the previous method, we will use the inMSTinMST array to determine if adding the current edge will result in a cycle, and we can stop as soon as nn edges are included in MST (including our imaginary zero-weight edge to node 0).
 *
 * Algorithm
 *
 * Initialize some variables:
 *
 * nn - Number of nodes of the graph.
 * mstCostmstCost - Cost to build the MST.
 * edgesUsededgesUsed - Number of edges included in the MST.
 * inMSTinMST - Array to track which nodes are already part of the MST.
 * minDistminDist - Array to track the minimum edge weight to reach the i^{th}i
 * th
 *   node from any node that is already in the tree.
 * Initially, we start with node 00, and the cost to reach this node will be 00. To signify this, we set minDist[0]minDist[0] equal to 00.
 *
 * We will try adding nodes to our MST until edgesUsededgesUsed becomes equal to nn.
 *
 * We pick the node which uses the lowest weight edge and is not present in the MST.
 * We increment edgesUsededgesUsed by 11, mark this node as included in the MST, and add the edge weight used to reach this node to the mstCostmstCost.
 * Try updating the minimum distance to all adjacent nodes in minDistminDist.
 * We return the total cost of the MST, mstCostmstCost.
 *
 * Complexity Analysis
 *
 * If NN is the number of points in the input array.
 *
 * Time complexity: O(N^2)O(N
 * 2
 *  ).
 *
 * We pick all NN nodes one by one to include in the MST. Picking each node takes O(N)O(N) time and after picking a node, we iterate over all of its adjacent nodes, which also takes O(N)O(N) time.
 * Thus, the overall time complexity is O(N \cdot (N + N)) = O(N^2)O(N⋅(N+N))=O(N
 * 2
 *  ).
 * Space complexity: O(N)O(N).
 *
 * We use two arrays each of size NN, inMSTinMST and minDistminDist.
 * Thus, the overall space complexity is O(N + N) = O(N)O(N+N)=O(N).
 */
public class MinCostToConnectPoints_Prim_Optimized {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        int mstCost = 0;
        int edgesUsed = 0;

        // Track nodes which are visited.
        boolean[] inMST = new boolean[n];

        int[] minDist = new int[n];
        minDist[0] = 0;

        for (int i = 1; i < n; ++i) {
            minDist[i] = Integer.MAX_VALUE;
        }

        while (edgesUsed < n) {
            int currMinEdge = Integer.MAX_VALUE;
            int currNode = -1;

            // Pick least weight node which is not in MST.
            for (int node = 0; node < n; ++node) {
                if (!inMST[node] && currMinEdge > minDist[node]) {
                    currMinEdge = minDist[node];
                    currNode = node;
                }
            }

            mstCost += currMinEdge;
            edgesUsed++;
            inMST[currNode] = true;

            // Update adjacent nodes of current node.
            for (int nextNode = 0; nextNode < n; ++nextNode) {
                int weight = Math.abs(points[currNode][0] - points[nextNode][0]) +
                        Math.abs(points[currNode][1] - points[nextNode][1]);

                if (!inMST[nextNode] && minDist[nextNode] > weight) {
                    minDist[nextNode] = weight;
                }
            }
        }

        return mstCost;
    }

    public static void main(String [] args){
        MinCostToConnectPoints_Prim_Optimized m= new MinCostToConnectPoints_Prim_Optimized();
        int minDistance = m.minCostConnectPoints(new int [][]{{0, 0}, {2,2},{3, 10}, {5, 2}, {7,0}});
        System.out.println(minDistance);
    }
}