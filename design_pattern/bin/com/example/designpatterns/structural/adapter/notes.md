---

## 🧠 **Adapter Design Pattern – Java Notes**

### ✅ Definition:
Adapter Pattern allows **two incompatible interfaces** to work together by converting the interface of one class into an interface expected by the client.

### ✅ Type:
- Structural Design Pattern

### ✅ Intent:
> Convert the interface of a class into another interface that a client expects.

---

## 🔌 **Real-Life Analogies**
| Real-world Thing       | Adapter Role                                                                 |
|------------------------|-------------------------------------------------------------------------------|
| Mobile Charger Adapter | Converts power outlet (AC) into phone input (DC).                            |
| Language Translator    | Converts French to English so an English speaker can understand.             |
| 3-Pin to 2-Pin Plug    | Allows 3-pin devices to connect to 2-pin sockets.                             |

---

## 🧩 **Components in Java Code**
| Component | Role |
|----------|------|
| **Target** | The interface expected by the client (e.g., `Paytm`) |
| **Adaptee** | The incompatible interface (e.g., `BillDesk`) |
| **Adapter** | The class that adapts `Adaptee` to `Target` |
| **Client** | Uses the `Target` interface but is actually calling an `Adaptee` through an `Adapter` |

---

## 🔁 Types of Adapters:
1. **Object Adapter** – uses composition (preferred in Java)
2. **Class Adapter** – uses inheritance (not commonly used in Java because it doesn’t support multiple inheritance)

---

## 🛠️ Practical Java Example Recap
```java
Paytm paytm = new PaymentAdapter(new AshokIt());
paytm.getTypeOfPaymentType(); // Delegates call to AshokIt via BillDesk interface
```

---

## 💡 When to Use It?
- When you have legacy code or third-party classes that don't match your interface
- When you want to reuse a class that doesn’t implement a required interface
- When refactoring is **not possible or desirable**

---

## 🧪 Common Interview Questions

### ✅ Conceptual Questions:
1. What is the Adapter Design Pattern and when would you use it?
2. What’s the difference between Adapter and Decorator pattern?
3. What are object adapters and class adapters?
4. Can you explain Adapter Pattern with a real-world example?
5. What are pros and cons of using Adapter?

### ✅ Coding/Practical Questions:
6. Convert a legacy billing system API into a REST-consumable interface using Adapter.
7. Implement Adapter to convert `List<String>` into a `Set<String>`.
8. Create an Adapter to integrate third-party payment gateway in your Spring Boot REST API.
9. How would you use Adapter to make a class implement multiple incompatible interfaces?

---

## 🟢 Advantages
- Promotes reusability of legacy or third-party code
- Loosely coupled code
- Helps in backward compatibility

## 🔴 Disadvantages
- Adds extra layers (might make code complex if overused)
- May require changes if the Adaptee changes significantly

---

## 🔁 Quick Revision Hack
- **A**dapter is for **A**ligning incompatible APIs/interfaces.
- Think: "I have a plug that doesn’t fit; I need an **Adapter**!"
- Adapter = Bridge between **Expected Interface** ↔ **Available Interface**

---

## 🧩 Where Java/Spring Uses It
- **Java I/O**: `InputStreamReader` adapts `InputStream` to `Reader`
- **Collections**: `Arrays.asList()` adapts an array to a `List`
- **Spring Boot REST APIs**: When integrating third-party services like payment gateways, email/SMS APIs

---

## 📝 Detailed Interview Questions & Answers

### Q1: Spring Boot Third-Party Integration Example
**Answer:**
```java
// Third-party payment gateway interface
public interface PaymentGateway {
    PaymentResult charge(double amount, String currency);
    boolean refund(String transactionId);
}

// Our application's payment interface
public interface PaymentService {
    PaymentResponse processPayment(PaymentRequest request);
    boolean refundPayment(String orderId);
}

// Adapter to make PaymentGateway work with our PaymentService interface
@Service
public class PaymentGatewayAdapter implements PaymentService {
    private final PaymentGateway paymentGateway;
    private final TransactionRepository transactionRepo;
    
    public PaymentGatewayAdapter(
        PaymentGateway paymentGateway,
        TransactionRepository transactionRepo
    ) {
        this.paymentGateway = paymentGateway;
        this.transactionRepo = transactionRepo;
    }
    
    @Override
    public PaymentResponse processPayment(PaymentRequest request) {
        PaymentResult result = paymentGateway.charge(
            request.getAmount(),
            request.getCurrency()
        );
        
        return new PaymentResponse(
            result.isSuccessful(),
            result.getTransactionId(),
            result.getMessage()
        );
    }
    
    @Override
    public boolean refundPayment(String orderId) {
        Transaction tx = transactionRepo.findByOrderId(orderId);
        return paymentGateway.refund(tx.getTransactionId());
    }
}
```

### Q2: Legacy System Integration Example
```java
// Legacy XML-based system
public class LegacyCustomerSystem {
    public String getCustomerXml(String customerId) {
        return "<customer>" +
               "<id>" + customerId + "</id>" +
               "<name>John Doe</name>" +
               "</customer>";
    }
    
    public void updateCustomerXml(String customerXml) {
        // Updates customer from XML
    }
}

// Modern JSON-based interface
public interface CustomerService {
    CustomerDTO getCustomer(String id);
    void updateCustomer(CustomerDTO customer);
}

// Adapter to make legacy system work with modern interface
@Service
public class LegacyCustomerAdapter implements CustomerService {
    private final LegacyCustomerSystem legacySystem;
    private final XmlMapper xmlMapper;
    
    public LegacyCustomerAdapter(
        LegacyCustomerSystem legacySystem,
        XmlMapper xmlMapper
    ) {
        this.legacySystem = legacySystem;
        this.xmlMapper = xmlMapper;
    }
    
    @Override
    public CustomerDTO getCustomer(String id) {
        String xml = legacySystem.getCustomerXml(id);
        return xmlMapper.readValue(xml, CustomerDTO.class);
    }
    
    @Override
    public void updateCustomer(CustomerDTO customer) {
        String xml = xmlMapper.writeValueAsString(customer);
        legacySystem.updateCustomerXml(xml);
    }
}
```

