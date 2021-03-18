package com.javarush.task.task27.task2712.kitchen;


import java.util.Arrays;

public enum Dish {

    Fish(25),
    Juice(5),
    Soup(15),
    Steak(30),
    Water(3);

    private int duration;

    Dish(int time) {
        duration = time;
    }

    public int getDuration() {
        return duration;
    }

    public static String allDishesToString() {
        String s = Arrays.toString(values());
        return s.substring(1, s.length()-1);
    }
}
