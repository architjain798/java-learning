Absolutely! Here's your **interview-focused, real-world ready, and easy-to-understand notes** on the **Chain of Responsibility Design Pattern** in Java. Just like the ones you requested for Builder, Singleton, Factory, Decorator, and Adapter.

---

# 🔗 Chain of Responsibility (CoR) Design Pattern – Java Notes

---

## ✅ Definition:
Chain of Responsibility allows multiple objects to **handle a request in a sequence**. Each handler **decides either to process the request or pass it along** to the next handler in the chain.

---

## 📦 Type:
- **Behavioral Design Pattern**

---

## 🧠 Real-Life Analogies:

| Example                         | Explanation |
|----------------------------------|-------------|
| 📬 Customer Support Chain       | Call goes to L1 → L2 → L3 based on complexity |
| 🧾 Leave Approval System        | Team Lead → Manager → HR |
| 🚥 Interceptors / Middleware    | Request passes through multiple filters |
| 🔍 Spam Filter in Email         | Series of checks: content → blacklist → etc. |

---

## ✅ When to Use:
- You want to decouple sender and receiver.
- Multiple handlers might process a request.
- You want a **flexible chain**, where new handlers can be added without affecting others.

---

## 👨‍💻 Java Code Example

### Step 1: Create a base handler
```java
abstract class Logger {
    public static int INFO = 1;
    public static int DEBUG = 2;
    public static int ERROR = 3;

    protected int level;
    protected Logger nextLogger;

    public void setNext(Logger nextLogger) {
        this.nextLogger = nextLogger;
    }

    public void logMessage(int level, String message) {
        if (this.level <= level) {
            write(message);
        }
        if (nextLogger != null) {
            nextLogger.logMessage(level, message);
        }
    }

    protected abstract void write(String message);
}
```

### Step 2: Create concrete loggers
```java
class ConsoleLogger extends Logger {
    public ConsoleLogger(int level) {
        this.level = level;
    }

    protected void write(String message) {
        System.out.println("Console: " + message);
    }
}

class FileLogger extends Logger {
    public FileLogger(int level) {
        this.level = level;
    }

    protected void write(String message) {
        System.out.println("File: " + message);
    }
}

class ErrorLogger extends Logger {
    public ErrorLogger(int level) {
        this.level = level;
    }

    protected void write(String message) {
        System.out.println("Error: " + message);
    }
}
```

### Step 3: Client code
```java
public class ChainPatternDemo {
    private static Logger getChain() {
        Logger errorLogger = new ErrorLogger(Logger.ERROR);
        Logger fileLogger = new FileLogger(Logger.DEBUG);
        Logger consoleLogger = new ConsoleLogger(Logger.INFO);

        errorLogger.setNext(fileLogger);
        fileLogger.setNext(consoleLogger);

        return errorLogger; // Start of the chain
    }

    public static void main(String[] args) {
        Logger loggerChain = getChain();

        loggerChain.logMessage(Logger.INFO, "This is an info.");
        loggerChain.logMessage(Logger.DEBUG, "This is a debug.");
        loggerChain.logMessage(Logger.ERROR, "This is an error.");
    }
}
```

---

## 🔁 Output:
```
Console: This is an info.
File: This is a debug.
Console: This is a debug.
Error: This is an error.
File: This is an error.
Console: This is an error.
```

---

## 🌱 Real-World Usage in Java / Spring Boot

| Scenario                          | Description |
|-----------------------------------|-------------|
| **Spring Interceptors / Filters** | HTTP requests pass through multiple filters |
| **Servlet Filters**               | Chain of pre-processing logic for requests |
| **Security (Spring Security)**    | Auth → Authorization → Token checks |
| **Logging Frameworks**            | Chain loggers (console → file → DB) |
| **Validation Handlers**           | Chain of validators for request fields |

---

## 💬 Interview Questions

### 🔹 Conceptual
1. What is the Chain of Responsibility pattern?
2. How is it different from Observer or Command pattern?
3. Why is it useful to avoid tight coupling?
4. Can you change the chain dynamically?

### 🔹 Coding
5. Implement a chain of loggers (info → debug → error).
6. Design a leave approval chain (team lead → manager → HR).
7. Create a chain of filters for request validation.

---

## 📝 Detailed Interview Questions & Answers

