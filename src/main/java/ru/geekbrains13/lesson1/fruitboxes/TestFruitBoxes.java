package ru.geekbrains13.lesson1.fruitboxes;

public class TestFruitBoxes {
    public static void main(String[] args) {
        FruitBox<Apple> appleBox1 = new FruitBox<>();
        FruitBox<Apple> appleBox2 = new FruitBox<>();
        FruitBox<Orange> orangeBox = new FruitBox<>();
        appleBox1.add(new Apple());
        appleBox1.add(new Apple());
        appleBox1.add(new Apple());
        //appleBox1.add(new Orange()); - compile time error
        orangeBox.add(new Orange());
        orangeBox.add(new Orange());
        System.out.println(appleBox1);
        System.out.println(appleBox2);
        System.out.println(orangeBox);
        System.out.println("Comparing weights: " + appleBox1.compare(orangeBox));
        appleBox1.takeOne();
        System.out.println("Comparing weights: " + appleBox1.compare(orangeBox));
        //appleBox1.moveAllFruitsTo(orangeBox); - compile time error
        appleBox1.moveAllFruitsTo(appleBox2);
        System.out.println(appleBox1);
        System.out.println(appleBox2);
    }
}
