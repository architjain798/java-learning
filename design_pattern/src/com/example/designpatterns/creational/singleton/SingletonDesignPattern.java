package com.example.designpatterns.creational.singleton;

import java.io.Serializable;

public class SingletonDesignPattern implements Serializable, Cloneable {
    
    private static final long serialVersionUID = 1L;
    
    // volatile keyword ensures:
    // 1. Variable value is always read from main memory, not from thread's local cache
    // 2. Prevents instruction reordering during object creation
    // 3. Provides happens-before relationship - any write to volatile field happens before any read
    //
    // Without volatile, partially constructed objects might be visible to other threads
    // because object creation is not atomic and involves 3 steps:
    // 1. Allocate memory for object
    // 2. Initialize object fields
    // 3. Assign reference to obj variable
    private static volatile SingletonDesignPattern obj;
    
    private SingletonDesignPattern() throws Exception {
        if (obj != null) {
            throw new IllegalStateException("Singleton instance already exists. Cannot create new instance.");
        }
    }
    
    public static SingletonDesignPattern getInstance() throws Exception {
        if (obj == null) {
            synchronized (SingletonDesignPattern.class) {
                if (obj == null) {
                    obj = new SingletonDesignPattern();
                }
            }
        }
        return obj;
    }
    
    private Object readResolve() {
        return obj;
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return obj;
    }
}

// Two ways to create singleton obj
// 1) Eager initialization 2) Lazy initialization
// # Phases of class loading
// 1) magical number check
// 2) load the class -> Class loader 
// 3) 3bootstrap -> rt.jar , system, extension-> lib/ext 
// link-> flag->true
// Ways in which we can break singleton design pattern
// Reflection
// Cloning
// Serialization
// Multithreading
// DB Connection
// get data from property files
// Serialization is done for an object
// Compilation is done for a class
// Example for Singleton class in java -> Runtime 
// Runtime r = Runtime.getRuntime();

