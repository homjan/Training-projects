package com.javarush.task.task27.task2712;

import java.util.List;

public class RandomOrderGeneratorTask implements Runnable {

    private List<Tablet> tablets;
    private final int ORDER_CREATING_INTERVAL;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval) {
        this.tablets = tablets;
        this.ORDER_CREATING_INTERVAL = interval;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            Tablet selectTablet = tablets.get((int)(Math.random() * tablets.size()));
            selectTablet.createTestOrder();
            try {
                Thread.sleep(ORDER_CREATING_INTERVAL);
            }catch (InterruptedException ine) {break;}
        }
    }
}
