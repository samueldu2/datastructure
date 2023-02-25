package com.samueldu.dynamicprogramming;

import java.util.Arrays;
import java.util.Hashtable;

/**
 * ou are given an integer array cost where cost[i] is the cost of ith step on a staircase. Once you pay the cost, you can either climb one or two steps.
 *
 * You can either start from the step with index 0, or the step with index 1.
 *
 * Return the minimum cost to reach the top of the floor.
 *
 *
 *
 * Example 1:
 *
 * Input: cost = [10,15,20]
 * Output: 15
 * Explanation: You will start at index 1.
 * - Pay 15 and climb two steps to reach the top.
 * The total cost is 15.
 * Example 2:
 *
 * Input: cost = [1,100,1,1,1,100,1,1,100,1]
 * Output: 6
 * Explanation: You will start at index 0.
 * - Pay 1 and climb two steps to reach index 2.
 * - Pay 1 and climb two steps to reach index 4.
 * - Pay 1 and climb two steps to reach index 6.
 * - Pay 1 and climb one step to reach index 7.
 * - Pay 1 and climb two steps to reach index 9.
 * - Pay 1 and climb one step to reach the top.
 * The total cost is 6.
 *
 *
 * Constraints:
 *
 * 2 <= cost.length <= 1000
 * 0 <= cost[i] <= 999
 */
public class MinCostClimbingStairs {
    /**
     * bottom up
     */

    public int minCostClimbingStairs(int[] cost) {
        int[] minCosts= new int [cost.length+1];
        Arrays.fill(minCosts, 0);
        for (int i=2; i<minCosts.length; i++){
            int back1Cost = minCosts[i-1]+cost[i-1];
            int back2Cost = minCosts[i-2]+cost[i-2];
            minCosts[i]=Math.min(back1Cost , back2Cost);
        }
        return minCosts[minCosts.length-1];
    }

    /**
     * top down
     */
    public Hashtable <Integer, Integer> memo = new Hashtable<>();
    public int minCostClimbingStairsTopDown(int[] cost) {
        //start with n, reduce to base case
        return minCost(cost.length, cost);
    }
    public int minCost(int i , int[] cost){
        if(i==0 || i==1)
            return 0;
        if(memo.get(i)!=null){
            return memo.get(i);
        }

        int downOne = cost[i-1]+minCost(i-1, cost);
        int downTwo = cost[i-2]+minCost(i-2, cost);
        memo.put(i, Math.min(downOne, downTwo));
        return memo.get(i);
    }


}
