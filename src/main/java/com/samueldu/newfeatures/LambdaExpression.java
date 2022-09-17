package com.samueldu.newfeatures;

import java.util.Arrays;

public class LambdaExpression {

    public String lamdas(Integer[] ia){
        StringBuffer result=new StringBuffer();
         Arrays.stream(ia).forEach(x->result.append(x));
         return result.toString();
    }
}
