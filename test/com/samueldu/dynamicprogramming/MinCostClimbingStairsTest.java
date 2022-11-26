package com.samueldu.dynamicprogramming;

import com.samueldu.dynamicprogramming.MinCostClimbingStairs;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MinCostClimbingStairsTest {
    MinCostClimbingStairs minCostClimbingStairs;

    @Before
    public void init(){
        minCostClimbingStairs= new MinCostClimbingStairs();
    }
    @Test
    public void minCostClimbingStairs(){

        int cost= minCostClimbingStairs.minCostClimbingStairs(new int[]{ 10, 15, 20});
        Assert.assertEquals(15, cost);
        int cost2= minCostClimbingStairs.minCostClimbingStairs(new int[]{ 1,100,1,1,1,100,1,1,100,1});
        Assert.assertEquals(6, cost2);
    }

    public void minCostClimbingStairsTopDown(){
        int cost= minCostClimbingStairs.minCostClimbingStairsTopDown(new int[]{ 10, 15, 20});
        Assert.assertEquals(15, cost);
        int cost2= minCostClimbingStairs.minCostClimbingStairsTopDown(new int[]{ 1,100,1,1,1,100,1,1,100,1});
        Assert.assertEquals(6, cost2);
    }


}