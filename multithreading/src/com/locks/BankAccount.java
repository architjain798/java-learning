package com.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {

    private int balance = 100;

    private final Lock lock = new ReentrantLock();

    public synchronized void wihdraw(int amount) {

        System.out.println(Thread.currentThread().getName() + " attempting to withdraw " + amount);

        if (amount <= balance) {
            System.out.println(Thread.currentThread().getName() + " proceding with withdrawal ");
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " Completed withdrawal ");
        } else {
            System.out.println(Thread.currentThread().getName() + " insufficient balance ");
        }
    }

    public void withdrawalUsingLock(int amount) {
        System.out.println(Thread.currentThread().getName() + " attempting to withdraw " + amount);
        try {
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                if (balance >= amount) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " proceding with withdrawal ");
                        Thread.sleep(3000);
                        balance -= amount;
                        System.out.println(Thread.currentThread().getName() + " Completed withdrawal ");
                    } catch (InterruptedException e) {
                    } finally {
                        lock.unlock();
                    }

                } else {
                    System.out.println(Thread.currentThread().getName() + " insufficient balance ");
                }
            }
            else{
                System.out.println(Thread.currentThread().getName() + " Could not acquire the lock, will try later ");
            }

        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Customer {

    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                // bankAccount.wihdraw(50);
                bankAccount.withdrawalUsingLock(50);
            }
        };

        Thread t1 = new Thread(task, "Thread 1");
        Thread t2 = new Thread(task, "Thread 2");

        t1.start();
        t2.start();
    }
}

// 1. Intrinsic
// These are built into every object in Java.
// You don't see them, but they're there.
// When you use a synchronized keyword,
// you're using these automatic locks.
// 2. Explicit
// These are more advanced locks you can control
// yourself using the Lock class from java.util.concurrent.locks.
// You explicitly say when to lock and unlock, giving you more
// control over how and when people can write in the notebook.
