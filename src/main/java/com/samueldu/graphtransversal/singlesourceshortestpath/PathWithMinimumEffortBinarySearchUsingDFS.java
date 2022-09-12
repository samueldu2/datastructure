package com.samueldu.graphtransversal.singlesourceshortestpath;

public class PathWithMinimumEffortBinarySearchUsingDFS {

    /**
     *Approach 5: Binary Search Using DFS
     * Intuition and Algorithm
     *
     * The solution is similar to Approach 4. Except that, here, we use a Depth First Search traversal to find if there exists a path from the source cell to destination cell for a given value middle value mid.
     *
     * Complexity Analysis
     *
     * Time Complexity : \mathcal{O}(m \cdot n)O(m⋅n). As in Approach 4. The only difference is that we are using Depth First Search instead of Breadth First Search and have similar time complexity.
     *
     * Space Complexity: \mathcal{O}(m \cdot n)O(m⋅n), As in Approach 4. In Depth First Search, we use the internal call stack (instead of the queue in Breadth First Search).
     * @param heights
     * @return
     */
        public int minimumEffortPath(int[][] heights) {
            int left = 0;
            int right = 1000000;
            int result = right;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (dfsUtil(heights, mid)) {
                    result = Math.min(result, mid);
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return result;
        }

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        boolean dfsUtil(int[][] heights, int mid) {
            int row = heights.length;
            int col = heights[0].length;
            boolean visited[][] = new boolean[row][col];
            return canReachDestinaton(0, 0, heights, visited, row, col, mid);
        }

        boolean canReachDestinaton(int x, int y, int[][] heights,
                                   boolean[][] visited, int row, int col, int mid) {
            if (x == row - 1 && y == col - 1) {
                return true;
            }
            visited[x][y] = true;
            for (int[] direction : directions) {
                int adjacentX = x + direction[0];
                int adjacentY = y + direction[1];
                if (isValidCell(adjacentX, adjacentY, row, col) && !visited[adjacentX][adjacentY]) {
                    int currentDifference = Math.abs(heights[adjacentX][adjacentY] - heights[x][y]);
                    if (currentDifference <= mid) {
                        if (canReachDestinaton(adjacentX, adjacentY, heights, visited, row, col, mid))
                            return true;
                    }
                }
            }
            return false;
        }

        boolean isValidCell(int x, int y, int row, int col) {
            return x >= 0 && x <= row - 1 && y >= 0 && y <= col - 1;
        }

}
