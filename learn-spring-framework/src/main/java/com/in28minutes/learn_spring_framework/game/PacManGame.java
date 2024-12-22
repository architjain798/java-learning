package com.in28minutes.learn_spring_framework.game;

public class PacManGame implements GamingConsole{

    @Override
    public void up() {
       System.out.println("up for pacman");
    }

    @Override
    public void down() {
        System.out.println("down for pacman");
    }

    @Override
    public void left() {
        System.out.println("left for pacman");

    }

    @Override
    public void right() {
        System.out.println("right for pacman");
    }

}