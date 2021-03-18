package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class TestOrder extends Order {

    public TestOrder(Tablet tablet) throws IOException {
        super(tablet);
    }

    @Override
    protected void initDishes() throws IOException {
        dishes = new ArrayList<>();
        int size = (int) (Math.random() * 10);
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            dishes.add(Dish.values()[random.nextInt(Dish.values().length)]);
        }
    }
}
