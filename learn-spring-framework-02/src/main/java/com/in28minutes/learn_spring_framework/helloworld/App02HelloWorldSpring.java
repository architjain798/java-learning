package com.in28minutes.learn_spring_framework.helloworld;

import java.util.Arrays;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App02HelloWorldSpring {

    public static void main(String[] args) {
        //1. Launch a Spring Context
        try (var context = new AnnotationConfigApplicationContext(HelloWorldConfiguration.class)) {
            //2. Configure the things that we want Spring to manage
            // HelloWorldConfiguration - @Configuration
            // name() - @Bean
            //3. Retrieving Beans managed by Spring
            System.out.println(context.getBean("name"));
            System.out.println(context.getBean("age"));
            System.out.println(context.getBean("person"));

            // Custom bean name
            // System.out.println(context.getBean(Address.class));
            System.out.println(context.getBean("customAddress"));
            System.out.println(context.getBean("customAddressNew"));
            System.out.println(context.getBean("person3ParameterCall"));

            // 
            // for(var x: context.getBeanDefinitionNames()) {
            //     System.out.println(x);
            // }
            Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
        
        } 

    }

}
