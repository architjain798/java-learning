package com.practice.javafeatures;

public interface Test {
    
    public void display();

    default void show(){
        System.out.println("Default method in interface");
    }

    static void print(){
        System.out.println("Static method in interface");
    }
}
