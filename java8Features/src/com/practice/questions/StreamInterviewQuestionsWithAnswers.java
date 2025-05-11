package com.practice.questions;

import java.util.*;
import java.util.stream.*;
import java.time.LocalDate;

public class StreamInterviewQuestionsWithAnswers {

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

        // Question 6: Find max/min element
        // Use max/min with a comparator to get the largest/smallest value.
        int max = numbers.stream().max(Integer::compare).orElse(-1);
        int min = numbers.stream().min(Integer::compare).orElse(-1);
        System.out.println("Max: " + max + ", Min: " + min);

        // Question 7: Group elements by criteria
        // Group words by their length using groupingBy.
        List<String> words = Arrays.asList("apple", "banana", "pear", "kiwi");
        Map<Integer, List<String>> grouped = words.stream().collect(Collectors.groupingBy(String::length));
        System.out.println("Grouped by length: " + grouped);

        // Question 8: Partition elements
        // Partition numbers into even and odd using partitioningBy.
        Map<Boolean, List<Integer>> partitioned = numbers.stream().collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println("Partitioned: " + partitioned);

        // Question 9: Concatenate strings
        // Join words with a comma using joining.
        String joined = words.stream().collect(Collectors.joining(", "));
        System.out.println("Joined: " + joined);

        // Question 10: Calculate average
        // Use mapToInt and average to get the mean.
        double avg = numbers.stream().mapToInt(Integer::intValue).average().orElse(0);
        System.out.println("Average: " + avg);

        // Question 11: Flatten nested lists
        // Use flatMap to flatten a list of lists.
        List<List<Integer>> nested = Arrays.asList(Arrays.asList(1,2), Arrays.asList(3,4));
        List<Integer> flat = nested.stream().flatMap(Collection::stream).collect(Collectors.toList());
        System.out.println("Flattened: " + flat);

        // Question 12: Find frequency of elements
        // Use groupingBy and counting to get frequency.
        List<String> fruits = Arrays.asList("apple", "banana", "apple", "orange");
        Map<String, Long> freq = fruits.stream().collect(Collectors.groupingBy(f -> f, Collectors.counting()));
        System.out.println("Frequency: " + freq);

        // Question 13: Merge two maps
        // Use Stream.concat and toMap with merge function.
        Map<String, Integer> map1 = Map.of("a", 1, "b", 2);
        Map<String, Integer> map2 = Map.of("b", 3, "c", 4);
        Map<String, Integer> merged = Stream.concat(map1.entrySet().stream(), map2.entrySet().stream())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Integer::sum));
        System.out.println("Merged Map: " + merged);

        // Question 14: Find second highest number
        // Sort in reverse, skip first, get next.
        int secondHighest = numbers.stream().sorted(Comparator.reverseOrder()).distinct().skip(1).findFirst().orElse(-1);
        System.out.println("Second Highest: " + secondHighest);

        // Question 15: Sum of squares
        // Map to square, then sum using reduce.
        int sumSquares = numbers.stream().map(n -> n * n).reduce(0, Integer::sum);
        System.out.println("Sum of Squares: " + sumSquares);

        // Question 16: Parallel stream operations
        // Explanation: Use parallelStream() for parallel processing (be careful with shared mutable state).
        List<Integer> parallelSorted = numbers.parallelStream().sorted().collect(Collectors.toList());
        System.out.println("Parallel Sorted Numbers: " + parallelSorted);

        // Question 17: Custom collectors
        // Explanation: You can create custom collectors for special aggregation needs.
        // Example: Collect to a LinkedList instead of ArrayList.
        LinkedList<Integer> linkedList = numbers.stream().collect(Collectors.toCollection(LinkedList::new));
        System.out.println("Collected to LinkedList: " + linkedList);

        // Question 18: Reduce operations
        // Explanation: Use reduce to combine elements into a single result (e.g., product).
        int product = numbers.stream().reduce(1, (a, b) -> a * b);
        System.out.println("Product: " + product);

        // Question 19: Stream with Optional
        // Explanation: Use findFirst/findAny to get Optional results from streams.
        Optional<Integer> firstEven = numbers.stream().filter(n -> n % 2 == 0).findFirst();
        System.out.println("First Even (Optional): " + firstEven.orElse(-1));

        // Question 20: Stream with Date and Time API
        // Explanation: Stream over dates using LocalDate.datesUntil.
        LocalDate start = LocalDate.of(2025, 5, 10);
        LocalDate end = start.plusDays(3);
        List<LocalDate> dates = start.datesUntil(end).collect(Collectors.toList());
        System.out.println("Dates: " + dates);

        // Question 21: Stream with Files API
        // Explanation: Use Files.lines to stream lines from a file (try-with-resources recommended).
        // Example (commented):
        // try (Stream<String> lines = Files.lines(Paths.get("test.txt"))) {
        //     lines.forEach(System.out::println);
        // }

        // Question 22: Stream with custom objects
        // Explanation: Stream over a list of custom objects and filter/map/group as needed.
        class Person {
            String name; int age;
            Person(String n, int a) { name = n; age = a; }
            public String getName() { return name; }
            public int getAge() { return age; }
            public String toString() { return name + "(" + age + ")"; }
        }
        List<Person> people = Arrays.asList(new Person("Alice", 30), new Person("Bob", 25), new Person("Charlie", 30));
        Map<Integer, List<Person>> byAge = people.stream().collect(Collectors.groupingBy(Person::getAge));
        System.out.println("People grouped by age: " + byAge);

        // Question 23: Stream with Enum
        // Explanation: Stream over Enum values and filter/map as needed.
        enum Status { ACTIVE, INACTIVE, PENDING }
        List<Status> statuses = Arrays.asList(Status.ACTIVE, Status.INACTIVE, Status.PENDING, Status.ACTIVE);
        long activeCount = statuses.stream().filter(s -> s == Status.ACTIVE).count();
        System.out.println("Active Status Count: " + activeCount);

        // Question 24: Stream with Exception handling
        // Explanation: Use try/catch inside lambda or wrap checked exceptions.
        List<String> data = Arrays.asList("1", "2", "a", "3");
        List<Integer> parsed = data.stream().map(s -> {
            try { return Integer.parseInt(s); } catch (NumberFormatException e) { return null; }
        }).filter(Objects::nonNull).collect(Collectors.toList());
        System.out.println("Parsed Integers: " + parsed);

        // Question 25: Infinite streams and limit
        // Explanation: Use Stream.iterate or Stream.generate for infinite streams, then limit.
        List<Integer> firstTen = Stream.iterate(1, n -> n + 1).limit(10).collect(Collectors.toList());
        System.out.println("First 10 natural numbers: " + firstTen);

        // For questions 26-35, see answers/StreamInterviewQuestionsWithAnswers_26_35.java
        // For questions 36-50, continue in a new file in the same folder for easy navigation.

        System.out.println("Stream Interview Questions with Answers (1-25) prepared.");
    }
}
