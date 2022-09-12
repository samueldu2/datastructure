package com.samueldu.graphtransversal.tree;

import java.util.*;

public class ValidateTree {
    public boolean validTreeTrackParentChildMapping(int n, int[][] edges) {

        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }

        Map<Integer, Integer> parent = new HashMap<>();
        parent.put(0, -1);
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        while (!stack.isEmpty()) {
            int node = stack.pop();
            for (int neighbour : adjacencyList.get(node)) {
                /**
                 * we are navigating from node parent to neighbor child now,
                 * but previously neighbor is node's parent. we therefore ignore it to avoid trivial cycles of A-->B--A
                  */
                if (parent.get(node) == neighbour) {
                    continue;
                }
                if (parent.containsKey(neighbour)) {
                    return false;
                }
                stack.push(neighbour);
                parent.put(neighbour, node);// navigated from node to neighbor, child <-->parent mapping.
            }
        }

        return parent.size() == n;
    }

    public boolean validTreeRemoveReverseLink(int n, int[][] edges) {

        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }


        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        Set<Integer> seen = new HashSet<>();
        seen.add(0);

        // While there are nodes remaining on the stack...
        while (!stack.isEmpty()) {
            int node = stack.pop(); // Take one off to visit.
            // Check for unseen neighbours of this node:
            for (int neighbour : adjacencyList.get(node)) {
                // Check if we've already seen this node.
                if (seen.contains(neighbour)) {
                    return false;
                }
                // Otherwise, put this neighbour onto stack
                // and record that it has been seen.
                stack.push(neighbour);
                seen.add(neighbour);
                // Remove the link that goes in the opposite direction.
                adjacencyList.get(neighbour).remove(node);
            }
        }

        // Return true iff the depth first search discovered ALL nodes.
        return seen.size() == n;
    }

    private List<List<Integer>> adjacencyList = new ArrayList<>();
    private Set<Integer> seen = new HashSet<>();
    public boolean validTreeDFS(int n, int[][] edges) {

        if (edges.length != n - 1) return false;

        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }

        // We return true iff no cycles were detected,
        // AND the entire graph has been reached.
        return dfs(0, -1) && seen.size() == n;
    }

    public boolean dfs(int node, int parent) {
        if (seen.contains(node)) return false;
        seen.add(node);
        for (int neighbour : adjacencyList.get(node)) {
            if (parent != neighbour) {
                boolean result = dfs(neighbour, node);
                if (!result) return false;
            }
        }
        return true;
    }
}
