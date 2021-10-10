package ru.geekbrains13.lesson5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CyclicBarrier;

public class Race {

    private final ArrayList<Stage> stages;
    private final CyclicBarrier cyclicBarrier;
    private volatile boolean hasWinner = false;

    public Race(int threadsCount, Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
        cyclicBarrier = new CyclicBarrier(threadsCount);
    }

    public CyclicBarrier getCyclicBarrier() {
        return cyclicBarrier;
    }

    public ArrayList<Stage> getStages() {
        return stages;
    }

    public synchronized boolean setWinner() {
        return !hasWinner && (hasWinner = true);
    }

}