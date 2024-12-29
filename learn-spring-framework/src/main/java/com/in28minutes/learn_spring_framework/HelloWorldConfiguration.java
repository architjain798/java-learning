package com.in28minutes.learn_spring_framework;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

record Address(String firstLine, String city) {

}

record Person(String name, int age, Address address) {

}

@Configuration
public class HelloWorldConfiguration {

    @Bean
    String name() {
        return "Test User";
    }

    @Bean
    int age() {
        return 40;
    }

    @Bean
    Person person() {
        return new Person("Archit Jain", 26, new Address("Gurugram", "Haryana"));
    }

    @Bean
    Person person2MethodCall() {
        return new Person(name(), age(), address());
    }

    @Bean
    Person person3ParameterCall(String name, int age, Address customAddressNew) {
        return new Person(name, age, customAddressNew);
    }

    @Bean(name = "customAddress")
    Address address() {
        return new Address("Chandni Chowk", "Delhi");
    }

    @Bean(name = "customAddressNew")
    Address customAddressNew() {
        return new Address("Noida", "Uttar Pradesh");
    }

}
