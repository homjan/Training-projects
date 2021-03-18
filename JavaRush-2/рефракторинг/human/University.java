package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University {

    private List<Student> students = new ArrayList<>();
    private String name;
    private int age;

    public University(String name, int age) {
        this.name = name;
        this.age = age;

    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public Student getStudentWithAverageGrade(double averageGrade) {
        Student stud=null;
        for (Student s:students) {
            if (s.getAverageGrade()==averageGrade){
                stud=s;
            }
        }
        return stud;
    }

    public Student getStudentWithMaxAverageGrade() {
        Student stud = null;
        double maxAvaregeGrade=0;
        for (Student s:students) {
            if (s.getAverageGrade()>maxAvaregeGrade){
                maxAvaregeGrade=s.getAverageGrade();
                stud=s;
            }
        }
        return stud;
    }

    public Student getStudentWithMinAverageGrade() {
        Student stud = null;
        double minAverageGrade=100;
        for (Student s:students) {
            if(s.getAverageGrade()<minAverageGrade){
                minAverageGrade=s.getAverageGrade();
                stud=s;
            }
        }
        return stud;
    }
   public void expel(Student student){
        students.remove(student);
    }

}