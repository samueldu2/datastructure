package com.samueldu.leetcode.studyplan.leetcode75.level1;

/**
 * Given an array of integers nums, calculate the pivot index of this array.
 *
 * The pivot index is the index where the sum of all the numbers strictly to the left of the index is equal to the sum of all the numbers strictly to the index's right.
 *
 * If the index is on the left edge of the array, then the left sum is 0 because there are no elements to the left. This also applies to the right edge of the array.
 *
 * Return the leftmost pivot index. If no such index exists, return -1.
 * Example 1:
 *
 * Input: nums = [1,7,3,6,5,6]
 * Output: 3
 * Explanation:
 * The pivot index is 3.
 * Left sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11
 * Right sum = nums[4] + nums[5] = 5 + 6 = 11
 * Example 2:
 *
 * Input: nums = [1,2,3]
 * Output: -1
 * Explanation:
 * There is no index that satisfies the conditions in the problem statement.
 * Example 3:
 *
 * Input: nums = [2,1,-1]
 * Output: 0
 * Explanation:
 * The pivot index is 0.
 * Left sum = 0 (no elements to the left of index 0)
 * Right sum = nums[1] + nums[2] = 1 + -1 = 0
 */
public class FindPivotIndex {

    public static void main(String [] args){
        int[] nums=new int[]{1, 7, 3,6, 5,6};
        int result= pivotIndex(nums);
        System.out.println(result);

        nums=new int[]{1, 2,3};
         result= pivotIndex(nums);
        System.out.println(result);

        nums=new int[]{2, 1,-1};
        result= pivotIndex(nums);
        System.out.println(result);
    }
    public static int pivotIndex(int[] nums) {
        //firset get the running sum array
        int [] runningsum=getRunningSum(nums);
        return searchForPivot(runningsum);

    }

    public static int searchForPivot(int[] runningsum){
        for (int i=0; i<runningsum.length; i++){
            int right=runningsum[runningsum.length-1]-runningsum[i];
            int left=0;
            if(i==0)
                left=0;
            else{
                left=runningsum[i-1];
            }
            if(left==right)
                return i;
        }
        return -1;
    }
    public static int [] getRunningSum(int[] nums){
        int[] result = new int[nums.length];
        if(nums.length==0)
            return result;
        int sum=nums[0];
        result[0]=sum;
        for(int i=1; i<nums.length; i++){
            sum+=nums[i];
            result[i]=sum;
        }
        return result;
    }
}
