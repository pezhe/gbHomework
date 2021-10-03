package ru.geekbrains11.lesson2;

import java.util.Random;

public class HomeWorkApp2 {
    public static void main(String[] args) {
        Random rn = new Random();

        System.out.println(method1(rn.nextInt(20), rn.nextInt(20)));

        method2(rn.nextInt(20) - 10);

        System.out.println(method3(rn.nextInt(20) - 10));

        method4("String", rn.nextInt(5));

        System.out.println(method5(rn.nextInt(2025)));

    }

    /*Написать метод, принимающий на вход два целых числа и проверяющий,
     что их сумма лежит в пределах от 10 до 20 (включительно),
     если да – вернуть true, в противном случае – false*/
    static boolean method1(int a, int b) {
        System.out.println("method1");
        int c = a + b;
        System.out.printf("a = %d, b = %d, a + b = %d\n", a, b, c);
        return c >= 10 && c <= 20;
    }

    /*Написать метод, которому в качестве параметра передается целое число,
     метод должен напечатать в консоль, положительное ли число передали или отрицательное.
      Замечание: ноль считаем положительным числом*/
    static void method2(int a) {
        System.out.println("\nmethod2");
        System.out.println("a = " + a);
        System.out.println(a >= 0 ? "Positive" : "Negative");
    }

    /*Написать метод, которому в качестве параметра передается целое число.
    Метод должен вернуть true, если число отрицательное, и вернуть false если положительное*/
    static boolean method3(int a) {
        System.out.println("\nmethod3");
        System.out.println("a = " + a);
        return a < 0;
    }

    /*Написать метод, которому в качестве аргументов передается строка и число,
    метод должен отпечатать в консоль указанную строку, указанное количество раз*/
    static void method4(String s, int n) {
        System.out.println("\nmethod4");
        System.out.println("n = " + n);
        for (int i = 0; i < n; i++) System.out.println(s);
    }

    /*Написать метод, который определяет, является ли год високосным,
    и возвращает boolean (високосный - true, не високосный - false).
    Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный*/
    static boolean method5(int year) {
        System.out.println("\nmethod5");
        System.out.println("Year: " + year);
        if (year % 400 == 0) return true;
        else if (year % 100 == 0) return false;
        else return year % 4 == 0;
    }

}
