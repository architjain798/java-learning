# Java Multithreading Complete Guide

## Introduction
Multithreading in Java allows execution of multiple threads concurrently to maximize CPU utilization. Here's how it works:

### Single-core Systems:
- Uses time-slicing and context switching
- Creates illusion of parallel execution
- OS scheduler manages thread execution

### Multi-core Systems:
- True parallel execution across cores
- JVM distributes threads across cores
- Better resource utilization

### Key Concepts:
- **Thread**: Smallest unit of execution within a process
- **Context Switching**: Process of saving/loading thread states
- **Time Slicing**: Division of CPU time into small intervals
- **Process vs Thread**: Process can have multiple threads sharing same resources

## Table of Contents
1. [Thread Creation](#1-thread-creation)
2. [Thread Lifecycle](#2-thread-lifecycle)
3. [Thread Safety](#3-thread-safety)
4. [Executor Framework](#4-executor-framework)
5. [Advanced Concurrency Tools and Their Real-World Analogies](#5-advanced-concurrency-tools-and-their-real-world-analogies)
6. [Best Practices](#6-best-practices)
7. [Code Examples](#7-code-examples)
8. [Concurrent Collections](#8-concurrent-collections)
9. [Synchronization Tools](#9-synchronization-tools)
10. [Performance Considerations](#10-performance-considerations)
11. [Deadlock Prevention Strategies](#11-deadlock-prevention-strategies)
12. [Thread Communication Best Practices](#12-thread-communication-best-practices)
13. [Spring Boot Multithreading Examples](#13-spring-boot-multithreading-examples)

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

## 5. Advanced Concurrency Tools and Their Real-World Analogies

### 5.1 ReentrantLock
A more flexible alternative to synchronized blocks. Think of it like a bank vault:
- Only one thread can access a resource at a time (exclusive access)
- The same thread can re-enter the lock multiple times (like a bank manager who can go in and out)

**Spring Boot Use Case**: Rate limiting in APIs
```java
@Service
public class RateLimiter {
    private final ReentrantLock lock = new ReentrantLock();
    private int requestCount = 0;
    
    public boolean tryRequest() {
        lock.lock();
        try {
            if (requestCount < maxRequests) {
                requestCount++;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }
}
```

### 5.2 ReadWriteLock
Optimizes for read-heavy scenarios - like a library system:
- Multiple readers can read simultaneously (many people reading books)
- Only one writer can write at a time (librarian updating catalog)
- Writes block all reads (catalog update blocks searches)

**Spring Boot Use Case**: In-memory cache
```java
@Service
public class CacheService {
    private final Map<String, Data> cache = new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    
    public Data read(String key) {
        lock.readLock().lock();
        try {
            return cache.get(key);
        } finally {
            lock.readLock().unlock();
        }
    }
    
    public void write(String key, Data value) {
        lock.writeLock().lock();
        try {
            cache.put(key, value);
        } finally {
            lock.writeLock().unlock();
        }
    }
}
```

### 5.3 CountDownLatch
Like a referee waiting for all runners to reach the finish line:
- Set number of events to wait for
- Each completion counts down
- Once all complete, waiting threads proceed

**Spring Boot Use Case**: Service startup coordination
```java
@Component
public class ServiceStartupManager {
    private final CountDownLatch startupLatch;
    
    public ServiceStartupManager(int requiredServices) {
        this.startupLatch = new CountDownLatch(requiredServices);
    }
    
    public void serviceStarted() {
        startupLatch.countDown();
    }
    
    public void waitForStartup() throws InterruptedException {
        startupLatch.await();
        log.info("All services started");
    }
}
```

### 5.4 CyclicBarrier
Like a group of cyclists at a checkpoint:
- All threads must arrive before any can proceed
- Can be reused for multiple rounds
- Great for coordinated computations

**Spring Boot Use Case**: Parallel data processing
```java
@Service
public class BatchProcessor {
    private final CyclicBarrier barrier;
    
    public void processDataInParallel(List<Data> chunks) {
        barrier = new CyclicBarrier(chunks.size(), () -> {
            log.info("All chunks processed, starting next phase");
        });
        
        chunks.forEach(chunk -> {
            CompletableFuture.runAsync(() -> {
                processChunk(chunk);
                barrier.await();
            });
        });
    }
}
```

### 5.5 CompletableFuture
Like a restaurant order system:
- Place order asynchronously (supplyAsync)
- Chain operations (thenApply like adding garnish)
- Handle errors (exceptionally like order mistakes)
- Combine results (allOf like serving all dishes together)

**Spring Boot Use Case**: Async API aggregation
```java
@Service
public class ProductService {
    public CompletableFuture<ProductDetails> getProductDetails(String productId) {
        return CompletableFuture.supplyAsync(() -> getProduct(productId))
            .thenCombine(
                getInventoryAsync(productId),
                (product, stock) -> new ProductDetails(product, stock)
            )
            .exceptionally(ex -> fallbackProductDetails(productId));
    }
}
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

## 8. Concurrent Collections

### ConcurrentHashMap
```java
ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
map.put("key", 1);
map.putIfAbsent("key", 2);
map.computeIfAbsent("key", k -> k.length());
```

### CopyOnWriteArrayList
```java
CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
list.add("item");
// Thread-safe iteration
for (String item : list) {
    System.out.println(item);
}
```

## 9. Synchronization Tools

### CountDownLatch
```java
CountDownLatch latch = new CountDownLatch(3);
// Threads call latch.countDown()
// Main thread waits
latch.await();
```

### CyclicBarrier
```java
CyclicBarrier barrier = new CyclicBarrier(3, () -> {
    System.out.println("All parties have arrived!");
});
// Threads call barrier.await()
```

### Semaphore
```java
Semaphore semaphore = new Semaphore(5);
semaphore.acquire();
try {
    // Access the resource
} finally {
    semaphore.release();
}
```

## 10. Performance Considerations

1. **Lock Granularity**
   - Fine-grained locks: Better concurrency but more overhead
   - Coarse-grained locks: Less overhead but reduced concurrency

2. **Thread Pool Sizing**
   - CPU-bound tasks: number of cores + 1
   - I/O-bound tasks: higher thread count based on expected I/O wait time

3. **Context Switching**
   - Too many threads can lead to excessive context switching
   - Monitor thread contention using profiling tools

4. **Memory Usage**
   - Each thread consumes memory for its stack
   - Consider thread pool limits based on available memory

## 11. Deadlock Prevention Strategies

1. **Lock Ordering**
   ```java
   // Always acquire locks in the same order
   public void transferMoney(Account from, Account to, double amount) {
       Account first = from.getId() < to.getId() ? from : to;
       Account second = from.getId() < to.getId() ? to : from;
       synchronized(first) {
           synchronized(second) {
               // Transfer logic
           }
       }
   }
   ```

2. **Lock Timeouts**
   ```java
   public boolean tryTransfer(Account from, Account to, double amount, long timeout) {
       if (from.tryLock(timeout, TimeUnit.MILLISECONDS)) {
           try {
               if (to.tryLock(timeout, TimeUnit.MILLISECONDS)) {
                   try {
                       // Transfer logic
                       return true;
                   } finally {
                       to.unlock();
                   }
               }
           } finally {
               from.unlock();
           }
       }
       return false;
   }
   ```

## 12. Thread Communication Best Practices

1. **Using wait/notify Correctly**
   ```java
   synchronized void waitForSignal() {
       while (!condition) {  // Use while instead of if
           try {
               wait();
           } catch (InterruptedException e) {
               Thread.currentThread().interrupt();
               return;
           }
       }
       // Process after condition is met
   }
   
   synchronized void sendSignal() {
       condition = true;
       notify();  // or notifyAll() if multiple threads might be waiting
   }
   ```

2. **BlockingQueue for Producer-Consumer**
   ```java
   BlockingQueue<Task> queue = new LinkedBlockingQueue<>(CAPACITY);
   // Producer
   queue.put(task);  // Blocks if queue is full
   // Consumer
   Task task = queue.take();  // Blocks if queue is empty
   ```

3. **CompletableFuture for Async Operations**
   ```java
   CompletableFuture<String> future = CompletableFuture
       .supplyAsync(() -> fetchDataFromDB())
       .thenApplyAsync(data -> processData(data))
       .exceptionally(ex -> handleError(ex));
   ```

## 13. Spring Boot Multithreading Examples

### 13.1 @Async Annotation Usage
```java
@Service
public class AsyncService {
    @Async
    public CompletableFuture<String> asyncMethod() {
        // Simulating long-running task
        Thread.sleep(2000);
        return CompletableFuture.completedFuture("Async task completed");
    }
}

// Configuration
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(25);
        executor.setThreadNamePrefix("AsyncThread-");
        executor.initialize();
        return executor;
    }
}
```

### 13.2 Scheduled Tasks
```java
@Component
public class ScheduledTasks {
    @Scheduled(fixedRate = 5000)
    public void scheduleFixedRateTask() {
        System.out.println("Task executed every 5 seconds");
    }
    
    @Scheduled(cron = "0 0 * * * *")
    public void scheduleTaskUsingCron() {
        System.out.println("Task executed every hour");
    }
}

// Configuration
@Configuration
@EnableScheduling
public class SchedulingConfig {
    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(5);
        scheduler.setThreadNamePrefix("scheduled-task-");
        return scheduler;
    }
}
```

### 13.3 REST API with Async Processing
```java
@RestController
@RequestMapping("/api")
public class AsyncController {
    @Autowired
    private AsyncService asyncService;
    
    @GetMapping("/async-process")
    public CompletableFuture<ResponseEntity<String>> handleAsyncRequest() {
        return asyncService.asyncMethod()
            .thenApply(result -> ResponseEntity.ok(result))
            .exceptionally(ex -> ResponseEntity.status(500)
                .body("Error: " + ex.getMessage()));
    }
}
```

### 13.4 Parallel Stream Processing
```java
@Service
public class BatchProcessingService {
    @Autowired
    private UserRepository userRepository;
    
    @Async
    public CompletableFuture<List<UserDTO>> processUsers() {
        return CompletableFuture.supplyAsync(() -> {
            return userRepository.findAll()
                .parallelStream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        });
    }
}
```

### 13.5 Event Processing with @TransactionalEventListener
```java
@Service
public class OrderService {
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    
    @Transactional
    public void createOrder(Order order) {
        // Save order
        orderRepository.save(order);
        // Publish event
        eventPublisher.publishEvent(new OrderCreatedEvent(order));
    }
}

@Component
public class OrderEventHandler {
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleOrderCreated(OrderCreatedEvent event) {
        // Process order asynchronously
        notifyExternalSystems(event.getOrder());
    }
}
```

### 13.6 Spring Batch with Multithreading
```java
@Configuration
@EnableBatchProcessing
public class BatchConfig {
    @Bean
    public Step parallelStep(StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("parallelStep")
            .<InputData, OutputData>chunk(100)
            .reader(itemReader())
            .processor(itemProcessor())
            .writer(itemWriter())
            .taskExecutor(taskExecutor())
            .build();
    }
    
    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();
        executor.setConcurrencyLimit(10);
        return executor;
    }
}
```

### Spring Boot Multithreading Best Practices

1. **Exception Handling in Async Methods**
```java
@Service
public class RobustAsyncService {
    @Async
    public CompletableFuture<String> asyncOperation() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                // Your async operation here
                return "Success";
            } catch (Exception e) {
                throw new AsyncException("Operation failed", e);
            }
        });
    }
    
    @Bean
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }
}
```

2. **Resource Management**
```java
@Configuration
public class ThreadPoolConfig {
    @Bean(destroyMethod = "shutdown")
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors());
    }
}
```

3. **Monitoring and Metrics**
```java
@Configuration
public class MonitoredAsyncConfig implements AsyncConfigurer {
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setTaskDecorator(task -> {
            long startTime = System.currentTimeMillis();
            return () -> {
                try {
                    task.run();
                } finally {
                    long duration = System.currentTimeMillis() - startTime;
                    // Log or report metrics
                    MetricsRegistry.record("async.task.duration", duration);
                }
            };
        });
        return executor;
    }
}
```

### Quick Reference: Spring Boot Threading Checklist

- [ ] Configure appropriate thread pool sizes based on workload
- [ ] Implement proper exception handling for async methods
- [ ] Use @Async with caution - consider transaction boundaries
- [ ] Monitor thread pool metrics
- [ ] Implement graceful shutdown procedures
- [ ] Use CompletableFuture for complex async workflows
- [ ] Consider using Spring Batch for large dataset processing
- [ ] Implement circuit breakers for external service calls
- [ ] Use appropriate transaction management with async operations
- [ ] Document threading behavior in API documentation

## Quick Reference: Thread Safety Checklist

- [ ] Identify shared resources
- [ ] Use appropriate synchronization mechanism
- [ ] Consider using immutable objects
- [ ] Implement proper exception handling
- [ ] Use thread-safe collections when needed
- [ ] Follow consistent lock ordering
- [ ] Implement proper shutdown procedures
- [ ] Test thoroughly under load
- [ ] Monitor performance metrics
- [ ] Document thread-safety guarantees

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