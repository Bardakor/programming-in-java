# Lab 6 Part 3: Higher-order functions

## 1. Using sumOfWith, without defining any new functions, calculate ∑15i=1i5

```java
import java.util.stream.IntStream;

public class SumOfPowers {
    public static void main(String[] args){
        int sum = IntStream.rangeClosed(1,15) //Generates number from 1 to 15
                .map(i -> i * i * i * i * i) //Maps each number to its 5th power
                .sum(); //sum up all values
    }
}
```

## 2. Write and test function that returns for a given function f, the approximation of its first derivative f′ calculated as (finite-difference): f′(x0,h)≈f(x0+h)−f(x0)h.

```java

import java.util.function.DoubleUnaryOperator;

public class ExponentialApproximation{
    public static void main(String[] args){
        //test the function for diff orders of approximation and values of x
        testApproxUpTo(0, 1);
        testApproxUpTo(1, 1);
        testApproxUpTo(2, 1);
        testApproxUpTo(3, 1);
        testApproxUpTo(4, 1);
        testApproxUpTo(5, 1);
    }

    public static Double UnaryOperator expApproxUpTo(int n){
        return (double x) -> {
            double result = 0;
            double factorial = 1; //start with 0! = 1

            for (int k = 0; k <= n; k++){
                if (k > 0){
                    factorial *= k; //update factorial
                }
                result += Math.pow(x,k) / factorial; //add the next term
            }
            return result;
        }
    }

    public static void testApproxUpTo(int n, double x){
        DoubleUnaryOperator expApprox = expApproxUpTo(n);
        double approx = expApprox.applyAsDouble(x);
        System.out.println("Approximation of e^" + x + " up to order " + n + " is " + approx);
    }
}

```
## 3. Analyse and test the following method:

 ```java
 private static <T, R> List<R> applyAll(List<Function<T, R>> fs, T x0) {
     List<R> ys = new ArrayList<>();

     for (var f : fs) {
         ys.add(f.apply(x0));
     }

     return Collections.unmodifiableList(ys);
 }
 ```

### 1. **Generics** ('<T,R>'): 
- The method uses generic types T and R which means it can work with fct that take any input type T and return type R.

### 2. **Parameters** 
- 'List<Function<T,R>> fs' : a list of fct where each fct takes an argument of type T and returns a value of type R
- 'T x0' : the input value to which all the fct in the list will be applied

### 3. **Process**
- The method itertates over the list of fct
- For each fct in the list, it applies the fct to the input value x0 and adds the result to a list of results

### 4. **Return value**
- The method returns an unmodifiable list of results, with each elements being the output of applying a function 'fs' to the input value 'x0'
- The returned list is made unmodifiable by wrapping it in a call to Collections.unmodifiableList() which returns a list that cannot be modified

### 5. **Usage**
- The method can be used to apply a list of fct to a given input value and return a list of results


### 6. **Test**
```java

import java.util.List;
import java.util.function.Function;
import java.util.Arrays;

public class FunctionApplication{
    public static void main(String[] args){
        List<Function<Integer,Integer>> functions = Arrays.asList(
            x -> x * 2 //double
            x -> x - 3 //subtract 3
            x -> x * x //square
        );

        List<Integer> results = applyAll(functions, 10);
        System.out.println(results); //prints [20, 7, 100]
}

private static <T, R> List<R> applyAll(List<Function<T, R>> fs, T x0) {
    List<R> ys = new ArrayList<>();

    for (var f : fs) {
        ys.add(f.apply(x0));
    }

    return Collections.unmodifiableList(ys);
}
```

