package ru.geekbrains11.lesson6;

public abstract class Animal {

    private static int animalCount = 0;
    protected String name;

    public static int getAnimalCount() {
        return animalCount;
    }

    public Animal(String name) {
        this.name = name;
        animalCount++;
    }

    public abstract void run(int length);

    public abstract void swim(int length);

}
