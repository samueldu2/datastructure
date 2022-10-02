package com.samueldu.leetcode.dynamicprogramming;

import com.samueldu.leetcode.dynamicprogramming.stragegies.NthTribonacciNumber;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NthTribonacciNumberTest {
    NthTribonacciNumber nthTribonacciNumber;

    @Before
    public void init(){
        nthTribonacciNumber= new NthTribonacciNumber();
    }
    @Test
    public void tribonacciTest(){

        int tb =nthTribonacciNumber.tribonacci (4);
        Assert.assertEquals(4, tb);

         tb =nthTribonacciNumber.tribonacci (25);
        Assert.assertEquals(1389537, tb);
    }

    @Test
    public void tribonacciTopDownTest(){

        int tb =nthTribonacciNumber.tribonacciTopDown (4);
        Assert.assertEquals(4, tb);

        tb =nthTribonacciNumber.tribonacciTopDown(37);
        Assert.assertEquals(2082876103, tb);
    }
}
