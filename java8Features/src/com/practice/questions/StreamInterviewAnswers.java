package com.practice.questions;

import java.util.*;
import java.util.stream.*;

public class StreamInterviewAnswers {

    public static void main(String[] args) {
        // Question 1: Filter even numbers
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> evenNumbers = numbers.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());
        System.out.println("Even Numbers: " + evenNumbers);

        // Question 2: Convert strings to uppercase
        List<String> names = Arrays.asList("alice", "bob", "charlie");
        List<String> upperCaseNames = names.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println("Uppercase Names: " + upperCaseNames);

        // Question 3: Count elements
        long count = numbers.stream().count();
        System.out.println("Count: " + count);

        // Question 4: Find distinct elements
        List<Integer> distinctNumbers = numbers.stream().distinct().collect(Collectors.toList());
        System.out.println("Distinct Numbers: " + distinctNumbers);

        // Question 5: Sort elements
        List<Integer> sortedNumbers = numbers.stream().sorted().collect(Collectors.toList());
        System.out.println("Sorted Numbers: " + sortedNumbers);

        // Intermediate Example: Group elements by length
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date");
        Map<Integer, List<String>> groupedByLength = words.stream().collect(Collectors.groupingBy(String::length));
        System.out.println("Grouped by Length: " + groupedByLength);

        // Advanced Example: Parallel stream sorting
        List<Integer> parallelSorted = numbers.parallelStream().sorted().collect(Collectors.toList());
        System.out.println("Parallel Sorted Numbers: " + parallelSorted);

        // Spring Boot Practical Example: Convert entities to map
        // Example: entities.stream().collect(Collectors.toMap(Entity::getId, Function.identity()));

        // Real-world Scenario Example: CSV parsing
        // Example: Files.lines(Paths.get("data.csv")).map(line -> line.split(",")).collect(Collectors.toList());

        // Performance Optimization Example: Lazy evaluation
        List<Integer> lazyEvaluated = numbers.stream().filter(n -> n > 3).limit(2).collect(Collectors.toList());
        System.out.println("Lazy Evaluated Numbers: " + lazyEvaluated);

        System.out.println("Stream Interview Answers prepared.");
    }
}
