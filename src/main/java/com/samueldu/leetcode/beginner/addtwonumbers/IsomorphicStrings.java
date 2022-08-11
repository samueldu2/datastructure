package com.samueldu.leetcode.beginner.addtwonumbers;

import java.util.HashMap;

public class IsomorphicStrings {
public static void main(String [] args){
    IsomorphicStrings is= new IsomorphicStrings();
    System.out.println(is.isIsomorphic("badc", "baba"));
}

        public boolean isIsomorphic(String s, String t) {
            if(s.length()!=t.length()) return false;
            HashMap<Character, Character>hm = new HashMap<>();
            StringBuffer buf = new StringBuffer();
            for(int i =0 ; i<t.length(); i++){
                Character from =s.charAt(i);

                Character to= hm.get(from);
                if(to==null){
                    to=t.charAt(i);
                    if(hm.containsValue(to))
                        return false;
                    hm.put(from, to);
                }
                buf.append(to);
            }
            String result= buf.toString();
            return t.equals(result);
        }

}
