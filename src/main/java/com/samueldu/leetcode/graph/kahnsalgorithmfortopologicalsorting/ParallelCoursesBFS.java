package com.samueldu.leetcode.graph.kahnsalgorithmfortopologicalsorting;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given an integer n, which indicates that there are n courses labeled from 1 to n. You are also given an array relations where relations[i] = [prevCoursei, nextCoursei], representing a prerequisite relationship between course prevCoursei and course nextCoursei: course prevCoursei has to be taken before course nextCoursei.
 *
 * In one semester, you can take any number of courses as long as you have taken all the prerequisites in the previous semester for the courses you are taking.
 *
 * Return the minimum number of semesters needed to take all courses. If there is no way to take all the courses, return -1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 3, relations = [[1,3],[2,3]]
 * Output: 2
 * Explanation: The figure above represents the given graph.
 * In the first semester, you can take courses 1 and 2.
 * In the second semester, you can take course 3.
 * Example 2:
 *
 *
 * Input: n = 3, relations = [[1,2],[2,3],[3,1]]
 * Output: -1
 * Explanation: No course can be studied because they are prerequisites of each other.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 5000
 * 1 <= relations.length <= 5000
 * relations[i].length == 2
 * 1 <= prevCoursei, nextCoursei <= n
 * prevCoursei != nextCoursei
 * All the pairs [prevCoursei, nextCoursei] are unique.
 *    Hide Hint #1
 * Try to think of it as a graph problem. It will be impossible to study all the courses if the graph had a cycle.
 *    Hide Hint #2
 * The graph is a directed acyclic graph (DAG). The answer is the longes path in this DAG.
 *    Hide Hint #3
 * You can use DP to find the longest path in the DAG.
 */
public class ParallelCoursesBFS {
/**
 * Kahn's Algorithm with Topological Sorting
 * Approach 1: Breadth-First Search (Kahn's Algorithm)
 * Intuition
 *
 * We can treat the problem as a directed graph problem (the courses are nodes and the prerequisites are egdes). What we need to do is somehow iterate over all the nodes in the graph.
 *
 * For iteration, we can do BFS or DFS. We introduce BFS in this approach and DFS in the following approaches.
 *
 * To achieve the fastest learning speed, our strategy is:
 *
 * Learn all courses available in each semester.
 *
 * This is intuitive. Even if we deliberately choose not to learn one available course, we still need to learn it in the following semesters. There is no harm to learn it now. Also, if we learn it later, then we have to postpone all courses whose prerequisite is that course.
 *
 * Now, the first question is:
 *
 * Where to start? (Which courses are available?)
 *
 * We can not start from courses with prerequisites.
 *
 * We start from nodes with no prerequisites.
 *
 * For example, in this graph, which courses can we learn in the first semester?
 *
 * Figure 1.1
 *
 * Yes, those courses marked with yellow can be learned in the first semester.
 *
 * Figure 1.2
 *
 * Now, we have learned those courses, what should we learn next?
 *
 * Figure 1.3
 *
 * Yes, the new yellow courses can be learned, since their prerequisites are fulfilled:
 *
 * Figure 1.4
 *
 * Keep going until no available courses to learn.
 *
 * By using this strategy to allocate courses to semesters, we are guaranteed to minimize the number of semesters needed. This is because in each semester, we're learning every course that isn't "locked" by a prerequisite, and so there is no possible way to be faster.
 *
 * Let's finish this example with Breadth-First Search:
 *
 * Current
 * 6 / 6
 * In some other cases, we can not learn all nodes. If the number of nodes we visited is strictly less than the number of total nodes, then there is not way to learn all the courses and we can do nothing but return -1.
 *
 * For example, in this graph with a cycle, we can not learn all the courses:
 *
 * Current
 * 2 / 2
 * This approach is also called Kahn's algorithm (with some modifications to adapt to the problem).
 *
 * Algorithm
 *
 * Step 1: Build a directed graph from relations.
 *
 * Step 2: Record the in-degree of each node. (i.e., the number of edges towards the node)
 *
 * Step 3: Initialize a queue, queue. Put nodes with an in-degree of 0 into queue. Initialize step = 0, visited_count = 0.
 *
 * Step 4: Start BFS: Loop until queue is empty:
 *
 * Initialize a queue next_queueto record the nodes needed in the next iteration.
 * Increment step.
 * For each node in queue:
 * Increment visitedCount
 * For each end_node reachable from node:
 * Decrement the in-degree of end_node
 * If the in-degree of end_node reaches 0, push it into next_queue
 * Assign queue to next_queue
 * Step 5: If visited_count == N, return step. Otherwise, return -1.
 */

    public int minimumSemesters(int N, int[][] relations) {
        int[] inCount = new int[N + 1]; // or indegree
        List<List<Integer>> graph = new ArrayList<>(N + 1);
        for (int i = 0; i < N + 1; ++i) {
            graph.add(new ArrayList<Integer>());
        }
        for (int[] relation : relations) {
            graph.get(relation[0]).add(relation[1]);
            inCount[relation[1]]++;
        }
        int step = 0;
        int studiedCount = 0;
        List<Integer> bfsQueue = new ArrayList<>();
        for (int node = 1; node < N + 1; node++) {
            if (inCount[node] == 0) {
                bfsQueue.add(node);
            }
        }
        // start learning with BFS
        while (!bfsQueue.isEmpty()) {
            // start new semester
            step++;
            List<Integer> nextQueue = new ArrayList<>();
            for (int node : bfsQueue) {
                studiedCount++;
                for (int endNode : graph.get(node)) {
                    inCount[endNode]--;
                    // if all prerequisite courses learned
                    if (inCount[endNode] == 0) {
                        nextQueue.add(endNode);
                    }
                }
            }
            bfsQueue = nextQueue;
        }

        // check if learn all courses
        return studiedCount == N ? step : -1;
    }


}
