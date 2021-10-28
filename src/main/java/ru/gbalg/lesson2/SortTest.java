package ru.gbalg.lesson2;

import java.util.concurrent.ThreadLocalRandom;

public class SortTest {

    public static void main(String[] args) {
        int[] array = new int[100000];
        randomize(array);
        long startTime = System.currentTimeMillis();
        bubbleSort(array);
        System.out.println("Bubble sort time: " + (System.currentTimeMillis() - startTime) + " ms.");
        randomize(array);
        startTime = System.currentTimeMillis();
        selectSort(array);
        System.out.println("Select sort time: " + (System.currentTimeMillis() - startTime) + " ms.");
        randomize(array);
        startTime = System.currentTimeMillis();
        insertSort(array);
        System.out.println("Insert sort time: " + (System.currentTimeMillis() - startTime) + " ms.");
    }

    static void randomize(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = ThreadLocalRandom.current().nextInt(0, 100);
        }
    }

    static void bubbleSort(int[] array) {
        for (int j = array.length - 1; j >= 1; j--) {
            for(int i = 0; i < j; i++) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                }
            }
        }
    }

    static void selectSort(int[] array) {
        int marker;
        for(int j = 0; j < array.length; j++) {
            marker = j;
            for(int i = j + 1; i < array.length; i++) {
                if (array[i] < array[marker]) {
                    marker = i;
                }
            }
            int temp = array[j];
            array[j] = array[marker];
            array[marker] = temp;
        }
    }

    static void insertSort(int[] array) {
        for(int j = 1; j < array.length; j++) {
            int temp = array[j];
            int i = j;
            while(i > 0 && array[i - 1] >= temp) {
                array[i] = array[i - 1];
                i--;
            }
            array[i] = temp;
        }
    }

}