### Q1: What is Chain of Responsibility Pattern and when should you use it?
**Answer:**
- It's a behavioral pattern that lets you pass requests along a chain of handlers
- Each handler decides to process the request or pass it to the next handler
- Used when:
  - Multiple objects can handle a request and the handler isn't known a priori
  - You want to decouple senders and receivers
  - The set of handlers can change dynamically

### Q2: Real-World Spring Boot Example: API Request Filter Chain
```java
@Component
public abstract class SecurityFilter {
    protected SecurityFilter nextFilter;
    
    public void setNext(SecurityFilter next) {
        this.nextFilter = next;
    }
    
    public abstract void doFilter(HttpRequest request);
}

@Component
class JWTAuthenticationFilter extends SecurityFilter {
    @Override
    public void doFilter(HttpRequest request) {
        if (!isValidJWT(request)) {
            throw new SecurityException("Invalid JWT");
        }
        if (nextFilter != null) {
            nextFilter.doFilter(request);
        }
    }
}

@Component
class RoleAuthorizationFilter extends SecurityFilter {
    @Override
    public void doFilter(HttpRequest request) {
        if (!hasRequiredRole(request)) {
            throw new SecurityException("Insufficient permissions");
        }
        if (nextFilter != null) {
            nextFilter.doFilter(request);
        }
    }
}

@Component
class RateLimitFilter extends SecurityFilter {
    @Override
    public void doFilter(HttpRequest request) {
        if (isRateLimitExceeded(request)) {
            throw new SecurityException("Rate limit exceeded");
        }
        if (nextFilter != null) {
            nextFilter.doFilter(request);
        }
    }
}

@Configuration
public class SecurityFilterConfig {
    @Autowired
    private JWTAuthenticationFilter jwtFilter;
    
    @Autowired
    private RoleAuthorizationFilter roleFilter;
    
    @Autowired
    private RateLimitFilter rateFilter;
    
    @PostConstruct
    public void setupChain() {
        jwtFilter.setNext(roleFilter);
        roleFilter.setNext(rateFilter);
    }
}
```

### Q3: System Design Example: Payment Processing Chain
```java
@Service
public abstract class PaymentProcessor {
    protected PaymentProcessor nextProcessor;
    
    public void setNext(PaymentProcessor next) {
        this.nextProcessor = next;
    }
    
    public abstract PaymentResult process(Payment payment);
}

@Service
class FraudCheckProcessor extends PaymentProcessor {
    @Override
    public PaymentResult process(Payment payment) {
        if (isFraudulent(payment)) {
            return PaymentResult.FRAUD_DETECTED;
        }
        return nextProcessor.process(payment);
    }
}

@Service
class BalanceCheckProcessor extends PaymentProcessor {
    @Override
    public PaymentResult process(Payment payment) {
        if (!hasSufficientBalance(payment)) {
            return PaymentResult.INSUFFICIENT_FUNDS;
        }
        return nextProcessor.process(payment);
    }
}

@Service
class TransactionProcessor extends PaymentProcessor {
    @Override
    public PaymentResult process(Payment payment) {
        try {
            executeTransaction(payment);
            return PaymentResult.SUCCESS;
        } catch (Exception e) {
            return PaymentResult.FAILED;
        }
    }
}

@Configuration
public class PaymentConfig {
    @Bean
    public PaymentProcessor paymentChain(
        FraudCheckProcessor fraudCheck,
        BalanceCheckProcessor balanceCheck,
        TransactionProcessor transactionProcessor
    ) {
        fraudCheck.setNext(balanceCheck);
        balanceCheck.setNext(transactionProcessor);
        return fraudCheck;
    }
}
```

### Q4: How is Chain of Responsibility different from Decorator Pattern?
**Answer:**
- Chain of Responsibility:
  - Each handler makes a decision to process or pass
  - Chain can be broken at any point
  - Handlers are independent
- Decorator:
  - Each decorator adds behavior and always calls next
  - Chain always completes
  - Decorators enhance functionality

