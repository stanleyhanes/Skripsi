package Skripsi;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
/**
 *
 * @author Stanley
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Masukkan Jumlah Vertex : ");
        int bnykVertex = sc.nextInt();
        Graph graph = new Graph(bnykVertex);
        double ratio = 0;
        int weight = 0;
        System.out.println("Masukkan Bobot Tiap Edge : ");
        for (int i = 0; i < bnykVertex; i++) {
            for (int j = 0; j < bnykVertex; j++) {
                weight = sc.nextInt();
                graph.setEdge(i, j, weight);
            }
            graph.setEdge(i, i, 0);
        }
        System.out.println("Masukkan Algoritma yang Dipilih (1-4) : ");
        int algoritma = sc.nextInt();
        Dijkstra dijk = new Dijkstra(algoritma);
        if (algoritma==3) {
            System.out.println("Masukkan Ratio (0-1) : ");
            ratio = sc.nextDouble();
            dijk.setRatio(ratio);
        }
        dijk.APSP(graph);
    }
}
