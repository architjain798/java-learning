Great! Here's a **complete, crisp, and interview-focused set of notes** on both the **Factory Design Pattern** and **Abstract Factory Pattern** in Java. These cover definitions, analogies, real-world usage, types, differences, code examples, and common interview questions.

---

# 🏭 **Factory & Abstract Factory Design Pattern – Java Notes**

---

## 🧠 **1. Factory Design Pattern**

### ✅ Definition:
Factory Pattern provides a way to **create objects without specifying the exact class** of object that will be created.

### ✅ Type:
- **Creational Design Pattern**

### ✅ Intent:
> Delegate object creation to a factory method/class based on input or configuration.

---

## 🧩 **Components**
| Role          | Description                                        |
|---------------|----------------------------------------------------|
| **Product**   | Common interface or superclass (e.g., `Notification`) |
| **ConcreteProduct** | Implementations (e.g., `EmailNotification`, `SMSNotification`) |
| **Creator (Factory)** | Contains logic to return appropriate product |

---

## 🔌 **Real-Life Analogies**
| Real-world Thing       | Factory Role                                                                  |
|------------------------|-------------------------------------------------------------------------------|
| **Vehicle Factory**    | Based on input ("car", "bike") it returns a Car or Bike                      |
| **Pizza Store**        | Based on order ("veg", "cheese"), makes corresponding Pizza                   |
| **OS Installer**       | Based on system type, installs Windows/Mac/Linux                             |

---

## ✅ Code Example:
```java
interface Notification {
    void notifyUser();
}

class EmailNotification implements Notification {
    public void notifyUser() {
        System.out.println("Sending Email Notification");
    }
}

class SMSNotification implements Notification {
    public void notifyUser() {
        System.out.println("Sending SMS Notification");
    }
}

class NotificationFactory {
    public static Notification getNotification(String type) {
        if ("EMAIL".equalsIgnoreCase(type)) return new EmailNotification();
        if ("SMS".equalsIgnoreCase(type)) return new SMSNotification();
        throw new IllegalArgumentException("Unknown type");
    }
}
```

---

## ✅ Usage:
```java
Notification notification = NotificationFactory.getNotification("EMAIL");
notification.notifyUser();
```

---

## 🟢 Pros:
- Encapsulates object creation logic
- Promotes loose coupling
- Easy to add new types

## 🔴 Cons:
- New types require modifying factory code (violates OCP)

---

## 🧪 Factory Interview Questions

1. What is the Factory Design Pattern and where is it used?
2. How is Factory different from Abstract Factory?
3. How to implement Factory Pattern in Java?
4. How does Factory promote loose coupling?
5. Can you give a real-world example of using the Factory pattern?

---

## 📝 Detailed Interview Questions & Answers

### Q1: Implementing Factory Pattern in Spring Boot
**Answer:**
```java
// Product interface
public interface PaymentProcessor {
    void processPayment(PaymentRequest request);
}

// Concrete products
@Service("creditCard")
public class CreditCardProcessor implements PaymentProcessor {
    @Override
    public void processPayment(PaymentRequest request) {
        // Process credit card payment
    }
}

@Service("paypal")
public class PayPalProcessor implements PaymentProcessor {
    @Override
    public void processPayment(PaymentRequest request) {
        // Process PayPal payment
    }
}

// Factory
@Service
public class PaymentProcessorFactory {
    private final Map<String, PaymentProcessor> processors;
    
    public PaymentProcessorFactory(Map<String, PaymentProcessor> processors) {
        this.processors = processors;
    }
    
    public PaymentProcessor getProcessor(String type) {
        PaymentProcessor processor = processors.get(type);
        if (processor == null) {
            throw new IllegalArgumentException("Unknown payment type: " + type);
        }
        return processor;
    }
}

// Usage in controller
@RestController
public class PaymentController {
    private final PaymentProcessorFactory factory;
    
    public PaymentController(PaymentProcessorFactory factory) {
        this.factory = factory;
    }
    
    @PostMapping("/payments")
    public void processPayment(@RequestBody PaymentRequest request) {
        PaymentProcessor processor = factory.getProcessor(request.getType());
        processor.processPayment(request);
    }
}
```

