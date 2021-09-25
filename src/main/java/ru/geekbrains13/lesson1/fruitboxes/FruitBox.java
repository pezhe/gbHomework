package ru.geekbrains13.lesson1.fruitboxes;

import java.util.ArrayList;

public class FruitBox<T extends Fruit> {

    private final ArrayList<T> fruits = new ArrayList<>();

    public void add(T fruit) {
        fruits.add(fruit);
    }

    public T takeOne() {
        return fruits.remove(fruits.size() - 1);
    }

    public float getWeight() {
        float result = 0.0f;
        if (!fruits.isEmpty()) result = fruits.get(0).getWeight() * fruits.size();
        return result;
    }

    public boolean compare(FruitBox<? extends Fruit> anotherBox) {
        return Math.abs(this.getWeight() - anotherBox.getWeight()) < 0.0001;
    }

    public void moveAllFruitsTo(FruitBox<T> anotherBox) {
        while (!fruits.isEmpty()) {
            anotherBox.add(fruits.remove(fruits.size() - 1));
        }
    }

    @Override
    public String toString() {
        if (!fruits.isEmpty())
            return "This is a " + this.getClass().getSimpleName() + ". It contains " + fruits.size() +
                    " objects of " + fruits.get(0).getClass().getSimpleName() + ". Its weight is " +
                    this.getWeight();
        else return "This is a " + this.getClass().getSimpleName() + ". It's empty";
    }

}
