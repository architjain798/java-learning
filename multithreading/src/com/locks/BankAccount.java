package com.locks;

public class BankAccount {

    private int balance = 100;

    public void wihdraw(int amount) {
        System.out.println(Thread.currentThread().getName() + " attempting to withdraw " + amount);

        if (amount <= balance) {
            System.out.println(Thread.currentThread().getName() + " proceding with withdrawal ");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " Completed withdrawal ");
        } else {
            System.out.println(Thread.currentThread().getName() + " insufficient balance ");
        }
    }
}

class Customer {

    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                bankAccount.wihdraw(50);
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
