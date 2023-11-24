package agh.ii.prinjava.proj2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.swing.text.html.Option;

import agh.ii.prinjava.proj2.dal.ImdbTop250;
import agh.ii.prinjava.proj2.model.Movie;

interface PlayWithMovies {

    /**
     * Returns the movies (only titles) directed (or co-directed) by a given
     * director
     */
    static Set<String> ex01(String director) {
        // Retrieve the list of movies, or an empty list if not present
        return ImdbTop250.movies()
                // Convert the list to a stream
                .orElse(new ArrayList<>())
                .stream()
                // Filter movies directed by the specified director
                .filter(movie -> movie.directors().contains(director))
                // Map each movie to its title
                .map(Movie::title)
                // Collect the titles into a set to eliminate duplicates
                .collect(Collectors.toSet());
    }

    /**
     * Returns the movies (only titles) in which an actor played
     */
    static Set<String> ex02(String actor) {
        // Retrieve the list of movies, or an empty list if not present
        return ImdbTop250.movies()
                // Convert the list to a stream
                .orElse(new ArrayList<>())
                .stream()
                // Filter movies featuring the specified actor
                .filter(movie -> movie.actors().contains(actor))
                // Map each movie to its title
                .map(Movie::title)
                // Collect the titles into a set to eliminate duplicates
                .collect(Collectors.toSet());
    }

