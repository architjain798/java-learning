package com.practice2;

public class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println("STARTED RUNNING LOOP");
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "--" + i);
        }
        System.out.println("END OF LOOP");
        try {
            //Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread t1 = new MyThread();
        System.out.println(t1.getState());
        t1.start();
        System.out.println(t1.getState());
        Thread.sleep(200);
        System.out.println(t1.getState());
        t1.join();
        System.out.println(t1.getState());
    }
}

class PracticeJoin {

    public static void main(String[] args) throws InterruptedException {
        MyThread t1 = new MyThread();
        t1.start();

        t1.join();

        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "--" + i);
        }
    }
}

// Thread LifeCycle
// New: A thread is in this state when it is created but not
// yet started.
// · Runnable: After the start method is called, the thread
// becomes runnable. It's ready to run and is waiting for
// CPU time.
// . Running: The thread is in this state when it is executing.
// · Blocked/Waiting: A thread is in this state when it is
// waiting for a resource or for another thread to perform
// an action.
// · Terminated: A thread is in this state when it has
// finished executing.
// jo thread jisko join krta hai usko rukna pad jaata hai

/* 
JOIN METHOD
*/
// hamre mein t1 ne main ko join kr a
// matlab main ko rukna pad gya

// when to use extends Thread vs Runnable interaface
// sometime we have to extend the class make thread as well then in that case we need to implement the Runnabel interafe
