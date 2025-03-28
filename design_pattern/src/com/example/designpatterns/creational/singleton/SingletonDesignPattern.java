package com.example.designpatterns.creational.singleton;

import java.io.Serializable;

public class SingletonDesignPattern implements Serializable, Cloneable {

    static SingletonDesignPattern obj;

    private SingletonDesignPattern() throws Exception {
        // to prevent it from reflection
        if (obj != null) {
            System.out.println("Object is trying to create using reflection");
            throw new Exception("Object can be created");
        }
    }

    public static SingletonDesignPattern getInstance() throws Exception {
        if (obj == null) {
            obj = new SingletonDesignPattern();
        }
        return obj;
    }

    // This method is called during deserialization
    Object readResolve() {
        return obj; // Return the existing instance
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // return super.clone(); // if we are not replacing then singleton will break
        return obj;
    }
}

// Two ways to create singleton obj
// 1) Eager intitalization 2) Lazy intitalization
// # Phases of class loading
// 1) magical number check
// 2) load the class -> Class loader 
// 3) 3bootstrap -> rt.jat , system, extension-> lib/ext 
// link-> flag->true
// Ways in which we can break singleton design pattern
// Reflection
// Cloning
// Serialization
// Multithreading
// DB Connection
// get data from property files
// Serilization is done for an object
// Compilation is done for a class
// Example for Singleton class in java -> Runtime 
// Runtime r = Runtime.getRuntime();

