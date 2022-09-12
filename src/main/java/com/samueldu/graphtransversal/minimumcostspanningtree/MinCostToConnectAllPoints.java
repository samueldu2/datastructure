package com.samueldu.graphtransversal.minimumcostspanningtree;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class MinCostToConnectAllPoints {
    /**
     * Kruskal's algorithm
     *
     * Overview
     * After reading the problem description, we can say we need to connect some points (the connection between any two points will be an edge whose weight is the Manhattan distance between those points) such that all points become connected and the sum of the weights of the chosen edges is minimized.
     *
     * We can say this problem is a variant of graph problems. More precisely, it is a Minimum Spanning Tree (MST) problem, where we are given nodes (points) and weighted edges (distance between two points) and we have to form an MST using them.
     *
     *
     * How do we know that this is an MST problem?
     *
     * Given a connected, weighted, and undirected graph, a minimum spanning tree is a subset of edges that connect all vertices while the total weights of these edges are minimum among all possible subsets.
     *
     * We can draw some similarities between the above definition and the problem here. We can consider our input as a complete graph (each point has an edge to every other point), and in this complete graph, we have to connect each point with minimum cost (sum of edge weights). Thus, we can rephrase the problem as "Find the Minimum Spanning Tree for the given set of points."
     *
     * graph to mst
     *
     * Concerning the MST problem, there exist several classic algorithms. In particular, we will demonstrate two of them, namely Kruskal's algorithm and Prim's algorithm, which are the most popular ones and feasible to implement during an interview.
     *
     * Note: If you are not familiar with either of the above algorithms, we highly recommend you to visit the Graph Explore Card and watch the video explanations to gain a general understanding of these algorithms as these are standard graph algorithms which are used frequently in MST problems.
     * We will focus on their implementation in the given problem rather than going into detail that how these algorithms work.
     *
     *
     * Approach 1: Kruskal's Algorithm
     * Intuition
     *
     * Kruskal's algorithm is a greedy algorithm for building a minimum spanning tree in a weighted and undirected graph.
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
     * Current
     * 1 / 12
     *
     * Implementation
     *
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
     * 1 / 15
     *
     * Implementation
     *
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

        public int minCostConnectPointsPrimsAlgorithm(int[][] points) {
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

}


class UnionFind {
    public int[] group;
    public int[] rank;

    public UnionFind(int size) {
        group = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; ++i) {
            group[i] = i;
        }
    }

    public int find(int node) {
        if (group[node] != node) {
            group[node] = find(group[node]);
        }
        return group[node];
    }

    public boolean union(int node1, int node2) {
        int group1 = find(node1);
        int group2 = find(node2);

        // node1 and node2 already belong to same group.
        if (group1 == group2) {
            return false;
        }

        if (rank[group1] > rank[group2]) {
            group[group2] = group1;
        } else if (rank[group1] < rank[group2]) {
            group[group1] = group2;
        } else {
            group[group1] = group2;
            rank[group2] += 1;
        }

        return true;
    }
}