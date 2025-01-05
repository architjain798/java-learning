package com.practice.javafeatures;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class Student implements Test {

    public static void main(String[] args) {
        Student student = new Student();
        student.show();
        Test.print();
        // Student.print();
        // student.print();
        student.display();
        

        Consumer consumer = (s) -> {
            System.out.println(s);
        };
        consumer.accept("Hello");

        Predicate <Integer> predicate = (Integer num) -> {
            if(num % 2 == 0) return true;

            return false;
        };
        System.out.println(predicate.test(5));

    }

    @Override
    public void display() {
        System.out.println("Display method in Student class");
    }

    @Override
    public void show() {
        Test.super.show();
        // System.out.println("Show method in Student class");
    }

    // static void print(){
    //     System.out.println("Static method in class");
    // }
}
