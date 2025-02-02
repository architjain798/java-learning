package com.in28minutes.learn_spring_framework;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.in28minutes.learn_spring_framework.game.GameRunner;
import com.in28minutes.learn_spring_framework.game.GamingConsole;

@Configuration
@ComponentScan("com.in28minutes.learn_spring_framework.game") 
public class App03GamingSpringBeans {

    @Bean
    GameRunner gameRunner(GamingConsole game) {
        return new GameRunner(game);
    }

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(App03GamingSpringBeans.class)) {

             context.getBean(GamingConsole.class).up();

             context.getBean(GameRunner.class).runGame();

            // Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);

        }
    }
}
