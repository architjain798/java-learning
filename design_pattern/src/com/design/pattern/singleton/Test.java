package com.design.pattern.singleton;

public class Test {

    public static void main(String[] args) {
        SingletonDesignPattern obj1 = SingletonDesignPattern.getInstance();
        SingletonDesignPattern obj2 = SingletonDesignPattern.getInstance();

        System.out.println(obj1.hashCode());
        System.out.println(obj2.hashCode());
    }
}
