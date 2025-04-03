package com.executorframework;

import java.util.concurrent.Callable;

public class CallableTask implements Callable {

    @Override
    public Object call() throws Exception {
        Thread.sleep(5000);
        return 1;
    }

}
