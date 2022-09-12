package com.samueldu.graphtransversal.singlesourceshortestpath;

import java.util.Arrays;
import java.util.PriorityQueue;


/**
 *
 */
public class PathWithminimumEffortDijkstra {

    /**
     * Approach 2: Variations of Dijkstra's Algorithm
     * Intuition
     *
     * The previous approach is exhaustive as it traverses all the paths. If we observe, the problem is similar to finding the shortest path from a source cell to a destination cell. Here, the shortest path is the one with minimum absolute difference between every adjacent cells in that path. Also, since there is height associated with each cell, simple BFS traversal won't be sufficient.
     *
     * The absolute difference between adjacent cells A and B can be perceived as the weight of an edge from cell A to cell B. Thus, we could use Dijkstra's Algorithm which is used to find the shortest path in a weighted graph with a slight modification of criteria for the shortest path.
     *
     * Let's look at the algorithm in detail.
     *
     * Algorithm
     *
     * We use a differenceMatrix of size \text{row} \cdot \text{col}row⋅col where each cell represents the minimum effort required to reach that cell from all the possible paths. Also, initialize we all the cells in the differenceMatrix to infinity \text{(MAX\_INT)}(MAX_INT) since none of the cells are reachable initially.
     *
     * As we start visiting each cell, all the adjacent cells are now reachable. We update the absolute difference between the current cell and adjacent cells in the differenceMatrix. At the same time, we also push all the adjacent cells in a priority queue. The priority queue holds all the reachable cells sorted by its value in differenceMatrix, i.e the cell with minimum absolute difference with its adjacent cells would be at the top of the queue.
     *
     * We begin by adding the source cell (x=0, y=0) in the queue. Now, until we have visited the destination cell or the queue is not empty, we visit each cell in the queue sorted in the order of priority. The less is the difference value(absolute difference with adjacent cell) of a cell, the higher is its priority.
     *
     * Get the cell from the top of the queue curr and visit the current cell.
     *
     * For each of the 4 cells adjacent to the current cell, calculate the maxDifference which is the maximum absolute difference to reach the adjacent cell (adjacentX, adjacentY) from current cell (curr.x, curr.y).
     *
     * If the current value of the adjacent cell (adjacentX, adjacentY) in the difference matrix is greater than the maxDifference, we must update that value with maxDifference. In other words, we have found that the path from the current cell to the adjacent cell takes lesser efforts than the other paths that have reached the adjacent cell so far. Also, we must add this updated difference value in the queue.
     *
     * Ideally, for updating the priority queue, we must delete the old value and reinsert with the new maxDifference value. But, as we know that the updated maximum value is always lesser than the old value and would be popped from the queue and visited before the old value, we could save time and avoid removing the old value from the queue.
     *
     * At the end, the value at differenceMatrix[row - 1][col - 1] is the minimum effort required to reach the destination cell (row-1,col-1).
     *
     * Complexity Analysis
     *
     * Time Complexity : \mathcal{O}(m \cdot n \log (m \cdot n))O(m⋅nlog(m⋅n)), where mm is the number of rows and nn is the number of columns in matrix \text{heights}heights. It will take \mathcal{O}(m \cdot n)O(m⋅n) time to visit every cell in the matrix. The priority queue will contain at most m \cdot nm⋅n cells, so it will take \mathcal{O}(\log (m \cdot n))O(log(m⋅n)) time to re-sort the queue after every adjacent cell is added to the queue. This given as total time complexiy as \mathcal{O}(m \cdot n \log(m \cdot n))O(m⋅nlog(m⋅n)).
     *
     * Space Complexity: \mathcal{O}(m \cdot n)O(m⋅n), where mm is the number of rows and nn is the number of columns in matrix \text{heights}heights. The maximum queue size is equal to the total number of cells in the matrix \text{height}height which is given by m \cdot nm⋅n. Also, we use a difference matrix of size m \cdot nm⋅n. This gives as time complexity as \mathcal{O}(m \cdot n + m \cdot n)O(m⋅n+m⋅n) = \mathcal{O}(m \cdot n)O(m⋅n)
     */


        int directions[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        public int minimumEffortPath(int[][] heights) {
            int row = heights.length;
            int col = heights[0].length;
            int[][] differenceMatrix = new int[row][col];
            for (int[] eachRow : differenceMatrix)
                Arrays.fill(eachRow, Integer.MAX_VALUE);
            differenceMatrix[0][0] = 0;
            PriorityQueue<Cell> queue = new PriorityQueue<>((a, b) -> (a.difference.compareTo(b.difference)));
            boolean[][] visited = new boolean[row][col];
            queue.add(new Cell(0, 0, differenceMatrix[0][0]));

            while (!queue.isEmpty()) {
                Cell curr = queue.poll();
                visited[curr.x][curr.y] = true;
                if (curr.x == row - 1 && curr.y == col - 1)
                    return curr.difference;
                for (int[] direction : directions) {
                    int adjacentX = curr.x + direction[0];
                    int adjacentY = curr.y + direction[1];
                    if (isValidCell(adjacentX, adjacentY, row, col) && !visited[adjacentX][adjacentY]) {
                        int currentDifference = Math.abs(heights[adjacentX][adjacentY] - heights[curr.x][curr.y]);
                        int maxDifference = Math.max(currentDifference, differenceMatrix[curr.x][curr.y]);
                        if (differenceMatrix[adjacentX][adjacentY] > maxDifference) {
                            differenceMatrix[adjacentX][adjacentY] = maxDifference;
                            queue.add(new Cell(adjacentX, adjacentY, maxDifference));
                        }
                    }
                }
            }
            return differenceMatrix[row - 1][col - 1];
        }

        boolean isValidCell(int x, int y, int row, int col) {
            return x >= 0 && x <= row - 1 && y >= 0 && y <= col - 1;
        }


    public class Cell {
        int x;
        int y;
        Integer difference;

        Cell(int x, int y, Integer difference) {
            this.x = x;
            this.y = y;
            this.difference = difference;
        }
    }


}
