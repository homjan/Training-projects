package com.javarush.task.task29.task2909.human;

public class BloodGroup {

    private final int code;

    private BloodGroup(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static BloodGroup first()
    {
        BloodGroup b1 = new BloodGroup(1);
        return b1;
    }

    public static BloodGroup second()
    {
        BloodGroup b2 = new BloodGroup(2);
        return b2;
    }
    public static BloodGroup third()
    {
        BloodGroup b3 = new BloodGroup(3);
        return b3;
    }
    public static BloodGroup fourth()
    {
        BloodGroup b4 = new BloodGroup(4);
        return b4;
    }
}
