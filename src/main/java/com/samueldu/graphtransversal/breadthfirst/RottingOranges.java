package com.samueldu.graphtransversal.breadthfirst;

import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 *You are given an m x n grid where each cell can have one of three values:
 *
 * 0 representing an empty cell,
 * 1 representing a fresh orange, or
 * 2 representing a rotten orange.
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 *
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 * Example 2:
 *
 * Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
 * Output: -1
 * Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
 * Example 3:
 *
 * Input: grid = [[0,2]]
 * Output: 0
 * Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 10
 * grid[i][j] is 0, 1, or 2.
 *
 *
 */
public class RottingOranges {
/**
 * Approach 1: Breadth-First Search (BFS)
 * Intuition
 *
 * This is yet another 2D traversal problem. As we know, the common algorithmic strategies to deal with these problems would be Breadth-First Search (BFS) and Depth-First Search (DFS).
 *
 * As suggested by its name, the BFS strategy prioritizes the breadth over depth, i.e. it goes wider before it goes deeper. On the other hand, the DFS strategy prioritizes the depth over breadth.
 *
 * The choice of strategy depends on the nature of the problem. Though sometimes, they are both applicable for the same problem. In addition to 2D grids, these two algorithms are often applied to problems associated with tree or graph data structures as well.
 *
 * In this problem, one can see that BFS would be a better fit.
 *
 * Because the process of rotting could be explained perfectly with the BFS procedure, i.e. the rotten oranges will contaminate their neighbors first, before the contamination propagates to other fresh oranges that are farther away.
 *
 * If one is not familiar with the algorithm of BFS, one can refer to our Explore card of Queue & Stack which covers this subject.
 *
 * However, it would be more intuitive to visualize the rotting process with a graph data structure, where each node represents a cell and the edge between two nodes indicates that the given two cells are adjacent to each other.
 *
 * Grid to Graph
 *
 * In the above graph (pun intended), as we can see, starting from the top rotten orange, the contamination would propagate layer by layer (or level by level), until it reaches the farthest fresh oranges. The number of minutes that are elapsed would be equivalent to the number of levels in the graph that we traverse during the propagation.
 *
 * Current
 * 5 / 5
 * Algorithm
 *
 * One of the most distinguished code patterns in BFS algorithms is that often we use a queue data structure to keep track of the candidates that we need to visit during the process.
 *
 * The main algorithm is built around a loop iterating through the queue. At each iteration, we pop out an element from the head of the queue. Then we do some particular process with the popped element. More importantly, we then append neighbors of the popped element into the queue, to keep the BFS process running.
 *
 * Here are some sample implementations.
 *
 * In the above implementations, we applied some tricks to further optimize both the time and space complexities.
 *
 * Usually in BFS algorithms, we keep a visited table which records the visited candidates. The visited table helps us to avoid repetitive visits.
 *
 * But as one notices, rather than using the visited table, we reuse the input grid to keep track of our visits, i.e. we were altering the status of the input grid in-place.
 *
 * This in-place technique reduces the memory consumption of our algorithm. Also, it has a constant time complexity to check the current status (i.e. array access, grid[row][col]), rather than referring to the visited table which might be of constant time complexity as well (e.g. hash table) but in reality could be slower than array access.
 *
 * We use a delimiter (i.e. (row=-1, col=-1)) in the queue to separate cells on different levels. In this way, we only need one queue for the iteration. As an alternative, one can create a queue for each level and alternate between the queues, though technically the initialization and the assignment of each queue could consume some extra time.
 *
 * Complexity
 *
 * Time Complexity: \mathcal{O}(N)O(N), where NN is the size of the grid.
 *
 * First, we scan the grid to find the initial values for the queue, which would take \mathcal{O}(N)O(N) time.
 *
 * Then we run the BFS process on the queue, which in the worst case would enumerate all the cells in the grid once and only once. Therefore, it takes \mathcal{O}(N)O(N) time.
 *
 * Thus combining the above two steps, the overall time complexity would be \mathcal{O}(N) + \mathcal{O}(N) = \mathcal{O}(N)O(N)+O(N)=O(N)
 *
 * Space Complexity: \mathcal{O}(N)O(N), where NN is the size of the grid.
 *
 * In the worst case, the grid is filled with rotten oranges. As a result, the queue would be initialized with all the cells in the grid.
 *
 * By the way, normally for BFS, the main space complexity lies in the process rather than the initialization. For instance, for a BFS traversal in a tree, at any given moment, the queue would hold no more than 2 levels of tree nodes. Therefore, the space complexity of BFS traversal in a tree would depend on the width of the input tree.
 */

