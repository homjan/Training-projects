package com.javarush.task.task29.task2909.human;

public class Soldier extends Human {

    private String name;
    private int age;

    public Soldier(String name1, int age1) {
        super(name1, age1);
        this.name = name1;
        this.age = age1;
    }

    public void live() {
        fight();
    }


    public void fight() {
    }

}
