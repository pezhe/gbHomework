package ru.geekbrains12.lesson5;

import java.util.Arrays;

public class Main {

    static final int SIZE = 10000000;
    static final int HALF_SIZE = SIZE/2;
    static float[] arr = new float[SIZE];

    public static void main(String[] args) {
        oneThreadMethod();
        twoThreadMethod();
    }

    public static void oneThreadMethod() {
        Arrays.fill(arr, 1.0f);
        long startTime = System.currentTimeMillis(); //Старт!

        performCalculation(arr);

        System.out.println("One thread time: " + (System.currentTimeMillis() - startTime) + " ms."); //Финиш!
    }

    public static void twoThreadMethod() {
        Arrays.fill(arr, 1.0f);
        long startTime = System.currentTimeMillis(); //Старт!

        float[] arr1 = new float[HALF_SIZE];
        float[] arr2 = new float[HALF_SIZE];

        System.arraycopy(arr, 0, arr1, 0, HALF_SIZE);
        System.arraycopy(arr, HALF_SIZE, arr2, 0, HALF_SIZE);

        Thread t1 = new Thread(() -> performCalculation(arr1));
        Thread t2 = new Thread(() -> performCalculation(arr2));

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(arr1, 0, arr, 0, HALF_SIZE);
        System.arraycopy(arr2, 0, arr, HALF_SIZE, HALF_SIZE);

        System.out.println("Two thread time: " + (System.currentTimeMillis() - startTime) + " ms."); //Финиш!
    }

    public static void performCalculation(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

}
