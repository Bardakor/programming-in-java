package agh.ii.prinjava.lab07.Part5;

import java.util.List;
import java.util.Random;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {

        // EXO 2

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

        // EXO 2

        // Print the sum of the numeric values of the characters in the array
        System.out.println(
                // creates a stream of characters
                Stream.of(new Character[] { 'D', 'B', 'A', 'C' })
                        // conerts each character to its numeric value by substracting the value of A
                        .mapToInt(e -> e - 'A')
                        // sums up all numeric values
                        .sum());

        // EXO 3
        // Create a random number generator
        Random random = new Random();

        // generate stream of 1000 random integers between a specified range
        List<Integer> randomIntegers = random.ints(1000, 1, 101)
                .boxed()
                .collect(Collectors.toList());

        // Calculate min, max, sum, average
        int min = randomIntegers.stream().mapToInt(Integer::intValue).min().orElse(0);
        int max = randomIntegers.stream().mapToInt(Integer::intValue).max().orElse(0);
        int sum = randomIntegers.stream().mapToInt(Integer::intValue).sum();
        double average = randomIntegers.stream().mapToDouble(Integer::intValue).average().orElse(0.0);

        // Print the results
        System.out.println("Minimum: " + min);
        System.out.println("Maximum: " + max);
        System.out.println("Sum: " + sum);
        System.out.println("Average: " + average);

        // EXO 5
        Stream<String> stringStream = Stream.of("Hello", "World", "OpenAI", "Java", "Stream");

        double averageLength = findAverageStringLength(stringStream);

        System.out.println("Average String Length: " + averageLength);
    }

    private static double findAverageStringLength(Stream<String> stringStream) {
        // Calculate the total length of all strings in the stream
        int totalLength = stringStream.mapToInt(String::length).sum();

        // Count the number of strings in the stream
        long count = stringStream.count();

        // Calculate the average string length
        return count == 0 ? 0 : (double) totalLength / count;

    }
}
