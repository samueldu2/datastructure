package com.samueldu.graphtransversal.breadthfirst;


import java.util.*;

public class FindIfPathExistsInGraph {

    AdjacencyList adjL;
    HashSet<Integer> visited = new HashSet<>();


    public boolean validPath(int n, int[][] edges, int source, int destination) {
        if(source==destination)
            return true;
        adjL = new AdjacencyList(n);
        adjL.addEdges(edges);
        Queue <Integer>q = new LinkedList();
        q.add(source);

        while(!q.isEmpty()){
            Integer i = q.poll();
            if(visited.contains(i)) continue;
            visited.add(i);

            List <Integer>neighbors =adjL.get(i);
            for (Integer nb:neighbors){
                if(nb==destination) return true;
                q.add(nb);
            }
        }

        return false;
    }


}


 class AdjacencyList {
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
