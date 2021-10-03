package ru.geekbrains11.lesson6;

public class Cat extends Animal {

    private static int catCount = 0;

    public static int getCatCount() {
        return catCount;
    }

    public Cat(String name) {
        super(name);
        catCount++;
    }

    @Override
    public void run(int length) {
        System.out.println("Кот " + this.name + (length <= 200 ? " пробежал " + length + "м" : " пробежал 200м и запыхался"));
    }

    @Override
    public void swim(int length) {
        System.out.println("Кот " + this.name + " утонул");
    }
}
