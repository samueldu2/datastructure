package com.samueldu.leetcode.studyplan.leetcode75.level3.unionfind;

import java.util.ArrayList;
import java.util.Collections;

/**
 * You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].
 *
 * The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.
 *
 * Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.
 *Example 1:
 *
 *
 * Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
 * Output: 20
 * Explanation:
 *
 * We can connect the points as shown above to get the minimum cost of 20.
 * Notice that there is a unique path between every pair of points.
 * Example 2:
 *
 * Input: points = [[3,12],[-2,5],[-4,1]]
 * Output: 18
 *
 *
 * Constraints:
 *
 * 1 <= points.length <= 1000
 * -106 <= xi, yi <= 106
 * All pairs (xi, yi) are distinct.
 *
 */
public class MinCostToConnectPoints_Kruskal {

    /**
     * Kruskal's Algorithm
     *Kruskal's algorithm is a greedy algorithm for building a minimum spanning tree in a weighted and undirected graph.
     *
     * The algorithm operates by identifying the lowest-weighted edge that is not part of the MST. Then, if the nodes that belong to the edge are not connected, the edge is added to the MST. This process is repeated until all nodes are connected. Since we do not add an edge when its nodes are already connected, no cycles are formed.
     *
     * kruskal
     *
     *
     * First, we sort all the edges in increasing order of weight to prioritize adding the lowest-weighted edges first. We will continue to include edges in our MST until all nodes are connected, but how can we determine if including an edge will form a cycle or not?
     *
     * For this purpose, we can use a disjoint set data structure also called a union-find data structure.
     * If this data structure is new to you, we encourage you to visit the disjoint set explore card. The explore card will help you gain a basic understanding of the data structure and practice using it before proceeding.
     *
     * The union-find data structure has two primary functions:
     *
     * find(a)find(a): Function which returns the ID of the group in which node aa belongs.
     * union(a, b)union(a,b): Function to merge the groups of node aa and bb. If they already belong to the same group, we don't do anything and return falsefalse to signify the edge between aa and bb was not added. Otherwise, we return truetrue.
     * While there are several ways to implement union-find, in this approach, we will implement union-find by rank with path compression.
     *
     * So, after sorting all the edges in increasing order, we will try to connect the end nodes of each edge one by one.
     * First, we need to check if these two nodes are already connected. To do so, we can use the findfind function. If they are already connected by some other path, then adding this edge will form a cycle; thus, we will omit this edge from the MST. If the nodes are not connected, we can use the unionunion function to connect them. We will perform the findfind check inside the unionunion function to keep our code clean.
     *
     * union
     *
     * We can make a small optimization here. Instead of iterating over the whole array, when we connect (n-1)(n−1) edges, we can skip the remaining edges because the MST is complete. The MST becomes complete as soon as it contains (n-1)(n−1) edges because a tree with nn nodes will always have (n-1)(n−1) edges.
     *
     * We will be given coordinate points in our input array, so we will name each point something unique such that working with them is easy. In the union-find data structure, representing each node with an integer will simplify our implementation, so we will represent each point as its index in the input array.
     *
     * Algorithm
     *
     * Create a class UnionFindUnionFind:
     *
     * group, \space rankgroup, rank - Arrays to store the group (also known as root) and rank of each node.
     * find(a)find(a) - Function to find the group of node aa using path compression.
     * union(a, b)union(a,b) - Function to merge groups of nodes aa and bb by rank.
     * Initialize some variables:
     *
     * nn - Number of nodes in the graph.
     * mstCostmstCost - Cost to build the MST.
     * edgesUsededgesUsed - Number of edges included in the MST.
     * ufuf - UnionFind object of size nn to connect nn nodes.
     * allEdgesallEdges - Array to store all the edges of our graph.
     * Iterate over all coordinate points, and for each coordinate point, create an edge to all other coordinate points. Store the edges in the allEdgesallEdges array. Each element of allEdgesallEdges contains three values: edge weight, node1, node2.
     *
     * Sort the elements in allEdgesallEdges in increasing order of their edge weights.
     *
     * Iterate over each edge in allEdgesallEdges until edgesUsededgesUsed becomes equal to n-1n−1. For each edge:
     *
     * Try joining both the nodes of the current edge.
     * If the nodes are already connected, we discard the current edge because including this edge would create a cycle. Otherwise, we add the weight of the current edge to the mstCostmstCost variable and increment edgesUsededgesUsed by 11.
     * Return the total cost of MST, mstCostmstCost.
     *
     * Complexity Analysis
     *
     * If NN is the number of points in the input array.
     *
     * Time complexity: O(N^2 \cdot \log(N))O(N
     * 2
     *  ⋅log(N)).
     *
     * First, we store N \cdot (N-1) / 2 \approx N^2N⋅(N−1)/2≈N
     * 2
     *   edges of our complete graph in the allEdgesallEdges array which will take O(N^2)O(N
     * 2
     *  ) time, and sorting this array will take O(N^2 \cdot \log(N^2))O(N
     * 2
     *  ⋅log(N
     * 2
     *  )) time.
     *
     * Then, we iterate over the allEdgesallEdges array, and for each element, we perform a union-find operation. The amortized time complexity for union-find by rank and path compression is O(\alpha(N))O(α(N)), where \alpha(N)α(N) is Inverse Ackermann Function, which is nearly constant, even for large values of NN.
     *
     * Thus, the overall time complexity is O(N^2 + N^2 \cdot \log(N^2) + N^2 \cdot \alpha(N)) \approx O(N^2 \cdot \log(N^2) \approx O(N^2 \cdot \log(N))O(N
     * 2
     *  +N
     * 2
     *  ⋅log(N
     * 2
     *  )+N
     * 2
     *  ⋅α(N))≈O(N
     * 2
     *  ⋅log(N
     * 2
     *  )≈O(N
     * 2
     *  ⋅log(N)).
     *
     * Space complexity: O(N^2)O(N
     * 2
     *  ).
     *
     * We use an array allEdgesallEdges to store all N \cdot (N-1) / 2 \approx N^2N⋅(N−1)/2≈N
     * 2
     *   edges of our graph.
     * UnionFind object ufuf uses two arrays each of size NN to store the group id and rank of all the nodes.
     * Thus, the overall space complexity is O(N^2 + N) \approx O(N^2)O(N
     * 2
     *  +N)≈O(N
     * 2
     *  ).
     */
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        ArrayList<int[]> allEdges = new ArrayList<>();

