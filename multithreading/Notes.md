# Java Multithreading Complete Guide

## Table of Contents
1. [Thread Creation](#1-thread-creation)
2. [Thread Lifecycle](#2-thread-lifecycle)
3. [Thread Safety](#3-thread-safety)
4. [Executor Framework](#4-executor-framework)
5. [Advanced Concepts](#5-advanced-concepts)
6. [Best Practices](#6-best-practices)
7. [Code Examples](#7-code-examples)

## 1. Thread Creation

### Three Ways to Create Threads

#### 1.1 Extending Thread Class
```java
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Thread running: " + Thread.currentThread().getName());
    }
}

// Usage
MyThread thread = new MyThread();
thread.start();
```

#### 1.2 Implementing Runnable Interface
```java
class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Runnable running: " + Thread.currentThread().getName());
    }
}

// Usage
Thread thread = new Thread(new MyRunnable());
thread.start();
```

#### 1.3 Lambda Expression
```java
Thread thread = new Thread(() -> {
    System.out.println("Lambda running: " + Thread.currentThread().getName());
});
thread.start();
```

## 2. Thread Lifecycle

### States
1. **NEW** - Thread created but not yet started
2. **RUNNABLE** - Thread executing or ready to execute
3. **BLOCKED** - Thread blocked waiting for monitor lock
4. **WAITING** - Thread waiting indefinitely for another thread
5. **TIMED_WAITING** - Thread waiting for specified time
6. **TERMINATED** - Thread completed execution

## 3. Thread Safety

### 3.1 Synchronization
```java
public class Counter {
    private int count = 0;
    
    public synchronized void increment() {
        count++;
    }
    
    public synchronized int getCount() {
        return count;
    }
}
```

### 3.2 Volatile Keyword
```java
public class SharedFlag {
    private volatile boolean flag = false;
    
    public void setFlag(boolean value) {
        flag = value;
    }
    
    public boolean isFlag() {
        return flag;
    }
}
```

## 4. Executor Framework

### 4.1 Types of Executors
```java
// Single Thread Executor
ExecutorService single = Executors.newSingleThreadExecutor();

// Fixed Thread Pool
ExecutorService fixed = Executors.newFixedThreadPool(5);

// Cached Thread Pool
ExecutorService cached = Executors.newCachedThreadPool();

// Scheduled Thread Pool
ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(4);
```

### 4.2 Submitting Tasks
```java
// Submit Runnable
Future<?> future1 = executor.submit(() -> System.out.println("Task"));

// Submit Callable
Future<Integer> future2 = executor.submit(() -> {
    return 42;
});
```

## 5. Advanced Concepts

### 5.1 CompletableFuture
```java
CompletableFuture<String> future = CompletableFuture
    .supplyAsync(() -> "Hello")
    .thenApply(s -> s + " World")
    .thenApply(String::toUpperCase);
```

### 5.2 ThreadLocal
```java
private static ThreadLocal<SimpleDateFormat> dateFormat = 
    ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));
```

## 6. Best Practices

1. Always use `try-finally` with explicit locks
2. Prefer executor framework over raw threads
3. Keep synchronized blocks as small as possible
4. Always handle InterruptedException properly
5. Use thread pools for better resource management
6. Implement proper shutdown procedures
7. Avoid sharing mutable state between threads
8. Use concurrent collections when appropriate
9. Implement proper exception handling in threads
10. Test concurrent code thoroughly

## 7. Code Examples

### 7.1 Producer-Consumer Pattern
```java
public class ProducerConsumer {
    private static final BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);

    static class Producer implements Runnable {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    queue.put(i);
                    System.out.println("Produced: " + i);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static class Consumer implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    Integer value = queue.take();
                    System.out.println("Consumed: " + value);
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
```

### 7.2 Thread Pool Example
```java
public class ThreadPoolExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        
        try {
            for (int i = 0; i < 5; i++) {
                final int taskId = i;
                executor.submit(() -> {
                    System.out.println("Task " + taskId + 
                        " executed by " + Thread.currentThread().getName());
                    return taskId;
                });
            }
        } finally {
            executor.shutdown();
            try {
                if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                    executor.shutdownNow();
                }
            } catch (InterruptedException e) {
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }
}
```

## Common Issues to Watch Out For

1. **Deadlocks**
   - Circular dependency between threads
   - Resource starvation
   
2. **Race Conditions**
   - Unsynchronized access to shared resources
   - Incorrect ordering of operations

3. **Memory Consistency Errors**
   - Lack of proper synchronization
   - Missing volatile keyword

4. **Thread Leaks**
   - Not shutting down executor services
   - Unclosed resources

## Additional Resources
- [Java Concurrency in Practice](https://jcip.net/)
- [Oracle's Concurrency Tutorial](https://docs.oracle.com/javase/tutorial/essential/concurrency/)
- [Java Memory Model](https://docs.oracle.com/javase/specs/jls/se8/html/jls-17.html)