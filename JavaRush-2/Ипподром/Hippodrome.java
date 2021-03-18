package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;


public class Hippodrome {
    
   private List<Horse> horses = new ArrayList<Horse>();
   
   public static Hippodrome game;
    
    public static void main(String[] args)
    {
         game = new Hippodrome(new ArrayList<>());
         
         Horse Zwezda = new Horse("Звезда", 3, 0);
         Horse Ogonek = new Horse("Огонек", 3, 0);
         Horse Belaja = new Horse("Белая", 3, 0);
          game.getHorses().add(Zwezda);
          game.getHorses().add(Ogonek);
          game.getHorses().add(Belaja);
          game.run();
        game.printWinner();

    }
    
    public Hippodrome(List<Horse> horses )
    {
        this.horses = horses;
    }
    
    public List getHorses ()
    {
        return horses;
    }
    
    public void move ()
    {
       for(Horse horse:horses) {
         horse.move();
    }
        
    }
    public void run ()
    {
        try{
            
         for (int i =1; i<=100;i++)
         {
             move();
             print();
             Thread.sleep(200);
         }
        }
        catch(Exception e)
        {}
    }
    public void print ()
    {
         for(Horse horse:horses) {
         horse.print();
          
    }
    for (int i =0; i<10;i++)
         {
             System.out.println();
         }
    
    }

    public Horse getWinner()
    { Horse horseWinner = null;
        for (Horse horse :horses) {
            if (horseWinner == null) horseWinner = horse ;
            if (horseWinner.getDistance() < horse.getDistance())
                horseWinner = horse;
        }
        return horseWinner;


    }

    public void printWinner()
    {
        System.out.println("Winner is "+getWinner().getName()+"!");
    }
    
    
}