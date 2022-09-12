package com.samueldu.graphtransversal.unionfind;

/**
 *Time Complexity
 * Union-find Constructor	Find	Union	Connected
 * Time Complexity	O(N)O(N)	O(\alpha (N))O(α(N))	O(\alpha (N))O(α(N))	O(\alpha (N))O(α(N))
 *
 * Note: NN is the number of vertices in the graph. \alphaα refers to the Inverse Ackermann function. In practice, we assume it's a constant. In other words, O(\alpha (N))O(α(N)) is regarded as O(1)O(1) on average.
 *
 * For the union-find constructor, we need to create two arrays of size NN each.
 * When using the combination of union by rank and the path compression optimization, the find operation will take O(\alpha(N))O(α(N)) time on average. Since union and connected both make calls to find and all other operations require constant time, union and connected functions will also take O(\alpha(N))O(α(N)) time on average.
 * Space Complexity
 * We need O(N)O(N) space to store the array of size NN.
 */
public class UnionFindWithPathCompression {

    // UnionFind.class
    class UnionFind {
        private int[] root;

        public UnionFind(int size) {
            root = new int[size];
            for (int i = 0; i < size; i++) {
                root[i] = i;
            }
        }

        public int find(int x) {
            if (x == root[x]) {
                return x;
            }

            /**
             * Recursion here!!! Different from  our usual recursion,
             * it has lasting side effects of modifying the underlying data structure.
             * this is the key for path compression to shortcut the root search for the next time.
             * Say if we have the tree as: 5-->4-->3-->2-->1-->0, then the following code will
             * recursively set the parent for all nodes to 0, essentially flatten the tree to depth of one:
             *                  0
             *                  ^
             *                  |
             *       ---------------------
             *       |    |    |    |    |
             *       5    4    3    2    1
             *
             */
            return root[x] = find(root[x]);
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                root[rootY] = rootX;
            }
        }

        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }
    }

    // App.java

        public static void main(String[] args) throws Exception {
            UnionFindWithBothByRankAndPathCompression uf = new UnionFindWithBothByRankAndPathCompression(10);
            // 1-2-5-6-7 3-8-9 4
            uf.union(1, 2);
            uf.union(2, 5);
            uf.union(5, 6);
            uf.union(6, 7);
            uf.union(3, 8);
            uf.union(8, 9);
            System.out.println(uf.connected(1, 5)); // true
            System.out.println(uf.connected(5, 7)); // true
            System.out.println(uf.connected(4, 9)); // false
            // 1-2-5-6-7 3-8-9-4
            uf.union(9, 4);
            System.out.println(uf.connected(4, 9)); // true
        }

}
