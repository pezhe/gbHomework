package ru.geekbrains.lesson1;

public class HomeWorkApp {

    public static void main(String[] args) {
        printThreeWords();
        checkSumSign();
        printColor();
        compareNumbers();
    }

    static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple\n");
    }

    static void checkSumSign() {
        int a = (int)(Math.random() * 200) - 100;
        int b = (int)(Math.random() * 200) - 100;
        int c = a + b;
        System.out.println("a = " + a + ", b = " + b + ", a + b = " + c);
        System.out.println(c >= 0 ? "Сумма положительная\n" : "Сумма отрицательная\n");
    }

    static void printColor() {
        int value = (int)(Math.random() * 300) - 100;
        System.out.println("value = " + value);
        if (value <= 0) System.out.println("Красный\n");
        else if (value <= 100) System.out.println("Жёлтый\n");
        else System.out.println("Зелёный\n");
    }

    static void compareNumbers() {
        int a = (int)(Math.random() * 100);
        int b = (int)(Math.random() * 100);
        System.out.println("a = " + a + ", b = " + b);
        System.out.println(a >= b ? "a >= b" : "a < b");
    }

}
