package com.samueldu.leetcode.beginner.addtwonumbers.hash;

import java.util.Arrays;
import java.util.HashSet;

public class Intersection {
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet <Integer>s = new HashSet<>();
        Arrays.stream(nums1).forEach(x->s.add(new Integer(x)));
        HashSet result = new HashSet();
        Arrays.stream(nums2).forEach(x->{if(s.contains(x) ) result.add(x);});

        Object[] o=result.toArray();
        int [] resultsInt= new int [o.length];
        for(int i =0; i<o.length; i++){
            resultsInt[i]=(Integer)o[i];
        }
        return resultsInt;
    }
}