    /**
     * Returns the number of movies per director (as a map)
     */
    static Map<String, Long> ex03() {
        // Retrieve the list of movies
        Optional<List<Movie>> optMovies = ImdbTop250.movies();

        // Use Streams to process the movies
        return optMovies.map(movies -> movies.stream()
                // FlatMap to convert List<Movie> to Stream<String> of directors
                .flatMap(movie -> movie.directors().stream())
                // Collect into a map with the director's name as key and count of movies as
                // value
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())))
                // If the Optional is empty, return an empty map
                .orElse(new HashMap<>());
    }

    /**
     * Returns the 10 directors with the most films on the list
     */
    static Map<String, Long> ex04() {
        // Get the number of movies per director and stream the entries
        return ex03().entrySet().stream()
                // Sort the entries by value in descending order
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                // Limit the results to the top 10
                .limit(10)
                // Collect the results into a map, preserving the order
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));
    }

    /**
     * Returns the movies (only titles) made by each of the 10 directors found in
     * {@link PlayWithMovies#ex04 ex04}
     */
    static Map<String, Set<String>> ex05() {
        // Get the set of top 10 directors
        Set<String> top10Directors = ex04().keySet();
        // Retrieve movies or an empty list if not present
        return ImdbTop250.movies()
                // Convert the list to a stream
                .orElse(new ArrayList<>())
                .stream()
                // Filter movies directed by any of the top 10 directors
                .filter(movie -> movie.directors().stream().anyMatch(top10Directors::contains))
                // Group movies by director and collect their titles in a set
                .collect(Collectors.groupingBy(
                        movie -> movie.directors().stream().filter(top10Directors::contains).findFirst().orElse(null),
                        Collectors.mapping(Movie::title, Collectors.toSet())));
    }

    /**
     * Returns the number of movies per actor (as a map)
     */
    static Map<String, Long> ex06() {
        // Retrieve the list of movies
        Optional<List<Movie>> optMovies = ImdbTop250.movies();

        // Use Streams to process the movies
        return optMovies.map(movies -> movies.stream()
                // FlatMap to convert List<Movie> to Stream<String> of actors
                .flatMap(movie -> movie.actors().stream())
                // Collect into a map with the actor's name as key and count of movies as value
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())))
                // If the Optional is empty, return an empty map
                .orElse(new HashMap<>());
    }

    /**
     * Returns the 9 actors with the most films on the list
     */
    static Map<String, Long> ex07() {
        // Get the number of movies per actor and stream the entries
        return ex06().entrySet().stream()
                // Sort the entries by value in descending order
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                // Limit the results to the top 9
                .limit(9)
                // Collect the results into a map, preserving the order
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));
    }

    /**
     * Returns the movies (only titles) of each of the 9 actors from
     * {@link PlayWithMovies#ex07 ex07}
     */
    static Map<String, Set<String>> ex08() {
        Map<String, Long> top9Actors = ex07();
        Optional<List<Movie>> optMovies = ImdbTop250.movies();

        return optMovies.map(movies ->
        // Stream over the top 9 actors
        top9Actors.keySet().stream()
                // Map each actor to a pair of actor and the set of movie titles they acted in
                .collect(Collectors.toMap(
                        Function.identity(),
                        actor -> movies.stream()
                                .filter(movie -> movie.actors().contains(actor))
                                .map(Movie::title)
                                .collect(Collectors.toSet()))))
                // If the Optional is empty, return an empty map
                .orElse(new HashMap<>());
    }

    /**
     * Returns the 5 most frequent actor partnerships (i.e., appearing together most
     * often)
     */
    static Map<String, Long> ex09() {
        // Retrieve the list of movies
        Optional<List<Movie>> optMovies = ImdbTop250.movies();
        // Map to store the count of appearances of actor pairs
        Map<String, Long> actorPairCounts = new HashMap<>();

        // If movies are present
        if (optMovies.isPresent()) {
            List<Movie> movies = optMovies.get();
            // Iterate over each movie
            for (Movie movie : movies) {
                List<String> actors = movie.actors();
                // Generate all unique pairs of actors for each movie
                for (int i = 0; i < actors.size(); i++) {
                    for (int j = i + 1; j < actors.size(); j++) {
                        String actor1 = actors.get(i);
                        String actor2 = actors.get(j);

                        // Sort actor names to ensure consistency in the pair
                        String pair = actor1.compareTo(actor2) < 0 ? actor1 + ", " + actor2 : actor2 + ", " + actor1;

                        // Increment the count for each pair
                        actorPairCounts.put(pair, actorPairCounts.getOrDefault(pair, 0L) + 1);
                    }
                }
            }
        }

        // Sort and limit the results to the top 5 pairs
        List<Map.Entry<String, Long>> sortedPairs = new ArrayList<>(actorPairCounts.entrySet());
        sortedPairs.sort(Map.Entry.<String, Long>comparingByValue().reversed());

        // Map to store the top 5 pairs
        Map<String, Long> top5ActorPairs = new LinkedHashMap<>();
        for (int i = 0; i < Math.min(sortedPairs.size(), 5); i++) {
            Map.Entry<String, Long> entry = sortedPairs.get(i);
            top5ActorPairs.put(entry.getKey(), entry.getValue());
        }

        return top5ActorPairs;
    }

    /**
     * Returns the movies (only titles) of each of the 5 most frequent actor
     * partnerships
     */
    static Map<String, Set<String>> ex10() {
        // Define a method named ex10 with no parameters that returns a Map with Strings
        // as keys and Sets of Strings as values.

        Map<String, Long> top5ActorDuos = ex09();
        // Call another method ex09, which presumably returns a Map with actor pairs as
        // keys and a Long value (possibly representing some metric like collaboration
        // frequency).

        Optional<List<Movie>> optMovies = ImdbTop250.movies();
        // Retrieve a list of movies from a class or API ImdbTop250. This list is
        // wrapped in an Optional, indicating it might or might not contain a value.

        Map<String, Set<String>> moviesPerActorDuo = new HashMap<>();
        // Create a new HashMap to store actor pairs (as String) and the Set of movie
        // titles they've acted in together.

        if (optMovies.isPresent()) {
            // Check if the Optional object contains a list of movies.

            List<Movie> movies = optMovies.get();
            // Retrieve the actual list of movies from the Optional object.

            for (String actorPair : top5ActorDuos.keySet()) {
                // Iterate through each actor pair in the Map returned by ex09.

                Set<String> moviesForDuo = new HashSet<>();
                // Create a new HashSet to store the titles of movies for the current actor duo.

                String[] actors = actorPair.split(", ");
                // Split the actor pair string into individual actor names.

                for (Movie movie : movies) {
                    // Iterate through each movie in the movie list.

                    if (movie.actors().contains(actors[0]) && movie.actors().contains(actors[1])) {
                        // Check if both actors in the current pair acted in this movie.

                        moviesForDuo.add(movie.title());
                        // Add the movie title to the set of movies for the current actor duo.
                    }
                }

                moviesPerActorDuo.put(actorPair, moviesForDuo);
                // Add the actor pair and their corresponding set of movies to the final Map.
            }
        }

        return moviesPerActorDuo;
        // Return the Map containing actor pairs and the movies they've acted in
        // together.
    }

}
