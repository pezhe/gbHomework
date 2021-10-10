package ru.geekbrains13.lesson4;

import ru.geekbrains13.lesson4.QueuedLetters.Letter;

public class ConcurrencyHomework1 {
    public static void main(String[] args) {
        QueuedLetters ql = new QueuedLetters();
        new Thread(() -> ql.printLetter(Letter.A, Letter.B)).start();
        new Thread(() -> ql.printLetter(Letter.B, Letter.C)).start();
        new Thread(() -> ql.printLetter(Letter.C, Letter.A)).start();
    }

}