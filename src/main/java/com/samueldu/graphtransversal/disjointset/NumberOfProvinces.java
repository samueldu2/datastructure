package com.samueldu.graphtransversal.disjointset;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Number of Provinces
 *
 * Solution
 * There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b,
 * and city b is connected directly with city c, then city a is connected indirectly with city c.
 *
 * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
 *
 * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected,
 * and isConnected[i][j] = 0 otherwise.
 *
 * Return the total number of provinces.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * Output: 2
 * Example 2:
 *
 *
 * Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * Output: 3
 *
 *
 * Constraints:
 *
 * 1 <= n <= 200
 * n == isConnected.length
 * n == isConnected[i].length
 * isConnected[i][j] is 1 or 0.
 * isConnected[i][i] == 1
 * isConnected[i][j] == isConnected[j][i]
 */
public class NumberOfProvinces {
    /**
     * depth first search
     * Approach #1 Using Depth First Search[Accepted]
     * Algorithm
     * <p>
     * The given matrix can be viewed as the Adjacency Matrix of a graph. By viewing the matrix in such a manner, our problem reduces to the problem of finding the number of connected components in an undirected graph. In order to understand the above statement, consider the example matrix below:
     * <p>
     * M= [1 1 0 0 0 0
     * <p>
     * 1 1 0 0 0 0
     * <p>
     * 0 0 1 1 1 0
     * <p>
     * 0 0 1 1 0 0
     * <p>
     * 0 0 1 0 1 0
     * <p>
     * 0 0 0 0 0 1]
     * <p>
     * If we view this matrix M as the adjancency matrix of a graph, the following graph is formed:
     * <p>
     * Friend_Circles
     * <p>
     * In this graph, the node numbers represent the indices in the matrix M and an edge exists between the nodes numbered ii and jj, if there is a 1 at the corresponding M[i][j]M[i][j].
     * <p>
     * In order to find the number of connected components in an undirected graph, one of the simplest methods is to make use of Depth First Search starting from every node. We make use of visitedvisited array of size NN(MM is of size NxNNxN). This visited[i]visited[i] element is used to indicate that the i^{th}i
     * th
     * node has already been visited while undergoing a Depth First Search from some node.
     * <p>
     * To undergo DFS, we pick up a node and visit all its directly connected nodes. But, as soon as we visit any of those nodes, we recursively apply the same process to them as well. Thus, we try to go as deeper into the levels of the graph as possible starting from a current node first, leaving the other direct neighbour nodes to be visited later on.
     * <p>
     * The depth first search for an arbitrary graph is shown below:
     * <p>
     * Current
     * 13 / 14
     * From the graph, we can see that the components which are connected can be reached starting from any single node of the connected group. Thus, to find the number of connected components, we start from every node which isn't visited right now and apply DFS starting with it. We increment the countcount of connected components for every new starting node.
     * <p>
     * Complexity Analysis
     * <p>
     * Time complexity : O(n^2)O(n
     * 2
     * ). The complete matrix of size n^2n
     * 2
     * is traversed.
     * <p>
     * Space complexity : O(n)O(n). visitedvisited array of size nn is used.
     */

