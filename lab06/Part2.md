# Lab 6 Part 2: Standard functional interfaces

## 2. For each of the standard functional interfaces, write an example that uses the interface.

```java	

import java.util.function.*;

public class Functional interfacesExample{
    public static void main (String[] args){
        //Consumer : Accepts one input and performs an operation without returning any result 
        Consumer<String> consumer = s -> System.out.println("Consuming" + s);
        consumer.accept("Hello");

        //Biconsumer : Accepts two input and performs an operation without returning any result
        BiConsumer<String, String> biConsumer = (s1, s2) -> System.out.println("Consuming " + s1 + " and " + s2);

        //Supplier : Returns a result of given type without accepting any input
        Supplier<String> supplier = () -> "Hello";
        System.out.println(supplier.get());

        //Function : Accepts one argument and produces a result
        Function<String, Integer> function = s -> s.length();
        System.out.println(function.apply("Hello"));

        //Bifunction : Accepts two argument and produces a result
        BiFunction<String, String, Integer> biFunction = (s1, s2) -> s1.length() + s2.length();

        //Predicate : Evaluates a condition and returns a boolean
        Predicate<String> predicate = s -> s.isEmpty();
        System.out.println(predicate.test("Hello"));

        //Bipredicate : Evaluates a condition and returns a boolean
        BiPredicate<String, String> biPredicate = (s1, s2) -> s1.equals(s2);
        System.out.println(biPredicate.test("Hello", "Hello"));

        //UnaryOperator : Accepts one argument and produces a result of the same type as its operand
        UnaryOperator<String> unaryOperator = s -> s.toUpperCase();
        System.out.println(unaryOperator.apply("Hello"));

        //BinaryOperator : Accepts two argument and produces a result of the same type as its operand
        BinaryOperator<String> binaryOperator = (s1, s2) -> s1 + s2;
        System.out.println(binaryOperator.apply("Hello", "World"));
    }
}
