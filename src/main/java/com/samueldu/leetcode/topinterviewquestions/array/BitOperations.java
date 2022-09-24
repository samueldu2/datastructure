package com.samueldu.leetcode.topinterviewquestions.array;

/**
 * Set union A | B
 * Set intersection A & B
 * Set subtraction A & ~B
 * Set negation ALL_BITS ^ A or ~A
 * Set bit A |= 1 << bit
 * Clear bit A &= ~(1 << bit)
 * Test bit (A & 1 << bit) != 0
 * Extract last bit A&-A or A&~(A-1) or x^(x&(x-1))
 * Remove last bit A&(A-1)
 * Get all 1-bits ~0
 */
public class BitOperations {
    /**
     * bit wise union
     * 111 | 101 ==> 111
     */
    public int union(int A, int B){
        return A|B;
    }

    /**
     * bit wise intersection
     * 111 & 101==> 101
     */
    public int intersection(int A, int B){
        return A & B;
    }

    /**
     * bitwise subtraction
     * 111 & ~ 101
     * 1. ~101==> 010
     * 2. 111 & 010 ==> 010
     */
    public int subtraction(int A, int B){
        return A &~ B;
    }

    /**
     * bitwise negation
     */
    public int negateAllBits(int A){
        System.out.println(Integer.toBinaryString(A));
        System.out.println(Integer.toBinaryString(~A));
        return ~A;
    }

    /**
     * set bit
     * 1000 |= 1<<2 ==> 1000 | 100==>1100
     */
    public int setBit(int A, int bit){
        System.out.println(Integer.toBinaryString(A));
        System.out.println(Integer.toBinaryString(A |= 1<<bit));
        return A |= 1<<bit;
    }

    /**
     *
     */
    public int clearBit(int A, int bit){
        System.out.println(Integer.toBinaryString(A));
        System.out.println(Integer.toBinaryString(A &= ~(1<<bit)));
        return A &= ~(1<<bit);
    }

    public boolean testBit(int A, int bit){
        return (A & 1<<bit) !=0;
    }

    /**
     * extract the last non zero bit
     * for example
     * 14: 1110==>10
     * 13: 1011==> 1
     * 12: 1100==> 100
     * @param A
     * @return
     */
    public int extractLastBit(int A){
        System.out.println(Integer.toBinaryString(A));
        System.out.println(Integer.toBinaryString(-A));
        return A&-A;
    }

    public int extractLastBitMethod2(int A){
        System.out.println(Integer.toBinaryString(A));
        System.out.println(Integer.toBinaryString(A-1));
        System.out.println(Integer.toBinaryString(~(A-1)));
        return A&~(A-1);
    }

    public int extractLastBitMethod3(int A){
        System.out.println(Integer.toBinaryString(A));
        System.out.println(Integer.toBinaryString(A-1));
        System.out.println(Integer.toBinaryString(A&(A-1)));
        System.out.println(Integer.toBinaryString(A^(A&(A-1))));
        return A^(A&(A-1));
    }

    public int removeLastBit(int A){
        System.out.println(Integer.toBinaryString(A));
        System.out.println(Integer.toBinaryString(A-1));
        return A&(A-1);
    }

    public int getAllOneBits(){
        System.out.println(Integer.toBinaryString(~0));
        return ~0;
    }
}
