package ru.gbalg.lesson3;

// Задание №4 - Приоритетная очередь с вставкой О(1) и извлечением О(n)
// O(1) получаем добавляя элемент в хвост очереди по индексу (т. е. они не упорядочены)
// O(n) получаем поиском элемента с наивысшим приоритетом, а на его место ставим элемент с конца
public class MyPriorityQueue {
    private static final int DEFAULT_SIZE = 10;
    private final int maxSize;
    private final int[] queue;
    private int items;

    public MyPriorityQueue(int i) {
        maxSize = i;
        queue = new int[maxSize];
        items = 0;
    }

    public MyPriorityQueue() {
        this(DEFAULT_SIZE);
    }

    public void insert(int item) {
        if (isFull()) throw new RuntimeException("This queue is full");
        queue[items++] = item;
    }

    public int remove() {
        if (isEmpty()) throw new RuntimeException("This queue is empty");
        int highestPriorityPosition = items - 1;
        int highestPriority = queue[highestPriorityPosition];
        for (int i = items - 2; i >= 0; i--) {
            if (highestPriority >= queue[i]) {
                highestPriority = queue[i];
                highestPriorityPosition = i;
            }
        }
        queue[highestPriorityPosition] = queue[items - 1];
        items--;
        return highestPriority;
    }

    public boolean isEmpty() {
        return (items == 0);
    }

    public boolean isFull() {
        return (items == maxSize);
    }

}
