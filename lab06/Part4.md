# Lab 6 Part 4 : Function Composition

## 1. Function.Compose

### a. For f1(x)=2x, g1(x)=x2, x∈ℝ

```java

import java.util.function.Function;

public class FunctionComposition{
    public static void main (String[] args){
        Function<Double,Double> f1 = x -> 2 * x;
        Function<Double,Double> g1 = x -> x * x;

        Function<Double,Double> f1g1 = f1.compose(g1);

        double result = f1g1.apply(5.0);
        System.out.println(result);
    }
}
```

### b. For f2(x)=sin(x), g2(x)=1−x/1+x2, x∈ℝ

```java

import java.util.function.Function;

public class FunctionComposition{
    public static void main (String[] args){
        Function<Double,Double> f2 = x -> Math.sin(x);
        Function<Double,Double> g2 = x -> (1 - x) / (1 + x * x);

        Function<Double,Double> f2g2 = f2.compose(g2);

        double result = f2g2.apply(5.0);
        System.out.println(result);
    }
}
```

### c. For f3(x)=1−sin(x/)1+2x2, g3(x)=cos(x), x∈ℝ

```java

import java.util.function.Function;

public class FunctionComposition{
    public static void main (String[] args){
        Function<Double,Double> f3 = x -> (1 - Math.sin(x)) / (1 + 2 * x * x);
        Function<Double,Double> g3 = x -> Math.cos(x);

        Function<Double,Double> f3g3 = f3.compose(g3);

        double result = f3g3.apply(5.0);
        System.out.println(result);
    }
}
```

## 2. Function.andThen

### a. For f1(x)=2x, g1(x)=x2, x∈ℝ

```java
Function<Double,Double> andThenFunction = g1.andThen(f1);
```

### b. For f2(x)=sin(x), g2(x)=1−x/1+x2, x∈ℝ

```java
Function<Double,Double> andThenFunction = g2.andThen(f2);
```

### c. For f3(x)=1−sin(x/)1+2x2, g3(x)=cos(x), x∈ℝ

```java
Function<Double,Double> andThenFunction = g3.andThen(f3);
```

## 3. Write a function/method that composes a given list of functions

```java
import java.util.List;
import java.util.function.Function;

public class FunctionComposer {

    // A generic method that composes a list of functions into a single function.
    // The generic type <T> ensures that the method can work with functions of any type.
    public static <T> Function<T, T> composeFunctions(List<Function<T, T>> functionList) {
        // Start with the identity function. This function does nothing (returns its input).
        // It serves as an initial value for the composition.
        Function<T, T> resultFunction = Function.identity();

        // Iterate over each function in the list.
        for (Function<T, T> function : functionList) {
            // Compose the current function with the resultFunction.
            // The compose method applies the specified function first and then applies
            // the resultFunction to the result of the first function.
            resultFunction = resultFunction.compose(function);
        }

        // Return the composed function.
        return resultFunction;
    }

    public static void main(String[] args) {
        // Example usage with Double type functions
        // Define a list of functions to be composed.
        List<Function<Double, Double>> functions = List.of(
                x -> 2 * x,  // First function: Multiplies by 2
                x -> x + 3,  // Second function: Adds 3
                x -> x * x   // Third function: Squares the result
        );

        // Compose all functions in the list into a single function.
        Function<Double, Double> composedFunction = composeFunctions(functions);

        // Apply the composed function to a value (2.0 in this case).
        double result = composedFunction.apply(2.0);

        // Print the result. This will be the result of applying all the functions
        // in sequence to the value 2.0.
        System.out.println("Result: " + result);  // Expected output: ((2 * 2) + 3)^2 = 49.0
    }
}

```	