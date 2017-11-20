package Skripsi;

import java.util.LinkedList;
import java.util.Queue;

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
/**
 *
 * @author Stanley
 */
public class Dijkstra {
    int algoritma;
    double ratio;
    
    public Dijkstra(int algoritma){
        this.algoritma = algoritma;
    }
    
    public void setRatio(double ratio){
        this.ratio = ratio;
    }
    
    public void APSP(Graph graph){
        Queue q = new LinkedList();
        int[][] distance = new int[graph.getVertex()][graph.getVertex()];
        int[][] previous = new int[graph.getVertex()][graph.getVertex()];
        for (int i = 0; i < graph.getVertex(); i++) {
            for (int j = 0; j < graph.getVertex(); j++) {
                distance[i][j] = Integer.MAX_VALUE;
                previous[i][j] = Integer.MAX_VALUE;
            }
        }
        int source = 0;
        long startTime, endTime, totalTime;
        boolean[] flag = new boolean[graph.getVertex()];
        int[] order = new int[graph.getVertex()];
        int[] degree = new int[graph.getVertex()];
        int count = -1;
        int temp = 0;
        switch(algoritma){
            case 1:
                startTime = System.currentTimeMillis();
                for (int i = 0; i < graph.getVertex(); i++) {
                    for (int j = 0; j < graph.getVertex(); j++) {
                        q.add(j);
                    }
                    source = i;
                    distance[source][source] = 0;
                    while(!q.isEmpty()){
                        int mindis = 0;
                        int distemp = Integer.MAX_VALUE;
                        for (int j = 0; j < q.size(); j++) {
                            temp = (int)q.poll();
                            if(distemp>distance[source][temp]){
                                mindis = temp;
                                distemp = distance[source][temp];
                            }
                            q.add(temp);
                        }
                        temp = mindis;
                        q.remove(temp);
                        int tempDis = 0;
                        for (int j = 0; j < graph.getVertex(); j++) {
                            if ((graph.getEdge(temp, j)!=0) && (temp!=j)) {
                                tempDis = distance[source][temp] + graph.getEdge(temp, j);
                                if (tempDis < distance[source][j]) {
                                    distance[source][j] = tempDis;
                                    previous[source][j] = temp;
                                }
                            }
                        }
                    }
                }
                
                
                for (int i = 0; i < graph.getVertex(); i++) {
                    for (int j = 0; j < graph.getVertex(); j++) {
                        System.out.print(distance[i][j] + " ");
                    }
                    System.out.println("");
                }
                endTime   = System.currentTimeMillis();
                totalTime = endTime - startTime;
                System.out.println("Total = " + totalTime);
                break;
            case 2:
                startTime = System.currentTimeMillis();
                for (int i = 0; i < graph.getVertex(); i++) {
                    flag[i]=false;
                }
                for (int i = 0; i < graph.getVertex(); i++) {
                    dijkstraModif(graph, i, distance, flag);
                }
                for (int j = 0; j < graph.getVertex(); j++) {
                    for (int k = 0; k < graph.getVertex(); k++) {
                        System.out.print(distance[j][k] + " ");
                    }
                    System.out.println("");
                }
                endTime   = System.currentTimeMillis();
                totalTime = endTime - startTime;
                System.out.println("Total = " + totalTime);
                break;
            case 3:
                startTime = System.currentTimeMillis();
                for (int i = 0; i < graph.getVertex(); i++) {
                    flag[i]=false;
                }
                for (int i = 0; i < graph.getVertex(); i++) {
                    for (int j = 0; j < graph.getVertex(); j++) {
                        if ((graph.getEdge(i, j) != 0) && (i!=j)) {
                            count++;
                        }
                    }
                    degree[i] = count;
                    count = -1;
                }
                for (int i = 0; i < graph.getVertex(); i++) {
                    order[i] = i;
                }
                for (int i = 0; i < ratio*graph.getVertex(); i++) {
                    for (int j = i+1; j < graph.getVertex(); j++) {
                        if (degree[order[j]] > degree[order[i]]) {
                            temp = order[j];
                            order[j] = order[i];
                            order[i] = temp;
                        }
                    }
                }
                for (int i = 0; i < graph.getVertex(); i++) {
                    dijkstraModif(graph, order[i], distance, flag);
                }
                for (int j = 0; j < graph.getVertex(); j++) {
                    for (int k = 0; k < graph.getVertex(); k++) {
                        System.out.print(distance[j][k] + " ");
                    }
                    System.out.println("");
                }
                endTime   = System.currentTimeMillis();
                totalTime = endTime - startTime;
                System.out.println("Total = " + totalTime);
                break;
            case 4:
                startTime = System.currentTimeMillis();
                for (int i = 0; i < graph.getVertex(); i++) {
                    flag[i]=false;
                }
                for (int i = 0; i < graph.getVertex(); i++) {
                    for (int j = 0; j < graph.getVertex(); j++) {
                        if ((graph.getEdge(i, j) != 0) && (i!=j)) {
                            count++;
                        }
                    }
                    degree[i] = count;
                    count = -1;
                }
                for (int i = 0; i < graph.getVertex(); i++) {
                    order[i] = i;
                }
                for (int i = 0; i < graph.getVertex(); i++) {
                    for (int j = i+1; j < graph.getVertex(); j++) {
                        if (degree[order[j]] > degree[order[i]]) {
                            temp = order[j];
                            order[j] = order[i];
                            order[i] = temp;
                        }
                    }
                    dijkstraAdaptive(graph, order[i], distance, flag, degree);
                }
                for (int j = 0; j < graph.getVertex(); j++) {
                    for (int k = 0; k < graph.getVertex(); k++) {
                        System.out.print(distance[j][k] + " ");
                    }
                    System.out.println("");
                }
                endTime   = System.currentTimeMillis();
                totalTime = endTime - startTime;
                System.out.println("Total = " + totalTime);
                break;
        }
    }
    public void dijkstraModif(Graph graph, int source, int[][] distance, boolean[] flag){
        Queue q = new LinkedList();
        distance[source][source] = 0;
        q.add(source);
        int t = 0;
        while(!q.isEmpty()){
            t = (int)q.poll();
            if (flag[t]) {
                for (int j = 0; j < graph.getVertex(); j++) {
                    if (distance[source][t] + distance[t][j] < distance[source][j]) {
                        distance[source][j] = distance[source][t] + distance[t][j];
                    }
                }
            }
            else{
                for (int j = 0; j < graph.getVertex(); j++) {
                    if ((graph.getEdge(t, j)!=0) && (t!=j)) {
                        if (distance[source][t] + graph.getEdge(t, j) < distance[source][j]) {
                            distance[source][j] = distance[source][t] + graph.getEdge(t, j);
                            q.add(j);
                        }
                    }
                }
            }
        }
        flag[source] = true;
    }
    
    public void dijkstraAdaptive(Graph graph, int source, int[][] distance, boolean[] flag, int[] degree){
        Queue q = new LinkedList();
        distance[source][source] = 0;
        q.add(source);
        int t = 0;
        while(!q.isEmpty()){
            t = (int)q.poll();
            if (flag[t]) {
                for (int j = 0; j < graph.getVertex(); j++) {
                    if (distance[source][t] + distance[t][j] < distance[source][j]) {
                        distance[source][j] = distance[source][t] + distance[t][j];
                    }
                }
            }
            else{
                for (int j = 0; j < graph.getVertex(); j++) {
                    if ((graph.getEdge(t, j) != 0) && (t!=j)) {
                        if (distance[source][t] + graph.getEdge(t, j) < distance[source][j]) {
                            degree[t]++;
                            distance[source][j] = distance[source][t] + graph.getEdge(t, j);
                            q.add(j);
                        }
                    }
                }
            }
        }
        flag[source] = true;
    }
}
