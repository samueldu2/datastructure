package com.samueldu.leetcode.beginner.addtwonumbers.hash;

import java.util.HashSet;

public class SingleAmountDuplicates {
    public int singleNumber(int[] nums) {
        HashSet s = new HashSet();
        for (int n:nums){
            if(s.contains(n)){
                s.remove(n);
                continue;
            }
            s.add(n);
        }
        Object[] a= new Object[1];
        s.toArray(a);
        return new Integer(a[0].toString());
    }
}
