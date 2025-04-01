package com.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteCounter {

    private int count = 0;

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    private final Lock readLock = lock.readLock();

    private final Lock writeLock = lock.writeLock();

    public void incremenet() {
        writeLock.lock();
        try {
            count++;
        } finally {
            writeLock.unlock();
        }
    }

    public int getCount() {
        readLock.lock();
        try {
            return count;
        } finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteCounter counter = new ReadWriteCounter();

        Runnable readTask = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + " read: " + counter.getCount());
                }
            }

        };

        Runnable writeTask = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    counter.incremenet();
                    System.out.println(Thread.currentThread().getName() + " incremented! ");
                }
            }

        };

        Thread writerThread = new Thread(writeTask, "writerThread");
        Thread readerThread = new Thread(readTask, "readerThread 1");
        Thread readerThread2 = new Thread(readTask, "readerThread 2");

        readerThread2.start();
        readerThread.start();
        writerThread.start();

    }

}
// jab read thread read kr rha hoga
// write lock read krne ka wait krega
// vo lock release krega
// tab ki write hoga vrna read aur write inconsisnent ho jaenge

// Deadlocks typically occur when four conditions are met
// simultaneously:
// 1. Mutual Exclusion: Only one thread can access a resource at a
// time.
// 2. Hold and Wait: A thread holding at least one resource is waiting
// to acquire additional resources held by other threads.
// 3. No Preemption: Resources cannot be forcibly taken from
// threads holding them.
// 4. Circular Wait: A set of threads is waiting for each other in a
// circular chain.

