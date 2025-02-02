package com.in28minutes.learn_spring_framework.game;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("superContraGameQualifier")
public class SuperContraGame implements GamingConsole {

    @Override
    public void up() {
        System.out.println("Up");
    }

    @Override
    public void down() {
        System.out.println("Sit down");
    }

    @Override
    public void left() {
        System.out.println("Go back");
    }

    @Override
    public void right() {
        System.out.println("Shoot a bullet");
    }
}
