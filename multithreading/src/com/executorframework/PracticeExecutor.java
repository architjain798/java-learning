package com.executorframework;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PracticeExecutor {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // ExecutorService executorService = Executors.newFixedThreadPool(2);
        // System.out.println("start");
        // executorService.execute(() -> {
        //     System.out.println("simple thread");
        // });

        // Future<Integer> future = executorService.submit(() -> {
        //     System.out.println("return thread value");
        //     return 56;
        // });
        // System.out.println("future value"+future.get());

        // System.out.println("end");
        // executorService.shutdown();




        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 1; i <= 5; i++) {
            int taskId = i;
            executor.execute(() -> System.out.println("Task " + taskId + " executed by " + Thread.currentThread().getName()));
        }
        executor.shutdown();

    }
}
