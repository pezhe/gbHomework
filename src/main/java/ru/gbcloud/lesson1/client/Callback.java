package ru.gbcloud.lesson1.client;

@FunctionalInterface
public interface Callback {

    void onReceive(String message);

}
