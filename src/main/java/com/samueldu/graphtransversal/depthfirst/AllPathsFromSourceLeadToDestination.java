package com.samueldu.graphtransversal.depthfirst;

import java.util.ArrayList;
import java.util.List;

/**
 *059. All Paths from Source Lead to Destination
 * Medium
 *
 * 577
 *
 * 239
 *
 * Add to List
 *
 * Share
 * Given the edges of a directed graph where edges[i] = [ai, bi] indicates there is an edge between nodes ai and bi, and two nodes source and destination of this graph, determine whether or not all paths starting from source eventually, end at destination, that is:
 *
 * At least one path exists from the source node to the destination node
 * If a path exists from the source node to a node with no outgoing edges, then that node is equal to destination.
 * The number of possible paths from source to destination is a finite number.
 * Return true if and only if all roads from source lead to destination.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 3, edges = [[0,1],[0,2]], source = 0, destination = 2
 * Output: false
 * Explanation: It is possible to reach and get stuck on both node 1 and node 2.
 * Example 2:
 *
 *
 * Input: n = 4, edges = [[0,1],[0,3],[1,2],[2,1]], source = 0, destination = 3
 * Output: false
 * Explanation: We have two possibilities: to end at node 3, or to loop over node 1 and node 2 indefinitely.
 * Example 3:
 *
 *
 * Input: n = 4, edges = [[0,1],[0,2],[1,3],[2,3]], source = 0, destination = 3
 * Output: true
 *
 *
 * Constraints:
 *
 * 1 <= n <= 104
 * 0 <= edges.length <= 104
 * edges.length == 2
 * 0 <= ai, bi <= n - 1
 * 0 <= source <= n - 1
 * 0 <= destination <= n - 1
 * The given graph may have self-loops and parallel edges.
 */
