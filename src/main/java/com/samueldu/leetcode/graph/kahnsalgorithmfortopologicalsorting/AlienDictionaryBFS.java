package com.samueldu.leetcode.graph.kahnsalgorithmfortopologicalsorting;

import java.util.*;

/**
 * Alien Dictionary
 *
 * Solution
 * There is a new alien language that uses the English alphabet. However, the order among the letters is unknown to you.
 *
 * You are given a list of strings words from the alien language's dictionary, where the strings in words are sorted lexicographically by the rules of this new language.
 *
 * Return a string of the unique letters in the new alien language sorted in lexicographically increasing order by the new language's rules. If there is no solution, return "". If there are multiple solutions, return any of them.
 *
 * A string s is lexicographically smaller than a string t if at the first letter where they differ, the letter in s comes before the letter in t in the alien language. If the first min(s.length, t.length) letters are the same, then s is smaller if and only if s.length < t.length.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["wrt","wrf","er","ett","rftt"]
 * Output: "wertf"
 * Example 2:
 *
 * Input: words = ["z","x"]
 * Output: "zx"
 * Example 3:
 *
 * Input: words = ["z","x","z"]
 * Output: ""
 * Explanation: The order is invalid, so return "".
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 100
 * words[i] consists of only lowercase English letters.
 */