### Q5: How to implement dynamic chain building in Spring Boot?
**Answer:**
```java
@Configuration
public class DynamicFilterChain {
    @Autowired
    private List<SecurityFilter> availableFilters;
    
    @Bean
    public SecurityFilter filterChain() {
        // Sort filters based on @Order annotation
        availableFilters.sort(
            Comparator.comparing(f -> 
                f.getClass().getAnnotation(Order.class).value()
            )
        );
        
        // Build chain
        for (int i = 0; i < availableFilters.size() - 1; i++) {
            availableFilters.get(i)
                .setNext(availableFilters.get(i + 1));
        }
        
        return availableFilters.get(0);
    }
}
```

### Q6: Performance Considerations
**Best Practices:**
1. Keep chains short (ideally < 5 handlers)
2. Implement early termination for common cases
3. Use builder pattern for chain construction
4. Consider using composite pattern for complex chains
5. Example with metrics:
```java
@Service
@Slf4j
public abstract class MonitoredFilter extends SecurityFilter {
    @Override
    public void doFilter(HttpRequest request) {
        long startTime = System.currentTimeMillis();
        try {
            processFilter(request);
        } finally {
            long duration = System.currentTimeMillis() - startTime;
            log.info("{} took {}ms", 
                getClass().getSimpleName(), duration);
        }
    }
    
    protected abstract void processFilter(HttpRequest request);
}
```

---

## 🔨 Practical Use Cases in Spring Boot

1. **Security Filters**
   - JWT Authentication
   - Role Authorization
   - Rate Limiting

2. **Request Processing**
   - Logging
   - Compression
   - Caching

3. **Business Logic**
   - Payment Processing
   - Order Validation
   - Document Approval

4. **Exception Handling**
   - Global Error Handling
   - Retry Logic
   - Circuit Breaking

---

## 🎯 Implementation Tips

1. Use Spring's `@Order` annotation for filter chain ordering
2. Consider using `@ConditionalOnProperty` for optional handlers
3. Implement circuit breaking for fault tolerance
4. Use builder pattern for complex chain construction
5. Add monitoring and metrics

Example:
```java
@Configuration
@EnableAspectJAutoProxy
public class ChainMonitoringConfig {
    @Bean
    public FilterChainMetrics filterChainMetrics() {
        return new FilterChainMetrics();
    }
    
    @Aspect
    public class FilterChainMetrics {
        @Around("execution(* com.example..SecurityFilter+.doFilter(..))")
        public Object measureFilterTime(ProceedingJoinPoint jp) 
            throws Throwable {
            
            String filterName = jp.getTarget()
                .getClass().getSimpleName();
            long startTime = System.currentTimeMillis();
            
            try {
                return jp.proceed();
            } finally {
                long duration = System.currentTimeMillis() - startTime;
                recordMetric(filterName, duration);
            }
        }
    }
}
```

---

## 🎯 Modern Chain of Responsibility Use Cases

### 1. API Gateway Middleware
```java
public interface GatewayFilter {
    void doFilter(HttpRequest request, HttpResponse response, FilterChain chain);
}

@Component
@Order(1)
public class AuthenticationFilter implements GatewayFilter {
    @Override
    public void doFilter(HttpRequest request, HttpResponse response, FilterChain chain) {
        if (!isAuthenticated(request)) {
            response.setStatus(401);
            return;
        }
        chain.doFilter(request, response);
    }
}

@Component
@Order(2)
public class RateLimitFilter implements GatewayFilter {
    private final RateLimiter rateLimiter;
    
    @Override
    public void doFilter(HttpRequest request, HttpResponse response, FilterChain chain) {
        if (!rateLimiter.tryAcquire()) {
            response.setStatus(429);
            return;
        }
        chain.doFilter(request, response);
    }
}
```

### 2. Event Processing Pipeline
```java
public interface EventProcessor {
    void process(Event event, ProcessorChain chain);
}

@Component
public class ValidationProcessor implements EventProcessor {
    @Override
    public void process(Event event, ProcessorChain chain) {
        if (!isValid(event)) {
            event.setStatus(EventStatus.INVALID);
            return;
        }
        chain.process(event);
    }
}

@Component
public class EnrichmentProcessor implements EventProcessor {
    @Override
    public void process(Event event, ProcessorChain chain) {
        enrichEventWithMetadata(event);
        chain.process(event);
    }
}
```

## 🎯 Decision Making Guide

### When to Use Chain of Responsibility?

