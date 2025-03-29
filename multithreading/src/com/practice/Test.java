package com.practice;

public class Test {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        System.out.println(Thread.currentThread().getName());

        // extending thread class
        World world = new World();  // NEW STATE
        world.start(); // RUNNABLE STATE

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
// Thread LifeCycle
// New: A thread is in this state when it is created but not
// yet started.
// · Runnable: After the start method is called, the thread
// becomes runnable. It's ready to run and is waiting for
// CPU time.
// . Running: The thread is in this state when it is executing.
// · Blocked/Waiting: A thread is in this state when it is
// waiting for a resource or for another thread to perform
// an action.
// · Terminated: A thread is in this state when it has
// finished executing.

