package ru.gbalg.lesson7;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MyGraph {

    static class Node {

        private final char label;
        private boolean marked;

        public Node(char label) {
            this.label = label;
            this.marked = false;
        }
    }

    private final Node[] nodeList;
    private final int[][] adjMat;
    private final int maxSize;
    private int size;

    public MyGraph(int maxSize) {
        this.maxSize = maxSize;
        nodeList = new Node[maxSize];
        adjMat = new int[maxSize][maxSize];
        size = 0;
    }

    private int getAdjUnmarkedNode(int node) {
        for (int i = 0; i < size; i++) {
            if(adjMat[node][i] == 1 && !nodeList[i].marked) {
                return i;
            }
        }
        return -1;
    }

    private int getNodePosByLabel(char label) {
        for (int i = 0; i < size; i++) {
            if (nodeList[i].label == label) return i;
        }
        return -1;
    }

    public List<Character> getShortestPath(char startLabel, char endLabel) {
        // Определение узлов
        int start = getNodePosByLabel(startLabel);
        int end = getNodePosByLabel(endLabel);
        if (start == -1 || end == -1) {
            System.out.println("Node(s) not found");
            return Collections.emptyList();
        }
        // Обход в ширину от стартового узла с сохранением связей
        int[] connections = new int[size];
        LinkedList<Integer> queue = new LinkedList<>();
        nodeList[start].marked = true;
        queue.add(start);
        while (!queue.isEmpty()) {
            int current = queue.poll();
            int next;
            while ((next = getAdjUnmarkedNode(current)) != -1) {
                nodeList[next].marked = true;
                connections[next] = current;
                queue.add(next);
            }
        }
        // Проверка наличия связи
        if (!nodeList[end].marked) {
            System.out.println("Nodes are not connected");
            return Collections.emptyList();
        }
        // Построение пути
        LinkedList<Character> stack = new LinkedList<>();
        int current = end;
        while (current != start) {
            stack.push(nodeList[current].label);
            current = connections[current];
        }
        stack.push(nodeList[start].label);
        // Сброс маркеров
        for (int i = 0; i < size; i++)
            nodeList[i].marked = false;

        return stack;
    }

    public void addNode(char label) {
        nodeList[size++] = new Node(label);
    }

    public void addEdge(char startLabel, char endLabel) {
        // Определение узлов
        int start = getNodePosByLabel(startLabel);
        int end = getNodePosByLabel(endLabel);
        if (start == -1 || end == -1) {
            System.out.println("Node(s) not found");
            return;
        }
        // Создание ребра
        adjMat[start][end] = 1;
        adjMat[end][start] = 1;
    }

    public int getMaxSize() {
        return maxSize;
    }

}