package ru.geekbrains12.lesson1;

public class Wall extends Trial {

    private final int height;
    private final int id;
    private static int counter = 1;

    public Wall (int height) {
        this.height = height;
        this.id = counter++;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public boolean pass(CanPassTrial cpt) {
        if (cpt instanceof CanJump) {
            CanJump cj = (CanJump)cpt;
            if (cj.jump(height)) {
                System.out.println("Succeed");
                return true;
            }
            else {
                System.out.println("Failed");
                return false;
            }
        }
        else {
            System.out.println("This dude can not jump!");
            return false;
        }
    }
}
