package com.javarush.task.task37.task3702.female;

import com.javarush.task.task37.task3702.AbstractFactory;
import com.javarush.task.task37.task3702.Human;


public class FemaleFactory implements AbstractFactory {


    @Override
    public Human GetPerson(int age) {
        if(age<=12){
            KidGirl girl = new KidGirl();
            return girl;
        }if(age>19){
            Woman woman = new Woman();
            return woman;
        }else {
            TeenGirl teenGirl = new TeenGirl();
            return teenGirl;
        }
    }
}
