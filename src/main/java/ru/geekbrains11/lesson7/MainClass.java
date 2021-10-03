package ru.geekbrains11.lesson7;

public class MainClass {
    public static void main(String[] args) {

        Plate plate = new Plate(100, 50);
        plate.info();
        plate.addFood(100);

        Cat[] catArr = new Cat[5];
        catArr[0] = new Cat("Barsik", 15);
        catArr[1] = new Cat("Murzik", 25);
        catArr[2] = new Cat("Vaska", 35);
        catArr[3] = new Cat("Pushok", 10);
        catArr[4] = new Cat("Murka", 30);

        for (Cat c : catArr) {
            c.eat(plate);
            System.out.println(c.getName() + " is satiated: " + c.isSaiated());
        }

        plate.info();
    }
}
