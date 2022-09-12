package com.samueldu.graphtransversal.khanalgorithmfortopologicalsorting;

import java.util.*;

public class SourceScheduleIIIndegrees {

    /**
     * Approach 2: Using Node Indegree
     * Intuition
     *
     * This approach is much easier to think about intuitively as will be clear from the following point/fact about topological ordering.
     *
     * The first node in the topological ordering will be the node that doesn't have any incoming edges. Essentially, any node that has an in-degree of 0 can start the topologically sorted order. If there are multiple such nodes, their relative order doesn't matter and they can appear in any order.
     *
     * Our current algorithm is based on this idea. We first process all the nodes/course with 0 in-degree implying no prerequisite courses required. If we remove all these courses from the graph, along with their outgoing edges, we can find out the courses/nodes that should be processed next. These would again be the nodes with 0 in-degree. We can continuously do this until all the courses have been accounted for.
     *
     * Algorithm
     *
     * Initialize a queue, Q to keep a track of all the nodes in the graph with 0 in-degree.
     * Iterate over all the edges in the input and create an adjacency list and also a map of node v/s in-degree.
     * Add all the nodes with 0 in-degree to Q.
     * The following steps are to be done until the Q becomes empty.
     * Pop a node from the Q. Let's call this node, N.
     * For all the neighbors of this node, N, reduce their in-degree by 1. If any of the nodes' in-degree reaches 0, add it to the Q.
     * Add the node N to the list maintaining topologically sorted order.
     * Continue from step 4.1.
     * Let us now look at an animation depicting this algorithm and then we will get to the implementations.
     *
     * Current
     * 1 / 14
     * An important thing to note here is, using a queue is not a hard requirement for this algorithm. We can make use of a stack. That however, will give us a different ordering than what we might get from the queue because of the difference in access patterns between the two data-structures.
     * @param numCourses
     * @param prerequisites
     * @return
     *
     * Complexity Analysis
     *
     * Time Complexity: O(V + E)O(V+E) where VV represents the number of vertices and EE represents the number of edges. We pop each node exactly once from the zero in-degree queue and that gives us VV. Also, for each vertex, we iterate over its adjacency list and in totality, we iterate over all the edges in the graph which gives us EE. Hence, O(V + E)O(V+E)
     *
     * Space Complexity: O(V + E)O(V+E). We use an intermediate queue data structure to keep all the nodes with 0 in-degree. In the worst case, there won't be any prerequisite relationship and the queue will contain all the vertices initially since all of them will have 0 in-degree. That gives us O(V)O(V). Additionally, we also use the adjacency list to represent our graph initially. The space occupied is defined by the number of edges because for each node as the key, we have all its adjacent nodes in the form of a list as the value. Hence, O(E)O(E). So, the overall space complexity is O(V + E)O(V+E).
     */
        public int[] findOrder(int numCourses, int[][] prerequisites) {

            boolean isPossible = true;
            Map<Integer, List<Integer>> adjList = new HashMap<Integer, List<Integer>>();
            int[] indegree = new int[numCourses];
            int[] topologicalOrder = new int[numCourses];

            // Create the adjacency list representation of the graph
            for (int i = 0; i < prerequisites.length; i++) {
                int dest = prerequisites[i][0];
                int src = prerequisites[i][1];
                List<Integer> lst = adjList.getOrDefault(src, new ArrayList<Integer>());
                lst.add(dest);
                adjList.put(src, lst);

                // Record in-degree of each vertex
                indegree[dest] += 1;
            }

            // Add all vertices with 0 in-degree to the queue
            Queue<Integer> q = new LinkedList<Integer>();
            for (int i = 0; i < numCourses; i++) {
                if (indegree[i] == 0) {
                    q.add(i);
                }
            }

            int i = 0;
            // Process until the Q becomes empty
            while (!q.isEmpty()) {
                int node = q.remove();
                topologicalOrder[i++] = node;

                // Reduce the in-degree of each neighbor by 1
                if (adjList.containsKey(node)) {
                    for (Integer neighbor : adjList.get(node)) {
                        indegree[neighbor]--;

                        // If in-degree of a neighbor becomes 0, add it to the Q
                        if (indegree[neighbor] == 0) {
                            q.add(neighbor);
                        }
                    }
                }
            }

            // Check to see if topological sort is possible or not.
            if (i == numCourses) {
                return topologicalOrder;
            }

            return new int[0];
        }

}
