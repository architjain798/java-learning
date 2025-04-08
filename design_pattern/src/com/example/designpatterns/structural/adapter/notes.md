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
