# Part 2: Race Conditions and Synchronisation

## Concepts

### Thread-Safety
Thread-safety in Java refers to the property of an object or method to function correctly when accessed by multiple threads simultaneously. Thread-safe code manages the coordination of threads to ensure correct execution without interfering with each other.

### Race Condition
A race condition occurs when multiple threads access shared data and try to change it at the same time. It leads to unpredictable results and bugs because the outcome depends on the sequence or timing of the threads' execution.

### Deadlock
Deadlock is a situation in multi-threaded applications where two or more threads are blocked forever, each waiting for the other to release a resource. It occurs when multiple threads need the same locks but obtain them in different orders.

### Synchronisation
Synchronisation in Java is the process of controlling the access of multiple threads to shared resources. It's a mechanism to ensure that only one thread can access the resource at a given point in time, thus preventing race conditions.

### Atomic Operation
An atomic operation in Java is an operation that is performed as a single unit of work without the possibility of interference from other operations. Atomic operations are executed completely or not executed at all. They are essential for thread safety in concurrent applications.

### Atomic Variable
An atomic variable in Java supports operations that are atomic. It means that operations like reading, writing, and updating the value of the variable are thread-safe and performed as a single atomic operation. Java's concurrent package provides classes like `AtomicInteger`, `AtomicLong`, etc., for this purpose.

## Immutable Objects in Multi-threaded Applications

### Advantages
- **Thread-Safety**: Immutable objects are naturally thread-safe as their state cannot be modified after creation. No synchronization is needed.
- **No Side Effects**: They do not change state, so there are no side effects, making the code easier to understand and debug.
- **Safe Sharing**: Immutable objects can be shared between threads without synchronization or copying, reducing the overhead.
- **Memory Efficiency**: Reusing immutable objects can be more memory efficient as compared to creating new objects.

## Synchronisation Mechanisms in Java

[optional] Java provides several mechanisms for synchronisation:

1. **Synchronized Methods**: Use the `synchronized` keyword with a method to make it accessible by only one thread at a time.
2. **Synchronized Blocks**: Similar to synchronized methods but with a smaller scope. Only the code inside the block is synchronized.
3. **Locks**: The `java.util.concurrent.locks` package provides various lock implementations for more flexible synchronization.
4. **Atomic Variables**: From `java.util.concurrent.atomic` package, these provide a way to perform atomic operations without using synchronization.
5. **Volatile Keyword**: A `volatile` variable ensures that changes to a variable are visible to all threads.
6. **Read/Write Locks**: Allow multiple threads to read a shared resource but only one to write, optimizing read-heavy environments.

Each of these mechanisms has its use cases and trade-offs in terms of complexity, performance, and flexibility.
