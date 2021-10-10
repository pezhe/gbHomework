package ru.geekbrains13.lesson4;

public class QueuedLetters {

    enum Letter {A, B, C}

    private volatile Letter currentLetter = Letter.A;

    public void printLetter(Letter letterToPrint, Letter nextLetter) {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                while (currentLetter != letterToPrint) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(letterToPrint);
                currentLetter = nextLetter;
                notifyAll();
            }
        }
    }
}
