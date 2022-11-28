package com.samueldu.dynamicprogramming;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CoinChange2Test {
    CoinChange2 coinChange2;

    @Before
    public void init(){
        coinChange2= new CoinChange2();
    }
    @Test
    public void numWays(){

        int ways2= coinChange2.change(4,new int[]{2, 5} );
        Assert.assertEquals(1, ways2);

        int ways= coinChange2.change(5,new int[]{1, 2, 5} );
        Assert.assertEquals(4, ways);

        int ways3= coinChange2.change(3,new int[]{ 2} );
        Assert.assertEquals(0, ways3);





    }
}
