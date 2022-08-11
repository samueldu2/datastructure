package com.samueldu.leetcode.beginner.addtwonumbers.hash;

import java.util.*;

/**
 * This demonstrates the effect of hashcode collisions.
 * the speed difference of the same code below is:
 * HT:      217458<-- with collisions.
 * HTFast:  31 <-- without collisions.
 * That's 7000x difference!
 */
public class HashSetVSHashMap {

    public static void main(String [] args){

        long time0= System.currentTimeMillis();
        HashMap<RepeatedV, RepeatedV> hm= new HashMap<>();
        for (int i =0; i<10000; i++){
            RepeatedV rv = new RepeatedV(""+i);
            hm.put(rv, rv);
        }

        hm.keySet().stream().forEach(x->hm.get(x));
        long time1= System.currentTimeMillis();

        HashMap<RepeatedVFast, RepeatedVFast> hf= new HashMap<>();
        for (int i =0; i<10000000; i++){
            RepeatedVFast rv = new RepeatedVFast(""+i);
            hf.put(rv, rv);
        }
        hf.keySet().stream().forEach(x->hf.get(x));

        long time2= System.currentTimeMillis();
        System.out.println("HT:"+(time1-time0));
        System.out.println("HTFast:"+(time2-time1));
    }

    public static class RepeatedV{
        private String value;
        public int hashCode(){
            return 1;
        }

        public RepeatedV(String v){
            this.value=v;
        }
        public String toString(){return value;}
        public String getValue(){
            return value;
        }
    }

    public static class RepeatedVFast{
        private String value;
        public int hashCode(){
            return value.hashCode();
        }

        public RepeatedVFast(String v){
            this.value=v;
        }
        public String toString(){return value;}
        public String getValue(){
            return value;
        }
    }
}
