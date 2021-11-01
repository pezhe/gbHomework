package ru.gbalg.lesson3;

import java.lang.reflect.Array;

// Задание № 3* - Саморасширяющийся обобщенный стек
public class MyStack<T> {
    private static final int DEFAULT_SIZE = 10; // Исходный размер по умолчанию
    private int maxSize;
    private T[] stack;
    private int top;
    private final Class<T> cl;

    @SuppressWarnings("unchecked")
    public MyStack(Class<T> cl, int size){ // Не знаю как можно создать обобщенный массив без рефлексии
        this.cl = cl;
        maxSize = size;
        stack = (T[]) Array.newInstance(cl, maxSize);
        top = -1;
    }

// Конструктор с исходным размером по умолчанию
    public MyStack(Class<T> cl) {
        this(cl, DEFAULT_SIZE);
    }

// Увеличение размера стека
    @SuppressWarnings("unchecked")
    private void expand() {
        T[] temp = (T[]) Array.newInstance(cl, maxSize * 2);
        System.arraycopy(stack, 0, temp, 0, maxSize);
        stack = temp;
        maxSize = stack.length;
    }

    public void push(T i) {
        if (top == maxSize-1) this.expand(); // Проверка на переполнение при добавлении элемента
        stack[++top] = i;
    }

    public T pop(){
        return this.stack[top--];
    }

    public T peek(){
        return this.stack[top];
    }

    public boolean isEmpty(){
        return (top == -1);
    }

}
