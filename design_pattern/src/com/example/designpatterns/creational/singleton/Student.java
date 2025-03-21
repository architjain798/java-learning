package com.example.designpatterns.creational.singleton;
public class Student {
    String name;
    int num;

    public Student(String name,int num){
        this.name=name;
        this.num= num;
    }
}

class Demo {
    public static void main(String[] args) {
        Student obj = new Student("archit", 123);
        System.out.println(obj.name);
        System.out.println(obj.num);

        Student obj2 = obj;

        obj2.name = "ajay";

        System.out.println(obj2.name);
        System.out.println(obj.name);
    }
}
