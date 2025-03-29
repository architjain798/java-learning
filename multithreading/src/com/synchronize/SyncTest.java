package com.synchronize;

class MyThreadSync extends Thread {

    private Counter counter;

    public MyThreadSync(Counter counter, String threadName) {
        super(threadName);
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            counter.increment();
        }
    }

}

class Counter {

    private int count;

    public Counter() {
        this.count = 0;
    }

    public synchronized void increment() {
        count++;

        // this section is critical section
    }

    public void incrementNew() {
        synchronized (this) {
            count++;
        }
    }

    public int getCount() {
        return count;
    }
}

public class SyncTest {

    public static void main(String[] args) throws InterruptedException {
        Counter c1 = new Counter();
        MyThreadSync t1 = new MyThreadSync(c1, "t-1");
        MyThreadSync t2 = new MyThreadSync(c1, "t-2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(c1.getCount());
    }
}

// when we were not using synchronized the multiple thread were trying to update the value at same time
// due to this the result were unpredicatable
// this is called RACE CONDITION

// WHEN WE APPLY synchronized keyword to achieve the MUTUAL EXCULSION
// it ensures that multiple thread do not access the critical section simultaneosuly
