package com.samueldu.soduku;


public class TwentyFourSeven_FourInOne {

    public static void main(String [] args){

        tryNext(0);
        System.out.println("GRID:");
        SudokuBoard.printGrid();
        System.out.println("RESULTS:");
        SudokuBoard.printResults();

    }



    private static boolean tryNext(int k){
       //if( k > SudokuBoard.grid[0].length * SudokuBoard.grid.length-1)
        //   return true;

        int rowNumber = k/SudokuBoard.grid[0].length;
        int columnNUmber = k%SudokuBoard.grid[0].length;

        if(SudokuBoard.grid[rowNumber][columnNUmber]!=null){
            SudokuBoard.result[rowNumber][columnNUmber]=SudokuBoard.grid[rowNumber][columnNUmber];
            SudokuBoard.state[rowNumber][columnNUmber]=1;
            return tryNext(k+1);
        }else {
            SudokuBoard.state[rowNumber][columnNUmber] = -1;
            boolean hasSuccess=false;
            for (int i = 0; i <=7; i++) {
                SudokuBoard.result[rowNumber][columnNUmber] = i;
                boolean preValid =SudokuBoardPreValidator.check(k);
                if(!preValid){
                    SudokuBoard.result[rowNumber][columnNUmber]=0;
                    continue;
                }

                if (k < SudokuBoard.grid[0].length * SudokuBoard.grid.length-1)
                     if(tryNext(k + 1)) {
                         hasSuccess = true;
                     }
            }

            if(hasSuccess){
                SudokuBoard.state[rowNumber][columnNUmber] = 1;
                return true;
            }else {
                SudokuBoard.state[rowNumber][columnNUmber] = 0;
                return false;
            }


        }

    }
}
