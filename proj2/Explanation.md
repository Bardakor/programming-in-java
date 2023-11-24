# EFREI - AGH : JAVA PROJECT II


## EXO 1 : Returns the movies (only titles) directed (or co-directed) by a given director

```java
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
```

## EXO 2 : Returns the movies (only titles) in which an actor played

```java
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
```

## EXO 3 : Returns the number of movies per director (as a map)

```java
static Map<String, Long> ex03() {
        // Retrieve the list of movies
        Optional<List<Movie>> optMovies = ImdbTop250.movies();

        // Use Streams to process the movies
        return optMovies.map(movies -> movies.stream()
                // FlatMap to convert List<Movie> to Stream<String> of directors
                .flatMap(movie -> movie.directors().stream())
                // Collect into a map with the director's name as key and count of movies as value
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())))
                // If the Optional is empty, return an empty map
                .orElse(new HashMap<>());
    }
```

## EXO 4 : Returns the 10 directors with the most films on the list

```java
static Map<String, Long> ex04() {
        // Get the number of movies per director and stream the entries
        return ex03().entrySet().stream()
                // Sort the entries by value in descending order
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                // Limit the results to the top 10
                .limit(10)
                // Collect the results into a map, preserving the order
                .collect(Collectors.toMap(
                        //extract key from each entry (director's name)
                        Map.Entry::getKey,
                        //extract the value from each entry (number of movies) 
                        Map.Entry::getValue,
                        //in case of key collision, keep the first entry
                        (e1, e2) -> e1,
                        // use a linkedHashMap to maintain order
                        LinkedHashMap::new));
    }
```

## EXO 5 : Returns the movies (only titles) made by each of the 10 directors found in EXO 4

This method is designed to filter and group movies from a list of top 250 movies, specifically focusing on those directed by the top 10 directors determined in the ex04() method. It returns a map where each key is a director's name, and the associated value is a set of movie titles directed by that director.

```java
static Map<String, Set<String>> ex05() {
    // Get the set of top 10 directors from the previous exercise (ex04)
    Set<String> top10Directors = ex04().keySet();

    // Retrieve the list of top 250 movies from the ImdbTop250 class, or an empty list if not present
    return ImdbTop250.movies()
            .orElse(new ArrayList<>()) // If no movies are found, use an empty list
            .stream() // Convert the list of movies to a stream for processing
            // Filter the stream to include only movies directed by any of the top 10 directors
            .filter(movie -> movie.directors().stream().anyMatch(top10Directors::contains))
            // Group the filtered movies by director, collecting their titles in a set
            .collect(Collectors.groupingBy(
                    // Extract the first director from the top 10 directors for each movie
                    movie -> movie.directors().stream().filter(top10Directors::contains).findFirst().orElse(null),
                    // Map each movie to its title and collect these in a set for each director
                    Collectors.mapping(Movie::title, Collectors.toSet())));
}

```



## EXO 6 : Returns the number of movies per actor (as a map)

This method aims to count the number of movies each actor has appeared in from a list of movies provided by ImdbTop250.movies(). It utilizes Java Streams and the Optional class to handle the list of movies and process them efficiently. The method returns a map where each key is an actor's name, and the corresponding value is the count of movies in which they have appeared.

```java
static Map<String, Long> ex06() {
    // Retrieve the list of movies from the ImdbTop250 class, wrapped in an Optional
    Optional<List<Movie>> optMovies = ImdbTop250.movies();

    // Use the Optional.map method to process the list if it's present
    return optMovies.map(movies -> movies.stream()
            // Use flatMap to convert the List<Movie> into a Stream<String> of actor names
            .flatMap(movie -> movie.actors().stream())
            // Collect the stream into a map, where each key is an actor's name, 
            // and the value is the count of movies they've appeared in
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())))
            // If the Optional<List<Movie>> is empty, return an empty HashMap
            .orElse(new HashMap<>());
}
```

## EXO 7 : Returns the 9 actors with the most films on the list

This method extends the functionality of ex06 by not only getting the number of movies per actor but also sorting these actors based on the number of movies they have appeared in, in descending order. It limits the result to the top 9 actors and collects the data into a LinkedHashMap to maintain the sorting order.

```java
static Map<String, Long> ex07() {
    // Get the number of movies per actor from the previous method (ex06) and create a stream of entries
    return ex06().entrySet().stream()
            // Sort the entries in the stream by the number of movies (value), in descending order
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            // Limit the stream to include only the top 9 actors with the most movies
            .limit(9)
            // Collect the results from the stream into a LinkedHashMap to maintain the order
            .collect(Collectors.toMap(
                    Map.Entry::getKey, // Use the actor's name (key of the entry) as the key in the new map
                    Map.Entry::getValue, // Use the number of movies (value of the entry) as the value in the new map
                    (e1, e2) -> e1, // In case of duplicate keys, keep the first entry encountered
                    LinkedHashMap::new)); // Use a LinkedHashMap to preserve the order of elements
}
```

## EXO 8 : Returns the movies (only titles) of each of the 9 actors from EXO 7

This method builds a map where each key is the name of one of the top 9 actors (by number of movies acted in) and the value is a set of titles of movies in which they have acted. It first retrieves the top 9 actors and the list of movies, then processes this data to construct the desired map. If no movies are found, it returns an empty map.

