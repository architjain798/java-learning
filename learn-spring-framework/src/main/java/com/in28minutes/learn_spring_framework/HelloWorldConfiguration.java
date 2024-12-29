package com.in28minutes.learn_spring_framework;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

record Person(String name, int age) {
}

record Address(String firstLine, String city) {
}

@Configuration
public class HelloWorldConfiguration {

    @Bean
    String name() {
        return "Archit Jain";
    }

    @Bean
    int age() {
        return 26;
    }

    @Bean
    Person person() {
        var obj = new Person("Archit Jain", 26);
        System.out.println(obj.age());
        return obj;
    }

    @Bean(name="customAddress")
    Address address() {
        return new Address("First Line", "City");
    }


}
