package com.javarush.task.task30.task3008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){

            System.out.println(message);


    }

    public static String readString(){
        String s1 = null;
        while (true){
        try {
             s1 = reader.readLine();
             break;

        }catch (IOException e)
        {
            System.out.println("Произошла ошибка при попытке ввода текста. Попробуйте еще раз.");
            
        }}
        return s1;
    }

    public static int readInt(){
        int n1 = 0;
        try {
            n1 = Integer.parseInt(readString());

        }catch (NumberFormatException e)
        {
            System.out.println("Произошла ошибка при попытке ввода числа. Попробуйте еще раз.");
            n1 = Integer.parseInt(readString());
        }
        return n1;

    }

}
