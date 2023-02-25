package com.samueldu.soduku;

public class SudokuBoardValidator {
    public static boolean check(){
        return check7by7 ()&&checkSideNumbers();
    }

    private static boolean checkSideNumbers() {
        return checkSumOfRowOrColumnAndValueOfFirstNumber();
    }

    private static boolean checkSumOfRowOrColumnAndValueOfFirstNumber() {
        return checkSumOfRowOrColumnAndValueOfFirstNumberTop() &&
                checkSumOfRowOrColumnAndValueOfFirstNumberBottom() &&
                checkSumOfRowOrColumnAndValueOfFirstNumberLeft() &&
                checkSumOfRowOrColumnAndValueOfFirstNumberRight();
    }

    private static boolean checkSumOfRowOrColumnAndValueOfFirstNumberRight() {
        for (int i=0; i<SudokuBoard.vertical[1].length; i++){
            int sum=0;
            boolean seenFirstValue=false;
            for (int j=SudokuBoard.grid[0].length;j>0; j--){
                if(SudokuBoard.result[i][j]!=null){
                    if(!seenFirstValue && SudokuBoard.result[i][j]==SudokuBoard.vertical[1][j])
                        return true;
                    seenFirstValue=true;
                    sum+=SudokuBoard.result[i][j];
                }
            }
            if(sum==SudokuBoard.vertical[1][i]) return true;
        }
        return false;
    }
    private static boolean checkSumOfRowOrColumnAndValueOfFirstNumberLeft() {
        for (int i=0; i<SudokuBoard.vertical[0].length; i++){
            int sum=0;
            boolean seenFirstValue=false;
            for (int j=0;j<SudokuBoard.grid[0].length; j++){
                if(SudokuBoard.result[i][j]!=null){
                    if(!seenFirstValue && SudokuBoard.result[i][j]==SudokuBoard.vertical[0][j])
                        return true;
                    seenFirstValue=true;
                    sum+=SudokuBoard.result[i][j];
                }
            }
            if(sum==SudokuBoard.vertical[0][i]) return true;
        }
        return false;
    }

    private static boolean checkSumOfRowOrColumnAndValueOfFirstNumberBottom() {
        for (int i=0; i<SudokuBoard.horizontal[1].length; i++){
            int sum=0;
            boolean seenFirstValue=false;
            for (int j=SudokuBoard.grid.length; j>0;j--){
                if(SudokuBoard.result[j][i]!=null){
                    if(!seenFirstValue && SudokuBoard.result[j][i]==SudokuBoard.horizontal[1][i])
                        return true;
                    seenFirstValue=true;
                    sum+=SudokuBoard.result[j][i];
                }
            }
            if(sum==SudokuBoard.horizontal[1][i]) return true;
        }
        return false;
    }

    private static boolean checkSumOfRowOrColumnAndValueOfFirstNumberTop(){
        for (int i=0; i<SudokuBoard.horizontal[0].length; i++){
            int sum=0;
            boolean seenFirstValue=false;
            for (int j=0; j<SudokuBoard.grid.length;j++){
                if(SudokuBoard.result[j][i]!=null){
                    if(!seenFirstValue && SudokuBoard.result[j][i]==SudokuBoard.horizontal[0][i])
                        return true;
                    seenFirstValue=true;
                    sum+=SudokuBoard.result[j][i];
                }
            }
            if(sum==SudokuBoard.horizontal[0][i]) return true;
        }
        return false;
    }


    private static boolean check2by2ContainsOneOrMoreEmptyCellInEach7by7() {
        return check2by2ContainsOneOrMoreEmptyCellInEach7by7(0, 6, 0,6) &&
                check2by2ContainsOneOrMoreEmptyCellInEach7by7(5, 11, 0, 6) &&
                check2by2ContainsOneOrMoreEmptyCellInEach7by7(0, 6, 5, 11) &&
                check2by2ContainsOneOrMoreEmptyCellInEach7by7( 5, 11, 5, 11);
    }
    private static boolean check2by2ContainsOneOrMoreEmptyCellInEach7by7(int rowStart, int rowEnd, int columnStart, int columnEnd) {

        for (int i=rowStart; i<rowEnd-1;i++){
            for (int j=columnStart; j<columnEnd-1; j++){
                if(!check2by2(i, i+1,j, j+1))
                    return false;
            }
        }
        return true;
    }

    private static boolean check2by2(int rowStart, int rowEnd, int columnStart, int columnEnd) {
        for (int i=rowStart; i<rowEnd; i++ ){
            for (int j=columnStart; j<columnEnd; j++){
                if(SudokuBoard.result[i][j]==0 || SudokuBoard.result[i][j]==null) return true;
            }
        }
        return false;
    }


    public static boolean check7by7(){
       return  check1through7() && checkColumnRow4SumTo20InEach7by7()&& checkNumberCellsAreConnectedInEach7by7() && check2by2ContainsOneOrMoreEmptyCellInEach7by7();
    }
    
    public static boolean check1through7(){
        return check1through7(0, 6, 0,6) && check1through7(5, 11, 0, 6) && check1through7(0, 6, 5, 11) && check1through7( 5, 11, 5, 11);
    }

