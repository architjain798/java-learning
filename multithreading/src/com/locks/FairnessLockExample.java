package com.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairnessLockExample {

    private final Lock lock = new ReentrantLock(true);

    public void accessResouce() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " acquired the lock ");
            Thread.sleep(3000);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.println(Thread.currentThread().getName() + " released the lock ");
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        FairnessLockExample fairnessLockExample = new FairnessLockExample();

        Runnable task = new Runnable() {
            @Override
            public void run() {

                fairnessLockExample.accessResouce();
            }
        };

        Thread t1 = new Thread(task, "Thread 1");
        Thread t2 = new Thread(task, "Thread 2");
        Thread t3 = new Thread(task, "Thread 3");

        t1.start();
        t2.start();
        t3.start();
    }
}


// ReentrantLock(true);
// tue krne se ye hota hai har thread ko mauka milta hai 
// order FIFO hoga
// agar false hoga to har thread ko shayad aisa ho kabhi mauka hi na mil pae
// usse starvation bolte hai

// toffee ek ek krke sabko milegi vrna aisa hota hai agar fairness false hoti to bheed lag jati
// to shayad kisi ko toffee nhi mil pati


// DISADVANTAGE OF SYNCROSIZATION
// 1) Fairness
// 2) Blocking
// 3) Interruptiblity
// 4) Read/ Write Locking