package agh.ii.prinjava.lab07.Part7;

import agh.ii.prinjava.lab07.model.Movie;
import agh.ii.prinjava.lab07.dal.ImdbTop250;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    public static long totalNumberOfActors() {
        return ImdbTop250.movies()
                .orElse(Collections.emptyList())
                .stream()
                .flatMap(movie -> movie.actors().stream())
                .distinct()
                .count();
    }

    public static long totalNumberOfMoviesRatedPG13() {
        return ImdbTop250.movies()
                .orElse(Collections.emptyList())
                .stream()
                .filter(movie -> "PG-13".equals(movie.rated()))
                .count();
    }

    public static long totalNumberOfGenres() {
        return ImdbTop250.movies()
                .orElse(Collections.emptyList())
                .stream()
                .flatMap(movie -> movie.genre().stream())
                .distinct()
                .count();
    }

    public static Map<String, List<String>> moviesForEachCertification() {
        return ImdbTop250.movies()
                .orElse(Collections.emptyList())
                .stream()
                .collect(Collectors.groupingBy(
                        Movie::rated,
                        Collectors.mapping(Movie::title, Collectors.toList())
                ));
    }

    public static Map<String, Long> numberOfMoviesForEachCertification() {
        return ImdbTop250.movies()
                .orElse(Collections.emptyList())
                .stream()
                .collect(Collectors.groupingBy(
                        Movie::rated,
                        Collectors.counting()
                ));
    }

    public static Map<String, List<String>> moviesForEachActor() {
        return ImdbTop250.movies()
                .orElse(Collections.emptyList())
                .stream()
                .flatMap(movie -> movie.actors().stream().map(actor -> new AbstractMap.SimpleEntry<>(actor, movie.title())))
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList())
                ));
    }

    public static Map<String, Long> numberOfMoviesForEachActor() {
        return ImdbTop250.movies()
                .orElse(Collections.emptyList())
                .stream()
                .flatMap(movie -> movie.actors().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public static List<String> fiveMostFrequentDirectors() {
        return ImdbTop250.movies()
                .orElse(Collections.emptyList())
                .stream()
                .flatMap(movie -> movie.director().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public static List<String> fiveMostFrequentActors() {
        return ImdbTop250.movies()
                .orElse(Collections.emptyList())
                .stream()
                .flatMap(movie -> movie.actors().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public static void main(String[] args){
        System.out.println("Total number of actors: " + totalNumberOfActors());
        System.out.println("Total number of movies rated PG-13: " + totalNumberOfMoviesRatedPG13());
        System.out.println("Total number of genres: " + totalNumberOfGenres());
        System.out.println("Movies for each certification: " + moviesForEachCertification());
        System.out.println("Number of movies for each certification: " + numberOfMoviesForEachCertification());
        System.out.println("Movies for each actor: " + moviesForEachActor());
        System.out.println("Number of movies for each actor: " + numberOfMoviesForEachActor());
        System.out.println("Five most frequent directors: " + fiveMostFrequentDirectors());
        System.out.println("Five most frequent actors: " + fiveMostFrequentActors());
    }
}