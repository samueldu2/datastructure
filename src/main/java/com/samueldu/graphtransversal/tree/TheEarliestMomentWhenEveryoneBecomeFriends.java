package com.samueldu.graphtransversal.tree;

import java.util.Arrays;
import java.util.Comparator;

/**
 * There are n people in a social group labeled from 0 to n - 1. You are given an array logs where logs[i] = [timestampi, xi, yi] indicates that xi and yi will be friends at the time timestampi.
 *
 * Friendship is symmetric. That means if a is friends with b, then b is friends with a. Also, person a is acquainted with a person b if a is friends with b, or a is a friend of someone acquainted with b.
 *
 * Return the earliest time for which every person became acquainted with every other person. If there is no such earliest time, return -1.
 *
 *
 *
 * Example 1:
 *
 * Input: logs = [[20190101,0,1],[20190104,3,4],[20190107,2,3],[20190211,1,5],[20190224,2,4],[20190301,0,3],[20190312,1,2],[20190322,4,5]], n = 6
 * Output: 20190301
 * Explanation:
 * The first event occurs at timestamp = 20190101 and after 0 and 1 become friends we have the following friendship groups [0,1], [2], [3], [4], [5].
 * The second event occurs at timestamp = 20190104 and after 3 and 4 become friends we have the following friendship groups [0,1], [2], [3,4], [5].
 * The third event occurs at timestamp = 20190107 and after 2 and 3 become friends we have the following friendship groups [0,1], [2,3,4], [5].
 * The fourth event occurs at timestamp = 20190211 and after 1 and 5 become friends we have the following friendship groups [0,1,5], [2,3,4].
 * The fifth event occurs at timestamp = 20190224 and as 2 and 4 are already friends anything happens.
 * The sixth event occurs at timestamp = 20190301 and after 0 and 3 become friends we have that all become friends.
 * Example 2:
 *
 * Input: logs = [[0,2,0],[1,0,1],[3,0,3],[4,1,2],[7,3,1]], n = 4
 * Output: 3
 *
 *
 * Constraints:
 *
 * 2 <= n <= 100
 * 1 <= logs.length <= 104
 * logs[i].length == 3
 * 0 <= timestampi <= 109
 * 0 <= xi, yi <= n - 1
 * xi != yi
 * All the values timestampi are unique.
 * All the pairs (xi, yi) occur at most one time in the input.
 *
 *
 */
