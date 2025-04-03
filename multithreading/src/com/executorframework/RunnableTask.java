package com.executorframework;

import java.lang.Runnable;

public class RunnableTask implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " started execution.");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupted state
            System.out.println("Thread was interrupted: " + e.getMessage());
        }

        System.out.println(Thread.currentThread().getName() + " finished execution.");
    }
}