    public int findCircleNumDFS(int[][] M) {
        int[] visited = new int[M.length];
        int count = 0;
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == 0) {
                // dfs search on row i
                dfs(M, visited, i);
                count++;
            }
        }
        return count;
    }

    public void dfs(int[][] M, int[] visited, int i) {
        for (int j = 0; j < M.length; j++) {
            if (M[i][j] == 1 && visited[j] == 0) {
                visited[j] = 1; // this also sets visited[i] to 1, since M[i][i]=1.
                dfs(M, visited, j);//recursive on depth.
            }
        }
    }

    /**
     * Approach #2 Using Breadth First Search[Accepted]
     * Algorithm
     * <p>
     * As discussed in the above method, if we view the given matrix as an adjacency matrix of a graph, we can use graph algorithms easily to find the number of connected components. This approach makes use of Breadth First Search for a graph.
     * <p>
     * In case of Breadth First Search, we start from a particular node and visit all its directly connected nodes first. After all the direct neighbours have been visited, we apply the same process to the neighbour nodes as well. Thus, we exhaust the nodes of a graph on a level by level basis. An example of Breadth First Search is shown below:
     * <p>
     * Current
     * 1 / 13
     * In this case also, we apply BFS starting from one of the nodes. We make use of a visitedvisited array to keep a track of the already visited nodes. We increment the countcount of connected components whenever we need to start off with a new node as the root node for applying BFS which hasn't been already visited.
     * <p>
     * <p>
     * Complexity Analysis
     * <p>
     * Time complexity : O(n^2)O(n
     * 2
     * ). The complete matrix of size n^2n
     * 2
     * is traversed.
     * <p>
     * Space complexity : O(n)O(n). A queuequeue and visitedvisited array of size nn is used.
     *
     * @param M
     * @return
     */
    public int findCircleNumBFS(int[][] M) {
        int[] visited = new int[M.length];
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == 0) {
                queue.add(i);
                while (!queue.isEmpty()) {
                    int s = queue.remove();
                    visited[s] = 1;
                    /**
                     * look Ma, no recursion! the following is BFS, as we go through all adjacent nodes for the same parent node
                     * first in the adjacency matrix horizontally without using recursion.
                     */
                    for (int j = 0; j < M.length; j++) {
                        if (M[s][j] == 1 && visited[j] == 0)
                        /**
                         * entries in the columns are added for the next while loop to search along the rows with the same index
                         * we can do this because the adjacency matrix is symmetric.
                         */
                            queue.add(j);
                    }
                }
                count++;
            }
        }
        return count;
    }

    /**
     *
     * Approach #3 Using Union-Find Method[Accepted]
     * Algorithm
     *
     * Another method that can be used to determine the number of connected components in a graph is the union find method. The method is simple.
     *
     * We make use of a parentparent array of size NN. We traverse over all the nodes of the graph. For every node traversed, we traverse over all the nodes directly connected to it and assign them to a single group which is represented by their parentparent node. This process is called forming a unionunion. Every group has a single parentparent node, whose own parent is given by \text{-1}-1.
     *
     * For every new pair of nodes found, we look for the parents of both the nodes. If the parents nodes are the same, it indicates that they have already been united into the same group. If the parent nodes differ, it means they are yet to be united. Thus, for the pair of nodes (x, y)(x,y), while forming the union, we assign parent\big[parent[x]\big]=parent[y]parent[parent[x]]=parent[y], which ultimately combines them into the same group.
     *
     * The following animation depicts the process for a simple matrix:
     *
     * Current
     * 2 / 39
     * At the end, we find the number of groups, or the number of parent nodes. Such nodes have their parents indicated by a \text{-1}-1. This gives us the required count.
     *
     *
     * Complexity Analysis
     *
     * Time complexity : O(n^3)O(n
     * 3
     *  ). We traverse over the complete matrix once. Union and find operations take O(n)O(n) time in the worst case.
     * Space complexity : O(n)O(n). parentparent array of size nn is used.
     */
    public int findCircleNumUnionFind(int[][] M) {
        int[] parent = new int[M.length];
        Arrays.fill(parent, -1);
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M.length; j++) {
                if (M[i][j] == 1 && i != j) {
                    union(parent, i, j);
                }
            }
        }
        int count = 0;
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == -1)
                count++;
        }
        return count;
    }

    int find(int parent[], int i) {
        if (parent[i] == -1)
            return i;
        return find(parent, parent[i]);
    }

    void union(int parent[], int x, int y) {
        int xset = find(parent, x);
        int yset = find(parent, y);
        if (xset != yset)
            parent[xset] = yset;
    }

}

