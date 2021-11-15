package ru.gbalg.lesson7;

import java.util.LinkedList;

public class MyGraph {

    static class Node {

        private final char label;
        private boolean wasVisited;

        public Node(char label) {
            this.label = label;
            this.wasVisited = false;
        }
    }

    private final int MAX_NODES = 10;
    private final Node[] nodeList;
    private final int[][] adjMat;
    private int size;

    public MyGraph() {
        nodeList = new Node[MAX_NODES];
        adjMat = new int[MAX_NODES][MAX_NODES];
        size = 0;
    }

    private int getAdjUnvisitedNode(int node) {
        for (int i = 0; i < size; i++) {
            if(adjMat[node][i] == 1 && !nodeList[i].wasVisited) {
                return i;
            }
        }
        return -1;
    }

    private int getNodeByLabel(char label) {
        for (int i = 0; i < size; i++) {
            if (nodeList[i].label == label) return i;
        }
        return -1;
    }

    public char[] getShortestPath(char start, char end) {
        int startPos = getNodeByLabel(start);
        int endPos = getNodeByLabel(end);
        if (startPos * endPos < 0) {
            System.out.println("Node(s) not found");
            return new char[0];
        }
        // Очередь
        LinkedList<Integer> queue = new LinkedList<>();
        nodeList[0].wasVisited = true;
        queue.add(0); // Вставка в конец очереди
        int v2;
        while (!queue.isEmpty()) {
            int v1 = queue.poll();
            while ((v2 = getAdjUnvisitedNode(v1)) != -1) {
                nodeList[v2].wasVisited = true; // Пометка
                queue.add(v2);
            }
        }
        for (int i = 0; i < size; i++) // Сброс флагов
            nodeList[i].wasVisited = false;
        return new char[0];
    }

    public void addNode(char label) {
        nodeList[size++] = new Node(label);
    }

    public void addEdge(char start, char end) {
        int startPos = getNodeByLabel(start);
        int endPos = getNodeByLabel(end);
        if (startPos * endPos < 0) {
            System.out.println("Node(s) not found");
            return;
        }
        adjMat[startPos][endPos] = 1;
        adjMat[endPos][startPos] = 1;
    }

}