```java
static Map<String, Set<String>> ex08() {
    // Get the top 9 actors with the most movies from the ex07 method
    Map<String, Long> top9Actors = ex07();

    // Retrieve the list of movies from the ImdbTop250 class, wrapped in an Optional
    Optional<List<Movie>> optMovies = ImdbTop250.movies();

    // Use the Optional.map method to process the movies if they are present
    return optMovies.map(movies ->
        // Stream over the set of actor names from the top 9 actors
        top9Actors.keySet().stream()
            // Collect into a map, where each key is an actor's name, and the value is a set of movie titles they acted in
            .collect(Collectors.toMap(
                Function.identity(), // The actor's name serves as the key in the map
                actor -> movies.stream() // Stream over the list of movies
                        .filter(movie -> movie.actors().contains(actor)) // Filter movies where the actor has a role
                        .map(Movie::title) // Map each movie to its title
                        .collect(Collectors.toSet()) // Collect the titles in a set for each actor
            )))
    // If the Optional<List<Movie>> is empty, return an empty HashMap
    .orElse(new HashMap<>());
}

```
## EXO 9 : Returns the 5 most frequent actor partnerships (i.e., appearing together most often

This method calculates the frequency of co-appearance for each pair of actors across a list of movies. It then finds the top 5 most frequent actor pairs and returns them in a map, where each key is a string representing an actor pair and the value is the number of movies they appeared in together.

```java
static Map<String, Long> ex09() {
    // Retrieve the list of movies from the ImdbTop250 class, wrapped in an Optional
    Optional<List<Movie>> optMovies = ImdbTop250.movies();

    // Initialize a map to store the counts of appearances for each pair of actors
    Map<String, Long> actorPairCounts = new HashMap<>();

    // Check if the Optional contains a list of movies
    if (optMovies.isPresent()) {
        // Get the list of movies from the Optional
        List<Movie> movies = optMovies.get();

        // Iterate over each movie in the list
        for (Movie movie : movies) {
            // Get the list of actors for the current movie
            List<String> actors = movie.actors();

            // Generate all unique pairs of actors for the current movie
            for (int i = 0; i < actors.size(); i++) {
                for (int j = i + 1; j < actors.size(); j++) {
                    // Retrieve the names of the actors in the pair
                    String actor1 = actors.get(i);
                    String actor2 = actors.get(j);

                    // Sort the actor names to ensure consistency in how the pair is represented
                    String pair = actor1.compareTo(actor2) < 0 ? actor1 + ", " + actor2 : actor2 + ", " + actor1;

                    // Increment the count for this pair of actors in the map
                    actorPairCounts.put(pair, actorPairCounts.getOrDefault(pair, 0L) + 1);
                }
            }
        }
    }

    // Sort the pairs by their counts in descending order and limit to the top 5
    List<Map.Entry<String, Long>> sortedPairs = new ArrayList<>(actorPairCounts.entrySet());
    sortedPairs.sort(Map.Entry.<String, Long>comparingByValue().reversed());

    // Create a map to store the top 5 actor pairs
    Map<String, Long> top5ActorPairs = new LinkedHashMap<>();
    for (int i = 0; i < Math.min(sortedPairs.size(), 5); i++) {
        // Get each entry from the sorted list and add to the result map
        Map.Entry<String, Long> entry = sortedPairs.get(i);
        top5ActorPairs.put(entry.getKey(), entry.getValue());
    }

    // Return the map containing the top 5 actor pairs and their counts
    return top5ActorPairs;
}
```

## EXO 10 : Returns the movies (only titles) of each of the 5 most frequent actor partnerships

This method ex10 aims to find out which movies the top 5 actor duos (as determined by ex09) have appeared in together. It achieves this by iterating over a list of movies, checking for the presence of both actors in each duo, and collecting the titles of these movies. The result is a map where each key is an actor pair, and the value is a set of movie titles they have acted in together.

```java
static Map<String, Set<String>> ex10() {
    // Define a method named ex10 that returns a map with actor pairs as keys and sets of movie titles as values.
    
    Map<String, Long> top5ActorDuos = ex09();
    // Invoke the ex09 method, which returns a map of the top 5 actor pairs based on some frequency metric.

    Optional<List<Movie>> optMovies = ImdbTop250.movies();
    // Retrieve a list of movies from the ImdbTop250 class; this list is wrapped in an Optional to handle the possibility of it being null.

    Map<String, Set<String>> moviesPerActorDuo = new HashMap<>();
    // Initialize a new HashMap to store the mapping of actor pairs to sets of movies they've appeared in together.

    if (optMovies.isPresent()) {
        // Check if the Optional containing the list of movies actually has a value (i.e., the list is not null).

        List<Movie> movies = optMovies.get();
        // Extract the list of movies from the Optional.

        for (String actorPair : top5ActorDuos.keySet()) {
            // Iterate over each actor pair in the top 5 actor pairs map.

            Set<String> moviesForDuo = new HashSet<>();
            // Create a new HashSet to store movie titles for each actor pair.

            String[] actors = actorPair.split(", ");
            // Split the actor pair string into individual actor names.

            for (Movie movie : movies) {
                // Iterate through each movie in the list of movies.

                if (movie.actors().contains(actors[0]) && movie.actors().contains(actors[1])) {
                    // Check if both actors in the pair are part of the current movie's cast.

                    moviesForDuo.add(movie.title());
                    // Add the movie's title to the set of movies for this actor pair.
                }
            }

            moviesPerActorDuo.put(actorPair, moviesForDuo);
            // Map the actor pair to their corresponding set of movie titles.
        }
    }

    return moviesPerActorDuo;
    // Return the final map containing actor pairs and the sets of movies they appeared in together.
}
```











