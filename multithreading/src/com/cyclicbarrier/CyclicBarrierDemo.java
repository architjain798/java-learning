package com.cyclicbarrier;

import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierDemo {

    public static void main(String[] args) {
        int numberOfServices = 3;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfServices);
        CyclicBarrier barrier = new CyclicBarrier(numberOfServices);

        executorService.submit(new DependentService(barrier));
        executorService.submit(new DependentService(barrier));
        executorService.submit(new DependentService(barrier));

        System.out.println("Main");
        executorService.shutdown();
    }
}

class DependentService implements Callable<String> {

    private final CyclicBarrier barrier;

    public DependentService(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " service started");
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName() + " is waiting at barrier ");
        barrier.await();
        return "ok";
    }

}

// 3 friends waiting for 2 friends that inroute to reach the movie hall
