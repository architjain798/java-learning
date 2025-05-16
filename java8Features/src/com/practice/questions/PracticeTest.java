package com.practice.questions;

import java.util.*;
import java.util.stream.*;

public class PracticeTest {

    public static void main(String[] args) {
        // List<Integer> ls = Arrays.asList(1, 2, 3, 4, 5);
        // List<Integer> evenList = ls.stream().filter((num) -> num % 2 == 0).collect(Collectors.toList());
        // System.out.println(evenList);
        // System.out.println("-----------------------------------------------------------------------------------");

        
        // List<String> lsStr = Arrays.asList("abc", "xyz", "def");
        // List<String> upperStr = lsStr.stream().map((str) -> str.toUpperCase()).collect(Collectors.toList());
        // List<String> upperStr2 = lsStr.stream().map(String::toUpperCase).collect(Collectors.toList());
        // System.out.println(upperStr2);
        // System.out.println("-----------------------------------------------------------------------------------");


        // String str = "abcdefg";
        // str.chars().mapToObj(c -> String.valueOf(c)).forEach(System.out::println);
        // System.out.println("-----------------------------------------------------------------------------------");

        
        // String[] array = { "abc", "def", "xyz" };
        // List<String> newList = (Arrays.stream(array).map((s) -> s.toUpperCase()).collect(Collectors.toList()));
        // System.out.println(newList.getClass().getSimpleName());
        // System.out.println("-----------------------------------------------------------------------------------");


        // String[] upperCaseArray = Arrays.stream(array).map((s) -> s.toUpperCase()).toArray(size -> new String[size]);
        // System.out.println(Arrays.toString(upperCaseArray));
        // System.out.println(upperCaseArray.getClass().getSimpleName());


       Stream.generate(() -> 1).forEach(System.out::println);

    }
}
