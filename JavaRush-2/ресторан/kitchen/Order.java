package com.javarush.task.task27.task2712.kitchen;


import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.List;

public class Order {

    private final Tablet tablet;
    protected List<Dish> dishes;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        initDishes();
    }

    protected void initDishes() throws IOException {
        dishes = ConsoleHelper.getAllDishesForOrder();
    }

    public int getTotalCookingTime() {
        int time = 0;
        for (Dish d : dishes)
            time += d.getDuration();

        return time;
    }

    public Tablet getTablet() {
        return tablet;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public boolean isEmpty() {
        return dishes.isEmpty();
    }

    @Override
    public String toString() {
        return dishes.isEmpty() ? "" : String.format("Your order: %s of %s", dishes, tablet);
    }
}
