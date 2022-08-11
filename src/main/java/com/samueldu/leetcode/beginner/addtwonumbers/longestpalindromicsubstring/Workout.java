package com.samueldu.leetcode.beginner.addtwonumbers.longestpalindromicsubstring;

/**
 * Given a string s, return the longest palindromic substring in s.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "babad"
 * Output: "bab"
 * Explanation: "aba" is also a valid answer.
 * Example 2:
 *
 * Input: s = "cbbd"
 * Output: "bb"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s consist of only digits and English letters.
 */
public class Workout {
    public static void main(String [] args){
        Workout wo = new Workout();
        System.out.println(wo.longestPalindrome("ccc"));
    }

    /**
     * My implementation here is buggy
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        String result="";
        int maxPalindromLength=0;
        for (int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            //look for whether the next one is the same
            if(i<s.length()-1 && c== s.charAt(i+1)){
                String tempResult = s.substring(i, i+2);
                if(tempResult.length()>maxPalindromLength){
                    result = tempResult;
                    maxPalindromLength= result.length();
                }
                //we look extends on both sides
                int extendCount = 0;


                while (i - extendCount > 0 && i + extendCount + 1 < s.length()) {
                        if(s.charAt(i-extendCount)==s.charAt(i+extendCount+1)){
                            tempResult = s.substring(i-extendCount, i+extendCount+2);
                            if(tempResult.length()>maxPalindromLength){
                                result = tempResult;
                                maxPalindromLength= result.length();
                            }
                            extendCount++;
                        }else{
                            break;
                        }
                }

            }

            //try middle extensions for all.
            {
                String tempResult=s.substring(i, i+1);
                if(tempResult.length()>maxPalindromLength){
                    result = tempResult;
                    maxPalindromLength= result.length();
                }

                int extendCount = 0;
                while (i - extendCount >= 0 && i + extendCount  < s.length()) {
                    if(s.charAt(i-extendCount)==s.charAt(i+extendCount)){
                        tempResult = s.substring(i-extendCount, i+extendCount+1);
                        if(tempResult.length()>maxPalindromLength){
                            result = tempResult;
                            maxPalindromLength= result.length();
                        }
                        extendCount++;
                    }else{
                        break;
                    }
                }
            }
        }


        return result;
    }
}
