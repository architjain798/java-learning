package com.in28minutes.learn_spring_framework.game;

public class GameRunner {
    GamingConsole game;

    public GameRunner(GamingConsole game) {
        this.game = game;
    }

    public void runGame() {
       System.out.println("Running Game"+game);
       game.up();
       game.down();
       game.left();
       game.right();
    }

}
