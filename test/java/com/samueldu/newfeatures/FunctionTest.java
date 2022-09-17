package com.samueldu.newfeatures;

import org.junit.Assert;
import org.junit.Test;

public class FunctionTest {
    Functions fs = new Functions();

    @Test
    public void suppliers(){
        String s = fs.suppliers();
        Assert.assertTrue(s.startsWith("supplied string"));
    }
}
