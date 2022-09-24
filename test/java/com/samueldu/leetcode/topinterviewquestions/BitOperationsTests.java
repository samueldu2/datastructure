package com.samueldu.leetcode.topinterviewquestions;

import com.samueldu.leetcode.topinterviewquestions.array.BitOperations;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BitOperationsTests {

    private BitOperations bitOperations;

    @Before
    public void init(){
        bitOperations=new BitOperations();
    }
    @Test
    public void union(){
        /**
         * 7: 111, 3: 11==> 111 | 11= 111
         */
       int result= bitOperations.union(7, 3);
        Assert.assertEquals(result, 7);
    }

    @Test
    public void intersection(){
        /**
         * 7: 111, 5: 101==> 111&101 = 101
         */
        int result= bitOperations.intersection(7, 5);
        Assert.assertEquals(result, 5);
    }

    @Test
    public void subtraction(){
        /**
         * 7: 111, 5: 101== 111 &~ 101=010
         */
        int result = bitOperations.subtraction(7, 5);
        Assert.assertEquals(result, 2);
    }

    @Test
    public void negateAllBits(){
        int result = bitOperations.negateAllBits(7);
        Assert.assertEquals(result, -8);
    }

    @Test
    public void setBit(){
        int result = bitOperations.setBit(8, 2);
        Assert.assertEquals(result, 12);
    }

    @Test
    public void clearBit(){
        int result= bitOperations.clearBit(15, 2);
        Assert.assertEquals(result, 11);
    }

    @Test
    public void testBit(){
        /**
         * 12: 1100
         * 11: 1011
         */
        boolean result= bitOperations.testBit(12, 2);
        Assert.assertEquals(result, true);
        result = bitOperations.testBit(11, 2);
        Assert.assertEquals(result, false);
    }

    /**
     * extract the last non zero bit
     * for example
     * 14: 1110==>10
     * 13: 1011==> 1
     * 12: 1100==> 100
     */
    @Test
    public void extractLastBit(){
        int result = bitOperations.extractLastBit(3);
        Assert.assertEquals(result , 1);
        result= bitOperations.extractLastBit(12);
        Assert.assertEquals(result, 4);
        result= bitOperations.extractLastBit(13);
        Assert.assertEquals(result, 1);
        result= bitOperations.extractLastBit(14);
        Assert.assertEquals(result, 2);
    }

    @Test
    public void extractLastBit2(){
        int result = bitOperations.extractLastBitMethod2(3);
        Assert.assertEquals(result , 1);
        result= bitOperations.extractLastBitMethod2(12);
        Assert.assertEquals(result, 4);
        result= bitOperations.extractLastBitMethod2(13);
        Assert.assertEquals(result, 1);
        result= bitOperations.extractLastBitMethod2(14);
        Assert.assertEquals(result, 2);
    }

    @Test
    public void extractLastBit3(){
        int result = bitOperations.extractLastBitMethod3(3);
        Assert.assertEquals(result , 1);
        result= bitOperations.extractLastBitMethod3(12);
        Assert.assertEquals(result, 4);
        result= bitOperations.extractLastBitMethod3(13);
        Assert.assertEquals(result, 1);
        result= bitOperations.extractLastBitMethod3(14);
        Assert.assertEquals(result, 2);
    }

    @Test
    public void removeLastBit(){
        /**
         * 5: 101 ==> 100
         */
        int result  = bitOperations.removeLastBit(5);
        Assert.assertEquals(result, 4);
    }

    @Test
    public void getAllOneBits(){
        int result = bitOperations.getAllOneBits();
        Assert.assertEquals(result, -1);
    }

}