public class TheEarliestMomentWhenEveryoneBecomeFriends {
/**
 * Approach: Union-Find (Disjoint-Set)
 * Intuition
 *
 * This problem deals with the relationship or membership of entities. For those of you who are familiar with a data structure called Union-Find, this problem might ring a bell. In fact, it is a perfect example that demonstrates the advantages of Union-Find data structure (also known as Disjoint-Set).
 *
 * Union-Find (a.k.a Disjoint-Set) is a data structure that keeps track of the connectivities among interconnected individuals efficiently. With Union-Find, one can quickly determine which group a specific individual belongs to. In addition, one can quickly merge two individuals together along with the two groups that they belong to.
 *
 * As suggested by its name, a typical Union-Find data structure usually provides two interfaces as follows:
 *
 * find(a): this function returns the group that the individual a belongs to.
 *
 * union(a, b): this function merges the two groups that the individuals a and b belong to respectively, if the groups are not of the same group already.
 *
 * To make the union(a, b) function more useful, one can return a boolean value in the function to indicate whether the merging actually happens or not. For example, union(a, b) would return true when a and b (and their respective groups) are merged together, and false when a and b are already in the same group and thus do not need to be merged together.
 *
 * Now, imagine that we already have the above Union-Find data structure available, we can go over the problem again and try to come up with a solution using the data structure.
 *
 * Algorithm
 *
 * The solution, which as shown below, can be implemented in only a few lines, is actually less difficult than the implementation of the Union-Find data structure.
 *
 * Yes, talk is cheap. But still, here are a few more words to help you better understand the above code.
 *
 * In order to discover the earliest moment, we must first ensure that we read through the logs in chronological order. Since there is no mentioning whether the logs are ordered or not in the problem description, we need to sort them first.
 *
 * Once the logs are sorted by time, we then iterate through them, while applying the Union-Find data structure.
 *
 * For each log, we connect the two individuals that were involved in the log, by applying the union(a, b) function.
 * Each log adds more connections among the individuals. A connection is useful if the two individuals are separated (disjoint), or redundant if the two individuals are connected already via other individuals.
 * Initially, we treat each individual as a separate group. The number of groups decreases along with the useful merging operations. The moment when the number of groups is reduced to one is the earliest moment when everyone becomes connected (friends).
 * Implementation
 *
 * In the above solutions, we assume that the Union-Find data structure has been implemented. In this section, we provide a complete solution with an optimized implementation of the Union-Find data structure. By optimized, we apply the path compression optimization in the find(a) interface and union by rank in the union(a, b) interface. For those of you who are not familiar with the data structure, we have an Explore Card that dives into more details, including the optimization techniques mentioned here.
 *
 *Complexity Analysis
 *
 * Since we applied the Union-Find data structure in our algorithm, we would like to start with a statement on the time complexity of the data structure, as follows:
 *
 * Statement: If MM operations, either Union or Find, are applied to NN elements, the total run time is O(M \cdot \alpha(N))O(M⋅α(N)), where \alpha (N)α(N) is the Inverse Ackermann Function.
 *
 * One can refer to this article on Union-Find complexity for more details.
 *
 * In our case, the number of elements in the Union-Find data structure is equal to the number of people, and the number of operations on the Union-Find data structure is up to the number of logs.
 *
 * Let NN be the number of people and MM be the number of logs.
 *
 * Time Complexity: O(N + M \log M + M \alpha (N))O(N+MlogM+Mα(N))
 *
 * First of all, we sort the logs in the order of timestamp. The time complexity of (quick) sorting is O(M \log M)O(MlogM).
 *
 * Then we created a Union-Find data structure, which takes O(N)O(N) time to initialize the array of group IDs.
 *
 * We then iterate through the sorted logs. At each iteration, we invoke the union(a, b) function. According to the statement we made above, the amortized time complexity of the entire process is O(M \alpha (N))O(Mα(N)).
 *
 * To sum up, the overall time complexity of our algorithm is O(N + M \log M + M \alpha (N))O(N+MlogM+Mα(N)).
 *
 * Space Complexity: O(N + M)O(N+M) or O(N + \log M)O(N+logM)
 *
 * The space complexity of our Union-Find data structure is O(N)O(N), because we keep track of the group ID for each individual.
 *
 * The space complexity of the sorting algorithm depends on the implementation of each program language.
 *
 * For instance, the list.sort() function in Python is implemented with the Timsort algorithm whose space complexity is O(M)O(M). While in Java, the Arrays.sort() is implemented as a variant of quicksort algorithm whose space complexity is O(\log{M})O(logM).
 *
 * To sum up, the overall space complexity of the algorithm is O(N + M)O(N+M) for Python and O(N + \log M)O(N+logM) for Java.
 */


    public int earliestAcq(int[][] logs, int n) {

        // First, we need to sort the events in chronological order.
        Arrays.sort(logs, new Comparator<int[]>() {
            @Override
            public int compare(int[] log1, int[] log2) {
                Integer tsp1 = log1[0];
                Integer tsp2 = log2[0];
                return tsp1.compareTo(tsp2);
            }
        });

        // Initially, we treat each individual as a separate group.
        int groupCount = n;
        UnionFind uf = new UnionFind(n);

        for (int[] log : logs) {
            int timestamp = log[0], friendA = log[1], friendB = log[2];

            // We merge the groups along the way.
            if (uf.union(friendA, friendB)) {
                groupCount -= 1;
            }

            // The moment when all individuals are connected to each other.
            if (groupCount == 1) {
                return timestamp;
            }
        }

        // There are still more than one groups left,
        //  i.e. not everyone is connected.
        return -1;
    }
}

    class UnionFind {
        private int[] group;
        private int[] rank;

        public UnionFind(int size) {
            this.group = new int[size];
            this.rank = new int[size];
            for (int person = 0; person < size; ++person) {
                this.group[person] = person;
                this.rank[person] = 0;
            }
        }

        /** Return the id of group that the person belongs to. */
        public int find(int person) {
            if (this.group[person] != person)
                this.group[person] = this.find(this.group[person]);
            return this.group[person];
        }

        /**
         * If it is necessary to merge the two groups that x, y belong to.
         * @return true: if the groups are merged.
         */
        public boolean union(int a, int b) {
            int groupA = this.find(a);
            int groupB = this.find(b);
            boolean isMerged = false;

            // The two people share the same group.
            if (groupA == groupB)
                return isMerged;

            // Otherwise, merge the two groups.
            isMerged = true;
            // Merge the lower-rank group into the higher-rank group.
            if (this.rank[groupA] > this.rank[groupB]) {
                this.group[groupB] = groupA;
            } else if (this.rank[groupA] < this.rank[groupB]) {
                this.group[groupA] = groupB;
            } else {
                this.group[groupA] = groupB;
                this.rank[groupB] += 1;
            }

            return isMerged;
        }
    }