### Q2: Abstract Factory in Spring Boot Microservices
```java
// Abstract Factory interface
public interface StorageFactory {
    BlobStorage createBlobStorage();
    QueueStorage createQueueStorage();
    TableStorage createTableStorage();
}

// Concrete factories
@Configuration
@Profile("azure")
public class AzureStorageFactory implements StorageFactory {
    @Value("${azure.storage.connection-string}")
    private String connectionString;
    
    @Override
    @Bean
    public BlobStorage createBlobStorage() {
        return new AzureBlobStorage(connectionString);
    }
    
    @Override
    @Bean
    public QueueStorage createQueueStorage() {
        return new AzureQueueStorage(connectionString);
    }
    
    @Override
    @Bean
    public TableStorage createTableStorage() {
        return new AzureTableStorage(connectionString);
    }
}

@Configuration
@Profile("aws")
public class AWSStorageFactory implements StorageFactory {
    @Value("${aws.access-key}")
    private String accessKey;
    
    @Value("${aws.secret-key}")
    private String secretKey;
    
    @Override
    @Bean
    public BlobStorage createBlobStorage() {
        return new S3Storage(accessKey, secretKey);
    }
    
    @Override
    @Bean
    public QueueStorage createQueueStorage() {
        return new SQSStorage(accessKey, secretKey);
    }
    
    @Override
    @Bean
    public TableStorage createTableStorage() {
        return new DynamoDBStorage(accessKey, secretKey);
    }
}

// Usage in service
@Service
public class FileService {
    private final BlobStorage blobStorage;
    private final QueueStorage queueStorage;
    
    public FileService(StorageFactory factory) {
        this.blobStorage = factory.createBlobStorage();
        this.queueStorage = factory.createQueueStorage();
    }
    
    public void processFile(MultipartFile file) {
        String blobId = blobStorage.store(file);
        queueStorage.enqueue(new ProcessFileMessage(blobId));
    }
}
```

### Q3: Database Connection Factory Example
```java
@Configuration
public class DatabaseConfig {
    @Bean
    public ConnectionFactory connectionFactory(
        @Value("${db.type}") String dbType,
        @Value("${db.url}") String url,
        @Value("${db.username}") String username,
        @Value("${db.password}") String password
    ) {
        switch (dbType.toLowerCase()) {
            case "mysql":
                return new MySQLConnectionFactory(url, username, password);
            case "postgres":
                return new PostgresConnectionFactory(url, username, password);
            case "mongodb":
                return new MongoConnectionFactory(url, username, password);
            default:
                throw new IllegalArgumentException("Unknown DB type: " + dbType);
        }
    }
}

// Usage with Spring Data
@Configuration
@EnableJpaRepositories
public class JpaConfig {
    @Autowired
    private ConnectionFactory connectionFactory;
    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(connectionFactory.createDataSource());
        // ... other configuration
        return em;
    }
}
```

### Q4: Factory Method vs Abstract Factory
```java
// Factory Method Pattern
@Component
public abstract class DocumentGenerator {
    public final void generateDocument(DocumentRequest request) {
        Document doc = createDocument(); // Factory method
        doc.setContent(request.getContent());
        doc.format();
        doc.save();
    }
    
    protected abstract Document createDocument();
}

@Component("pdf")
public class PdfGenerator extends DocumentGenerator {
    @Override
    protected Document createDocument() {
        return new PdfDocument();
    }
}

@Component("word")
public class WordGenerator extends DocumentGenerator {
    @Override
    protected Document createDocument() {
        return new WordDocument();
    }
}

// Abstract Factory Pattern
public interface UIComponentFactory {
    Button createButton();
    TextField createTextField();
    Checkbox createCheckbox();
}

@Configuration
@Profile("material")
public class MaterialUIFactory implements UIComponentFactory {
    @Override
    public Button createButton() {
        return new MaterialButton();
    }
    
    @Override
    public TextField createTextField() {
        return new MaterialTextField();
    }
    
    @Override
    public Checkbox createCheckbox() {
        return new MaterialCheckbox();
    }
}
```

### Q5: Testing Factories in Spring Boot
```java
@SpringBootTest
class PaymentProcessorFactoryTest {
    @MockBean
    private CreditCardProcessor creditCardProcessor;
    
    @MockBean
    private PayPalProcessor paypalProcessor;
    
    @Autowired
    private PaymentProcessorFactory factory;
    
    @Test
    void shouldGetCorrectProcessor() {
        // Given
        String type = "creditCard";
        
        // When
        PaymentProcessor processor = factory.getProcessor(type);
        
        // Then
        assertThat(processor)
            .isInstanceOf(CreditCardProcessor.class);
    }
    
    @Test
    void shouldThrowForUnknownType() {
        // Given
        String type = "unknown";
        
        // When/Then
        assertThrows(
            IllegalArgumentException.class,
            () -> factory.getProcessor(type)
        );
    }
}
```

