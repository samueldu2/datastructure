package com.samueldu.leetcode.dynamicprogramming.commonpatterns;

import java.util.List;

/**
 * Word Break
 * Medium
 *
 * 12368
 *
 * 526
 *
 * Add to List
 *
 * Share
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "leetcode", wordDict = ["leet","code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * Example 2:
 *
 * Input: s = "applepenapple", wordDict = ["apple","pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * Note that you are allowed to reuse a dictionary word.
 * Example 3:
 *
 * Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s and wordDict[i] consist of only lowercase English letters.
 * All the strings of wordDict are unique.
 *
 * In this article, we'll use the framework to solve Word Break. So far, in this card, this is the most unique and perhaps the most difficult problem to see that dynamic programming is a viable approach. This is because, unlike all of the previous problems, we will not be working with numbers at all. When a question asks, "is it possible to do..." it isn't necessarily a dead giveaway that it should be solved with DP. However, we can see that in this question, the order in which we choose words from \text{wordDict}wordDict is important, and a greedy strategy will not work.
 *
 * Recall back in the first chapter, we said that a good way to check if a problem should be solved with DP or greedy is to first assume that it can be solved greedily, then try to think of a counterexample.
 *
 * Let's say that we had \text{s} =s= "abcdef" and \text{wordDict = [}wordDict = [ "abcde", "ef", "abc", "a", "d"\text{]}]. A greedy algorithm (picking the longest substring available) will not be able to determine that picking "abcde" here is the wrong decision. Likewise, a greedy algorithm (picking the shortest substring available) will not be able to determine that picking "a" first is the wrong decision.
 *
 * With that being said, let's develop a DP algorithm using our framework:
 *
 * For this problem, we'll look at bottom-up first.
 */
public class WordBreak {
    /**
     *1. An array that answers the problem for a given state
     *
     * Despite this problem being unlike the ones we have seen so far, we should still stick to the ideas of the framework. In the article where we learned about multi-dimensional dynamic programming, we talked about how an index variable, usually denoted \text{i}i is typically used in DP problems where the input is an array or string. All the problems that we have looked at up to this point reflect this.
     *
     * With this in mind, let's use a state variable \text{i}i, which keeps track of which index we are currently at in \text{s}s.
     *
     * Do we need any other state variables? The other input is \text{wordDict}wordDict - however, it says in the problem that we can reuse words from \text{wordDict}wordDict as much as we want. Therefore, a state variable isn't necessary because \text{wordDict}wordDict and what we can do with it never changes. If the problem was changed so that we can only use a word once, or say \text{k}k times, then we would need extra state variables to know what words we are allowed to use at each state.
     *
     * In all the past problems, we had a function \text{dp}dp return the answer to the original problem for some state. We should try to do the same thing here. The problem is asking, is it possible to create \text{s}s by combining words in \text{wordDict}wordDict. So, let's have an array \text{dp}dp where \text{dp[i]}dp[i] represents if it is possible to build the string \text{s}s up to index \text{i}i from \text{wordDict}wordDict. To answer the original problem, we can return \text{dp[s.length - 1]}dp[s.length - 1] after populating \text{dp}dp.
     *
     * 2. A recurrence relation to transition between states
     *
     * At each index \text{i}i, what criteria determines if \text{dp[i]}dp[i] is true? First, a word from \text{wordDict}wordDict needs to be able to end at index \text{i}i. In terms of code, this means that there is some \text{word}word from \text{wordDict}wordDict that matches the substring of \text{s}s that starts at index \text{i - word.length + 1}i - word.length + 1 and ends at index \text{i}i.
     *
     * We can iterate through all states of \text{i}i from \text{0}0 up to but not including \text{s.length}s.length, and at each state, check all the words in \text{wordDict}wordDict for this criteria. For each \text{word}word in \text{wordDict}wordDict, if \text{s}s from index \text{i - word.length + 1}i - word.length + 1 to \text{i}i is equal to \text{word}word, that means \text{word}word ends at \text{i}i. However, this is not the sole criteria.
     *
     * Remember, we are forming \text{s}s by adding words together. That means, if a \text{word}word meets the first criteria and we want to use it in a solution, we would add it on top of another string. We need to make sure that the string before it is also formable. If \text{word}word meets the first criteria, it starts at index \text{i - word.length + 1}i - word.length + 1. The index before that is \text{i - word.length}i - word.length, and the second criteria is that \text{s}s up to this index is also formable from \text{wordDict}wordDict. This gives us our recurrence relation:
     *
     * dp(i) = true if s.substring(i - word.length + 1, i + 1) == word and dp[i - word.length] == true for any word in wordDict, otherwise false
     *
     *
     *
     *
     * In summary, the criteria is:
     *
     * A \text{word}word from \text{wordDict}wordDict can end at the current index \text{i}i.
     *
     * If that \text{word}word is to end at index \text{i}i, then it starts at index \text{i - word.length + 1}i - word.length + 1. The index before that \text{i - word.length}i - word.length should also be formable from \text{wordDict}wordDict.
     *
     * 3. Base cases
     *
     * The base case for this problem is another simple one. The first word used from \text{wordDict}wordDict starts at index \text{0}0, which means we would need to check \text{dp[-1]}dp[-1] for the second criteria, which is out of bounds. To fix this, we say that the second criteria can also be satisfied by \text{i == word.length - 1}i == word.length - 1.
     * @param s
     * @param wordDict
     * @return
     */

        public boolean wordBreak(String s, List<String> wordDict) {
            boolean[] dp = new boolean[s.length()];
            for (int i = 0; i < s.length(); i++) {
                for (String word : wordDict) {
                    // Make sure to stay in bounds while checking criteria
                    if (i >= word.length() - 1 && (i == word.length() - 1 || dp[i - word.length()])) {
                        if (s.substring(i - word.length() + 1, i + 1).equals(word)) {
                            dp[i] = true;
                            break;
                        }
                    }
                }
            }

            return dp[s.length() - 1];
        }


}
