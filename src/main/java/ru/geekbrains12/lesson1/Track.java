package ru.geekbrains12.lesson1;

public class Track extends Trial {

    private final int length;
    private final int id;
    private static int counter = 1;

    public Track (int length) {
        this.length = length;
        this.id = counter++;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public boolean pass(CanPassTrial cpt) {
        if (cpt instanceof CanRun) {
            CanRun cr = (CanRun)cpt;
            if (cr.run(length)) {
                System.out.println("Succeed");
                return true;
            }
            else {
                System.out.println("Failed");
                return false;
            }
        }
        else {
            System.out.println("This dude can not run!");
            return false;
        }
    }
}
