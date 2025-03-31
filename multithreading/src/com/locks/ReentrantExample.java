package com.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantExample {

    private final Lock lock = new ReentrantLock();

    public void outerMethod() {
        // lock.lockInterruptibly(); // ismien agar koi new thread aaya to vo isko interupt kr dega

        lock.lock();
        try {
            System.out.println("Outer Method");
            innerMethod();
        } finally {
            lock.unlock();
        }
    }

    public void innerMethod() {
        lock.lock();
        try {
            System.out.println("Inner Method");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        // in this scenarios when thread will enter outer method 
        // tread will acquire lock and enter the inner method
        // but lock has already acuiqred so not able to enter the
        // innerMethod

        // pr ye ReentrantLock isloye aisa nhi hoga
        // balki jab outermethod mein lock acquire hua
        // phir jab inner ne acquire kra to isse like 
        // pehle main building mein enter hue
        // phir dusre room pe lock acquire kr liya
    }
}
