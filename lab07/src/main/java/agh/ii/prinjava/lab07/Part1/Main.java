package agh.ii.prinjava.lab07.Part1;

import java.util.Random;
import java.util.stream.Stream;
import java.util.stream.IntStream;

public class Main {
    // Exo 3
    static class Exo3 {
        static Stream<Boolean> getBooleanStream() {
            return Stream.of(true, false, true, false, true);
        }
    }

    static void exo3() {
        Exo3.getBooleanStream().forEach(System.out::println);
    }

    // Exo 4
    static class Exo4 {
        static Stream<Integer> getRandomIntStream() {
            return Stream.generate(new Random()::nextInt);
        }
    }

    static void exo4() {
        Exo4.getRandomIntStream().limit(10).forEach(System.out::println);
    }

    // Exo 5
    static class Exo5 {
        static Stream<Integer> getRandomEvenPosInt() {
            return Stream.iterate(2, n -> n + 2);
        }
    }

    static void exo5() {
        Exo5.getRandomEvenPosInt().limit(120).forEach(System.out::println);
    }

    // Exo 6
    static class Exo6 {
        static boolean isPrime(int number) {
            if (number <= 1)
                return false;
            return IntStream.rangeClosed(2, (int) Math.sqrt(number))
                    .allMatch(n -> number % n != 0);
        }

        static Stream<Integer> getPrimeSTream() {
            return Stream.iterate(2, n -> n + 1)
                    .filter(Exo6::isPrime)
                    .limit(20);
        }
    }

    static void exo6() {
        Exo6.getPrimeSTream().forEach(System.out::println);
    }

    static class Exo7 {
        static Stream<Long> getFibonacciStream() {
            return Stream.iterate(new long[] { 0, 1 }, f -> new long[] { f[1], f[0] + f[1] })
                    .map(f -> f[0]);
        }
    }

    static void exo7() {
        // Print first 10 Fibonacci numbers
        Exo7.getFibonacciStream().limit(10).forEach(System.out::println);
    }

    public static void main(String[] args) {
        // exo3();
        // exo4();
        // exo5();
        // exo6();
        exo7();
    }
}
