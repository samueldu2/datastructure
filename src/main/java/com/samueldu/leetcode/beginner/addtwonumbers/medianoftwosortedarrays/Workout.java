package com.samueldu.leetcode.beginner.addtwonumbers.medianoftwosortedarrays;

/**
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 *
 * The overall run time complexity should be O(log (m+n)).
 */
public class Workout {

    public static void main(String [] args){
        int [] nums1 ={2};
        int [] nums2 ={1};

        System.out.println( new Workout().findMedianSortedArrays(nums1, nums2));
    }
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

       int[] merged= merge(nums1,nums2);
       if(merged.length%2==1){
           int mid = merged.length/2;
           return merged[mid];
       }else{
           int leftMid = merged.length/2-1;
           int rightMid=leftMid+1;
           return ((double)(merged[leftMid]+merged[rightMid]))/2.0;
       }
    }

    public int[] merge(int[] a1, int[] a2){
        int[] result = new int[a1.length+a2.length];
        int a1Index=0, a2Index=0;
        int count=0;
        while (a1Index<a1.length|| a2Index<a2.length){
            if(a1Index==a1.length){
                if(a2Index==a2.length)
                    return result;
                else{
                    result[count++]=a2[a2Index++];
                    continue;
                }
            }
            if(a2Index==a2.length){
                if(a1Index==a1.length)
                    return result;
                else{
                    result[count++]=a1[a1Index++];
                    continue;
                }
            }
            if(a1[a1Index]<a2[a2Index] ){
                    result[count++]=a1[a1Index++];

            }else{
                result[count++]=a2[a2Index++];
            }
        }
        return result;
    }
}

//using while loop, this is O(m+n).