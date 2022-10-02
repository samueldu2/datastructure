package com.samueldu.leetcode.dynamicprogramming.stragegies;

import java.util.HashMap;

/**
 * The Tribonacci sequence Tn is defined as follows:
 *
 * T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.
 *
 * Given n, return the value of Tn.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 4
 * Output: 4
 * Explanation:
 * T_3 = 0 + 1 + 1 = 2
 * T_4 = 1 + 1 + 2 = 4
 * Example 2:
 *
 * Input: n = 25
 * Output: 1389537
 *
 *
 * Constraints:
 *
 * 0 <= n <= 37
 * The answer is guaranteed to fit within a 32-bit integer, ie. answer <= 2^31 - 1.
 */
public class NthTribonacciNumber {
    /**
     * bottom up
     * @param n
     * @return
     */
    public int tribonacci(int n) {
        int [] tbnc= new int[ n+1];
        if (n==0) return 0;
        if (n==1 || n==2) return 1;
        /**
         * base cases
         */
        tbnc[0]=0;
        tbnc[1]=1;
        tbnc[2]=1;
        for (int i=3; i<n+1; i++){
            tbnc[i]=tbnc[i-1]+tbnc[i-2]+tbnc[i-3];
        }
        return tbnc[n];
    }

    /**
     * top down
     * @param n
     * @return
     */
    HashMap<Integer, Integer> cache= new HashMap<>();
    public int tribonacciTopDown(int n){
        //base cases
        if(n==0) return 0;
        if(n==1|| n==2) return 1;
        if(cache.get(n)!=null) return cache.get(n);
        Integer downOne = cache.get(n-1);
        Integer downTwo = cache.get(n-2);
        Integer downThree = cache.get(n-3);
        if(downOne==null) {
            downOne=tribonacciTopDown(n-1);
            cache.put(n-1, downOne);
        }
        if(downTwo==null){
            downTwo=tribonacciTopDown(n-2);
            cache.put(n-2, downTwo);
        }
        if(downThree==null){
            downThree=tribonacciTopDown(n-3);
            cache.put(n-3, downThree);
        }
        Integer current = downTwo+downThree+downOne;
        cache.put(n, current);
        return cache.get(n);

    }
}
