package com.javarush.task.task37.task3702.male;

import com.javarush.task.task37.task3702.AbstractFactory;
import com.javarush.task.task37.task3702.Human;

public class MaleFactory implements AbstractFactory {



    @Override
    public Human GetPerson(int age) {
        if(age<=12){
            KidBoy boy = new KidBoy();
            return boy;
        }if(age>19){
            Man man = new Man();
            return man;
        }else {
            TeenBoy teenBoy = new TeenBoy();
            return teenBoy;
        }
    }
}
