package com.samueldu.graphtransversal.singlesourceshortestpath;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Approach 4: Binary Search Using BFS
 * Intuition
 *
 * Our aim to find the minimum effort required to travel from source cell to destination cell. We know from the given constraints that the maximum height could be 10^6 (1000000)10
 * 6
 *  (1000000). So we know that our required absolute difference values would between 00 and 10^610
 * 6
 *  . We could use Binary Search and reduce our search space by half.
 *
 * Given the lower bound as 00 and upper bound as 10^610
 * 6
 *  , we could repeatedly calculate the middle value. Let this middle value be mid. We could divide our search space based on the following condition,
 *
 * If there exists a path from the source cell to the destination cell with the effort less than the value mid, we know that the required minimum effort value lies between lower bound 00 and mid.
 * Similarly, if there doesn't exist any path from a source cell to destination cell with the effort less than the value mid, we know that the required minimum effort value lies between mid and upper bound 10^610
 * 6
 *   .
 * To find if there exists a path from the source cell to the destination cell for a given mid value, we could use simple graph traversal. In this approach, we use Breadth First Search traversal.
 *
 * Algorithm
 *
 * Intialize the lower bound left as 00 and upper bound right as 10^610
 * 6
 *  . Calculate the middle value mid of the left and right value.
 *
 * Using Breadth First Search, check if there exists a path from source cell (x=0, y=0) to destination cell (x=row-1, y=column-1) with effort less than or equal to mid using method canReachDestination which returns a boolean value.
 *
 * If there exists a path from the source cell to the destination cell, we must update the result value as a minimum of the current result and mid and continue the search in the range between left and mid-1. For this, we must update the value of right to mid-1.
 *
 * Otherwise, we must search in the range between mid+1 and right and update left to mid+1.
 */
public class PathWithMinimumEffortBinarySearchUsingBFS {

    /**
     * Complexity Analysis
     *
     * Let mm be the number of rows and nn be the number of columns for the matrix \text{height}height.
     *
     * Time Complexity : \mathcal{O}(m \cdot n)O(m⋅n). We do a binary search to calculate the mid values and then do Breadth First Search on the matrix for each of those values.
     * Binary Search:To perform Binary search on numbers in range (0.. 10^{6})(0..10
     * 6
     *  ), the time taken would be \mathcal{O}(\log 10^{6})O(log10
     * 6
     *  ).
     *
     * Breadth First Search: The time complexity for the Breadth First Search for vertices V and edges E is \mathcal{O}(V+E)O(V+E) (See our Explore Card on BFS) Thus, in the matrix of size m \cdot nm⋅n, with m \cdot nm⋅n vertices and m \cdot nm⋅n edges (Refer time complexity of Approach 3), the time complexity to perform Breadth First Search would be \mathcal{O}(m \cdot n + m \cdot n)O(m⋅n+m⋅n) = \mathcal{O}(m \cdot n)O(m⋅n).
     *
     * This gives us total time complexity as \mathcal{O}(\log10^{6}\cdot(m \cdot n))O(log10
     * 6
     *  ⋅(m⋅n)) which is equivalent to \mathcal{O}(m \cdot n)O(m⋅n).
     *
     * Space Complexity: \mathcal{O}(m \cdot n)O(m⋅n), as we use a queue and visited array of size m \cdot nm⋅n
     * @param heights
     * @return
     */
        public int minimumEffortPath(int[][] heights) {
            int left = 0;
            int right = 1000000;
            int result = right;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (canReachDestinaton(heights, mid)) {
                    result = Math.min(result, mid);
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return result;
        }

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        // use bfs to check if we can reach destination with max absolute difference k
        boolean canReachDestinaton(int[][] heights, int k) {
            int row = heights.length;
            int col = heights[0].length;
            Deque<Cell> queue = new ArrayDeque<>();
            boolean[][] visited = new boolean[heights.length][heights[0].length];
            queue.addLast(new Cell(0, 0));
            visited[0][0] = true;
            while (!queue.isEmpty()) {
                Cell curr = queue.removeFirst();
                if(curr.x == row - 1 && curr.y == col - 1) {
                    return true;
                }
                for (int[] direction : directions) {
                    int adjacentX = curr.x + direction[0];
                    int adjacentY = curr.y + direction[1];
                    if (isValidCell(adjacentX, adjacentY, row, col) && !visited[adjacentX][adjacentY]) {
                        int currentDifference = Math.abs(heights[adjacentX][adjacentY] - heights[curr.x][curr.y]);
                        if (currentDifference <= k) {
                            visited[adjacentX][adjacentY] = true;
                            queue.addLast(new Cell(adjacentX, adjacentY));
                        }
                    }
                }
            }
            return false;
        }

        boolean isValidCell(int x, int y, int row, int col) {
            return x >= 0 && x <= row - 1 && y >= 0 && y <= col - 1;
        }


    class Cell {
        int x;
        int y;

        Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
