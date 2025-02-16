package com.design.pattern.singleton;

public class SingletonDesignPattern {
    static SingletonDesignPattern obj ;
    
    private SingletonDesignPattern(){}

    public static SingletonDesignPattern getInstance(){
        if(obj == null){
            obj = new SingletonDesignPattern();
        }
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