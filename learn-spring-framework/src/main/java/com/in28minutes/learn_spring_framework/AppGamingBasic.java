package com.in28minutes.learn_spring_framework;

import com.in28minutes.learn_spring_framework.game.GameRunner;
import com.in28minutes.learn_spring_framework.game.MarioGame;
import com.in28minutes.learn_spring_framework.game.PacManGame;
import com.in28minutes.learn_spring_framework.game.SuperContraGame;

public class AppGamingBasic {

    public static void main(String[] args) {
        var marioGame = new MarioGame();
        var superContraGame = new SuperContraGame();
        var pacManGame= new PacManGame();

        // var gameRunner = new GameRunner(marioGame);
        // var gameRunner = new GameRunner(superContraGame);

        var gameRunner = new GameRunner(pacManGame);

        gameRunner.runGame();
    }
}
