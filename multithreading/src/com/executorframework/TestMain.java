package com.executorframework;

public class TestMain {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        Thread[] threads = new Thread[10];

        for (int i = 1; i <= 10; i++) {
            int number  = i;
            threads[i-1] = new Thread(() -> {
                long result = factorial(number);
                System.out.println("Factorial of "+number+" -> "+result);
            });
            threads[i-1].start();
        }

        for(var thread : threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Total Time Taken: " + (System.currentTimeMillis() - startTime));

    }

    public static int factorial(int n) {
        if (n <= 1) {
            return 1;
        }

        return n * factorial(n - 1);
    }
}
