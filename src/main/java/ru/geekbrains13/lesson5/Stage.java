package ru.geekbrains13.lesson5;

public abstract class Stage {
    protected int length;
    protected String description;
    //public String getDescription() {return description;} - не используется
    public abstract void go(Car c);
}