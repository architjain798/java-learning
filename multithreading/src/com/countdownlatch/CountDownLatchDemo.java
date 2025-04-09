package com.countdownlatch;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

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
    }
}

class DependentService implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " service started");
        Thread.sleep(2000);
        return "ok";
    }

}