    public int orangesRotting(int[][] grid) {
        Queue<Pair<Integer, Integer>> queue = new ArrayDeque();

        // Step 1). build the initial set of rotten oranges
        int freshOranges = 0;
        int ROWS = grid.length, COLS = grid[0].length;

        for (int r = 0; r < ROWS; ++r)
            for (int c = 0; c < COLS; ++c)
                if (grid[r][c] == 2)
                    queue.offer(new Pair(r, c));
                else if (grid[r][c] == 1)
                    freshOranges++;

        // Mark the round / level, _i.e_ the ticker of timestamp
        queue.offer(new Pair(-1, -1));

        // Step 2). start the rotting process via BFS
        int minutesElapsed = -1;
        int[][] directions = { {-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        while (!queue.isEmpty()) {
            Pair<Integer, Integer> p = queue.poll();
            int row = p.getKey();
            int col = p.getValue();
            if (row == -1) {
                // We finish one round of processing
                minutesElapsed++;
                // to avoid the endless loop
                if (!queue.isEmpty())
                    queue.offer(new Pair(-1, -1));
            } else {
                // this is a rotten orange
                // then it would contaminate its neighbors
                for (int[] d : directions) {
                    int neighborRow = row + d[0];
                    int neighborCol = col + d[1];
                    if (neighborRow >= 0 && neighborRow < ROWS &&
                            neighborCol >= 0 && neighborCol < COLS) {
                        if (grid[neighborRow][neighborCol] == 1) {
                            // this orange would be contaminated
                            grid[neighborRow][neighborCol] = 2;
                            freshOranges--;
                            // this orange would then contaminate other oranges
                            queue.offer(new Pair(neighborRow, neighborCol));
                        }
                    }
                }
            }
        }

        // return elapsed minutes if no fresh orange left
        return freshOranges == 0 ? minutesElapsed : -1;
    }

    /**
     * Approach 2: In-place BFS
     * Intuition
     *
     * Although there is no doubt that the best strategy for this problem is BFS, some users in the Discussion forum have proposed different implementations of BFS with constant space complexity \mathcal{O}(1)O(1). To name just a few, one can see the posts from @manky and @votrubac.
     *
     * As one might recall from the previous BFS implementation, its space complexity is mainly due to the queue that we were using to keep the order for the visits of cells. In order to achieve \mathcal{O}(1)O(1) space complexity, we then need to eliminate the queue in the BFS.
     *
     * The secret in doing BFS traversal without a queue lies in the technique called in-place algorithm, which transforms input to solve the problem without using auxiliary data structure.
     *
     * Actually, we have already had a taste of in-place algorithm in the previous implementation of BFS, where we directly modified the input grid to mark the oranges that turn rotten, rather than using an additional visited table.
     *
     * How about we apply the in-place algorithm again, but this time for the role of the queue variable in our previous BFS implementation?
     *
     * The idea is that at each round of the BFS, we mark the cells to be visited in the input grid with a specific timestamp.
     *
     * By round, we mean a snapshot in time where a group of oranges turns rotten.
     *
     * Algorithm
     *
     * Grid Snapshot I
     *
     * Grid Snapshot II
     *
     * In the above graph, we show how we manipulate the values in the input grid in-place in order to run the BFS traversal.
     *
     * 1). Starting from the beginning (with timestamp=2), the cells that are marked with the value 2 contain rotten oranges. From this moment on, we adopt a rule stating as "the cells that have the value of the current timestamp (i.e. 2) should be visited at this round of BFS.".
     *
     * 2). For each of the cell that is marked with the current timestamp, we then go on to mark its neighbor cells that hold a fresh orange with the next timestamp (i.e. timestamp += 1). This in-place modification serves the same purpose as the queue variable in the previous BFS implementation, which is to select the candidates to visit for the next round.
     *
     * 3). At this moment, we should have timestamp=3, and meanwhile we also have the cells to be visited at this round marked out. We then repeat the above step (2) until there is no more new candidates generated in the step (2) (i.e. the end of BFS traversal).
     *
     * To summarize, the above algorithm is still a BFS traversal in a 2D grid. But rather than using a queue data structure to keep track of the visiting order, we applied an in-place algorithm to serve the same purpose as a queue in a more classic BFS implementation.
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: \mathcal{O}(N^2)O(N
     * 2
     *  ) where NN is the size of the input grid.
     *
     * In the in-place BFS traversal, for each round of BFS, we would have to iterate through the entire grid.
     *
     * The contamination propagates in 4 different directions. If the orange is well adjacent to each other, the chain of propagation would continue until all the oranges turn rotten.
     *
     * In the worst case, the rotten and the fresh oranges might be arranged in a way that we would have to run the BFS loop over and over again, which could amount to \frac{N}{2}
     * 2
     * N
     * ​
     *   times which is the longest propagation chain that we might have, i.e. the zigzag walk in a 2D grid as shown in the following graph.
     */

        // run the rotting process, by marking the rotten oranges with the timestamp
        public boolean runRottingProcess(int timestamp, int[][] grid, int ROWS, int COLS) {
            int[][] directions = { {-1, 0}, {0, 1}, {1, 0}, {0, -1}};
            // flag to indicate if the rotting process should be continued
            boolean toBeContinued = false;
            for (int row = 0; row < ROWS; ++row)
                for (int col = 0; col < COLS; ++col)
                    if (grid[row][col] == timestamp)
                        // current contaminated cell
                        for (int[] d : directions) {
                            int nRow = row + d[0], nCol = col + d[1];
                            if (nRow >= 0 && nRow < ROWS && nCol >= 0 && nCol < COLS)
                                if (grid[nRow][nCol] == 1) {
                                    // this fresh orange would be contaminated next
                                    grid[nRow][nCol] = timestamp + 1;
                                    toBeContinued = true;
                                }
                        }
            return toBeContinued;
        }

        public int orangesRottingInPlaceBFS(int[][] grid) {
            int ROWS = grid.length, COLS = grid[0].length;
            int timestamp = 2;
            while (runRottingProcess(timestamp, grid, ROWS, COLS))
                timestamp++;

            // end of process, to check if there are still fresh oranges left
            for (int[] row : grid)
                for (int cell : row)
                    // still got a fresh orange left
                    if (cell == 1)
                        return -1;


            // return elapsed minutes if no fresh orange left
            return timestamp - 2;
        }


}
