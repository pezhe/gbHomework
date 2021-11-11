package ru.gbalg.lesson3;

import java.util.Arrays;

// Задание № 3* - Саморасширяющийся обобщенный стек
public class MyStack<T> {
    private static final int DEFAULT_SIZE = 10;
    private int maxSize;
    private T[] stack;
    private int top;

    @SuppressWarnings("unchecked")
    public MyStack(int size){
        maxSize = size;
        stack = (T[]) new Object[maxSize];
        top = -1;
    }

// Конструктор с исходным размером по умолчанию
    public MyStack() {
        this(DEFAULT_SIZE);
    }

// Увеличение размера стека
    private void expand() {
        stack = Arrays.copyOf(stack, maxSize * 2);
        maxSize = stack.length;
    }

    public void push(T i) {
        if (top == maxSize - 1) this.expand();
        stack[++top] = i;
    }

    public T pop() {
        T temp = this.stack[top];
        this.stack[top--] = null;
        return temp;
    }

    public T peek() {
        return this.stack[top];
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (this.top == this.maxSize-1);
    }

}
