package com.samueldu.leetcode.graph.unionfind;

import java.util.LinkedList;
import java.util.Queue;

/**
 * There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.
 *
 * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
 *
 * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
 */
public class NumberOfProvince_BFS {

        public int findCircleNum(int[][] M) {
            int[] visited = new int[M.length];
            int count = 0;
            Queue< Integer > queue = new LinkedList< >();
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

}

/**
 * Complexity Analysis
 *
 * Time complexity : O(n^2)O(n
 * 2
 *  ). The complete matrix of size n^2n
 * 2
 *   is traversed.
 *
 * Space complexity : O(n)O(n). A queuequeue and visitedvisited array of size nn is used.
 */

/**
 * As discussed in the above method, if we view the given matrix as an adjacency matrix of a graph, we can use graph algorithms easily to find the number of connected components. This approach makes use of Breadth First Search for a graph.
 *
 * In case of Breadth First Search, we start from a particular node and visit all its directly connected nodes first. After all the direct neighbours have been visited, we apply the same process to the neighbour nodes as well. Thus, we exhaust the nodes of a graph on a level by level basis. An example of Breadth First Search is shown below:
 *
 * Current
 * 3 / 13
 * In this case also, we apply BFS starting from one of the nodes. We make use of a visitedvisited array to keep a track of the already visited nodes. We increment the countcount of connected components whenever we need to start off with a new node as the root node for applying BFS which hasn't been already visited.
 */