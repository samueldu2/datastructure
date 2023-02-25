package com.samueldu.soduku;

public class SudokuBoardPreValidator {
    /**
     * check whether selections are legal up to k,
     *
     * @param k
     * @return true if everything is leagl up to k
     * false of new addition is illegal
     */
    public static boolean check(int k) {
        int rowNumber = k / SudokuBoard.grid[0].length;
        int columnNUmber = k % SudokuBoard.grid[0].length;
        return check7by7(rowNumber, columnNUmber) && checkSideNumbers(rowNumber, columnNUmber);
    }

    private static boolean checkSideNumbers(int rowNumber, int columnNUmber) {
        return checkSumOfRowOrColumnAndValueOfFirstNumber(rowNumber, columnNUmber);
    }

    private static boolean checkSumOfRowOrColumnAndValueOfFirstNumber(int rowNumber, int columnNUmber) {
        return checkSumOfRowOrColumnAndValueOfFirstNumberTop(rowNumber, columnNUmber) &&
                checkSumOfRowOrColumnAndValueOfFirstNumberBottom(rowNumber, columnNUmber) &&
                checkSumOfRowOrColumnAndValueOfFirstNumberLeft(rowNumber, columnNUmber) &&
                checkSumOfRowOrColumnAndValueOfFirstNumberRight(rowNumber, columnNUmber);
    }

    private static boolean checkSumOfRowOrColumnAndValueOfFirstNumberRight(int rowNumber, int columnNUmber) {
        if (columnNUmber == SudokuBoard.grid[0].length - 1) {
            int sumOfRow = 0;
            boolean seenFirstValue = false;
            for (int j = SudokuBoard.grid[0].length; j > 0; j--) {
                if (SudokuBoard.result[rowNumber][j] != null) {
                    if (!seenFirstValue && SudokuBoard.result[rowNumber][j] == SudokuBoard.vertical[1][j])
                        return true;
                    seenFirstValue = true;
                    sumOfRow += SudokuBoard.result[rowNumber][j];
                }
            }
            if (SudokuBoard.vertical[1][rowNumber]!=null && SudokuBoard.vertical[1][rowNumber] > 0)
                if (sumOfRow != SudokuBoard.vertical[1][rowNumber])
                    return false;

        } else {

            int sumOfRow = 0;
            for (int j = columnNUmber; j > 0; j--) {
                if (SudokuBoard.result[rowNumber][j] != null) {
                    sumOfRow += SudokuBoard.result[rowNumber][j];
                }
            }
            if (SudokuBoard.vertical[1][rowNumber]!=null && SudokuBoard.vertical[1][rowNumber] > 7)
                if (sumOfRow > SudokuBoard.vertical[1][rowNumber])
                    return false;

        }
        return true;
    }

    private static boolean checkSumOfRowOrColumnAndValueOfFirstNumberLeft(int rowNumber, int columnNUmber) {


        int sum = 0;
        boolean seenFirstValue = false;
        for (int j = 0; j < columnNUmber; j++) {
            if (SudokuBoard.result[rowNumber][j] != null) {
                if (!seenFirstValue && SudokuBoard.result[rowNumber][j] == SudokuBoard.vertical[0][j])
                    return true;
                seenFirstValue = true;
                sum += SudokuBoard.result[rowNumber][j];
            }
        }
        if (SudokuBoard.vertical[0][rowNumber] > 0)
            if (sum > SudokuBoard.vertical[0][rowNumber])
                return false;


        return true;

    }

    private static boolean checkSumOfRowOrColumnAndValueOfFirstNumberBottom(int rowNumber, int columnNUmber) {
        if (rowNumber == SudokuBoard.grid.length - 1) {

                int sum = 0;
                boolean seenFirstValue = false;
                for (int j = SudokuBoard.grid.length; j > 0; j--) {
                    if (SudokuBoard.result[j][columnNUmber] != null) {
                        if (!seenFirstValue && SudokuBoard.result[j][columnNUmber] == SudokuBoard.horizontal[1][columnNUmber])
                            return true;
                        seenFirstValue = true;
                        sum += SudokuBoard.result[j][columnNUmber];
                    }
                }
                if (SudokuBoard.horizontal[1][columnNUmber]>0) {
                    if (sum != SudokuBoard.horizontal[1][columnNUmber])
                        return false;
                }


        }else{
            int sum = 0;

            for (int j = SudokuBoard.grid.length-1; j > 0; j--) {
                if (SudokuBoard.result[j][columnNUmber] != null) {
                    sum += SudokuBoard.result[j][columnNUmber];
                }
            }

            if (SudokuBoard.horizontal[1][columnNUmber]!=null && SudokuBoard.horizontal[1][columnNUmber]>7) {
                if (sum > SudokuBoard.horizontal[1][columnNUmber])
                    return false;
            }
            return true;
        }
        return true;
    }


