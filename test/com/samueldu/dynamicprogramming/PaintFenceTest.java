package com.samueldu.dynamicprogramming;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PaintFenceTest {
    PaintFence paintFence;

    @Before
    public void init(){
        paintFence= new PaintFence();
    }
    @Test
    public void numWays(){

        int ways= paintFence.numWays(3,2 );
        Assert.assertEquals(6, ways);

        int ways2= paintFence.numWays(7,2 );
        Assert.assertEquals(42, ways2);

    }

}
