package ru.geekbrains13.lesson5;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {

    Semaphore semaphore;

    public Tunnel(int capacity) {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        this.semaphore = new Semaphore(capacity);
    }
    @Override
    public void go(Car c) {
        try {
            try {
                //if (semaphore.availablePermits() == 0) - с этим условием сообщение о подготовке/ожидании появится
                // только если тоннель полностью занят
                    System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                semaphore.acquire();
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                semaphore.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}