    private static boolean checkSumOfRowOrColumnAndValueOfFirstNumberTop(int rowNumber, int columnNUmber) {

            int sum = 0;
            boolean seenFirstValue = false;
            for (int j = 0; j < rowNumber; j++) {
                if (SudokuBoard.result[j][columnNUmber] != null) {
                    if(SudokuBoard.horizontal[0][columnNUmber]!=null && SudokuBoard.horizontal[0][columnNUmber]!=0) {
                        if (!seenFirstValue && SudokuBoard.result[j][columnNUmber] == SudokuBoard.horizontal[0][columnNUmber])
                            return true;
                    }
                    seenFirstValue = true;
                    sum += SudokuBoard.result[j][columnNUmber];
                }
            }
            if(SudokuBoard.horizontal[0][columnNUmber]!=null && SudokuBoard.horizontal[0][columnNUmber]>7)
                if (sum > SudokuBoard.horizontal[0][columnNUmber]) return false;

            return true;

    }


    private static boolean check2by2ContainsOneOrMoreEmptyCellInEach7by7(int rowNumber, int columnNUmber) {
        return check2by2ContainsOneOrMoreEmptyCellInEach7by7(rowNumber, columnNUmber,0, 6, 0, 6) &&
                check2by2ContainsOneOrMoreEmptyCellInEach7by7(rowNumber, columnNUmber,5, 11, 0, 6) &&
                check2by2ContainsOneOrMoreEmptyCellInEach7by7(rowNumber, columnNUmber,0, 6, 5, 11) &&
                check2by2ContainsOneOrMoreEmptyCellInEach7by7(rowNumber, columnNUmber,5, 11, 5, 11);
    }

    private static boolean check2by2ContainsOneOrMoreEmptyCellInEach7by7(int rowNumber, int columnNUmber,int rowStart, int rowEnd, int columnStart, int columnEnd) {
        if(rowNumber<rowStart+1|| rowNumber>rowEnd|| columnNUmber<columnStart+1|| columnNUmber>columnEnd) return true;

        for (int i = rowStart; i < rowEnd - 1; i++) {
            for (int j = columnStart; j < columnEnd - 1; j++) {
                if (!check2by2(i, i + 1, j, j + 1))
                    return false;
            }
        }
        return true;
    }

    private static boolean check2by2(int rowStart, int rowEnd, int columnStart, int columnEnd) {
        for (int i = rowStart; i < rowEnd; i++) {
            for (int j = columnStart; j < columnEnd; j++) {
                if ( SudokuBoard.result[i][j] == null||SudokuBoard.result[i][j] == 0 ) return true;
            }
        }
        return false;
    }


    public static boolean check7by7(int rowNumber, int columnNUmber) {
        return check1through7( rowNumber,  columnNUmber) &&
        checkColumnRow4SumTo20InEach7by7( rowNumber,  columnNUmber) &&
        checkNumberCellsAreConnectedInEach7by7( rowNumber,  columnNUmber) && check2by2ContainsOneOrMoreEmptyCellInEach7by7( rowNumber,  columnNUmber);
    }

    public static boolean check1through7(int rowNumber, int columnNUmber) {
        return check1through7(rowNumber,  columnNUmber,0, 6, 0, 6) &&
                check1through7(rowNumber,  columnNUmber,5, 11, 0, 6) &&
                check1through7(rowNumber,  columnNUmber,0, 6, 5, 11) &&
                check1through7(rowNumber,  columnNUmber,5, 11, 5, 11);
    }

