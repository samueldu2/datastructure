package com.samueldu.graphtransversal.singlesourceshortestpath;

/**
 *  Path With Minimum Effort
 *
 * Solution
 * You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.
 *
 * A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
 *
 * Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
 * Output: 2
 * Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
 * This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.
 * Example 2:
 *
 *
 *
 * Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
 * Output: 1
 * Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].
 * Example 3:
 *
 *
 * Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
 * Output: 0
 * Explanation: This route does not require any effort.
 *
 *
 * Constraints:
 *
 * rows == heights.length
 * columns == heights[i].length
 * 1 <= rows, columns <= 100
 * 1 <= heights[i][j] <= 106
 *
 *   Hide Hint #1
 * Consider the grid as a graph, where adjacent cells have an edge with cost of the difference between the cells.
 *    Hide Hint #2
 * If you are given threshold k, check if it is possible to go from (0, 0) to (n-1, m-1) using only edges of ≤ k cost.
 *    Hide Hint #3
 * Binary search the k value.
 *
 * Consider the grid as a graph, where adjacent cells have an edge with cost of the difference between the cells.
 *    Hide Hint #2
 * If you are given threshold k, check if it is possible to go from (0, 0) to (n-1, m-1) using only edges of ≤ k cost.
 *    Hide Hint #3
 * Binary search the k value.
 */
public class PathWithMinimumEffortBruteForce {

    /**
     *Approach 1: Brute Force using Backtracking
     * Intuition
     *
     * The brute force approach would be to traverse all the possible paths from the source cell to the destination cell and track the path with minimum efforts. To try all possible paths, the first thing that comes in our mind is Backtracking. Backtracking incrementally builds the candidates for a solution using depth first search and discards the candidates (backtrack) if it doesn't satisfy the condition.
     *
     * The backtracking algorithms consists of the following steps,
     *
     * Choose: Choose the potential candidate. For any given cell A, we must choose the adjacent cells in all 4 directions (up, down, left, right) as a potential candidate.
     * Constraint: Define a constraint that must be satisfied by the chosen candidate. In this case, a chosen cell is valid if it is within the boundaries of the matrix and it is not visited before.
     * Goal: We must define the goal that determines if we have found the required solution and we must backtrack. Here, our goal is achieved once we have reached the destination cell. On reaching the destination cell, we must track the maximum absolute difference in that path and backtrack.
     * To make the algorithm more efficient, once we find any path from source to destination, we track the maximum absolute difference of all adjacent cells in that path in a variable \text{maxSoFar}maxSoFar. With this, we can avoid going into other paths in the future where effort is greater than or equal to \text{maxSoFar}maxSoFar.
     *
     * In other words, if we have already found a path to reach the destination cell with \text{maxSoFar}maxSoFar, then, we would only explore other paths if it takes efforts less than \text{maxSoFar}maxSoFar.
     *
     * Algorithm
     *
     * We must begin the Depth First Search traversal from source cell (x = 0 and y = 0). Using the intuition discussed above, we must explore all the potential paths using the following steps,
     *
     * For a given cell (x, y), explore the adjacent cells in all the 4 directions defined by directions and choose the one with minimum effort.
     * The maxDifference keeps track of the maximum absolute difference seen so far in the current path. On every move to the adjacent cell, we must update the maxDifference if it is lesser than the currentDifference (The absolute difference between current cell(x, y) and adjacent cell(adjacentX, adjacentY)).
     * We must backtrack from the depth first search traversal once we reach the destination cell (row-1) and (col-1) and return the maximum absolute difference of the current path.
     * Thus, for each cell, we recursively calculate the effort required to reach the destination cell from all the adjacent cells and find the minimum effort.
     *
     * Note
     *
     * It must be noted that we mark the current cell as visited by setting the height of the current cell (x,y) as 0. We must update the height back to the previous value once we backtrack from the current path. This is necessary because the cell must be visited again for other paths.
     *
     * Implementation
     *
     *
     * Complexity Analysis
     *
     * Let mm be the number of rows and nn be the number of columns in the matrix \text{heights}heights.
     *
     * Time Complexity : \mathcal{O}(3^{m \cdot n})O(3
     * m⋅n
     *  ). The total number of cells in the matrix is given by m \cdot nm⋅n. For the backtracking, there are at most 4 possible directions to explore, but further, the choices are reduced to 3 (since we won't go back to where we come from). Thus, considering 3 possibilities for every cell in the matrix the time complexity would be \mathcal{O}(3^{m \cdot n})O(3
     * m⋅n
     *  ).
     * The time complexity is exponential, hence this approach is exhaustive and results in Time Limit Exceeded (TLE).
     *
     * Space Complexity: \mathcal{O}(m \cdot n)O(m⋅n) This space will be used to store the recursion stack. As we recursively move to the adjacent cells, in the worst case there could be m \cdot nm⋅n cells in the recursive call stack.
     * @param heights
     * @return
     */

        public int minimumEffortPath(int[][] heights) {
            return backtrack(0, 0, heights, heights.length, heights[0].length, 0);
        }

        int directions[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    /**
     * keeps track of the global best minimum effort
     */
    int maxSoFar = Integer.MAX_VALUE;

        int backtrack(int x, int y, int[][] heights, int row, int col, int maxDifference) {
            if (x == row - 1 && y == col - 1) {
                maxSoFar = Math.min(maxSoFar, maxDifference);
                return maxDifference;
            }
            int currentHeight = heights[x][y];
            //sets the current cell to 0 to mark it as "visited"
            heights[x][y] = 0;
            int minEffort = Integer.MAX_VALUE;
            for (int i = 0; i < 4; i++) {
                int adjacentX = x + directions[i][0];
                int adjacentY = y + directions[i][1];
                //note heights[adjacentX][adjacentY] != 0 ensures that we dont visit previous cell that was marked as "visited"
                if (isValidCell(adjacentX, adjacentY, row, col) && heights[adjacentX][adjacentY] != 0) {
                    int currentDifference = Math.abs(heights[adjacentX][adjacentY] - currentHeight);
                    int maxCurrentDifference = Math.max(maxDifference, currentDifference);
                    if (maxCurrentDifference < maxSoFar) {
                        int result = backtrack(adjacentX, adjacentY, heights, row, col, maxCurrentDifference);
                        minEffort = Math.min(minEffort, result);
                    }
                }
            }
            heights[x][y] = currentHeight;
            return minEffort;
        }

        boolean isValidCell(int x, int y, int row, int col){
            return x >= 0 && x <= row - 1 && y >= 0 && y <= col - 1;
        }

}
