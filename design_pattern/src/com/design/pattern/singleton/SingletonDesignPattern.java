package com.design.pattern.singleton;

public class SingletonDesignPattern {
    // The object will be loaded at class loading only even without calling the get instance
    // Eager Initialization
    static SingletonDesignPattern obj = new SingletonDesignPattern();
    
    private SingletonDesignPattern(){}

    public static SingletonDesignPattern getInstance(){
        return obj;
    }
}
