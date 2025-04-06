package com.executorframework;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
            executorService.submit(() -> System.out.println("Hello"), "successful");
        } finally {
            executorService.shutdown();
        }
    }
}

class ExecutorDemoNew {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> submit = executorService.submit(() -> 1 + 2);
        Integer i = submit.get();
        System.out.println("sum is " + i);
        executorService.shutdown();
        System.out.println(executorService.isShutdown());
        Thread.sleep(2000);
        System.out.println(executorService.isTerminated());

    }

}

class ExecutorDemo3 {

    public static void main(String[] args) {
        try (ExecutorService executorService = Executors.newFixedThreadPool(3)) {
            Callable<Integer> callable1 = () -> {
                Thread.sleep(1000);
                System.out.println("Task 1");
                return 1;
            };
            Callable<Integer> callable2 = () -> {
                Thread.sleep(1000);
                System.out.println("Task 2");
                return 2;
            };
            Callable<Integer> callable3 = () -> {
                Thread.sleep(1000);
                System.out.println("Task 3");
                return 3;
            };

            List<Callable<Integer>> ls = Arrays.asList(callable1, callable2, callable3);
            List<Future<Integer>> futures = null;
            try {
                // futures = executorService.invokeAll(ls, 1, TimeUnit.SECONDS);
                futures = executorService.invokeAll(ls);
                // Integer ifutures = executorService.invokeAny(ls);
                // System.out.println(ifutures);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

            if (futures != null) {
                for (Future<Integer> f : futures) {
                    try {
                        System.out.println(f.get());
                    } catch (CancellationException e) {
                        System.out.println(e);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                }
            }

            executorService.shutdown();
            System.out.println("Hello World");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

class ScheduleExecutorServiceDemo {

    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        // scheduler.schedule(()->System.out.println("task executed after 5 seconds delay"), 5, TimeUnit.SECONDS);
        // scheduler.shutdown();

        scheduler.scheduleAtFixedRate(() -> System.out.println("task executed after every 5 seconds! "), 5, 5, TimeUnit.SECONDS);
        scheduler.schedule(() -> {
            System.out.println("Initiating shutdown");
            scheduler.shutdown();
        }, 20, TimeUnit.SECONDS);
        

    }
}
