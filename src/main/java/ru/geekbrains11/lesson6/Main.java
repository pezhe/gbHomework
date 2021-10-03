package ru.geekbrains11.lesson6;

public class Main {

    public static void main(String[] args) {

        //Тестируем
        Cat cat1 = new Cat("Пирожок");
        Cat cat2 = new Cat("Себастьян");
        Cat cat3 = new Cat("Орешек");
        Dog dog1 = new Dog("Цербер");
        Dog dog2 = new Dog("Люцифер");
        cat1.swim(1);
        cat2.run(150);
        cat3.run(250);
        dog1.run(450);
        dog2.run(550);
        dog1.swim(5);
        dog2.swim(12);
        System.out.println("Всего животных " + Animal.getAnimalCount());
        System.out.println("Кошек " + Cat.getCatCount());
        System.out.println("Собак " + Dog.getDogCount());

    }

}
