package com.samueldu.basics;

import java.util.Arrays;
import java.util.List;

public class Array{
    public static void main(String[] args){
        int [] nubmers ={ 1, 2, 3};
        int [][] arr={{1}, {2}, {1,2,3}};
        int [] nums = new int[] {1,2 , 3};
        List numl = Arrays.asList( 1, 2, 3);
        Integer [] numlArray=new Integer[3];
        Arrays.asList(1, 2,3).toArray(numlArray);

        /**
         * check out Arrays methods.
         */
        Arrays.stream(numlArray).forEach(System.out::println);

        char[][] ticktacktock = {
               {'-', '-', '-'},
                {'-', '-', '-'},
               {'-', '-', '-'}
        };

       System.out.println(Arrays.deepToString(ticktacktock));

    }
}