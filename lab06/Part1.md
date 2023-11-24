# Lab 6 Part 1: Functional Interfaces, Lambda Expressions, and Method References



## 1.a. Functional Interface
A**Functional Interface** in Java, an interface with only one abstract method. It can have any number of default, static methods but can contain only one abstract method. It can also declare methods of object class. They are annotated with @FunctionalInterface annotation.

Example:
```java
@FunctionalInterface
public interface MyFunctionalInterface {
    void abstractMethod();
    // It can have any number of default or static methods
}
```

## 1.b. Lambda Expression
A **Lambda Expression** in Java is a concise way to represent an anonymous function (function with no name). Depending on the context in which it's used, it can be interpreted as different types. It is very useful in collection library in Java. It's consists of a list of parameters, a "->" symbol and a body. '(x,y) -> x + y' is a lambda expression where x and y are parameters and x + y is the body.

```java
(x, y) -> x + y
```

## 1.c. Method Reference
A **Mehod Reference** is shorthand notation of a lamda expression to call a method. Instead of providing an implementation of a method, you refer to an existing method name. Method references are denoted using '::' symbol. Fot example, 'System.out::println' is a method reference for the println method of System.out.

```java
System.out::println
```

## 2. Anonymous functions corresponding to : 

```java	
// a. f1(x)=x−2; x∈ℝ
Function<Double,Double> f1 = x -> x - 2;

double result1 = f1.apply(5.0);
System.out.println(result1);
```

```java	
// b. f2(x,y)=(√x2+y2); x,y∈ℝ
BiFunction<Double, Double, Double> f2 = (x,y) -> Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

double result2 = f2.apply(3.0, 4.0);
System.out.println(result2);
```

```java	
//c. f3(x,y,z)=(√x2+y2+z2); x,y,z∈ℤ
@FunctionalInterface
interface MyFunctionalInterface <T, U, V, R> {
    R apply(T t, U u, V v);
}

TriFunction<Integer, Integer, Integer, Double> f3 = (x,y,z) -> Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));

double result3 = f3.apply(3, 4, 5);
```

## 3. Write and test anonymous functions corresponding to: sqrt, abs, log, id

```java
public class LambdaFunctions{
    public static void main(String[] args){
        // sqrt
        Function<Double, Double> sqrt = x -> Math::sqrt(x);

        // abs
        Function<Double, Double> abs = x -> Math::abs(x);

        // log
        Function<Double, Double> log = x -> Math::log(x);

        // id
        Function<Double, Double> id = x -> x;
    }
}
```

## 4. Given the following functional interface:

```java
@FunctionalInterface
interface FunIf<T,R>{
    R apply(T t);
}

public class LambdaFunctionsTest {
    public static void main(String[] args) {
        // f1: Adds 1 to an Integer and returns it
        FunIf<Integer, Integer> f1 = x -> x + 1;

        // f2: Converts an Integer to its String representation
        FunIf<Integer, String> f2 = x -> x.toString();

        // f3: Multiplies a Double by itself (square of the number)
        FunIf<Double, Double> f3 = x -> x * x;

        // f4: Checks if an Integer is even (returns true if even, false otherwise)
        FunIf<Integer, Boolean> f4 = x -> x % 2 == 0;

        // f5: Converts a Boolean to Integer (returns 1 if true, 0 if false)
        FunIf<Boolean, Integer> f5 = x -> x ? 1 : 0;

        // f6: Negates a Boolean value (returns true if input is false, and vice versa)
        FunIf<Boolean, Boolean> f6 = x -> !x;
    }
}
```
