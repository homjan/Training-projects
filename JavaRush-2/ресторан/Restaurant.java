package com.javarush.task.task27.task2712;


import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Restaurant {

    private static final int ORDER_CREATING_INTERVAL = 100;
    private static final LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();


    public static void main(String[] args) {
        Waiter waiter = new Waiter();

        Cook cookB = new Cook("Ben");
        cookB.addObserver(waiter);
        cookB.setQueue(orderQueue);

        Cook cookA = new Cook("Adam");
        cookA.addObserver(waiter);
        cookA.setQueue(orderQueue);



        List<Tablet> tablets = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Tablet tablet = new Tablet(i);
            tablet.setQueue(orderQueue);

            tablets.add(tablet);
        }

        Thread thread = new Thread(new RandomOrderGeneratorTask(tablets, ORDER_CREATING_INTERVAL));
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();

        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printActiveVideoSet();
        directorTablet.printArchivedVideoSet();
        directorTablet.printCookWorkloading();
    }
}