1. **Request Processing**
   - Multiple handlers might process request
   - Processing order matters
   - Some handlers might reject request
   
2. **Event Processing**
   - Events need multiple processing steps
   - Steps can be added/removed dynamically
   - Some steps might stop processing
   
3. **Validation Pipeline**
   - Multiple validation rules
   - Rules can be chained
   - Early termination on failure

```java
// Decision Flow
if (multipleHandlers && sequentialProcessing) {
    if (handlersMightReject || earlyTermination) {
        use ChainOfResponsibility;
    } else if (allHandlersMustExecute) {
        use Decorator;
    }
} else {
    use Strategy or Command;
}
```

## 💡 Common Anti-patterns to Avoid

1. **Long Chains**
```java
// Bad: Too many steps
authFilter
    .setNext(rateLimitFilter)
    .setNext(cacheFilter)
    .setNext(loggingFilter)
    .setNext(metricFilter)
    .setNext(transformFilter)
    .setNext(routingFilter);

// Good: Group related filters
authenticationChain
    .setNext(performanceChain)
    .setNext(businessLogicChain);
```

2. **Tight Coupling**
```java
// Bad: Direct references
class Handler {
    private ConcreteNextHandler next;
}

// Good: Interface-based
class Handler {
    private Handler next;
}
```

## 🔍 Performance Optimization Tips

1. **Chain Caching**
```java
@Service
public class ChainCache {
    private final Map<String, Handler> chainCache = new ConcurrentHashMap<>();
    
    public Handler getChain(String type) {
        return chainCache.computeIfAbsent(type, this::buildChain);
    }
    
    private Handler buildChain(String type) {
        // Build and return chain
    }
}
```

2. **Parallel Processing**
```java
public class ParallelChain {
    private final List<Handler> handlers;
    private final ExecutorService executor;
    
    public void process(Request request) {
        CompletableFuture<?>[] futures = handlers.stream()
            .map(h -> CompletableFuture.runAsync(
                () -> h.handle(request), executor))
            .toArray(CompletableFuture[]::new);
        
        CompletableFuture.allOf(futures).join();
    }
}
```

## 🌟 Microservices Implementation

### 1. API Gateway Chain
```java
@Configuration
public class GatewayConfig {
    @Bean
    public FilterChain gatewayChain(
        AuthFilter authFilter,
        RateLimitFilter rateLimitFilter,
        RoutingFilter routingFilter
    ) {
        return new FilterChain()
            .addFilter(authFilter)
            .addFilter(rateLimitFilter)
            .addFilter(routingFilter);
    }
}
```

### 2. Event Processing Chain
```java
@Service
public class EventProcessingChain {
    private final List<EventProcessor> processors;
    
    public void processEvent(Event event) {
        new ProcessorChain(processors).process(event);
    }
}

class ProcessorChain {
    private final Iterator<EventProcessor> processors;
    
    public void process(Event event) {
        if (processors.hasNext()) {
            processors.next().process(event, this);
        }
    }
}
```

## 💡 Testing Best Practices

1. **Mock Chain Elements**
```java
@Test
void testAuthenticationChain() {
    // Arrange
    Handler mockAuth = mock(AuthHandler.class);
    Handler mockAuthorization = mock(AuthorizationHandler.class);
    
    Chain chain = new Chain()
        .addHandler(mockAuth)
        .addHandler(mockAuthorization);
    
    // Act
    chain.handle(request);
    
    // Assert
    verify(mockAuth).handle(request);
    verify(mockAuthorization).handle(request);
}
```

2. **Test Chain Building**
```java
@Test
void testChainBuilding() {
    // Given
    ChainBuilder builder = new ChainBuilder();
    
    // When
    Chain chain = builder
        .addHandler(HandlerType.AUTH)
        .addHandler(HandlerType.CACHE)
        .build();
    
    // Then
    assertThat(chain)
        .hasHandlerOfType(AuthHandler.class)
        .hasHandlerOfType(CacheHandler.class);
}
```

Remember: Chain of Responsibility is about **creating a pipeline of processors** where each processor can:
- Handle the request and/or pass it on
- Modify the request/response
- Break the chain if needed
- Be added/removed dynamically

Key Benefits:
- Decoupled processing steps
- Flexible processing order
- Easy to add/remove steps
- Clear single responsibility for each handler
