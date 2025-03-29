package com.practice.questions;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PracticeQuestion {

    public static void main(String[] args) {
        List<String> fruits = Arrays.asList("archit", "jain", "ajay");

        Map<String, Integer> ans = fruits.stream().collect(Collectors.toMap(fruit -> fruit, fruit -> fruit.length(), (existing, duplicate) -> existing));
        // System.out.println(ans);

        String str = "my name is archit jain";

        // List <String> longestWord = Arrays.stream(str.split(" ")).filter().collect(Collectors.toList());
        // System.out.println(longestWord);
        List<List<String>> listOLists = Arrays.asList(
                Arrays.asList("a", "b"),
                Arrays.asList("c", "d"));
        List<String> flatList = listOLists.stream()
                .flatMap(Collection::stream)
                // .peek(System.out::println)
                .collect(Collectors.toList());
    }
}
