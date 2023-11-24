# Part 5 : Primitive type streams `IntSream`, `LongStream`, `DoubleStream`

## 1. Compare IntStream with Stream<Integer>

`IntSTream` is a specialiazed stream for primitive `int` values, whereas `Stream<Integer` is a stream of boxed `Integer` objects.

## 2. Explain following 

```java 
public class Main {
    public static void main(String[] args) {
        // declare and initialize a 2D array of int
        int[][] a = { { 1, 2 }, { 3, 4 }, { 5, 6 } };

        // Print the sum of all element in 2D array
        System.out.println(
                // create stream of array
                Stream.of(a)
                        // converts each array ton an IntStream and sums its elements
                        .mapToInt(e -> IntStream.of(e).sum())
                        // sums up all of the sums of the arrays
                        .sum());

        // declare and initialize an array of doubles
        double[] numbers = { 1.2, 1, 2.2, 3.6 };
        // print sum of all elements in array after converting them to int
        System.out.println(
                // creates a stream of doubles
                DoubleStream.of(numbers)
                        // converts each double to int
                        .mapToInt(e -> (int) e)
                        // sums up all int
                        .sum());
    }
}
```
