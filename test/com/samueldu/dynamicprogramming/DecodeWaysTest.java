package com.samueldu.dynamicprogramming;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DecodeWaysTest {

    DecodeWays DecodeWays;

    @Before
    public void init(){
        DecodeWays= new DecodeWays();
    }
    @Test
    public void numWays(){

        int ways= DecodeWays.numDecodings("12");
        Assert.assertEquals(2, ways);

    }
}
