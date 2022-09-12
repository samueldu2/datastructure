package com.samueldu.graphtransversal.tree;

import java.util.ArrayList;
import java.util.List;

public class AdjacencyList {
    private List<List<Integer>> adjacencyList ;
    public AdjacencyList(int numberOfNode){
        adjacencyList = new ArrayList<>();
        for (int i =0; i<numberOfNode; i++)
            adjacencyList.add(new ArrayList<Integer>());
    }
    public void addEdges(int[][] edges){


        for (int[] edge:edges){
            adjacencyList.get(edge[0]).add(edge[1]);
            adjacencyList.get(edge[1]).add(edge[0]);
        }
    }

    public List<Integer> get(int i){
        return adjacencyList.get(i);
    }
}
