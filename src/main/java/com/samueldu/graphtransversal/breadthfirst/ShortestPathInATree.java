package com.samueldu.graphtransversal.breadthfirst;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.
 *
 * A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:
 *
 * All the visited cells of the path are 0.
 * All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
 * The length of a clear path is the number of visited cells of this path.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[0,1],[1,0]]
 * Output: 2
 * Example 2:
 *
 *
 * Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
 * Output: 4
 * Example 3:
 *
 * Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
 * Output: -1
 *
 *
 * Constraints:
 *
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 100
 * grid[i][j] is 0 or 1
 */
public class ShortestPathInATree {
/**
 * Overview
 * If an interviewer asks you this question in an interview, then their goal is probably to determine that:
 *
 * You can recognize that this is a typical shortest path problem that can be solved with a Breadth-first search (BFS).
 * You can correctly implement a BFS to solve it.
 * For bonus points, you know that the solution could be optimized using the A* algorithm.
 * For this article, I'm going to assume that you already know the fundamentals of BFS and are at the stage of figuring out how to apply it to a wide range of problems, such as this one. If you aren't yet at this stage, then I recommend checking out our relevant Explore Card content on BFS before coming back to this problem.
 *
 * We'll look at two BFS implementations in this article; one that overwrites the input and another that does not. We'll also take a look at how this problem could be solved using A*.
 *
 *
 * Approach 1: Breadth-first Search (BFS), Overwriting Input
 * Intuition
 *
 * This section is aimed at readers who aren't yet at a level that they can immediately recognize this as being a BFS problem. If you don't require this level of guidance, feel free to skip forward to the algorithm or code section.
 *
 * If you're faced with a problem like this and aren't sure where to go with it, then a good first step is to make an example and solve it on a whiteboard or paper. Here's the example we'll work through here.
 *
 * The example we'll be using.
 *
 * The next step is to think about whether there is a better way of visualizing the input. In this case, we're told 0's represent "open" cells, and 1's represent "blocked" cells. An intuitive way of visualizing this, therefore, is to color in the "blocked" cells.
 *
 * Using black and white cells to visualize the input.
 *
 * As long as you communicate clearly with your interviewer about what you're doing, making the input more "friendly" towards your eyes and brain can be one of the most effective problem-solving techniques when you're stuck. Most problems that involve grids of 0's and 1's become a lot easier when drawn like this.
 *
 * Now that our example is ready to go, have a go at finding the shortest distance to get from the top-left to the bottom-right cell.
 *
 * The shortest path through the above example.
 *
 * It also helps to look at more than one example. Here are a couple more for you to find the shortest distance for.
 *
 * Another example.
 *
 * And another example.
 *
 * For that last one, did you find both of the shortest paths? If not, have another look!
 *
 * If you did it right, these are the distances and paths you should have identified. Notice that the last one has two paths of the same length.
 *
 * Another example.
 *
 * And another example.
 *
 * Now that we've looked at a few examples let's try and generalize a bit and come up with an algorithm. Notice that from a given cell, there are up to 8 options to move out of that cell into another cell. For example:
 *
 * We can go in up to 8 different directions out of a cell.
 *
 * If we do this with every cell, we get the following.
 *
 * The input visualized as a graph.
 *
 * Notice that what we have discovered is that the grid is a graph; white cells are nodes, and lines between them are edges. This is a special type of graph we call a lattice graph. 2D arrays that are representing a graph come up a lot in interview questions. It is essential to be confident with them (don't worry too much about how we will implement this yet; we'll get to that in a bit).
 *
 * So, we have a graph, and we need to find the length of the shortest path from the top-left to the bottom-right cell. Recall that to find the shortest path in a graph, we should use Breadth-first Search (BFS).
 *
 * Finding the shortest path between two nodes in a graph is almost always done using BFS, and all programmers should know this. BFS is one of the fundamental algorithms that you are expected to be confident coding before a tech interview. So, if you're finding this question challenging, then you're doing the right thing by working on it now.
 *
 * Recall that BFS works by firstly identifying all of the nodes (white cells) that can be reached in 1 step from the top-left cell, then those in 2 steps, then 3 steps, etc., until it "finds" the target node (the bottom-right cell). Here is a simple animation that shows the general way that BFS could work for this problem (this is something that you might do on the whiteboard during your interview).
 *
 * Current
 * 17 / 17
 * Algorithm
 *
 * Now that we've determined that this is a BFS problem, we need to fill in a few more details and figure out how it will all go together. Recall that BFS is implemented using a queue.
 *
 * A queue is what we refer to as a First-In-First-Out (FIFO) data structure, comparable to people queuing to go on a theme park ride. People enter the queue at the back and leave from the front. BFS works by putting the start node on the queue, and then while the queue is non-empty, it takes a node off the front of the queue and puts that node's neighbors on the back of the queue. In this way, the graph is progressively explored, starting with the nodes nearest to the start node and ending with the nodes farthest away.
 *
 * We commonly refer to putting a node on the queue as enqueuing and taking a node off the queue as dequeuing. We'll use this terminology for the remainder of the article.
 *
 * Applying BFS to this problem, we'll use the queue to keep track of cells that we have numbered but haven't yet numbered the * neighbors* of. While usually for BFS, we'd need a "visited" set to avoid infinite looping around cycles, we won't need one for this approach because we're going to overwrite the input, and so only unvisited cells will have a 0 in them.
 *
 * Here's the pseudocode for setting up the BFS. We identify cells with a (row, col) pair. The top-left cell is at row = 0 and col = 0 so is identified with the pair (0, 0).
 *
 * queue = a new queue
 * enqueue cell (0, 0)
 * set grid[0][0] to 1
 * We enqueue the top-left cell as it's the first cell we'll be exploring. We also need to set its distance to be 1 in the grid (note that this will not cause confusion with the 1's that were used to represent blocked cells).
 *
 * Now that we've done the initialization, it's time to design the main BFS loop (again, this is fairly standard template stuff).
 *
 * While there are cells left on the queue, we should dequeue a cell, look up its distance (that has already been written into the input grid), and explore its neighbors. Exploring the cell's neighbors involves identifying all open cells adjacent to the current cell that still have a 0 in them. For each of these cells, we write the number distance + 1 into them. Finally, we need to enqueue the neighbor so that when we're ready, we can explore its neighbors too.
 *
 * Here is some pseudocode.
 *
 * while queue is not empty:
 *
 *     cell = dequeue a cell
 *     look up distance at grid[cell row][cell col]
 *
 *     for each open neighbour:
 *         if this neighbour is the bottom right cell (target):
 *             return distance + 1
 *         set grid[neighbour row][neighbour col] = distance + 1
 *         enqueue neighbour
 * return -1
 * A few points to note:
 *
 * We return -1 if the loop terminates without returning, as this means we ran out of cells to explore before reaching the bottom-right cell.
 *
 * The reason we can simply do distance = grid[cell row][cell col] is because cells are only enqueued once a number has been written into them.
 *
 * We should only write numbers into cells that currently have a 0 in them. If, for example, a cell already had a 2 in it and you then change that to a 4, it would no longer have the number that represents the shortest distance from the top left to itself.
 *
 * It would be okay to do the check for the bottom-right cell in the outer loop. We would need to return distance instead of distance + 1.
 *
 * The final thing we need to consider is how to get all the neighbors of a cell. In traditional graph representations, this would be the equivalent of examining all the edges of a given node. For grids, we identify each neighbor by its row and column offset from the given cell.
 *
 * Offsets of a cell's neighbors.
 *
 * The most common pattern is to put these "offsets" into a list as follows.
 *
 * directions = [(-1, -1), (-1, 0), (-1, 1), (0, -1), (0, 1), (1, -1), (1, 0), (1, 1)]
 * We can then iterate over this list and use each offset to calculate a neighbor row and column. We need to be careful, though; while most cells have 8 neighbors, corner cells only have 3 neighbors, and edges cells have 5 neighbors. To handle this, we start by checking that the neighbors row and column actually are within the dimensions of the grid. If they are within the grid, we also check that the cell currently contains a 0 (in other words, it hasn't yet been numbered and is open). If the cell contains a 0, then we add it to a list of all the neighbors to be returned.
 *
 * Here is the pseudocode that puts all of this together. This function is reusable for many grid problems (usually without the 4 diagonal directions). You should be very familiar with this algorithm and be able to implement it in your programming language of choice very quickly.
 *
 *
 * directions = [(-1, -1), (-1, 0), (-1, 1), (0, -1), (0, 1), (1, -1), (1, 0), (1, 1)]
 *
 * define function get_neighbors(row, col):
 * neighbors = a container to put the neighbors of (row, col) in
 *     for each (row_direction, col_direction) pair in directions:
 *         neighbor_row = row + row_direction
 *         neighbor_col = col + col_direction
 *         if (neighbor_row, neighbor_col) is NOT over the edge of the grid AND is 0:
 *             add (neighbor_row, neighbor_col) to neighbors
 *     return neighbors
 * Note that it is very important to check that the neighbor row and column are within the grid before checking the number in it. In most languages, getting this wrong will cause a crash. In Python, it will cause weird bugs due to Python's handling of negative indices.
 *
 *Complexity Analysis
 *
 * Let N be the number of cells in the grid.
 *
 * Time complexity : O(N)O(N).
 *
 * Each cell was guaranteed to be enqueued at most once. This is because a condition for a cell to be enqueued was that it had a zero in the grid, and when enqueuing, we also permanently changed the cell's grid value to be non-zero.
 *
 * The outer loop ran as long as there were still cells in the queue, dequeuing one each time. Therefore, it ran at most NN times, giving a time complexity of O(N)O(N).
 *
 * The inner loop iterated over the unvisited neighbors of the cell that was dequeued by the outer loop. There were at most 88 neighbors. Identifying the unvisited neighbors is an O(1)O(1) operation because we treat the 88 as a constant.
 *
 * Therefore, we have a time complexity of O(N)O(N).
 *
 * Space complexity : O(N)O(N).
 *
 * The only additional space we used was the queue. We determined above that at most, we enqueued NN cells. Therefore, an upper bound on the worst-case space complexity is O(N)O(N).
 *
 * Given that BFS will have nodes of at most two unique distances on the queue at any one time, it would be reasonable to wonder if the worst-case space complexity is actually lower. But actually, it turns out that there are cases with massive grids where the number of cells at a single distance is proportional to NN. So even with cells of a single distance on the queue, in the worst case, the space needed is O(N)O(N).
 */


