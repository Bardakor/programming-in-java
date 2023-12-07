# Part 4: Parallel Streams

## Applying Parallel Streams in proj2

Parallel streams in Java are a feature that allows for parallel processing of data in a stream. This is particularly useful when dealing with large datasets or computationally intensive tasks. To apply parallel streams in 'proj2', you would use the `parallelStream()` method instead of `stream()` on a collection. For example:

```java
List<MyObject> list = //...;
list.parallelStream()
    .filter(/* some condition */)
    .map(/* some mapping function */)
    .collect(Collectors.toList());
```

## Comparing Performance: stream vs. parallelStream

When comparing the performance of `stream()` vs. `parallelStream()`, it's important to consider the context:

- **For small datasets or simple computations**, the overhead of parallel processing might make `stream()` faster.

- **For large datasets or complex tasks**, `parallelStream()` can significantly improve performance by dividing the workload among multiple threads.

To compare performance, you can measure the time taken by each approach to complete the same task.

## [Optional] Using Parallel Streams to Search Files

To use parallel streams to find all files in a directory that contain a given word, you can combine file walking with a parallel stream. Here's a basic example:

```java
import java.nio.file.*;
import java.io.IOException;
import java.util.stream.Collectors;

public class FileSearch {
    public static List<Path> findFilesContainingWord(Path dir, String word) throws IOException {
        return Files.walk(dir)
                    .parallel() // Use parallel stream
                    .filter(Files::isRegularFile)
                    .filter(path -> fileContainsWord(path, word))
                    .collect(Collectors.toList());
    }

    private static boolean fileContainsWord(Path file, String word) {
        // Implement logic to check if file contains the word
        return true; // Placeholder
    }

    public static void main(String[] args) throws IOException {
        Path dir = Paths.get("path/to/directory");
        String word = "targetWord";
        List<Path> files = findFilesContainingWord(dir, word);
        // Output the list of files
    }
}
```

This code provides a framework for the task. The actual implementation of `fileContainsWord` should be added to check the contents of each file.
