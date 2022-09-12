package com.samueldu.graphtransversal.tree;

import java.util.*;

/**
 * 1202. Smallest String With Swaps
 * Medium
 *
 * 3039
 *
 * 102
 *
 * Add to List
 *
 * Share
 * You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.
 *
 * You can swap the characters at any pair of indices in the given pairs any number of times.
 *
 * Return the lexicographically smallest string that s can be changed to after using the swaps.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "dcab", pairs = [[0,3],[1,2]]
 * Output: "bacd"
 * Explaination:
 * Swap s[0] and s[3], s = "bcad"
 * Swap s[1] and s[2], s = "bacd"
 * Example 2:
 *
 * Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
 * Output: "abcd"
 * Explaination:
 * Swap s[0] and s[3], s = "bcad"
 * Swap s[0] and s[2], s = "acbd"
 * Swap s[1] and s[2], s = "abcd"
 * Example 3:
 *
 * Input: s = "cba", pairs = [[0,1],[1,2]]
 * Output: "abc"
 * Explaination:
 * Swap s[0] and s[1], s = "bca"
 * Swap s[1] and s[2], s = "bac"
 * Swap s[0] and s[1], s = "abc"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * 0 <= pairs.length <= 10^5
 * 0 <= pairs[i][0], pairs[i][1] < s.length
 * s only contains lower case English letters.
 *
 *
 */
public class SmallestStringWithSwaps {
    /**
     * DFS:
     *
     * * Approach 1: Depth-First Search (DFS)
     *  * Intuition
     *  *
     *  * If you're not familiar with DFS, check out our Explore Card.
     *  *
     *  * We will build the adjacency list using the pairs given i.e., for each pair (x, y) we will add an edge from x to y and from y to x. Then we will iterate over the indices from 0 to n-1 where n is the length of the given string s. For each index, if it has not been visited yet, we will perform a DFS and store the vertices (index) and the characters at these indices in a list. Each list will represent a different component in the graph. Then we will sort each list of indices and each list of characters and place the i_{th}i
     *  * th
     *  * ​
     *  *   character at the i_{th}i
     *  * th
     *  * ​
     *  *   index in the string smallestString.
     *  *
     *  * Algorithm
     *  *
     *  * Iterate over the pairs and create an adjacency list such that adj[source] contains all the adjacent vertices of vertex source.
     *  * Iterate over the indices from 0 to s.size() - 1. For each index vertex we will:
     *  * Perform DFS if vertex is not visited yet (visited[vertex] is false)
     *  * While performing DFS, store vertex in the list indices and the character s[vertex] in the list characters.
     *  * Sort the lists indices and characters.
     *  * Iterate over indices and characters, and place the i_{th}i
     *  * th
     *  * ​
     *  *   character at the i_{th}i
     *  * th
     *  * ​
     *  *   index in the string smallestString.
     *  * Return smallestString.
     *  * Complexity Analysis
     *  *
     *  * Here, VV represents the number of vertices (the length of the given string) and EE represents the number of edges (the number of pairs).
     *  *
     *  * Time complexity: O(E + V \log V)O(E+VlogV)
     *  *
     *  * Building the adjacency list will take O(E)O(E) operations, as we iterate over the list of pairs once, and inserting an element into the adjacency list takes O(1)O(1) time.
     *  *
     *  * During the DFS traversal, each vertex will only be visited once. This is because we mark each vertex as visited as soon as we see it, and then we only visit vertices that are not marked as visited. When we iterate over the edge list of each vertex, we look at each edge once. This has a total cost of O(V + E)O(V+E).
     *  *
     *  * Additionally, we also sort the list indices and characters for each component. In the worst case, all of the vertices in the graph belong to the same component. In that case, sorting two lists of VV elements will take O(V \log V)O(VlogV) time. Hence the total time complexity is equal to O(E + V \log V)O(E+VlogV).
     *  *
     *  * Space complexity: O(E + V)O(E+V)
     *  *
     *  * Building the adjacency list will take O(E)O(E) space. To track the visited vertices, an array visited of size O(V)O(V) is required. In the worst case, indices and characters can take O(V)O(V) space. Also, the run-time stack for DFS will use O(V)O(V) space i.e., one active function call for each vertex.
     *  *
     *  * Additional space is used for sorting the lists indices and characters. The space complexity of the sorting algorithm is language-specific. For instance, in Java, the Arrays.sort() for primitives is implemented as a variant of quicksort algorithm whose space complexity is O(\log V)O(logV). In C++ sort() function provided by STL is a hybrid of Quick Sort, Heap Sort, and Insertion Sort and has a worst-case space complexity of O(\log V)O(logV). Thus, using the inbuilt sort() function might add up to O(\log V)O(logV) to space complexity.
     *  *
     *  * The total space required is (E + V + \log V)(E+V+logV) and hence, the space complexity is equal to O(E + V)O(E+V).
     *
     */
        // Maximum number of vertices
        final static int N = 100001;
        boolean[] visited = new boolean[N];
        List<Integer>[] adj = new ArrayList[N];