### Q6: Performance Considerations
**Best Practices:**
1. Use object pooling for expensive objects
2. Consider caching factory results
3. Use lazy initialization where appropriate
4. Implement proper error handling
5. Example with metrics:
```java
@Service
@Slf4j
public class MonitoredPaymentFactory {
    private final Map<String, PaymentProcessor> processors;
    private final MeterRegistry registry;
    
    public MonitoredPaymentFactory(
        Map<String, PaymentProcessor> processors,
        MeterRegistry registry
    ) {
        this.processors = processors;
        this.registry = registry;
    }
    
    public PaymentProcessor getProcessor(String type) {
        Timer.Sample sample = Timer.start(registry);
        try {
            PaymentProcessor processor = processors.get(type);
            if (processor == null) {
                registry.counter("factory.errors", 
                    "type", type).increment();
                throw new IllegalArgumentException(
                    "Unknown type: " + type);
            }
            return processor;
        } finally {
            sample.stop(registry.timer("factory.getProcessor", 
                "type", type));
        }
    }
}
```

---

## 🎯 Implementation Tips

1. Use Spring's dependency injection
2. Consider using enums for factory types
3. Implement proper error handling
4. Add monitoring and metrics
5. Use profiles for different implementations

Example:
```java
@Configuration
public class FactoryConfig {
    public enum StorageType {
        S3, AZURE_BLOB, GCS
    }
    
    @Bean
    public StorageFactory storageFactory(
        @Value("${storage.type}") StorageType type,
        ObjectMapper objectMapper
    ) {
        return switch (type) {
            case S3 -> new S3Factory(objectMapper);
            case AZURE_BLOB -> new AzureBlobFactory(objectMapper);
            case GCS -> new GCSFactory(objectMapper);
        };
    }
}
```

---

# 🧠 **2. Abstract Factory Design Pattern**

### ✅ Definition:
Abstract Factory provides an **interface for creating families of related or dependent objects** without specifying their concrete classes.

### ✅ Type:
- **Creational Design Pattern**

---

## 🔍 Factory vs Abstract Factory
| Aspect            | Factory                     | Abstract Factory                         |
|------------------|-----------------------------|-------------------------------------------|
| Creates           | One product                 | Multiple related products (family)        |
| Uses              | Single method               | Multiple factories for related objects    |
| Example           | One notification type       | UI toolkit: Buttons + TextFields          |

---

## 🔌 Real-Life Analogy:
> Imagine a furniture factory that creates Victorian-style **chairs and tables**, and another factory that makes Modern-style **chairs and tables**. Each factory returns a consistent **family of objects**.

---

## ✅ Code Example:

### 🎯 Product Interfaces
```java
interface Button {
    void click();
}

interface TextBox {
    void type();
}
```

### 🏭 Concrete Products
```java
class WindowsButton implements Button {
    public void click() { System.out.println("Windows Button clicked"); }
}

class MacButton implements Button {
    public void click() { System.out.println("Mac Button clicked"); }
}
```

### 🧱 Abstract Factory
```java
interface GUIFactory {
    Button createButton();
    TextBox createTextBox();
}
```

### 🏭 Concrete Factories
```java
class WindowsFactory implements GUIFactory {
    public Button createButton() { return new WindowsButton(); }
    public TextBox createTextBox() { return new WindowsTextBox(); }
}

class MacFactory implements GUIFactory {
    public Button createButton() { return new MacButton(); }
    public TextBox createTextBox() { return new MacTextBox(); }
}
```

---

## ✅ Usage:
```java
GUIFactory factory = new WindowsFactory();
Button btn = factory.createButton();
btn.click();
```

---

## 🧪 Abstract Factory Interview Questions

1. What is Abstract Factory and how is it different from Factory?
2. When would you use Abstract Factory over Factory?
3. How does Abstract Factory help with consistency in design?
4. Can you give a real-time system design use case?

---

## 📦 Real-World Usage in Java & Spring

| Usage Area           | Pattern Used      |
|----------------------|-------------------|
| `Spring BeanFactory` | Factory            |
| `Spring ApplicationContext` | Abstract Factory |
| `JDBC Connection`    | Factory (`DriverManager.getConnection`) |
| `DocumentBuilderFactory` | Abstract Factory |
| `LoggerFactory` from SLF4J | Factory |

---

## 🧠 Quick Revision Hack

- **Factory** = 1 Object based on input
- **Abstract Factory** = Family of related objects
- Think:  
  - Factory: “Give me **one** type of Notification”  
  - Abstract Factory: “Give me a **full UI Kit** (Button + TextBox) for Mac”

---