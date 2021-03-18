package com.javarush.task.task29.task2909.car;

import java.util.Date;

public abstract class Car {
    static public final int TRUCK = 0;
    static public final int SEDAN = 1;
    static public final int CABRIOLET = 2;

    double fuel;

    public double summerFuelConsumption;
    public double winterFuelConsumption;
    public double winterWarmingUp;

    private int type;

    private boolean driverAvailable;
    private int numberOfPassengers;

    protected Car(int type, int numberOfPassengers) {
        this.type = type;
        this.numberOfPassengers = numberOfPassengers;
    }

    public void fill(double numberOfLiters) throws Exception {
        if (numberOfLiters < 0)
        { throw new Exception();
       }
        fuel += numberOfLiters;

    }

    public double getTripConsumption(Date date, int length, Date SummerStart, Date SummerEnd) {
        double consumption;
        if (isSummer(date,SummerStart,SummerEnd)) {
            consumption = getSummerConsumption(length);
        } else {
            consumption = getWinterConsumption(length);
        }
        return consumption;
    }

    public int getNumberOfPassengersCanBeTransferred() {
        if (canPassengersBeTransferred()){
                    return numberOfPassengers;}
        return 0;
    }

    public boolean isDriverAvailable() {
        return driverAvailable;
    }

    public void setDriverAvailable(boolean driverAvailable) {
        this.driverAvailable = driverAvailable;
    }

    public void startMoving() {
        if (numberOfPassengers > 0) {
            fastenPassengersBelts();
           }
            fastenDriverBelt();

    }

    public void fastenPassengersBelts() {
    }

    public void fastenDriverBelt() {
    }

    public abstract int getMaxSpeed();

    public static Car create(int type, int numberOfPassengers) {
        if (type==TRUCK)
            return new Truck(numberOfPassengers);
        else if (type==SEDAN)
            return new Sedan(numberOfPassengers);
        else if (type==CABRIOLET)
            return new Cabriolet(numberOfPassengers);
        else return null;

    }

    public boolean isSummer(Date date, Date summerStart, Date summerEnd){
        boolean summm = false;
        if(date.after(summerStart) && date.before(summerEnd))
        {
            summm=true;
        }

        return summm;
    }

    public double getWinterConsumption(int length){

        return (double) length*winterFuelConsumption+ winterWarmingUp;
    }
    public double getSummerConsumption(int length){
        return (double) length*summerFuelConsumption;
    }

    private boolean canPassengersBeTransferred(){

        if(isDriverAvailable() && fuel>0)
        {return true;}

        return false;
    }


}