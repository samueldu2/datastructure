package com.samueldu.mis;

public class BitShifts {
    public static void main(String [] args){
        System.out.println(Integer.toBinaryString(-1));
        // prints "11111111111111111111111111111111"
        System.out.println(Integer.toBinaryString(-1 >> 16));
        // prints "11111111111111111111111111111111"
        System.out.println(Integer.toBinaryString(-1 << 16));
        // prints "11111111111111110000000000000000"
        System.out.println(Integer.toBinaryString(-1 >>> 16));
        // prints "1111111111111111"

        //To make things more clear adding positive counterpart

        System.out.println(Integer.toBinaryString(121));
// prints "1111001"
        System.out.println(Integer.toBinaryString(121 >> 1));
// prints "111100"
        System.out.println(Integer.toBinaryString(121 >>> 1));
// prints "111100"
    }
}
