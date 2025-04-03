package com.executorframework;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestMain {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        Thread[] threads = new Thread[10];

        for (int i = 1; i <= 10; i++) {
            int number = i;
            threads[i - 1] = new Thread(() -> {
                long result = factorial(number);
                System.out.println("Factorial of " + number + " -> " + result);
            });
            threads[i - 1].start();
        }

        for (var thread : threads) {
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

class ExecutorServiceDemo {

    public static void main(String[] args) {
        ExecutorService executor = null;
        try {
            executor = Executors.newFixedThreadPool(10);
            for (int i = 1; i <= 10; i++) {
                int number = i;
                executor.submit(() -> {
                    long result = TestMain.factorial(number);
                    System.out.println("Factorial of " + number + " -> " + result);
                });
            }
        } finally {
            if (executor != null) {
                executor.shutdown();
            }
        }

    }

}

class ExecutorDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        try {
            Future<Integer> future = executorService.submit(() -> 42);
            System.out.println(future.get());

            executorService.shutdown();
            Future<String> submit = executorService.submit(() -> System.out.println("Hello"), "successfull");
        } finally {
            executorService.shutdown();
        }
    }
}

class ExecutorDemoNew {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        try (ExecutorService executorService = Executors.newFixedThreadPool(2)) {
            Future<Integer> submit = executorService.submit(() -> 1 + 2);
            Integer i = submit.get();
            System.out.println("sum is " + i);
            System.out.println(executorService.isShutdown());
            Thread.sleep(2000);
            System.out.println(executorService.isTerminated());
        }
    }
}
