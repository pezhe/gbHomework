package ru.geekbrains12.lesson1;

public class Main {
    public static void main(String[] args) {
        CanPassTrial[] cptarr = new CanPassTrial[5];
        cptarr[0] = new Human("Rick", 100, 10);
        cptarr[1] = new Human("Morty", 80, 12);
        cptarr[2] = new Robot("Bender", 150, 5);
        cptarr[3] = new Cat("Tom", 200, 15);
        cptarr[4] = new Cat("Garfield", 50, 2);

        Trial[] tarr = new Trial[4];
        tarr[0] = new Track(60);
        tarr[1] = new Wall(5);
        tarr[2] = new Track(60);
        tarr[3] = new Wall(11);

        for (CanPassTrial cpt : cptarr) {
            System.out.println("\n" + cpt.getClass().getSimpleName() + " " + cpt.getName() + " starts!");
            for (int i = 0; i < tarr.length; i++) {
                System.out.print("Attempting to pass the"
                        + " " + tarr[i].getClass().getSimpleName() + " #" + tarr[i].getId() + "... ");
                if (!tarr[i].pass(cpt)) {
                    System.out.println(cpt.getClass().getSimpleName() + " " + cpt.getName() + " has left the contest!");
                    break;
                }
                if (i == tarr.length - 1) {
                    System.out.println(cpt.getClass().getSimpleName() + " " + cpt.getName() + " has passed the " +
                            "contest!");
                }
            }
        }
    }
}
