package com.samueldu.graphtransversal.unionfind;

/**
 * There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.
 *
 * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
 *
 * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
 *
 * Return the total number of provinces.
 */
public class NumberOfProvince_DFS {

        public void dfs(int[][] M, int[] visited, int i) {
            for (int j = 0; j < M.length; j++) {
                if (M[i][j] == 1 && visited[j] == 0) {
                    //mark all adjacent cities.
                    visited[j] = 1;
                    /**
                     * the following recursive call makes this method a depth first,
                     * as we are searching downwards first and then take care of the rest of the row elements.
                     */
                    dfs(M, visited, j);
                }
            }
        }
        public int findCircleNum(int[][] M) {
            int[] visited = new int[M.length];
            int count = 0;
            for (int i = 0; i < M.length; i++) {
                if (visited[i] == 0) {
                    //search on the i th row.
                    dfs(M, visited, i);
                    count++;
                }
            }
            return count;
        }


}
/**
 * Approach #1 Using Depth First Search[Accepted]
 * Algorithm
 *
 * The given matrix can be viewed as the Adjacency Matrix of a graph. By viewing the matrix in such a manner, our problem reduces to the problem of finding the number of connected components in an undirected graph. In order to understand the above statement, consider the example matrix below:
 *
 * M= [1 1 0 0 0 0
 *
 *     1 1 0 0 0 0
 *
 *     0 0 1 1 1 0
 *
 *     0 0 1 1 0 0
 *
 *     0 0 1 0 1 0
 *
 *     0 0 0 0 0 1]
 *
 * If we view this matrix M as the adjancency matrix of a graph, the following graph is formed:
 *
 * Friend_Circles
 *
 * In this graph, the node numbers represent the indices in the matrix M and an edge exists between the nodes numbered ii and jj, if there is a 1 at the corresponding M[i][j]M[i][j].
 *
 * In order to find the number of connected components in an undirected graph, one of the simplest methods is to make use of Depth First Search starting from every node. We make use of visitedvisited array of size NN(MM is of size NxNNxN). This visited[i]visited[i] element is used to indicate that the i^{th}i
 * th
 *   node has already been visited while undergoing a Depth First Search from some node.
 *
 * To undergo DFS, we pick up a node and visit all its directly connected nodes. But, as soon as we visit any of those nodes, we recursively apply the same process to them as well. Thus, we try to go as deeper into the levels of the graph as possible starting from a current node first, leaving the other direct neighbour nodes to be visited later on.
 *
 * The depth first search for an arbitrary graph is shown below:
 *
 * Current
 * 12 / 14
 * From the graph, we can see that the components which are connected can be reached starting from any single node of the connected group. Thus, to find the number of connected components, we start from every node which isn't visited right now and apply DFS starting with it. We increment the countcount of connected components for every new starting node.
 */