    private static final int[][] directions =
            new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    public int shortestPathBinaryMatrix(int[][] grid) {

        // Firstly, we need to check that the start and target cells are open.
        if (grid[0][0] != 0 || grid[grid.length - 1][grid[0].length - 1] != 0) {
            return -1;
        }

        // Set up the BFS.
        Queue<int[]> queue = new ArrayDeque<>();
        grid[0][0] = 1;
        queue.add(new int[]{0, 0});

        // Carry out the BFS
        while (!queue.isEmpty()) {
            int[] cell = queue.remove();
            int row = cell[0];
            int col = cell[1];
            int distance = grid[row][col];
            if (row == grid.length - 1 && col == grid[0].length - 1) {
                return distance;
            }
            for (int[] neighbour : getNeighbours(row, col, grid)) {
                int neighbourRow = neighbour[0];
                int neighbourCol = neighbour[1];
                queue.add(new int[]{neighbourRow, neighbourCol});
                grid[neighbourRow][neighbourCol] = distance + 1;
            }
        }

        // The target was unreachable.
        return -1;
    }

    private List<int[]> getNeighbours(int row, int col, int[][] grid) {
        List<int[]> neighbours = new ArrayList<>();
        for (int i = 0; i < directions.length; i++) {
            int newRow = row + directions[i][0];
            int newCol = col + directions[i][1];
            if (newRow < 0 || newCol < 0 || newRow >= grid.length
                    || newCol >= grid[0].length
                    || grid[newRow][newCol] != 0) {
                continue;
            }
            neighbours.add(new int[]{newRow, newCol});
        }
        return neighbours;
    }


    /**
     *
     */

}
