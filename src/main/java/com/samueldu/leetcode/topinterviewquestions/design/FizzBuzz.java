package com.samueldu.leetcode.topinterviewquestions.design;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Fizz Buzz
 * Easy
 *
 * 937
 *
 * 155
 *
 * Add to List
 *
 * Share
 * Given an integer n, return a string array answer (1-indexed) where:
 *
 * answer[i] == "FizzBuzz" if i is divisible by 3 and 5.
 * answer[i] == "Fizz" if i is divisible by 3.
 * answer[i] == "Buzz" if i is divisible by 5.
 * answer[i] == i (as a string) if none of the above conditions are true.
 *
 *
 * Example 1:
 *
 * Input: n = 3
 * Output: ["1","2","Fizz"]
 * Example 2:
 *
 * Input: n = 5
 * Output: ["1","2","Fizz","4","Buzz"]
 * Example 3:
 *
 * Input: n = 15
 * Output: ["1","2","Fizz","4","Buzz","Fizz","7","8","Fizz","Buzz","11","Fizz","13","14","FizzBuzz"]
 *
 *
 * Constraints:
 *
 * 1 <= n <= 104
 */
public class FizzBuzz {

        public List<String> fizzBuzz(int n) {

            // ans list
            List<String> ans = new ArrayList<String>();

            // Hash map to store all fizzbuzz mappings.
            HashMap<Integer, String> fizzBuzzDict =
                    new HashMap<Integer, String>() {
                        {
                            put(3, "Fizz");
                            put(5, "Buzz");
                        }
                    };

            // List of divisors which we will iterate over.
            List<Integer> divisors = new ArrayList<>(Arrays.asList(3, 5));

            for (int num = 1; num <= n; num++) {

                String numAnsStr = "";

                for (Integer key : divisors) {

                    // If the num is divisible by key,
                    // then add the corresponding string mapping to current numAnsStr
                    if (num % key == 0) {
                        numAnsStr += fizzBuzzDict.get(key);
                    }
                }

                if (numAnsStr.equals("")) {
                    // Not divisible by 3 or 5, add the number
                    numAnsStr += Integer.toString(num);
                }

                // Append the current answer str to the ans list
                ans.add(numAnsStr);
            }

            return ans;
        }


}
