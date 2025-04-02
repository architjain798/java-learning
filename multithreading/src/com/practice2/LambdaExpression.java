package com.practice2;

public class LambdaExpression {

    public static void main(String[] args) {
        Thread t1 = new Thread(()-> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i*2);
            }
        });

        Runnable task = () ->{
            for (int i = 0; i < 10; i++) {
                System.out.println(" runnable "+i);
            }
        };
        task.run();
        

        t1.start();
    }
}
