package com.practice;

public class Test {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        System.out.println(Thread.currentThread().getName());

        // extending thread class
        World world = new World();
        world.start();

        // implememting runnable interface
        WorldModern worldModern = new WorldModern();
        Thread t1 = new Thread(worldModern);
        t1.start();

        // main thread it is
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName());
        }
    }
}
