package com.countdownlatch;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // ExecutorService executorService = Executors.newFixedThreadPool(3);
        // Future<String> future1 = executorService.submit(new DependentService());
        // Future<String> future2 = executorService.submit(new DependentService());
        // Future<String> future3 = executorService.submit(new DependentService());

        // future1.get();
        // future2.get();
        // future3.get();
        // System.out.println("All dependent service finished, Starting main service...");
        // executorService.shutdown();
        int numberOfServices = 3;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfServices);
        CountDownLatch latch = new CountDownLatch(numberOfServices);

        executorService.submit(new DependentService(latch));
        executorService.submit(new DependentService(latch));
        executorService.submit(new DependentService(latch));

        latch.await();

        System.out.println("Main");
        executorService.shutdown();

    }
}

class DependentService implements Callable<String> {

    private final CountDownLatch latch;

    public DependentService(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public String call() throws Exception {
        try {
            System.out.println(Thread.currentThread().getName() + " service started");
            Thread.sleep(2000);
        } finally {
            latch.countDown();
        }

        return "ok";
    }

}
