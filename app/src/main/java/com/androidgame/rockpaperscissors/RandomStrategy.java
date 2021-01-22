package com.androidgame.rockpaperscissors;

import java.util.Random;


public class RandomStrategy implements Strategy {

    private static Random rand = new Random();
    /**
     *
     * TODO: STUDENT WORK
     * Complete the method below based on the strategy defined by this
     * class. This method returns a move that other classes can use to
     * determine behavior of a player of RPS.
     *
     * @return a Move value chosen by the strategy
     */
    @Override
    public Move getMove() {
        int action = rand.nextInt(3);
        Move myMove = null;


        switch (action) {
            case 0:
                myMove = Move.ROCK;
                break;
            case 1:
                myMove = Move.PAPER;
                break;
            case 2:
                myMove = Move.SCISSORS;
        }

        return myMove;

    }
}
