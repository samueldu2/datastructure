package com.samueldu.leetcode.dynamicprogramming;

import com.samueldu.leetcode.dynamicprogramming.stragegies.DeleteAndEarn;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DeleteAndEarnTest {
    DeleteAndEarn deleteAndEarn;

    @Before
    public void init(){
        deleteAndEarn= new DeleteAndEarn();
    }
    @Test
    public void deleteAndEarnTest(){

        int tb =deleteAndEarn.deleteAndEarn (new int []{3, 4, 2});
        Assert.assertEquals(6, tb);
         tb =deleteAndEarn.deleteAndEarn (new int []{2, 2, 3, 3, 3, 4});
        Assert.assertEquals(9, tb);
    }


}
