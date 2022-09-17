package com.samueldu.newfeatures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LambdaExpressionTest {
    LambdaExpression le  = new LambdaExpression();

    @Test
    public void lambda(){
        String s=le.lamdas(new Integer[]{1, 2, 3});
        assertEquals("123", s);
    }
}
