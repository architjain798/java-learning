package com.practice.questions;

import java.util.*;
import java.util.stream.*;

public class StreamInterviewQuestions {

    public static void main(String[] args) {
        // Question 1: Easy - How to filter elements from a list?
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> evenNumbers = numbers.stream()
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Even Numbers: " + evenNumbers);

        // Question 2: Easy - How to convert a list of strings to uppercase?
        List<String> names = Arrays.asList("john", "jane", "doe");
        List<String> upperCaseNames = names.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Uppercase Names: " + upperCaseNames);

        // Question 3: Medium - How to find the longest string in a list?
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date");
        String longestWord = words.stream()
                .max(Comparator.comparingInt(String::length))
                .orElse("");
        System.out.println("Longest Word: " + longestWord);

        // Question 4: Medium - How to group elements by length?
        Map<Integer, List<String>> groupedByLength = words.stream()
                .collect(Collectors.groupingBy(String::length));
        System.out.println("Grouped by Length: " + groupedByLength);

        // Question 5: Hard - How to flatten a nested list and remove duplicates?
        List<List<Integer>> nestedList = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(3, 4, 5),
                Arrays.asList(5, 6, 7));
        List<Integer> flatDistinctList = nestedList.stream()
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Flat Distinct List: " + flatDistinctList);

        // Question 6: Hard - How to find frequency of each character in a string?
        String input = "streaminterviewquestions";
        Map<Character, Long> frequencyMap = input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        System.out.println("Character Frequency: " + frequencyMap);

        // Question 7: Medium - How to sort a list of objects by multiple fields?
        List<String> sortedWords = words.stream()
                .sorted(Comparator.comparingInt(String::length).thenComparing(String::compareTo))
                .collect(Collectors.toList());
        System.out.println("Sorted Words: " + sortedWords);

        // Question 8: Medium - How to partition a list into two based on a predicate?
        Map<Boolean, List<Integer>> partitionedNumbers = numbers.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println("Partitioned Numbers: " + partitionedNumbers);

        // Question 9: Hard - How to perform reduction operation to find the sum of
        // squares?
        int sumOfSquares = numbers.stream()
                .map(n -> n * n)
                .reduce(0, Integer::sum);
        System.out.println("Sum of Squares: " + sumOfSquares);

        // Question 10: Hard - How to use parallel streams to improve performance?
        List<Integer> parallelSortedNumbers = numbers.parallelStream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Parallel Sorted Numbers: " + parallelSortedNumbers);

        // Question 11: Hard - How to merge two maps using streams?
        Map<String, Integer> map1 = Map.of("a", 1, "b", 2);
        Map<String, Integer> map2 = Map.of("b", 3, "c", 4);
        Map<String, Integer> mergedMap = Stream.concat(map1.entrySet().stream(), map2.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Integer::sum));
        System.out.println("Merged Map: " + mergedMap);

        // Question 12: Easy - How to count elements in a stream?
        long count = numbers.stream().count();
        System.out.println("Count of Numbers: " + count);

        // Question 13: Medium - How to find the average of numbers in a list?
        double average = numbers.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0);
        System.out.println("Average of Numbers: " + average);

        // Question 14: Medium - How to concatenate strings using streams?
        String concatenated = words.stream()
                .collect(Collectors.joining(", "));
        System.out.println("Concatenated Words: " + concatenated);

        // Question 15: Hard - How to find the second highest number in a list?
        int secondHighest = numbers.stream()
                .sorted(Comparator.reverseOrder())
                .distinct()
                .skip(1)
                .findFirst()
                .orElse(Integer.MIN_VALUE);
        System.out.println("Second Highest Number: " + secondHighest);

        // Question 16: Hard - How to create a map from a list with custom key-value
        // pairs?
        Map<String, Integer> customMap = words.stream()
                .collect(Collectors.toMap(word -> word, String::length));
        System.out.println("Custom Map: " + customMap);
    }
}