    public static boolean check1through7(int rowNumber, int columnNUmber, int rowStart, int rowEnd, int columnStart, int columnEnd) {
        if(rowNumber<rowStart|| rowNumber>rowEnd) return true;
        if(columnNUmber<columnStart|| columnNUmber>columnEnd) return true;
        if(rowNumber!=rowEnd ||columnNUmber!=columnEnd) return true;

        rowEnd=rowNumber<rowEnd? rowNumber: rowEnd;

        int oneCount = 0;
        int twoCount = 0;
        int threeCount = 0;
        int fourCount = 0;
        int fiveCount = 0;
        int sixCount = 0;
        int sevenCount = 0;
        for (int i = rowStart; i < rowEnd; i++) {
            for (int j = columnStart; j < columnEnd; j++) {
                if(i!=rowEnd) {
                    if (SudokuBoard.result[i][j] == 1) oneCount++;
                    if (SudokuBoard.result[i][j] == 2) twoCount++;
                    if (SudokuBoard.result[i][j] == 3) threeCount++;
                    if (SudokuBoard.result[i][j] == 4) fourCount++;
                    if (SudokuBoard.result[i][j] == 5) fiveCount++;
                    if (SudokuBoard.result[i][j] == 6) sixCount++;
                    if (SudokuBoard.result[i][j] == 7) sevenCount++;
                }else{
                    if(j<=columnNUmber){
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
        }
        return oneCount == 1 && twoCount == 2 && threeCount == 3 && fourCount == 4 && fiveCount == 5 && sixCount == 6 && sevenCount == 7;
    }

    public static boolean checkColumnRow4SumTo20InEach7by7(int rowNumber, int columnNUmber) {
        return checkColumnRow4SumTo20InEach7by7(rowNumber,  columnNUmber,0, 6, 0, 6)
                && checkColumnRow4SumTo20InEach7by7(rowNumber,  columnNUmber,5, 11, 0, 6)
                && checkColumnRow4SumTo20InEach7by7(rowNumber,  columnNUmber,0, 6, 5, 11)
                && checkColumnRow4SumTo20InEach7by7(rowNumber,  columnNUmber,5, 11, 5, 11);

    }

    public static boolean checkColumnRow4SumTo20InEach7by7(int rowNumber, int columnNUmber,int rowStart, int rowEnd, int columnStart, int columnEnd) {
        //do nothing
        if(rowNumber!=rowEnd || columnNUmber!=columnEnd)
            return true;

        //just check one row
        {
            rowEnd = rowNumber < rowEnd ? rowNumber : rowEnd;
            columnEnd = columnNUmber < columnEnd ? columnNUmber : columnEnd;

            int hasValueCount = 0;
            int sum = 0;
            for (int i = rowStart; i < rowEnd; i++) {

                if (SudokuBoard.result[i][columnNUmber] != 0 && SudokuBoard.result[i][columnNUmber] != null) {
                    hasValueCount++;
                    sum += SudokuBoard.result[i][columnNUmber];
                }


            }
            if (hasValueCount != 4 || sum != 20)
                return false;
        }
        {
            int hasValueCount = 0;
            int sum = 0;
            for (int j = columnStart; j < columnEnd; j++) {


                    if (SudokuBoard.result[rowNumber][j] != 0 && SudokuBoard.result[rowNumber][j] != null) {
                        hasValueCount++;
                        sum += SudokuBoard.result[rowNumber][j];
                    }


            }
            if (hasValueCount != 4 || sum != 20)
                return false;
            return true;
        }

    }

    public static boolean checkNumberCellsAreConnectedInEach7by7(int rowNumber, int columnNUmber) {
        return checkNumberCellsAreConnectedInEach7by7( rowNumber,  columnNUmber,0, 6, 0, 6)
                && checkNumberCellsAreConnectedInEach7by7(rowNumber,  columnNUmber,5, 11, 0, 6)
                && checkNumberCellsAreConnectedInEach7by7(rowNumber,  columnNUmber,0, 6, 5, 11)
                && checkNumberCellsAreConnectedInEach7by7(rowNumber,  columnNUmber,5, 11, 5, 11);
    }

    public static boolean checkNumberCellsAreConnectedInEach7by7(int rowNumber, int columnNUmber,int rowStart, int rowEnd, int columnStart, int columnEnd) {
        if(rowNumber<rowEnd || columnNUmber<columnStart|| rowNumber>rowEnd || columnNUmber>columnEnd) return true;

        boolean[][] markings = new boolean[rowEnd][columnEnd];
        outer:
        for (int i = rowStart; i < rowEnd; i++) {
            for (int j = columnStart; j < columnEnd; j++) {
                if (SudokuBoard.result[i][j] != 0 && SudokuBoard.result[i][j] != null) {
                    markings[i][j] = true;
                    markConnectedFrom(markings, i, j, rowStart, rowEnd, columnStart, columnEnd);
                    break outer;
                }
            }
        }

        for (int i = rowStart; i < rowEnd; i++) {
            for (int j = columnStart; j < columnEnd; j++) {
                if (SudokuBoard.result[i][j] != 0 && SudokuBoard.result[i][j] != null) {
                    if (markings[i][j] == false) return false;
                }
            }
        }
        return true;
    }

    private static void markConnectedFrom(boolean[][] markings, int i, int j, int rowStart, int rowEnd, int columnStart, int columnEnd) {
        //up
        if (i > rowStart) {
            if (SudokuBoard.result[i - 1][j] != 0 && SudokuBoard.result[i - 1][j] != null) {
                markings[i - 1][j] = true;
                markConnectedFrom(markings, i - 1, j, rowStart, rowEnd, columnStart, columnEnd);
            }
        }
        //down
        if (i < rowEnd) {
            if (SudokuBoard.result[i + 1][j] != 0 && SudokuBoard.result[i + 1][j] != null) {
                markings[i + 1][j] = true;
                markConnectedFrom(markings, i + 1, j, rowStart, rowEnd, columnStart, columnEnd);
            }
        }
        //left
        if (j > columnStart) {
            if (SudokuBoard.result[i][j - 1] != 0 && SudokuBoard.result[i][j - 1] != null) {
                markings[i][j - 1] = true;
                markConnectedFrom(markings, i, j - 1, rowStart, rowEnd, columnStart, columnEnd);
            }
        }
        //right
        if (j < columnEnd) {
            if (SudokuBoard.result[i][j + 1] != 0 && SudokuBoard.result[i][j + 1] != null) {
                markings[i][j + 1] = true;
                markConnectedFrom(markings, i, j + 1, rowStart, rowEnd, columnStart, columnEnd);
            }
        }
    }


}
