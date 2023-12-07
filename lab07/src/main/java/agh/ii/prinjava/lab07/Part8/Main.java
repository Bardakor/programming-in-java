package agh.ii.prinjava.lab07.Part8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {

    private static String readFileContent(String filePath) throws IOException {
        return Files.readString(Path.of(filePath));
    }

    private static List<String> splitIntoChapters(String content) {
        List<String> chapters = new ArrayList<>();
        Matcher matcher = Pattern.compile("(I{1,3}--.*?)(?=\\n\\nI{1,3}--|$)", Pattern.DOTALL).matcher(content);
        while (matcher.find()) {
            chapters.add(matcher.group(1).trim());
        }
        return chapters;
    }

    public static int totalNumberOfWords(String content) {
        return Arrays.stream(content.split("\\s+"))
                     .map(word -> word.replaceAll("[^a-zA-Z]", ""))
                     .filter(word -> !word.isEmpty())
                     .toArray().length;
    }

    public static int totalNumberOfItalicizedWords(String content) {
        Matcher matcher = Pattern.compile("_([^_]+)_").matcher(content);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    public static Map<Integer, Integer> numberOfWordsForEachChapter(List<String> chapters) {
        Map<Integer, Integer> chapterWordCounts = new HashMap<>();
        for (int i = 0; i < chapters.size(); i++) {
            int wordCount = totalNumberOfWords(chapters.get(i));
            chapterWordCounts.put(i + 1, wordCount); // Assuming chapters are numbered starting from 1
        }
        return chapterWordCounts;
    }

    public static List<String> tenMostFrequentWords(String content) {
        return Arrays.stream(content.toLowerCase().split("\\W+"))
                     .filter(word -> !word.isEmpty())
                     .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                     .entrySet().stream()
                     .sorted(Map.Entry.<String, Long>comparingByValue(Comparator.reverseOrder()).thenComparing(Map.Entry::getKey))
                     .limit(10)
                     .map(Map.Entry::getKey)
                     .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        try {
            String content = readFileContent("lab07/datasources/alice.txt");
            List<String> chapters = splitIntoChapters(content);

            System.out.println("Total number of words: " + totalNumberOfWords(content));
            System.out.println("Total number of italicized words: " + totalNumberOfItalicizedWords(content));
            System.out.println("Number of words for each chapter: " + numberOfWordsForEachChapter(chapters));
            System.out.println("10 most frequent words: " + tenMostFrequentWords(content));
            // Implement and call other methods as needed...
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
