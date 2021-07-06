package ru.geekbrains12.lesson1;

public class Robot  implements CanRun, CanJump {

    private final String name;
    private int stamina;
    private final int jumpHeight;

    public Robot (String name, int stamina, int jumpHeight) {
        this.name = name;
        this.stamina = stamina;
        this.jumpHeight = jumpHeight;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean jump(int height) {
        return jumpHeight >= height;
    }

    @Override
    public boolean run(int distance) {
        return (stamina -= distance) >= 0;
    }
}
