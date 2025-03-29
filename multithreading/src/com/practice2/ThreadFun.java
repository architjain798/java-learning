package com.practice2;

class MyThreadFun extends Thread {

    public MyThreadFun(String name) {
        super(name);
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                // Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "--" + i + " Priority " + Thread.currentThread().getPriority());
            }
            while (true) {
                System.out.println(Thread.currentThread().getName() + "--" + " Priority " + Thread.currentThread().getPriority());
            }
        } catch (Exception e) {
        }
    }

}

public class ThreadFun {

    public static void main(String[] args) throws InterruptedException {
        MyThreadFun t1 = new MyThreadFun("t1");
        MyThreadFun t2 = new MyThreadFun("t2");

        t1.setPriority(Thread.MAX_PRIORITY);
        t1.setDaemon(true);
        // t2.setPriority(5);

        t1.start();
        // t1.join();
        // t2.start();

        // t1.interrupt();
    }
}

// start join run sleep setPriority interrupt yield


// DAEMON THREAD
