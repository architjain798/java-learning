package com.in28minutes.learn_spring_framework;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.in28minutes.learn_spring_framework.game.GameRunner;
import com.in28minutes.learn_spring_framework.game.GamingConsole;
import com.in28minutes.learn_spring_framework.game.PacManGame;

// @Configuration
// public class GammingConfiguration {

//     @Bean
//     GamingConsole game() {
//         return new PacManGame();
//     }

//     @Bean
//     GameRunner gameRunner(GamingConsole game) {
//         return new GameRunner(game);
//     }

// }
