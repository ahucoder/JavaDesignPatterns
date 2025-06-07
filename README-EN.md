# Java Design Patterns

A comprehensive collection of Java design pattern implementations, including the 23 GoF patterns as well as additional patterns commonly used in modern development. Each pattern includes detailed explanations, UML diagrams, and real-world example code.

## Why Learn Design Patterns?

Design patterns offer reusable solutions to common software development problems. They provide:

* **Proven Solutions**: Time-tested approaches to specific challenges
* **Code Reusability**: Reduce duplicated code
* **Maintainability**: Make code easier to understand and modify
* **Team Collaboration**: Provide a shared design vocabulary

## Pattern Categories

### Creational Patterns

| Pattern                                         | Description                                       | Example                              |
| ----------------------------------------------- | ------------------------------------------------- | ------------------------------------ |
| [Singleton](src/main/java/creational/singleton) | Ensure a class has only one instance              | Database connection pool             |
| [Factory Method]()                              | Create objects without specifying the exact class | Cross-platform UI component creation |
| [Abstract Factory]()                            | Create families of related objects                | Cross-platform UI widget suites      |
| [Builder]()                                     | Step-by-step construction of complex objects      | SQL query builder                    |
| [Prototype]()                                   | Create objects by cloning                         | Game character duplication           |

### Structural Patterns

| Pattern                                   | Description                              | Example                        |
| ----------------------------------------- | ---------------------------------------- | ------------------------------ |
| [Adapter]()                               | Convert one interface into another       | Logging system integration     |
| [Bridge]()                                | Separate abstraction from implementation | Graphics rendering engine      |
| [Composite]()                             | Handle tree-structured data              | File system representation     |
| [Decorator]()                             | Dynamically add responsibilities         | Java I/O streams               |
| [Facade](src/main/java/structural/facade) | Simplify complex system interfaces       | Smart home control center      |
| [Flyweight]()                             | Efficiently share small objects          | Text editor character handling |
| [Proxy](src/main/java/structural/proxy)   | Control access to objects                | Lazy image loading             |

### Behavioral Patterns

| Pattern                                                   | Description                         | Example                            |
| --------------------------------------------------------- | ----------------------------------- | ---------------------------------- |
| [Chain of Responsibility](src/main/java/behavioral/chain) | Request handling chain              | Order approval workflow            |
| [Command](src/main/java/behavioral/command)               | Encapsulate operations as objects   | Transaction system, macro commands |
| [Iterator]()                                              | Traverse elements of a collection   | Custom collection iteration        |
| [Mediator]()                                              | Reduce dependencies between objects | Chat room system                   |
| [Memento]()                                               | Capture and restore object state    | Document history                   |
| [Observer](src/main/java/behavioral/observer)             | Notify on state changes             | Event-driven systems               |
| [State]()                                                 | Encapsulate state-specific behavior | Order state machine                |
| [Strategy](src/main/java/behavioral/strategy)             | Encapsulate a family of algorithms  | Payment method selection           |
| [Template Method]()                                       | Define the skeleton of an algorithm | Build pipelines                    |
| [Visitor]()                                               | Separate algorithms from objects    | Document export system             |
| [Interpreter]()                                           | Define a language’s grammar         | SQL parser                         |

### Concurrency Patterns

| Pattern                                                                      | Description                                                   | Example                                            |
| ---------------------------------------------------------------------------- | ------------------------------------------------------------- | -------------------------------------------------- |
| [Producer-Consumer](src/main/java/concurrency/producerconsumer)              | Coordinate tasks between threads                              | Message queue system                               |
| [Thread Pool]()                                                              | Reuse threads for better performance                          | Web server request handling                        |
| [Read-Write Lock]()                                                          | Optimize concurrent access                                    | Cache system implementation                        |
| [Two-Phase Stop](src/main/java/concurrency/twophasestop)                     | Gracefully stop threads                                       | Thread synchronization                             |
| [Guarded Suspension](src/main/java/concurrency/guardedsuspension)            | Wait for results between threads                              | Thread synchronization                             |
| [Fixed Operating Sequence](src/main/java/concurrency/fixedoperatingsequence) | Control the execution order of threads (e.g., run t1 then t2) | Thread synchronization (common interview question) |
| [Thread-Alternate Running](src/main/java/concurrency/threadalternaterunning) | Control round-robin thread execution (t1, t2, t3, t1…)        | Thread synchronization (common interview question) |

## Project Structure

```
src/main/java/
├── behavioral/            # Behavioral patterns
├── concurrency/           # Concurrency patterns
├── creational/            # Creational patterns
├── structural/            # Structural patterns
└── utils/                 # Common utility classes

src/test/java/             # Unit tests
```

## Quick Start

### Prerequisites

* Java 21
* Maven 3.6+

### Running Examples

1. Clone the repository:

   ```bash
   git clone git@github.com:ahucoder/JavaDesignPatterns.git
   cd JavaDesignPatterns
   ```
2. Build and run a particular example:

   ```bash
   mvn compile
   mvn exec:java -Dexec.mainClass="com.example.behavioral.command.Client"
   ```

## Design Principles

This project strives to follow the SOLID design principles:

1. **Single Responsibility Principle (SRP)**: Each class has only one responsibility
2. **Open/Closed Principle (OCP)**: Open for extension, closed for modification
3. **Liskov Substitution Principle (LSP)**: Subtypes should be substitutable for their base types
4. **Interface Segregation Principle (ISP)**: Clients should not be forced to depend on methods they do not use
5. **Dependency Inversion Principle (DIP)**: Depend upon abstractions, not concretions

## Related Resources

* [Refactoring.Guru Design Patterns](https://refactoring.guru/design-patterns)
* [Java Design Patterns](https://java-design-patterns.com/)
* [Expert Bilibili Video Course](https://space.bilibili.com/7968519/upload/video)

---

**Happy Coding!** 🚀
