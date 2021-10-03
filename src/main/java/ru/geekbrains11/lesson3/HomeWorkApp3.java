package ru.geekbrains11.lesson3;

import java.util.Random;

public class HomeWorkApp3 {
    public static void main(String[] args) {
        Random rn = new Random();
//Проверка задания 1
        method1(rn, 10);
//Проверка задания 2
        method2();
//Проверка задания 3
        method3();
//Проверка задания 4
        method4(5);
//Проверка задания 5
        int[] arr1 = method5(5, 10); //Method call
        for (int i : arr1) System.out.print(i + " "); //Result output
        System.out.println();
//Проверка задания 6
        method6(rn, 10);
//Проверка задания 7
        int[] arr2 = new int[10];
        System.out.print("\nArray: ");
        for (int i = 0; i < arr2.length; i++) System.out.print((arr2[i] = rn.nextInt(3)) + " "); //Initialization
        System.out.println(method7(arr2)); //Method call
//Проверка задания 8
        int[] arr3 = new int[5];
        int shift = rn.nextInt(5) - 2;
        System.out.println("\nShift = " + shift);
        System.out.print("Before: ");
        for (int i = 0; i < arr3.length; i++) System.out.print((arr3[i] = rn.nextInt(10)) + " "); //Initialization
        method8(arr3, shift); //Method call
        System.out.print("After:  ");
        for (int n : arr3) System.out.print(n + " "); //Result output
    }

    /*1. Задать целочисленный массив, состоящий из элементов 0 и 1.
    Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
    С помощью цикла и условия заменить 0 на 1, 1 на 0*/
    static void method1(Random rn, int len) {
        System.out.println("method1");
        int[] arr = new int[len];
        System.out.print("Before: ");
        for (int i = 0; i < len; i++) System.out.print((arr[i] = rn.nextInt(2)) + " ");
        System.out.print("\nAfter:  ");
        for (int i = 0; i < len; i++)
            if (arr[i] == 0) System.out.print((arr[i] = 1) + " ");
            else System.out.print((arr[i] = 0) + " ");
        System.out.println();
    }

    /*2. Задать пустой целочисленный массив длиной 100.
    С помощью цикла заполнить его значениями 1 2 3 4 5 6 7 8 … 100*/
    static void method2() {
        System.out.println("\nmethod2");
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) System.out.print((arr[i] = i + 1) + " ");
        System.out.println();
    }

    /*3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ]
    пройти по нему циклом, и числа меньшие 6 умножить на 2*/
    static void method3() {
        System.out.println("\nmethod3");
        int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) arr[i] *= 2;
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    /*4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое),
    и с помощью цикла(-ов) заполнить его диагональные элементы единицами
    можно только одну из диагоналей, если обе сложно).
    Определить элементы одной из диагоналей можно по следующему принципу:
    индексы таких элементов равны, то есть [0][0], [1][1], [2][2], …, [n][n]*/
    static void method4(int size) {
        System.out.println("\nmethod4");
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) matrix[i][i] = matrix[i][size - i - 1] = 1;
        //Output
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) System.out.print(matrix[i][j] + " ");
            System.out.println();
        }
    }

    /*5. Написать метод, принимающий на вход два аргумента: len и initialValue,
    и возвращающий одномерный массив типа int длиной len, каждая ячейка которого равна initialValue*/
    static int[] method5(int len, int initialValue) {
        System.out.println("\nmethod5");
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) arr[i] = initialValue;
        return arr;
    }

    /*6. * Задать одномерный массив и найти в нем минимальный и максимальный элементы*/
    static void method6(Random rn, int len) {
        System.out.println("\nmethod6");
        //Initialization
        int[] arr = new int[len];
        System.out.print("Array: ");
        for (int i = 0; i < len; i++) System.out.print((arr[i] = rn.nextInt(21) - 10) + " ");
        //Search
        int min, max;
        min = max = arr[0];
        for (int n : arr) {
            if (n < min) min = n;
            if (n > max) max = n;
        }
        //Output
        System.out.printf("\nmin = %d, max = %d\n", min, max);
    }

    /*7. ** Написать метод, в который передается не пустой одномерный целочисленный массив,
    метод должен вернуть true, если в массиве есть место, в котором сумма левой и правой части массива равны*/
    static boolean method7(int[] arr) {
        System.out.println("\nmethod7");
        if (arr.length < 2) return false;
        int centerPoint = arr.length / 2 - 1;
        int leftSum = 0;
        int rightSum = 0;
        boolean moveRight = false;
        boolean moveLeft = false;
        for (int i = centerPoint; i >= 0; i--) leftSum += arr[i];
        for (int i = centerPoint + 1; i < arr.length; i++) rightSum += arr[i];
        while (centerPoint >= 0 && centerPoint < arr.length && !(moveLeft && moveRight)) {
            if (leftSum > rightSum) {
                leftSum -= arr[centerPoint];
                rightSum += arr[centerPoint];
                centerPoint--;
                moveLeft = true;
            } else if (rightSum > leftSum) {
                leftSum += arr[centerPoint + 1];
                rightSum -= arr[centerPoint + 1];
                centerPoint++;
                moveRight = true;
            } else {
                for (int i = 0; i < arr.length; i++) {
                    System.out.print(arr[i] + " ");
                    if (i == centerPoint) System.out.print("||| ");
                }
                System.out.println();
                return true;
            }
        }
        return false;
    }

    /*8. *** Написать метод, которому на вход подается одномерный массив и число n
    (может быть положительным, или отрицательным), при этом метод должен сместить все элементы массива на n позиций.
    Элементы смещаются циклично. Для усложнения задачи нельзя пользоваться вспомогательными массивами.
    Примеры: [ 1, 2, 3 ] при n = 1 (на один вправо) -> [ 3, 1, 2 ];
    [ 3, 5, 6, 1] при n = -2 (на два влево) -> [ 6, 1, 3, 5 ].*/
    static void method8(int[] arr, int shift) {
        System.out.println("\nmethod8");
        if (shift > 0) { //Move right
            for (int i = 0; i < shift; i++) {
                int buffer = arr[0];
                arr[0] = arr[arr.length - 1];
                for (int j = 1; j < arr.length; j++) {
                    int swap = arr[j];
                    arr[j] = buffer;
                    buffer = swap;
                }
            }
        } else if (shift < 0) { //Move left
            for (int i = 0; i < -shift; i++) {
                int buffer = arr[arr.length - 1];
                arr[arr.length - 1] = arr[0];
                for (int j = arr.length - 2; j >= 0; j--) {
                    int swap = arr[j];
                    arr[j] = buffer;
                    buffer = swap;
                }
            }
        }
    }
}