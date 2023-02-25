package com.samueldu.matrix.dynamicproramming;

public class UniquePathsTopDown {

        private int[][] memo;

        private int dp(int row, int col) {
            if (row + col == 0) {
                return 1; // Base case
            }

            int ways = 0;
            if (memo[row][col] == 0) {
                if (row > 0) {
                    ways += dp(row - 1, col);
                }
                if (col > 0) {
                    ways += dp(row, col - 1);
                }

                memo[row][col] = ways;
            }

            return memo[row][col];
        }

        public int uniquePaths(int m, int n) {
            memo = new int[m][n];
            return dp(m - 1, n- 1);
        }

}