        private void DFS(String s, int vertex, List<Character> characters, List<Integer> indices) {
            // Add the character and index to the list
            characters.add(s.charAt(vertex));
            indices.add(vertex);

            visited[vertex] = true;

            // Traverse the adjacents
            for (int adjacent : adj[vertex]) {
                if (!visited[adjacent]) {
                    DFS(s, adjacent, characters, indices);
                }
            }
        }

        public String smallestStringWithSwapsDFS(String s, List<List<Integer>> pairs) {
            for (int i = 0; i < s.length(); i++) {
                adj[i] = new ArrayList<Integer>();
            }

            // Build the adjacency list
            for (List<Integer> edge : pairs) {
                int source = edge.get(0);
                int destination = edge.get(1);

                // Undirected edge
                adj[source].add(destination);
                adj[destination].add(source);
            }

            char[] answer = new char[s.length()];
            for (int vertex = 0; vertex < s.length(); vertex++) {
                // If not covered in the DFS yet
                if (!visited[vertex]) {
                    List<Character> characters = new ArrayList<>();
                    List<Integer> indices = new ArrayList<>();

                    DFS(s, vertex, characters, indices);
                    // Sort the list of characters and indices
                    Collections.sort(characters);
                    Collections.sort(indices);

                    // Store the sorted characters corresponding to the index
                    for (int index = 0; index < characters.size(); index++) {
                        answer[indices.get(index)] = characters.get(index);
                    }
                }
            }
            return new String(answer);
        }

    /**
     * Union Find
     *
     * Approach 2: Disjoint Set Union (DSU)
     * Intuition
     *
     * Remember, our first task is to determine which indices belong to the same connected component. In this approach, we will use the Union-Find data structure to accomplish this.
     *
     * If you're not familiar with DSU, check out our Explore Card.
     *
     * First, we will union all vertices that share an edge (vertices a and b share an edge if (a, b) or (b, a) exists in pairs). After which, all vertices with the same root will belong to the same component. This way, by looking at the root node for each vertex, we can put the vertices and the characters at these vertices (indices) in separate lists corresponding to the component they belong to. Then, similar to the previous approach, we will sort the list of characters that belong to the same component and place the i_{th}i
     * th
     * ​
     *   character at the i_{th}i
     * th
     * ​
     *   index in a string smallestString.
     *
     * Note that we don't need to sort the list of indices in this approach because, as we iterate over vertices in ascending order, we will store the vertices that belong to the same component in ascending order.
     *
     * Algorithm
     *
     * Iterate over the pairs, for each pair (a, b) perform the union operation for vertices a and b.
     * Iterate over the indices from 0 to s.size() - 1. For each index (vertex) we will:
     * Perform the find operation on vertex to find the root.
     * Store the vertex in the list corresponding to root in the HashMap rootToComponent.
     * Iterate over each list in the HashMap rootToComponent:
     * For each list indices, iterate over the list and for each element store the corresponding character in s in the list of characters (characters). Here, each element in indices represents an index in s and each character in characters represents the characters at this index in s.
     * Sort the list and characters.
     * Iterate over the lists indices and characters, place the i_{th}i
     * th
     * ​
     *   character at the i_{th}i
     * th
     * ​
     *   index in the string smallestString.
     * Return smallestString.
     *
     *
     */

        public String smallestStringWithSwapsUnionFind(String s, List<List<Integer>> pairs) {
            UnionFind uf = new UnionFind(s.length());

            // Iterate over the edges
            for (List<Integer> edge : pairs) {
                int source = edge.get(0);
                int destination = edge.get(1);

                // Perform the union of end points
                uf.union(source, destination);
            }

            Map<Integer, List<Integer>> rootToComponent = new HashMap<>();
            // Group the vertices that are in the same component
            for (int vertex = 0; vertex < s.length(); vertex++) {
                int root = uf.find(vertex);
                // Add the vertices corresponding to the component root
                rootToComponent.putIfAbsent(root, new ArrayList<>());
                rootToComponent.get(root).add(vertex);
            }

            // String to store the answer
            char[] smallestString = new char[s.length()];
            // Iterate over each component
            for (List<Integer> indices : rootToComponent.values()) {
                // Sort the characters in the group
                List<Character> characters = new ArrayList<>();
                for (int index : indices) {
                    characters.add(s.charAt(index));
                }
                Collections.sort(characters);

                // Store the sorted characters
                for (int index = 0; index < indices.size(); index++) {
                    smallestString[indices.get(index)] = characters.get(index);
                }
            }

            return new String(smallestString);
        }
    }




