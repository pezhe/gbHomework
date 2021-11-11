package ru.gbalg.lesson3;

// Задание №1 - Метод, переворачивающий строки, сделанный с помощью класса из задания 3*
public class ReversePrinter {

// Можно сделать быстро с использовнием StringBuilder reverse(), но раз урок про стеки и прочее...
    public static void printReverse(String line) {
        char[] ch = line.toCharArray();
        MyStack<Character> ms = new MyStack<>();
        for (char c : ch) ms.push(c);
        while (!ms.isEmpty()) {
            System.out.print(ms.pop());
        }
    }

// Короткий тест
    public static void main(String[] args) {
        ReversePrinter.printReverse("А роза упала на лапу Азора");
    }

}
