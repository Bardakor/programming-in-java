package agh.ii.prinjava.lab07.Part4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    // imperative implementation of filter
    static class ImpFilter {
        static List<Integer> filter(List<Integer> list, Predicate<Integer> predicate) {
            List<Integer> filteredList = new ArrayList<>();
            for (Integer item : list) {
                if (predicate.test(item)) {
                    filteredList.add(item);
                }
            }
            return filteredList;
        }
    }

    static void impfilter() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> evenNumbers = ImpFilter.filter(numbers, n -> n % 2 == 0);
        System.out.println("Even numbers: " + evenNumbers);
    }

    // imperative implementation of Map
    static class impMap {
        static List<Integer> map(List<Integer> list, Function<Integer, Integer> function) {
            List<Integer> mappedList = new ArrayList<>();
            for (Integer item : list) {
                mappedList.add(function.apply(item));
            }
            return mappedList;
        }
    }

    static void impMap() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> squaredNumbers = impMap.map(numbers, n -> n * n);
        System.out.println("Squared numbers: " + squaredNumbers);
    }

    // imperative implemetnation of flatMap
    static class ImpFlatMap {
        static List<Integer> flatMap(List<Integer> list, Function<Integer, List<Integer>> function) {
            List<Integer> flatMappedList = new ArrayList<>();
            for (Integer item : list) {
                List<Integer> mappedResult = function.apply(item);
                flatMappedList.addAll(mappedResult);
            }
            return flatMappedList;
        }
    }

    static void impFlatMap() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        List<Integer> doubledNumbers = ImpFlatMap.flatMap(numbers, n -> List.of(n, n * 2));
        System.out.println("Doubled numbers: " + doubledNumbers);
    }

    // imperative implemation of reduce
    static class ImpReduce {
        static int reduce(List<Integer> list, BinaryOperator<Integer> operator) {
            int result = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                result = operator.apply(result, list.get(i));
            }
            return result;
        }
    }

    static void impReduce() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        int sum = ImpReduce.reduce(numbers, (a, b) -> a + b);
        System.out.println("Sum of numbers: " + sum);
    }

    // Given a stream of 100 random integers print out only even values
    static class EvenPrinter {
        static void printEvenValues() {
            // create stream of 100 integers
            Random random = new Random();
            IntStream randoInt = random.ints(100);

            // filter and print even values
            randoInt
                    .filter(n -> n % 2 == 0)
                    .forEach(System.out::println);
        }
    }

    // EXO 3
    static class StringProcessor {
        static void printFirstLetterCapitalized(List<String> strings) {
            strings.stream().map(s -> Character.toUpperCase(s.charAt(0))).forEach(System.out::println);
        }
    }

    // EXO 4
    static class ListFlattener {
        static List<Integer> flattenListOfLIsts(List<List<Integer>> listOfList) {
            return listOfList.stream()
                    .flatMap(List::stream)
                    .collect(Collectors.toList());
        }
    }

    // EXO 5
    static class SumCalculator {
        static int computeSumofRandoInt() {
            Random random = new Random();
            IntStream randomPositiveIntegers = random.ints(100, 1, 20000); // Generate 100 random positive
                                                                           // integers
            return randomPositiveIntegers.reduce(0, Integer::sum); // Compute their sum
        }
    }

    // EXO 6
    static class ProductCalculator {
        static int computeProductOfRandomPositiveIntegers() {
            Random random = new Random();
            IntStream randomPositiveIntegers = random.ints(7, 1, 20000); // Generate 7 random positive
                                                                         // integers
            return randomPositiveIntegers.reduce(1, (a, b) -> a * b); // Compute their product
        }
    }

    // EXO 7
    static class MaxValueFinder {
        static int findMaxOfRandomPositiveIntegers() {
            Random random = new Random();
            IntStream randomPositiveIntegers = random.ints(100, 1, 200000); // Generate 100 random positive integers
            return randomPositiveIntegers.reduce(Integer.MIN_VALUE, Integer::max);
        }
    }

    // EXO 8
    static class Concatenator {
        static String concatenateRandomPositiveIntegers() {
            Random random = new Random();
            IntStream randomPositiveIntegers = random.ints(10, 1, 200000); // Generate 10 random positive
                                                                                      // integers
            return randomPositiveIntegers.mapToObj(String::valueOf).reduce("", (a, b) -> a + b);
        }
    }

    public static void main(String[] args) {
        // Exo 1
        impfilter();
        impMap();
        impFlatMap();
        impReduce();

        // Exo 2
        EvenPrinter.printEvenValues();

        // Exo 3
        StringProcessor.printFirstLetterCapitalized(List.of("alpha", "bravo",
        "charlie", "delta"));

        // Exo 4
        List<List<Integer>> listOLists = List.of(
        List.of(1, 2),
        List.of(3, 4, 5),
        List.of(6, 7, 8, 9));
        List<Integer> flattIntegersList =
        ListFlattener.flattenListOfLIsts(listOLists);
        System.out.println(flattIntegersList);

        // Exo 5
        int sum = SumCalculator.computeSumofRandoInt();
        System.out.println("Sum of random positive integers: " + sum);

        //Exo 6
        int product = ProductCalculator.computeProductOfRandomPositiveIntegers();
        System.out.println("Product of random positive integers: " + product);

        // Exo 7
        int max = MaxValueFinder.findMaxOfRandomPositiveIntegers();
        System.out.println("Max value of random positive integers: " + max);

        // Exo 8
        String concatenatedString = Concatenator.concatenateRandomPositiveIntegers();
        System.out.println("Concatenated string of random positive integers: " + concatenatedString);
    }
}
