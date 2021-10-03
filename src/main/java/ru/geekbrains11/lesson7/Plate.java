package ru.geekbrains11.lesson7;

public class Plate {
    private int food;
    private final int capacity;

    public Plate(int capacity, int food) {
        this.capacity = capacity;
        this.food = Math.min(food, capacity);
    }

    public boolean decreaseFood(int n) {
        if (n <= food) {
            food -= n;
            return true;
        }
        else return false;
    }

    public void addFood(int n) {
        food = Math.min(food + n, capacity);
    }

    public void info() {
        System.out.println("plate: " + food);
    }
}
