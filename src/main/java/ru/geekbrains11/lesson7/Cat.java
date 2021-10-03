package ru.geekbrains11.lesson7;

public class Cat {
    private final String name;
    private final int appetite;
    private boolean satiety = false;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }

    public String getName() {
        return name;
    }

    public void eat(Plate p) {
        System.out.print("feeding " + this.name + "...");
        if (p.decreaseFood(appetite)) {
            System.out.println(" succeed");
            this.satiety = true;
        }
        else System.out.println(" failed");
    }

    public boolean isSaiated() {
        return satiety;
    }

}
