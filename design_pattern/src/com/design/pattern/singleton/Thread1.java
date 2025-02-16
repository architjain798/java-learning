package com.design.pattern.singleton;

public class Thread1 extends Thread{
    // Constructor to set the thread name
    public Thread1(String name) {
        super(name);
    }

    public static void main(String[] args) {
        Thread t1 = new Thread1("thread-1");
        Thread t2 = new Thread1("thread-2");

        System.out.println("hello");
        t1.start();
        t2.start();
    }

    @Override
    public void run() {
        // for (int i = 0; i < 10; i++) {
        //     System.out.println(this.getName() + ": " + i);
        // }

        SingletonDesignPattern obj = SingletonDesignPattern.getInstance();
        System.out.println(this.getName()+"----"+obj.hashCode());
    }

    
}
