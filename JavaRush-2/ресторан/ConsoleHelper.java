package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConsoleHelper {

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException{
        return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException{
        writeMessage(Dish.allDishesToString());
        List<String> dishes =
                Arrays.asList(Dish.allDishesToString().replaceAll(",", "").split(" "));

        List<Dish> order = new ArrayList<>();

        writeMessage("Вводите название блюда или 'exit' для завершения заказа");
        String choose = readString();
        while (!choose.equals("exit")) {
            if(dishes.contains(choose))
                order.add(Dish.valueOf(choose));
            else
                writeMessage("Такого блюда нет");

            choose = readString();
        }

        return order;
    }
}
