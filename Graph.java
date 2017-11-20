package Skripsi;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Stanley
 */
public class Graph {
    int[][] graph;
    
    public Graph(int bnykVertex){
        graph = new int[bnykVertex][bnykVertex];
    }
    
    public void setEdge(int x, int y, int weight){
        this.graph[x][y] = weight;
    }
    
    public int getEdge(int x, int y){
        return this.graph[x][y];
    }
    
    public int getVertex(){
        return this.graph.length;
    }
}
