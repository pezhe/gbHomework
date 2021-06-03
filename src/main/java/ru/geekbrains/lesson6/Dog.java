package ru.geekbrains.lesson6;

public class Dog extends Animal {

    private static int dogCount = 0;

    public static int getDogCount() {
        return dogCount;
    }

    public Dog(String name) {
        super(name);
        dogCount++;
    }

    @Override
    public void run(int length) {
        System.out.println("Пёс " + this.name + (length <= 500 ? " пробежал " + length + "м" : " пробежал 500м и запыхался"));
    }

    @Override
    public void swim(int length) {
        System.out.println("Пёс " + this.name + (length <= 10 ? " проплыл " + length + "м" : " проплыл 10м и утонул"));
    }
}
