package com.samueldu.leetcode.dynamicprogramming.commonpatterns;

import java.util.Arrays;
import java.util.List;

public class WordBreakTopDown {
    /**
     * Top-down Implementation
     * In the top-down approach, we can check for the base case by returning \text{true}true if \text{i < 0}i < 0. In Java, we will memoize by using a \text{-1}-1 to indicate that the state is unvisited, \text{0}0 to indicate \text{false}false, and \text{1}1 to indicate \text{true}true.
     *
     *
     * Let's say that \text{n = s.length}n = s.length, \text{k = wordDict.length}k = wordDict.length, and \text{L}L is the average length of the words in \text{wordDict}wordDict. While the space complexity for this problem is the same as the number of states \text{n}n, the time complexity is much worse. At each state \text{i}i, we iterate through \text{wordDict}wordDict and splice \text{s}s to a new string with average length \text{L}L. This gives us a time complexity of O(n \cdot k \cdot L)O(n⋅k⋅L).
     */
        private String s;
        private List<String> wordDict;
        private int[] memo;

        private boolean dp(int i) {
            if (i < 0) return true;

            if (memo[i] == -1) {
                for (String word: wordDict) {
                    if (i >= word.length() - 1 && dp(i - word.length())) {
                        if (s.substring(i - word.length() + 1, i + 1).equals(word)) {
                            memo[i] = 1;//1==>true, matched.
                            break;
                        }
                    }
                }
            }

            if (memo[i] == -1) {//-1: unvisidited
                memo[i] = 0;//0: false, not matching.
            }

            return memo[i] == 1;
        }

        public boolean wordBreak(String s, List<String> wordDict) {
            this.s = s;
            this.wordDict = wordDict;
            this.memo = new int[s.length()];
            Arrays.fill(this.memo, -1);
            return dp(s.length() - 1);
        }

}
