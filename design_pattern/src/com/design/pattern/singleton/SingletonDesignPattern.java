package com.design.pattern.singleton;

public class SingletonDesignPattern {
    static SingletonDesignPattern obj = new SingletonDesignPattern();;
    
    private SingletonDesignPattern(){}

    public static SingletonDesignPattern getInstance(){
        return obj;
    }
}