    public static boolean check1through7(int rowStart, int rowEnd, int columnStart, int columnEnd){
        int oneCount=0;
        int twoCount=0;
        int threeCount=0;
        int fourCount=0;
        int fiveCount=0;
        int sixCount=0;
        int sevenCount=0;
        for (int i=rowStart; i<rowEnd; i++){
            for (int j=columnStart; i<columnEnd; i++){
                if(SudokuBoard.result[i][j]!=null) {
                    if (SudokuBoard.result[i][j] == 1) oneCount++;
                    if (SudokuBoard.result[i][j] == 2) twoCount++;
                    if (SudokuBoard.result[i][j] == 3) threeCount++;
                    if (SudokuBoard.result[i][j] == 4) fourCount++;
                    if (SudokuBoard.result[i][j] == 5) fiveCount++;
                    if (SudokuBoard.result[i][j] == 6) sixCount++;
                    if (SudokuBoard.result[i][j] == 7) sevenCount++;
                }
            }
        }
        return oneCount==1 && twoCount==2 && threeCount==3 && fourCount==4 && fiveCount==5 && sixCount==6 && sevenCount==7;
    }
    public static boolean checkColumnRow4SumTo20InEach7by7(){
        return checkColumnRow4SumTo20InEach7by7(0, 6, 0,6)
                && checkColumnRow4SumTo20InEach7by7(5, 11, 0, 6)
                && checkColumnRow4SumTo20InEach7by7(0, 6, 5, 11)
                && checkColumnRow4SumTo20InEach7by7( 5, 11, 5, 11);
        
    }
    public static boolean checkColumnRow4SumTo20InEach7by7(int rowStart, int rowEnd, int columnStart, int columnEnd){

        for (int i=rowStart; i<rowEnd; i++){
            int hasValueCount=0;
            int sum=0;
            for (int j=columnStart; i<columnEnd; i++){
                if(SudokuBoard.result[i][j]!=0 && SudokuBoard.result[i][j]!=null){
                    hasValueCount++;
                    sum+=SudokuBoard.result[i][j];
                }
            }
            if(hasValueCount!=4 || sum!=20)
                return false;
        }
        for (int i=columnStart; i<columnEnd; i++){
            int hasValueCount=0;
            int sum=0;
            for (int j=rowStart; i<rowEnd; i++){
                if(SudokuBoard.result[i][j]!=0 && SudokuBoard.result[i][j]!=null){
                    hasValueCount++;
                    sum+=SudokuBoard.result[i][j];
                }
            }
            if(hasValueCount!=4 || sum!=20)
                return false;
        }
        return true;

    }
    
    public static boolean checkNumberCellsAreConnectedInEach7by7(){
        return checkNumberCellsAreConnectedInEach7by7(0, 6, 0,6)
                && checkNumberCellsAreConnectedInEach7by7(5, 11, 0, 6)
                && checkNumberCellsAreConnectedInEach7by7(0, 6, 5, 11)
                && checkNumberCellsAreConnectedInEach7by7( 5, 11, 5, 11);
    }
    public static boolean checkNumberCellsAreConnectedInEach7by7(int rowStart, int rowEnd, int columnStart, int columnEnd){
        boolean [][] markings = new boolean[rowEnd][columnEnd];
        outer: for (int i=rowStart; i<rowEnd; i++){
            for (int j=columnStart; j<columnEnd; j++){
                if(SudokuBoard.result[i][j]!=0 && SudokuBoard.result[i][j]!=null){
                    markings[i][j]=true;
                    markConnectedFrom(markings,i, j,rowStart, rowEnd,columnStart, columnEnd );
                    break outer;
                }
            }
        }

        for (int i=rowStart; i<rowEnd; i++){
            for (int j=columnStart; j<columnEnd; j++){
                if(SudokuBoard.result[i][j]!=0 && SudokuBoard.result[i][j]!=null){
                    if(markings[i][j]==false) return false;
                }
            }
        }
        return true;
    }

    private static void markConnectedFrom(boolean[][] markings, int i, int j, int rowStart, int rowEnd, int columnStart, int columnEnd) {
        //up
        if(i>rowStart){
            if(SudokuBoard.result[i-1][j]!=0 &&SudokuBoard.result[i-1][j]!=null){
                markings[i-1][j]=true;
                markConnectedFrom(markings,i-1,j, rowStart, rowEnd,columnStart,columnEnd);
            }
        }
        //down
        if(i<rowEnd){
            if(SudokuBoard.result[i+1][j]!=0 &&SudokuBoard.result[i+1][j]!=null){
                markings[i+1][j]=true;
                markConnectedFrom(markings,i+1,j, rowStart, rowEnd,columnStart,columnEnd);
            }
        }
        //left
        if(j>columnStart){
            if(SudokuBoard.result[i][j-1]!=0 &&SudokuBoard.result[i][j-1]!=null){
                markings[i][j-1]=true;
                markConnectedFrom(markings,i,j-1, rowStart, rowEnd,columnStart,columnEnd);
            }
        }
        //right
        if(j<columnEnd){
            if(SudokuBoard.result[i][j+1]!=0 &&SudokuBoard.result[i][j+1]!=null){
                markings[i][j+1]=true;
                markConnectedFrom(markings,i,j+1, rowStart, rowEnd,columnStart,columnEnd);
            }
        }
    }


}
