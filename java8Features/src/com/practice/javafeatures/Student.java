package com.practice.javafeatures;

import java.util.function.Consumer;

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
