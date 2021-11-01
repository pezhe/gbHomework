package ru.gbalg.lesson3;

// Задание №2 - Целочисленный дек с фиксированной длиной
public class MyIntDeque {
    private static final int DEFAULT_SIZE = 10;
    private final int maxSize;
    private final int[] queue;
    private int left;
    private int right;
    private int items;

    public MyIntDeque(int size) {
        maxSize = size;
        queue = new int[maxSize];
        left = maxSize;
        right = -1;
        items = 0;
    }

    public MyIntDeque() {
        this(DEFAULT_SIZE);
    }

    public void insertRight(int i) {
        if (isFull()) throw new RuntimeException("This deque is full");
        if (right == maxSize - 1)
            right = -1;
        queue[++right] = i;
        items++;
    }

    public void insertLeft(int i) {
        if (isFull()) throw new RuntimeException("This deque is full");
        if (left == 0)
            left = maxSize;
        queue[--left] = i;
        items++;
    }

    public int removeLeft() {
        if (isEmpty()) throw new RuntimeException("This deque is empty");
        if (left == maxSize) left = 0;
        int temp = queue[left];
        if (left == maxSize - 1) left = 0;
        else left++;
        items--;
        return temp;
    }

    public int removeRight() {
        if (isEmpty()) throw new RuntimeException("This deque is empty");
        if (right == -1) right = maxSize - 1;
        int temp = queue[right];
        if (right == 0) right = maxSize - 1;
        else right--;
        items--;
        return temp;
    }

    public int peekLeft() {
        if (isEmpty()) throw new RuntimeException("This deque is empty");
        if (left == maxSize) left = 0;
        return queue[left];
    }

    public int peekRight() {
        if (isEmpty()) throw new RuntimeException("This deque is empty");
        if (right == -1) right = maxSize - 1;
        return queue[right];
    }

    public boolean isEmpty() {
        return (items == 0);
    }

    public boolean isFull() {
        return (items == maxSize);
    }

    public int size() {
        return items;
    }

}

