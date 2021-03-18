package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.TestOrder;

import java.io.IOException;
import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet{

    final int number;
    private LinkedBlockingQueue queue;

    public void setQueue(LinkedBlockingQueue queue) {
        this.queue = queue;
    }

    public static Logger logger = Logger.getLogger(Tablet.class.getName());

    public Tablet(int number) {
        this.number = number;
    }

    public void createOrder() {
        Order order = null;
        try {
            order = new Order(this);
            ConsoleHelper.writeMessage(order.toString());
            if (!order.isEmpty()) {
                queue.add(order);
                AdvertisementManager advertisementManager = new AdvertisementManager(order.getTotalCookingTime() * 60);
                advertisementManager.processVideos();
            }
        } catch (NoVideoAvailableException e) {
            logger.log(Level.INFO, "No video is available for the order " + order);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }
    }

    public void createTestOrder() {
        TestOrder testOrder = null;
        try {
            testOrder = new TestOrder(this);
            ConsoleHelper.writeMessage(testOrder.toString());
            if (!testOrder.isEmpty()) {
                try {
                    queue.put(testOrder);
                } catch (InterruptedException e) {
                }
                AdvertisementManager advertisementManager = new AdvertisementManager(testOrder.getTotalCookingTime() * 60);
                advertisementManager.processVideos();
            }
        } catch (NoVideoAvailableException e) {
            logger.log(Level.INFO, "No video is available for the order " + testOrder);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }
    }

  /*  private void launchOrder(Order order) {
        System.out.println(order.toString());

        if (!order.isEmpty()) {
         //   setChanged();
         //   notifyObservers(order);

            AdvertisementManager manager = new AdvertisementManager(order.getTotalCookingTime() * 60);
            try {
                manager.processVideos();
            } catch (NoVideoAvailableException nvex) {
                logger.log(Level.INFO, "No video is available for the order " + order);
            }
        }
    }

*/

    @Override
    public String toString() {
        return "Tablet{number=" + number + "}";
    }
}
