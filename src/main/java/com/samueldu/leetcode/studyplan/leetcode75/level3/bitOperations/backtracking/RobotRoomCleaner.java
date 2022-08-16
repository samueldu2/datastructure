package com.samueldu.leetcode.studyplan.leetcode75.level3.bitOperations.backtracking;

import java.util.HashSet;
import java.util.Set;

import javafx.util.*;

/**
 * Approach 1: Spiral Backtracking
 * Concepts to use
 *
 * Let's use here two programming concepts.
 *
 * The first one is called constrained programming.
 *
 * That basically means to put restrictions after each robot move. Robot moves, and the cell is marked as visited. That propagates constraints and helps to reduce the number of combinations to consider.
 *
 * bla
 *
 * The second one called backtracking.
 *
 * Let's imagine that after several moves the robot is surrounded by the visited cells. But several steps before there was a cell which proposed an alternative path to go. That path wasn't used and hence the room is not yet cleaned up. What to do? To backtrack. That means to come back to that cell, and to explore the alternative path.
 *
 * bla
 *
 * Intuition
 *
 * This solution is based on the same idea as maze solving algorithm called right-hand rule. Go forward, cleaning and marking all the cells on the way as visited. At the obstacle turn right, again go forward, etc. Always turn right at the obstacles and then go forward. Consider already visited cells as virtual obstacles.
 *
 * What to do if after the right turn there is an obstacle just in front ?
 *
 * Turn right again.
 *
 * How to explore the alternative paths from the cell ?
 *
 * Go back to that cell and then turn right from your last explored direction.
 *
 * When to stop ?
 *
 * Stop when you explored all possible paths, i.e. all 4 directions (up, right, down, and left) for each visited cell.
 *
 * Algorithm
 *
 * Time to write down the algorithm for the backtrack function backtrack(cell = (0, 0), direction = 0).
 *
 * Mark the cell as visited and clean it up.
 *
 * Explore 4 directions : up, right, down, and left (the order is important since the idea is always to turn right) :
 *
 * Check the next cell in the chosen direction :
 *
 * If it's not visited yet and there is no obtacles :
 *
 * Move forward.
 *
 * Explore next cells backtrack(new_cell, new_direction).
 *
 * Backtrack, i.e. go back to the previous cell.
 *
 * Turn right because now there is an obstacle (or a virtual obstacle) just in front.
 *
 * Complexity Analysis
 *
 * Time complexity : O(N - M)O(N−M), where NN is a number of cells in the room and MM is a number of obstacles.
 *
 * We visit each non-obstacle cell once and only once.
 * At each visit, we will check 4 directions around the cell. Therefore, the total number of operations would be 4 \cdot (N-M)4⋅(N−M).
 * Space complexity : O(N - M)O(N−M), where NN is a number of cells in the room and MM is a number of obstacles.
 *
 * We employed a hashtable to keep track of whether a non-obstacle cell is visited or not.
 */
public class RobotRoomCleaner {
        // going clockwise : 0: 'up', 1: 'right', 2: 'down', 3: 'left'
    /**
     * Robot interface has the same directional order,
     * when robot.move() is called, the robot moves to the next available space
     * in the same ordering of the directions.
      */
    int[][] directions = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
        Set<Pair<Integer, Integer>> visited = new HashSet();
        Robot robot;

        public void goBack() {
            robot.turnRight(); //just turn direction facing, not change of cell.
            robot.turnRight();
            robot.move();//turns 180 degrees and move  1 cell.
            robot.turnRight();
            robot.turnRight();//now moved back one cell, but still facing the same direction as at the start.
        }

    /**
     * Complexity Analysis
     *
     * Time complexity : O(N - M)O(N−M), where NN is a number of cells in the room and MM is a number of obstacles.
     *
     * We visit each non-obstacle cell once and only once.
     * At each visit, we will check 4 directions around the cell. Therefore, the total number of operations would be 4 \cdot (N-M)4⋅(N−M).
     * Space complexity : O(N - M)O(N−M), where NN is a number of cells in the room and MM is a number of obstacles.
     *
     * We employed a hashtable to keep track of whether a non-obstacle cell is visited or not.
     * @param row
     * @param col
     * @param d
     */
        public void backtrack(int row, int col, int d) {
            visited.add(new Pair(row, col));
            robot.clean();
            // going clockwise : 0: 'up', 1: 'right', 2: 'down', 3: 'left'
            // note robot uses the same ordering for directions.
            for (int i = 0; i < 4; ++i) {
                int newD = (d + i) % 4;
                int newRow = row + directions[newD][0];
                int newCol = col + directions[newD][1];

                if (!visited.contains(new Pair(newRow, newCol)) && robot.move()) {
                    //check new the newly moved in cell.
                    backtrack(newRow, newCol, newD);
                    /**
                     * robots got in this bloack of code only after it moved one cell. always go back the where we started before checking the next direction.
                     */
                    goBack();
                }
                // turn the robot following chosen direction : clockwise
                // we end up facing the same direction after the 4th turn, and end up exactly the same as we started.
                // 4 of this call + the goBack() call earlier ensures the robot get back to the same cell and faces the same direction as at the start.
                robot.turnRight();

            }
        }

        public void cleanRoom(Robot robot) {
            this.robot = robot;
            backtrack(0, 0, 0);
        }

}


interface Robot {
     // Returns true if the cell in front is open and robot moves into the cell.
              // Returns false if the cell in front is blocked and robot stays in the current cell.
             public boolean move();

             // Robot will stay in the same cell after calling turnLeft/turnRight.
    // Each turn will be 90 degrees.
              public void turnLeft();
      public void turnRight();

    /**
     * Robot will report when the last run is cleaned.
     */
              // Clean the current cell.
            public void clean();
 }