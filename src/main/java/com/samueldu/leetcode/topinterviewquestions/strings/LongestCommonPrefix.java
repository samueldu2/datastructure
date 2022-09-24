package com.samueldu.leetcode.topinterviewquestions.strings;

public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        StringBuffer buf = new StringBuffer();
            for (int j=0; j<strs[0].length(); j++){
                char c = strs[0].charAt(j);
                boolean diff=false;
                for (int i=0; i<strs.length; i++) {
                    if(strs[i].length()<=j){
                        diff=true;
                        break;
                    }
                    if(strs[i].charAt(j)!=c)
                        diff=true;
                }
                if(!diff)
                    buf.append(c);
                else
                    break;
            }
            return buf.toString();
    }
}