public class AllPathsFromSourceLeadToDestination {
    /**
     * Overview
     * Let's try to boil the problem down to simpler, more commonly understandable terms.
     *
     * We are given a directed graph.
     * Also, as inputs we are provided a source and a destination.
     * We need to tell if all the paths from the source lead to the destination and and we can split this into a few criteria.
     * If a path exists from the source node to a node with no outgoing edges, then that node must be equal to destination. Here, we simply need to see that if a node does not have any neighbors in the adjacency list structure, then it has to be the destination or else we return false.
     * The number of possible paths from source to destination is a finite number. This simply means that the graph is actually a tree. Or in other words, there are no cycles in the graph. If there is a cycle in the graph, there would be an infinite number of paths from the source to the destination, as each would go around the cycle a different number of times.
     * Thus, the problem simply boils down to two things which we need to check during our graph traversal algorithm. We need to detect any cycles in the traversal and return false if there are any. Also, we need to ensure that if there is a leaf node encountered during the traversal, it is the destination node.
     *
     * The common strategies to traverse a graph are Breadth-First Search (a.k.a. BFS) and Depth-First Search (a.k.a. DFS). If one is not familiar with the concepts of BFS and DFS, we have an Explore card called Queue & Stack where we cover the BFS traversal as well as the DFS traversal. In this article, we'll be assuming that you're already familiar with these concepts.
     *
     * Approach: Depth First Search
     * Intuition
     *
     * This is one of the most common graph traversal techniques out there which makes use of the stack data structure. As mentioned in the overview section above, we simply need to run a graph traversal algorithm and check for two basic conditions. It is easy enough to check for a leaf node since the adjacency list entry for that node would not contain any neighbors. So our first condition can be easily checked i.e. if we encounter a leaf node, we check its equality to the destination node.
     *
     * As for cycle detection, there are multiple ways one can go about modifying the standard DFS algorithm. We will be following the node-coloring variant of the algorithm which is explained in the Introduction to Algorithms (CLRS) book. The idea is to do DFS of a given graph and while doing traversal, assign one of the below three colors to every vertex. According to the algorithm mentioned in the book, there are three different colors we can assign a node:
     *
     * WHITE ~ Vertex is not processed yet. Initially, all vertices are WHITE.
     *
     * GRAY ~ Vertex is being processed (DFS for this vertex has started, but not finished which means that all descendants (in DFS tree) of this vertex are not processed yet (or this vertex is in the function call stack).
     *
     *
     *
     * Figure 1. Highlighting an edge to a GRAY node thus creating a cycle in the graph.
     *
     * BLACK ~ Vertex and all its descendants are processed.
     *
     *
     *
     * Figure 2. Highlighting an edge to a BLACK node.
     *
     * While doing DFS, if an edge is encountered from current vertex to a GRAY vertex, then this edge is a back edge and hence there is a cycle. A GRAY node represents a node whose processing is still ongoing. Thus, if a descendent eventually leads back to a node whose processing is ongoing, it ends up creating a cycle in the directed graph and we call the edge leading back to a GRAY node as a backward edge.
     *
     * Algorithm
     *
     * Create a recursive function leadsToDest that takes the current node that needs to be processed and the color array.
     * We check if this node has any neighbors or not. If it doesn't then we return true or false based on whether this node equals the destination node.
     * There are three possibilities for the color of this current node.
     * If it is BLACK, do nothing; we've already processed it.
     * If it is WHITE, then mark it as GRAY since we are starting the processing of the graph rooted at this node.
     * Otherwise, if it is GRAY, then return false as we have discovered a backward edge, and hence a cycle.
     * Traverse all the adjacent nodes and for each of them call the recursive function for that node. If the function returns false, return false.
     * Mark the current Node as BLACK and return true.
     *
     * Complexity Analysis
     *
     * Time Complexity: Typically for an entire DFS over an input graph, it takes \mathcal{O}(V + E)O(V+E) where VV represents the number of vertices in the graph and likewise, EE represents the number of edges in the graph. In the worst case EE can be \mathcal{O}(V^2)O(V
     * 2
     *  ) in case each vertex is connected to every other vertex in the graph. However even in the worst case, we will end up discovering a cycle very early on and prune the recursion tree. If we were to traverse the entire graph, then the complexity would be \mathcal{O}(V^2)O(V
     * 2
     *  ) as the \mathcal{O}(E)O(E) part would dominate. However, due to pruning and backtracking in case of cycle detection, we end up with an overall time complexity of \mathcal{O}(V)O(V).
     * Space Complexity: \mathcal{O}(V + E)O(V+E) where \mathcal{O}(E)O(E) is occupied by the adjacency list and \mathcal{O}(V)O(V) is occupied by the recursion stack and the color states.
     * Why not Breadth-First Search?
     *
     * From this Stack Overflow answer:
     *
     * A BFS could be reasonable if the graph is undirected (be my guest at showing an efficient algorithm using BFS that would report the cycles in a directed graph!), where each cross edge defines a cycle (edge going from a node to an already visited node). If the cross edge is {v1, v2}, and the root (in the BFS tree) that contains those nodes is r, then the cycle is r ~ v1 - v2 ~ r (~ is a path, - a single edge), which can be reported almost as easily as in DFS.
     *
     * The only reason to use a BFS would be if you know your (undirected) graph is going to have long paths and small path cover (in other words, deep and narrow). In that case, BFS would require proportionally less memory for its queue than DFS' stack (both still linear of course).
     *
     * In all other cases, DFS is clearly the winner.
     */

            // We don't use the state WHITE as such anywhere. Instead, the "null" value in the states array below is a substitute for WHITE.
            enum Color { GRAY, BLACK };

            public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {

                List<Integer>[] graph = buildDigraph(n, edges);
                return leadsToDest(graph, source, destination, new Color[n]);
            }

            private boolean leadsToDest(List<Integer>[] graph, int node, int dest, Color[] states) {

                // If the state is GRAY, this is a backward edge and hence, it creates a loop.
                if (states[node] != null) {
                    return states[node] == Color.BLACK;
                }

                // If this is a leaf node, it should be equal to the destination.
                if (graph[node].isEmpty()) {
                    return node == dest;
                }

                // Now, we are processing this node. So we mark it as GRAY
                states[node] = Color.GRAY;

                for (int next : graph[node]) {

                    // If we get a `false` from any recursive call on the neighbors, we short circuit and return from there.
                    if (!leadsToDest(graph, next, dest, states)) {
                        return false;
                    }
                }

                // Recursive processing done for the node. We mark it BLACK
                states[node] = Color.BLACK;
                return true;
            }

            private List<Integer>[] buildDigraph(int n, int[][] edges) {
                List<Integer>[] graph = new List[n];
                for (int i = 0; i < n; i++) {
                    graph[i] = new ArrayList<>();
                }

                for (int[] edge : edges) {
                    graph[edge[0]].add(edge[1]);
                }

                return graph;
            }


}