### Q3: Multiple Interface Adaptation
```java
// Different messaging interfaces
interface EmailSender {
    void sendEmail(String to, String subject, String body);
}

interface SMSProvider {
    void sendSMS(String phoneNumber, String message);
}

interface PushNotifier {
    void sendPush(String userId, String title, String message);
}

// Unified notification interface
interface NotificationService {
    void notify(String recipient, String subject, String content);
}

// Adapter that supports multiple notification methods
@Service
public class NotificationAdapter implements NotificationService {
    private final EmailSender emailSender;
    private final SMSProvider smsProvider;
    private final PushNotifier pushNotifier;
    
    @Override
    public void notify(String recipient, String subject, String content) {
        if (isEmailAddress(recipient)) {
            emailSender.sendEmail(recipient, subject, content);
        } else if (isPhoneNumber(recipient)) {
            smsProvider.sendSMS(recipient, content);
        } else {
            pushNotifier.sendPush(recipient, subject, content);
        }
    }
    
    private boolean isEmailAddress(String recipient) {
        return recipient.contains("@");
    }
    
    private boolean isPhoneNumber(String recipient) {
        return recipient.matches("\\d{10,}");
    }
}
```

### Q4: Testing Adapters
```java
@SpringBootTest
class PaymentGatewayAdapterTest {
    @MockBean
    private PaymentGateway mockGateway;
    
    @MockBean
    private TransactionRepository mockRepo;
    
    @Autowired
    private PaymentGatewayAdapter adapter;
    
    @Test
    void shouldAdaptSuccessfulPayment() {
        // Given
        PaymentRequest request = new PaymentRequest(100.0, "USD");
        PaymentResult gatewayResult = new PaymentResult(
            true, "TX123", "Success"
        );
        
        when(mockGateway.charge(100.0, "USD"))
            .thenReturn(gatewayResult);
            
        // When
        PaymentResponse response = adapter.processPayment(request);
        
        // Then
        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getTransactionId())
            .isEqualTo("TX123");
    }
    
    @Test
    void shouldAdaptFailedPayment() {
        // Given
        PaymentRequest request = new PaymentRequest(100.0, "USD");
        PaymentResult gatewayResult = new PaymentResult(
            false, null, "Insufficient funds"
        );
        
        when(mockGateway.charge(100.0, "USD"))
            .thenReturn(gatewayResult);
            
        // When
        PaymentResponse response = adapter.processPayment(request);
        
        // Then
        assertThat(response.isSuccess()).isFalse();
        assertThat(response.getMessage())
            .isEqualTo("Insufficient funds");
    }
}
```

### Q5: Event Adapter Pattern
```java
// Legacy event system
interface LegacyEventListener {
    void onEvent(Map<String, Object> eventData);
}

// Modern event system
interface EventHandler<T> {
    void handle(T event);
}

// Event adapter
@Component
public class LegacyEventAdapter implements LegacyEventListener {
    private final List<EventHandler<CustomEvent>> handlers;
    private final ObjectMapper mapper;
    
    @Override
    public void onEvent(Map<String, Object> eventData) {
        CustomEvent event = mapper.convertValue(
            eventData, 
            CustomEvent.class
        );
        
        handlers.forEach(handler -> handler.handle(event));
    }
}
```

### Q6: Configuration and Performance Considerations
**Best Practices:**
1. Use composition over inheritance
2. Keep adapters simple and focused
3. Consider caching for expensive adaptations
4. Handle errors gracefully
5. Example with caching and metrics:
```java
@Service
@Slf4j
public class CachedCustomerAdapter implements CustomerService {
    private final LegacyCustomerSystem legacySystem;
    private final Cache<String, CustomerDTO> cache;
    private final MeterRegistry registry;
    
    @Override
    public CustomerDTO getCustomer(String id) {
        Timer.Sample sample = Timer.start(registry);
        try {
            return cache.get(id, this::fetchFromLegacy);
        } catch (Exception e) {
            registry.counter("adapter.errors").increment();
            throw e;
        } finally {
            sample.stop(registry.timer("adapter.getCustomer"));
        }
    }
    
    private CustomerDTO fetchFromLegacy(String id) {
        String xml = legacySystem.getCustomerXml(id);
        return xmlMapper.readValue(xml, CustomerDTO.class);
    }
}
```

---

## 🎯 Implementation Tips

1. Keep adapters simple and focused
2. Use Spring's dependency injection
3. Consider caching for expensive operations
4. Add proper error handling
5. Include monitoring and metrics

Example:
```java
@Configuration
public class AdapterConfig {
    @Bean
    public Cache<String, CustomerDTO> customerCache() {
        return Caffeine.newBuilder()
            .maximumSize(10_000)
            .expireAfterWrite(Duration.ofMinutes(5))
            .build();
    }
    
    @Bean
    public CustomerService customerService(
        LegacyCustomerSystem legacySystem,
        Cache<String, CustomerDTO> cache,
        MeterRegistry registry
    ) {
        return new CachedCustomerAdapter(
            legacySystem, 
            cache, 
            registry
        );
    }
}
```

---