public class AlienDictionaryBFS {
    /**
     * Approach 1: Breadth-First Search
     * Intuition
     *
     * There are three parts to this problem.
     *
     * Getting as much information about the alphabet order as we can out of the input word list.
     * Representing that information in a meaningful way.
     * Assembling a valid alphabet ordering.
     * Part 1: Extracting Information
     *
     * Let's start with a large example of a dictionary in an "alien language", and see how much we can conclude with some simple reasoning. This is likely to be your first step in tackling this question in a programming interview.
     *
     * List of alien words:  wxqkj, whqg, cckgh, cdxg, cdxdt, cdht, ktgxt, ktgch, ktdw, ktdc, jqw, jmc, jmg
     *
     * Remember that in an ordinary English dictionary, all the words starting with a are at the start, followed by all the ones starting with b, then c, d, e, and at the very end, z. In the "alien dictionary", we also expect the first letters of each word to be in alphabetical order. So, let's look at them.
     *
     * First letters of each word: w, w, c, c, c, c, k, k, k, k, j, and j
     *
     * Removing the duplicates, we get:
     *
     * First letters of each word without duplicates: w, c, k, and j
     *
     * Going by this, we know the relative order of four letters in the "alien dictionary". However, we don't know how these four letters fit in with the other seven letters, or even how those other seven letters fit in with each other. To get more information, we'll need to look further.
     *
     * Going back to the English dictionary analogy, the word abacus will appear before algorithm. This is because when the first letter of two words is the same, we instead look at the second letter; b and l in this case. b is before l in the alphabet.
     *
     * Let's take a closer look at the first two words of the "alien dictionary"; wxqkj and whgg. Seeing as the first letters are the same, w, we look at the second letters. The first word has x, and the second word has h. Therefore, we know that x is before h in the alien dictionary. We know have two fragments of the letter-order.
     *
     * First two sequences, w, c, k, j and x, h
     *
     * We don't know yet how these two fragments could fit together into a single ordering. For example, we don't know whether w is before x, or x is before w, or even whether or not there's enough information available in the input for us to know.
     *
     * Anyway, we've now gotten all the information we can out of the first two words. All letters after x in wxqkj, and after h in whqg, should be ignored because they did not impact the relative ordering of the two words (if you're confused, think back to abacus and algorithm. Because b > l, the gorithm and acus parts are unimportant for determining alphabetical ordering).
     *
     * Hopefully, you're starting to see a pattern here. Where two words are adjacent, we need to look for the first difference between them. That difference tells us the relative order between two letters. Let's have a look at all the relations we can find by comparing adjacent words.
     *
     * Relations between words
     *
     * You might notice that we haven't included some rules, such as w → j. This is fine though, as we can still derive it from the rules we have: w → c, c → k, k → j.
     *
     * This completes the first part. There is no further information we can extract from the input. Therefore, our task is now to put together what we know.
     *
     * Part 2: Representing the Relations
     *
     * We now have a set of relations stating how pairs of letters are ordered relative to each other.
     *
     * Relations: x → h, w → c, c → d, g → d, c → k, x → c, k → j, q → m, and c → g
     *
     * How could we put these together? You may be tempted to start trying to build "chains" out of them. Here are a few possible chains.
     *
     * Some combined chains: w→c→k→j, w→c→d, x→c→k→j, and q→m
     *
     * The problem here though is that some letters are in more than one chain. Simply putting the chains into the output list one-after-the-other won't work. Some letters will be duplicated, which invalidates the ordering. Simply deleting the duplicates will not work either.
     *
     * When we have a set of relations, often drawing a graph is the best way to visualize them. The nodes are the letters, and an edge between two letters, A and B represents that A is before B in the "alien alphabet".
     *
     * Graph with sources highlighted
     *
     * Part 3: Assembling a Valid Ordering
     *
     * As we can see from the graph, four of the letters have no arrows going into them. What this means is that there are no letters that have to be before any of these four (remember that the question states there could be multiple valid orderings, and if there are, then it's fine for us to return any of them).
     *
     * Therefore, a valid start to the ordering we return would be:
     *
     * First group ordering: q w t x
     *
     * We can now remove these letters and edges from the graph, because any other letters that required them first will now have this requirement satisfied.
     *
     * Graph after first step with new sources highlighted
     *
     * On this new graph, there are now three new letters that have no in-arrows. We can add these to our output list.
     *
     * Two groups ordering: q w t x, m h c
     *
     * Again, we can remove these from the graph.
     *
     * Graph after second step with new sources highlighted
     *
     * Then add the two new letters with no in-arrows.
     *
     * Three groups ordering: q w t x, m h c, g k
     *
     * Which leaves the following graph.
     *
     * Graph after third step with new sources highlighted
     *
     * With the final two letters.
     *
     * All groups ordering: q w t x, m h c, g k, j d
     *
     * Which is a valid ordering that we can return.
     *
     * As a side note, each of the four groups of letters we picked off could have been in any order within themselves (as another side note, it's not too difficult to calculate how many valid orderings there are. Have a think about this if you want, determining how many valid alphabet orderings there are is an interesting follow-up question!)
     *
     * Algorithm
     *
     * Now that we have come up with an approach, we need to figure out how to implement it efficiently.
     *
     * The first and second parts are straightforward; we'll leave you to look at the code for these. It should extract the order relations and then insert them into an adjacency list. The only thing we need to be careful of is ensuring that we've handled the "prefix" edge case correctly.
     *
     * Adjacency list
     *
     * The third part is more complicated. We need to somehow identify which letters have no incoming links left. With the adjacency list format above, this is a bit annoying to do, because determining whether or not a particular letter has any incoming links requires repeatedly checking over the adjacency lists of all the other letters to see whether or not they feature that letter.
     *
     * A naïve solution would be to do exactly this. While this would be efficient enough with at most 26 letters, it may result in your interviewer quickly telling you that we might want to use the same algorithm on an "alien language" with millions of unique letters.
     *
     * An alternative is to keep two adjacency lists; one the same as above, and another that is the reverse, showing the incoming links. Then, each time we traverse an edge, we could remove the corresponding edge in the reverse adjacency list. Seeing when a letter has no more incoming links would now be straightforward.
     *
     * Reverse adjacency list
     *
     * However, we can do even better than that. Instead of keeping track of all the other letters that must be before a particular letter, we only need to keep track of how many of them there are! While building the forward adjacency list, we can also count up how many incoming edges each letter has. We call the number of incoming edges the indegree of a node.
     *
     * Count list
     *
     * Then, instead of removing an edge from a reverse adjacency list, we can simply decrement the count by 1. Once the count reaches 0, this is equivalent to there being no incoming edges left in the reverse adjacency list.
     *
     * We'll do a BFS for all letters that are reachable, adding each letter to the output as soon as it's reachable. A letter is reachable once all of the letters that need to be before it have been added to the output. To do a BFS, recall that we use a queue. We should initially put all letters with an in-degree of 0 onto that queue. Each time a letter gets down to an in-degree of 0, it is added to the queue.
     *
     * We continue this until the queue is empty. After that, we check whether or not all letters were put in the output list. If some are missing, this is because we got to a point where all remaining letters had at least one edge going in; this means there must be a cycle! In that case, we should return "" as per the problem description. Otherwise, we should return the complete ordering we found.
     *
     * One edge case we need to be careful of is where a word is followed by its own prefix. In these cases, it is impossible to come up with a valid ordering and so we should return "". The best place to detect it is in the loop that compares each adjacent pair of words.
     *
     * Also, remember that not all letters will necessarily have an edge going into or out of them. These letters can go anywhere in the output. But we need to be careful to not forget about them in our implementation.
     *
     * Here is an animation showing the entire algorithm on our example from earlier.
     *
     * Current
     * 22 / 22
     *
     * Complexity Analysis
     *
     * Let NN be the total number of strings in the input list.
     *
     * Let CC be the total length of all the words in the input list, added together.
     *
     * Let UU be the total number of unique letters in the alien alphabet. While this is limited to 2626 in the question description, we'll still look at how it would impact the complexity if it was not limited (as this could potentially be a follow-up question).
     *
     * Time complexity : O(C)O(C).
     *
     * There were three parts to the algorithm; identifying all the relations, putting them into an adjacency list, and then converting it into a valid alphabet ordering.
     *
     * In the worst case, the first and second parts require checking every letter of every word (if the difference between two words was always in the last letter). This is O(C)O(C).
     *
     * For the third part, recall that a breadth-first search has a cost of O(V + E)O(V+E), where VV is the number of vertices and EE is the number of edges. Our algorithm has the same cost as BFS, as it too is visiting each edge and node once (a node is visited once all of its edges are visited, unlike the traditional BFS where it is visited once one edge is visited). Therefore, determining the cost of our algorithm requires determining how many nodes and edges there are in the graph.
     *
     * Nodes: We know that there is one vertex for each unique letter, i.e. O(U)O(U) vertices.
     *
     * Edges: Each edge in the graph was generated from comparing two adjacent words in the input list. There are N - 1N−1 pairs of adjacent words, and only one edge can be generated from each pair. This might initially seem a bit surprising, so let's quickly look at an example. We'll use English words.
     *
     * abacus
     * algorithm
     * The only conclusion we can draw is that b is before l. This is the reason abacus appears before algorithm in an English dictionary. The characters afterward are irrelevant. It is the same for the "alien" alphabets we're working with here. The only rule we can draw is the one based on the first difference between the two words.
     *
     * Also, remember that we are only generating rules for adjacent words. We are not adding the "implied" rules to the adjacency list. For example, assume we have the following word list.
     *
     * rgh
     * xcd
     * tny
     * bcd
     * We are only generating the following 3 edges.
     *
     * r -> x
     * x -> t
     * t -> b
     * We are not generating these implied rules (the graph structure shows them indirectly).
     *
     * r -> t
     * r -> b
     * x -> b
     * So with this, we know that there are at most N - 1 edges.
     *
     * There is an additional upper limit on the number of edges too—it is impossible for there to be more than one edge between each pair of nodes. With UU nodes, this means there can't be more than U^2U
     * 2
     *   edges.
     *
     * It's not common in complexity analysis that we get two separate upper bounds like this. Because the number of edges has to be lower than both N - 1N−1 and U^2U
     * 2
     *  , we know it is at most the smallest of these two values. Mathematically, we can say this is \min(U^2, N - 1)min(U
     * 2
     *  ,N−1).
     *
     * Going all the way back to the cost of breadth first search, we can now substiute in the number of nodes and the number of edges: V = UV=U and E = \min(U^2, N - 1)E=min(U
     * 2
     *  ,N−1). This gives us:
     *
     * O(V + E) = O(U + \min(U^2, N - 1)) = O(U + \min(U^2, N))O(V+E)=O(U+min(U
     * 2
     *  ,N−1))=O(U+min(U
     * 2
     *  ,N)).
     *
     * Finally, we need to combine the two parts: O(C)O(C) for the first and second parts, and O(U + \min(U^2, N))O(U+min(U
     * 2
     *  ,N)) for the third part. When we have two independent parts, we add the costs together, as we don't necessarily know which is larger. After we've done this, we should look at the final formula and see whether or not we can actually draw any conclusions about which is larger. Adding them together, we initially get the following:
     *
     * O(C) + O(U + \min(U^2, N)) = O(C + U + \min(U^2, N))O(C)+O(U+min(U
     * 2
     *  ,N))=O(C+U+min(U
     * 2
     *  ,N)).
     *
     * So, what do we know about the relative values of NN, CC, and UU? Well, we know that N < CN<C, as each word contains at least one character (remember, CC is total characters across the words, not unique characters). Additionally, U < CU<C because there can't be more unique characters than there are characters.
     *
     * In summary, CC is the biggest of the three, and NN and UU are smaller, although we don't know which is smaller out of those two.
     *
     * So for starters, we know that the UU bit is insignificant compared to the CC. Therefore, we can just remove it:
     *
     * O(C + U + \min(U^2, N)) → O(C + \min(U^2, N))O(C+U+min(U
     * 2
     *  ,N))→O(C+min(U
     * 2
     *  ,N))
     *
     * Now, to simplify the rest, consider two cases:
     *
     * If U^2U
     * 2
     *   is smaller than NN, then \min(U^2, N) = U^2min(U
     * 2
     *  ,N)=U
     * 2
     *  . By definition, we've just said that U^2U
     * 2
     *   is smaller than NN, which is in turn smaller than CC, and so U^2U
     * 2
     *   is definitely less than CC. This leaves us with O(C)O(C).
     *
     * If U^2U
     * 2
     *   is larger than NN, then \min(U^2, N) = Nmin(U
     * 2
     *  ,N)=N. Because C > NC>N, we're left with O(C)O(C).
     *
     *
     * So in all cases, we know that C > \min(U^2, N)C>min(U
     * 2
     *  ,N). This gives us a final time complexity of O(C)O(C).
     *
     * Space complexity : O(1)O(1) or O(U + \min(U^2, N))O(U+min(U
     * 2
     *  ,N)).
     *
     * The adjacency list uses the most auxiliary memory. This list uses O(V + E)O(V+E) memory, where VV is the number of unique letters, and EE is the number of relations.
     *
     * The number of vertices is simply UU; the number of unique letters.
     *
     * The number of edges in the worst case is \min(U^2, N)min(U
     * 2
     *  ,N), as explained in the time complexity analysis.
     *
     * So in total the adjacency list takes O(U + \min(U^2, N))O(U+min(U
     * 2
     *  ,N)) space.
     *
     * So for the question we're given, where UU is a constant fixed at a maximum of 2626, the space complexity is simply O(1)O(1). This is because UU is fixed at 2626, and the number of relations is fixed at 26^226
     * 2
     *  , so O(\min(26^2, N)) = O(26^2) = O(1)O(min(26
     * 2
     *  ,N))=O(26
     * 2
     *  )=O(1).
     *
     * But when we consider an arbitrarily large number of possible letters, we use the size of the adjacency list; O(U + \min(U^2, N))O(U+min(U
     * 2
     *  ,N)).
     * @param words
     * @return
     */
    public String alienOrder(String[] words) {

        // Step 0: Create data structures and find all unique letters.
        Map<Character, List<Character>> adjList = new HashMap<>();
        Map<Character, Integer> counts = new HashMap<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                counts.put(c, 0);
                adjList.put(c, new ArrayList<>());
            }
        }

        // Step 1: Find all edges.
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
                    adjList.get(word1.charAt(j)).add(word2.charAt(j));
                    counts.put(word2.charAt(j), counts.get(word2.charAt(j)) + 1);
                    break;
                }
            }
        }

        // Step 2: Breadth-first search.
        StringBuilder sb = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();
        for (Character c : counts.keySet()) {
            if (counts.get(c).equals(0)) {
                queue.add(c);
            }
        }
        while (!queue.isEmpty()) {
            Character c = queue.remove();
            sb.append(c);
            for (Character next : adjList.get(c)) {
                counts.put(next, counts.get(next) - 1);
                if (counts.get(next).equals(0)) {
                    queue.add(next);
                }
            }
        }

        if (sb.length() < counts.size()) {
            return "";
        }
        return sb.toString();
    }

}
