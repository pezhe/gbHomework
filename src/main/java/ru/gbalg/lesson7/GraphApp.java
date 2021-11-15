package ru.gbalg.lesson7;

import java.util.Arrays;

public class GraphApp {
    public static void main(String[] args) {
        MyGraph graph = new MyGraph();
        // Создание узлов 'A' - 'J'
        char c = 'A';
        for (int i = 0; i < 10; i++) {
            graph.addNode(c);
            c++;
        }
        /*
        A-B-C-D
        |   | |
        E   F G
        |  /\/
        H-I J
         */
        graph.addEdge('A', 'B');
        graph.addEdge('B', 'C');
        graph.addEdge('C', 'D');
        graph.addEdge('A', 'E');
        graph.addEdge('C', 'F');
        graph.addEdge('D', 'G');
        graph.addEdge('E', 'H');
        graph.addEdge('F', 'I');
        graph.addEdge('F', 'J');
        graph.addEdge('G', 'J');
        graph.addEdge('H', 'I');
        // Вывод кратчайшего пути
        System.out.println(Arrays.toString(graph.getShortestPath('A', 'J')));
    }
}
