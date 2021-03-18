package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Objects;
import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;


public class Cook extends Observable implements Runnable{

    private String name;
    private boolean busy;

    private boolean stopped = false;
    private boolean caught = false;

    private LinkedBlockingQueue queue;

    public void setQueue(LinkedBlockingQueue queue) {
        this.queue = queue;
    }

    public Cook(String name) {
        this.name = name;
    }


    public boolean isBusy() {
        return busy;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;

        if(obj == null || obj.getClass() != this.getClass())
            return false;

        Cook c = (Cook) obj;
        return Objects.equals(name, c.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public void startCookingOrder(Order order) {
        busy = true;
        StatisticManager statisticManager = StatisticManager.getInstance();
        ConsoleHelper.writeMessage("Start cooking - " + order + ", cooking time " + order.getTotalCookingTime() + "min");

        statisticManager.register(new CookedOrderEventDataRow(
                order.getTablet().toString(), name, order.getTotalCookingTime() * 60, order.getDishes()
        ));

        try {
            Thread.sleep(order.getTotalCookingTime() * 10);
        } catch (InterruptedException e) {}

        setChanged();
        notifyObservers(order);

        busy = false;
    }

    @Override
    public void run() {
        while (!stopped){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                ConsoleHelper.writeMessage(e.getMessage());
            }
            if (queue.peek()!=null){

                if (!this.isBusy()) {
                    try{
                        //final Cook cookFinal = cook;
                        Order order = (Order) queue.take();
                        if (order!=null){
                            //Thread tr = new Thread(()->
                            startCookingOrder(order);
                            //tr.start();
                        }
                    }
                    catch (InterruptedException e){caught = true;}
                }
            }
            if (caught&&queue.isEmpty()) stopped=true;
        }
    }
}