        // Storing all edges of our complete graph.
        for (int currNext = 0; currNext < n; ++currNext) {
            for (int nextNext = currNext + 1; nextNext < n; ++nextNext) {
                int weight = Math.abs(points[currNext][0] - points[nextNext][0]) +
                        Math.abs(points[currNext][1] - points[nextNext][1]);
                //weight, from node # , to node #
                int[] currEdge = {weight, currNext, nextNext};
                allEdges.add(currEdge);
            }
        }

        // Sort all edges in increasing order.
        Collections.sort(allEdges, (a, b) -> Integer.compare(a[0], b[0]));

        UnionFind uf = new UnionFind(n);
        int mstCost = 0;
        int edgesUsed = 0;

        for (int i = 0; i < allEdges.size() && edgesUsed < n - 1; ++i) {
            int node1 = allEdges.get(i)[1];
            int node2 = allEdges.get(i)[2];
            int weight = allEdges.get(i)[0];

            if (uf.union(node1, node2)) {
                mstCost += weight;
                edgesUsed++;
            }
        }

        return mstCost;
    }

    public static void main(String [] args){
        MinCostToConnectPoints_Kruskal m= new MinCostToConnectPoints_Kruskal();
        int minDistance = m.minCostConnectPoints(new int [][]{{0, 0}, {2,2},{3, 10}, {5, 2}, {7,0}});
        System.out.println(minDistance);
    }
}
