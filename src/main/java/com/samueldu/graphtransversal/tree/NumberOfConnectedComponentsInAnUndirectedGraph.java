package com.samueldu.graphtransversal.tree;

import java.util.ArrayList;
import java.util.List;

public class NumberOfConnectedComponentsInAnUndirectedGraph {
    /**
     * Approach 1: Depth-First Search (DFS)
     * Intuition
     *
     * If you're not familiar with DFS, check out our Explore Card.
     *
     * In an undirected graph, a connected component is a subgraph in which each pair of vertices is connected via a path. So essentially, all vertices in a connected component are reachable from one another.
     *
     * Let's see how we can use DFS to solve the problem. If we run DFS, starting from a particular vertex, it will continue to visit the vertices depth-wise until there are no more adjacent vertices left to visit. Thus, it will visit all of the vertices within the connected component that contains the starting vertex. Each time we finish exploring a connected component, we can find another vertex that has not been visited yet, and start a new DFS from there. The number of times we start a new DFS will be the number of connected components.
     *
     * Here is an example illustrating this approach.
     *
     * fig
     *
     * Figure 1. An example demonstrating the DFS approach.
     *
     * Algorithm
     *
     * Create an adjacency list such that adj[v] contains all the adjacent vertices of vertex v.
     * Initialize a hashmap or array, visited, to track the visited vertices.
     * Define a counter variable and initialize it to zero.
     * Iterate over each vertex in edges, and if the vertex is not already in visited, start a DFS from it. Add every vertex visited during the DFS to visited.
     * Every time a new DFS starts, increment the counter variable by one.
     * At the end, the counter variable will contain the number of connected components in the undirected graph.
     *
     * Complexity Analysis
     *
     * Here EE = Number of edges, VV = Number of vertices.
     *
     * Time complexity: {O}(E + V)O(E+V).
     *
     * Building the adjacency list will take {O}(E)O(E) operations, as we iterate over the list of edges once, and insert each edge into two lists.
     *
     * During the DFS traversal, each vertex will only be visited once. This is because we mark each vertex as visited as soon as we see it, and then we only visit vertices that are not marked as visited. In addition, when we iterate over the edge list of each vertex, we look at each edge once. This has a total cost of {O}(E + V)O(E+V).
     *
     * Space complexity: {O}(E + V)O(E+V).
     *
     * Building the adjacency list will take {O}(E)O(E) space. To keep track of visited vertices, an array of size {O}(V)O(V) is required. Also, the run-time stack for DFS will use {O}(V)O(V) space.
     */
    private void dfs(List<Integer>[] adjList, int[] visited, int startNode) {
            visited[startNode] = 1;

            for (int i = 0; i < adjList[startNode].size(); i++) {
                if (visited[adjList[startNode].get(i)] == 0) {
                    dfs(adjList, visited, adjList[startNode].get(i));
                }
            }
        }

        public int countComponentsDFS(int n, int[][] edges) {
            int components = 0;
            int[] visited = new int[n];

            List<Integer>[] adjList = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                adjList[i] = new ArrayList<Integer>();
            }

            for (int i = 0; i < edges.length; i++) {
                adjList[edges[i][0]].add(edges[i][1]);
                adjList[edges[i][1]].add(edges[i][0]);
            }

            for (int i = 0; i < n; i++) {
                if (visited[i] == 0) {
                    components++;
                    dfs(adjList, visited, i);
                }
            }
            return components;
        }

    /**
     * Approach 2: Disjoint Set Union (DSU)
     * Imagine we have a graph with N vertices and 0 edges. The number of connected components will be N in that graph.
     *
     * fig
     *
     * Let's now add the edge from vertex 1 to vertex 2. This will decrease the number of components by 1. This is because vertices 1 and 2 are now in the same component.
     *
     * fig
     *
     * When we then add the edge from vertex 2 to vertex 3, the number of components will decrease by 1 again.
     *
     * fig
     *
     * However, this pattern will not continue when we add the edge from vertex 1 to vertex 3. The number of components will not change because vertices 1, 2, and 3 are already in the same component.
     *
     * fig
     *
     * The above observation is the main intuition behind the DSU approach.
     *
     * Algorithm
     *
     * Initialize a variable count with the number of vertices in the input.
     * Traverse all of the edges one by one, performing the union-find method combine on each edge. If the endpoints are already in the same set, then keep traversing. If they are not, then decrement count by 1.
     * After traversing all of the edges, the variable count will contain the number of components in the graph.
     *
     * Complexity Analysis
     *
     * Here EE = Number of edges, VV = Number of vertices.
     *
     * Time complexity: O(E\cdotα(n))O(E⋅α(n)).
     *
     * Iterating over every edge requires O(E)O(E) operations, and for every operation, we are performing the combine method which is O(α(n))O(α(n)), where α(n) is the inverse Ackermann function.
     *
     * Space complexity: O(V)O(V).
     *
     * Storing the representative/immediate-parent of each vertex takes O(V)O(V) space. Furthermore, storing the size of components also takes O(V)O(V) space.
     */
    private int find(int[] representative, int vertex) {
            if (vertex == representative[vertex]) {
                return vertex;
            }
        /**
         * Recursion with side effects here!!!
         * notice the side effect of setting the parent of all set members to the same root.
         */

            return representative[vertex] = find(representative, representative[vertex]);
        }

        private int combine(int[] representative, int[] size, int vertex1, int vertex2) {
            vertex1 = find(representative, vertex1);
            vertex2 = find(representative, vertex2);

            if (vertex1 == vertex2) {
                return 0;
            } else {
                //always merge into the larger set, us the root of larger set.
                if (size[vertex1] > size[vertex2]) {
                    //note size of the set is size[IndexOfTheRrootOfTheSet].
                    size[vertex1] += size[vertex2];
                    representative[vertex2] = vertex1;
                } else {
                    size[vertex2] += size[vertex1];
                    representative[vertex1] = vertex2;
                }
                return 1;
            }
        }

        public int countComponentsUsingDisjointSet(int n, int[][] edges) {
            int[] representative = new int[n];
            int[] size = new int[n];

            for (int i = 0; i < n; i++) {
                representative[i] = i;
                size[i] = 1;
            }

            int components = n;
            for (int i = 0; i < edges.length; i++) {
                components -= combine(representative, size, edges[i][0], edges[i][1]);
            }

            return components;
        }

}
