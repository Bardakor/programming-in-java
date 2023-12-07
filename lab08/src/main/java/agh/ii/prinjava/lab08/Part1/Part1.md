# Part 1: Creating and executing tasks

## Process, Thread, and Task in Java

### Process
A process is an instance of a computer program that is being executed. It contains the program code and its current activity. Each process has a separate memory address space, which means a process runs independently and is isolated from other processes. In Java, processes are managed by the operating system and are heavyweight, requiring more resources.

### Thread
A thread is the smallest unit of execution within a process. A Java thread runs in the same memory space as its parent process, allowing threads to share data with each other more efficiently than if they were separate processes. Threads are lightweight, requiring less overhead than a process. Java applications use threads to perform tasks simultaneously, enabling efficient use of resources.

### Task
A task is a piece of work which a thread performs. In Java, tasks are often represented as code that implements the `Runnable` or `Callable` interfaces. Tasks can be executed by threads directly or submitted to an executor service.

## Creating Execution Threads in Java

There are two primary ways to create threads in Java:

1. **Extending the `Thread` class**: By extending the `Thread` class, a class inherits its properties and can override the `run` method to define the code the thread will execute. An instance of this class is a thread object.

2. **Implementing the `Runnable` interface**: A class can implement the `Runnable` interface and implement the `run` method. An instance of this class can then be passed to a `Thread` object and executed by calling `start` on the `Thread` object.

## Runnable vs Callable

- **Runnable**: 
  - An interface used to create a task that can be executed by a thread.
  - Has a single method `run` which does not return a value and cannot throw a checked exception.

- **Callable**:
  - Introduced in Java 5 as an alternative to `Runnable`.
  - Has a single method `call` which can return a value (of a specified type) and can throw a checked exception.
  - Used when a task needs to return a result or throw an exception.

## Executor Framework Components

The Executor Framework in Java provides a higher-level replacement for working with threads directly and simplifies the management of thread life cycles. Key components include:

- **Executors**: Utility class providing factory methods for creating various types of executor services.
- **Executor**: Basic interface for executing tasks.
- **ExecutorService**: An extended interface of `Executor` that adds features like tracking and completion of tasks.
- **ScheduledExecutorService**: An interface that can schedule tasks to run after a delay or periodically.
- **ThreadPoolExecutor**: A class that executes each submitted task using one of the pooled threads.

## Thread State Diagram

[optional] The life cycle of a thread in Java includes several states:

- **New**: When a thread is created but not yet started.
- **Runnable**: When a thread is ready to run and waiting for CPU time.
- **Running**: The thread is currently executing.
- **Blocked/Waiting**: The thread is waiting for external processing such as IO operations to complete.
- **Timed Waiting**: The thread is waiting for a specified period of time.
- **Terminated**: The thread has completed execution or has been stopped.

## Concurrency vs Parallelism

- **Concurrency**:
  - Refers to an application handling multiple tasks at the same time.
  - Involves executing multiple tasks within overlapping time periods, not necessarily simultaneously.
  - Aims at maximizing the utilization of CPU time.

- **Parallelism**:
  - Involves performing multiple operations at the exact same time, typically on multiple CPU cores.
  - Focuses on increasing the throughput and computational speed.

While both aim to execute multiple tasks, concurrency is about dealing with lots of tasks at once, and parallelism is about doing lots of things at once.
