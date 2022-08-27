package com.samueldu.leetcode.studyplan.leetcode75.level3.unionfind;

public class UnionFind {
    /**
     * keeps track of ith element's group
     */
    public int[] group;

    /**
     * rank[] tracks
     */
    public int[] rank;

    public UnionFind(int size) {
        group = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; ++i) {
            group[i] = i;
        }
    }

    public int find(int node) {
        if (group[node] != node) {
            //recursive side effects of setting the group to root group
            group[node] = find(group[node]);
        }
        return group[node];
    }

    public boolean union(int node1, int node2) {
        int group1 = find(node1);
        int group2 = find(node2);

        // node1 and node2 already belong to same group.
        if (group1 == group2) {
            return false;
        }
        //group1 and group2 are not in the same group, union them.
        //Assign group number of larger group to the smaller group.
        if (rank[group1] > rank[group2]) {
            group[group2] = group1;
        } else if (rank[group1] < rank[group2]) {
            group[group1] = group2;
        } else {
            group[group1] = group2;

            //the line below ensures larger group has higher rank.
            rank[group2] += 1;
        }

        return true;
    }
}

