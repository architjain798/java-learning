Here's your **interview-focused, practical, and revision-friendly notes** on the **Decorator Design Pattern in Java**, just like we did for Builder, Singleton, and Factory.

---

# 🎨 **Decorator Design Pattern – Java Notes**

---

## ✅ Definition:
Decorator Pattern allows you to **add new behavior or responsibilities** to an existing object **dynamically at runtime** **without modifying its structure.**

---

## 📦 Type:
- **Structural Design Pattern**

---

## 🧠 Real-Life Analogies:

| Example                         | Decorator Logic                                           |
|----------------------------------|-----------------------------------------------------------|
| 🍕 Pizza Toppings               | You start with plain pizza and add toppings (cheese, etc.)|
| 🎁 Gift Wrapping                | Wrapping an object to make it look better                 |
| ☕ Coffee at Café                | Base coffee + milk + sugar + caramel = Decorators         |
| 📄 Java IO (`BufferedReader`)   | Wrap `FileReader` with `BufferedReader` for extra features|

---

## ✅ When to Use:
- You want to **add behavior** to objects **without changing their class**
- You want to follow the **Single Responsibility Principle** (each decorator has its job)
- You need **flexibility in combinations** (e.g., add logging + compression + encryption)

---

## 👨‍💻 Java Code Example

```java
// Step 1: Base Interface
interface Policy {
    String getDetails();
    int getPremium();
}

// Step 2: Concrete Component
class BasePolicy implements Policy {
    public String getDetails() {
        return "Base Policy";
    }
    public int getPremium() {
        return 1000;
    }
}
```

```java
// Step 3: Abstract Decorator
abstract class PolicyDecorator implements Policy {
    protected Policy policy;

    public PolicyDecorator(Policy policy) {
        this.policy = policy;
    }

    public String getDetails() {
        return policy.getDetails();
    }

    public int getPremium() {
        return policy.getPremium();
    }
}
```

```java
// Step 4: Concrete Decorators
class AccidentalCover extends PolicyDecorator {
    public AccidentalCover(Policy policy) {
        super(policy);
    }

    public String getDetails() {
        return super.getDetails() + " + Accidental Cover";
    }

    public int getPremium() {
        return super.getPremium() + 500;
    }
}

class CriticalIllnessCover extends PolicyDecorator {
    public CriticalIllnessCover(Policy policy) {
        super(policy);
    }

    public String getDetails() {
        return super.getDetails() + " + Critical Illness Cover";
    }

    public int getPremium() {
        return super.getPremium() + 700;
    }
}
```

```java
// Step 5: Client Usage
public class Main {
    public static void main(String[] args) {
        Policy policy = new BasePolicy(); // base
        policy = new AccidentalCover(policy); // add accidental
        policy = new CriticalIllnessCover(policy); // add illness

        System.out.println(policy.getDetails());   // Output details
        System.out.println(policy.getPremium());   // Output premium
    }
}
```

---

## 📘 Real Use in Java

| Class               | Decorates                                 |
|--------------------|--------------------------------------------|
| `BufferedReader`   | Adds buffer to `Reader`                    |
| `Collections.synchronizedList()` | Adds thread safety to a List      |
| `HttpServletRequestWrapper` | Adds logic to HTTP request in Spring |
| `Logger Decorators`| Add metadata, trace, enrich logs           |

---

## 🧪 Interview Questions

### 🔹 Conceptual
1. What is the Decorator Pattern?
2. How is it different from Inheritance?
3. How does it follow Open-Closed Principle?
4. Where have you used it in Java or Spring?

### 🔹 Coding
5. Implement a policy system with add-ons using Decorator.
6. Add logging dynamically to a service call using Decorator.
7. Use Decorator to add headers to a REST response.

---

## 🌱 Decorator vs Other Patterns

| Pattern       | Purpose                                     |
|---------------|---------------------------------------------|
| **Decorator** | Add behavior to existing object dynamically |
| **Builder**   | Step-by-step object construction            |
| **Adapter**   | Convert interface of one class to another   |
| **Proxy**     | Control access (e.g., logging, caching)     |
| **Inheritance** | Compile-time extension                    |

---

## 🧠 Revision Hack:

- Think **layers on a sandwich**
- **Wrap the object** and add behavior  
- Java IO is the perfect example:  
  `BufferedReader br = new BufferedReader(new FileReader("file.txt"));`

---

## 🧪 Spring Boot Use Cases:

- ✅ Add **logging, auth, or metrics** dynamically to service layer
- ✅ Decorate **API responses** (e.g., add extra metadata to a response object)
- ✅ Create **response wrappers** for REST APIs
- ✅ Create **interceptors** in a decorator-like way

---
