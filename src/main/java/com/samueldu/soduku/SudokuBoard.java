package com.samueldu.soduku;

public class SudokuBoard {
    //top row and bottom row
    public static final Integer [] []horizontal=new Integer[][]{
            {6, 36, 30, 34, 27, 3, 40, 27, null, null, 7, null},
            {6, null, null, 4, null, null, null, null, null, null, null, 5}
    };
    public static final Integer [][] vertical = new Integer[][]{
            {5, 7, 7,7, 33, 29, 2, 40, 28, null, null, 36, null},
            {4, null, null, 1, null, null, null, null, null, null,null, 7}
    };

    /** original from question
     *public static final Integer [][] grid = new Integer[][]{
            {null, null, null,null, null, null,null, null, null,null, null, null},
            {null, null, null,1, null, null,null, null, null,6, 5, null},
            {null, null, null,null, null, 3,null, null, null,null, 6, null},
            {null, 4, null,null, null, null,null, null, 7,null, null, null},
            {null, null, null,null, 2, null,null, null, null,null, null, 7},
            {null, null, 6,null, null, null,null, null, 3,7, null, null},
            {null, null, null,null, null, null,null, null, null,null, null, null},
            {null, null, null,null, null, null,null, null, null,null, null, null},
            {null, null, null,7, null, 5,null, null, null,null, null, null},
            {null, 5, null,null, null, 7,null, null, null,null, null, null},
            {null, 6, 7,null, null, null,null, null, null,null, null, null},
            {null, null, null,null, 6, null,null, null, null,null, null, null}
    };
     */

    public static final Integer [][] grid = new Integer[][]{
            {0, null, null,null, null, null,null, null, null,null, 7, 4},
            {0, null, null,1, null, null,null, null, null,6, 5, null},
            {0, null, null,null, null, 3,null, null, null,null, 6, null},
            {null, 4, null,null, null, null,null, null, 7,null, null, null},
            {null, null, null,null, 2, null,null, null, null,null, null, 7},
            {null, null, 6,null, null, null,0, null, 3,7, null, null},
            {null, null, null,null, null, 0,0, null, null,null, null, null},
            {null, null, null,null, null, null,null, null, null,null, null, null},
            {null, null, null,7, null, 5,null, null, null,null, null, null},
            {null, 5, null,null, null, 7,null, null, null,null, null, null},
            {null, 6, 7,null, null, null,null, null, null,null, null, null},
            {null, null, null,null, 6, null,null, null, null,null, null, null}
    };

    /**
     * 0 through 7
     * 0: Nan
     */
    public static Integer[][] result = new Integer[grid.length][grid[0].length];
    static{
        for (int i=0; i<grid.length;i++){
            for (int j=0; j<grid[0].length; j++){
                result[i][j]=grid[i][j];
            }
        }
    }

    /**
     * state: 0: not checked yet
     *         1: success
     *         0: untouched or invalid.
     *         -1: checking
     */
    public static int[][] state = new int[result.length][result[0].length];

    public static void printResults() {

        for (Integer [] r:result){
            for (Integer i:r){
                System.out.print(i+",");
            }
            System.out.println();
        }
    }
    public static void printGrid() {

        for (Integer [] r:grid){
            for (Integer i:r){
                System.out.print(i+",");
            }
            System.out.println();
        }
    }
}
