package com.samueldu.leetcode.dynamicprogramming;

import com.samueldu.leetcode.dynamicprogramming.stragegies.MinCostClimbingStairs;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MinCostClibingStairsTest {
    MinCostClimbingStairs minCostClimbingStairs;

    @Before
    public void init(){
        minCostClimbingStairs= new MinCostClimbingStairs();
    }
    @Test
    public void minCostClimbingStairs(){
        int[] input = new int[]{1, 2, 3};
        int minCost =minCostClimbingStairs.minCostClimbingStairs (input);
        Assert.assertEquals(3, minCost);
    }
}
