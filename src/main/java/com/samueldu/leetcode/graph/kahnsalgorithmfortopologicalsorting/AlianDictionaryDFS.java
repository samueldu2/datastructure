package com.samueldu.leetcode.graph.kahnsalgorithmfortopologicalsorting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlianDictionaryDFS {
    /**
     * Depth-First Search
     * Intuition
     *
     * Another approach to the third part is to use a depth-first search. We still need to extract relations and then generate an adjacency list in the same way as before, but this time we don't need the indegrees map.
     *
     * Recall that in a depth-first search, nodes are returned once they either have no outgoing links left, or all their outgoing links have been visited. Therefore, the order in which nodes are returned by the depth-first search will be the reverse of a valid alphabet order.
     *
     * Algorithm
     *
     * If we made a reverse adjacency list instead of a forward one, the output order would be correct (without needing to be reversed). Remember that when we reverse the edges of a directed graph, the nodes with no incoming edges became the ones with no outgoing edges. This means that the ones at the start of the alphabet will now be the ones returned first.
     *
     * One issue we need to be careful of is cycles. In directed graphs, we often detect cycles by using graph coloring. All nodes start as white, and then once they're first visited they become grey, and then once all their outgoing nodes have been fully explored, they become black. We know there is a cycle if we enter a node that is currently grey (it works because all nodes that are currently on the stack are grey. Nodes are changed to black when they are removed from the stack).
     *
     * Here is an animation showing the DFS, starting from a reverse adjacency list of the input.
     *
     * Current
     * 32 / 33
     *
     * Complexity Analysis
     *
     * Time complexity : O(C)O(C).
     *
     * Building the adjacency list has a time complexity of O(C)O(C) for the same reason as in Approach 1.
     *
     * Again, like in Approach 1, we traverse every "edge", but this time we're using depth-first-search.
     *
     * Space complexity : O(1)O(1) or O(U + \min(U^2, N))O(U+min(U
     * 2
     *  ,N)).
     *
     * Like in Approach 1, we build an adjacency list. Even though this one is a reversed adjacency list, it still contains the same number of relations.
     */



        private Map<Character, List<Character>> reverseAdjList = new HashMap<>();
        private Map<Character, Boolean> seen = new HashMap<>();
        private StringBuilder output = new StringBuilder();

        public String alienOrder(String[] words) {

            // Step 0: Put all unique letters into reverseAdjList as keys.
            for (String word : words) {
                for (char c : word.toCharArray()) {
                    reverseAdjList.putIfAbsent(c, new ArrayList<>());
                }
            }

            // Step 1: Find all edges and add reverse edges to reverseAdjList.
            for (int i = 0; i < words.length - 1; i++) {
                String word1 = words[i];
                String word2 = words[i + 1];
                // Check that word2 is not a prefix of word1.
                if (word1.length() > word2.length() && word1.startsWith(word2)) {
                    return "";
                }
                // Find the first non match and insert the corresponding relation.
                for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                    if (word1.charAt(j) != word2.charAt(j)) {
                        reverseAdjList.get(word2.charAt(j)).add(word1.charAt(j));
                        break;
                    }
                }
            }

            // Step 2: DFS to build up the output list.
            for (Character c : reverseAdjList.keySet()) {
                boolean result = dfs(c);
                if (!result) return "";
            }

            return output.toString();
        }

        // Return true iff no cycles detected.
        private boolean dfs(Character c) {
            if (seen.containsKey(c)) {
                return seen.get(c); // If this node was grey (false), a cycle was detected.
            }
            seen.put(c, false);
            for (Character next : reverseAdjList.get(c)) {
                boolean result = dfs(next);
                if (!result) return false;
            }
            seen.put(c, true);
            output.append(c);
            return true;
        }

}
